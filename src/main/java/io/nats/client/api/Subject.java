// Copyright 2022 The NATS Authors
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

import io.nats.client.support.JsonValue;
import org.jspecify.annotations.NonNull;
import java.util.ArrayList;
import java.util.List;
import static io.nats.client.support.JsonValueUtils.getLong;

/**
 * An object representing a stream's subject and the count of it's messages
 */
public class Subject implements Comparable<Subject> {

    private final String name;

    private final long count;

    static List<Subject> listOf(JsonValue vSubjects) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Construct a Subject instance
     * @param name the subject name
     * @param count the message count
     */
    public Subject(String name, long count) {
        this.name = name;
        this.count = count;
    }

    /**
     * Get the subject name
     * @return the subject
     */
    @NonNull
    public String getName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the subject message count
     * @return the count
     */
    public long getCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int compareTo(Subject o) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
