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
import io.nats.client.KeyValueManagement;
import io.nats.client.KeyValueOptions;
import io.nats.client.api.KeyValueConfiguration;
import io.nats.client.api.KeyValueStatus;
import io.nats.client.api.StreamConfiguration;
import io.nats.client.support.Validator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static io.nats.client.support.NatsKeyValueUtil.*;

public class NatsKeyValueManagement implements KeyValueManagement {

    private final NatsJetStreamManagement jsm;

    private final boolean serverOlderThan272;

    NatsKeyValueManagement(NatsConnection connection, KeyValueOptions kvo, NatsJetStreamManagement jsm) throws IOException {
        if (jsm == null) {
            this.jsm = new NatsJetStreamManagement(connection, kvo == null ? null : kvo.getJetStreamOptions());
        } else {
            this.jsm = jsm;
        }
        serverOlderThan272 = this.jsm.conn.getServerInfo().isOlderThanVersion("2.7.2");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KeyValueStatus create(KeyValueConfiguration config) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KeyValueStatus update(KeyValueConfiguration config) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getBucketNames() throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KeyValueStatus getBucketInfo(String bucketName) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KeyValueStatus getStatus(String bucketName) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<KeyValueStatus> getStatuses() throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(String bucketName) throws IOException, JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
