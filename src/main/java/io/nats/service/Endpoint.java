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

import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.JsonValue;
import io.nats.client.support.Validator;
import org.jspecify.annotations.NonNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import static io.nats.client.support.ApiConstants.*;
import static io.nats.client.support.JsonUtils.endJson;
import static io.nats.client.support.JsonValueUtils.readString;
import static io.nats.client.support.JsonValueUtils.readStringStringMap;
import static io.nats.client.support.Validator.validateIsRestrictedTerm;

/**
 * Endpoint encapsulates the name, subject and metadata for a {@link ServiceEndpoint}.
 * <p>Endpoints can be used directly or as part of a group. {@link ServiceEndpoint} and {@link Group}</p>
 * <p>Endpoint names and subjects are considered 'Restricted Terms' and must only contain A-Z, a-z, 0-9, '-' or '_'</p>
 * <p>To create an Endpoint, either use a direct constructor or use the Endpoint builder
 * via the static method <code>builder()</code> or <code>new Endpoint.Builder() to get an instance.</code>
 * </p>
 */
public class Endpoint implements JsonSerializable {

    /**
     * The name of the default queue group
     */
    public static final String DEFAULT_QGROUP = "q";

    private final String name;

    private final String subject;

    private final String queueGroup;

    private final Map<String, String> metadata;

    /**
     * Directly construct an Endpoint with a name, which becomes the subject
     * @param name the name
     */
    public Endpoint(String name) {
        this(name, null, DEFAULT_QGROUP, null, true);
    }

    /**
     * Directly construct an Endpoint with a name, which becomes the subject, and metadata
     * @param name the name
     * @param metadata the metadata
     */
    public Endpoint(String name, Map<String, String> metadata) {
        this(name, null, DEFAULT_QGROUP, metadata, true);
    }

    /**
     * Directly construct an Endpoint with a name and a subject
     * @param name the name
     * @param subject the subject
     */
    public Endpoint(String name, String subject) {
        this(name, subject, DEFAULT_QGROUP, null, true);
    }

    /**
     * Directly construct an Endpoint with a name, the subject, and metadata
     * @param name the name
     * @param subject the subject
     * @param metadata the metadata
     */
    public Endpoint(String name, String subject, Map<String, String> metadata) {
        this(name, subject, DEFAULT_QGROUP, metadata, true);
    }

    /**
     * Directly construct an Endpoint with a name, the subject, queueGroup and metadata
     * @param name the name
     * @param subject the subject
     * @param queueGroup the queueGroup
     * @param metadata the metadata
     */
    public Endpoint(String name, String subject, String queueGroup, Map<String, String> metadata) {
        this(name, subject, queueGroup, metadata, true);
    }

    // internal use constructors
    Endpoint(String name, String subject, String queueGroup, Map<String, String> metadata, boolean validate) {
        if (validate) {
            this.name = validateIsRestrictedTerm(name, "Endpoint Name", true);
            if (subject == null) {
                this.subject = this.name;
            } else {
                this.subject = Validator.validateSubjectTermStrict(subject, "Endpoint Subject", false);
            }
            this.queueGroup = queueGroup == null ? null : Validator.validateSubjectTermStrict(queueGroup, "Endpoint Queue Group", true);
        } else {
            this.name = name;
            this.subject = subject;
            this.queueGroup = queueGroup;
        }
        this.metadata = metadata == null || metadata.isEmpty() ? null : metadata;
    }

    Endpoint(JsonValue vEndpoint) {
        name = readString(vEndpoint, NAME);
        subject = readString(vEndpoint, SUBJECT);
        queueGroup = readString(vEndpoint, QUEUE_GROUP);
        metadata = readStringStringMap(vEndpoint, METADATA);
    }

    Endpoint(Builder b) {
        this(b.name, b.subject, b.queueGroup, b.metadata, true);
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

    /**
     * Get the name of the Endpoint
     * @return the name
     */
    public String getName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the subject of the Endpoint
     * @return the subject
     */
    public String getSubject() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the queueGroup for the Endpoint
     * @return the queueGroup
     */
    public String getQueueGroup() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a copy of the metadata of the Endpoint
     * @return the copy of metadata
     */
    public Map<String, String> getMetadata() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get an instance of an Endpoint builder.
     * @return the instance
     */
    public static Builder builder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Build an Endpoint using a fluent builder.
     */
    public static class Builder {

        private String name;

        private String subject;

        private String queueGroup = DEFAULT_QGROUP;

        private Map<String, String> metadata;

        /**
         * Construct the builder
         */
        public Builder() {
        }

        /**
         * Copy the Endpoint, replacing all existing endpoint information.
         * @param endpoint the endpoint to copy
         * @return the Endpoint.Builder
         */
        public Builder endpoint(Endpoint endpoint) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the name for the Endpoint, replacing any name already set.
         * @param name the endpoint name
         * @return the Endpoint.Builder
         */
        public Builder name(String name) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the queueGroup for the Endpoint, overriding the system default queue group
         * Setting to null sets the group to the default qgroup, {@value #DEFAULT_QGROUP}. If you
         * do not want a queue group, use {@link #noQueueGroup() noQueueGroup()}
         * @param queueGroup the queueGroup
         * @return the Endpoint.Builder
         */
        public Builder queueGroup(String queueGroup) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set to not use a queueGroup for this endpoint
         * @return the Endpoint.Builder
         */
        public Builder noQueueGroup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the subject for the Endpoint, replacing any subject already set.
         * @param subject the subject
         * @return the Endpoint.Builder
         */
        public Builder subject(String subject) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the metadata for the Endpoint, replacing any metadata already set.
         * @param metadata the metadata
         * @return the Endpoint.Builder
         */
        public Builder metadata(Map<String, String> metadata) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Build the Endpoint instance.
         * @return the Endpoint instance
         */
        public Endpoint build() {
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
}
