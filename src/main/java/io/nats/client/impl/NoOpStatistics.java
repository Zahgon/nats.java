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

import io.nats.client.StatisticsCollector;

public class NoOpStatistics implements StatisticsCollector {

    @Override
    public void setAdvancedTracking(boolean trackAdvanced) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void incrementPingCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void incrementReconnects() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void incrementDroppedCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void incrementOkCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void incrementErrCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void incrementExceptionCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void incrementRequestsSent() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void incrementRepliesReceived() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void incrementDuplicateRepliesReceived() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void incrementOrphanRepliesReceived() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void incrementIn(long bytes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void incrementOut(long bytes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void incrementInMsgs() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void incrementOutMsgs() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void incrementInBytes(long bytes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void incrementOutBytes(long bytes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void incrementFlushCounter() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void incrementOutstandingRequests() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void decrementOutstandingRequests() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void registerRead(long bytes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void registerWrite(long bytes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public long getPings() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public long getReconnects() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public long getDroppedCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public long getOKs() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public long getErrs() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public long getExceptions() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public long getRequestsSent() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public long getRepliesReceived() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public long getDuplicateRepliesReceived() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public long getOrphanRepliesReceived() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public long getInMsgs() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public long getOutMsgs() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public long getInBytes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public long getOutBytes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public long getFlushCounter() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public long getOutstandingRequests() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
