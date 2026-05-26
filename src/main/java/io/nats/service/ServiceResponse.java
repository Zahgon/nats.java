// Copyright 2023 The NATS Authors
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
package io.nats.service;

import io.nats.client.support.*;
import org.jspecify.annotations.NonNull;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import static io.nats.client.support.ApiConstants.*;
import static io.nats.client.support.JsonUtils.endJson;
import static io.nats.client.support.JsonValueUtils.readString;
import static io.nats.client.support.JsonValueUtils.readStringStringMap;

/**
 * Base class for service responses Info, Ping and Stats
 */
public abstract class ServiceResponse implements JsonSerializable {

    protected final String type;

    protected final String name;

    protected final String id;

    protected final String version;

    protected final Map<String, String> metadata;

    protected final AtomicReference<byte[]> serialized;

    protected ServiceResponse(String type, String id, String name, String version, Map<String, String> metadata) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.version = version;
        this.metadata = metadata == null || metadata.isEmpty() ? null : metadata;
        serialized = new AtomicReference<>();
    }

    protected ServiceResponse(String type, ServiceResponse template) {
        this(type, template.id, template.name, template.version, template.metadata);
    }

    protected ServiceResponse(String type, JsonValue jv) {
        String jvType = readString(jv, TYPE);
        if (Validator.emptyAsNull(jvType) == null) {
            throw new IllegalArgumentException("Type cannot be null or empty.");
        }
        if (!type.equals(jvType)) {
            throw new IllegalArgumentException("Invalid type for " + getClass().getSimpleName() + ". Expecting: " + type + ". Received " + jvType);
        }
        this.type = type;
        id = Validator.required(readString(jv, ID), "Id");
        name = Validator.required(readString(jv, NAME), "Name");
        version = Validator.required(readString(jv, VERSION), "Version");
        metadata = readStringStringMap(jv, METADATA);
        serialized = new AtomicReference<>();
    }

    @Override
    public byte @NonNull [] serialize() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected static JsonValue parseMessage(byte[] bytes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The type of this response;
     * @return the type string
     */
    public String getType() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The unique ID of the service
     * @return the service id
     */
    public String getId() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The name of the service
     * @return the service name
     */
    public String getName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Version of the service
     * @return the version
     */
    public String getVersion() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * A copy of the metadata for the service, or null if there is no metadata
     * @return the metadata
     */
    public Map<String, String> getMetadata() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void subToJson(StringBuilder sb) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    @NonNull
    public String toJson() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
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
