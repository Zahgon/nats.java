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

import io.nats.client.Dispatcher;
import io.nats.client.MessageHandler;
import io.nats.client.Subscription;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import static io.nats.client.support.Validator.required;
import static io.nats.client.support.Validator.validateQueueName;

class NatsDispatcher extends NatsConsumer implements Dispatcher, Runnable {

    protected final ConsumerMessageQueue incoming;

    protected final MessageHandler defaultHandler;

    protected Future<Boolean> thread;

    protected final AtomicBoolean running;

    protected final AtomicBoolean started;

    protected String id;

    // This tracks subscriptions made with the default handlers
    // There can only be one default handler subscription for any given subject
    protected final Map<String, NatsSubscription> subWithDefaultHandlerBySubject;

    // This tracks subscriptions made with non-default handlers
    protected final Map<String, NatsSubscription> subWithNonDefaultHandlerBySid;

    // There can be multiple non default handlers for any given subject, this track them
    protected final Map<String, Map<String, NatsSubscription>> subsBySidNonDefaultHandlersBySubject;

    // This tracks the non-default handler by sid
    protected final Map<String, MessageHandler> nonDefaultHandlerBySid;

    protected final Duration waitForMessage;

    NatsDispatcher(NatsConnection conn, MessageHandler handler) {
        super(conn);
        this.defaultHandler = handler;
        this.incoming = new ConsumerMessageQueue();
        this.subWithDefaultHandlerBySubject = new ConcurrentHashMap<>();
        this.subWithNonDefaultHandlerBySid = new ConcurrentHashMap<>();
        this.subsBySidNonDefaultHandlersBySubject = new ConcurrentHashMap<>();
        this.nonDefaultHandlerBySid = new ConcurrentHashMap<>();
        this.running = new AtomicBoolean(false);
        this.started = new AtomicBoolean(false);
        // This can be long since we aren't doing anything
        this.waitForMessage = Duration.ofMinutes(5);
    }

    @Override
    public void start(String id) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("SameParameterValue")
    protected void internalStart(String id, boolean threaded) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean breakRunLoop() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void run() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void stop(boolean unsubscribeAll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isActive() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    String getId() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    ConsumerMessageQueue getMessageQueue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    MessageHandler getNonDefaultHandlerBySid(String sid) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean hasNoSubs() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void resendSubscriptions() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Remove this sub from all of our tracking maps.
    // Instead of logic to figure where the sub is in the first place,
    //   we try all tracking maps, with guard rails.
    // It's possible multiple threads/workflow could be hitting this,
    //   but all the maps are ConcurrentHashMap, we're safe.
    // For the case when we do find the sub's subject mapped to the default handler,
    //   we double-check that what is mapped is the same sub, again mostly a code guard.
    void remove(NatsSubscription sub) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Dispatcher subscribe(String subject) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    NatsSubscription subscribeReturningSubscription(String subject) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Subscription subscribe(String subject, MessageHandler handler) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Dispatcher subscribe(String subject, String queueName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Subscription subscribe(String subject, String queueName, MessageHandler handler) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Assumes the subj/queuename checks are done, does check for closed status
    NatsSubscription subscribeImplCore(String subject, String queueName, MessageHandler handler) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    NatsSubscription subscribeImplJetStream(String subject, String queueName, MessageHandler handler, NatsSubscriptionFactory nsf) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private NatsSubscription _subscribeImplHandlerProvided(String subject, String queueName, MessageHandler handler, NatsSubscriptionFactory nsf) {
        NatsSubscription sub = connection.createSubscription(subject, queueName, this, nsf);
        trackSubWithUserHandler(sub.getSID(), sub, handler);
        return sub;
    }

    String reSubscribe(NatsSubscription sub, String subject, String queueName, MessageHandler handler) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void trackSubWithUserHandler(String sid, NatsSubscription sub, MessageHandler handler) {
        subWithNonDefaultHandlerBySid.put(sid, sub);
        Map<String, NatsSubscription> subsBySid = subsBySidNonDefaultHandlersBySubject.computeIfAbsent(sub.getSubject(), k -> new ConcurrentHashMap<>());
        subsBySid.put(sid, sub);
        nonDefaultHandlerBySid.put(sid, handler);
    }

    private void checkBeforeSubImpl() {
        if (!running.get()) {
            throw new IllegalStateException("Dispatcher is closed");
        }
        if (isDraining()) {
            throw new IllegalStateException("Dispatcher is draining");
        }
    }

    public Dispatcher unsubscribe(String subject) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Dispatcher unsubscribe(Subscription subscription) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Dispatcher unsubscribe(String subject, int after) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Dispatcher unsubscribe(Subscription subscription, int after) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void sendUnsubForDrain() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void cleanUpAfterDrain() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isDrained() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
