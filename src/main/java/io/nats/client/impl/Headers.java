// Copyright 2020-2025 The NATS Authors
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

import io.nats.client.support.ByteArrayBuilder;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import java.util.*;
import java.util.function.BiConsumer;
import static io.nats.client.support.NatsConstants.*;
import static io.nats.client.support.Validator.nullOrEmpty;

/**
 * An object that represents a map of keys to a list of values. It does not accept
 * null or invalid keys. It ignores null values, accepts empty string as a value
 * and rejects invalid values.
 * !!!
 * THIS CLASS IS NOT THREAD SAFE
 */
public class Headers {

    private static final String KEY_CANNOT_BE_EMPTY_OR_NULL = "Header key cannot be null.";

    private static final String KEY_INVALID_CHARACTER = "Header key has invalid character: 0x";

    private static final String VALUE_INVALID_CHARACTERS = "Header value has invalid character: 0x";

    private final Map<String, List<String>> valuesMap;

    private final Map<String, Integer> lengthMap;

    private final boolean readOnly;

    private byte[] serialized;

    private int dataLength;

    /**
     * Create a new Headers object
     */
    public Headers() {
        this(null, false, null);
    }

    /**
     * Create a new Headers object by copying all header entries
     * @param headers the headers to copy
     */
    public Headers(@Nullable Headers headers) {
        this(headers, false, null);
    }

    /**
     * Create a new Headers object by copying all header entries
     * @param headers the headers to copy
     * @param readOnly flag to indicate that whether the new Headers should be marked as read-only
     */
    public Headers(@Nullable Headers headers, boolean readOnly) {
        this(headers, readOnly, null);
    }

    /**
     * Create a new Headers object by copying all header entries, except those indicated by keysNotToCopy
     * @param headers the headers to copy
     * @param readOnly flag to indicate that whether the new Headers should be marked as read-only
     * @param keysNotToCopy an array of keys that should not be copied
     */
    public Headers(@Nullable Headers headers, boolean readOnly, String @Nullable [] keysNotToCopy) {
        Map<String, List<String>> tempValuesMap = new HashMap<>();
        Map<String, Integer> tempLengthMap = new HashMap<>();
        if (headers != null) {
            tempValuesMap.putAll(headers.valuesMap);
            tempLengthMap.putAll(headers.lengthMap);
            dataLength = headers.dataLength;
            if (keysNotToCopy != null) {
                for (String key : keysNotToCopy) {
                    if (key != null) {
                        if (tempValuesMap.remove(key) != null) {
                            dataLength -= tempLengthMap.remove(key);
                        }
                    }
                }
            }
        }
        this.readOnly = readOnly;
        if (readOnly) {
            valuesMap = Collections.unmodifiableMap(tempValuesMap);
            lengthMap = Collections.unmodifiableMap(tempLengthMap);
        } else {
            valuesMap = tempValuesMap;
            lengthMap = tempLengthMap;
        }
    }

    /**
     * If the key is present add the values to the list of values for the key.
     * If the key is not present, sets the specified values for the key.
     * null values are ignored. If all values are null, the key is not added or updated.
     * @param key the key
     * @param values the values
     * @return the Headers object
     * @throws IllegalArgumentException if the key is null or empty or contains invalid characters
     *         -or- if any value contains invalid characters
     */
    public Headers add(String key, String... values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * If the key is present add the values to the list of values for the key.
     * If the key is not present, sets the specified values for the key.
     * null values are ignored. If all values are null, the key is not added or updated.
     * @param key the entry key
     * @param values a list of values to the entry
     * @return the Header object
     * @throws IllegalArgumentException if the key is null or empty or contains invalid characters
     *         -or- if any value contains invalid characters
     */
    public Headers add(String key, Collection<String> values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // the add delegate
    private Headers _add(String key, @NonNull Collection<String> values) {
        ValuesAndLength collected = validateKeyAndCollect(key, values);
        if (collected != null) {
            // get values by key or compute empty if absent
            // update the data length with the additional len
            // update the lengthMap for the key to the old length plus the new length
            List<String> currentSet = valuesMap.computeIfAbsent(key, k -> new ArrayList<>());
            currentSet.addAll(collected.values);
            dataLength += collected.length;
            int oldLen = lengthMap.getOrDefault(key, 0);
            lengthMap.put(key, oldLen + collected.length);
            // since the data changed, clear this so it's rebuilt
            serialized = null;
        }
        return this;
    }

    /**
     * Associates the specified values with the key. If the key was already present
     * any existing values are removed and replaced with the new list.
     * null values are ignored. If all values are null, the put is ignored
     * @param key the key
     * @param values the values
     * @return the Headers object
     * @throws IllegalArgumentException if the key is null or empty or contains invalid characters
     *         -or- if any value contains invalid characters
     */
    public Headers put(String key, String... values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Associates the specified values with the key. If the key was already present
     * any existing values are removed and replaced with the new list.
     * null values are ignored. If all values are null, the put is ignored
     * @param key the key
     * @param values the values
     * @return the Headers object
     * @throws IllegalArgumentException if the key is null or empty or contains invalid characters
     *         -or- if any value contains invalid characters
     */
    public Headers put(String key, Collection<String> values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Associates all specified values with their key. If the key was already present
     * any existing values are removed and replaced with the new list.
     * null values are ignored. If all values are null, the put is ignored
     * @param map the map
     * @return the Headers object
     */
    public Headers put(Map<String, List<String>> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // the put delegate
    private Headers _put(String key, Collection<String> values) {
        ValuesAndLength collected = validateKeyAndCollect(key, values);
        if (collected != null) {
            // update the data length removing the old length adding the new length
            // put for the key
            dataLength = dataLength - lengthMap.getOrDefault(key, 0) + collected.length;
            valuesMap.put(key, collected.values);
            lengthMap.put(key, collected.length);
            // since the data changed, clear this so it's rebuilt
            serialized = null;
        }
        return this;
    }

    static final class ValuesAndLength {

        final List<String> values;

        final int length;

        ValuesAndLength(List<String> values, int length) {
            this.values = values;
            this.length = length;
        }
    }

    static ValuesAndLength validateKeyAndCollect(String key, Collection<String> values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Removes each key and its values if the key was present
     * @param keys the key or keys to remove
     */
    public void remove(String... keys) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Removes each key and its values if the key was present
     * @param keys the key or keys to remove
     */
    public void remove(Collection<String> keys) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void _remove(Collection<String> keys) {
        for (String key : keys) {
            if (!nullOrEmpty(key) && valuesMap.remove(key) != null) {
                dataLength -= lengthMap.remove(key);
                // since the data changed, clear this so it's rebuilt
                serialized = null;
            }
        }
    }

    /**
     * Returns the number of keys (case-sensitive) in the header.
     * @return the number of header entries
     */
    public int size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns ture if map contains no keys.
     * @return true if there are no headers
     */
    public boolean isEmpty() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Removes all the keys The object map will be empty after this call returns.
     */
    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns true if key (case-sensitive) is present (has values)
     * @param key key whose presence is to be tested
     * @return true if the key (case-sensitive) is present (has values)
     */
    public boolean containsKey(String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns true if key (case-insensitive) is present (has values)
     * @param key exact key whose presence is to be tested
     * @return true if the key (case-insensitive) is present (has values)
     */
    public boolean containsKeyIgnoreCase(String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns a {@link Set} view of the keys (case-sensitive) contained in the object.
     * @return a read-only set the keys contained in this map
     */
    public Set<String> keySet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns a {@link Set} view of the keys (case-insensitive) contained in the object.
     * @return a read-only set of keys (in lowercase) contained in this map
     */
    public Set<String> keySetIgnoreCase() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns a {@link List} view of the values for the specific (case-sensitive) key.
     * Will be {@code null} if the key is not found.
     * @param key the key whose associated value is to be returned
     * @return a read-only list of the values for the case-sensitive key.
     */
    @Nullable
    public List<String> get(String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the first value for the specific (case-sensitive) key.
     * Will be {@code null} if the key is not found.
     * @param key the key whose associated value is to be returned
     * @return the first value for the case-sensitive key.
     */
    @Nullable
    public String getFirst(String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the last value for the specific (case-sensitive) key.
     * Will be {@code null} if the key is not found.
     * @param key the key whose associated value is to be returned
     * @return the last value for the case-sensitive key.
     */
    @Nullable
    public String getLast(String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns a {@link List} view of the values for the specific (case-insensitive) key.
     * Will be {@code null} if the key is not found.
     * @param key the key whose associated value is to be returned
     * @return a read-only list of the values for the case-insensitive key.
     */
    @Nullable
    public List<String> getIgnoreCase(String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Performs the given action for each header entry (case-sensitive keys) until all entries
     * have been processed or the action throws an exception.
     * Any attempt to modify the values will throw an exception.
     * @param action The action to be performed for each entry
     * @throws NullPointerException if the specified action is null
     * @throws ConcurrentModificationException if an entry is found to be
     * removed during iteration
     */
    public void forEach(BiConsumer<String, List<String>> action) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns a {@link Set} read only view of the mappings contained in the header (case-sensitive keys).
     * The set is not modifiable and any attempt to modify will throw an exception.
     * @return a set view of the mappings contained in this map or Collections.emptySet() if there are no entries
     */
    @NonNull
    public Set<Map.Entry<String, List<String>>> entrySet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns if the headers are dirty, which means the serialization
     * has not been done so also don't know the byte length
     * @return true if dirty
     */
    public boolean isDirty() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the number of bytes that will be in the serialized version.
     * @return the number of bytes
     */
    public int serializedLength() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static final int HVCRLF_BYTES = HEADER_VERSION_BYTES_PLUS_CRLF.length;

    private static final int NON_DATA_BYTES = HVCRLF_BYTES + 2;

    /**
     * Returns the serialized bytes.
     * @return the bytes
     */
    public byte @NonNull [] getSerialized() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @deprecated
     * Used for unit testing.
     * Appends the serialized bytes to the builder.
     * @param bab the ByteArrayBuilder to append
     * @return the builder
     */
    @Deprecated
    public ByteArrayBuilder appendSerialized(ByteArrayBuilder bab) {
        bab.append(HEADER_VERSION_BYTES_PLUS_CRLF);
        for (Map.Entry<String, List<String>> entry : valuesMap.entrySet()) {
            for (String value : entry.getValue()) {
                bab.append(entry.getKey());
                bab.append(COLON_BYTES);
                bab.append(value);
                bab.append(CRLF_BYTES);
            }
        }
        bab.append(CRLF_BYTES);
        return bab;
    }

    /**
     * Write the header to the byte array. Assumes that the caller has
     * already validated that the destination array is large enough by using {@link #serializedLength()}.
     * <p>deprecated {@link String#getBytes(int, int, byte[], int)} is used, because it still exists in JDK 25
     * and is 10–30 times faster than {@code getBytes(ISO_8859_1/US_ASCII)}/
     * @param destPosition the position index in destination byte array to start
     * @param dest the byte array to write to
     * @return the length of the header
     */
    public int serializeToArray(int destPosition, byte[] dest) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Whether the entire Headers is read only
     * @return the read only state
     */
    public boolean isReadOnly() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean equals(Object o) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
