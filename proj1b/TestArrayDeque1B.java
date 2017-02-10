/**
 * Created by Anna on 2/7/17.
 */

import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDeque1B {

    @Test
    public void testArrayDeque() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<Integer>();
        ArrayDequeSolution<Integer> answer = new ArrayDequeSolution<Integer>();
        int length = 0;
        String message = "";
        for (int  i = 0; i < 1000; i++) {
            double method = StdRandom.uniform();
            int number = StdRandom.uniform(9);
            if (method < .5) {
                message += ("addFirst(" + number + ")");
                student.addFirst(number);
                answer.addFirst(number);
                message += "\n";
            } else {
                message += ("addLast(" + number + ")");
                student.addLast(number);
                answer.addLast(number);
                message += "\n";
            }
        }
        for (int  i = 0; i < 999; i++) {
            double method = StdRandom.uniform();
            if (method < .5) {
                message += "removeFirst()";
                assertEquals(message, answer.removeFirst(), student.removeFirst());
                message += "\n";

            } else {
                message += "removeLast()";
                //int m = student.removeLast();
                //int n = answer.removeLast();
                assertEquals(message, answer.removeFirst(), student.removeFirst());
                message += "\n";

            }
        }
        //System.out.print(message);
        /*for (int  i = 0; i < 500; i++) {
            if (method == 0) {
                message += "size()";
                assertEquals(message, student.size(), answer.size());
                message += "\n";
            } else if (method == 1) {
                int index = StdRandom.uniform(length);
                message += "get()";
                assertEquals(message, student.get(index), answer.get(index));
                message += "\n";
            }
        }*/
    }
}
