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

import io.nats.client.Message;
import io.nats.client.PullRequestOptions;
import io.nats.client.SubscribeOptions;
import io.nats.client.support.Status;
import static io.nats.client.impl.MessageManager.ManageResult.*;
import static io.nats.client.support.NatsJetStreamConstants.*;
import static io.nats.client.support.Status.*;

class PullMessageManager extends MessageManager {

    protected boolean raiseStatusWarnings;

    protected PullManagerObserver pullManagerObserver;

    protected String currentPinId;

    protected PullMessageManager(NatsConnection conn, SubscribeOptions so, boolean syncMode) {
        super(conn, so, syncMode);
    }

    @Override
    protected void startup(NatsJetStreamSubscription sub) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected void startPullRequest(String pullSubject, PullRequestOptions pro, boolean raiseStatusWarnings, PullManagerObserver pullManagerObserver) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected void handleHeartbeatError() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected Boolean beforeQueueProcessorImpl(NatsMessage msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected ManageResult manage(Message msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void checkForPin(Message msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected ManageResult manageStatus(Message msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
