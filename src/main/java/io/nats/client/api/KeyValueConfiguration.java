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
package io.nats.client.api;

import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import io.nats.client.support.NatsKeyValueUtil;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import java.time.Duration;
import java.util.*;
import static io.nats.client.support.NatsJetStreamConstants.SERVER_DEFAULT_DUPLICATE_WINDOW_MS;
import static io.nats.client.support.NatsKeyValueUtil.*;
import static io.nats.client.support.Validator.*;

/**
 * The KeyValueConfiguration class contains the configuration for a Key Value bucket.
 */
public class KeyValueConfiguration extends FeatureConfiguration {

    KeyValueConfiguration(StreamConfiguration sc) {
        super(sc, extractBucketName(sc.getName()));
    }

    /**
     * Gets the maximum number of history for any one key. Includes the current value.
     * @return the maximum number of values for any one key.
     */
    public long getMaxHistoryPerKey() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the maximum size for an individual value in the bucket.
     * @deprecated the server value is a 32-bit signed value. Use {@link #getMaximumValueSize()} instead.
     * @return the maximum size for a value.
     */
    @Deprecated
    public long getMaxValueSize() {
        return sc.getMaximumMessageSize();
    }

    /**
     * Gets the maximum size for an individual value in the bucket.
     * @return the maximum size for a value.
     */
    public int getMaximumValueSize() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the republish configuration. Might be null.
     * @return the republish object
     */
    @Nullable
    public Republish getRepublish() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The mirror definition for this configuration
     * @return the mirror
     */
    @Nullable
    public Mirror getMirror() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The sources for this configuration
     * @return the sources
     */
    @Nullable
    public List<Source> getSources() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The limit marker ttl if set
     * @return the duration
     */
    @Nullable
    public Duration getLimitMarkerTtl() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    @NonNull
    public JsonValue toJsonValue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates a builder for the Key Value Configuration.
     * @return a KeyValueConfiguration Builder
     */
    public static Builder builder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates a builder for the Key Value Configuration.
     * @param name the name of the key value bucket
     * @return a KeyValueConfiguration Builder
     */
    public static Builder builder(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates a builder to copy the key value configuration.
     * @param kvc an existing KeyValueConfiguration
     * @return a KeyValueConfiguration Builder
     */
    public static Builder builder(KeyValueConfiguration kvc) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * KeyValueConfiguration is created using a Builder. The builder supports chaining and will
     * create a default set of options if no methods are calls.
     *
     * <p>{@code new Builder().build()} will create a new KeyValueConfiguration.
     */
    public static class Builder extends FeatureConfiguration.Builder<Builder, KeyValueConfiguration> {

        Mirror mirror;

        Duration limitMarkerTtl;

        final List<Source> sources = new ArrayList<>();

        @Override
        protected Builder getThis() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Default Builder
         */
        public Builder() {
            this((KeyValueConfiguration) null);
        }

        /**
         * Builder accepting the key value bucket name.
         * @param name name of the key value bucket.
         */
        public Builder(String name) {
            this((KeyValueConfiguration) null);
            name(name);
        }

        /**
         * Construct the builder by copying another configuration
         * @param kvc the configuration to copy
         */
        public Builder(KeyValueConfiguration kvc) {
            if (kvc == null) {
                scBuilder = new StreamConfiguration.Builder();
                maxHistoryPerKey(1);
                replicas(1);
            } else {
                scBuilder = new StreamConfiguration.Builder(kvc.sc);
                name = NatsKeyValueUtil.extractBucketName(kvc.sc.getName());
            }
        }

        /**
         * Sets the name of the key value bucket.
         * @param name name of the key value bucket.
         * @return the builder
         */
        @Override
        public Builder name(String name) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the description of the store.
         * @param description description of the store.
         * @return the builder
         */
        @Override
        public Builder description(String description) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the maximum number of history for any one key. Includes the current value.
         * @param maxHistoryPerKey the maximum history
         * @return Builder
         */
        public Builder maxHistoryPerKey(int maxHistoryPerKey) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the maximum number of bytes in the KeyValueConfiguration.
         * @param maxBucketSize the maximum number of bytes
         * @return Builder
         */
        @Override
        public Builder maxBucketSize(long maxBucketSize) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the maximum size for an individual value in the KeyValueConfiguration.
         * @deprecated the server value is a 32-bit signed value. Use {@link #maximumValueSize(int)} instead.
         * @param maxValueSize the maximum size for a value
         * @return Builder
         */
        @Deprecated
        public Builder maxValueSize(long maxValueSize) {
            scBuilder.maximumMessageSize((int) validateMaxValueSize(maxValueSize));
            return this;
        }

        /**
         * Sets the maximum size for an individual value in the KeyValueConfiguration.
         * @param maxValueSize the maximum size for a value
         * @return Builder
         */
        public Builder maximumValueSize(int maxValueSize) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the maximum age for a value in this KeyValueConfiguration.
         * @param ttl the maximum age
         * @return Builder
         */
        @Override
        public Builder ttl(Duration ttl) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the storage type in the KeyValueConfiguration.
         * @param storageType the storage type
         * @return Builder
         */
        @Override
        public Builder storageType(StorageType storageType) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the number of replicas a message must be stored on in the KeyValueConfiguration.
         * @param replicas the number of replicas
         * @return Builder
         */
        @Override
        public Builder replicas(int replicas) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the placement directive object
         * @param placement the placement directive object
         * @return Builder
         */
        @Override
        public Builder placement(Placement placement) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets whether to use compression for the KeyValueConfiguration.
         * If set, will use the default compression algorithm of the KV backing store.
         * @param compression whether to use compression in the KeyValueConfiguration
         * @return Builder
         */
        @Override
        public Builder compression(boolean compression) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the metadata for the KeyValueConfiguration
         * @param metadata the metadata map
         * @return Builder
         */
        @Override
        public Builder metadata(Map<String, String> metadata) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the Republish options
         * @param republish the Republish object
         * @return Builder
         */
        public Builder republish(Republish republish) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the mirror in the KeyValueConfiguration.
         * @param mirror the KeyValue's mirror
         * @return Builder
         */
        public Builder mirror(Mirror mirror) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the sources in the KeyValueConfiguration.
         * @param sources the KeyValue's sources
         * @return Builder
         */
        public Builder sources(Source... sources) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the sources in the KeyValueConfiguration
         * @param sources the KeyValue's sources
         * @return Builder
         */
        public Builder sources(Collection<Source> sources) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Add a source into the KeyValueConfiguration.
         * @param source a KeyValue source
         * @return Builder
         */
        public Builder addSource(Source source) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Adds the sources into the KeyValueConfiguration
         * @param sources the KeyValue's sources to add
         * @return Builder
         */
        public Builder addSources(Source... sources) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Adds the sources into the KeyValueConfiguration
         * @param sources the KeyValue's sources to add
         * @return Builder
         */
        public Builder addSources(Collection<Source> sources) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * The limit marker TTL duration. Server accepts 1 second or more.
         * Null or empty has the effect of clearing the limit marker ttl
         * @param limitMarkerTtl the TTL duration
         * @return The Builder
         */
        public Builder limitMarker(Duration limitMarkerTtl) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * The limit marker TTL duration in milliseconds. Server accepts 1 second or more.
         * 0 or less has the effect of clearing the limit marker ttl
         * @param limitMarkerTtlMillis the TTL duration
         * @return The Builder
         */
        public Builder limitMarker(long limitMarkerTtlMillis) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Builds the KeyValueConfiguration
         * @return the KeyValueConfiguration.
         */
        public KeyValueConfiguration build() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
