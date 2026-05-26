// Copyright 2020 The NATS Authors
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

import io.nats.client.support.*;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import java.time.Duration;
import java.util.*;
import static io.nats.client.support.ApiConstants.*;
import static io.nats.client.support.JsonUtils.*;
import static io.nats.client.support.JsonValueUtils.*;
import static io.nats.client.support.JsonValueUtils.readBoolean;
import static io.nats.client.support.JsonValueUtils.readInteger;
import static io.nats.client.support.JsonValueUtils.readLong;
import static io.nats.client.support.JsonValueUtils.readNanos;
import static io.nats.client.support.JsonValueUtils.readString;
import static io.nats.client.support.Validator.*;

/**
 * The StreamConfiguration class specifies the configuration for creating a JetStream stream on the server.
 * Options are created using a {@link StreamConfiguration.Builder Builder}.
 */
public class StreamConfiguration implements JsonSerializable {

    // see builder for defaults
    private final String name;

    private final String description;

    private final List<String> subjects;

    private final RetentionPolicy retentionPolicy;

    private final CompressionOption compressionOption;

    private final long maxConsumers;

    private final long maxMsgs;

    private final long maxMsgsPerSubject;

    private final long maxBytes;

    private final Duration maxAge;

    private final int maxMsgSize;

    private final StorageType storageType;

    private final int replicas;

    private final boolean noAck;

    private final String templateOwner;

    private final DiscardPolicy discardPolicy;

    private final Duration duplicateWindow;

    private final Placement placement;

    private final Republish republish;

    private final SubjectTransform subjectTransform;

    private final ConsumerLimits consumerLimits;

    private final Mirror mirror;

    private final List<Source> sources;

    private final boolean sealed;

    private final boolean allowRollup;

    private final boolean allowDirect;

    private final boolean mirrorDirect;

    private final boolean denyDelete;

    private final boolean denyPurge;

    private final boolean discardNewPerSubject;

    private final Map<String, String> metadata;

    private final long firstSequence;

    private final Duration subjectDeleteMarkerTtl;

    private final boolean allowMessageTtl;

    private final boolean allowMsgSchedules;

    private final boolean allowMessageCounter;

    private final boolean allowAtomicPublish;

    private final boolean allowBatched;

    private final PersistMode persistMode;

    static StreamConfiguration instance(JsonValue v) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // For the builder, assumes all validations are already done in builder
    StreamConfiguration(Builder b) {
        this.name = b.name;
        this.description = b.description;
        this.subjects = b.subjects;
        this.retentionPolicy = b.retentionPolicy;
        this.compressionOption = b.compressionOption;
        this.maxConsumers = b.maxConsumers;
        this.maxMsgs = b.maxMsgs;
        this.maxMsgsPerSubject = b.maxMsgsPerSubject;
        this.maxBytes = b.maxBytes;
        this.maxAge = b.maxAge;
        this.maxMsgSize = b.maxMsgSize;
        this.storageType = b.storageType;
        this.replicas = b.replicas;
        this.noAck = b.noAck;
        this.templateOwner = b.templateOwner;
        this.discardPolicy = b.discardPolicy;
        this.duplicateWindow = b.duplicateWindow;
        this.placement = b.placement;
        this.republish = b.republish;
        this.subjectTransform = b.subjectTransform;
        this.consumerLimits = b.consumerLimits;
        this.mirror = b.mirror;
        this.sources = b.sources;
        this.sealed = b.sealed;
        this.allowRollup = b.allowRollup;
        this.allowDirect = b.allowDirect;
        this.mirrorDirect = b.mirrorDirect;
        this.denyDelete = b.denyDelete;
        this.denyPurge = b.denyPurge;
        this.discardNewPerSubject = b.discardNewPerSubject;
        this.metadata = b.metadata;
        this.firstSequence = b.firstSequence;
        this.subjectDeleteMarkerTtl = b.subjectDeleteMarkerTtl;
        this.allowMessageTtl = b.allowMessageTtl;
        this.allowMsgSchedules = b.allowMsgSchedules;
        this.allowMessageCounter = b.allowMessageCounter;
        this.allowAtomicPublish = b.allowAtomicPublish;
        this.allowBatched = b.allowBatched;
        this.persistMode = b.persistMode;
    }

    /**
     * Returns a StreamConfiguration deserialized from its JSON form.
     *
     * @see #toJson()
     * @param json the json representing the Stream Configuration
     * @return StreamConfiguration for the given json
     * @throws JsonParseException if there is a problem parsing the json
     */
    public static StreamConfiguration instance(String json) throws JsonParseException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns a JSON representation of this consumer configuration.
     *
     * @return json consumer configuration to send to the server.
     */
    @Override
    @NonNull
    public String toJson() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the name of this stream configuration.
     * @return the name of the stream.
     */
    @NonNull
    public String getName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the description of this stream configuration.
     * @return the description of the stream.
     */
    @Nullable
    public String getDescription() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the subjects for this stream configuration.
     * @return the subject of the stream.
     */
    @NonNull
    public List<String> getSubjects() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the discard policy for this stream configuration.
     * @return the discard policy of the stream.
     */
    @Nullable
    public DiscardPolicy getDiscardPolicy() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the retention policy for this stream configuration.
     * @return the retention policy for this stream.
     */
    @NonNull
    public RetentionPolicy getRetentionPolicy() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the compression option for this stream configuration.
     * @return the compression option for this stream.
     */
    @Nullable
    public CompressionOption getCompressionOption() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the maximum number of consumers for this stream configuration.
     * @return the maximum number of consumers for this stream.
     */
    public long getMaxConsumers() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the maximum messages for this stream configuration.
     * @return the maximum number of messages for this stream.
     */
    public long getMaxMsgs() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the maximum messages for this stream configuration.
     * @return the maximum number of messages for this stream.
     */
    public long getMaxMsgsPerSubject() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the maximum number of bytes for this stream configuration.
     * @return the maximum number of bytes for this stream.
     */
    public long getMaxBytes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the maximum message age for this stream configuration.
     * @return the maximum message age for this stream.
     */
    @NonNull
    public Duration getMaxAge() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the maximum message size for this stream configuration.
     * @deprecated the server value is a 32-bit signed value. Use {@link #getMaximumMessageSize()} instead.
     * @return the maximum message size for this stream.
     */
    @Deprecated
    public long getMaxMsgSize() {
        return maxMsgSize;
    }

    /**
     * Gets the maximum message size for this stream configuration.
     * @return the maximum message size for this stream.
     */
    public int getMaximumMessageSize() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the storage type for this stream configuration.
     * @return the storage type for this stream.
     */
    @NonNull
    public StorageType getStorageType() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the number of replicas for this stream configuration.
     * @return the number of replicas
     */
    public int getReplicas() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets whether acknowledgements are required in this stream configuration.
     * @return true if acknowedgments are not required.
     */
    public boolean getNoAck() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the template json for this stream configuration.
     * @return the template for this stream.
     */
    @Nullable
    public String getTemplateOwner() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the duplicate checking window stream configuration.  Duration.ZERO
     * means duplicate checking is not enabled.
     * @return the duration of the window.
     */
    @Nullable
    public Duration getDuplicateWindow() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the placement directives to consider when placing replicas of this stream,
     * random placement when unset. May be null.
     * @return the placement object
     */
    @Nullable
    public Placement getPlacement() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the republish configuration. May be null.
     * @return the republish object
     */
    @Nullable
    public Republish getRepublish() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the subjectTransform configuration. May be null.
     * @return the subjectTransform object
     */
    @Nullable
    public SubjectTransform getSubjectTransform() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the consumerLimits configuration. May be null.
     * @return the consumerLimits object
     */
    @Nullable
    public ConsumerLimits getConsumerLimits() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The mirror definition for this stream
     * @return the mirror
     */
    @Nullable
    public Mirror getMirror() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The sources for this stream
     * @return the sources
     */
    @Nullable
    public List<Source> getSources() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the flag indicating if the stream is sealed.
     * @return the sealed flag
     */
    public boolean getSealed() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the flag indicating if the stream allows rollup.
     * @return the allows rollup flag
     */
    public boolean getAllowRollup() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the flag indicating if the stream allows direct message access.
     * @return the allows direct flag
     */
    public boolean getAllowDirect() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the flag indicating if the stream allows
     * higher performance and unified direct access for mirrors as well.
     * @return the allows direct flag
     */
    public boolean getMirrorDirect() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the flag indicating if deny delete is set for the stream
     * @return the deny delete flag
     */
    public boolean getDenyDelete() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the flag indicating if deny purge is set for the stream
     * @return the deny purge flag
     */
    public boolean getDenyPurge() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Whether discard policy with max message per subject is applied per subject.
     * @return the discard new per subject flag
     */
    public boolean isDiscardNewPerSubject() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Metadata for the stream
     * @return the metadata map. Might be null.
     */
    @Nullable
    public Map<String, String> getMetadata() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The first sequence used in the stream.
     * @return the first sequence
     */
    public long getFirstSequence() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @deprecated Prefer getAllowMessageTtl
     * Whether Allow Message TTL is set
     * @return the flag
     */
    @Deprecated
    public boolean isAllowMessageTtl() {
        return allowMessageTtl;
    }

    /**
     * Whether Allow Message TTL is set
     * @return the flag
     */
    public boolean getAllowMessageTtl() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Whether Allow Message Schedules is set
     * @return the flag
     */
    public boolean getAllowMsgSchedules() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Whether Allow Message Counter is set
     * @return the flag
     */
    public boolean getAllowMessageCounter() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Whether Allow Atomic Publish is set
     * @return the flag
     */
    public boolean getAllowAtomicPublish() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Whether Allow Batched is set
     * @return the flag
     */
    public boolean getAllowBatched() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the Subject Delete Marker TTL duration. May be null.
     * @return The duration
     */
    @Nullable
    public Duration getSubjectDeleteMarkerTtl() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the persist mode or null if it was not explicitly set when creating or the server did not send it with stream info
     * @return the persist mode
     */
    @Nullable
    public PersistMode getPersistMode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates a builder for the stream configuration.
     * @return a stream configuration builder
     */
    public static Builder builder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates a builder to copy the stream configuration.
     * @param sc an existing StreamConfiguration
     * @return a stream configuration builder
     */
    public static Builder builder(StreamConfiguration sc) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * StreamConfiguration is created using a Builder. The builder supports chaining and will
     * create a default set of options if no methods are calls.
     *
     * <p>{@code new StreamConfiguration.Builder().build()} will create a new StreamConfiguration.
     */
    public static class Builder {

        private String name = null;

        private String description = null;

        private final List<String> subjects = new ArrayList<>();

        private RetentionPolicy retentionPolicy = RetentionPolicy.Limits;

        private CompressionOption compressionOption = CompressionOption.None;

        private long maxConsumers = -1;

        private long maxMsgs = -1;

        private long maxMsgsPerSubject = -1;

        private long maxBytes = -1;

        private Duration maxAge = Duration.ZERO;

        private int maxMsgSize = -1;

        private StorageType storageType = StorageType.File;

        private int replicas = 1;

        private boolean noAck = false;

        private String templateOwner = null;

        private DiscardPolicy discardPolicy = DiscardPolicy.Old;

        private Duration duplicateWindow = Duration.ZERO;

        private Placement placement = null;

        private Republish republish = null;

        private SubjectTransform subjectTransform = null;

        private ConsumerLimits consumerLimits = null;

        private Mirror mirror = null;

        private final List<Source> sources = new ArrayList<>();

        private boolean sealed = false;

        private boolean allowRollup = false;

        private boolean allowDirect = false;

        private boolean mirrorDirect = false;

        private boolean denyDelete = false;

        private boolean denyPurge = false;

        private boolean discardNewPerSubject = false;

        private Map<String, String> metadata;

        private long firstSequence = 1;

        private Duration subjectDeleteMarkerTtl;

        private boolean allowMessageTtl = false;

        private boolean allowMsgSchedules = false;

        private boolean allowMessageCounter = false;

        private boolean allowAtomicPublish = false;

        private boolean allowBatched = false;

        private PersistMode persistMode = null;

        /**
         * Default Builder
         */
        public Builder() {
        }

        /**
         * Update Builder, useful if you need to update a configuration
         * @param sc the configuration to copy
         */
        public Builder(StreamConfiguration sc) {
            if (sc != null) {
                this.name = sc.name;
                this.description = sc.description;
                subjects(sc.subjects);
                this.retentionPolicy = sc.retentionPolicy;
                this.compressionOption = sc.compressionOption;
                this.maxConsumers = sc.maxConsumers;
                this.maxMsgs = sc.maxMsgs;
                this.maxMsgsPerSubject = sc.maxMsgsPerSubject;
                this.maxBytes = sc.maxBytes;
                this.maxAge = sc.maxAge;
                this.maxMsgSize = sc.maxMsgSize;
                this.storageType = sc.storageType;
                this.replicas = sc.replicas;
                this.noAck = sc.noAck;
                this.templateOwner = sc.templateOwner;
                this.discardPolicy = sc.discardPolicy;
                this.duplicateWindow = sc.duplicateWindow;
                this.placement = sc.placement;
                this.republish = sc.republish;
                this.subjectTransform = sc.subjectTransform;
                this.consumerLimits = sc.consumerLimits;
                this.mirror = sc.mirror;
                sources(sc.sources);
                this.sealed = sc.sealed;
                this.allowRollup = sc.allowRollup;
                this.allowDirect = sc.allowDirect;
                this.mirrorDirect = sc.mirrorDirect;
                this.denyDelete = sc.denyDelete;
                this.denyPurge = sc.denyPurge;
                this.discardNewPerSubject = sc.discardNewPerSubject;
                if (sc.metadata != null) {
                    this.metadata = new HashMap<>(sc.metadata);
                }
                this.firstSequence = sc.firstSequence;
                this.subjectDeleteMarkerTtl = sc.subjectDeleteMarkerTtl;
                this.allowMessageTtl = sc.allowMessageTtl;
                this.allowMsgSchedules = sc.allowMsgSchedules;
                this.allowMessageCounter = sc.allowMessageCounter;
                this.allowAtomicPublish = sc.allowAtomicPublish;
                this.allowBatched = sc.allowBatched;
                this.persistMode = sc.persistMode;
            }
        }

        /**
         * Sets the name of the stream.
         * @param name name of the stream.
         * @return the builder
         */
        public Builder name(String name) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the description
         * @param description the description
         * @return the builder
         */
        public Builder description(String description) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the subjects in the StreamConfiguration.
         * @param subjects the stream's subjects
         * @return The Builder
         */
        public Builder subjects(String... subjects) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the subjects in the StreamConfiguration.
         * @param subjects the stream's subjects
         * @return The Builder
         */
        public Builder subjects(Collection<String> subjects) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Adds unique subjects into the StreamConfiguration.
         * @param subjects the stream's subjects to add
         * @return The Builder
         */
        public Builder addSubjects(String... subjects) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Adds unique subjects into the StreamConfiguration.
         * @param subjects the stream's subjects to add
         * @return The Builder
         */
        public Builder addSubjects(Collection<String> subjects) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private Builder _addSubjects(@NonNull Collection<String> subjects) {
            for (String sub : subjects) {
                if (!nullOrEmpty(sub) && !this.subjects.contains(sub)) {
                    this.subjects.add(sub);
                }
            }
            return this;
        }

        /**
         * Sets the retention policy in the StreamConfiguration.
         * @param policy the retention policy of the StreamConfiguration
         * @return The Builder
         */
        public Builder retentionPolicy(RetentionPolicy policy) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the compression option in the StreamConfiguration.
         * @param compressionOption the compression option of the StreamConfiguration
         * @return The Builder
         */
        public Builder compressionOption(CompressionOption compressionOption) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the maximum number of consumers in the StreamConfiguration.
         * @param maxConsumers the maximum number of consumers
         * @return The Builder
         */
        public Builder maxConsumers(long maxConsumers) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the maximum number of messages in the StreamConfiguration.
         * @param maxMsgs the maximum number of messages
         * @return The Builder
         */
        public Builder maxMessages(long maxMsgs) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the maximum number of message per subject in the StreamConfiguration.
         * @param maxMsgsPerSubject the maximum number of messages
         * @return The Builder
         */
        public Builder maxMessagesPerSubject(long maxMsgsPerSubject) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the maximum number of bytes in the StreamConfiguration.
         * @param maxBytes the maximum number of bytes
         * @return The Builder
         */
        public Builder maxBytes(long maxBytes) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the maximum age in the StreamConfiguration.
         * @param maxAge the maximum message age
         * @return The Builder
         */
        public Builder maxAge(Duration maxAge) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the maximum age in the StreamConfiguration.
         * @param maxAgeMillis the maximum message age
         * @return The Builder
         */
        public Builder maxAge(long maxAgeMillis) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the maximum message size in the StreamConfiguration.
         * @deprecated the server value is a 32-bit signed value. Use {@link #maximumMessageSize(int)} instead.
         * @param maxMsgSize the maximum message size
         * @return The Builder
         */
        @Deprecated
        public Builder maxMsgSize(long maxMsgSize) {
            this.maxMsgSize = (int) validateMaxMessageSize(maxMsgSize);
            return this;
        }

        /**
         * Sets the maximum message size in the StreamConfiguration.
         * @param maxMsgSize the maximum message size
         * @return The Builder
         */
        public Builder maximumMessageSize(int maxMsgSize) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the storage type in the StreamConfiguration.
         * @param storageType the storage type
         * @return The Builder
         */
        public Builder storageType(StorageType storageType) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the number of replicas a message must be stored on in the StreamConfiguration.
         * Must be 1 to 5 inclusive
         * @param replicas the number of replicas to store this message on
         * @return The Builder
         */
        public Builder replicas(int replicas) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the acknowledgement mode of the StreamConfiguration.  if no acknowledgements are
         * set, then acknowledgements are not sent back to the client.  The default is false.
         * @param noAck true to disable acknowledgements.
         * @return The Builder
         */
        public Builder noAck(boolean noAck) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the template a stream in the form of raw JSON.
         * @param templateOwner the stream template of the stream.
         * @return the builder
         */
        public Builder templateOwner(String templateOwner) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the discard policy in the StreamConfiguration.
         * @param policy the discard policy of the StreamConfiguration
         * @return The Builder
         */
        public Builder discardPolicy(DiscardPolicy policy) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the duplicate checking window in the StreamConfiguration.  A Duration.Zero
         * disables duplicate checking.  Duplicate checking is disabled by default.
         * @param window duration to hold message ids for duplicate checking.
         * @return The Builder
         */
        public Builder duplicateWindow(Duration window) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the duplicate checking window in the StreamConfiguration.  A Duration.Zero
         * disables duplicate checking.  Duplicate checking is disabled by default.
         * @param windowMillis duration to hold message ids for duplicate checking.
         * @return The Builder
         */
        public Builder duplicateWindow(long windowMillis) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the placement directive object
         * @param placement the placement directive object
         * @return The Builder
         */
        public Builder placement(Placement placement) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the republish config object
         * @param republish the republish config object
         * @return The Builder
         */
        public Builder republish(Republish republish) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the subjectTransform config object
         * @param subjectTransform the subjectTransform config object
         * @return The Builder
         */
        public Builder subjectTransform(SubjectTransform subjectTransform) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the consumerLimits config object
         * @param consumerLimits the consumerLimits config object
         * @return The Builder
         */
        public Builder consumerLimits(ConsumerLimits consumerLimits) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the mirror  object
         * @param mirror the mirror object
         * @return The Builder
         */
        public Builder mirror(Mirror mirror) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the sources in the StreamConfiguration.
         * @param sources the stream's sources
         * @return The Builder
         */
        public Builder sources(Source... sources) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Add the sources into the StreamConfiguration.
         * @param sources the stream's sources
         * @return The Builder
         */
        public Builder sources(Collection<Source> sources) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Add the sources into the StreamConfiguration.
         * @param sources the stream's sources
         * @return The Builder
         */
        public Builder addSources(Source... sources) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the sources in the StreamConfiguration.
         * @param sources the stream's sources
         * @return The Builder
         */
        public Builder addSources(Collection<Source> sources) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Add a source into the StreamConfiguration.
         * @param source a stream source
         * @return The Builder
         */
        public Builder addSource(Source source) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set whether to seal the stream.
         * INTERNAL USE ONLY. Scoped protected for test purposes.
         * @param sealed the sealed setting
         * @return The Builder
         */
        protected Builder sealed(boolean sealed) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set whether to allow the rollup feature for a stream
         * @param allowRollup the allow rollup setting
         * @return The Builder
         */
        public Builder allowRollup(boolean allowRollup) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set whether to allow direct message access for a stream
         * @param allowDirect the allow direct setting
         * @return The Builder
         */
        public Builder allowDirect(boolean allowDirect) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set whether to allow unified direct access for mirrors
         * @param mirrorDirect the allow direct setting
         * @return The Builder
         */
        public Builder mirrorDirect(boolean mirrorDirect) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set whether to deny deleting messages from the stream
         * @param denyDelete the deny delete setting
         * @return The Builder
         */
        public Builder denyDelete(boolean denyDelete) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set whether to deny purging messages from the stream
         * @param denyPurge the deny purge setting
         * @return The Builder
         */
        public Builder denyPurge(boolean denyPurge) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set whether discard policy new with max message per subject applies to existing subjects, not just new subjects.
         * @param discardNewPerSubject the setting
         * @return The Builder
         */
        public Builder discardNewPerSubject(boolean discardNewPerSubject) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set this stream to be sealed. This is irreversible.
         * @return The Builder
         */
        public Builder seal() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the metadata for the configuration
         * @param metadata the metadata map
         * @return The Builder
         */
        public Builder metadata(Map<String, String> metadata) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the first sequence to be used. 1 is the default. All values less than 2 are treated as 1.
         * @param firstSeq specify the first_seq in the stream config when creating the stream.
         * @return The Builder
         */
        public Builder firstSequence(long firstSeq) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the subject delete marker TTL duration. Server accepts 1 second or more.
         * null has the effect of clearing the subject delete marker TTL
         * @param subjectDeleteMarkerTtl the TTL duration
         * @return The Builder
         */
        public Builder subjectDeleteMarkerTtl(Duration subjectDeleteMarkerTtl) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the subject delete marker TTL duration in milliseconds. Server accepts 1 second or more.
         * 0 or less has the effect of clearing the subject delete marker TTL
         * @param subjectDeleteMarkerTtlMillis the TTL duration in milliseconds
         * @return The Builder
         */
        public Builder subjectDeleteMarkerTtl(long subjectDeleteMarkerTtlMillis) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set allow per message TTL to true
         * @return The Builder
         */
        public Builder allowMessageTtl() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the allow per message TTL flag
         * @param allowMessageTtl the flag
         * @return The Builder
         */
        public Builder allowMessageTtl(boolean allowMessageTtl) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set to allow message Schedules to true
         * @return The Builder
         */
        public Builder allowMessageSchedules() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set allow message Schedules flag
         * @param allowMessageSchedules the flag
         * @return The Builder
         */
        public Builder allowMessageSchedules(boolean allowMessageSchedules) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set allow message counter to true
         * @return The Builder
         */
        public Builder allowMessageCounter() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the allow message counter flag
         * @param allowMessageCounter the flag
         * @return The Builder
         */
        public Builder allowMessageCounter(boolean allowMessageCounter) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set allow atomic publish to true
         * @return The Builder
         */
        public Builder allowAtomicPublish() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set allow atomic publish flag
         * @param allowAtomicPublish the flag
         * @return The Builder
         */
        public Builder allowAtomicPublish(boolean allowAtomicPublish) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set allow batched published flag to true
         * @return The Builder
         */
        public Builder allowBatched() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set allow batched published flag
         * @param allowBatched the flag
         * @return The Builder
         */
        public Builder allowBatched(boolean allowBatched) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the persist mode. Setting null leaves it up to the server
         * @param persistMode the persist mode
         * @return The Builder
         */
        public Builder persistMode(PersistMode persistMode) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Builds the StreamConfiguration
         * @return a stream configuration.
         */
        public StreamConfiguration build() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
