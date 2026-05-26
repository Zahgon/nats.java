// Copyright 2015-2025 The NATS Authors
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

import java.time.Duration;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import static io.nats.client.impl.MarkerMessage.POISON_PILL;

abstract class MessageQueueBase {

    protected static final int PAUSED = 0;

    protected static final int RUNNING = 1;

    protected static final int DRAINING = 2;

    protected final int queueCapacity;

    protected final LinkedBlockingQueue<NatsMessage> queue;

    protected final AtomicLong length;

    protected final AtomicLong sizeInBytes;

    protected final AtomicInteger running;

    MessageQueueBase() {
        this(Integer.MAX_VALUE);
    }

    MessageQueueBase(int queueCapacity) {
        this.queueCapacity = queueCapacity > 0 ? queueCapacity : Integer.MAX_VALUE;
        queue = new LinkedBlockingQueue<>(this.queueCapacity);
        length = new AtomicLong(0);
        sizeInBytes = new AtomicLong(0);
        running = new AtomicInteger(RUNNING);
    }

    boolean isRunning() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean isPaused() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean isDraining() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean isDrained() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void pause() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void drain() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void resume() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    long queueSize() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    long length() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    long sizeInBytes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // this is just a helper method to poll a message from
    // the queue handling various forms of timeouts
    // if the polled message was a POISON_PILL, return null
    NatsMessage _poll(Duration timeout) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
