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
package io.nats.client.support;

import org.jspecify.annotations.NonNull;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.function.Function;
import static io.nats.client.support.Encoding.base64BasicDecode;
import static io.nats.client.support.JsonValue.*;

/**
 * Internal json value helpers.
 */
public abstract class JsonValueUtils {

    private JsonValueUtils() {
    }

    /* ensures cannot be constructed */
    public interface JsonValueSupplier<T> {

        T get(JsonValue v);
    }

    public static <T> T read(JsonValue jsonValue, String key, JsonValueSupplier<T> valueSupplier) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static JsonValue readValue(JsonValue jsonValue, String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static JsonValue readObject(JsonValue jsonValue, String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<JsonValue> readArray(JsonValue jsonValue, String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Map<String, String> readStringStringMap(JsonValue jv, String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String readString(JsonValue jsonValue, String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String readStringEmptyAsNull(JsonValue jsonValue, String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String readString(JsonValue jsonValue, String key, String dflt) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static ZonedDateTime readDate(JsonValue jsonValue, String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Integer readInteger(JsonValue jsonValue, String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int readInteger(JsonValue jsonValue, String key, int dflt) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Long readLong(JsonValue jsonValue, String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static long readLong(JsonValue jsonValue, String key, long dflt) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean readBoolean(JsonValue jsonValue, String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Boolean readBoolean(JsonValue jsonValue, String key, Boolean dflt) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Duration readNanos(JsonValue jsonValue, String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Duration readNanos(JsonValue jsonValue, String key, Duration dflt) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> listOf(JsonValue v, Function<JsonValue, T> provider) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> optionalListOf(JsonValue v, Function<JsonValue, T> provider) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<String> readStringList(JsonValue jsonValue, String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<String> readStringListIgnoreEmpty(JsonValue jsonValue, String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<String> readOptionalStringList(JsonValue jsonValue, String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<Long> readLongList(JsonValue jsonValue, String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<Duration> readNanosList(JsonValue jsonValue, String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<Duration> readNanosList(JsonValue jsonValue, String key, boolean nullIfEmpty) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static byte[] readBytes(JsonValue jsonValue, String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static byte[] readBase64(JsonValue jsonValue, String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Integer getInteger(JsonValue v) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Long getLong(JsonValue v) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static long getLong(JsonValue v, long dflt) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static JsonValue instance(Duration d) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("rawtypes")
    public static JsonValue instance(Collection list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("rawtypes")
    public static JsonValue instance(Map map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static JsonValue toJsonValue(Object o) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static MapBuilder mapBuilder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static class MapBuilder implements JsonSerializable {

        public JsonValue jv;

        public MapBuilder() {
            jv = new JsonValue(new HashMap<>());
        }

        public MapBuilder(JsonValue jv) {
            this.jv = jv;
        }

        public MapBuilder put(String s, Object o) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public MapBuilder put(String s, Map<String, String> stringMap) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        @NonNull
        public String toJson() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        @NonNull
        public JsonValue toJsonValue() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Deprecated
        public JsonValue getJsonValue() {
            return jv;
        }
    }

    public static ArrayBuilder arrayBuilder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static class ArrayBuilder implements JsonSerializable {

        public JsonValue jv = new JsonValue(new ArrayList<>());

        public ArrayBuilder add(Object o) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        @NonNull
        public String toJson() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        @NonNull
        public JsonValue toJsonValue() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Deprecated
        public JsonValue getJsonValue() {
            return jv;
        }
    }
}
