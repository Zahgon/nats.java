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
import io.nats.client.Message;
import io.nats.client.PullRequestOptions;
import io.nats.client.api.ConsumerInfo;
import java.io.IOException;
import static io.nats.client.impl.NatsJetStreamSubscription.EXPIRE_ADJUSTMENT;

class NatsNextConsumer extends NatsMessageConsumerBase {

    final long maxWaitMillis;

    NatsNextConsumer(SimplifiedSubscriptionMaker subscriptionMaker, ConsumerInfo cachedConsumerInfo, long maxWaitMillis) throws IOException, JetStreamApiException {
        super(cachedConsumerInfo);
        this.maxWaitMillis = maxWaitMillis;
        // 10% longer than the wait
        long inactiveThreshold = maxWaitMillis * 110 / 100;
        initSub(subscriptionMaker.subscribe(null, null, null, inactiveThreshold), false);
        // the call to subscribe sets this
        setConsumerName(consumerName);
        sub._pull(PullRequestOptions.builder(1).expiresIn(maxWaitMillis - EXPIRE_ADJUSTMENT).build(), false, null);
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

    Message getMessage() throws InterruptedException, IllegalStateException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
