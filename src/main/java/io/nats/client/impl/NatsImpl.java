// Copyright 2015-2018 The NATS Authors
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

import io.nats.client.AuthHandler;
import io.nats.client.Connection;
import io.nats.client.Options;
import io.nats.client.Statistics;
import java.io.IOException;

/**
 * Adapter to impl package to minimize access leakage.
 */
public class NatsImpl {

    public static Connection createConnection(Options options, boolean reconnectOnConnect) throws IOException, InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Statistics createEmptyStats() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create an AuthHandler from a credentials file that contains the jwt and nkey
     * @param credsFile the fully qualified path to the file
     * @return an AuthHandler implementation
     */
    public static AuthHandler credentials(String credsFile) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create an AuthHandler from individual files for the jwt and the nkey
     * @param jwtFile the fully qualified path to the jwt file
     * @param nkeyFile the fully qualified path to the nkey file
     * @return an AuthHandler implementation
     */
    public static AuthHandler credentials(String jwtFile, String nkeyFile) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create an AuthHandler from a bytes representing a credentials file that contains the jwt and nkey
     * @param credsBytes the bytes of the file
     * @return an AuthHandler implementation
     */
    public static AuthHandler staticCredentials(byte[] credsBytes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create an AuthHandler from char arrays representing the jwt and the nkey
     * @param jwt the chars for the jwt
     * @param nkey the chars for the nkey
     * @return an AuthHandler implementation
     */
    public static AuthHandler staticCredentials(char[] jwt, char[] nkey) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
