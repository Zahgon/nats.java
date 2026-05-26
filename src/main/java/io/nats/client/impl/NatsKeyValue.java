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

import io.nats.client.*;
import io.nats.client.api.*;
import io.nats.client.support.DateTimeUtils;
import io.nats.client.support.Validator;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import static io.nats.client.support.NatsConstants.DOT;
import static io.nats.client.support.NatsConstants.GREATER_THAN;
import static io.nats.client.support.NatsJetStreamConstants.JS_SEQUENCE_TEMPORARILY_UNKNOWN;
import static io.nats.client.support.NatsJetStreamConstants.JS_WRONG_LAST_SEQUENCE;
import static io.nats.client.support.NatsKeyValueUtil.*;
import static io.nats.client.support.Validator.*;

public class NatsKeyValue extends NatsFeatureBase implements KeyValue {

    private final String bucketName;

    private final String streamSubject;

    private final String readPrefix;

    private final String writePrefix;

    NatsKeyValue(String bucketName, NatsConnection connection, KeyValueOptions kvo, NatsJetStreamManagement jsm) throws IOException {
        super(connection, kvo, jsm);
        this.bucketName = Validator.validateBucketName(bucketName, true);
        streamName = toStreamName(bucketName);
        StreamInfo si;
        try {
            si = this.jsm.getStreamInfo(streamName);
        } catch (JetStreamApiException e) {
            // can't throw directly, that would be a breaking change
            throw new IOException(e);
        }
        streamSubject = toStreamSubject(bucketName);
        String readTemp = toKeyPrefix(bucketName);
        String writeTemp;
        Mirror m = si.getConfiguration().getMirror();
        if (m != null) {
            String bName = trimPrefix(m.getName());
            String mExtApi = m.getExternal() == null ? null : m.getExternal().getApi();
            if (mExtApi == null) {
                writeTemp = toKeyPrefix(bName);
            } else {
                readTemp = toKeyPrefix(bName);
                writeTemp = mExtApi + DOT + toKeyPrefix(bName);
            }
        } else if (kvo == null || kvo.getJetStreamOptions().isDefaultPrefix()) {
            writeTemp = readTemp;
        } else {
            writeTemp = kvo.getJetStreamOptions().getPrefix() + readTemp;
        }
        readPrefix = readTemp;
        writePrefix = writeTemp;
    }

    String readSubject(String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    String writeSubject(String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBucketName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KeyValueEntry get(String key) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KeyValueEntry get(String key, long revision) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    KeyValueEntry existingOnly(KeyValueEntry kve) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    KeyValueEntry _get(String key) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    KeyValueEntry _get(String key, long revision) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long put(String key, byte[] value) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long put(String key, String value) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long put(String key, Number value) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long create(String key, byte[] value) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public long create(String key, byte[] value, MessageTtl messageTtl) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long update(String key, byte[] value, long expectedRevision) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private long _update(String key, byte[] value, long expectedRevision, MessageTtl messageTtl) throws IOException, JetStreamApiException {
        return _write(key, value, null, getPublishOptions(expectedRevision, messageTtl)).getSeqno();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long update(String key, String value, long expectedRevision) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(String key) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(String key, long expectedRevision) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void purge(String key) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void purge(String key, long expectedRevision) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void purge(String key, MessageTtl messageTtl) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void purge(String key, long expectedRevision, MessageTtl messageTtl) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private PublishAck _write(String key, byte[] data, Headers h, PublishOptions popts) throws IOException, JetStreamApiException {
        validateNonWildcardKvKeyRequired(key);
        return js.publish(NatsMessage.builder().subject(writeSubject(key)).data(data).headers(h).build(), popts);
    }

    @Override
    public NatsKeyValueWatchSubscription watch(String key, KeyValueWatcher watcher, KeyValueWatchOption... watchOptions) throws IOException, JetStreamApiException, InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public NatsKeyValueWatchSubscription watch(String key, KeyValueWatcher watcher, long fromRevision, KeyValueWatchOption... watchOptions) throws IOException, JetStreamApiException, InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public NatsKeyValueWatchSubscription watch(List<String> keys, KeyValueWatcher watcher, KeyValueWatchOption... watchOptions) throws IOException, JetStreamApiException, InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public NatsKeyValueWatchSubscription watch(List<String> keys, KeyValueWatcher watcher, long fromRevision, KeyValueWatchOption... watchOptions) throws IOException, JetStreamApiException, InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public NatsKeyValueWatchSubscription watchAll(KeyValueWatcher watcher, KeyValueWatchOption... watchOptions) throws IOException, JetStreamApiException, InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public NatsKeyValueWatchSubscription watchAll(KeyValueWatcher watcher, long fromRevision, KeyValueWatchOption... watchOptions) throws IOException, JetStreamApiException, InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> keys() throws IOException, JetStreamApiException, InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<String> keys(String filter) throws IOException, JetStreamApiException, InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<String> keys(List<String> filters) throws IOException, JetStreamApiException, InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private List<String> _keys(List<String> readSubjectFilters) throws IOException, JetStreamApiException, InterruptedException {
        List<String> list = new ArrayList<>();
        visitSubject(readSubjectFilters, DeliverPolicy.LastPerSubject, true, false, m -> {
            KeyValueOperation op = getOperation(m.getHeaders());
            if (op == KeyValueOperation.PUT) {
                list.add(new BucketAndKey(m).key);
            }
        });
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LinkedBlockingQueue<KeyResult> consumeKeys() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LinkedBlockingQueue<KeyResult> consumeKeys(String filter) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LinkedBlockingQueue<KeyResult> consumeKeys(List<String> filters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private LinkedBlockingQueue<KeyResult> _consumeKeys(List<String> readSubjectFilters) {
        LinkedBlockingQueue<KeyResult> q = new LinkedBlockingQueue<>();
        js.conn.getOptions().getExecutor().submit(() -> {
            try {
                visitSubject(readSubjectFilters, DeliverPolicy.LastPerSubject, true, false, m -> {
                    KeyValueOperation op = getOperation(m.getHeaders());
                    if (op == KeyValueOperation.PUT) {
                        q.offer(new KeyResult(new BucketAndKey(m).key));
                    }
                });
                q.offer(new KeyResult());
            } catch (IOException | JetStreamApiException e) {
                q.offer(new KeyResult(e));
            } catch (InterruptedException e) {
                q.offer(new KeyResult(e));
                Thread.currentThread().interrupt();
            }
        });
        return q;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<KeyValueEntry> history(String key) throws IOException, JetStreamApiException, InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void purgeDeletes() throws IOException, JetStreamApiException, InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void purgeDeletes(KeyValuePurgeOptions options) throws IOException, JetStreamApiException, InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KeyValueStatus getStatus() throws IOException, JetStreamApiException, InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
