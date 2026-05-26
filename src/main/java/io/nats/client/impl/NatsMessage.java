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

import io.nats.client.Connection;
import io.nats.client.Message;
import io.nats.client.Subscription;
import io.nats.client.support.ByteArrayBuilder;
import io.nats.client.support.Status;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.TimeoutException;
import static io.nats.client.support.NatsConstants.*;
import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

public class NatsMessage implements Message {

    protected static final String NOT_A_JET_STREAM_MESSAGE = "Message is not a JetStream message";

    protected String subject;

    protected String replyTo;

    protected byte[] data;

    protected Headers headers;

    // incoming specific : subject, replyTo, data and these fields
    protected String sid;

    protected int controlLineLength;

    // protocol specific : just this field
    ByteArrayBuilder protocolBab;

    // housekeeping
    protected int sizeInBytes;

    protected int headerLen;

    protected int dataLen;

    protected NatsSubscription subscription;

    // for accumulate
    protected NatsMessage next;

    protected boolean flushImmediatelyAfterPublish;

    // ack tracking
    protected AckType lastAck;

    // ----------------------------------------------------------------------------------------------------
    // Constructors - Prefer to use Builder
    // ----------------------------------------------------------------------------------------------------
    protected NatsMessage() {
        this((byte[]) null);
    }

    protected NatsMessage(byte[] data) {
        this.data = data == null ? EMPTY_BODY : data;
        dataLen = this.data.length;
    }

    // utf8-mode is ignored
    @Deprecated
    public NatsMessage(String subject, String replyTo, byte[] data, boolean utf8mode) {
        this(subject, replyTo, null, data);
    }

    // utf8-mode is ignored
    @Deprecated
    public NatsMessage(String subject, String replyTo, Headers headers, byte[] data, boolean utf8mode) {
        this(subject, replyTo, headers, data);
    }

    public NatsMessage(String subject, String replyTo, byte[] data) {
        this(subject, replyTo, null, data);
    }

    public NatsMessage(String subject, String replyTo, Headers headers, byte[] data) {
        this(data);
        this.subject = subject;
        this.replyTo = replyTo;
        this.headers = headers;
    }

    public NatsMessage(Message message) {
        this(message.getData());
        this.subject = message.getSubject();
        this.replyTo = message.getReplyTo();
        this.headers = message.getHeaders();
    }

    // ----------------------------------------------------------------------------------------------------
    // Client and Message Internal Methods
    // ----------------------------------------------------------------------------------------------------
    boolean isProtocol() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean isFilterOnStop() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static final Headers EMPTY_READ_ONLY = new Headers(null, true, null);

    protected void calculate() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    ByteArrayBuilder getProtocolBab() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    long getSizeInBytes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    byte[] getProtocolBytes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    int getPayloadSize() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    int getControlLineLength() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @param destPosition the position index in destination byte array to start
     * @param dest is the byte array to write to
     * @return the length of the header
     */
    int copyNotEmptyHeaders(int destPosition, byte[] dest) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void setSubscription(NatsSubscription sub) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    NatsSubscription getNatsSubscription() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // ----------------------------------------------------------------------------------------------------
    // Public Interface Methods
    // ----------------------------------------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public String getSID() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Connection getConnection() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSubject() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getReplyTo() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasHeaders() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Headers getHeaders() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isStatusMessage() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status getStatus() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getData() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUtf8mode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Subscription getSubscription() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public AckType lastAck() {
        throw new UnsupportedOperationException("STUB: not implemented");
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

    /**
     * {@inheritDoc}
     */
    @Override
    public long consumeByteCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    String toDetailString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private String headersToString() {
        return hasHeaders() ? new String(headers.getSerialized(), ISO_8859_1).replace("\r", "+").replace("\n", "+") : "";
    }

    private String dataToString() {
        if (data.length == 0) {
            return "<no data>";
        }
        String s = new String(data, UTF_8);
        int at = s.indexOf("io.nats.jetstream.api");
        if (at == -1) {
            return s.length() > 27 ? s.substring(0, 27) + "..." : s;
        }
        int at2 = s.indexOf('"', at);
        return s.substring(at, at2);
    }

    private String replyToString() {
        return replyTo == null ? "<no reply>" : replyTo;
    }

    private String protocolBytesToString() {
        return protocolBab == null ? null : protocolBab.toString();
    }

    private String nextToString() {
        return next == null ? "No" : "Yes";
    }

    // ----------------------------------------------------------------------------------------------------
    // Standard Builder
    // ----------------------------------------------------------------------------------------------------
    public static Builder builder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The builder is for building normal publish/request messages,
     * as an option for client use developers instead of the normal constructor
     */
    public static class Builder {

        private String subject;

        private String replyTo;

        private Headers headers;

        private byte[] data;

        /**
         * Set the subject
         *
         * @param subject the subject
         * @return the builder
         */
        public Builder subject(final String subject) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the reply to
         *
         * @param replyTo the reply to
         * @return the builder
         */
        public Builder replyTo(final String replyTo) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the headers
         *
         * @param headers the headers
         * @return the builder
         */
        public Builder headers(final Headers headers) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the data from a string converting using the
         * charset StandardCharsets.UTF_8
         *
         * @param data    the data string
         * @return the builder
         */
        public Builder data(final String data) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the data from a string
         *
         * @param data    the data string
         * @param charset the charset, for example {@code StandardCharsets.UTF_8}
         * @return the builder
         */
        public Builder data(final String data, final Charset charset) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the data from a byte array. null data changed to empty byte array
         *
         * @param data the data
         * @return the builder
         */
        public Builder data(final byte[] data) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set if the subject should be treated as utf
         * @deprecated Code is just always treating as utf8
         * @param utf8mode true if utf8 mode for subject
         * @return the builder
         */
        @Deprecated
        public Builder utf8mode(final boolean utf8mode) {
            return this;
        }

        /**
         * Build the {@code NatsMessage} object
         *
         * @return the {@code NatsMessage}
         */
        public NatsMessage build() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
