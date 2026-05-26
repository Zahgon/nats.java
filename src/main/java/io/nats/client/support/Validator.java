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

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import static io.nats.client.support.NatsConstants.DOT;
import static io.nats.client.support.NatsJetStreamConstants.MAX_HISTORY_PER_KEY;
import static io.nats.client.support.NatsJetStreamConstants.NATS_META_KEY_PREFIX;

@SuppressWarnings("UnusedReturnValue")
public abstract class Validator {

    private Validator() {
    }

    /* ensures cannot be constructed */
    /*
        cannot contain spaces \r \n \t
    */
    public static String validateSubjectTerm(String subject, String label, boolean required) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
        cannot contain spaces \r \n \t
        cannot start or end with subject token delimiter .
        some things don't allow it to end greater
    */
    public static String validateSubjectTermStrict(String subject, String label, boolean required) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String validateSubject(String s, boolean required) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String validateSubjectStrict(String s, boolean required) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String validateSubject(String subject, String label, boolean required, boolean cantEndWithGt) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String validateReplyTo(String s, boolean required) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String validateQueueName(String s, boolean required) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String validateStreamName(String s, boolean required) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String validateDurable(String s, boolean required) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String validateConsumerName(String s, boolean required) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String validatePrefixOrDomain(String s, String label, boolean required) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<String> validateKvKeysWildcardAllowedRequired(List<String> keys) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String validateKvKeyWildcardAllowedRequired(String s) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String validateNonWildcardKvKeyRequired(String s) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void validateNotSupplied(String s, NatsJetStreamClientError err) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String validateMustMatchIfBothSupplied(String s1, String s2, NatsJetStreamClientError err) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String required(String s, String label) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Deprecated
    public static String required(String s1, String s2, String label) {
        if (emptyAsNull(s1) == null || emptyAsNull(s2) == null) {
            throw new IllegalArgumentException(label + " cannot be null or empty.");
        }
        return s1;
    }

    public static <T> T required(T o, String label) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void required(List<?> l, String label) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void required(Map<?, ?> m, String label) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String _validate(String s, boolean required, String label, Supplier<String> customValidate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String validateMaxLength(String s, int maxLength, boolean required, String label) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String validatePrintable(String s, String label, boolean required) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String validatePrintableExceptWildDotGt(String s, String label, boolean required) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String validatePrintableExceptWildDotGtSlashes(String s, String label, boolean required) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String validatePrintableExceptWildGt(String s, String label, boolean required) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String validateIsRestrictedTerm(String s, String label, boolean required) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String validateBucketName(String s, boolean required) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String validateWildcardKvKey(String s, String label, boolean required) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String validateNonWildcardKvKey(String s, String label, boolean required) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static long validateMaxConsumers(long max) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static long validateMaxMessages(long max) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static long validateMaxMessagesPerSubject(long max) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int validateMaxHistory(int max) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static long validateMaxBytes(long max) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static long validateMaxBucketBytes(long max) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static long validateMaxMessageSize(long max, String label) {
        long l = validateGtZeroOrMinus1(max, label);
        if (l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(label + " cannot be larger than " + Integer.MAX_VALUE);
        }
        return l;
    }

    public static long validateMaxMessageSize(long max) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static long validateMaxValueSize(long max) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int validateNumberOfReplicas(int replicas) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Duration validateDurationRequired(Duration d) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Duration validateDurationNotRequiredGtOrEqZero(Duration d, Duration ifNull) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Duration validateDurationNotRequiredGtOrEqZero(long millis) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Duration validateDurationNotRequiredGtOrEqSeconds(long minSeconds, Duration d, Duration ifNull, String label) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Duration validateDurationGtOrEqSeconds(long minSeconds, long millis, String label) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String validateNotNull(String s, String fieldName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Object validateNotNull(Object o, String fieldName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int validateGtZero(int i, String label) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static long validateGtZero(long l, String label) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static long validateGtZeroOrMinus1(long l, String label) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static long validateGtEqMinus1(long l, String label) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static long validateNotNegative(long l, String label) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean isGtEqZero(long l) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static long validateGtEqZero(long l, String label) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // ----------------------------------------------------------------------------------------------------
    // Helpers
    // ----------------------------------------------------------------------------------------------------
    public static boolean nullOrEmpty(String s) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> boolean nullOrEmpty(T[] a) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean nullOrEmpty(Collection<?> c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean notPrintable(String s) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean notPrintableOrHasChars(String s, char[] charsToNotHave) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // restricted-term  = (A-Z, a-z, 0-9, dash 45, underscore 95)+
    public static boolean notRestrictedTerm(String s) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // limited-term = (A-Z, a-z, 0-9, dash 45, dot 46, fwd-slash 47, equals 61, underscore 95)+
    // kv-key-name = limited-term (dot limited-term)*
    public static boolean notNonWildcardKvKey(String s) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // (A-Z, a-z, 0-9, star 42, dash 45, dot 46, fwd-slash 47, equals 61, gt 62, underscore 95)+
    public static boolean notWildcardKvKey(String s) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static final char[] WILD_GT = { '*', '>' };

    static final char[] WILD_GT_DOT = { '*', '>', '.' };

    static final char[] WILD_GT_DOT_SLASHES = { '*', '>', '.', '\\', '/' };

    public static boolean notPrintableOrHasWildGt(String s) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean notPrintableOrHasWildGtDot(String s) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean notPrintableOrHasWildGtDotSlashes(String s) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String emptyAsNull(String s) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String emptyOrNullAs(String s, String ifEmpty) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean zeroOrLtMinus1(long l) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Duration ensureNotNullAndNotLessThanMin(Duration provided, Duration minimum, Duration dflt) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Duration ensureDurationNotLessThanMin(long providedMillis, Duration minimum, Duration dflt) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String ensureEndsWithDot(String s) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static final Pattern SEMVER_PATTERN = Pattern.compile("^(0|[1-9]\\d*)\\.(0|[1-9]\\d*)\\.(0|[1-9]\\d*)(?:-((?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*)(?:\\.(?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*))*))?(?:\\+([0-9a-zA-Z-]+(?:\\.[0-9a-zA-Z-]+)*))?$");

    public static String validateSemVer(String s, String label, boolean required) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean isSemVer(String s) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // This function tests filter subject equivalency
    // It does not care what order and also assumes that there are no duplicates.
    // From the server: consumer subject filters cannot overlap [10138]
    public static <T> boolean listsAreEquivalent(List<T> l1, List<T> l2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean mapsAreEquivalent(Map<String, String> m1, Map<String, String> m2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // this is a special case map where the meta has both user and nats headers like
    // _nats.req.level=0, _nats.ver=2.12.0-preview.2, _nats.level=2
    // in this case we only want to compare the user keys
    public static boolean metaIsEquivalent(Map<String, String> m1, Map<String, String> m2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
