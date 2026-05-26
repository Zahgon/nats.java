// Copyright 2023 The NATS Authors
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

import io.nats.client.Options;
import io.nats.client.ServerPool;
import io.nats.client.support.NatsConstants;
import io.nats.client.support.NatsUri;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public class NatsServerPool implements ServerPool {

    protected final ReentrantLock listLock;

    protected List<ServerPoolEntry> entryList;

    protected Options options;

    protected int maxConnectAttempts;

    protected boolean hasSecureServer;

    protected NatsUri lastConnected;

    protected String defaultScheme;

    public NatsServerPool() {
        listLock = new ReentrantLock();
        // this gets updated occasionally
        entryList = new ArrayList<>();
        // this will get updated when initialize is called
        options = Options.builder().build();
    }

    /**
     * {@inheritDoc}
     */
    public void initialize(@NonNull Options opts) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean acceptDiscoveredUrls(@NonNull List<@NonNull String> discoveredServers) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void afterListChanged() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    @Nullable
    public NatsUri peekNextServer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    @Nullable
    public NatsUri nextServer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // this implementation has been deprecated but implemented for completeness
    @Deprecated
    @Override
    @Nullable
    public List<String> resolveHostToIps(@NonNull String host) {
        return NatsHostResolver.resolveHostToIps(host, false, false);
    }

    @Override
    @Nullable
    public List<String> resolveHostToIps(@NonNull String host, boolean maxOneResult, boolean includeIPV6) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void connectSucceeded(@NonNull NatsUri nuri) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void connectFailed(@NonNull NatsUri nuri) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    @NonNull
    public List<String> getServerList() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean hasSecureServer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected int findEquivalent(List<NatsUri> list, NatsUri toFind) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
