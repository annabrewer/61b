/**
 * Created by Anna on 2/7/17.
 */

import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDeque1B {

    @Test
    public void testArrayDeque() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<Integer>();
        StudentArrayDeque<Integer> answer = new StudentArrayDeque<Integer>();
        int length = 0;
        String message = "";
        for (int  i = 0; i < 500; i++) {
            int method = StdRandom.uniform(5);
            int number = StdRandom.uniform(9);
            if (method == 0){
                message += ("addFirst("+number+")");
                length += 1;
                message += "\n";
            }
            else if (method == 1){
                message += ("addLast("+number+")");
                length += 1;
                message += "\n";
            }
            else if (method == 2){
                if (length > 0) {
                    message += "removeFirst()";
                    assertEquals(message, student.removeFirst(), answer.removeFirst());
                    length -= 1;
                    message += "\n";
                }
            }
            else if (method == 3){
                if (length > 0) {
                    message += "removeLast()";
                    assertEquals(message, student.removeLast(), answer.removeLast());
                    length -= 1;
                    message += "\n";
                }
            }
            else if(method == 4){
                message += "size()";
                assertEquals(message, student.size(), answer.size());
                message += "\n";
            }
            else if(method == 5){
                int index = StdRandom.uniform(length);
                message += "get()";
                assertEquals(message, student.get(index), answer.get(index));
                message += "\n";
            }
        }
    }
}
