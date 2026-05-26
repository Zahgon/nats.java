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

import io.nats.client.support.NatsObjectStoreUtil;
import java.time.Duration;
import java.util.Map;
import static io.nats.client.support.NatsObjectStoreUtil.*;
import static io.nats.client.support.Validator.required;

/**
 * The ObjectStoreConfiguration class contains the configuration for an object store.
 */
public class ObjectStoreConfiguration extends FeatureConfiguration {

    ObjectStoreConfiguration(StreamConfiguration sc) {
        super(sc, extractBucketName(sc.getName()));
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * If true, indicates the store is sealed and cannot be modified in any way
     * @return the sealed setting
     */
    public boolean isSealed() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates a builder for the Object Store Configuration.
     * @return an ObjectStoreConfiguration Builder
     */
    public static ObjectStoreConfiguration.Builder builder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates a builder for the Object Store Configuration.
     * @param name the name of the object store bucket
     * @return an ObjectStoreConfiguration Builder
     */
    public static ObjectStoreConfiguration.Builder builder(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates a builder to copy the Object Store configuration.
     * @param osc an existing ObjectStoreConfiguration
     * @return an ObjectStoreConfiguration Builder
     */
    public static ObjectStoreConfiguration.Builder builder(ObjectStoreConfiguration osc) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * ObjectStoreConfiguration is created using a Builder. The builder supports chaining and will
     * create a default set of options if no methods are calls.
     *
     * <p>{@code new ObjectStoreConfiguration.Builder().build()} will create a new ObjectStoreConfiguration.
     */
    public static class Builder extends FeatureConfiguration.Builder<ObjectStoreConfiguration.Builder, ObjectStoreConfiguration> {

        @Override
        protected Builder getThis() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Default Builder
         */
        public Builder() {
            this((ObjectStoreConfiguration) null);
        }

        /**
         * Builder accepting the object store bucket name.
         * @param name name of the store.
         */
        public Builder(String name) {
            this((ObjectStoreConfiguration) null);
            name(name);
        }

        /**
         * Construct the builder by copying another configuration
         * @param osc the configuration to copy
         */
        public Builder(ObjectStoreConfiguration osc) {
            if (osc == null) {
                scBuilder = new StreamConfiguration.Builder();
                replicas(1);
            } else {
                scBuilder = new StreamConfiguration.Builder(osc.sc);
                name = NatsObjectStoreUtil.extractBucketName(osc.sc.getName());
            }
        }

        /**
         * Sets the name of the store.
         * @param name name of the store.
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
         * Sets the maximum number of bytes in the ObjectStoreConfiguration.
         * @param maxBucketSize the maximum number of bytes
         * @return Builder
         */
        @Override
        public Builder maxBucketSize(long maxBucketSize) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the maximum age for a value in this ObjectStoreConfiguration.
         * @param ttl the maximum age
         * @return Builder
         */
        @Override
        public Builder ttl(Duration ttl) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the storage type in the ObjectStoreConfiguration.
         * @param storageType the storage type
         * @return Builder
         */
        @Override
        public Builder storageType(StorageType storageType) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the number of replicas a message must be stored on in the ObjectStoreConfiguration.
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
         * Sets whether to use compression for the ObjectStoreConfiguration.
         * If set, will use the default compression algorithm of the Object Store backing.
         * @param compression whether to use compression in the ObjectStoreConfiguration
         * @return Builder
         */
        @Override
        public Builder compression(boolean compression) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the metadata for the ObjectStoreConfiguration
         * @param metadata the metadata map
         * @return Builder
         */
        @Override
        public Builder metadata(Map<String, String> metadata) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Builds the ObjectStoreConfiguration
         * @return the ObjectStoreConfiguration.
         */
        public ObjectStoreConfiguration build() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
