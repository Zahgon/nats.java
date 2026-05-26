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
import io.nats.client.Message;
import io.nats.client.MessageHandler;
import io.nats.client.Subscription;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

class NatsSubscription extends NatsConsumer implements Subscription {

    private String subject;

    private final String queueName;

    private String sid;

    private NatsDispatcher dispatcher;

    private ConsumerMessageQueue incoming;

    private final AtomicLong unSubMessageLimit;

    private Function<NatsMessage, Boolean> beforeQueueProcessor;

    NatsSubscription(String sid, String subject, String queueName, NatsConnection connection, NatsDispatcher dispatcher) {
        super(connection);
        this.subject = subject;
        this.queueName = queueName;
        this.sid = sid;
        this.dispatcher = dispatcher;
        this.unSubMessageLimit = new AtomicLong(-1);
        if (this.dispatcher == null) {
            this.incoming = new ConsumerMessageQueue();
        }
        setBeforeQueueProcessor(null);
    }

    void reSubscribe(String newDeliverSubject) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isActive() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void setBeforeQueueProcessor(Function<NatsMessage, Boolean> beforeQueueProcessor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Function<NatsMessage, Boolean> getBeforeQueueProcessor() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void invalidate() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void setUnsubLimit(long cd) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean reachedUnsubLimit() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    String getSID() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    NatsDispatcher getNatsDispatcher() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    ConsumerMessageQueue getMessageQueue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dispatcher getDispatcher() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSubject() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getQueueName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Message nextMessage(long timeoutMillis) throws InterruptedException, IllegalStateException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Message nextMessage(Duration timeout) throws InterruptedException, IllegalStateException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected NatsMessage nextMessageInternal(Duration timeout) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unsubscribe() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Subscription unsubscribe(int after) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void sendUnsubForDrain() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void cleanUpAfterDrain() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
