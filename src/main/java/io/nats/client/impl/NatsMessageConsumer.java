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

import io.nats.client.*;
import io.nats.client.api.ConsumerInfo;
import java.io.IOException;

class NatsMessageConsumer extends NatsMessageConsumerBase implements PullManagerObserver {

    protected final ConsumeOptions consumeOpts;

    protected final SimplifiedSubscriptionMaker subscriptionMaker;

    protected final Dispatcher userDispatcher;

    protected final MessageHandler userMessageHandler;

    protected final int thresholdMessages;

    protected final long thresholdBytes;

    protected final boolean isTrackingBytes;

    protected int pendingReceivedMessages;

    protected long pendingReceivedBytes;

    protected boolean noReceivedArePending;

    protected boolean forcePull;

    protected int pendingProcessedMessages;

    protected long pendingProcessedBytes;

    protected boolean processedHasCrossedThreshold;

    NatsMessageConsumer(SimplifiedSubscriptionMaker subscriptionMaker, ConsumerInfo cachedConsumerInfo, ConsumeOptions consumeOpts, Dispatcher userDispatcher, final MessageHandler userMessageHandler) throws IOException, JetStreamApiException {
        super(cachedConsumerInfo);
        this.subscriptionMaker = subscriptionMaker;
        this.consumeOpts = consumeOpts;
        this.userDispatcher = userDispatcher;
        this.userMessageHandler = userMessageHandler;
        int bm = consumeOpts.getBatchSize();
        long bb = consumeOpts.getBatchBytes();
        int rePullMessages = Math.max(1, bm * consumeOpts.getThresholdPercent() / 100);
        long rePullBytes = bb == 0 ? 0 : Math.max(1, bb * consumeOpts.getThresholdPercent() / 100);
        thresholdMessages = bm - rePullMessages;
        thresholdBytes = bb == 0 ? Integer.MIN_VALUE : bb - rePullBytes;
        isTrackingBytes = rePullBytes > 0;
        doSub(true);
    }

    protected void fullResetPending() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void statusAdjustPending(int messages, long bytes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void aboutToPull(int messages, long bytes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void updateProcessed(Message msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void messageReceived(Message msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void pullCompletedWithStatus(int messages, long bytes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void pullTerminatedByError() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void doSub(boolean first) throws JetStreamApiException, IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void resetOnException() {
        fullResetPending();
        pmm.updateLastMessageReceived();
        pmm.initOrResetHeartbeatTimer();
    }

    protected void afterPendingUpdated() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void rePull() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
