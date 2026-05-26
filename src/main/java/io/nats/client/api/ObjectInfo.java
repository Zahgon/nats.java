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
package io.nats.client.api;

import io.nats.client.Message;
import io.nats.client.impl.Headers;
import io.nats.client.support.*;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import java.time.ZonedDateTime;
import java.util.Map;
import static io.nats.client.support.ApiConstants.*;
import static io.nats.client.support.JsonUtils.beginJson;
import static io.nats.client.support.JsonUtils.endJson;

/**
 * The ObjectInfo is Object Meta Information plus instance information
 */
public class ObjectInfo implements JsonSerializable {

    private final String bucket;

    private final String nuid;

    private final long size;

    private final long chunks;

    private final String digest;

    private final boolean deleted;

    private final ObjectMeta objectMeta;

    private final ZonedDateTime modified;

    private ObjectInfo(Builder b) {
        bucket = b.bucket;
        nuid = b.nuid;
        size = b.size;
        modified = b.modified;
        chunks = b.chunks;
        digest = b.digest;
        deleted = b.deleted;
        objectMeta = b.metaBuilder.build();
    }

    /**
     * Construct ObjectInfo from message info
     * @param mi the message info
     */
    public ObjectInfo(MessageInfo mi) {
        this(mi.getData(), mi.getTime());
    }

    /**
     * Construct ObjectInfo from a message
     * @param m the message
     */
    public ObjectInfo(Message m) {
        this(m.getData(), m.metaData().timestamp());
    }

    ObjectInfo(byte[] jsonBytes, ZonedDateTime messageTime) {
        JsonValue jv = JsonParser.parseUnchecked(jsonBytes);
        objectMeta = new ObjectMeta(jv);
        bucket = JsonValueUtils.readString(jv, BUCKET);
        nuid = JsonValueUtils.readString(jv, NUID);
        size = JsonValueUtils.readLong(jv, SIZE, 0);
        modified = DateTimeUtils.toGmt(messageTime);
        chunks = JsonValueUtils.readLong(jv, CHUNKS, 0);
        digest = JsonValueUtils.readString(jv, DIGEST);
        deleted = JsonValueUtils.readBoolean(jv, DELETED);
    }

    @Override
    @NonNull
    public String toJson() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * the bucket name
     * @return the name
     */
    @NonNull
    public String getBucket() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * the bucket nuid
     * @return the nuid
     */
    @Nullable
    public String getNuid() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The size of the object
     * @return the size in bytes
     */
    public long getSize() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * When the object was last modified
     * @return the last modified date
     */
    @Nullable
    public ZonedDateTime getModified() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The total number of chunks in the object
     * @return the number of chunks
     */
    public long getChunks() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The digest string for the object
     * @return the digest
     */
    @Nullable
    public String getDigest() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Whether the object is deleted
     * @return the deleted state
     */
    public boolean isDeleted() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The full object meta object
     * @return the ObjectMeta
     */
    @NonNull
    public ObjectMeta getObjectMeta() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The object name
     * @return the object name
     */
    @NonNull
    public String getObjectName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The object meta description
     * @return the description text or null
     */
    @Nullable
    public String getDescription() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The object meta Headers. May be empty but will not be null. In all cases it will be unmodifiable
     * @return the headers object
     */
    @NonNull
    public Headers getHeaders() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The object meta metadata. May be empty but will not be null. In all cases it will be unmodifiable
     * @return the map
     */
    @NonNull
    public Map<String, String> getMetaData() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Whether the object is actually a link
     * @return true if the object is a link instead of a direct object
     */
    public boolean isLink() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * If this is a link to an object, get the ObjectLink instance, otherwise this will be null
     * @return the ObjectLink or null
     */
    @Nullable
    public ObjectLink getLink() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get an instance of the builder initialized with a bucket and object meta
     * @param bucket the bucket name
     * @param objectName the object name
     * @return the builder
     */
    public static Builder builder(String bucket, String objectName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get an instance of the builder initialized with a bucket and object meta
     * @param bucket the bucket
     * @param meta the object meta
     * @return the builder
     */
    public static Builder builder(String bucket, ObjectMeta meta) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get an instance of the builder initialized from existing ObjectInfo
     * @param info the info
     * @return the builder
     */
    public static Builder builder(ObjectInfo info) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * A builder for ObjectInfo
     */
    public static class Builder {

        String bucket;

        String nuid;

        long size;

        ZonedDateTime modified;

        long chunks;

        String digest;

        boolean deleted;

        ObjectMeta.Builder metaBuilder;

        /**
         * Construct the builder initialized with a bucket and object name
         * @param bucket the bucket name
         * @param objectName the object name
         */
        public Builder(String bucket, String objectName) {
            metaBuilder = ObjectMeta.builder(objectName);
            bucket(bucket);
        }

        /**
         * Construct the builder initialized with a bucket and object meta
         * @param bucket the bucket name
         * @param meta the object meta
         */
        public Builder(String bucket, ObjectMeta meta) {
            metaBuilder = ObjectMeta.builder(meta);
            bucket(bucket);
        }

        /**
         * Construct the builder initialized from existing ObjectInfo
         * @param info the info
         */
        public Builder(ObjectInfo info) {
            bucket = info.bucket;
            nuid = info.nuid;
            size = info.size;
            modified = info.modified;
            chunks = info.chunks;
            digest = info.digest;
            deleted = info.deleted;
            metaBuilder = ObjectMeta.builder(info.objectMeta);
        }

        /**
         * set the object name
         * @param name the name
         * @return the builder
         */
        public Builder objectName(String name) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * set the object's bucket name
         * @param bucket the bucket name
         * @return the builder
         */
        public Builder bucket(String bucket) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * set the object's nuid
         * @param nuid the nuid
         * @return the builder
         */
        public Builder nuid(String nuid) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * set the object's size
         * @param size the size
         * @return the builder
         */
        public Builder size(long size) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * set the object's modified time
         * @param modified the time
         * @return the builder
         */
        public Builder modified(ZonedDateTime modified) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * set the object's number of chunks
         * @param chunks the number of chunks
         * @return the builder
         */
        public Builder chunks(long chunks) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * set the object's digest
         * @param digest the digest
         * @return the builder
         */
        public Builder digest(String digest) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * set the object's deleted state
         * @param deleted the state
         * @return the builder
         */
        public Builder deleted(boolean deleted) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * convenience method to set the object's ObjectMeta description
         * @param description the description text
         * @return the builder
         */
        public Builder description(String description) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * convenience method to set the object's ObjectMeta headers
         * @param headers the headers
         * @return the builder
         */
        public Builder headers(Headers headers) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * convenience method to set the object's ObjectMeta meta data
         * @param metadata the meta data
         * @return the builder
         */
        public Builder metadata(Map<String, String> metadata) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * convenience method to set the object's ObjectMeta meta options
         * @param objectMetaOptions the options
         * @return the builder
         */
        public Builder options(ObjectMetaOptions objectMetaOptions) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * convenience method to set the object's ObjectMeta chunk size
         * @param chunkSize the size of the chunks
         * @return the builder
         */
        public Builder chunkSize(int chunkSize) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * convenience method to set the object's ObjectMeta link
         * @param link the link
         * @return the builder
         */
        public Builder link(ObjectLink link) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * convenience method to set the object's ObjectMeta link bucket name
         * @param bucket the link bucket name
         * @return the builder
         */
        public Builder bucketLink(String bucket) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * convenience method to set the object's ObjectMeta link
         * @param bucket the link's bucket name
         * @param objectName the link's object name
         * @return the builder
         */
        public Builder objectLink(String bucket, String objectName) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Build an ObjectInfo
         * @return the ObjectInfo instance
         */
        public ObjectInfo build() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    @Override
    public boolean equals(Object o) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
