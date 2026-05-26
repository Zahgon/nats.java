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

import io.nats.client.JetStreamApiException;
import io.nats.client.Message;
import io.nats.client.support.*;
import org.jspecify.annotations.Nullable;
import java.time.ZonedDateTime;
import static io.nats.client.support.ApiConstants.ERROR;
import static io.nats.client.support.ApiConstants.TYPE;
import static io.nats.client.support.JsonValueUtils.*;

/**
 * ApiResponse is the base class for all api responses from the server
 * @param <T> the success response class
 */
public abstract class ApiResponse<T> {

    /**
     * A constant for a response without a type
     */
    public static final String NO_TYPE = "io.nats.jetstream.api.v1.no_type";

    /**
     * a constant for a response that errors while parsing
     */
    public static final String PARSE_ERROR_TYPE = "io.nats.client.api.parse_error";

    /**
     * The JSON value made from creating the object from a message or that was used to directly construct the response
     */
    protected final JsonValue jv;

    private final String type;

    private Error error;

    /**
     * construct an ApiResponse from a message
     * @param msg the message
     */
    public ApiResponse(Message msg) {
        this(parseMessage(msg));
    }

    /**
     * parse the response message
     * @param msg the message
     * @return the JsonValue of the parsed JSON
     */
    protected static JsonValue parseMessage(Message msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * called when the JSON is invalid
     * @param retVal the fluent value to return
     * @return the return value
     * @param <R> the type of the return value
     */
    protected <R> R invalidJson(R retVal) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * set an error if the value in the key is null
     * @param jv the input
     * @param key the key
     * @return the value of the key
     */
    protected String nullStringIsError(JsonValue jv, String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * set an error if the value in the key is null
     * @param jv the input
     * @param key the key
     * @return the value of the key
     */
    @SuppressWarnings("SameParameterValue")
    protected ZonedDateTime nullDateIsError(JsonValue jv, String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * set an error if the value in the key is null
     * @param jv the input
     * @param key the key
     * @param errorValue the value in case of error
     * @return the value of the key
     */
    @SuppressWarnings("SameParameterValue")
    protected JsonValue nullValueIsError(JsonValue jv, String key, JsonValue errorValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Construct an ApiResponse from a JsonValue
     * @param jsonValue the value
     */
    public ApiResponse(JsonValue jsonValue) {
        jv = jsonValue;
        if (jv == null) {
            error = null;
            type = null;
        } else {
            error = Error.optionalInstance(readValue(jv, ERROR));
            String temp = readString(jv, TYPE);
            if (temp == null) {
                type = NO_TYPE;
            } else {
                type = temp;
                // just so it's not in the toString, it's very long and the object name will be there
                jv.map.remove(TYPE);
            }
        }
    }

    /**
     * Construct an empty ApiResponse
     */
    public ApiResponse() {
        jv = null;
        error = null;
        type = NO_TYPE;
    }

    /**
     * Construct an ApiResponse from an error object
     * @param error the error object
     */
    public ApiResponse(Error error) {
        jv = null;
        this.error = error;
        type = NO_TYPE;
    }

    /**
     * throw an Exception if the response had an error
     * @return the ApiResponse if not an error
     * @throws JetStreamApiException if the response had an error
     */
    @SuppressWarnings("unchecked")
    public T throwOnHasError() throws JetStreamApiException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the JsonValue used to make this object
     * @return the value
     */
    @Nullable
    public JsonValue getJv() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Does the response have an error
     * @return true if the response has an error
     */
    public boolean hasError() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The type of the response object
     * @return the type
     */
    @Nullable
    public String getType() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The request error code from the server
     * @return the code
     */
    public int getErrorCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The api error code from the server
     * @return the code
     */
    public int getApiErrorCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the error description
     * @return the description if the response is an error
     */
    @Nullable
    public String getDescription() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the error object string
     * @return the error object string if the response is an error
     */
    @Nullable
    public String getError() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the error object
     * @return the error object if the response is an error
     */
    @Nullable
    public Error getErrorObject() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
