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

import io.nats.client.impl.Headers;
import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.JsonValue;
import io.nats.client.support.Validator;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import static io.nats.client.support.ApiConstants.*;
import static io.nats.client.support.JsonUtils.beginJson;
import static io.nats.client.support.JsonUtils.endJson;
import static io.nats.client.support.JsonValueUtils.*;

/**
 * The ObjectMeta is Object Meta is high level information about an object
 */
public class ObjectMeta implements JsonSerializable {

    private final String objectName;

    private final String description;

    private final Headers headers;

    private final Map<String, String> metadata;

    private final ObjectMetaOptions objectMetaOptions;

    private ObjectMeta(Builder b) {
        objectName = b.objectName;
        description = b.description;
        headers = new Headers(b.headers, true);
        metadata = Collections.unmodifiableMap(b.metadata);
        objectMetaOptions = b.metaOptionsBuilder.build();
    }

    ObjectMeta(JsonValue vObjectMeta) {
        objectName = readString(vObjectMeta, NAME);
        description = readString(vObjectMeta, DESCRIPTION);
        Headers h = new Headers();
        JsonValue hJv = readObject(vObjectMeta, HEADERS);
        for (String key : hJv.map.keySet()) {
            h.put(key, readStringList(hJv, key));
        }
        headers = new Headers(h, true);
        Map<String, String> meta = readStringStringMap(vObjectMeta, METADATA);
        metadata = meta == null ? Collections.unmodifiableMap(new HashMap<>()) : Collections.unmodifiableMap(meta);
        objectMetaOptions = new ObjectMetaOptions(readObject(vObjectMeta, OPTIONS));
    }

    @Override
    @NonNull
    public String toJson() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void embedJson(StringBuilder sb) {
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
     * The description
     * @return the description text or null
     */
    @Nullable
    public String getDescription() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Headers may be empty but will not be null. In all cases it will be unmodifiable
     * @return the headers object
     */
    @NonNull
    public Headers getHeaders() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Metadata may be empty but will not be null. In all cases it will be unmodifiable
     * @return the map
     */
    @NonNull
    public Map<String, String> getMetadata() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The ObjectMetaOptions are additional options describing the object
     * @return the object meta data
     */
    @Nullable
    public ObjectMetaOptions getObjectMetaOptions() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get an instance of the ObjectMeta.Builder starting with the object name
     * @param objectName the object name
     * @return the builder
     */
    public static Builder builder(String objectName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get an instance of the ObjectMeta.Builder as a copy of existing meta
     * @param om the existing meta
     * @return the builder
     */
    public static Builder builder(ObjectMeta om) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create object meta that only has the object name. Fairly common use case
     * @param objectName the object name
     * @return the ObjectMeta
     */
    public static ObjectMeta objectName(String objectName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The builder for ObjectMeta
     */
    public static class Builder {

        String objectName;

        String description;

        Headers headers;

        Map<String, String> metadata;

        ObjectMetaOptions.Builder metaOptionsBuilder;

        /**
         * Construct a builder starting with the object name
         * @param objectName the object name
         */
        public Builder(String objectName) {
            headers = new Headers();
            metadata = new HashMap<>();
            metaOptionsBuilder = ObjectMetaOptions.builder();
            objectName(objectName);
        }

        /**
         * Construct a builder as a copy of existing meta
         * @param om the existing meta
         */
        public Builder(ObjectMeta om) {
            objectName = om.objectName;
            description = om.description;
            headers = new Headers(om.headers);
            metadata = new HashMap<>(om.metadata);
            metaOptionsBuilder = ObjectMetaOptions.builder(om.objectMetaOptions);
        }

        /**
         * Set the object name
         * @param name the name
         * @return the builder
         */
        public Builder objectName(String name) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the object description
         * @param description the description
         * @return the builder
         */
        public Builder description(String description) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the object headers
         * @param headers the headers
         * @return the builder
         */
        public Builder headers(Headers headers) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the object metadata
         * @param metadata the metadata
         * @return the builder
         */
        public Builder metadata(Map<String, String> metadata) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the object meta options
         * @param objectMetaOptions the objectMetaOptions
         * @return the builder
         */
        public Builder options(ObjectMetaOptions objectMetaOptions) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Convenience method to set the ObjectMetaOptions chunk size
         * @param chunkSize the size in bytes
         * @return the builder
         */
        public Builder chunkSize(int chunkSize) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Convenience method to set the ObjectMetaOptions to set the link
         * @param link the link
         * @return the builder
         */
        public Builder link(ObjectLink link) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Build the Object Meta
         * @return the ObjectMeta instance
         */
        public ObjectMeta build() {
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
