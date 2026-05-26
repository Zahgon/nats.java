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
import io.nats.client.Options.HostnameResolveMode;
import io.nats.client.support.HappyEyeballsConnector;
import io.nats.client.support.NatsUri;
import io.nats.client.support.WebSocket;
import org.jspecify.annotations.NonNull;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import static io.nats.client.support.NatsConstants.SECURE_WEBSOCKET_PROTOCOL;

/**
 * This class is not thread-safe.  Caller must ensure thread safety.
 */
// NatsConnection
@SuppressWarnings("ClassEscapesDefinedScope")
public class SocketDataPort implements DataPort {

    protected NatsConnection connection;

    protected String host;

    protected int port;

    protected Socket socket;

    protected boolean isSecure = false;

    protected InputStream in;

    protected OutputStream out;

    @Deprecated
    @Override
    public void connect(@NonNull String serverURI, @NonNull NatsConnection conn, long timeoutNanos) throws IOException {
        try {
            connect(conn, new NatsUri(serverURI), timeoutNanos);
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void connect(@NonNull NatsConnection conn, @NonNull NatsUri nuri, long timeoutNanos) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Upgrade the port to SSL. If it is already secured, this is a no-op.
     * If the data port type doesn't support SSL it should throw an exception.
     */
    public void upgradeToSecure() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int read(byte[] dst, int off, int len) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void write(byte[] src, int toWrite) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void shutdownInput() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void close() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void forceClose() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void flush() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private Socket createSocket(Options options) throws SocketException {
        Socket socket;
        if (options.getProxy() != null) {
            socket = new Socket(options.getProxy());
        } else {
            socket = new Socket();
        }
        socket.setTcpNoDelay(true);
        socket.setReceiveBufferSize(2 * 1024 * 1024);
        socket.setSendBufferSize(2 * 1024 * 1024);
        return socket;
    }
}
