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
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import static io.nats.client.support.ApiConstants.DELETED_DETAILS;
import static io.nats.client.support.ApiConstants.SUBJECTS_FILTER;
import static io.nats.client.support.JsonUtils.*;
import static io.nats.client.support.NatsConstants.GREATER_THAN;
import static io.nats.client.support.Validator.emptyAsNull;

/**
 * Object used to make a request for special stream info requests
 */
public class StreamInfoOptions implements JsonSerializable {

    private final String subjectsFilter;

    private final boolean deletedDetails;

    private StreamInfoOptions(String subjectsFilter, boolean deletedDetails) {
        this.subjectsFilter = subjectsFilter;
        this.deletedDetails = deletedDetails;
    }

    /**
     * Get the configured subject filter
     * @return the subject filter
     */
    @Nullable
    public String getSubjectsFilter() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the configured flag requesting deleted details
     * @return true if configured for deleted details
     */
    public boolean isDeletedDetails() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create options that get subject information, filtering for subjects. Wildcards are allowed.
     * @param subjectsFilter the subject filter. &gt; is equivalent to all
     * @return the StreamInfoOptions object
     */
    public static StreamInfoOptions filterSubjects(String subjectsFilter) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create options that get subject information, filtering for all subjects.
     * @return the StreamInfoOptions object
     */
    public static StreamInfoOptions allSubjects() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create options that get deleted details.
     * @return the StreamInfoOptions object
     */
    public static StreamInfoOptions deletedDetails() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get an instance of the builder
     * @return the builder
     */
    public static Builder builder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    @NonNull
    public String toJson() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * StreamInfoOptions is created using a Builder. The builder supports chaining and will
     * create a default set of options if no methods are calls.
     *
     * <p>{@code new StreamInfoOptions.Builder().build()} will create a new StreamInfoOptions.
     */
    public static class Builder {

        private String subjectsFilter;

        private boolean deletedDetails;

        /**
         * Construct an instance of the builder
         */
        public Builder() {
        }

        /**
         * Set the subjects filter, which turns on getting subject info.
         * Setting the filter to &gt; is the same as all subjects
         * Setting the filter to null clears the filter and turns off getting subject info
         * @param subjectsFilter the
         * @return the builder
         */
        public Builder filterSubjects(String subjectsFilter) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the subjects filter to &gt;, which turns on getting subject info.
         * @return the builder
         */
        public Builder allSubjects() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Turns on getting deleted details
         * @return the builder
         */
        public Builder deletedDetails() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Build the options
         * @return the StreamInfoOptions object
         */
        public StreamInfoOptions build() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
