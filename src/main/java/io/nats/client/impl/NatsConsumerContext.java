// Copyright 2020-2023 The NATS Authors
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
package io.nats.client.impl;

import io.nats.client.*;
import io.nats.client.api.ConsumerConfiguration;
import io.nats.client.api.ConsumerInfo;
import io.nats.client.api.OrderedConsumerConfiguration;
import io.nats.client.api.PriorityPolicy;
import io.nats.client.support.Validator;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import static io.nats.client.BaseConsumeOptions.DEFAULT_EXPIRES_IN_MILLIS;
import static io.nats.client.BaseConsumeOptions.MIN_EXPIRES_MILLS;
import static io.nats.client.ConsumeOptions.DEFAULT_CONSUME_OPTIONS;

/**
 * Implementation of Consumer Context
 */
public class NatsConsumerContext implements ConsumerContext, SimplifiedSubscriptionMaker {

    private final ReentrantLock stateLock;

    private final NatsStreamContext streamCtx;

    private final boolean ordered;

    private final ConsumerConfiguration initialOrderedConsumerConfig;

    private final PullSubscribeOptions unorderedBindPso;

    private final AtomicReference<ConsumerInfo> cachedConsumerInfo;

    private final AtomicReference<String> consumerName;

    private final AtomicLong highestSeq;

    private final AtomicReference<Dispatcher> defaultDispatcher;

    private final AtomicReference<NatsMessageConsumerBase> lastConsumer;

    NatsConsumerContext(@NonNull NatsStreamContext sc, @Nullable ConsumerInfo unorderedConsumerInfo, @Nullable OrderedConsumerConfiguration occ) {
        stateLock = new ReentrantLock();
        streamCtx = sc;
        cachedConsumerInfo = new AtomicReference<>();
        consumerName = new AtomicReference<>();
        highestSeq = new AtomicLong();
        defaultDispatcher = new AtomicReference<>();
        lastConsumer = new AtomicReference<>();
        if (unorderedConsumerInfo != null) {
            ordered = false;
            initialOrderedConsumerConfig = null;
            cachedConsumerInfo.set(unorderedConsumerInfo);
            consumerName.set(unorderedConsumerInfo.getName());
            unorderedBindPso = PullSubscribeOptions.fastBind(sc.streamName, unorderedConsumerInfo.getName());
        } else if (occ != null) {
            ordered = true;
            initialOrderedConsumerConfig = ConsumerConfiguration.builder().name(occ.getConsumerNamePrefix()).filterSubjects(occ.getFilterSubjects()).deliverPolicy(occ.getDeliverPolicy()).startSequence(occ.getStartSequence()).startTime(occ.getStartTime()).replayPolicy(occ.getReplayPolicy()).headersOnly(occ.getHeadersOnly()).build();
            unorderedBindPso = null;
        } else {
            throw new IllegalArgumentException("Internal Error, must be ordered or unordered.");
        }
    }

    static class OrderedPullSubscribeOptionsBuilder extends PullSubscribeOptions.Builder {

        OrderedPullSubscribeOptionsBuilder(String streamName, ConsumerConfiguration cc) {
            stream(streamName);
            configuration(cc);
            ordered = true;
        }
    }

    @Override
    public NatsJetStreamPullSubscription subscribe(@Nullable MessageHandler messageHandler, @Nullable Dispatcher userDispatcher, @SuppressWarnings("ClassEscapesDefinedScope") @Nullable PullMessageManager optionalPmm, @Nullable Long optionalInactiveThreshold) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void checkState() throws IOException {
        NatsMessageConsumerBase lastCon = lastConsumer.get();
        if (lastCon != null && ordered && !lastCon.finished.get()) {
            throw new IOException("The ordered consumer is already receiving messages. Ordered Consumer does not allow multiple instances at time.");
        }
    }

    private void checkNotPinned(String label) throws IOException {
        ConsumerInfo ci = cachedConsumerInfo.get();
        if (ci != null && ci.getConsumerConfiguration().getPriorityPolicy() == PriorityPolicy.PinnedClient) {
            throw new IOException("Pinned not allowed with " + label);
        }
    }

    private NatsMessageConsumerBase trackConsume(NatsMessageConsumerBase con) {
        lastConsumer.set(con);
        return con;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getConsumerName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public ConsumerInfo getConsumerInfo() throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public ConsumerInfo getCachedConsumerInfo() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public Message next() throws IOException, InterruptedException, JetStreamStatusCheckedException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public Message next(@Nullable Duration maxWait) throws IOException, InterruptedException, JetStreamStatusCheckedException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public Message next(long maxWaitMillis) throws IOException, InterruptedException, JetStreamStatusCheckedException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public FetchConsumer fetchMessages(int maxMessages) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public FetchConsumer fetchBytes(int maxBytes) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public FetchConsumer fetch(@NonNull FetchConsumeOptions fetchConsumeOptions) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public IterableConsumer iterate() throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public IterableConsumer iterate(@NonNull ConsumeOptions consumeOptions) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public MessageConsumer consume(@NonNull MessageHandler handler) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public MessageConsumer consume(@Nullable Dispatcher dispatcher, @NonNull MessageHandler handler) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public MessageConsumer consume(@NonNull ConsumeOptions consumeOptions, @NonNull MessageHandler handler) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public MessageConsumer consume(@NonNull ConsumeOptions consumeOptions, @Nullable Dispatcher userDispatcher, @NonNull MessageHandler handler) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean unpin(String group) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
