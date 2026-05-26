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
package io.nats.client;

import io.nats.client.api.ConsumerConfiguration;
import io.nats.client.support.JsonValue;
import static io.nats.client.support.ApiConstants.EXPIRES_IN;
import static io.nats.client.support.ApiConstants.NO_WAIT;
import static io.nats.client.support.JsonUtils.addFldWhenTrue;
import static io.nats.client.support.JsonValueUtils.readBoolean;
import static io.nats.client.support.JsonValueUtils.readLong;

/**
 * Fetch Consume Options are provided to customize the fetch operation.
 */
public class FetchConsumeOptions extends BaseConsumeOptions {

    /**
     * An instance of FetchConsumeOptions representing the default fetch options
     */
    public static final FetchConsumeOptions DEFAULT_FETCH_OPTIONS = FetchConsumeOptions.builder().build();

    private final boolean noWait;

    private FetchConsumeOptions(Builder b) {
        super(b);
        this.noWait = b.noWait;
    }

    @Override
    protected void subclassSpecificToJson(StringBuilder sb) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The maximum number of messages to fetch.
     * @return the maximum number of messages to fetch
     */
    public int getMaxMessages() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The maximum number of bytes to fetch.
     * @return the maximum number of bytes to fetch
     */
    public long getMaxBytes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * If the options specify no wait
     * @return the flag
     */
    public boolean isNoWait() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get an instance of the builder
     * @return a builder
     */
    public static Builder builder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The builder for FetchConsumeOptions
     */
    public static class Builder extends BaseConsumeOptions.Builder<Builder, FetchConsumeOptions> {

        /**
         * Construct a builder for FetchConsumeOptions
         */
        public Builder() {
        }

        protected boolean noWait = false;

        protected Builder getThis() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Builder jsonValue(JsonValue jsonValue) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the maximum number of messages to fetch and remove any previously set {@link #maxBytes(long)} constraint.
         * The number of messages fetched will also be constrained by the expiration time.
         * <p>Less than 1 means default of {@value BaseConsumeOptions#DEFAULT_MESSAGE_COUNT}.</p>
         * @param maxMessages the number of messages.
         * @return the builder
         */
        public Builder maxMessages(int maxMessages) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set maximum number of bytes to fetch and remove any previously set {@link #maxMessages constraint}
         * The number of bytes fetched will also be constrained by the expiration time.
         * <p>Less than 1 removes any previously set max bytes constraint.</p>
         * <p>It is important to set the byte size greater than your largest message payload, plus some amount
         * to account for overhead, otherwise the consume process will stall if there are no messages that fit the criteria.</p>
         * @see Message#consumeByteCount()
         * @param maxBytes the maximum bytes
         * @return the builder
         */
        public Builder maxBytes(long maxBytes) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set maximum number of bytes or messages to fetch.
         * The number of messages/bytes fetched will also be constrained by
         * whichever constraint is reached first, as well as the expiration time.
         * <p>Less than 1 max bytes removes any previously set max bytes constraint.</p>
         * <p>Less than 1 max messages removes any previously set max messages constraint.</p>
         * <p>It is important to set the byte size greater than your largest message payload, plus some amount
         * to account for overhead, otherwise the consume process will stall if there are no messages that fit the criteria.</p>
         * @see Message#consumeByteCount()
         * @param maxBytes the maximum bytes
         * @param maxMessages the maximum number of messages
         * @return the builder
         */
        public Builder max(int maxBytes, int maxMessages) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Builder expiresIn(long expiresInMillis) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set no wait to true
         * When no wait is true, the fetch will return immediately with as many messages as are available. Between zero and the maximum configured.
         * @return the builder
         */
        public Builder noWait() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set no wait to true with an expiration. This is the common configuration to receive messages as soon as they arrive in the stream without excessive pulling.
         * When no wait is true with expire, the fetch will return immediately with as many messages as are available, but at least one message. Between one and the maximum configured.
         * When no message is available it will wait for new messages to arrive till it expires.
         * @param expiresInMillis the expiration time in milliseconds
         * @return the builder
         */
        public Builder noWaitExpiresIn(long expiresInMillis) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Build the FetchConsumeOptions.
         * @return a FetchConsumeOptions instance
         */
        public FetchConsumeOptions build() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
