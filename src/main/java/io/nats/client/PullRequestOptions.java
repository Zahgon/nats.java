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
package io.nats.client;

import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonUtils;
import org.jspecify.annotations.NonNull;
import java.time.Duration;
import static io.nats.client.support.ApiConstants.*;
import static io.nats.client.support.Validator.validateGtZero;

/**
 * The PullRequestOptions class specifies the options for pull requests
 */
public class PullRequestOptions implements JsonSerializable {

    private final int batchSize;

    private final long maxBytes;

    private final boolean noWait;

    private final Duration expiresIn;

    private final Duration idleHeartbeat;

    private final String group;

    private final int priority;

    private final long minPending;

    private final long minAckPending;

    /**
     * Construct PullRequestOptions from the builder
     * @param b the builder
     */
    public PullRequestOptions(Builder b) {
        this.batchSize = b.batchSize;
        this.maxBytes = b.maxBytes;
        this.noWait = b.noWait;
        this.expiresIn = b.expiresIn;
        this.idleHeartbeat = b.idleHeartbeat;
        this.group = b.group;
        this.priority = b.priority;
        this.minPending = b.minPending < 0 ? -1 : b.minPending;
        this.minAckPending = b.minAckPending < 0 ? -1 : b.minAckPending;
    }

    @Override
    @NonNull
    public String toJson() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected String getPinId() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the batch size option value
     * @return the batch size
     */
    public int getBatchSize() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the max bytes size option value
     * @return the max bytes size
     */
    public long getMaxBytes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the no wait flag value
     * @return the flag
     */
    public boolean isNoWait() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the expires in option value
     * @return the expires in duration
     */
    public Duration getExpiresIn() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the idle heartbeat option value
     * @return the idle heartbeat duration
     */
    public Duration getIdleHeartbeat() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the group option
     * @return the group
     */
    public String getGroup() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the priority
     * @return the priority
     */
    public int getPriority() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the min pending setting
     * @return the min pending
     */
    public long getMinPending() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the min ack pending setting
     * @return the min ack setting
     */
    public long getMinAckPending() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates a builder for the pull options, with batch size since it's always required
     * @param batchSize the size of the batch. Must be greater than 0
     * @return a pull options builder
     */
    public static Builder builder(int batchSize) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates a builder for the pull options, setting no wait to true and accepting batch size
     * @param batchSize the size of the batch. Must be greater than 0
     * @return a pull options builder
     */
    public static Builder noWait(int batchSize) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The builder for PullRequestOptions
     */
    public static class Builder {

        private int batchSize;

        private long maxBytes;

        private boolean noWait;

        private Duration expiresIn;

        private Duration idleHeartbeat;

        private String group;

        private int priority;

        private long minPending = -1;

        private long minAckPending = -1;

        /**
         * Construct an instance of the builder
         */
        public Builder() {
        }

        /**
         * Set the batch size for the pull
         * @param batchSize the size of the batch. Must be greater than 0
         * @return the builder
         */
        public Builder batchSize(int batchSize) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * The maximum bytes for the pull
         * @param maxBytes the maximum bytes
         * @return the builder
         */
        public Builder maxBytes(long maxBytes) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set no wait to true
         * @return the builder
         */
        public Builder noWait() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the no wait flag
         * @param noWait the flag
         * @return the builder
         */
        public Builder noWait(boolean noWait) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the expires time in millis
         * @param expiresInMillis the millis
         * @return the builder
         */
        public Builder expiresIn(long expiresInMillis) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the expires duration
         * @param expiresIn the duration
         * @return the builder
         */
        public Builder expiresIn(Duration expiresIn) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the idle heartbeat time in millis
         * @param idleHeartbeatMillis the millis
         * @return the builder
         */
        public Builder idleHeartbeat(long idleHeartbeatMillis) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the idle heartbeat duration
         * @param idleHeartbeat the duration
         * @return the builder
         */
        public Builder idleHeartbeat(Duration idleHeartbeat) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the group
         * Replaces any other groups set in the builder
         * @param group the priority group for this pull
         * @return Builder
         */
        public Builder group(String group) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the priority within the group. Priority must be between 0 and 9 inclusive.
         * @param priority the priority
         * @return Builder
         */
        public Builder priority(int priority) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * When specified, the pull request will only receive messages when the consumer has at least this many pending messages.
         * @param minPending the min pending
         * @return the builder
         */
        public Builder minPending(long minPending) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * When specified, this Pull request will only receive messages when the consumer has at least this many ack pending messages.
         * @param minAckPending the min ack pending
         * @return the builder
         */
        public Builder minAckPending(long minAckPending) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Build the PullRequestOptions.
         * <p>Validates that the batch size is greater than 0</p>
         * <p>If supplied, validates that the idle heartbeat is valid for the expiration</p>
         * @return the built PullRequestOptions
         */
        public PullRequestOptions build() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
