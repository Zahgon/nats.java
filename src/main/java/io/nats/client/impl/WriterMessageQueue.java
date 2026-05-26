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

import io.nats.client.NatsSystemClock;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import static io.nats.client.Options.MINIMUM_WRITE_QUEUE_PUSH_TIMEOUT;
import static io.nats.client.impl.MarkerMessage.POISON_PILL;
import static io.nats.client.support.NatsConstants.OUTPUT_QUEUE_BUSY;
import static io.nats.client.support.NatsConstants.OUTPUT_QUEUE_IS_FULL;

class WriterMessageQueue extends MessageQueueBase {

    protected static final long MIN_PUSH_TIMEOUT_NANOS = MINIMUM_WRITE_QUEUE_PUSH_TIMEOUT.toNanos();

    protected final int maxMessagesInOutgoingQueue;

    protected final boolean discardWhenFull;

    protected final Lock editLock;

    protected final long pushTimeoutNanos;

    WriterMessageQueue(Duration pushTimeout) {
        this(-1, false, pushTimeout);
    }

    WriterMessageQueue(int maxMessagesInOutgoingQueue, boolean discardWhenFull, Duration pushTimeout) {
        super(maxMessagesInOutgoingQueue);
        this.maxMessagesInOutgoingQueue = queueCapacity;
        this.discardWhenFull = discardWhenFull;
        this.pushTimeoutNanos = Math.max(MIN_PUSH_TIMEOUT_NANOS, pushTimeout.toNanos());
        this.editLock = new ReentrantLock();
    }

    boolean push(NatsMessage msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean push(NatsMessage msg, boolean internal) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Marking the queue, like POISON, is a message we don't want to count.
     * Intended to only be used with an unbounded queue. Use at your own risk.
     * @param msg the mark
     */
    @SuppressWarnings("SameParameterValue")
    void queueMarkerMessage(MarkerMessage msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Waits up to the timeout to try to accumulate multiple messages
    // Use the NatsMessage.next field to read the entire set accumulated.
    // maxBytesToAccumulate and maxMessagesToAccumulate are both checked
    // and if either is exceeded the method returns.
    //
    // A timeout of 0 will wait forever (or until the queue is stopped/drained)
    //
    // Only works in writer mode, because we want to maintain order.
    // accumulate reads off the concurrent queue one at a time, so if multiple
    // readers are present, you could get out of order message delivery.
    NatsMessage accumulate(long maxBytesToAccumulate, long maxMessagesToAccumulate, Duration timeout) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void filter() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
