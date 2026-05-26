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

import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.JsonValue;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import static io.nats.client.support.ApiConstants.LINK;
import static io.nats.client.support.ApiConstants.MAX_CHUNK_SIZE;
import static io.nats.client.support.JsonUtils.beginJson;
import static io.nats.client.support.JsonUtils.endJson;
import static io.nats.client.support.JsonValueUtils.readInteger;
import static io.nats.client.support.JsonValueUtils.readValue;

/**
 * The ObjectMetaOptions are additional options describing the object
 */
public class ObjectMetaOptions implements JsonSerializable {

    private final ObjectLink link;

    private final int chunkSize;

    private ObjectMetaOptions(Builder b) {
        link = b.link;
        chunkSize = b.chunkSize;
    }

    ObjectMetaOptions(JsonValue vOptions) {
        link = ObjectLink.optionalInstance(readValue(vOptions, LINK));
        chunkSize = readInteger(vOptions, MAX_CHUNK_SIZE, -1);
    }

    @Override
    @NonNull
    public String toJson() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Whether the object is a link or has its own data
     * @return true if it has data
     */
    boolean hasData() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the link this object refers to
     * @return the link or null if this is not a link object
     */
    @Nullable
    public ObjectLink getLink() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the chunk size
     * @return the chunk size in bytes
     */
    public int getChunkSize() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static Builder builder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static Builder builder(ObjectMetaOptions om) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The builder for ObjectMetaOptions
     */
    public static class Builder {

        ObjectLink link;

        int chunkSize;

        /**
         * Construct an ObjectMetaOptions.Builder
         */
        public Builder() {
        }

        /**
         * Construct an ObjectMetaOptions.Builder as a copy of existing options
         * @param om the existing options
         */
        public Builder(ObjectMetaOptions om) {
            link = om.link;
            chunkSize = om.chunkSize;
        }

        /**
         * Set the link
         * @param link the link
         * @return the builder
         */
        public Builder link(ObjectLink link) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the chunk size
         * @param chunkSize the size in bytes
         * @return the builder
         */
        public Builder chunkSize(int chunkSize) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Build the ObjectMetaOptions
         * @return the ObjectMetaOptions instance
         */
        public ObjectMetaOptions build() {
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
