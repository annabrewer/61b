/**
 * Created by Anna on 2/7/17.
 */

import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDeque1B {
    String message = "";
    int length = 0;
    StudentArrayDeque<Integer> student = new StudentArrayDeque<Integer>();
    ArrayDequeSolution<Integer> answer = new ArrayDequeSolution<Integer>();
    @Test
    public void testArrayDeque() {
        for (int i = 0; i < 1000; i++) {
            int number = StdRandom.uniform(4);
            if (0 <= number && number < 1) {
                addFirst();
            } else if (1 <= number && number < 2) {
                addLast();
            } else if (2 <= number && number < 3) {
                removeFirst();
            } else if (3 <= number && number < 4) {
                removeLast();
            }
        }
    }

    private void addFirst() {
        int number = StdRandom.uniform(9);
        message += ("addFirst(" + number + ")");
        student.addFirst(number);
        answer.addFirst(number);
        message += "\n";
        length += 1;
    }

    private void addLast() {
        int number = StdRandom.uniform(9);
        message += ("addLast(" + number + ")");
        student.addLast(number);
        answer.addLast(number);
        message += "\n";
        length += 1;
    }

    private void removeFirst() {
        if (length > 0) {
            message += "removeFirst()";
            assertEquals(message, answer.removeFirst(), student.removeFirst());
            message += "\n";
            length -= 1;
        }
    }

    private void removeLast() {
        if (length > 0) {
            message += "removeLast()";
            assertEquals(message, answer.removeFirst(), student.removeFirst());
            message += "\n";
            length -= 1;
        }
    }
}
