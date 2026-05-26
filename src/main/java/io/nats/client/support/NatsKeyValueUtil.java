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
package io.nats.client.support;

import io.nats.client.Message;
import io.nats.client.MessageTtl;
import io.nats.client.PublishOptions;
import io.nats.client.api.KeyValueOperation;
import io.nats.client.impl.Headers;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import static io.nats.client.support.NatsConstants.DOT;
import static io.nats.client.support.NatsJetStreamConstants.*;

public abstract class NatsKeyValueUtil {

    private NatsKeyValueUtil() {
    }

    /* ensures cannot be constructed */
    public static final String KV_STREAM_PREFIX = "KV_";

    public static final int KV_STREAM_PREFIX_LEN = KV_STREAM_PREFIX.length();

    public static final String KV_SUBJECT_PREFIX = "$KV.";

    public static final String KV_SUBJECT_SUFFIX = ".>";

    public static final String KV_OPERATION_HEADER_KEY = NatsJetStreamConstants.KV_OPERATION_HEADER_KEY;

    @NonNull
    public static String extractBucketName(String streamName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @NonNull
    public static String toStreamName(String bucketName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @NonNull
    public static String toStreamSubject(String bucketName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @NonNull
    public static String toKeyPrefix(String bucketName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean hasPrefix(String bucketName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @NonNull
    public static String trimPrefix(String bucketName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Nullable
    public static String getOperationHeader(Headers h) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Nullable
    public static String getNatsMarkerReasonHeader(Headers h) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @NonNull
    public static KeyValueOperation getOperation(Headers h) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @NonNull
    public static Headers getDeleteHeaders() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @NonNull
    public static Headers getPurgeHeaders() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Nullable
    public static PublishOptions getPublishOptions(long expectedRevision, MessageTtl messageTtl) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static class BucketAndKey {

        public final String bucket;

        public final String key;

        public BucketAndKey(Message m) {
            this(m.getSubject());
        }

        public BucketAndKey(String subject) {
            String[] split = subject.split("\\Q.\\E", 3);
            bucket = split[1];
            key = split[2];
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
}
