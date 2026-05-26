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

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import static io.nats.client.support.NatsConstants.*;
import static io.nats.client.support.NatsJetStreamConstants.*;
import static io.nats.client.support.Status.*;
import static java.nio.charset.StandardCharsets.UTF_8;

public class Token {

    private final byte[] serialized;

    private final TokenType type;

    private final int start;

    private int end;

    private boolean hasValue;

    private final int valueLength;

    public Token(byte[] serialized, int len, Token prev, TokenType required) {
        this(serialized, len, prev.end + (prev.type == TokenType.KEY ? 2 : 1), required);
    }

    public Token(byte[] serialized, int len, int cur, TokenType required) {
        this.serialized = serialized;
        if (cur >= len) {
            throw new IllegalArgumentException(INVALID_HEADER_COMPOSITION);
        }
        if (serialized[cur] == SP) {
            type = TokenType.SPACE;
            start = cur;
            end = cur;
            while (serialized[++cur] == SP) {
                end = cur;
            }
        } else if (serialized[cur] == CR) {
            mustBeCrlf(len, cur);
            type = TokenType.CRLF;
            start = cur;
            end = cur + 1;
        } else if (required == TokenType.CRLF || required == TokenType.SPACE) {
            throw new IllegalArgumentException(INVALID_HEADER_COMPOSITION);
        } else {
            byte ender1;
            byte ender2;
            if (required == null || required == TokenType.TEXT) {
                type = TokenType.TEXT;
                ender1 = CR;
                ender2 = CR;
            } else if (required == TokenType.WORD) {
                ender1 = SP;
                ender2 = CR;
                type = TokenType.WORD;
            } else {
                // KEY is all that's left if (required == TokenType.KEY) {
                ender1 = COLON;
                ender2 = COLON;
                type = TokenType.KEY;
            }
            start = cur;
            end = cur;
            while (++cur < len && serialized[cur] != ender1 && serialized[cur] != ender2) {
                end = cur;
            }
            if (cur >= len) {
                throw new IllegalArgumentException(INVALID_HEADER_COMPOSITION);
            }
            if (serialized[cur] == CR) {
                mustBeCrlf(len, cur);
            }
            hasValue = true;
        }
        valueLength = hasValue ? end - start + 1 : 0;
    }

    private void mustBeCrlf(int len, int cur) {
        if ((cur + 1) >= len || serialized[cur + 1] != LF) {
            throw new IllegalArgumentException(INVALID_HEADER_COMPOSITION);
        }
    }

    public void mustBe(TokenType expected) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isType(TokenType expected) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasValue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @NonNull
    public String getValue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Nullable
    public String getValueOrNull() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private String valueAsString() {
        return new String(serialized, start, valueLength, UTF_8).trim();
    }

    @NonNull
    public String getValueCheckKnownKeys() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Nullable
    public String getValueCheckKnownStatuses() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static final byte[] NATS_DASH_PREFIX_BYTES = "Nats-".getBytes();

    static final byte[] EXCEEDED_MAX_PREFIX_BYTES = EXCEEDED_MAX_PREFIX.getBytes();

    static final int EXCEEDED_MAX_PREFIX_BYTES_LEN = EXCEEDED_MAX_PREFIX.length();

    private boolean valueStartsNatsDash() {
        for (int i = 0; i < 4; i++) {
            if (NATS_DASH_PREFIX_BYTES[i] != serialized[start + i]) {
                return false;
            }
        }
        return true;
    }

    private boolean valueStartsWithExceededMax() {
        // we know we already checked the first letter to be E
        for (int i = 1; i < EXCEEDED_MAX_PREFIX_BYTES_LEN; i++) {
            if (EXCEEDED_MAX_PREFIX_BYTES[i] != serialized[start + i]) {
                return false;
            }
        }
        return true;
    }

    private boolean endsMatch(byte @NonNull [] checkBytes, int compareStartIndex) {
        if (valueLength != checkBytes.length) {
            return false;
        }
        for (int i = compareStartIndex; i < valueLength; i++) {
            if (checkBytes[i] != serialized[start + i]) {
                return false;
            }
        }
        return true;
    }

    public boolean samePoint(Token token) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
