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

import io.nats.client.support.WebsocketFrameHeader.OpCode;
import java.io.IOException;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Random;

public class WebsocketOutputStream extends OutputStream {

    private OutputStream wrap;

    private boolean masked;

    private byte[] oneByte = new byte[1];

    /**
     * Minimize fragmenting, by using a 1440 buffer size. This buffer is used to
     * store the websocket header + payload for the first write.
     *
     * We are using this size since a typical MTU for ethernet is 1500 and the
     * TCP/IPv6 header consumes 60 bytes, thus by using 1440 we should minimize
     * the need for further fragmentation at the lower layers and yet minimize
     * the overhead associated with the fact that a write to the Socket (which
     * has NO_DELAY set on it) results in a single TCP/IPv6 datagram which has
     * a 60 byte overhead.
     *
     * Note that the effective payload is less due to the websocket frame overhead:
     * 4 bytes header/length + 4 bytes masking. Which would be a payload
     * size of 1432. This means any write which is larger than 1432 will be
     * fragmented into two socket writes and will thus have another 60 bytes
     * of TCP/IPv6 datagram header overhead.
     */
    private byte[] headerBuffer = new byte[1440];

    private WebsocketFrameHeader header = new WebsocketFrameHeader().withOp(OpCode.BINARY, true).withNoMask();

    private Random random = new SecureRandom();

    public WebsocketOutputStream(OutputStream wrap, boolean masked) {
        this.wrap = wrap;
        this.masked = masked;
    }

    @Override
    public void close() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void flush() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void write(byte[] buffer) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void write(int b) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * NOTE: the buffer will be modified if masking is enabled and the length is greater
     * than 1432. Regardless of if masking is enabled or not, any writes of length &gt; 1432
     * will be split into two writes to the underlying OutputStream which is being wrapped.
     */
    @Override
    public void write(byte[] buffer, int offset, int length) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
