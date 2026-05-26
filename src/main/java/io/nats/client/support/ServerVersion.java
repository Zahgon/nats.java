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
package io.nats.client.support;

public class ServerVersion implements Comparable<ServerVersion> {

    final Integer major;

    final Integer minor;

    final Integer patch;

    final String extra;

    public ServerVersion(String v) {
        int mjr;
        int mnr = -1;
        int ptch = -1;
        String xtra = null;
        try {
            String[] split;
            if (v.startsWith("v")) {
                split = v.substring(1).replace("-", ".").split("\\Q.\\E");
            } else {
                split = v.replace("-", ".").split("\\Q.\\E");
            }
            mjr = Integer.parseInt(split[0]);
            mnr = Integer.parseInt(split[1]);
            ptch = split.length < 3 ? -1 : Integer.parseInt(split[2]);
            for (int i = 3; i < split.length; i++) {
                if (i == 3) {
                    xtra = "-" + split[i];
                } else {
                    //noinspection StringConcatenationInLoop
                    xtra = xtra + "." + split[i];
                }
            }
        } catch (NumberFormatException nfe) {
            mjr = -1;
        }
        if (mjr == -1) {
            major = -1;
            minor = -1;
            patch = -1;
            extra = null;
        } else {
            major = mjr;
            minor = mnr;
            patch = ptch;
            extra = xtra;
        }
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int compareTo(ServerVersion o) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean isNewer(String v, String than) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean isSame(String v, String than) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean isOlder(String v, String than) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean isSameOrOlder(String v, String than) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean isSameOrNewer(String v, String than) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
