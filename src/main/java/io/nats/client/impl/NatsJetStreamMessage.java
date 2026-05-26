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

import io.nats.client.Connection;
import java.time.Duration;
import java.util.concurrent.TimeoutException;
import static io.nats.client.impl.AckType.*;
import static io.nats.client.support.NatsConstants.NANOS_PER_MILLI;
import static io.nats.client.support.Validator.validateDurationRequired;

class NatsJetStreamMessage extends IncomingMessage {

    private NatsJetStreamMetaData jsMetaData = null;

    NatsJetStreamMessage(byte[] data) {
        super(data);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void ack() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void ackSync(Duration d) throws InterruptedException, TimeoutException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nak() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nakWithDelay(Duration nakDelay) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nakWithDelay(long nakDelayMillis) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void inProgress() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void term() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NatsJetStreamMetaData metaData() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isJetStream() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void ackReply(AckType ackType, long delayNanos) {
        if (ackHasntBeenTermed()) {
            Connection nc = getJetStreamValidatedConnection();
            nc.publish(replyTo, ackType.bodyBytes(delayNanos));
            lastAck = ackType;
        }
    }

    private boolean ackHasntBeenTermed() {
        return lastAck == null || !lastAck.terminal;
    }

    Connection getJetStreamValidatedConnection() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
