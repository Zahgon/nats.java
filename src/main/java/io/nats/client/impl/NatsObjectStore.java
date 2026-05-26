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
package io.nats.client.impl;

import io.nats.client.*;
import io.nats.client.api.*;
import io.nats.client.support.DateTimeUtils;
import io.nats.client.support.Digester;
import io.nats.client.support.Validator;
import java.io.*;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static io.nats.client.support.NatsConstants.GREATER_THAN;
import static io.nats.client.support.NatsJetStreamClientError.*;
import static io.nats.client.support.NatsObjectStoreUtil.*;

public class NatsObjectStore extends NatsFeatureBase implements ObjectStore {

    private final ObjectStoreOptions oso;

    private final String bucketName;

    private final String rawChunkPrefix;

    private final String rawMetaPrefix;

    NatsObjectStore(String bucketName, NatsConnection connection, ObjectStoreOptions oso, NatsJetStreamManagement jsm) throws IOException {
        super(connection, oso, jsm);
        this.oso = oso;
        this.bucketName = Validator.validateBucketName(bucketName, true);
        streamName = toStreamName(bucketName);
        rawChunkPrefix = toChunkPrefix(bucketName);
        rawMetaPrefix = toMetaPrefix(bucketName);
    }

    String rawChunkSubject(String nuid) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    String rawMetaSubject(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    String rawAllMetaSubject() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBucketName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private ObjectInfo publishMeta(ObjectInfo info) throws IOException, JetStreamApiException {
        js.publish(NatsMessage.builder().subject(rawMetaSubject(info.getObjectName())).headers(getMetaHeaders()).data(info.serialize()).build());
        return ObjectInfo.builder(info).modified(DateTimeUtils.gmtNow()).build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectInfo put(ObjectMeta meta, InputStream inputStream) throws IOException, JetStreamApiException, NoSuchAlgorithmException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectInfo put(String objectName, InputStream inputStream) throws IOException, JetStreamApiException, NoSuchAlgorithmException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectInfo put(String objectName, byte[] input) throws IOException, JetStreamApiException, NoSuchAlgorithmException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectInfo put(File file) throws IOException, JetStreamApiException, NoSuchAlgorithmException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectInfo get(String objectName, OutputStream out) throws IOException, JetStreamApiException, InterruptedException, NoSuchAlgorithmException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectInfo getInfo(String objectName) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectInfo getInfo(String objectName, boolean includingDeleted) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectInfo updateMeta(String objectName, ObjectMeta meta) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectInfo delete(String objectName) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectInfo addLink(String objectName, ObjectInfo toInfo) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectInfo addBucketLink(String objectName, ObjectStore toStore) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * @return returs the new status info of the object store
     */
    @Override
    public ObjectStoreStatus seal() throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ObjectInfo> getList() throws IOException, JetStreamApiException, InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public NatsObjectStoreWatchSubscription watch(ObjectStoreWatcher watcher, ObjectStoreWatchOption... watchOptions) throws IOException, JetStreamApiException, InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectStoreStatus getStatus() throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
