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

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public abstract class Encoding {

    private Encoding() {
    }

    /* ensures cannot be constructed */
    /**
     * base64 encode a byte array to a byte array
     * @param input the input byte array to encode
     * @return the encoded byte array
     */
    public static byte[] base64BasicEncode(byte[] input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * base64 encode a byte array to a byte array
     * @param input the input byte array to encode
     * @return the encoded byte array
     */
    public static String base64BasicEncodeToString(byte[] input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * base64 url encode a byte array to a byte array
     * @param input the input byte array to encode
     * @return the encoded byte array
     */
    public static String base64BasicEncodeToString(String input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * base64 url encode a byte array to a byte array
     * @param input the input byte array to encode
     * @return the encoded byte array
     */
    public static byte[] base64UrlEncode(byte[] input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * base64 url encode a byte array to a byte array
     * @param input the input byte array to encode
     * @return the encoded byte array
     */
    public static String base64UrlEncodeToString(byte[] input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * base64 url encode a byte array to a byte array
     * @param input the input byte array to encode
     * @return the encoded byte array
     */
    public static String base64UrlEncodeToString(String input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * base64 decode a byte array
     * @param input the input byte array to decode
     * @return the decoded byte array
     */
    public static byte[] base64BasicDecode(byte[] input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * base64 decode a base64 encoded string
     * @param input the input string to decode
     * @return the decoded byte array
     */
    public static byte[] base64BasicDecode(String input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * base64 decode a base64 encoded string
     * @param input the input string to decode
     * @return the decoded string
     */
    public static String base64BasicDecodeToString(String input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * base64 url decode a byte array
     * @param input the input byte array to decode
     * @return the decoded byte array
     */
    public static byte[] base64UrlDecode(byte[] input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * base64 url decode a base64 url encoded string
     * @param input the input string to decode
     * @return the decoded byte array
     */
    public static byte[] base64UrlDecode(String input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * base64 url decode a base64 url encoded string
     * @param input the input string to decode
     * @return the decoded string
     */
    public static String base64UrlDecodeToString(String input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // http://en.wikipedia.org/wiki/Base_32
    private static final String BASE32_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";

    private static final int[] BASE32_LOOKUP;

    private static final int MASK = 31;

    private static final int SHIFT = 5;

    /**
     * base32 encode a byte array
     * @param input the input
     * @return the encoded character array
     */
    public static char[] base32Encode(final byte[] input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static {
        BASE32_LOOKUP = new int[256];
        Arrays.fill(BASE32_LOOKUP, 0xFF);
        for (int i = 0; i < BASE32_CHARS.length(); i++) {
            int index = BASE32_CHARS.charAt(i) - '0';
            BASE32_LOOKUP[index] = i;
        }
    }

    /**
     * base32 decode a character array
     * @param input the input
     * @return the decoded byte array
     */
    public static byte[] base32Decode(final char[] input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * json decoding of a string
     * @param s the string
     * @return the decoded string
     */
    public static String jsonDecode(String s) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * json encode a string
     * @param s the string
     * @return the encoded string
     */
    public static String jsonEncode(String s) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * json encode a string appended into a StringBuilder
     * @param sb the target StringBuilder
     * @param s the string
     * @return the StringBuilder fluent style
     */
    public static StringBuilder jsonEncode(StringBuilder sb, String s) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * json encode a char array
     * @param chars the char array
     * @return the encoded string
     */
    public static String jsonEncode(char[] chars) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * json encode a char array appended into a StringBuilder
     * @param sb the target StringBuilder
     * @param chars the char array
     * @return the StringBuilder fluent style
     */
    public static StringBuilder jsonEncode(StringBuilder sb, char[] chars) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static void appendChar(StringBuilder sb, char ch) {
        switch(ch) {
            case '"':
                sb.append("\\\"");
                break;
            case '\\':
                sb.append("\\\\");
                break;
            case '\b':
                sb.append("\\b");
                break;
            case '\f':
                sb.append("\\f");
                break;
            case '\n':
                sb.append("\\n");
                break;
            case '\r':
                sb.append("\\r");
                break;
            case '\t':
                sb.append("\\t");
                break;
            case '/':
                sb.append("\\/");
                break;
            default:
                if (ch < ' ') {
                    sb.append(String.format("\\u%04x", (int) ch));
                } else {
                    sb.append(ch);
                }
                break;
        }
    }

    /**
     * perform basic uri decoding, replacing the plus sign with %2B
     * @param source the source string
     * @return tje decoded string
     */
    public static String uriDecode(String source) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @deprecated Use {@link #base64UrlEncode(byte[])} instead.
     * base64 url encode a byte array to a byte array
     * @param input the input byte array to encode
     * @return the encoded byte array
     */
    @Deprecated
    public static byte[] base64Encode(byte[] input) {
        return base64UrlEncode(input);
    }

    /**
     * @deprecated Use {@link #base64UrlEncodeToString(byte[])} instead.
     * base64 url encode a byte array to a string
     * @param input the input byte array to encode
     * @return the encoded string
     */
    @Deprecated
    public static String toBase64Url(byte[] input) {
        return base64UrlEncodeToString(input);
    }

    /**
     * @deprecated Use {@link #base64UrlEncodeToString(String)} instead.
     * base64 url encode a string to a string
     * @param input the input string to encode
     * @return the encoded string
     */
    @Deprecated
    public static String toBase64Url(String input) {
        return base64UrlEncodeToString(input);
    }

    /**
     * @deprecated Use {@link #base64UrlDecodeToString(String)} instead.
     * get a string from a base64 url encoded byte array
     * @param input the input string to decode
     * @return the decoded string
     */
    @Deprecated
    public static String fromBase64Url(String input) {
        return base64UrlDecodeToString(input);
    }
}
