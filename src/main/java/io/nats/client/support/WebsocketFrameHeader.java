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
package io.nats.client.support;

public class WebsocketFrameHeader {

    public static int MAX_FRAME_HEADER_SIZE = 14;

    public enum OpCode {

        CONTINUATION(0),
        TEXT(1),
        BINARY(2),
        CLOSE(8),
        PING(9),
        PONG(10),
        UNKNOWN(0x10);

        private int code;

        OpCode(int code) {
            this.code = code;
        }

        public int getCode() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public static OpCode of(int code) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    // Fields of header:
    private byte byte0;

    private boolean mask;

    private long payloadLength;

    private int maskingKey;

    private int maskingKeyOffset = 0;

    public WebsocketFrameHeader withOp(OpCode op, boolean isFinal) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public WebsocketFrameHeader withNoMask() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public WebsocketFrameHeader withMask(int maskingKey) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public WebsocketFrameHeader withPayloadLength(long payloadLength) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isFinal() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isMasked() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getMaskingKey() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public long getPayloadLength() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public OpCode getOpCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isPayloadEmpty() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Decrement the payloadLength by at most maxSize such that payloadLength is non-negative,
     * returning the amount decremented.
     *
     * @param buffer is the buffer to filter.
     * @param offset is the start offset within buffer to filter.
     * @param length is the number of bytes to filter.
     *
     * @return min(payloadLength, maxSize), decrementing the internal payloadLength by this amount.
     */
    public int filterPayload(byte[] buffer, int offset, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Introspects the first 2 bytes of buffer at the specified offset to
     * determine how large the entire header is.
     *
     * @param buffer is the buffer to introspect
     * @param offset is the offset within the buffer where the websocket
     *     header begins.
     * @return the number of bytes used by the full websocket header.
     */
    public static int size(byte[] buffer, int offset) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Serializes this WebsocketFrameHeader into a buffer.
     *
     * @param buffer where the serialized header will be placed.
     *
     * @param offset is the start offset into the buffer.
     *
     * @param length is the max bytes that can be placed in the buffer.
     *
     * @return 0 if there is insufficient remainder in the buffer, otherwise
     *    returns the number of bytes placed into the buffer.
     */
    public int read(byte[] buffer, int offset, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Overwrite internal frame header content with input buffer.
     *
     * <pre>
     * WebsocketFrameHeader header = new WebsocketFrameHeader();
     * int consumedLength = header.write(buffer, offset, length);
     * offset += consumedLength;
     * length -= consumedLength;
     * </pre>
     *
     * @param buffer containing the serialized header.
     *
     * @param offset is the start offset into the buffer.
     *
     * @param length is the max bytes to consume.
     *
     * @return 0 if there is insufficient remainder in the buffer, otherwise
     *     returns the number of bytes consumed from the buffer.
     */
    public int write(byte[] buffer, int offset, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
