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
import org.jspecify.annotations.Nullable;
import java.util.List;
import static io.nats.client.support.ApiConstants.BYTES;
import static io.nats.client.support.ApiConstants.MSGS;
import static io.nats.client.support.JsonValueUtils.readLong;
import static io.nats.client.support.JsonValueUtils.readLongList;

/**
 * Information about lost stream data
 */
public class LostStreamData {

    private final List<Long> messages;

    private final Long bytes;

    static LostStreamData optionalInstance(JsonValue vLost) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    LostStreamData(JsonValue vLost) {
        messages = readLongList(vLost, MSGS);
        bytes = readLong(vLost, BYTES);
    }

    /**
     * Get the lost message ids. May be empty
     * @return the list of message ids
     */
    @NonNull
    public List<Long> getMessages() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the number of bytes that were lost
     * @return the number of lost bytes
     */
    @Nullable
    public Long getBytes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
