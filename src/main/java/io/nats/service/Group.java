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
package io.nats.service;

import io.nats.client.support.Validator;
import java.util.Objects;
import static io.nats.client.support.NatsConstants.DOT;
import static io.nats.client.support.NatsConstants.GREATER_THAN;
import static io.nats.client.support.Validator.emptyAsNull;

/**
 * Group is way to organize endpoints by serving as a common prefix to all endpoints registered in it.
 */
public class Group {

    private final String name;

    private Group next;

    /**
     * Construct a group.
     * <p>Group names are considered 'Restricted Terms' and must only contain A-Z, a-z, 0-9, '-' or '_'</p>
     * @param name the group name
     */
    public Group(String name) {
        name = emptyAsNull(name);
        if (name == null) {
            throw new IllegalArgumentException("Group name cannot be null or empty.");
        }
        if (name.contains(GREATER_THAN)) {
            throw new IllegalArgumentException("Group name cannot contain '>'.");
        }
        this.name = Validator.validateSubjectTermStrict(name, "Group name", false);
    }

    /**
     * Append a group at the end of the list of groups this group starts or is a part of.
     * Appended groups can be traversed by doing {@link #getNext}
     * Subsequent appends add the group to the end of the list.
     * @param group the group to append
     * @return like a fluent builder, return the Group instance
     */
    public Group appendGroup(Group group) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the resolved subject of a group by concatenating the group name and any groups.
     * For example, this:
     * <code>
     * Group g = new Group("A")
     *     .appendGroup(new Group("B"))
     *     .appendGroup(new Group("C"))
     *     .appendGroup(new Group("D"));
     * System.out.println(g.getSubject());
     * </code>
     * prints "A.B.C.D"
     * @return the subject
     */
    public String getSubject() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the name of the group.
     * @return the name
     */
    public String getName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the next group after this group. May be null
     * @return the next group
     */
    public Group getNext() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
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
}
