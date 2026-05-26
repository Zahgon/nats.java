// Copyright 2021 The NATS Authors
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
import io.nats.client.api.AckPolicy;
import io.nats.client.api.ConsumerConfiguration;
import io.nats.client.api.ConsumerInfo;
import io.nats.client.api.PublishAck;
import io.nats.client.support.Validator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import static io.nats.client.PushSubscribeOptions.DEFAULT_PUSH_OPTS;
import static io.nats.client.impl.MessageManager.ManageResult;
import static io.nats.client.support.NatsJetStreamClientError.*;
import static io.nats.client.support.NatsJetStreamUtil.generateConsumerName;
import static io.nats.client.support.NatsRequestCompletableFuture.CancelAction;
import static io.nats.client.support.Validator.*;

public class NatsJetStream extends NatsJetStreamImpl implements JetStream {

    public NatsJetStream(NatsConnection connection, JetStreamOptions jsOptions) throws IOException {
        super(connection, jsOptions);
    }

    NatsJetStream(NatsJetStreamImpl impl) {
        super(impl);
    }

    // ----------------------------------------------------------------------------------------------------
    // Publish
    // ----------------------------------------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public PublishAck publish(String subject, byte[] body) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PublishAck publish(String subject, Headers headers, byte[] body) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PublishAck publish(String subject, byte[] body, PublishOptions options) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PublishAck publish(String subject, Headers headers, byte[] body, PublishOptions options) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PublishAck publish(Message message) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PublishAck publish(Message message, PublishOptions options) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompletableFuture<PublishAck> publishAsync(String subject, byte[] body) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompletableFuture<PublishAck> publishAsync(String subject, Headers headers, byte[] body) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompletableFuture<PublishAck> publishAsync(String subject, byte[] body, PublishOptions options) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompletableFuture<PublishAck> publishAsync(String subject, Headers headers, byte[] body, PublishOptions options) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompletableFuture<PublishAck> publishAsync(Message message) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompletableFuture<PublishAck> publishAsync(Message message, PublishOptions options) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private PublishAck publishSyncInternal(String subject, Headers headers, byte[] data, PublishOptions options) throws IOException, JetStreamApiException {
        Headers merged = mergePublishOptions(headers, options);
        if (jso.isPublishNoAck()) {
            conn.publishInternal(subject, null, merged, data, false);
            return null;
        }
        Message resp = makeInternalRequestResponseRequired(subject, merged, data, getTimeout(), CancelAction.COMPLETE, conn.forceFlushOnRequest);
        return processPublishResponse(resp, options);
    }

    private CompletableFuture<PublishAck> publishAsyncInternal(String subject, Headers headers, byte[] data, PublishOptions options, boolean validateSubjectAndReplyTo) {
        Headers merged = mergePublishOptions(headers, options);
        if (jso.isPublishNoAck()) {
            conn.publishInternal(subject, null, merged, data, false);
            return null;
        }
        CompletableFuture<Message> future = conn.requestFutureInternal(subject, merged, data, null, CancelAction.COMPLETE, conn.forceFlushOnRequest);
        return future.thenCompose(resp -> {
            try {
                responseRequired(resp);
                return CompletableFuture.completedFuture(processPublishResponse(resp, options));
            } catch (IOException | JetStreamApiException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private PublishAck processPublishResponse(Message resp, PublishOptions options) throws IOException, JetStreamApiException {
        if (resp.isStatusMessage()) {
            throw new IOException("Error Publishing: " + resp.getStatus().getMessageWithCode());
        }
        PublishAck ack = new PublishAck(resp);
        String ackStream = ack.getStream();
        //noinspection deprecation options.getStream() is deprecated since checking after the publish is not useful, but the functionality can't be removed
        String optAckStream = options == null ? null : options.getStream();
        // stream specified in options but different from ack should not happen but...
        if (optAckStream != null && !optAckStream.equals(ackStream)) {
            throw new IOException("Expected ack from stream " + optAckStream + ", received from: " + ackStream);
        }
        return ack;
    }

    private Headers mergePublishOptions(Headers headers, PublishOptions opts) {
        if (opts == null) {
            return headers;
        }
        // never touch the user's original headers
        Headers merged = headers == null ? null : new Headers(headers);
        merged = mergeNum(merged, EXPECTED_LAST_SEQ_HDR, opts.getExpectedLastSequence());
        merged = mergeNum(merged, EXPECTED_LAST_SUB_SEQ_HDR, opts.getExpectedLastSubjectSequence());
        merged = mergeString(merged, EXPECTED_LAST_SUB_SEQ_SUB_HDR, opts.getExpectedLastSubjectSequenceSubject());
        merged = mergeString(merged, EXPECTED_LAST_MSG_ID_HDR, opts.getExpectedLastMsgId());
        merged = mergeString(merged, EXPECTED_STREAM_HDR, opts.getExpectedStream());
        merged = mergeString(merged, MSG_ID_HDR, opts.getMessageId());
        return mergeString(merged, MSG_TTL_HDR, opts.getMessageTtl());
    }

    private Headers mergeNum(Headers h, String key, long value) {
        return value > -1 ? _merge(h, key, Long.toString(value)) : h;
    }

    private Headers mergeString(Headers h, String key, String value) {
        return Validator.nullOrEmpty(value) ? h : _merge(h, key, value);
    }

    private Headers _merge(Headers h, String key, String value) {
        if (h == null) {
            h = new Headers();
        }
        // this is always an internal header with one value per key
        return h.put(key, Collections.singletonList(value));
    }

    // ----------------------------------------------------------------------------------------------------
    // Subscribe
    // ----------------------------------------------------------------------------------------------------
    interface MessageManagerFactory {

        MessageManager createMessageManager(NatsConnection conn, NatsJetStream js, String stream, SubscribeOptions so, ConsumerConfiguration cc, boolean queueMode, boolean syncMode);
    }

    MessageManagerFactory _pushMessageManagerFactory = PushMessageManager::new;

    MessageManagerFactory _pushOrderedMessageManagerFactory = OrderedMessageManager::new;

    MessageManagerFactory _pullMessageManagerFactory = (mmConn, mmJs, mmStream, mmSo, mmCc, mmQueueMode, mmSyncMode) -> new PullMessageManager(mmConn, mmSo, mmSyncMode);

    MessageManagerFactory _pullOrderedMessageManagerFactory = (mmConn, mmJs, mmStream, mmSo, mmCc, mmQueueMode, mmSyncMode) -> new PullOrderedMessageManager(mmConn, mmJs, mmStream, mmSo, mmCc, mmSyncMode);

    JetStreamSubscription createSubscription(String userSubscribeSubject, PushSubscribeOptions pushSubscribeOptions, PullSubscribeOptions pullSubscribeOptions, String queueName, NatsDispatcher dispatcher, MessageHandler userHandler, boolean isAutoAck, PullMessageManager pmmInstance) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static class ConsumerConfigurationComparer extends ConsumerConfiguration {

        public ConsumerConfigurationComparer(ConsumerConfiguration cc) {
            super(cc);
        }

        public List<String> getChanges(ConsumerConfiguration serverCc) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    static class AsyncMessageHandler implements MessageHandler {

        MessageManager manager;

        MessageHandler userHandler;

        boolean autoAck;

        public AsyncMessageHandler(MessageManager manager, MessageHandler userHandler, boolean isAutoAck, ConsumerConfiguration settledServerCC) {
            this.manager = manager;
            this.userHandler = userHandler;
            autoAck = isAutoAck && settledServerCC.getAckPolicy() != AckPolicy.None;
        }

        @Override
        public void onMessage(Message msg) throws InterruptedException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JetStreamSubscription subscribe(String subscribeSubject) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JetStreamSubscription subscribe(String subscribeSubject, PushSubscribeOptions options) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JetStreamSubscription subscribe(String subscribeSubject, String queue, PushSubscribeOptions options) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JetStreamSubscription subscribe(String subscribeSubject, Dispatcher dispatcher, MessageHandler handler, boolean autoAck) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JetStreamSubscription subscribe(String subscribeSubject, Dispatcher dispatcher, MessageHandler handler, boolean autoAck, PushSubscribeOptions options) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JetStreamSubscription subscribe(String subscribeSubject, String queue, Dispatcher dispatcher, MessageHandler handler, boolean autoAck, PushSubscribeOptions options) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JetStreamSubscription subscribe(String subscribeSubject, PullSubscribeOptions options) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JetStreamSubscription subscribe(String subscribeSubject, Dispatcher dispatcher, MessageHandler handler, PullSubscribeOptions options) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StreamContext getStreamContext(String streamName) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConsumerContext getConsumerContext(String streamName, String consumerName) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private NatsStreamContext getNatsStreamContext(String streamName) throws IOException, JetStreamApiException {
        return new NatsStreamContext(streamName, this, conn, jso);
    }
}
