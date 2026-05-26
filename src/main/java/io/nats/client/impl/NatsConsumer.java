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

import io.nats.client.Consumer;
import io.nats.client.NatsSystemClock;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

abstract class NatsConsumer implements Consumer {

    NatsConnection connection;

    private final AtomicLong maxMessages;

    private final AtomicLong maxBytes;

    private final AtomicLong droppedMessages;

    private final AtomicLong messagesDelivered;

    private final AtomicBoolean slow;

    private final AtomicReference<CompletableFuture<Boolean>> drainingFuture;

    NatsConsumer(NatsConnection conn) {
        this.connection = conn;
        this.maxMessages = new AtomicLong(Consumer.DEFAULT_MAX_MESSAGES);
        this.maxBytes = new AtomicLong(Consumer.DEFAULT_MAX_BYTES);
        this.droppedMessages = new AtomicLong();
        this.messagesDelivered = new AtomicLong(0);
        this.slow = new AtomicBoolean(false);
        this.drainingFuture = new AtomicReference<>();
    }

    /**
     * Set limits on the maximum number of messages, or maximum size of messages
     * this consumer will hold before it starts to drop new messages waiting.
     * <p>
     * Messages are dropped as they encounter a full queue, which is to say, new
     * messages are dropped rather than old messages. If a queue is 10 deep and
     * fills up, the 11th message is dropped.
     * <p>
     * Any value less than or equal to zero means unlimited and will be stored as 0.
     * @param maxMessages the maximum message count to hold, defaults to
     *                    {@value #DEFAULT_MAX_MESSAGES}.
     * @param maxBytes    the maximum bytes to hold, defaults to
     *                    {@value #DEFAULT_MAX_BYTES}.
     */
    public void setPendingLimits(long maxMessages, long maxBytes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @return the pending message limit set by {@link #setPendingLimits(long, long)
     *         setPendingLimits}.
     */
    public long getPendingMessageLimit() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @return the pending byte limit set by {@link #setPendingLimits(long, long)
     *         setPendingLimits}.
     */
    public long getPendingByteLimit() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @return the number of messages waiting to be delivered/popped,
     *         {@link #setPendingLimits(long, long) setPendingLimits}.
     */
    public long getPendingMessageCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @return the cumulative size of the messages waiting to be delivered/popped,
     *         {@link #setPendingLimits(long, long) setPendingLimits}.
     */
    public long getPendingByteCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @return the total number of messages delivered to this consumer, for all
     *         time.
     */
    public long getDeliveredCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void incrementDeliveredCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void incrementDroppedCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @return the number of messages dropped from this consumer, since the last
     *         call to {@link #clearDroppedCount}.
     */
    public long getDroppedCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Reset the drop count to 0.
     */
    public void clearDroppedCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void markSlow() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void markNotSlow() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean isMarkedSlow() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean hasReachedPendingLimits() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void markDraining(CompletableFuture<Boolean> future) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void markUnsubedForDrain() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    CompletableFuture<Boolean> getDrainingFuture() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean isDraining() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean isDrained() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Drain tells the consumer to process in flight, or cached messages, but stop receiving new ones. The library will
     * flush the unsubscribe call(s) insuring that any publish calls made by this client are included. When all messages
     * are processed the consumer effectively becomes unsubscribed.
     *
     * @param timeout The time to wait for the drain to succeed, pass 0 to wait
     *                    forever. Drain involves moving messages to and from the server
     *                    so a very short timeout is not recommended.
     * @return A future that can be used to check if the drain has completed
     * @throws InterruptedException if the thread is interrupted
     */
    public CompletableFuture<Boolean> drain(Duration timeout) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @return whether this consumer is still processing messages. For a
     *         subscription the answer is false after unsubscribe. For a dispatcher,
     *         false after stop.
     */
    public abstract boolean isActive();

    abstract ConsumerMessageQueue getMessageQueue();

    /**
     * Called during drain to tell the consumer to send appropriate unsub requests
     * to the connection.
     *
     * A subscription will unsub itself, while a dispatcher will unsub all of its
     * subscriptions.
     */
    abstract void sendUnsubForDrain();

    /**
     * Abstract method, called by the connection when the drain is complete.
     */
    abstract void cleanUpAfterDrain();
}
