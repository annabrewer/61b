/**
 * Created by Anna on 2/8/17.
 */
public class OffByN implements CharacterComparator {
    private int n;

    public OffByN(int input) {
        n = input;
    }
    public boolean equalChars(char x, char y) {
        if (x - y == n || x - y == -n) {
            return true;
        }
        return false;
    }
}
