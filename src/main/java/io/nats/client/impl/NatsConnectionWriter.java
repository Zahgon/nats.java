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

import io.nats.client.Options;
import io.nats.client.StatisticsCollector;
import io.nats.client.support.ByteArrayBuilder;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.time.Duration;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import static io.nats.client.impl.MarkerMessage.END_RECONNECT;
import static io.nats.client.support.BuilderBase.bufferAllocSize;
import static io.nats.client.support.NatsConstants.CR;
import static io.nats.client.support.NatsConstants.LF;

class NatsConnectionWriter implements Runnable {

    enum Mode {

        Normal, Reconnect, WaitingForEndReconnect
    }

    private static final int BUFFER_BLOCK_SIZE = 256;

    private final NatsConnection connection;

    private final ReentrantLock writerLock;

    private Future<Boolean> stopped;

    private Future<DataPort> dataPortFuture;

    private DataPort dataPort;

    private final AtomicBoolean running;

    private final AtomicReference<Mode> mode;

    private final ReentrantLock startStopLock;

    private byte[] sendBuffer;

    private final AtomicInteger sendBufferLength;

    private final WriterMessageQueue normalOutgoing;

    private final WriterMessageQueue reconnectOutgoing;

    private final long reconnectBufferSize;

    NatsConnectionWriter(NatsConnection connection) {
        this.connection = connection;
        writerLock = new ReentrantLock();
        this.running = new AtomicBoolean(false);
        mode = new AtomicReference<>(Mode.Normal);
        this.startStopLock = new ReentrantLock();
        this.stopped = new CompletableFuture<>();
        // we are stopped on creation
        ((CompletableFuture<Boolean>) this.stopped).complete(Boolean.TRUE);
        Options options = connection.getOptions();
        int sbl = bufferAllocSize(options.getBufferSize(), BUFFER_BLOCK_SIZE);
        sendBufferLength = new AtomicInteger(sbl);
        sendBuffer = new byte[sbl];
        reconnectBufferSize = options.getReconnectBufferSize();
        normalOutgoing = new WriterMessageQueue(options.getMaxMessagesInOutgoingQueue(), options.isDiscardMessagesWhenOutgoingQueueFull(), options.getWriteQueuePushTimeout());
        // The "reconnect" buffer contains internal messages
        // Using this constructor makes it unbounded and without "discard when full"
        reconnectOutgoing = new WriterMessageQueue(options.getWriteQueuePushTimeout());
    }

    // Should only be called if the current thread has exited.
    // Use the Future from stop() to determine if it is ok to call this.
    // This method resets that future so mistiming can result in badness.
    void start(Future<DataPort> dataPortFuture) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // May be called several times on an error.
    // Returns a future that is completed when the thread completes, not when this
    // method does.
    Future<Boolean> stop() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean isRunning() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void sendMessageBatch(NatsMessage msg, DataPort dataPort, StatisticsCollector stats) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void enterReconnectMode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void enterWaitingForEndReconnectMode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean canQueueDuringReconnect(NatsMessage msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean queue(NatsMessage msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void queueInternalMessage(NatsMessage msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void flushBuffer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    long outgoingPendingMessageCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    long outgoingPendingBytes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
