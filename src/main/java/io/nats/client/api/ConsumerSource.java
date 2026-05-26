// Copyright 2026 The NATS Authors
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
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import org.jspecify.annotations.NonNull;
import java.util.Objects;
import static io.nats.client.support.ApiConstants.DELIVER_SUBJECT;
import static io.nats.client.support.ApiConstants.NAME;
import static io.nats.client.support.JsonUtils.*;
import static io.nats.client.support.Validator.validateConsumerName;
import static io.nats.client.support.Validator.validateSubject;

/**
 * Consumer information for durable sourcing. Dictates that a durable consumer with a specific
 * name is used for sourcing.
 */
public class ConsumerSource implements JsonSerializable {

    private final String name;

    private final String deliverSubject;

    static ConsumerSource optionalInstance(JsonValue vConsumerSource) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    ConsumerSource(JsonValue vConsumerSource) {
        name = JsonValueUtils.readString(vConsumerSource, NAME);
        deliverSubject = JsonValueUtils.readString(vConsumerSource, DELIVER_SUBJECT);
    }

    /**
     * Construct the ConsumerSource configuration
     * @param name the consumer name
     * @param deliverSubject the deliver subject
     */
    public ConsumerSource(String name, String deliverSubject) {
        this.name = name;
        this.deliverSubject = deliverSubject;
    }

    /**
     * Construct a ConsumerSource configuration copying the information from another ConsumerSource
     * @param consumerSource the source configuration
     */
    public ConsumerSource(ConsumerSource consumerSource) {
        this.name = consumerSource.name;
        this.deliverSubject = consumerSource.deliverSubject;
    }

    @Override
    @NonNull
    public String toJson() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The durable consumer name used for sourcing.
     * @return the consumer name
     */
    @NonNull
    public String getName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The subject to deliver messages to.
     * @return the deliver subject
     */
    @NonNull
    public String getDeliverSubject() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public final boolean equals(Object o) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates a builder for a ConsumerSource object.
     * @return the builder.
     */
    public static Builder builder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * ConsumerSource can be created using a Builder.
     */
    public static class Builder {

        private String name;

        private String deliverSubject;

        /**
         * Construct a builder for a ConsumerSource object
         */
        public Builder() {
        }

        /**
         * Set the consumer name.
         * @param name the consumer name
         * @return the builder
         */
        public Builder name(String name) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the deliver subject.
         * @param deliverSubject the deliver subject
         * @return the builder
         */
        public Builder deliverSubject(String deliverSubject) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Build a ConsumerSource object
         * @return the ConsumerSource object
         */
        public ConsumerSource build() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
