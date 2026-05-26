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
import io.nats.client.api.ConsumerConfiguration;
import io.nats.client.api.ConsumerInfo;
import java.io.IOException;
import static io.nats.client.BaseConsumeOptions.MIN_EXPIRES_MILLS;
import static io.nats.client.support.NatsConstants.NANOS_PER_MILLI;

class NatsFetchConsumer extends NatsMessageConsumerBase implements FetchConsumer {

    private final boolean isNoWaitNoExpires;

    private final long maxWaitNanos;

    private final String pullSubject;

    private long startNanos;

    private final boolean isTrackingBytes;

    private int pendingReceivedMessages;

    private long pendingReceivedBytes;

    private boolean noReceivedArePending;

    NatsFetchConsumer(SimplifiedSubscriptionMaker subscriptionMaker, ConsumerInfo cachedConsumerInfo, FetchConsumeOptions fetchConsumeOptions) throws IOException, JetStreamApiException {
        super(cachedConsumerInfo);
        boolean isNoWait = fetchConsumeOptions.isNoWait();
        long expiresInMillis = fetchConsumeOptions.getExpiresInMillis();
        isNoWaitNoExpires = isNoWait && expiresInMillis == ConsumerConfiguration.LONG_UNSET;
        long inactiveThreshold;
        if (expiresInMillis == ConsumerConfiguration.LONG_UNSET) {
            // can be for noWait
            maxWaitNanos = MIN_EXPIRES_MILLS * NANOS_PER_MILLI;
            // no need to do the 10% longer
            inactiveThreshold = MIN_EXPIRES_MILLS;
        } else {
            maxWaitNanos = expiresInMillis * NANOS_PER_MILLI;
            // 10% longer than the wait
            inactiveThreshold = expiresInMillis * 110 / 100;
        }
        pendingReceivedMessages = fetchConsumeOptions.getMaxMessages();
        pendingReceivedBytes = fetchConsumeOptions.getMaxBytes();
        noReceivedArePending = false;
        isTrackingBytes = pendingReceivedBytes > 0;
        PinnablePullRequestOptions pro = new PinnablePullRequestOptions(pmm == null ? null : pmm.currentPinId, PullRequestOptions.builder(pendingReceivedMessages).maxBytes(pendingReceivedBytes).expiresIn(expiresInMillis).idleHeartbeat(fetchConsumeOptions.getIdleHeartbeat()).noWait(isNoWait).group(fetchConsumeOptions.getGroup()).priority(fetchConsumeOptions.getPriority()).minPending(fetchConsumeOptions.getMinPending()).minAckPending(fetchConsumeOptions.getMinAckPending()));
        initSub(subscriptionMaker.subscribe(null, null, null, inactiveThreshold), false);
        pullSubject = sub._pull(pro, fetchConsumeOptions.raiseStatusWarnings(), this);
        startNanos = -1;
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

    @Override
    public Message nextMessage() throws InterruptedException, JetStreamStatusCheckedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
