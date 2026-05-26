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
package io.nats.client.impl;

public class SSLContextFactoryProperties {

    public final String keystorePath;

    public final char[] keystorePassword;

    public final String truststorePath;

    public final char[] truststorePassword;

    public final String tlsAlgorithm;

    private SSLContextFactoryProperties(Builder b) {
        this.keystorePath = b.keystore;
        this.keystorePassword = b.keystorePassword;
        this.truststorePath = b.truststore;
        this.truststorePassword = b.truststorePassword;
        this.tlsAlgorithm = b.tlsAlgorithm;
    }

    public String getKeystorePath() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public char[] getKeystorePassword() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getTruststorePath() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public char[] getTruststorePassword() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getTlsAlgorithm() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static class Builder {

        String keystore;

        char[] keystorePassword;

        String truststore;

        char[] truststorePassword;

        String tlsAlgorithm;

        public Builder keystore(String keystore) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder keystorePassword(char[] keystorePassword) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder truststore(String truststore) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder truststorePassword(char[] truststorePassword) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder tlsAlgorithm(String tlsAlgorithm) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public SSLContextFactoryProperties build() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
