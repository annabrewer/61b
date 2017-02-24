package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(4);
        assertEquals(true, arb.isEmpty());      //(returns true)
        System.out.println(arb.first);
        System.out.println(arb.last);
        arb.enqueue(9.3);   // 9.3
        System.out.println(arb.first);
        System.out.println(arb.last);
        arb.enqueue(15.1);   // 9.3  15.1
        System.out.println(arb.first);
        System.out.println(arb.last);
        arb.enqueue(31.2);   // 9.3  15.1  31.2
        assertEquals(false, arb.isFull());        // 9.3  15.1  31.2       (returns false)
        System.out.println(arb.first);
        System.out.println(arb.last);
        arb.enqueue(-3.1);   // 9.3  15.1  31.2  -3.1
        assertEquals(true, arb.isFull());      // 9.3  15.1  31.2  -3.1 (returns true)
        for (Object d : arb) {
            System.out.println(d);
        }
        System.out.println(arb.first);
        System.out.println(arb.last);
        assertEquals(9.3, arb.dequeue());       // 15.1 31.2  -3.1       (returns 9.3)

        assertEquals(15.1, arb.peek()); // 15.1 31.2  -3.1       (returns 15.1)
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
