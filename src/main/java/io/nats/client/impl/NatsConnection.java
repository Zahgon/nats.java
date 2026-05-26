// Copyright 2015-2018 The NATS Authors
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at:
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package io.nats.client.impl;

import io.nats.client.*;
import io.nats.client.ConnectionListener.Events;
import io.nats.client.Options.HostnameResolveMode;
import io.nats.client.api.ServerInfo;
import io.nats.client.support.*;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;
import static io.nats.client.support.NatsConstants.*;
import static io.nats.client.support.NatsRequestCompletableFuture.CancelAction;
import static java.nio.charset.StandardCharsets.UTF_8;

class NatsConnection implements Connection {

    public static final double NANOS_PER_SECOND = 1_000_000_000.0;

    protected final Options options;

    protected final boolean forceFlushOnRequest;

    protected final StatisticsCollector statistics;

    // you can only connect in one thread
    protected volatile boolean connecting;

    // you can only disconnect in one thread
    protected volatile boolean disconnecting;

    // respect a close call regardless
    protected volatile boolean closing;

    // exception occurred in another thread while dis/connecting
    protected Exception exceptionDuringConnectChange;

    protected final ReentrantLock closeSocketLock;

    private volatile Status status;

    protected final ReentrantLock statusLock;

    protected final Condition statusChanged;

    protected CompletableFuture<DataPort> dataPortFuture;

    protected DataPort dataPort;

    protected NatsUri currentServer;

    protected NatsUri lastServer;

    protected CompletableFuture<Boolean> reconnectWaiter;

    protected final ConcurrentHashMap<NatsUri, String> serverAuthErrors;

    protected NatsConnectionReader reader;

    protected NatsConnectionWriter writer;

    protected final AtomicReference<ServerInfo> serverInfo;

    protected final Map<String, NatsSubscription> subscribers;

    // use a concurrent map so we get more consistent iteration behavior
    protected final Map<String, NatsDispatcher> dispatchers;

    protected final Collection<ConnectionListener> connectionListeners;

    protected final Map<String, NatsRequestCompletableFuture> responsesAwaiting;

    protected final Map<String, NatsRequestCompletableFuture> responsesRespondedTo;

    protected final ConcurrentLinkedDeque<CompletableFuture<Boolean>> pongQueue;

    protected final String mainInbox;

    protected final AtomicReference<NatsDispatcher> inboxDispatcher;

    protected final ReentrantLock inboxDispatcherLock;

    protected ScheduledTask pingTask;

    protected ScheduledTask cleanupTask;

    protected final AtomicBoolean needPing;

    protected final AtomicLong nextSid;

    protected final NUID nuid;

    protected final AtomicReference<String> connectError;

    protected final AtomicReference<String> lastError;

    protected final AtomicReference<CompletableFuture<Boolean>> draining;

    protected final AtomicBoolean blockPublishForDrain;

    protected final AtomicBoolean tryingToConnect;

    // these are not final so they can be nullified on close
    protected ExecutorService callbackExecutor;

    protected ExecutorService executor;

    protected ExecutorService connectExecutor;

    protected ScheduledExecutorService scheduledExecutor;

    protected final boolean advancedTracking;

    protected final ServerPool serverPool;

    protected final DispatcherFactory dispatcherFactory;

    @NonNull
    protected final CancelAction cancelAction;

    protected final boolean trace;

    protected final TimeTraceLogger timeTraceLogger;

    // allows user to opt into the level of subject validation they want
    protected interface SubjectReplyValidator {

        String validate(String subject, boolean required);
    }

    protected final SubjectReplyValidator subjectValidator;

    protected final SubjectReplyValidator replyValidator;

    protected String subjectValidate(String subject, boolean required) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected String replyValidate(String replyTo, boolean required) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected NatsConnection(@NonNull Options options) {
        trace = options.isTraceConnection();
        timeTraceLogger = options.getTimeTraceLogger();
        timeTraceLogger.trace("creating connection object");
        this.options = options;
        forceFlushOnRequest = options.forceFlushOnRequest();
        advancedTracking = options.isTrackAdvancedStats();
        this.statistics = options.getStatisticsCollector() == null ? new NatsStatistics() : options.getStatisticsCollector();
        this.statistics.setAdvancedTracking(advancedTracking);
        this.closeSocketLock = new ReentrantLock();
        this.statusLock = new ReentrantLock();
        this.statusChanged = this.statusLock.newCondition();
        this.status = Status.DISCONNECTED;
        this.reconnectWaiter = new CompletableFuture<>();
        this.reconnectWaiter.complete(Boolean.TRUE);
        this.connectionListeners = ConcurrentHashMap.newKeySet();
        if (options.getConnectionListener() != null) {
            addConnectionListener(options.getConnectionListener());
        }
        this.dispatchers = new ConcurrentHashMap<>();
        this.subscribers = new ConcurrentHashMap<>();
        this.responsesAwaiting = new ConcurrentHashMap<>();
        this.responsesRespondedTo = new ConcurrentHashMap<>();
        this.serverAuthErrors = new ConcurrentHashMap<>();
        this.nextSid = new AtomicLong(1);
        timeTraceLogger.trace("creating NUID");
        this.nuid = new NUID();
        this.mainInbox = createInbox() + ".*";
        this.lastError = new AtomicReference<>();
        this.connectError = new AtomicReference<>();
        // we want serverInfo.get to never return a null
        this.serverInfo = new AtomicReference<>(ServerInfo.EMPTY_INFO);
        this.inboxDispatcher = new AtomicReference<>();
        this.inboxDispatcherLock = new ReentrantLock();
        this.pongQueue = new ConcurrentLinkedDeque<>();
        this.draining = new AtomicReference<>();
        this.blockPublishForDrain = new AtomicBoolean();
        this.tryingToConnect = new AtomicBoolean();
        timeTraceLogger.trace("creating executors");
        options.incrementExecutorUse();
        this.executor = options.getExecutor();
        this.callbackExecutor = options.getCallbackExecutor();
        this.connectExecutor = options.getConnectExecutor();
        this.scheduledExecutor = options.getScheduledExecutor();
        timeTraceLogger.trace("creating reader and writer");
        this.reader = new NatsConnectionReader(this);
        this.writer = new NatsConnectionWriter(this);
        this.needPing = new AtomicBoolean(true);
        serverPool = options.getServerPool() == null ? new NatsServerPool() : options.getServerPool();
        serverPool.initialize(options);
        dispatcherFactory = options.getDispatcherFactory() == null ? new DispatcherFactory() : options.getDispatcherFactory();
        cancelAction = options.isReportNoResponders() ? CancelAction.REPORT : CancelAction.CANCEL;
        timeTraceLogger.trace("connection object created");
        switch(options.subjectValidationType()) {
            case None:
                subjectValidator = (subject, required) -> required ? Validator.required(subject, "Subject") : Validator.emptyAsNull(subject);
                replyValidator = (replyTo, required) -> Validator.emptyAsNull(replyTo);
                break;
            case Strict:
                subjectValidator = (subject, required) -> Validator.validateSubjectTermStrict(subject, "Subject", required);
                replyValidator = Validator::validateReplyTo;
                break;
            default:
                subjectValidator = (subject, required) -> Validator.validateSubjectTerm(subject, "Subject", required);
                replyValidator = Validator::validateReplyTo;
                break;
        }
    }

    // Connect is only called after creation
    protected void connect(boolean reconnectOnConnect) throws InterruptedException, IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void connectImpl(boolean reconnectOnConnect) throws InterruptedException, IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void forceReconnect() throws IOException, InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void forceReconnect(ForceReconnectOptions options) throws IOException, InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void forceReconnectImpl(@NonNull ForceReconnectOptions frOpts) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void reconnect() throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Reconnect can only be called when the connection is disconnected
    protected void reconnectImpl() throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void reconnectImplConnect() throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected long timeCheck(long endNanos, String message) throws TimeoutException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void traceTimeCheck(String message, long remainingNanos) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // is called from reconnect and connect
    // will wait for any previous attempt to complete, using the reader.stop and
    // writer.stop
    protected void tryToConnect(NatsUri cur, NatsUri resolved, long now) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void clearCurrentServer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void checkVersionRequirements() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void upgradeToSecureIfNeeded(NatsUri nuri) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Called from reader/writer thread
    protected void handleCommunicationIssue(Exception io) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Close socket is called when another connect attempt is possible
    // Close is called when the connection should shut down, period
    protected void closeSocket(boolean tryReconnectIfConnected, boolean forceClose) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Close socket is called when another connect attempt is possible
    // Close is called when the connection should shut down, period
    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // This method was originally built assuming there might be multiple paths to this method,
    // but it turns out there isn't. Not refactoring the code though, hence the warning suppression
    @SuppressWarnings("SameParameterValue")
    protected void close(boolean checkDrainStatus, boolean forceClose) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // these four *ExecutorIsClosed() are only used for tests
    protected boolean callbackExecutorIsClosed() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected boolean executorIsClosed() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected boolean connectExecutorIsClosed() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected boolean scheduledExecutorIsClosed() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Should only be called from closeSocket or close
    protected void closeSocketImpl(boolean forceClose) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void cleanUpPongQueue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void publish(@NonNull String subject, byte @Nullable [] body) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void publish(@NonNull String subject, @Nullable Headers headers, byte @Nullable [] body) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void publish(@NonNull String subject, @Nullable String replyTo, byte @Nullable [] body) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void publish(@NonNull String subject, @Nullable String replyTo, @Nullable Headers headers, byte @Nullable [] body) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void publish(@NonNull Message message) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void publishInternal(@NonNull String subject, @Nullable String replyTo, @Nullable Headers headers, byte @Nullable [] data, boolean flushImmediatelyAfterPublish) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public Subscription subscribe(@NonNull String subject) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public Subscription subscribe(@NonNull String subject, @NonNull String queueName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void invalidate(NatsSubscription sub) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void remove(NatsSubscription sub) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void unsubscribe(NatsSubscription sub, int after) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void sendUnsub(@NonNull NatsSubscription sub, int after) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Assumes the null/empty checks were handled elsewhere
    @NonNull
    protected NatsSubscription createSubscription(@NonNull String subject, @Nullable String queueName, @Nullable NatsDispatcher dispatcher, @Nullable NatsSubscriptionFactory factory) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected String getNextSid() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected String reSubscribe(NatsSubscription sub, String subject, String queueName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void sendSubscriptionMessage(String sid, String subject, String queueName, boolean treatAsInternal) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public String createInbox() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected int getRespInboxLength() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected String createResponseInbox(String inbox) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // If the inbox is long enough, pull out the end part, otherwise, just use the
    // full thing
    protected String getResponseToken(String responseInbox) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void cleanResponses(boolean closing) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public Message request(@NonNull String subject, byte @Nullable [] body, @Nullable Duration timeout) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public Message request(@NonNull String subject, @Nullable Headers headers, byte @Nullable [] body, @Nullable Duration timeout) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public Message request(@NonNull Message message, @Nullable Duration timeout) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Nullable
    protected Message requestInternal(@NonNull String subject, @Nullable Headers headers, byte @Nullable [] data, @Nullable Duration timeout, @NonNull CancelAction cancelAction, boolean flushImmediatelyAfterPublish) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public CompletableFuture<Message> request(@NonNull String subject, byte @Nullable [] body) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public CompletableFuture<Message> request(@NonNull String subject, @Nullable Headers headers, byte @Nullable [] body) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public CompletableFuture<Message> requestWithTimeout(@NonNull String subject, byte @Nullable [] body, @Nullable Duration timeout) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public CompletableFuture<Message> requestWithTimeout(@NonNull String subject, @Nullable Headers headers, byte @Nullable [] body, Duration timeout) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public CompletableFuture<Message> requestWithTimeout(@NonNull Message message, @Nullable Duration timeout) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public CompletableFuture<Message> request(@NonNull Message message) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @NonNull
    protected CompletableFuture<Message> requestFutureInternal(@NonNull String subject, @Nullable Headers headers, byte @Nullable [] body, @Nullable Duration futureTimeout, @NonNull CancelAction cancelAction, boolean flushImmediatelyAfterPublish) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void deliverReply(Message msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    public Dispatcher createDispatcher() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    public Dispatcher createDispatcher(@Nullable MessageHandler handler) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    public void closeDispatcher(@NonNull Dispatcher d) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void cleanupDispatcher(NatsDispatcher nd) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Map<String, Dispatcher> getDispatchers() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    public void addConnectionListener(@NonNull ConnectionListener connectionListener) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    public void removeConnectionListener(@NonNull ConnectionListener connectionListener) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    public void flush(@Nullable Duration timeout) throws TimeoutException, InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void sendConnect(NatsUri nuri) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected CompletableFuture<Boolean> sendPing() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void softPing() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public Duration RTT() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Send a ping request and push a pong future on the queue.
    // Futures are completed in order, keep this one if a thread wants to wait
    // for a specific pong. Note, if no pong returns, the wait will not return
    // without setting a timeout.
    @Nullable
    protected CompletableFuture<Boolean> sendPing(boolean treatAsInternal) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // This is a minor speed / memory enhancement.
    // We can't reuse the same instance of any NatsMessage b/c of the "NatsMessage next" state,
    // but it is safe to share the data bytes and the size since those fields are just being read
    // This constructor "ProtocolMessage(ProtocolMessage pm)" shares the data and size
    // reducing allocation of data for something that is often created and used.
    // These static instances are the ones that are used for copying in sendPing and sendPong
    protected static final ProtocolMessage PING_PROTO = new ProtocolMessage(OP_PING_BYTES, true);

    protected static final ProtocolMessage PONG_PROTO = new ProtocolMessage(OP_PONG_BYTES, true);

    protected void sendPong() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Called by the reader
    protected void handlePong() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void readInitialInfo() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void handleInfo(String infoJson) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void validatePayloadAndControlLineSizes(NatsMessage msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void queueOutgoing(NatsMessage msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void queueInternalOutgoing(NatsMessage msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void deliverMessage(NatsMessage msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void processOK() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void makeCallback(Runnable callback) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void processSlowConsumer(Consumer consumer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void processException(Exception exp) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void processError(String errorText) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected interface ErrorListenerCaller {

        void call(Connection conn, ErrorListener el);
    }

    protected void notifyErrorListener(ErrorListenerCaller elc) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected String uriDetail(NatsUri uri) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected String uriDetail(NatsUri uri, NatsUri hostOrlast) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void processConnectionEvent(Events type, String uriDetails) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public ServerInfo getServerInfo() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public InetAddress getClientInetAddress() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public Options getOptions() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public Statistics getStatistics() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected StatisticsCollector getStatisticsCollector() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected DataPort getDataPort() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Used for testing
    protected int getConsumerCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getMaxPayload() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public Collection<String> getServers() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected List<NatsUri> resolveHost(NatsUri nuri) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public String getConnectedUrl() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public Status getStatus() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public String getLastError() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearLastError() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected ExecutorService getExecutor() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected ScheduledExecutorService getScheduledExecutor() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void updateStatus(Status newStatus) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void updateStatus(Status newStatus, NatsUri resolvedUri, NatsUri hostUri) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void updateStatus(Status newStatus, String uriDetail) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected boolean isClosing() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected boolean isClosed() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected boolean isConnected() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected boolean isDisconnected() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected boolean isConnectedOrConnecting() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected boolean isDisconnectingOrClosed() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected boolean isDisconnecting() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void waitForDisconnectOrClose(Duration timeout) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void waitForConnectOrClose(Duration timeout) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void waitWhile(Duration timeout, Predicate<Void> waitWhileTrue) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void invokeReconnectDelayHandler(long totalRounds) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected ByteBuffer enlargeBuffer(ByteBuffer buffer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // For testing
    protected NatsConnectionReader getReader() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // For testing
    protected NatsConnectionWriter getWriter() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // For testing
    protected Future<DataPort> getDataPortFuture() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected boolean isDraining() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected boolean isDrained() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public CompletableFuture<Boolean> drain(@Nullable Duration timeout) throws TimeoutException, InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected boolean isAuthenticationError(String err) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void flushBuffer() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public StreamContext getStreamContext(@NonNull String streamName) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public StreamContext getStreamContext(@NonNull String streamName, @Nullable JetStreamOptions options) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public ConsumerContext getConsumerContext(@NonNull String streamName, @NonNull String consumerName) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public ConsumerContext getConsumerContext(@NonNull String streamName, @NonNull String consumerName, @Nullable JetStreamOptions options) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public JetStream jetStream() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public JetStream jetStream(JetStreamOptions options) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public JetStreamManagement jetStreamManagement() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public JetStreamManagement jetStreamManagement(JetStreamOptions options) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public KeyValue keyValue(@NonNull String bucketName) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public KeyValue keyValue(@NonNull String bucketName, @Nullable KeyValueOptions options) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public KeyValueManagement keyValueManagement() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public KeyValueManagement keyValueManagement(@Nullable KeyValueOptions options) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public ObjectStore objectStore(@NonNull String bucketName) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public ObjectStore objectStore(@NonNull String bucketName, @Nullable ObjectStoreOptions options) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public ObjectStoreManagement objectStoreManagement() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public ObjectStoreManagement objectStoreManagement(@Nullable ObjectStoreOptions options) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void ensureNotClosing() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long outgoingPendingMessageCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long outgoingPendingBytes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
