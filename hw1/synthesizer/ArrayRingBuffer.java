package synthesizer;

import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.

        rb = (T[]) new Object[capacity];
        this.capacity = capacity;
        first = capacity / 2;
        last = ((first + 1) % capacity());
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        } else {

            rb[last] = x;
            fillCount += 1;
            last = ((last + 1) % capacity());
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            T rv = rb[first];
            first = ((first + 1) % capacity());
            fillCount -= 1;
            return rv;
        }
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Nothing to see here");
        } else {
            return rb[first];
        }
    }

    public Iterator<T> iterator() {
        RbIterator rbI = new RbIterator();
        return rbI;

    }

    private class RbIterator implements Iterator<T> {
        private int counter;

        private RbIterator() {
            counter = 0;
        }

        public boolean hasNext() {
            return counter != capacity();
        }

        public T next() {
            T rv = rb[counter];
            counter += 1;
            return rv;
        }
    }
}
