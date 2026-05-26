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

import io.nats.client.Message;
import io.nats.client.NatsSystemClock;
import io.nats.client.PullRequestOptions;
import io.nats.client.SubscribeOptions;
import io.nats.client.support.NatsConstants;
import io.nats.client.support.ScheduledTask;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

abstract class MessageManager {

    public enum ManageResult {

        MESSAGE, STATUS_HANDLED, STATUS_TERMINUS, STATUS_ERROR
    }

    protected static final int THRESHOLD = 3;

    protected final ReentrantLock stateChangeLock;

    protected final NatsConnection conn;

    protected final SubscribeOptions so;

    protected final boolean syncMode;

    // not final it is not set until after construction
    protected NatsJetStreamSubscription sub;

    protected long lastStreamSeq;

    protected long lastConsumerSeq;

    protected final AtomicLong lastMsgReceivedNanoTime;

    // heartbeat stuff
    protected final AtomicBoolean hb;

    protected final AtomicLong idleHeartbeatSettingMillis;

    protected final AtomicLong alarmPeriodSettingNanos;

    protected final AtomicReference<ScheduledTask> heartbeatTaskRef;

    protected MessageManager(NatsConnection conn, SubscribeOptions so, boolean syncMode) {
        stateChangeLock = new ReentrantLock();
        this.conn = conn;
        this.so = so;
        this.syncMode = syncMode;
        lastStreamSeq = 0;
        lastConsumerSeq = 0;
        hb = new AtomicBoolean(false);
        idleHeartbeatSettingMillis = new AtomicLong();
        alarmPeriodSettingNanos = new AtomicLong();
        lastMsgReceivedNanoTime = new AtomicLong(NatsSystemClock.nanoTime());
        heartbeatTaskRef = new AtomicReference<>();
    }

    protected boolean isSyncMode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected long getLastStreamSequence() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected long getLastConsumerSequence() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected long getLastMsgReceivedNanoTime() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected boolean isHb() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected long getIdleHeartbeatSetting() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected long getAlarmPeriodSettingNanos() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void startup(NatsJetStreamSubscription sub) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void shutdown() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void startPullRequest(String pullSubject, PullRequestOptions pullRequestOptions, boolean raiseStatusWarnings, PullManagerObserver pullManagerObserver) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Boolean beforeQueueProcessorImpl(NatsMessage msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    abstract protected ManageResult manage(Message msg);

    protected void trackJsMessage(Message msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void handleHeartbeatError() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void configureIdleHeartbeat(Duration configIdleHeartbeat, long configMessageAlarmTime) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void updateLastMessageReceived() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void initOrResetHeartbeatTimer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void shutdownHeartbeatTimer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
