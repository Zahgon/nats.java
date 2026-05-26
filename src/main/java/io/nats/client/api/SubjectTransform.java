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
package io.nats.client.api;

import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import io.nats.client.support.Validator;
import org.jspecify.annotations.NonNull;
import java.util.List;
import java.util.Objects;
import static io.nats.client.support.ApiConstants.DEST;
import static io.nats.client.support.ApiConstants.SRC;
import static io.nats.client.support.JsonUtils.*;
import static io.nats.client.support.JsonValueUtils.readString;

/**
 * SubjectTransform
 */
public class SubjectTransform implements JsonSerializable {

    private final String source;

    private final String destination;

    static SubjectTransform optionalInstance(JsonValue vSubjectTransform) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static List<SubjectTransform> optionalListOf(JsonValue vSubjectTransforms) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    SubjectTransform(JsonValue vSubjectTransform) {
        source = readString(vSubjectTransform, SRC);
        destination = readString(vSubjectTransform, DEST);
    }

    /**
     * Construct a 'SubjectTransform' object
     * @param source the subject matching filter
     * @param destination the SubjectTransform Subject template
     */
    public SubjectTransform(@NonNull String source, @NonNull String destination) {
        this.source = Validator.required(source, "Source");
        this.destination = Validator.required(destination, "Destination");
    }

    /**
     * Get source, the subject matching filter
     * @return the source
     */
    @NonNull
    public String getSource() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get destination, the SubjectTransform Subject template
     * @return the destination
     */
    @NonNull
    public String getDestination() {
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
         * Set the SubjectTransform Subject template
         * @param destination the destination
         * @return the builder
         */
        public Builder destination(String destination) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Build a Placement object
         * @return the Placement
         */
        public SubjectTransform build() {
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
