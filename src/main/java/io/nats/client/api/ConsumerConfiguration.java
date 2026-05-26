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

import io.nats.client.PullSubscribeOptions;
import io.nats.client.PushSubscribeOptions;
import io.nats.client.support.*;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.*;
import static io.nats.client.support.ApiConstants.*;
import static io.nats.client.support.JsonUtils.beginJson;
import static io.nats.client.support.JsonUtils.endJson;
import static io.nats.client.support.JsonValueUtils.*;
import static io.nats.client.support.NatsJetStreamClientError.JsConsumerNameDurableMismatch;
import static io.nats.client.support.Validator.*;

/**
 * The ConsumerConfiguration class specifies the configuration for creating a JetStream consumer on the client and
 * if necessary the server.
 * Options are created using a ConsumerConfiguration.Builder.
 * <p>ConsumerConfiguration is intended to be used with  {@link io.nats.client.JetStreamManagement#createConsumer(String, ConsumerConfiguration) JetStreamManagement.createConsumer()}.
 * <P> By default this will create a <b>pull consumer</b> unless {@link ConsumerConfiguration.Builder#deliverSubject(String) ConsumerConfiguration.Builder.deliverSubject(String) } is set.
 */
public class ConsumerConfiguration implements JsonSerializable {

    /**
     * The default deliver policy for consumers
     */
    public static final DeliverPolicy DEFAULT_DELIVER_POLICY = DeliverPolicy.All;

    /**
     * The default ack policy for consumers
     */
    public static final AckPolicy DEFAULT_ACK_POLICY = AckPolicy.Explicit;

    /**
     * The default replay policy for consumers
     */
    public static final ReplayPolicy DEFAULT_REPLAY_POLICY = ReplayPolicy.Instant;

    /**
     * The default priority policy for consumers
     */
    public static final PriorityPolicy DEFAULT_PRIORITY_POLICY = PriorityPolicy.None;

    /**
     * The minimum allowed idle heartbeat setting
     */
    public static final Duration MIN_IDLE_HEARTBEAT = Duration.ofMillis(100);

    /**
     * Constant used to unset a Duration setting in the builder
     */
    public static final Duration DURATION_UNSET = Duration.ZERO;

    /**
     * Constant used to unset a Duration setting in the builder
     */
    public static final int INTEGER_UNSET = -1;

    /**
     * Constant used to unset a long setting in the builder
     */
    public static final long LONG_UNSET = -1;

    /**
     * Constant used to unset a long that represents an unsigned long setting in the builder
     */
    public static final long ULONG_UNSET = 0;

    /**
     * Constant used to unset a long setting in the builder
     */
    public static final long DURATION_UNSET_LONG = 0;

    /**
     * Constant used to unset a Duration setting in the builder
     */
    public static final long DURATION_MIN_LONG = 1;

    /**
     * Constant used to as a standard minimum value
     */
    public static final int STANDARD_MIN = 0;

    /**
     * Constant representing the minimum max deliver
     */
    public static final int MAX_DELIVER_MIN = 1;

    /**
     * Constant representing the minimum idle heartbeat in nanos
     */
    public static final long MIN_IDLE_HEARTBEAT_NANOS = MIN_IDLE_HEARTBEAT.toNanos();

    /**
     * Constant representing the minimum idle heartbeat in milliseconds
     */
    public static final long MIN_IDLE_HEARTBEAT_MILLIS = MIN_IDLE_HEARTBEAT.toMillis();

    protected final DeliverPolicy deliverPolicy;

    protected final AckPolicy ackPolicy;

    protected final ReplayPolicy replayPolicy;

    protected final String description;

    protected final String durable;

    protected final String name;

    protected final String deliverSubject;

    protected final String deliverGroup;

    protected final String sampleFrequency;

    protected final ZonedDateTime startTime;

    protected final Duration ackWait;

    protected final Duration idleHeartbeat;

    protected final Duration maxExpires;

    protected final Duration inactiveThreshold;

    // server side this is unsigned
    protected final Long startSeq;

    protected final Integer maxDeliver;

    // server side this is unsigned
    protected final Long rateLimit;

    protected final Integer maxAckPending;

    protected final Integer maxPullWaiting;

    protected final Integer maxBatch;

    protected final Long maxBytes;

    protected final Integer numReplicas;

    protected final ZonedDateTime pauseUntil;

    protected final Boolean flowControl;

    protected final Boolean headersOnly;

    protected final Boolean memStorage;

    protected final List<Duration> backoff;

    protected final Map<String, String> metadata;

    protected final List<String> filterSubjects;

    protected final List<String> priorityGroups;

    protected final PriorityPolicy priorityPolicy;

    protected final Duration priorityTimeout;

    protected ConsumerConfiguration(ConsumerConfiguration cc) {
        this.deliverPolicy = cc.deliverPolicy;
        this.ackPolicy = cc.ackPolicy;
        this.replayPolicy = cc.replayPolicy;
        this.description = cc.description;
        this.durable = cc.durable;
        this.name = cc.name;
        this.deliverSubject = cc.deliverSubject;
        this.deliverGroup = cc.deliverGroup;
        this.sampleFrequency = cc.sampleFrequency;
        this.startTime = cc.startTime;
        this.ackWait = cc.ackWait;
        this.idleHeartbeat = cc.idleHeartbeat;
        this.maxExpires = cc.maxExpires;
        this.inactiveThreshold = cc.inactiveThreshold;
        this.startSeq = cc.startSeq;
        this.maxDeliver = cc.maxDeliver;
        this.rateLimit = cc.rateLimit;
        this.maxAckPending = cc.maxAckPending;
        this.maxPullWaiting = cc.maxPullWaiting;
        this.maxBatch = cc.maxBatch;
        this.maxBytes = cc.maxBytes;
        this.numReplicas = cc.numReplicas;
        this.pauseUntil = cc.pauseUntil;
        this.flowControl = cc.flowControl;
        this.headersOnly = cc.headersOnly;
        this.memStorage = cc.memStorage;
        this.backoff = cc.backoff == null ? null : new ArrayList<>(cc.backoff);
        this.metadata = cc.metadata == null ? null : new HashMap<>(cc.metadata);
        this.filterSubjects = cc.filterSubjects == null ? null : new ArrayList<>(cc.filterSubjects);
        this.priorityGroups = cc.priorityGroups == null ? null : new ArrayList<>(cc.priorityGroups);
        this.priorityPolicy = cc.priorityPolicy;
        this.priorityTimeout = cc.priorityTimeout;
    }

    // For the builder
    protected ConsumerConfiguration(Builder b) {
        this.deliverPolicy = b.deliverPolicy;
        this.ackPolicy = b.ackPolicy;
        this.replayPolicy = b.replayPolicy;
        this.description = b.description;
        this.durable = b.durable;
        this.name = b.name;
        this.startTime = b.startTime;
        this.ackWait = b.ackWait;
        this.sampleFrequency = b.sampleFrequency;
        this.deliverSubject = b.deliverSubject;
        this.deliverGroup = b.deliverGroup;
        this.idleHeartbeat = b.idleHeartbeat;
        this.maxExpires = b.maxExpires;
        this.inactiveThreshold = b.inactiveThreshold;
        this.startSeq = b.startSeq;
        this.maxDeliver = b.maxDeliver;
        this.rateLimit = b.rateLimit;
        this.maxAckPending = b.maxAckPending;
        this.maxPullWaiting = b.maxPullWaiting;
        this.maxBatch = b.maxBatch;
        this.maxBytes = b.maxBytes;
        this.numReplicas = b.numReplicas;
        this.pauseUntil = b.pauseUntil;
        this.flowControl = b.flowControl;
        this.headersOnly = b.headersOnly;
        this.memStorage = b.memStorage;
        this.backoff = b.backoff;
        this.metadata = b.metadata;
        this.filterSubjects = b.filterSubjects;
        this.priorityGroups = b.priorityGroups;
        this.priorityPolicy = b.priorityPolicy;
        this.priorityTimeout = b.priorityTimeout;
    }

    /**
     * Returns a JSON representation of this consumer configuration.
     * @return json consumer configuration json string
     */
    @Override
    @NonNull
    public String toJson() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the name of the description of this consumer configuration.
     * @return name of the description.
     */
    @Nullable
    public String getDescription() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the name of the durable name for this consumer configuration.
     * @return name of the durable.
     */
    @Nullable
    public String getDurable() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the name of the consumer name for this consumer configuration.
     * @return name of the consumer.
     */
    @Nullable
    public String getName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the deliver subject of this consumer configuration.
     * @return the deliver subject.
     */
    @Nullable
    public String getDeliverSubject() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the deliver group of this consumer configuration.
     * @return the deliver group.
     */
    @Nullable
    public String getDeliverGroup() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the deliver policy of this consumer configuration.
     * @return the deliver policy.
     */
    @NonNull
    public DeliverPolicy getDeliverPolicy() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the start sequence of this consumer configuration.
     * @return the start sequence.
     */
    public long getStartSequence() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the start time of this consumer configuration.
     * @return the start time.
     */
    @Nullable
    public ZonedDateTime getStartTime() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the acknowledgment policy of this consumer configuration.
     * @return the acknowledgment policy.
     */
    @NonNull
    public AckPolicy getAckPolicy() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the acknowledgment wait of this consumer configuration.
     * @return the acknowledgment wait duration.
     */
    @Nullable
    public Duration getAckWait() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the max delivery amount of this consumer configuration.
     * @return the max delivery amount.
     */
    public long getMaxDeliver() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the filter subject of this consumer configuration.
     * With the introduction of multiple filter subjects, this method will
     * return null if there are not exactly one filter subjects
     * @return the first filter subject.
     */
    @Nullable
    public String getFilterSubject() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the filter subjects as a list. May be null, otherwise won't be empty
     * @return the list
     */
    @Nullable
    public List<String> getFilterSubjects() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the priority groups as a list. May be null, otherwise won't be empty
     * Needs to be set when PriorityPolicy is specified.
     * For Overflow, Pinned and Prioritized Policies only a single group can be specified.
     * See {@link io.nats.client.api.PriorityPolicy}
     * @return the list
     */
    @Nullable
    public List<String> getPriorityGroups() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Whether there are multiple filter subjects for this consumer configuration.
     * @return true if there are multiple filter subjects
     */
    public boolean hasMultipleFilterSubjects() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the replay policy of this consumer configuration.
     * @return the replay policy.
     */
    @NonNull
    public ReplayPolicy getReplayPolicy() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the rate limit for this consumer configuration.
     * @return the rate limit in bits per second
     */
    public long getRateLimit() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the maximum ack pending configuration.
     * @return maximum ack pending.
     */
    public long getMaxAckPending() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the sample frequency.
     * @return sampleFrequency.
     */
    @Nullable
    public String getSampleFrequency() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the idle heart beat wait time
     * @return the idle heart beat wait duration.
     */
    @Nullable
    public Duration getIdleHeartbeat() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the flow control flag indicating whether it's on or off
     * @return the flow control mode
     */
    public boolean isFlowControl() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the number of pulls that can be outstanding on a pull consumer
     * @return the max pull waiting
     */
    public long getMaxPullWaiting() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the header only flag indicating whether it's on or off
     * @return the flow control mode
     */
    public boolean isHeadersOnly() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the mem storage flag whether it's on or off.
     * @return the mem storage mode
     */
    public boolean isMemStorage() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the max batch size for the server to allow on pull requests.
     * @return the max batch size
     */
    public long getMaxBatch() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the max bytes size for the server to allow on pull requests.
     * @return the max byte size
     */
    public long getMaxBytes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the max amount of expire time for the server to allow on pull requests.
     * @return the max expire
     */
    @Nullable
    public Duration getMaxExpires() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the amount of time before the consumer is deemed inactive.
     * @return the inactive threshold
     */
    @Nullable
    public Duration getInactiveThreshold() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the backoff list; may be empty, will never be null.
     * @return the list
     */
    @NonNull
    public List<Duration> getBackoff() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Metadata for the consumer; may be empty, will never be null.
     * @return the metadata map
     */
    @NonNull
    public Map<String, String> getMetadata() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the number of consumer replicas.
     * @return the replicas count
     */
    public int getNumReplicas() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the time until the consumer is paused.
     * @return paused until time
     */
    @Nullable
    public ZonedDateTime getPauseUntil() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the priority policy of this consumer configuration. Defaults to PriorityPolicy.None
     * When PriorityPolicy is specified a PriorityGroup needs to be set as well. The PriorityGroup will be referenced in the ConsumerOptions for Pull consumers.
     * See {@link io.nats.client.api.PriorityPolicy}
     * @return the priority policy.
     */
    @NonNull
    public PriorityPolicy getPriorityPolicy() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * For pinned_client priority policy how long before the client times out.
     * See {@link io.nats.client.api.PriorityPolicy}
     * @return the duration
     */
    @Nullable
    public Duration getPriorityTimeout() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets whether deliver policy of this consumer configuration was set or left unset
     * @return true if the policy was set, false if the policy was not set
     */
    public boolean deliverPolicyWasSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets whether ack policy for this consumer configuration was set or left unset
     * @return true if the policy was set, false if the policy was not set
     */
    public boolean ackPolicyWasSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets whether replay policy for this consumer configuration was set or left unset
     * @return true if the policy was set, false if the policy was not set
     */
    public boolean replayPolicyWasSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets whether start sequence for this consumer configuration was set or left unset
     * @return true if the start sequence was set by the user
     */
    public boolean startSeqWasSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets whether max deliver for this consumer configuration was set or left unset
     * @return true if max deliver was set by the user
     */
    public boolean maxDeliverWasSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets whether rate limit for this consumer configuration was set or left unset
     * @return true if rate limit was set by the user
     */
    public boolean rateLimitWasSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets whether max ack pending for this consumer configuration was set or left unset
     * @return true if mac ack pending was set by the user
     */
    public boolean maxAckPendingWasSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets whether max pull waiting for this consumer configuration was set or left unset
     * @return true if max pull waiting was set by the user
     */
    public boolean maxPullWaitingWasSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets whether max batch for this consumer configuration was set or left unset
     * @return true if max batch was set by the user
     */
    public boolean maxBatchWasSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets whether max bytes for this consumer configuration was set or left unset
     * @return true if max bytes was set by the user
     */
    public boolean maxBytesWasSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets whether flow control for this consumer configuration was set or left unset
     * @return true if the policy was set, false if the policy was not set
     */
    public boolean flowControlWasSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets whether headers only for this consumer configuration was set or left unset
     * @return true if the policy was set, false if the policy was not set
     */
    public boolean headersOnlyWasSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets whether mem storage for this consumer configuration was set or left unset
     * @return true if the policy was set, false if the policy was not set
     */
    public boolean memStorageWasSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets whether num replicas for this consumer configuration was set or left unset
     * @return true if num replicas was set by the user
     */
    public boolean numReplicasWasSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets whether backoff for this consumer configuration was set or left unset
     * @return true if num backoff was set by the user
     */
    public boolean backoffWasSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets whether metadata for this consumer configuration was set or left unset
     * @return true if num metadata was set by the user
     */
    public boolean metadataWasSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets whether priority policy for this consumer configuration was set or left unset
     * @return true if the policy was set, false if the policy was not set
     */
    public boolean priorityPolicyWasSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets whether priority timeout for this consumer configuration was set or left unset
     * @return true if the timeout was set, false if the timeout was not set
     */
    public boolean priorityTimeoutWasSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates a builder for the options.
     * @return a publish options builder
     */
    public static Builder builder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates a builder for the options.
     * @param cc the consumer configuration
     * @return a publish options builder
     */
    public static Builder builder(ConsumerConfiguration cc) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * ConsumerConfiguration is created using a Builder. The builder supports chaining and will
     * create a default set of options if no methods are calls.
     *
     * <p>{@code new ConsumerConfiguration.Builder().build()} will create a default ConsumerConfiguration.
     */
    public static class Builder {

        private DeliverPolicy deliverPolicy;

        private AckPolicy ackPolicy;

        private ReplayPolicy replayPolicy;

        private String description;

        private String durable;

        private String name;

        private String deliverSubject;

        private String deliverGroup;

        private String sampleFrequency;

        private ZonedDateTime startTime;

        private Duration ackWait;

        private Duration idleHeartbeat;

        private Duration maxExpires;

        private Duration inactiveThreshold;

        private Long startSeq;

        private Integer maxDeliver;

        private Long rateLimit;

        private Integer maxAckPending;

        private Integer maxPullWaiting;

        private Integer maxBatch;

        private Long maxBytes;

        private Integer numReplicas;

        private ZonedDateTime pauseUntil;

        private Boolean flowControl;

        private Boolean headersOnly;

        private Boolean memStorage;

        private List<Duration> backoff;

        private Map<String, String> metadata;

        private List<String> filterSubjects;

        private List<String> priorityGroups;

        private PriorityPolicy priorityPolicy;

        private Duration priorityTimeout;

        /**
         * Construct the builder
         */
        public Builder() {
        }

        /**
         * Construct the builder and initialize values with the existing ConsumerConfiguration
         * @param cc the consumer configuration to clone
         */
        public Builder(ConsumerConfiguration cc) {
            if (cc != null) {
                this.deliverPolicy = cc.deliverPolicy;
                this.ackPolicy = cc.ackPolicy;
                this.replayPolicy = cc.replayPolicy;
                this.description = cc.description;
                this.durable = cc.durable;
                this.name = cc.name;
                this.deliverSubject = cc.deliverSubject;
                this.deliverGroup = cc.deliverGroup;
                this.sampleFrequency = cc.sampleFrequency;
                this.startTime = cc.startTime;
                this.ackWait = cc.ackWait;
                this.idleHeartbeat = cc.idleHeartbeat;
                this.maxExpires = cc.maxExpires;
                this.inactiveThreshold = cc.inactiveThreshold;
                this.startSeq = cc.startSeq;
                this.maxDeliver = cc.maxDeliver;
                this.rateLimit = cc.rateLimit;
                this.maxAckPending = cc.maxAckPending;
                this.maxPullWaiting = cc.maxPullWaiting;
                this.maxBatch = cc.maxBatch;
                this.maxBytes = cc.maxBytes;
                this.numReplicas = cc.numReplicas;
                this.pauseUntil = cc.pauseUntil;
                this.flowControl = cc.flowControl;
                this.headersOnly = cc.headersOnly;
                this.memStorage = cc.memStorage;
                if (cc.backoff != null) {
                    this.backoff = new ArrayList<>(cc.backoff);
                }
                if (cc.metadata != null) {
                    this.metadata = new HashMap<>(cc.metadata);
                }
                if (cc.filterSubjects != null) {
                    this.filterSubjects = new ArrayList<>(cc.filterSubjects);
                }
                if (cc.priorityGroups != null) {
                    this.priorityGroups = new ArrayList<>(cc.priorityGroups);
                }
                this.priorityPolicy = cc.priorityPolicy;
                this.priorityTimeout = cc.priorityTimeout;
            }
        }

        /**
         * Initialize values from the json string.
         * @param json the json string to parse
         * @return the builder
         * @throws JsonParseException if there is a problem parsing the json
         */
        public Builder json(String json) throws JsonParseException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Initialize values from the JsonValue object.
         * @param jsonValue the json value object
         * @return the builder
         */
        public Builder jsonValue(JsonValue jsonValue) {
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
         * Sets the name of the durable consumer.
         * Null or empty clears the field.
         * @param durable name of the durable consumer.
         * @return the builder
         */
        public Builder durable(String durable) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the name of the consumer.
         * Null or empty clears the field.
         * @param name name of the consumer.
         * @return the builder
         */
        public Builder name(String name) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the delivery policy of the ConsumerConfiguration.
         * @param policy the delivery policy.
         * @return Builder
         */
        public Builder deliverPolicy(DeliverPolicy policy) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the subject to deliver messages to.
         * <p> By setting the deliverySubject this configuration will create a <b>push consumer</b>. When left empty or set to NULL a pull consumer will be created.
         * @param subject the subject.
         * @return the builder
         */
        public Builder deliverSubject(String subject) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the group to deliver messages to.
         * @param group the delivery group.
         * @return the builder
         */
        public Builder deliverGroup(String group) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the start sequence of the ConsumerConfiguration or null to unset / clear.
         * @param sequence the start sequence
         * @return Builder
         */
        public Builder startSequence(Long sequence) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the start sequence of the ConsumerConfiguration.
         * @param sequence the start sequence
         * @return Builder
         */
        public Builder startSequence(long sequence) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the start time of the ConsumerConfiguration.
         * @param startTime the start time
         * @return Builder
         */
        public Builder startTime(ZonedDateTime startTime) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the acknowledgement policy of the ConsumerConfiguration.
         * @param policy the acknowledgement policy.
         * @return Builder
         */
        public Builder ackPolicy(AckPolicy policy) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the acknowledgement wait duration of the ConsumerConfiguration.
         * @param timeout the wait timeout
         * @return Builder
         */
        public Builder ackWait(Duration timeout) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the acknowledgement wait duration of the ConsumerConfiguration.
         * @param timeoutMillis the wait timeout in milliseconds
         * @return Builder
         */
        public Builder ackWait(long timeoutMillis) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the maximum delivery amount of the ConsumerConfiguration or null to unset / clear.
         * @param maxDeliver the maximum delivery amount
         * @return Builder
         */
        public Builder maxDeliver(Long maxDeliver) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the maximum delivery amount of the ConsumerConfiguration.
         * @param maxDeliver the maximum delivery amount
         * @return Builder
         */
        public Builder maxDeliver(long maxDeliver) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the filter subject of the ConsumerConfiguration.
         * Replaces any other filter subjects set in the builder
         * @param filterSubject the filter subject
         * @return Builder
         */
        public Builder filterSubject(String filterSubject) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the filter subjects of the ConsumerConfiguration.
         * Replaces any other filter subjects set in the builder
         * @param filterSubjects one or more filter subjects
         * @return Builder
         */
        public Builder filterSubjects(String... filterSubjects) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the filter subjects of the ConsumerConfiguration.
         * Replaces any other filter subjects set in the builder
         * @param filterSubjects the list of filter subjects
         * @return Builder
         */
        public Builder filterSubjects(List<String> filterSubjects) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private Builder _filterSubjects(@NonNull List<String> filterSubjects) {
            this.filterSubjects = new ArrayList<>();
            for (String fs : filterSubjects) {
                if (!nullOrEmpty(fs)) {
                    this.filterSubjects.add(fs);
                }
            }
            if (this.filterSubjects.isEmpty()) {
                this.filterSubjects = null;
            }
            return this;
        }

        /**
         * Sets the replay policy of the ConsumerConfiguration.
         * @param policy the replay policy.
         * @return Builder
         */
        public Builder replayPolicy(ReplayPolicy policy) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the sample frequency of the ConsumerConfiguration.
         * @param frequency the frequency
         * @return Builder
         */
        public Builder sampleFrequency(String frequency) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the rate limit of the ConsumerConfiguration or null to unset / clear.
         * @param bitsPerSecond bits per second to deliver
         * @return Builder
         */
        public Builder rateLimit(Long bitsPerSecond) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the rate limit of the ConsumerConfiguration.
         * @param bitsPerSecond bits per second to deliver
         * @return Builder
         */
        public Builder rateLimit(long bitsPerSecond) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the maximum ack pending or null to unset / clear.
         * @param maxAckPending maximum pending acknowledgements.
         * @return Builder
         */
        public Builder maxAckPending(Long maxAckPending) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the maximum ack pending.
         * @param maxAckPending maximum pending acknowledgements.
         * @return Builder
         */
        public Builder maxAckPending(long maxAckPending) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * sets the idle heart beat wait time
         * @param idleHeartbeat the idle heart beat duration
         * @return Builder
         */
        public Builder idleHeartbeat(Duration idleHeartbeat) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * sets the idle heart beat wait time
         * @param idleHeartbeatMillis the idle heart beat duration in milliseconds
         * @return Builder
         */
        public Builder idleHeartbeat(long idleHeartbeatMillis) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * set the flow control on and set the idle heartbeat
         * @param idleHeartbeat the idle heart beat duration
         * @return Builder
         */
        public Builder flowControl(Duration idleHeartbeat) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * set the flow control on and set the idle heartbeat
         * @param idleHeartbeatMillis the idle heart beat duration in milliseconds
         * @return Builder
         */
        public Builder flowControl(long idleHeartbeatMillis) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * sets the max amount of expire time for the server to allow on pull requests.
         * @param maxExpires the max expire duration
         * @return Builder
         */
        public Builder maxExpires(Duration maxExpires) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * sets the max amount of expire time for the server to allow on pull requests.
         * @param maxExpires the max expire duration in milliseconds
         * @return Builder
         */
        public Builder maxExpires(long maxExpires) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * sets the amount of time before the consumer is deemed inactive.
         * @param inactiveThreshold the threshold duration
         * @return Builder
         */
        public Builder inactiveThreshold(Duration inactiveThreshold) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * sets the amount of time before the consumer is deemed inactive.
         * @param inactiveThreshold the threshold duration in milliseconds
         * @return Builder
         */
        public Builder inactiveThreshold(long inactiveThreshold) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * sets the max pull waiting, the number of pulls that can be outstanding on a pull consumer, pulls received after this is reached are ignored.
         * Use null to unset / clear.
         * @param maxPullWaiting the max pull waiting
         * @return Builder
         */
        public Builder maxPullWaiting(Long maxPullWaiting) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * sets the max pull waiting, the number of pulls that can be outstanding on a pull consumer, pulls received after this is reached are ignored.
         * @param maxPullWaiting the max pull waiting
         * @return Builder
         */
        public Builder maxPullWaiting(long maxPullWaiting) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * sets the max batch size for the server to allow on pull requests.
         * @param maxBatch the max batch size
         * @return Builder
         */
        public Builder maxBatch(Long maxBatch) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * sets the max batch size for the server to allow on pull requests.
         * @param maxBatch the max batch size
         * @return Builder
         */
        public Builder maxBatch(long maxBatch) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * sets the max bytes size for the server to allow on pull requests.
         * @param maxBytes the max bytes size
         * @return Builder
         */
        public Builder maxBytes(Long maxBytes) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * sets the max bytes size for the server to allow on pull requests.
         * @param maxBytes the max bytes size
         * @return Builder
         */
        public Builder maxBytes(long maxBytes) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * set the number of replicas for the consumer. When set do not inherit the
         * replica count from the stream but specifically set it to this amount.
         * @param numReplicas number of replicas for the consumer
         * @return Builder
         */
        public Builder numReplicas(Integer numReplicas) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the time to pause the consumer until.
         * @param pauseUntil the time to pause
         * @return Builder
         */
        public Builder pauseUntil(ZonedDateTime pauseUntil) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * set the headers only flag saying to deliver only the headers of
         * messages in the stream and not the bodies
         * @param headersOnly the flag
         * @return Builder
         */
        public Builder headersOnly(Boolean headersOnly) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * set the mem storage flag to force the consumer state to be kept
         * in memory rather than inherit the setting from the stream
         * @param memStorage the flag
         * @return Builder
         */
        public Builder memStorage(Boolean memStorage) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the list of backoff. Will override ackwait setting.
         * @see <a href="https://docs.nats.io/using-nats/developer/develop_jetstream/consumers#delivery-reliability">Delivery Reliability</a>
         * @param backoffs zero or more backoff durations or an array of backoffs
         * @return Builder
         */
        public Builder backoff(Duration... backoffs) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the list of backoff. Will override ackwait setting.
         * @see <a href="https://docs.nats.io/using-nats/developer/develop_jetstream/consumers#delivery-reliability">Delivery Reliability</a>
         * @param backoffsMillis zero or more backoff in millis or an array of backoffsMillis
         * @return Builder
         */
        public Builder backoff(long... backoffsMillis) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the metadata for the configuration
         * @param metadata the metadata map
         * @return Builder
         */
        public Builder metadata(Map<String, String> metadata) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the priority groups of the ConsumerConfiguration.
         * Replaces any other priority groups set in the builder
         * @param priorityGroups one or more priority groups
         * @return Builder
         */
        public Builder priorityGroups(String... priorityGroups) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the priority groups of the ConsumerConfiguration.
         * Replaces any other priority groups set in the builder
         * @param priorityGroups the list of priority groups
         * @return Builder
         */
        public Builder priorityGroups(List<String> priorityGroups) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private Builder _priorityGroups(@NonNull List<String> priorityGroups) {
            this.priorityGroups = new ArrayList<>();
            for (String pg : priorityGroups) {
                if (!nullOrEmpty(pg)) {
                    this.priorityGroups.add(pg);
                }
            }
            if (this.priorityGroups.isEmpty()) {
                this.priorityGroups = null;
            }
            return this;
        }

        /**
         * Sets the priority policy of the ConsumerConfiguration.
         * @param policy the priority policy.
         * @return Builder
         */
        public Builder priorityPolicy(PriorityPolicy policy) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the priority policy timeout
         * @param priorityTimeout the timeout
         * @return Builder
         */
        public Builder priorityTimeout(Duration priorityTimeout) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the priority policy timeout
         * @param priorityTimeoutMillis the timeout in milliseconds
         * @return Builder
         */
        public Builder priorityTimeout(long priorityTimeoutMillis) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Builds the ConsumerConfiguration
         * @return The consumer configuration.
         */
        public ConsumerConfiguration build() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Builds the PushSubscribeOptions with this configuration
         * @return The PushSubscribeOptions.
         */
        public PushSubscribeOptions buildPushSubscribeOptions() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Builds the PushSubscribeOptions with this configuration.
         * Providing the stream is a hint for the subscription process that
         * saves a call to the server. Assumes the stream is the correct stream
         * for the subject filter, otherwise the server will return an error
         * which the subscription call will raise to the user.
         * @param stream the stream for this consumer
         * @return The PushSubscribeOptions.
         */
        public PushSubscribeOptions buildPushSubscribeOptions(String stream) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Builds the PullSubscribeOptions with this configuration
         * @return The PullSubscribeOptions.
         */
        public PullSubscribeOptions buildPullSubscribeOptions() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Builds the PullSubscribeOptions with this configuration
         * Providing the stream is a hint for the subscription process that
         * saves a call to the server. Assumes the stream is the correct stream
         * for the subject filter, otherwise the server will return an error
         * which the subscription call will raise to the user.
         * @param stream the stream for this consumer
         * @return The PullSubscribeOptions.
         */
        public PullSubscribeOptions buildPullSubscribeOptions(String stream) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected static int getOrUnset(Integer val) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected static long getOrUnset(Long val) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected static long getOrUnsetUlong(Long val) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected static Duration getOrUnset(Duration val) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected static Integer normalize(Long l, int min) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected static Long normalizeLong(Long l) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected static Long normalizeUlong(Long u) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected static Duration normalize(Duration d) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected static Duration normalizeDuration(long millis) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected static DeliverPolicy GetOrDefault(DeliverPolicy p) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected static AckPolicy GetOrDefault(AckPolicy p) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected static ReplayPolicy GetOrDefault(ReplayPolicy p) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected static PriorityPolicy GetOrDefault(PriorityPolicy p) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Not used
     */
    @Deprecated
    public static final Duration DURATION_MIN = Duration.ofNanos(1);
}
