// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;

import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
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
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.

        rb = (T[]) new Object[capacity];
        this.capacity = capacity;
        first = capacity / 2;
        last = first + 1;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        Iterator<T> rbIter = iterator();
        if (! rbIter.hasNext()) {
            throw new RuntimeException("Ring Buffer Overflow");
        } else {
            rb[last] = x;
            this.fillCount += 1;
            last = (last % (capacity()-1)) + 1;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        } else {
            T rv = rb[first];
            first = (first % (capacity()-1)) + 1;
            this.fillCount -= 1;
            return rv;
        }
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        return rb[first];
    }

    // TODO: When you get to part 5, implement the needed code to support iteration

    public Iterator<T> iterator() {
        rbIterator rbI = new rbIterator();
        return rbI;

    }

    private class rbIterator implements Iterator<T> {
        private int counter;

        public rbIterator() {
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
