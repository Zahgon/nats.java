// Copyright 2021-2023 The NATS Authors
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

import io.nats.client.NKey;
import io.nats.client.NatsSystemClock;
import org.jspecify.annotations.NonNull;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.time.Duration;
import java.util.List;
import static io.nats.client.support.Encoding.*;
import static io.nats.client.support.JsonUtils.beginJson;
import static io.nats.client.support.JsonUtils.endJson;

/**
 * Implements <a href="https://github.com/nats-io/nats-architecture-and-design/blob/main/adr/ADR-14.md">ADR-14</a>
 */
public abstract class JwtUtils {

    private JwtUtils() {
    }

    /* ensures cannot be constructed */
    private static final String ENCODED_CLAIM_HEADER = base64UrlEncodeToString("{\"typ\":\"JWT\", \"alg\":\"ed25519-nkey\"}");

    private static final long NO_LIMIT = -1;

    /**
     * Format string with `%s` placeholder for the JWT token followed
     * by the user NKey seed. This can be directly used as such:
     *
     * <pre>
     * NKey userKey = NKey.createUser(new SecureRandom());
     * NKey signingKey = loadFromSecretStore();
     * String jwt = issueUserJWT(signingKey, accountId, new String(userKey.getPublicKey()));
     * String.format(JwtUtils.NATS_USER_JWT_FORMAT, jwt, new String(userKey.getSeed()));
     * </pre>
     */
    public static final String NATS_USER_JWT_FORMAT = "-----BEGIN NATS USER JWT-----\n" + "%s\n" + "------END NATS USER JWT------\n" + "\n" + "************************* IMPORTANT *************************\n" + "NKEY Seed printed below can be used to sign and prove identity.\n" + "NKEYs are sensitive and should be treated as secrets.\n" + "\n" + "-----BEGIN USER NKEY SEED-----\n" + "%s\n" + "------END USER NKEY SEED------\n" + "\n" + "*************************************************************\n";

    /**
     * Get the current time in seconds since epoch. Used for issue time.
     * @return the time
     */
    public static long currentTimeSeconds() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Issue a user JWT from a scoped signing key. See <a href="https://docs.nats.io/nats-tools/nsc/signing_keys">Signing Keys</a>
     * @param signingKey a mandatory account nkey pair to sign the generated jwt.
     * @param accountId a mandatory public account nkey. Will throw error when not set or not account nkey.
     * @param publicUserKey a mandatory public user nkey. Will throw error when not set or not user nkey.
     * @throws IllegalArgumentException if the accountId or publicUserKey is not a valid public key of the proper type
     * @throws NullPointerException if signingKey, accountId, or publicUserKey are null.
     * @throws GeneralSecurityException if SHA-256 MessageDigest is missing, or if the signingKey can not be used for signing.
     * @throws IOException if signingKey sign method throws this exception.
     * @return a JWT
     */
    public static String issueUserJWT(NKey signingKey, String accountId, String publicUserKey) throws GeneralSecurityException, IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Issue a user JWT from a scoped signing key. See <a href="https://docs.nats.io/nats-tools/nsc/signing_keys">Signing Keys</a>
     * @param signingKey a mandatory account nkey pair to sign the generated jwt.
     * @param accountId a mandatory public account nkey. Will throw error when not set or not account nkey.
     * @param publicUserKey a mandatory public user nkey. Will throw error when not set or not user nkey.
     * @param name optional human-readable name. When absent, default to publicUserKey.
     * @throws IllegalArgumentException if the accountId or publicUserKey is not a valid public key of the proper type
     * @throws NullPointerException if signingKey, accountId, or publicUserKey are null.
     * @throws GeneralSecurityException if SHA-256 MessageDigest is missing, or if the signingKey can not be used for signing.
     * @throws IOException if signingKey sign method throws this exception.
     * @return a JWT
     */
    public static String issueUserJWT(NKey signingKey, String accountId, String publicUserKey, String name) throws GeneralSecurityException, IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Issue a user JWT from a scoped signing key. See <a href="https://docs.nats.io/nats-tools/nsc/signing_keys">Signing Keys</a>
     * @param signingKey a mandatory account nkey pair to sign the generated jwt.
     * @param accountId a mandatory public account nkey. Will throw error when not set or not account nkey.
     * @param publicUserKey a mandatory public user nkey. Will throw error when not set or not user nkey.
     * @param name optional human-readable name. When absent, default to publicUserKey.
     * @param expiration optional but recommended duration, when the generated jwt needs to expire. If not set, JWT will not expire.
     * @param tags optional list of tags to be included in the JWT.
     * @throws IllegalArgumentException if the accountId or publicUserKey is not a valid public key of the proper type
     * @throws NullPointerException if signingKey, accountId, or publicUserKey are null.
     * @throws GeneralSecurityException if SHA-256 MessageDigest is missing, or if the signingKey can not be used for signing.
     * @throws IOException if signingKey sign method throws this exception.
     * @return a JWT
     */
    public static String issueUserJWT(NKey signingKey, String accountId, String publicUserKey, String name, Duration expiration, String... tags) throws GeneralSecurityException, IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Issue a user JWT from a scoped signing key. See <a href="https://docs.nats.io/nats-tools/nsc/signing_keys">Signing Keys</a>
     * @param signingKey a mandatory account nkey pair to sign the generated jwt.
     * @param accountId a mandatory public account nkey. Will throw error when not set or not account nkey.
     * @param publicUserKey a mandatory public user nkey. Will throw error when not set or not user nkey.
     * @param name optional human-readable name. When absent, default to publicUserKey.
     * @param expiration optional but recommended duration, when the generated jwt needs to expire. If not set, JWT will not expire.
     * @param tags optional list of tags to be included in the JWT.
     * @param issuedAt the current epoch seconds.
     * @throws IllegalArgumentException if the accountId or publicUserKey is not a valid public key of the proper type
     * @throws NullPointerException if signingKey, accountId, or publicUserKey are null.
     * @throws GeneralSecurityException if SHA-256 MessageDigest is missing, or if the signingKey can not be used for signing.
     * @throws IOException if signingKey sign method throws this exception.
     * @return a JWT
     */
    public static String issueUserJWT(NKey signingKey, String accountId, String publicUserKey, String name, Duration expiration, String[] tags, long issuedAt) throws GeneralSecurityException, IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Issue a user JWT from a scoped signing key. See <a href="https://docs.nats.io/nats-tools/nsc/signing_keys">Signing Keys</a>
     * @param signingKey a mandatory account nkey pair to sign the generated jwt.
     * @param accountId a mandatory public account nkey. Will throw error when not set or not account nkey.
     * @param publicUserKey a mandatory public user nkey. Will throw error when not set or not user nkey.
     * @param name optional human-readable name. When absent, default to publicUserKey.
     * @param expiration optional but recommended duration, when the generated jwt needs to expire. If not set, JWT will not expire.
     * @param tags optional list of tags to be included in the JWT.
     * @param issuedAt the current epoch seconds.
     * @param audience the audience value
     * @return a JWT
     * @throws GeneralSecurityException if SHA-256 MessageDigest is missing, or if the signingKey can not be used for signing.
     * @throws IOException if signingKey sign method throws this exception.
     */
    public static String issueUserJWT(NKey signingKey, String accountId, String publicUserKey, String name, Duration expiration, String[] tags, long issuedAt, String audience) throws GeneralSecurityException, IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Issue a user JWT from a scoped signing key. See <a href="https://docs.nats.io/nats-tools/nsc/signing_keys">Signing Keys</a>
     * @param signingKey a mandatory account nkey pair to sign the generated jwt.
     * @param publicUserKey a mandatory public user nkey. Will throw error when not set or not user nkey.
     * @param name optional human-readable name. When absent, default to publicUserKey.
     * @param expiration optional but recommended duration, when the generated jwt needs to expire. If not set, JWT will not expire.
     * @param issuedAt the current epoch seconds.
     * @param nats the user claim
     * @throws IllegalArgumentException if the accountId or publicUserKey is not a valid public key of the proper type
     * @throws NullPointerException if signingKey, accountId, or publicUserKey are null.
     * @throws GeneralSecurityException if SHA-256 MessageDigest is missing, or if the signingKey can not be used for signing.
     * @throws IOException if signingKey sign method throws this exception.
     * @return a JWT
     */
    public static String issueUserJWT(NKey signingKey, String publicUserKey, String name, Duration expiration, long issuedAt, UserClaim nats) throws GeneralSecurityException, IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Issue a user JWT from a scoped signing key. See <a href="https://docs.nats.io/nats-tools/nsc/signing_keys">Signing Keys</a>
     * @param signingKey a mandatory account nkey pair to sign the generated jwt.
     * @param publicUserKey a mandatory public user nkey. Will throw error when not set or not user nkey.
     * @param name optional human-readable name. When absent, default to publicUserKey.
     * @param expiration optional but recommended duration, when the generated jwt needs to expire. If not set, JWT will not expire.
     * @param issuedAt the current epoch seconds.
     * @param audience the optional audience
     * @param nats the user claim
     * @throws IllegalArgumentException if the accountId or publicUserKey is not a valid public key of the proper type
     * @throws NullPointerException if signingKey, accountId, or publicUserKey are null.
     * @throws GeneralSecurityException if SHA-256 MessageDigest is missing, or if the signingKey can not be used for signing.
     * @throws IOException if signingKey sign method throws this exception.
     * @return a JWT
     */
    public static String issueUserJWT(NKey signingKey, String publicUserKey, String name, Duration expiration, long issuedAt, String audience, UserClaim nats) throws GeneralSecurityException, IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Issue a JWT
     * @param signingKey account nkey pair to sign the generated jwt.
     * @param publicUserKey a mandatory public user nkey.
     * @param name optional human-readable name.
     * @param expiration optional but recommended duration, when the generated jwt needs to expire. If not set, JWT will not expire.
     * @param issuedAt the current epoch seconds.
     * @param accSigningKeyPub the account signing key
     * @param nats the generic nats claim
     * @throws GeneralSecurityException if SHA-256 MessageDigest is missing, or if the signingKey can not be used for signing.
     * @throws IOException if signingKey sign method throws this exception.
     * @return a JWT
     */
    public static String issueJWT(NKey signingKey, String publicUserKey, String name, Duration expiration, long issuedAt, String accSigningKeyPub, JsonSerializable nats) throws GeneralSecurityException, IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Issue a JWT
     *
     * @param signingKey       account nkey pair to sign the generated jwt.
     * @param publicUserKey    a mandatory public user nkey.
     * @param name             optional human-readable name.
     * @param expiration       optional but recommended duration, when the generated jwt needs to expire. If not set, JWT will not expire.
     * @param issuedAt         the current epoch seconds.
     * @param accSigningKeyPub the account signing key
     * @param audience         the optional audience
     * @param nats             the generic nats claim
     * @return a JWT
     * @throws GeneralSecurityException if SHA-256 MessageDigest is missing, or if the signingKey can not be used for signing.
     * @throws IOException              if signingKey sign method throws this exception.
     */
    public static String issueJWT(NKey signingKey, String publicUserKey, String name, Duration expiration, long issuedAt, String accSigningKeyPub, String audience, JsonSerializable nats) throws GeneralSecurityException, IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the claim body from a JWT
     * @param jwt the encoded jwt
     * @return the claim body json
     */
    public static String getClaimBody(String jwt) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static class UserClaim implements JsonSerializable {

        // User
        public String issuerAccount;

        // User/GenericFields
        public String[] tags;

        // User/GenericFields
        public String type = "user";

        // User/GenericFields
        public int version = 2;

        // User/UserPermissionLimits/Permissions
        public Permission pub;

        // User/UserPermissionLimits/Permissions
        public Permission sub;

        // User/UserPermissionLimits/Permissions
        public ResponsePermission resp;

        // User/UserPermissionLimits/Limits/UserLimits
        public String[] src;

        // User/UserPermissionLimits/Limits/UserLimits
        public List<TimeRange> times;

        // User/UserPermissionLimits/Limits/UserLimits
        public String locale;

        // User/UserPermissionLimits/Limits/NatsLimits
        public long subs = NO_LIMIT;

        // User/UserPermissionLimits/Limits/NatsLimits
        public long data = NO_LIMIT;

        // User/UserPermissionLimits/Limits/NatsLimits
        public long payload = NO_LIMIT;

        // User/UserPermissionLimits
        public boolean bearerToken;

        // User/UserPermissionLimits
        public String[] allowedConnectionTypes;

        public UserClaim(String issuerAccount) {
            this.issuerAccount = issuerAccount;
        }

        @Override
        @NonNull
        public String toJson() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public UserClaim tags(String... tags) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public UserClaim pub(Permission pub) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public UserClaim sub(Permission sub) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public UserClaim resp(ResponsePermission resp) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public UserClaim src(String... src) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public UserClaim times(List<TimeRange> times) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public UserClaim locale(String locale) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public UserClaim subs(long subs) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public UserClaim data(long data) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public UserClaim payload(long payload) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public UserClaim bearerToken(boolean bearerToken) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public UserClaim allowedConnectionTypes(String... allowedConnectionTypes) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    public static class TimeRange implements JsonSerializable {

        public String start;

        public String end;

        public TimeRange(String start, String end) {
            this.start = start;
            this.end = end;
        }

        @Override
        @NonNull
        public String toJson() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    public static class ResponsePermission implements JsonSerializable {

        public int maxMsgs;

        public Duration expires;

        public ResponsePermission maxMsgs(int maxMsgs) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public ResponsePermission expires(Duration expires) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public ResponsePermission expires(long expiresMillis) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        @NonNull
        public String toJson() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    public static class Permission implements JsonSerializable {

        public String[] allow;

        public String[] deny;

        public Permission allow(String... allow) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Permission deny(String... deny) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        @NonNull
        public String toJson() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    static class Claim implements JsonSerializable {

        String aud;

        String jti;

        long iat;

        String iss;

        String name;

        String sub;

        Duration exp;

        JsonSerializable nats;

        @Override
        @NonNull
        public String toJson() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
