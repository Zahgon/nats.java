// Copyright 2021 The NATS Authors
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
package io.nats.client.api;

import io.nats.client.Message;
import io.nats.client.impl.Headers;
import io.nats.client.support.NatsKeyValueUtil;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import java.time.ZonedDateTime;
import static io.nats.client.support.NatsJetStreamConstants.MSG_SIZE_HDR;
import static io.nats.client.support.NatsKeyValueUtil.BucketAndKey;
import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * The KeyValueEntry represents a record in the Key Value history
 */
public class KeyValueEntry {

    private final BucketAndKey bucketAndKey;

    private final byte[] value;

    private final long dataLen;

    private final ZonedDateTime created;

    private final long revision;

    private final long delta;

    private final KeyValueOperation op;

    /**
     * Construct KeyValueEntry from message info
     * @param mi the message info
     */
    public KeyValueEntry(MessageInfo mi) {
        Headers h = mi.getHeaders();
        if (mi.getSubject() == null) {
            throw new IllegalStateException("Invalid Message Info");
        }
        bucketAndKey = new BucketAndKey(mi.getSubject());
        value = extractValue(mi.getData());
        dataLen = calculateLength(value, h);
        created = mi.getTime();
        revision = mi.getSeq();
        delta = 0;
        op = NatsKeyValueUtil.getOperation(h);
    }

    /**
     * Construct KeyValueEntry from a message
     * @param m the message
     */
    public KeyValueEntry(Message m) {
        Headers h = m.getHeaders();
        bucketAndKey = new BucketAndKey(m.getSubject());
        value = extractValue(m.getData());
        dataLen = calculateLength(value, h);
        created = m.metaData().timestamp();
        revision = m.metaData().streamSequence();
        delta = m.metaData().pendingCount();
        op = NatsKeyValueUtil.getOperation(h);
    }

    /**
     * Get the key value bucket this key in.
     * @return the bucket
     */
    @NonNull
    public String getBucket() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the key
     * @return the key
     */
    @NonNull
    public String getKey() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the value. May be null
     * @return the value
     */
    public byte @Nullable [] getValue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the value as a string using UTF-8 encoding
     * @return the value as a string or null if there is no value
     */
    @Nullable
    public String getValueAsString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the value as a long
     * @return the value or null if there is no value
     * @throws NumberFormatException  if the string does not contain a parsable {@code long}.
     */
    @Nullable
    public Long getValueAsLong() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the number of bytes in the data. May be zero
     * @return the number of bytes
     */
    public long getDataLen() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the creation time of the current version of the key
     * @return the creation time
     */
    @NonNull
    public ZonedDateTime getCreated() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the revision number of the string. Not a version, but an internally strictly monotonical value
     * @return the revision
     */
    public long getRevision() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Internal reference to pending message from the entry request
     * @return the delta
     */
    public long getDelta() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The KeyValueOperation of this entry
     * @return the operation
     */
    @NonNull
    public KeyValueOperation getOperation() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static byte[] extractValue(byte[] data) {
        return data == null || data.length == 0 ? null : data;
    }

    private static long calculateLength(byte[] value, Headers h) {
        if (value == null) {
            String hlen = h == null ? null : h.getFirst(MSG_SIZE_HDR);
            return hlen == null ? 0 : Long.parseLong(hlen);
        }
        return value.length;
    }

    @Override
    public boolean equals(Object o) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
