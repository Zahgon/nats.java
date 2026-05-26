package io.nats.service;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Message;
import io.nats.client.NatsSystemClock;
import io.nats.client.support.DateTimeUtils;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Internal class to support service implementation
 */
class EndpointContext {

    private final Connection conn;

    private final ServiceEndpoint se;

    private final ServiceMessageHandler handler;

    private final boolean recordStats;

    private final String qGroup;

    private boolean running;

    private final boolean internalDispatcher;

    private final Dispatcher dispatcher;

    private ZonedDateTime started;

    private String lastError;

    private final AtomicLong numRequests;

    private final AtomicLong numErrors;

    private final AtomicLong processingTime;

    EndpointContext(Connection conn, Dispatcher internalDispatcher, boolean internalEndpoint, ServiceEndpoint se) {
        this.conn = conn;
        this.se = se;
        handler = se.getHandler();
        this.recordStats = !internalEndpoint;
        qGroup = internalEndpoint ? null : se.getQueueGroup();
        running = false;
        if (se.getDispatcher() == null) {
            dispatcher = internalDispatcher;
            this.internalDispatcher = true;
        } else {
            dispatcher = se.getDispatcher();
            this.internalDispatcher = false;
        }
        numRequests = new AtomicLong();
        numErrors = new AtomicLong();
        processingTime = new AtomicLong();
        started = DateTimeUtils.gmtNow();
    }

    // this method does not need a lock because it is only
    // called from Service start which is already locked
    void start() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void onMessage(Message msg) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    EndpointStats getEndpointStats() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void reset() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean isNotInternalDispatcher() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    CompletableFuture<Boolean> drain(Duration timeout) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
