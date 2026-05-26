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
package io.nats.service;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.support.DateTimeUtils;
import io.nats.client.support.JsonUtils;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import static io.nats.client.support.ApiConstants.*;
import static io.nats.client.support.JsonUtils.endJson;
import static io.nats.client.support.Validator.nullOrEmpty;

/**
 * The Services Framework introduces a higher-level API for implementing services with NATS.
 * Services automatically contain Ping, Info and Stats responders.
 * Services have one or more service endpoints. {@link ServiceEndpoint}
 * When multiple instances of a service endpoints are active they work in a queue, meaning only one listener responds to any given request.
 */
public class Service {

    /**
     * Constant for the PING service
     */
    public static final String SRV_PING = "PING";

    /**
     * Constant for the INFO service
     */
    public static final String SRV_INFO = "INFO";

    /**
     * Constant for the STATS service
     */
    public static final String SRV_STATS = "STATS";

    /**
     * Constant of the service prefix
     */
    public static final String DEFAULT_SERVICE_PREFIX = "$SRV.";

    private final Connection conn;

    private final Duration drainTimeout;

    private final ConcurrentHashMap<String, EndpointContext> serviceContexts;

    private final List<EndpointContext> discoveryContexts;

    private final List<Dispatcher> dInternals;

    private final AtomicReference<ZonedDateTime> startTimeRef;

    private final CompletableFuture<Boolean> startedFuture;

    private final PingResponse pingResponse;

    private final InfoResponse infoResponse;

    private final ReentrantLock startStopLock;

    private CompletableFuture<Boolean> runningIndicator;

    Service(ServiceBuilder b) {
        String id = new io.nats.client.NUID().next();
        conn = b.conn;
        drainTimeout = b.drainTimeout;
        dInternals = new ArrayList<>();
        startStopLock = new ReentrantLock();
        startTimeRef = new AtomicReference<>(DateTimeUtils.DEFAULT_TIME);
        startedFuture = new CompletableFuture<>();
        // build responses first. info needs to be available when adding service endpoints.
        pingResponse = new PingResponse(id, b.name, b.version, b.metadata);
        infoResponse = new InfoResponse(id, b.name, b.version, b.metadata, b.description);
        // set up the service contexts
        // ? do we need an internal dispatcher for any user endpoints !! addServiceEndpoint deals with it
        serviceContexts = new ConcurrentHashMap<>();
        addServiceEndpoints(b.serviceEndpoints.values());
        Dispatcher dTemp = null;
        if (b.pingDispatcher == null || b.infoDispatcher == null || b.statsDispatcher == null) {
            dTemp = conn.createDispatcher();
            dInternals.add(dTemp);
        }
        discoveryContexts = new ArrayList<>();
        addDiscoveryContexts(SRV_PING, pingResponse, b.pingDispatcher, dTemp);
        addDiscoveryContexts(SRV_INFO, infoResponse, b.infoDispatcher, dTemp);
        addStatsContexts(b.statsDispatcher, dTemp);
    }

    /**
     * Adds one or more service endpoint to the list of service contexts and starts it if the service is running.
     * @param serviceEndpoints one or more service endpoints to be added
     */
    public void addServiceEndpoints(ServiceEndpoint... serviceEndpoints) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Adds all service endpoints to the list of service contexts and starts it if the service is running.
     * @param serviceEndpoints service endpoints to be added
     */
    public void addServiceEndpoints(Collection<ServiceEndpoint> serviceEndpoints) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void _addServiceEndpoints(Collection<ServiceEndpoint> serviceEndpoints) {
        startStopLock.lock();
        try {
            for (ServiceEndpoint se : serviceEndpoints) {
                if (se != null) {
                    // do this first so it's available on start
                    infoResponse.addServiceEndpoint(se);
                    EndpointContext ctx;
                    if (se.getDispatcher() == null) {
                        Dispatcher dTemp = dInternals.isEmpty() ? null : dInternals.get(0);
                        if (dTemp == null) {
                            dTemp = conn.createDispatcher();
                            dInternals.add(dTemp);
                        }
                        ctx = new EndpointContext(conn, dTemp, false, se);
                    } else {
                        ctx = new EndpointContext(conn, null, false, se);
                    }
                    serviceContexts.put(se.getName(), ctx);
                    // if the service is already started, start the newly added context
                    if (runningIndicator != null) {
                        ctx.start();
                    }
                }
            }
        } finally {
            startStopLock.unlock();
        }
    }

    private void addDiscoveryContexts(String discoveryName, Dispatcher dUser, Dispatcher dInternal, ServiceMessageHandler handler) {
        Endpoint[] endpoints = new Endpoint[] { internalEndpoint(discoveryName, null, null), internalEndpoint(discoveryName, pingResponse.getName(), null), internalEndpoint(discoveryName, pingResponse.getName(), pingResponse.getId()) };
        for (Endpoint endpoint : endpoints) {
            discoveryContexts.add(new EndpointContext(conn, dInternal, true, new ServiceEndpoint(endpoint, handler, dUser)));
        }
    }

    /**
     * Adds discovery contexts for the service, reusing the same static bytes at registration.
     * @param discoveryName the name of the discovery
     * @param sr the service response
     * @param dUser the user dispatcher
     * @param dInternal the internal dispatcher
     */
    private void addDiscoveryContexts(String discoveryName, ServiceResponse sr, Dispatcher dUser, Dispatcher dInternal) {
        ServiceMessageHandler handler = smsg -> smsg.respond(conn, sr.serialize());
        addDiscoveryContexts(discoveryName, dUser, dInternal, handler);
    }

    private void addStatsContexts(Dispatcher dUser, Dispatcher dInternal) {
        ServiceMessageHandler handler = smsg -> smsg.respond(conn, getStatsResponse().serialize());
        addDiscoveryContexts(SRV_STATS, dUser, dInternal, handler);
    }

    private Endpoint internalEndpoint(String discoveryName, String optionalServiceNameSegment, String optionalServiceIdSegment) {
        String subject = toDiscoverySubject(discoveryName, optionalServiceNameSegment, optionalServiceIdSegment);
        return new Endpoint(subject, subject, null, null, false);
    }

    static String toDiscoverySubject(String discoveryName, String optionalServiceNameSegment, String optionalServiceIdSegment) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Start the service
     * @return a future that can be held to see if another thread called stop
     */
    public CompletableFuture<Boolean> startService() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get an instance of a ServiceBuilder.
     * @return the instance
     */
    public static ServiceBuilder builder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Stop the service by draining.
     */
    public void stop() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Stop the service by draining. Mark the future that was received from the start method that the service completed exceptionally.
     * @param t the error cause
     */
    public void stop(Throwable t) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Stop the service, optionally draining.
     * @param drain the flag indicating to drain or not
     */
    public void stop(boolean drain) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Stop the service, optionally draining and optionally with an error cause
     * @param drain the flag indicating to drain or not
     * @param t the optional error cause. If supplied, mark the future that was received from the start method that the service completed exceptionally
     */
    public void stop(boolean drain, Throwable t) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Reset the statistics for the endpoints
     */
    public void reset() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the id of the service
     * @return the id
     */
    public String getId() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the name of the service
     * @return the name
     */
    public String getName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the version of the service
     * @return the version
     */
    public String getVersion() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the description of the service
     * @return the description
     */
    public String getDescription() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get whether the service has full started
     * @return true if started
     */
    public boolean isStarted() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get
     * @param timeout the maximum time to wait
     * @param unit the time unit of the timeout argument
     * @return true if started by the timeout
     */
    public boolean isStarted(long timeout, TimeUnit unit) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the drain timeout setting
     * @return the drain timeout setting
     */
    public Duration getDrainTimeout() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the pre-constructed ping response.
     * @return the ping response
     */
    public PingResponse getPingResponse() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the pre-constructed info response.
     * @return the info response
     */
    public InfoResponse getInfoResponse() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the up-to-date stats response which contains a list of all {@link EndpointStats}
     * @return the stats response
     */
    public StatsResponse getStatsResponse() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the up-to-date {@link EndpointStats} for a specific endpoint
     * @param endpointName the endpoint name
     * @return the EndpointStats or null if the name is not found.
     */
    public EndpointStats getEndpointStats(String endpointName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
