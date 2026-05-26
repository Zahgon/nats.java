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

import io.nats.client.Message;
import io.nats.client.ReadListener;
import io.nats.client.support.IncomingHeadersProcessor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import static io.nats.client.support.NatsConstants.*;

class NatsConnectionReader implements Runnable {

    enum Mode {

        GATHER_OP,
        GATHER_PROTO,
        GATHER_MSG_HMSG_PROTO,
        PARSE_PROTO,
        GATHER_HEADERS,
        GATHER_DATA
    }

    private final NatsConnection connection;

    // use a byte buffer to assist character decoding
    private ByteBuffer protocolBuffer;

    private boolean gotCR;

    private String op;

    private final char[] opArray;

    private int opPos;

    private final char[] msgLineChars;

    private int msgLinePosition;

    private Mode mode;

    private IncomingMessageFactory incoming;

    private byte[] msgHeaders;

    private byte[] msgData;

    private int msgHeadersPosition;

    private int msgDataPosition;

    private final byte[] buffer;

    private int bufferPosition;

    private Future<Boolean> stopped;

    private Future<DataPort> dataPortFuture;

    private DataPort dataPort;

    private final AtomicBoolean running;

    private final boolean utf8Mode;

    private final ReadListener readListener;

    NatsConnectionReader(NatsConnection connection) {
        this.connection = connection;
        this.running = new AtomicBoolean(false);
        this.stopped = new CompletableFuture<>();
        // we are stopped on creation
        ((CompletableFuture<Boolean>) this.stopped).complete(Boolean.TRUE);
        this.protocolBuffer = ByteBuffer.allocate(this.connection.getOptions().getMaxControlLine());
        this.msgLineChars = new char[this.connection.getOptions().getMaxControlLine()];
        this.opArray = new char[MAX_PROTOCOL_RECEIVE_OP_LENGTH];
        this.buffer = new byte[connection.getOptions().getBufferSize()];
        this.bufferPosition = 0;
        this.utf8Mode = connection.getOptions().supportUTF8Subjects();
        final ReadListener rl = connection.getOptions().getReadListener();
        if (rl == null) {
            readListener = new ReadListener() {
            };
        } else {
            readListener = new ReadListener() {

                @Override
                public void protocol(String op, String text) {
                    throw new UnsupportedOperationException("STUB: not implemented");
                }

                @Override
                public void message(String op, Message message) {
                    throw new UnsupportedOperationException("STUB: not implemented");
                }
            };
        }
    }

    // Should only be called if the current thread has exited.
    // Use the Future from stop() to determine if it is ok to call this.
    // This method resets that future so mistiming can result in badness.
    void start(Future<DataPort> dataPortFuture) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    Future<Boolean> stop() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // May be called several times on an error.
    // Returns a future that is completed when the thread completes, not when this
    // method does.
    Future<Boolean> stop(boolean shutdownDataPort) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean isRunning() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Gather the op, either up to the first space or the first carriage return.
    void gatherOp(int maxPos) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Stores the message protocol line in a char buffer that will be read for subject, reply
    void gatherMessageProtocol(int maxPos) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Gather bytes for a protocol line
    void gatherProtocol(int maxPos) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void gatherHeaders(int maxPos) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Gather bytes for a message body into a byte array that is then
    // given to the message object
    void gatherMessageData(int maxPos) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String grabNextMessageLineElement(int max) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static String opFor(char[] chars, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static final int[] TENS = new int[] { 1, 10, 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000, 100_000_000, 1_000_000_000 };

    public static int parseLength(String s) throws NumberFormatException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void parseProtocolMessage() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    //For testing
    void fakeReadForTest(byte[] bytes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    String currentOp() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
