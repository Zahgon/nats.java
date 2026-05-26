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

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Random;

public abstract class RandomUtils {

    private RandomUtils() {
    }

    /* ensures cannot be constructed */
    public static final SecureRandom SRAND = new SecureRandom();

    // seed with 8 bytes (64 bits)
    public static final Random PRAND = new Random(bytesToLong(SRAND.generateSeed(8)));

    public static long nextLong(Random rng, long maxValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static long bytesToLong(byte[] bytes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
