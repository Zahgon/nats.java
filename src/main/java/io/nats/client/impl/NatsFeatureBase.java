// Copyright 2022 The NATS Authors
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
import io.nats.client.api.DeliverPolicy;
import io.nats.client.api.MessageInfo;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import static io.nats.client.support.NatsJetStreamConstants.JS_NO_MESSAGE_FOUND_ERR;

public class NatsFeatureBase {

    protected final NatsJetStream js;

    protected final NatsJetStreamManagement jsm;

    protected String streamName;

    NatsFeatureBase(NatsConnection connection, FeatureOptions fo, NatsJetStreamManagement jsm) throws IOException {
        if (jsm != null) {
            this.jsm = jsm;
            js = (NatsJetStream) jsm.jetStream();
        } else if (fo == null) {
            js = new NatsJetStream(connection, null);
            this.jsm = new NatsJetStreamManagement(connection, null);
        } else {
            js = new NatsJetStream(connection, fo.getJetStreamOptions());
            this.jsm = new NatsJetStreamManagement(connection, fo.getJetStreamOptions());
        }
    }

    String getStreamName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected MessageInfo _getLast(String subject) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected MessageInfo _getBySeq(long seq) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void visitSubject(String subject, DeliverPolicy deliverPolicy, boolean headersOnly, boolean ordered, MessageHandler handler) throws IOException, JetStreamApiException, InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void visitSubject(List<String> subjects, DeliverPolicy deliverPolicy, boolean headersOnly, boolean ordered, MessageHandler handler) throws IOException, JetStreamApiException, InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
