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

import io.nats.client.support.JsonValueUtils;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import java.time.Duration;
import java.util.Map;

/**
 * The KeyValueStatus class contains information about a Key Value Bucket.
 */
public class KeyValueStatus {

    private final StreamInfo streamInfo;

    private final KeyValueConfiguration config;

    /**
     * Construct an instance from the underlying stream info
     * @param si the stream info
     */
    public KeyValueStatus(StreamInfo si) {
        streamInfo = si;
        config = new KeyValueConfiguration(streamInfo.getConfiguration());
    }

    /**
     * Get the name of the bucket
     * @return the name
     */
    @NonNull
    public String getBucketName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the description of this bucket.
     * @return the description of the bucket.
     */
    @Nullable
    public String getDescription() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the info for the stream which backs the bucket. Valid for BackingStore "JetStream"
     * @return the stream info
     */
    @NonNull
    public StreamInfo getBackingStreamInfo() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the configuration object directly
     * @return the configuration.
     */
    @NonNull
    public KeyValueConfiguration getConfiguration() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the number of total entries in the bucket, including historical entries
     * @return the count of entries
     */
    public long getEntryCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the size of the bucket in bytes
     * @return the number of bytes
     */
    public long getByteCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the maximum number of history for any one key. Includes the current value.
     * @return the maximum number of values for any one key.
     */
    public long getMaxHistoryPerKey() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the maximum number of bytes for this bucket.
     * @return the maximum number of bytes for this bucket.
     */
    public long getMaxBucketSize() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the maximum size for an individual value in the bucket.
     * @deprecated the server value is a 32-bit signed value. Use {@link #getMaximumValueSize()} instead.
     * @return the maximum size a value.
     */
    @Deprecated
    public long getMaxValueSize() {
        return config.getMaximumValueSize();
    }

    /**
     * Gets the maximum size for an individual value in the bucket.
     * @return the maximum size a value.
     */
    public int getMaximumValueSize() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the maximum age for a value in this bucket.
     * @return the maximum age.
     */
    @Nullable
    public Duration getTtl() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the storage type for this bucket.
     * @return the storage type for this stream.
     */
    @NonNull
    public StorageType getStorageType() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the number of replicas for this bucket.
     * @return the number of replicas
     */
    public int getReplicas() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the placement directive for the store.
     * @return the placement
     */
    @Nullable
    public Placement getPlacement() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the republish configuration
     * @return the republish object
     */
    @Nullable
    public Republish getRepublish() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the state of compression
     * @return true if compression is used
     */
    public boolean isCompressed() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the metadata for the store
     * @return the metadata map. Might be null.
     */
    @Nullable
    public Map<String, String> getMetadata() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the Limit Marker TTL duration or null if configured.
     * @return the duration.
     */
    @Nullable
    public Duration getLimitMarkerTtl() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the name of the type of backing store, currently only "JetStream"
     * @return the name of the store, currently only "JetStream"
     */
    @NonNull
    public String getBackingStore() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
