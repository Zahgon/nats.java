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
package io.nats.client.impl;

import io.nats.client.JetStreamApiException;
import io.nats.client.JetStreamOptions;
import io.nats.client.Message;
import io.nats.client.api.*;
import io.nats.client.support.NatsJetStreamConstants;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import static io.nats.client.support.NatsConstants.GREATER_THAN;
import static io.nats.client.support.NatsJetStreamClientError.JsConsumerCreate290NotAvailable;
import static io.nats.client.support.NatsJetStreamClientError.JsMultipleFilterSubjects210NotAvailable;
import static io.nats.client.support.NatsJetStreamUtil.generateConsumerName;
import static io.nats.client.support.NatsRequestCompletableFuture.CancelAction;

class NatsJetStreamImpl implements NatsJetStreamConstants {

    // currently the only thing we care about caching is the allowDirect setting
    static class CachedStreamInfo {

        public final boolean allowDirect;

        public CachedStreamInfo(StreamInfo si) {
            allowDirect = si.getConfiguration().getAllowDirect();
        }
    }

    private static final ConcurrentHashMap<String, CachedStreamInfo> CACHED_STREAM_INFO_MAP = new ConcurrentHashMap<>();

    final NatsConnection conn;

    final JetStreamOptions jso;

    final Duration timeout;

    final boolean consumerCreate290Available;

    final boolean multipleSubjectFilter210Available;

    final boolean directBatchGet211Available;

    // ----------------------------------------------------------------------------------------------------
    // Create / Init
    // ----------------------------------------------------------------------------------------------------
    NatsJetStreamImpl(NatsConnection connection, JetStreamOptions jsOptions) {
        conn = connection;
        // Get a working version of JetStream Options...
        // Clone the input jsOptions (JetStreamOptions.builder(...) handles null.
        // If jsOptions is not supplied or the jsOptions request timeout
        // was not set, use the connection options connect timeout.
        timeout = jsOptions == null || jsOptions.getRequestTimeout() == null ? conn.getOptions().getConnectionTimeout() : jsOptions.getRequestTimeout();
        jso = JetStreamOptions.builder(jsOptions).requestTimeout(timeout).build();
        ServerInfo si = conn.getServerInfo();
        consumerCreate290Available = si.isSameOrNewerThanVersion("2.9.0") && !jso.isOptOut290ConsumerCreate();
        multipleSubjectFilter210Available = si.isNewerVersionThan("2.9.99");
        directBatchGet211Available = si.isNewerVersionThan("2.10.99");
    }

    NatsJetStreamImpl(NatsJetStreamImpl impl) {
        conn = impl.conn;
        jso = impl.jso;
        timeout = impl.timeout;
        consumerCreate290Available = impl.consumerCreate290Available;
        multipleSubjectFilter210Available = impl.multipleSubjectFilter210Available;
        directBatchGet211Available = impl.directBatchGet211Available;
    }

    Duration getTimeout() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // ----------------------------------------------------------------------------------------------------
    // Management that is also needed by regular context
    // ----------------------------------------------------------------------------------------------------
    ConsumerInfo _getConsumerInfo(String streamName, String consumerName) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    ConsumerInfo _createConsumer(String streamName, ConsumerConfiguration config, ConsumerCreateRequest.Action action) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void _createConsumerUnsubscribeOnException(String stream, ConsumerConfiguration cc, NatsJetStreamSubscription sub) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    StreamInfo _getStreamInfo(String streamName, StreamInfoOptions options) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    StreamInfo createAndCacheStreamInfoThrowOnError(String streamName, Message resp) throws JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    StreamInfo cacheStreamInfo(String streamName, StreamInfo si) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    List<StreamInfo> cacheStreamInfo(List<StreamInfo> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    List<String> _getStreamNames(String subjectFilter) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // ----------------------------------------------------------------------------------------------------
    // General Utils
    // ----------------------------------------------------------------------------------------------------
    ConsumerConfiguration.Builder consumerConfigurationForOrdered(ConsumerConfiguration initial, long lastStreamSeq, String newDeliverSubject, Long inactiveThreshold) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    ConsumerInfo lookupConsumerInfo(String streamName, String consumerName) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    String lookupStreamBySubject(String subject) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // ----------------------------------------------------------------------------------------------------
    // Request Utils
    // ----------------------------------------------------------------------------------------------------
    Message makeRequestResponseRequired(String subject, byte[] bytes, Duration timeout) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    Message makeInternalRequestResponseRequired(String subject, Headers headers, byte[] data, Duration timeout, CancelAction cancelAction, boolean flushImmediatelyAfterPublish) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    Message responseRequired(Message respMessage) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    String prependPrefix(String subject) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    CachedStreamInfo getCachedStreamInfo(String streamName) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
