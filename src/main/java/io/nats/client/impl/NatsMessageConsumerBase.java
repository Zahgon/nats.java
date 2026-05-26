// Copyright 2020-2023 The NATS Authors
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

import io.nats.client.JetStreamApiException;
import io.nats.client.MessageConsumer;
import io.nats.client.PullRequestOptions;
import io.nats.client.api.ConsumerInfo;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

abstract class NatsMessageConsumerBase implements MessageConsumer, PullManagerObserver {

    protected NatsJetStreamPullSubscription sub;

    protected PullMessageManager pmm;

    protected final AtomicBoolean stopped;

    protected final AtomicBoolean finished;

    protected ConsumerInfo cachedConsumerInfo;

    protected String consumerName;

    NatsMessageConsumerBase(ConsumerInfo cachedConsumerInfo) {
        this.cachedConsumerInfo = cachedConsumerInfo;
        if (cachedConsumerInfo != null) {
            this.consumerName = cachedConsumerInfo.getName();
        }
        this.stopped = new AtomicBoolean(false);
        this.finished = new AtomicBoolean(false);
    }

    void setConsumerName(String consumerName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void initSub(NatsJetStreamPullSubscription sub, boolean clearCachedConsumerInfo) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void rePull() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    public boolean isStopped() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    public boolean isFinished() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getConsumerName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConsumerInfo getConsumerInfo() throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConsumerInfo getCachedConsumerInfo() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void close() throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void fullClose() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void shutdownSub() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static class PinnablePullRequestOptions extends PullRequestOptions {

        final String pinId;

        public PinnablePullRequestOptions(String pinId, Builder b) {
            super(b);
            this.pinId = pinId;
        }

        @Override
        protected String getPinId() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
