// Copyright 2020 The NATS Authors
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
import io.nats.client.api.ConsumerInfo;
import io.nats.client.support.NatsJetStreamConstants;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import static io.nats.client.support.NatsConstants.NANOS_PER_MILLI;

/**
 * This is a JetStream specific subscription.
 */
public class NatsJetStreamSubscription extends NatsSubscription implements JetStreamSubscription, NatsJetStreamConstants {

    public static final String SUBSCRIPTION_TYPE_DOES_NOT_SUPPORT_PULL = "Subscription type does not support pull.";

    public static final long EXPIRE_ADJUSTMENT = 10;

    public static final long MIN_EXPIRE_MILLIS = 20;

    protected final NatsJetStream js;

    protected String stream;

    protected String consumerName;

    protected MessageManager manager;

    NatsJetStreamSubscription(String sid, String subject, String queueName, NatsConnection connection, NatsDispatcher dispatcher, NatsJetStream js, String stream, String consumer, MessageManager manager) {
        super(sid, subject, queueName, connection, dispatcher);
        this.js = js;
        this.stream = stream;
        // might be null, someone will call setConsumerName
        this.consumerName = consumer;
        this.manager = manager;
        manager.startup(this);
    }

    void setConsumerName(String consumerName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String getConsumerName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String getStreamName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean isPullMode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // internal, for testing
    MessageManager getManager() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    void invalidate() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Message nextMessage(Duration timeout) throws InterruptedException, IllegalStateException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Message nextMessage(long timeoutMillis) throws InterruptedException, IllegalStateException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Message _nextUnmanagedWaitForever() throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Message _nextUnmanagedNoWait(String expectedPullSubject) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Message _nextUnmanaged(long timeoutNanos, String expectedPullSubject) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pull(int batchSize) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pull(PullRequestOptions pullRequestOptions) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pullNoWait(int batchSize) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pullNoWait(int batchSize, Duration expiresIn) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pullNoWait(int batchSize, long expiresInMillis) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pullExpiresIn(int batchSize, Duration expiresIn) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pullExpiresIn(int batchSize, long expiresInMillis) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Message> fetch(int batchSize, long maxWaitMillis) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Message> fetch(int batchSize, Duration maxWait) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<Message> iterate(int batchSize, Duration maxWait) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<Message> iterate(final int batchSize, long maxWaitMillis) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JetStreamReader reader(int batchSize, int repullAt) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConsumerInfo getConsumerInfo() throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
