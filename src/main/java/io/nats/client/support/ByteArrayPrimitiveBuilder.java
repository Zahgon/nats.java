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
package io.nats.client.support;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import static java.nio.charset.StandardCharsets.ISO_8859_1;

/**
 * A class that wraps a byte array that can automatically grow
 */
public class ByteArrayPrimitiveBuilder extends BuilderBase {

    private byte[] buffer;

    private int position;

    /**
     * Construct the ByteArrayPrimitiveBuilder with the supplied initial size,
     * allocation size and character set
     * @param initialSize the initial size
     * @param allocationSizeSuggestion the allocationSize size suggestion
     * @param defaultCharset the default character set
     */
    public ByteArrayPrimitiveBuilder(int initialSize, int allocationSizeSuggestion, Charset defaultCharset) {
        super(defaultCharset, allocationSizeSuggestion);
        this.buffer = new byte[bufferAllocSize(initialSize, allocationSize)];
        position = 0;
    }

    /**
     * Construct the ByteArrayPrimitiveBuilder with
     * the initial size and allocation size of {@value #DEFAULT_ASCII_ALLOCATION}
     * and the character set {@link java.nio.charset.StandardCharsets#ISO_8859_1}
     * since ISO_8859_1 is faster when encoding and decoding than US_ASCII
     */
    public ByteArrayPrimitiveBuilder() {
        this(-1, DEFAULT_ASCII_ALLOCATION, ISO_8859_1);
    }

    /**
     * Construct the ByteArrayPrimitiveBuilder with the supplied initial size,
     * allocation size of {@value #DEFAULT_ASCII_ALLOCATION}
     * and the character set {@link java.nio.charset.StandardCharsets#ISO_8859_1}
     * since ISO_8859_1 is faster when encoding and decoding than US_ASCII
     * @param initialSize the initial size
     */
    public ByteArrayPrimitiveBuilder(int initialSize) {
        this(initialSize, DEFAULT_ASCII_ALLOCATION, ISO_8859_1);
    }

    /**
     * Construct the ByteArrayPrimitiveBuilder with the supplied character set
     * with the default initial size and allocation size determined by that character set
     * @param defaultCharset the default character set
     */
    public ByteArrayPrimitiveBuilder(Charset defaultCharset) {
        this(-1, -1, defaultCharset);
    }

    /**
     * Construct the ByteArrayPrimitiveBuilder with the supplied initial size and character set
     * with the allocation size determined by that character set.
     * @param initialSize the initial size
     * @param defaultCharset the default character set
     */
    public ByteArrayPrimitiveBuilder(int initialSize, Charset defaultCharset) {
        this(initialSize, -1, defaultCharset);
    }

    /**
     * Construct the ByteArrayPrimitiveBuilder copying all the bytes
     * using the character set {@link java.nio.charset.StandardCharsets#ISO_8859_1}
     * since ISO_8859_1 is faster when encoding and decoding than US_ASCII
     * Then initializes the buffer with the supplied bytes
     * @param bytes the bytes
     */
    public ByteArrayPrimitiveBuilder(byte[] bytes) {
        this(bytes, bytes.length);
    }

    /**
     * Construct the ByteArrayPrimitiveBuilder copying the specified number of bytes;
     * and the character set {@link java.nio.charset.StandardCharsets#ISO_8859_1}
     * since ISO_8859_1 is faster when encoding and decoding than US_ASCII
     * Then initializes the buffer with the supplied bytes
     * @param bytes the bytes
     * @param len the number of bytes to copy
     */
    public ByteArrayPrimitiveBuilder(byte[] bytes, int len) {
        super(ISO_8859_1, DEFAULT_ASCII_ALLOCATION);
        position = len;
        buffer = new byte[bufferAllocSize(len, allocationSize)];
        System.arraycopy(bytes, 0, buffer, 0, len);
    }

    /**
     * Get the length of the data in the buffer
     * @return the length of the data
     */
    @Override
    public int length() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the number of bytes currently allocated (available) without resizing
     * @return the number of bytes
     */
    @Override
    public int capacity() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Determine if a byte array contains the same bytes as this builder
     * @param bytes the bytes
     * @return true if the supplied value equals what is in the builder
     */
    @Override
    public boolean equals(byte[] bytes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Copy the contents of the buffer to the byte array starting at the destination
     * positions supplied. Assumes that the {@link #position} method has been called
     * and the destination byte array has enough space allocated
     * @param dest the destination byte array
     * @param destPos the starting position in the destination byte array
     * @return the number of bytes copied
     */
    public int copyTo(byte[] dest, int destPos) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Copy the contents of the buffer to the output stream
     * @param out the output stream
     * @throws IOException if an I/O error occurs
     */
    public void copyTo(OutputStream out) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Copy the value in the buffer to a new byte array
     * @return the copy of the bytes
     */
    @Override
    public byte[] toByteArray() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Access the internal byte array of this buffer. Intended for read only
     * with knowledge of {@link #length()}
     * @return a direct handle to the internal byte array
     */
    @Override
    public byte[] internalArray() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Ensures that the buffer can accept the number of bytes needed by resizing if necessary.
     * Useful if the size of multiple append operations is known ahead of time,
     * reducing the number of resizes
     * @param bytesNeeded the number of bytes needed
     */
    public void ensureCapacity(int bytesNeeded) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Clear the buffer, resetting its length but not capacity
     * @return this (fluent)
     */
    public ByteArrayPrimitiveBuilder clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Change the allocation size
     * @param allocationSizeSuggestion the new allocation size suggestion
     */
    public void setAllocationSize(int allocationSizeSuggestion) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Append a String representation of the number.
     * @param  i the number
     * @return this (fluent)
     */
    public ByteArrayPrimitiveBuilder append(int i) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Append a String with the default charset.
     * If the src is null, the word 'null' is appended.
     * @param src The String from which bytes are to be read
     * @return this (fluent)
     */
    public ByteArrayPrimitiveBuilder append(String src) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Append a String with specified charset.
     * If the src is null, the word 'null' is appended.
     * @param src The String from which bytes are to be read
     * @param charset the charset for encoding
     * @return this (fluent)
     */
    public ByteArrayPrimitiveBuilder append(String src, Charset charset) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Append a CharBuffer with default charset.
     * If the src is null, the word 'null' is appended.
     * @param src The CharBuffer from which bytes are to be read
     * @return this (fluent)
     */
    public ByteArrayPrimitiveBuilder append(CharBuffer src) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Append a CharBuffer with specified charset.
     * If the src is null, the word 'null' is appended.
     * @param src The CharBuffer from which bytes are to be read
     * @param charset the charset for encoding
     * @return this (fluent)
     */
    public ByteArrayPrimitiveBuilder append(CharBuffer src, Charset charset) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Append a byte as is
     * @param b the byte
     * @return this (fluent)
     */
    public ByteArrayPrimitiveBuilder append(byte b) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Append an entire byte array
     * @param src The array from which bytes are to be read
     * @return this (fluent)
     */
    public ByteArrayPrimitiveBuilder append(byte[] src) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Append len bytes from the start of byte array
     * @param src The array from which bytes are to be read
     * @param len The number of bytes to be read from the given array
     * @return this (fluent)
     */
    public ByteArrayPrimitiveBuilder append(byte[] src, int len) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Append a byte array
     * @param src The array from which bytes are to be read
     * @param index The index in the array of the first byte to be read;
     * @param  len The number of bytes to be read from the given array;
     * @return this (fluent)
     */
    public ByteArrayPrimitiveBuilder append(byte[] src, int index, int len) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Appends the data bytes from an existing byte array primitive builder
     * @param bab an existing builder
     * @return this (fluent)
     */
    public ByteArrayPrimitiveBuilder append(ByteArrayPrimitiveBuilder bab) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int appendUnchecked(byte b) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int appendUnchecked(byte[] src) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int appendUnchecked(byte[] src, int srcPos, int len) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
