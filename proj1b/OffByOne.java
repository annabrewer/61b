/**
 * Created by Anna on 2/8/17.
 */
public class OffByOne implements CharacterComparator {
    public boolean equalChars(char x, char y) {
        if (x-y == 1 || x-y == -1) {
            return true;
        }
        return false;
    }
}
