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
package io.nats.client.support;

import java.time.Duration;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Internal json parsing helpers.
 */
public abstract class DateTimeUtils {

    private DateTimeUtils() {
    }

    /* ensures cannot be constructed */
    public static final ZoneId ZONE_ID_GMT = ZoneId.of("GMT");

    public static final ZonedDateTime DEFAULT_TIME = ZonedDateTime.of(1, 1, 1, 0, 0, 0, 0, ZONE_ID_GMT);

    public static final DateTimeFormatter RFC3339_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.nnnnnnnnn'Z'");

    private static final long NANO_FACTOR = 1_000_000_000;

    public static ZonedDateTime toGmt(ZonedDateTime zonedDateTime) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static ZonedDateTime gmtNow() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean equals(ZonedDateTime zdt1, ZonedDateTime zdt2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String toRfc3339(ZonedDateTime zonedDateTime) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Parses a date time from the server.
     * @param dateTime - date time from the server.
     * @return a Zoned Date time.
     */
    public static ZonedDateTime parseDateTime(String dateTime) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static ZonedDateTime parseDateTime(String dateTime, ZonedDateTime dflt) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static ZonedDateTime parseDateTimeThrowParseError(String dateTime) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Parses a long timestamp with nano precission in epoch UTC to the system
     * default time-zone date time
     *
     * @param timestampNanos String timestamp
     * @return a local Zoned Date time.
     */
    public static ZonedDateTime parseDateTimeNanos(String timestampNanos) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Parses a long timestamp with nano precission in epoch UTC to a Zoned date
     * time
     *
     * @param timestampNanos String timestamp
     * @param zoneId ZoneId
     * @return a Zoned Date time.
     */
    public static ZonedDateTime parseDateTimeNanos(String timestampNanos, ZoneId zoneId) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static ZonedDateTime fromNow(long millis) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static ZonedDateTime fromNow(Duration dur) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
