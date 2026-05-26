// Copyright 2015-2018 The NATS Authors
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

import io.nats.client.ForceReconnectOptions;
import io.nats.client.NatsSystemClock;
import io.nats.client.Options;
import io.nats.client.support.NatsUri;
import io.nats.client.support.ScheduledTask;
import org.jspecify.annotations.NonNull;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * This class is not thread-safe.  Caller must ensure thread safety.
 */
// NatsConnection
@SuppressWarnings("ClassEscapesDefinedScope")
public class SocketDataPortWithWriteTimeout extends SocketDataPort {

    private long writeTimeoutNanos;

    private long delayPeriodNanos;

    private ScheduledTask writeWatchTask;

    private final AtomicLong writeMustBeDoneBy;

    public SocketDataPortWithWriteTimeout() {
        writeMustBeDoneBy = new AtomicLong(Long.MAX_VALUE);
    }

    @Override
    public void afterConstruct(@NonNull Options options) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void connect(@NonNull NatsConnection conn, @NonNull NatsUri nuri, long timeoutNanos) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void write(byte[] src, int toWrite) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void close() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
