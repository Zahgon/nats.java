// Copyright 2022 The NATS Authors
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

import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.JsonValue;
import io.nats.client.support.Validator;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import static io.nats.client.support.ApiConstants.BUCKET;
import static io.nats.client.support.ApiConstants.NAME;
import static io.nats.client.support.JsonUtils.beginJson;
import static io.nats.client.support.JsonUtils.endJson;
import static io.nats.client.support.JsonValueUtils.readString;

/**
 * The ObjectLink is used to embed links to other objects.
 */
public class ObjectLink implements JsonSerializable {

    private final String bucket;

    private final String objectName;

    static ObjectLink optionalInstance(JsonValue vLink) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    ObjectLink(JsonValue vLink) {
        bucket = readString(vLink, BUCKET);
        objectName = readString(vLink, NAME);
    }

    private ObjectLink(String bucket, String objectName) {
        this.bucket = Validator.validateBucketName(bucket, true);
        this.objectName = objectName;
    }

    @Override
    @NonNull
    public String toJson() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the bucket the linked object is in
     * @return the bucket name
     */
    @NonNull
    public String getBucket() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the name of the object for the link
     * @return the object name
     */
    @Nullable
    public String getObjectName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * True if the object is a link to an object versus a link to a bucket
     * @return true if the object is a link
     */
    public boolean isObjectLink() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * True if the object is a bucket to an object versus a link to a link
     * @return true if the object is a bucket
     */
    public boolean isBucketLink() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * create a bucket link
     * @param bucket the bucket name
     * @return the ObjectLink
     */
    public static ObjectLink bucket(@NonNull String bucket) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * create an object link
     * @param bucket the bucket the object is in
     * @param objectName the object name
     * @return the ObjectLink
     */
    public static ObjectLink object(String bucket, String objectName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean equals(Object o) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
