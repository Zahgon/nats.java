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
import io.nats.client.SubscribeOptions;
import io.nats.client.api.ConsumerConfiguration;
import io.nats.client.support.Status;
import static io.nats.client.ErrorListener.FlowControlSource;
import static io.nats.client.ErrorListener.FlowControlSource.FLOW_CONTROL;
import static io.nats.client.ErrorListener.FlowControlSource.HEARTBEAT;
import static io.nats.client.impl.MessageManager.ManageResult.*;
import static io.nats.client.support.NatsJetStreamConstants.CONSUMER_STALLED_HDR;

class PushMessageManager extends MessageManager {

    protected final NatsJetStream js;

    protected final String stream;

    protected final ConsumerConfiguration initialCc;

    protected final boolean queueMode;

    protected final boolean fc;

    protected String lastFcSubject;

    protected PushMessageManager(NatsConnection conn, NatsJetStream js, String stream, SubscribeOptions so, ConsumerConfiguration initialCc, boolean queueMode, boolean syncMode) {
        super(conn, so, syncMode);
        this.js = js;
        this.stream = stream;
        this.initialCc = initialCc;
        this.queueMode = queueMode;
        if (queueMode) {
            fc = false;
        } else {
            configureIdleHeartbeat(initialCc.getIdleHeartbeat(), so.getMessageAlarmTime());
            // can't have fc w/o heartbeat
            fc = hb.get() && initialCc.isFlowControl();
        }
    }

    protected boolean isQueueMode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected boolean isFc() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected String getLastFcSubject() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected void startup(NatsJetStreamSubscription sub) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected Boolean beforeQueueProcessorImpl(NatsMessage msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected boolean hasFcSubject(Message msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected String extractFcSubject(Message msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected ManageResult manage(Message msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected ManageResult manageStatus(Message msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void processFlowControl(String fcSubject, FlowControlSource source) {
        // we may get multiple fc/hb messages with the same reply
        // only need to post to that subject once
        if (fcSubject != null && !fcSubject.equals(lastFcSubject)) {
            conn.publishInternal(fcSubject, null, null, null, false);
            // set after publish in case the pub fails
            lastFcSubject = fcSubject;
            conn.notifyErrorListener((c, el) -> el.flowControlProcessed(c, sub, fcSubject, source));
        }
    }
}
