import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        // Your code here!
        Queue<Queue<Item>> result = new Queue<Queue<Item>>();
        while (!items.isEmpty()) {
            Queue<Item> thing = new Queue<Item>();
            thing.enqueue(items.dequeue());
            result.enqueue(thing);
        }
        return result;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> result = new Queue<Item>();
        while (q1.size() > 0 && q2.size() > 0) {
            result.enqueue(getMin(q1, q2));
        }
        if (q1.size() == 0) {
            while (q2.size() > 0) {
                result.enqueue(q2.dequeue());
            }
        }
        if (q2.size() == 0) {
            while (q1.size() > 0) {
                result.enqueue(q1.dequeue());
            }
        }
        return result;

    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        // Your code here!
        Queue<Item> result = new Queue<Item>();
        Queue<Queue<Item>> qq = new Queue<Queue<Item>>();
        qq = MergeSort.makeSingleItemQueues(items);
        result = mergeSortedQueues(qq.dequeue(), qq.dequeue());
        while(qq.size() > 0) {
            result = mergeSortedQueues(result, qq.dequeue());
        }
        return result;
    }

    public static void main(String[] args){
        Queue<Integer> students = new Queue<Integer>();
        students.enqueue(5);
        students.enqueue(3);
        students.enqueue(4);
        students.enqueue(2);
        System.out.println(MergeSort.mergeSort(students));
    }
}