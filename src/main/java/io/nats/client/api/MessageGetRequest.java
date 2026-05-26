// Copyright 2020 The NATS Authors
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
package io.nats.client.api;

import io.nats.client.support.JsonSerializable;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import java.time.ZonedDateTime;
import static io.nats.client.support.ApiConstants.*;
import static io.nats.client.support.JsonUtils.*;

/**
 * Object used to make a request for message get requests.
 */
public class MessageGetRequest implements JsonSerializable {

    private final long sequence;

    private final String lastBySubject;

    private final String nextBySubject;

    private final ZonedDateTime startTime;

    @NonNull
    public static MessageGetRequest forSequence(long sequence) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @NonNull
    public static MessageGetRequest lastForSubject(String subject) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @NonNull
    public static MessageGetRequest firstForSubject(String subject) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @NonNull
    public static MessageGetRequest firstForStartTime(ZonedDateTime startTime) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @NonNull
    public static MessageGetRequest firstForStartTimeAndSubject(ZonedDateTime startTime, String subject) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @NonNull
    public static MessageGetRequest nextForSubject(long sequence, String subject) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected MessageGetRequest(long sequence, String lastBySubject, String nextBySubject, ZonedDateTime startTime) {
        this.sequence = sequence;
        this.lastBySubject = lastBySubject;
        this.nextBySubject = nextBySubject;
        this.startTime = startTime;
    }

    public long getSequence() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Nullable
    public String getLastBySubject() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Nullable
    public String getNextBySubject() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isSequenceOnly() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isLastBySubject() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isNextBySubject() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Nullable
    public ZonedDateTime getStartTime() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    @NonNull
    public String toJson() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @deprecated use static method forSequence with .serialize instead
     * @param sequence start sequence
     * @return rendered output
     */
    @Deprecated
    public static byte[] seqBytes(long sequence) {
        return forSequence(sequence).serialize();
    }

    /**
     * @deprecated use static method lastForSubject with .serialize instead
     * @param subject filter subject
     * @return rendered output
     */
    @Deprecated
    public static byte[] lastBySubjectBytes(String subject) {
        return lastForSubject(subject).serialize();
    }

    /**
     * @deprecated use static method forSequence instead
     *
     * @param sequence start sequence number
     */
    @Deprecated
    public MessageGetRequest(long sequence) {
        this(sequence, null, null, null);
    }

    /**
     * @deprecated use static method lastForSubject instead
     *
     * @param lastBySubject filter subject
     */
    @Deprecated
    public MessageGetRequest(String lastBySubject) {
        this(-1, lastBySubject, null, null);
    }
}
