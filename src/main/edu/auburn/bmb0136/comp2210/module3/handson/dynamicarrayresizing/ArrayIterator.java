package edu.auburn.bmb0136.comp2210.module3.handson.dynamicarrayresizing;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ArrayIterator.java
 * Provides iteration behavior over an array of elements.
 */
public class ArrayIterator<T> implements Iterator<T> {

    // the array of elements to iterate over
    private final T[] elements;
    // the number of elements in the array, beginning at index zero
    private final int count;
    // the index of the next element in the iteration sequence
    private int current;

    /**
     * Construct a properly initialized iterator.
     *
     * @param elem the array to be iterated over
     * @param size the number of elements in the array
     */
    public ArrayIterator(T[] elem, int size) {
        elements = elem;
        count = size;
        current = 0;
    }

    /**
     * Returns true if there is at least one more element remaining
     * in the iteration sequence.
     *
     * @return true if there is a next element to iterate over
     */
    public boolean hasNext() {
        return current < count;
    }

    /**
     * Returns the next element in the iteration sequence.
     *
     * @return the next element in the iteration sequence
     */
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return elements[current++];
    }

    /**
     * Unsupported operation.
     */
    public void remove() {
        throw new UnsupportedOperationException();
    }
}