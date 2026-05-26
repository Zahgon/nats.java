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
import io.nats.client.support.JsonValue;
import io.nats.client.support.Validator;
import org.jspecify.annotations.NonNull;
import static io.nats.client.support.ApiConstants.*;
import static io.nats.client.support.JsonUtils.*;
import static io.nats.client.support.JsonValueUtils.readBoolean;
import static io.nats.client.support.JsonValueUtils.readString;

/**
 * Republish Configuration
 */
public class Republish implements JsonSerializable {

    private final String source;

    private final String destination;

    private final boolean headersOnly;

    static Republish optionalInstance(JsonValue vRepublish) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    Republish(JsonValue vRepublish) {
        source = readString(vRepublish, SRC);
        destination = readString(vRepublish, DEST);
        headersOnly = readBoolean(vRepublish, HEADERS_ONLY);
    }

    /**
     * Construct a 'republish' object
     * @param source the Published subject matching filter
     * @param destination the RePublish Subject template
     * @param headersOnly Whether to RePublish only headers (no body)
     */
    public Republish(String source, String destination, boolean headersOnly) {
        Validator.required(source, "Source");
        Validator.required(destination, "Destination");
        this.source = source;
        this.destination = destination;
        this.headersOnly = headersOnly;
    }

    /**
     * Get source, the Published subject matching filter
     * @return the source
     */
    @NonNull
    public String getSource() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get destination, the RePublish Subject template
     * @return the destination
     */
    @NonNull
    public String getDestination() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get headersOnly, Whether to RePublish only headers (no body)
     * @return headersOnly
     */
    public boolean isHeadersOnly() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    @NonNull
    public String toJson() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates a builder for a placements object.
     * @return the builder.
     */
    public static Builder builder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Placement can be created using a Builder.
     */
    public static class Builder {

        private String source;

        private String destination;

        private boolean headersOnly;

        /**
         * Construct an instance of the builder
         */
        public Builder() {
        }

        /**
         * Set the Published Subject-matching filter
         * @param source the source
         * @return the builder
         */
        public Builder source(String source) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the RePublish Subject template
         * @param destination the destination
         * @return the builder
         */
        public Builder destination(String destination) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * set Whether to RePublish only headers (no body)
         * @param headersOnly the flag
         * @return Builder
         */
        public Builder headersOnly(Boolean headersOnly) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Build a Placement object
         * @return the Placement
         */
        public Republish build() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
