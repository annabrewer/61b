package lab9;

/**
 * Created by Anna on 3/17/17.
 */
public class Entry<K, V> {
    K key;
    V val;

    public Entry(K keyInput, V valInput) {
        key = keyInput;
        val = valInput;
    }

    public void setKey(K keyInput) {
        key = keyInput;
    }

    public void setValue(V valInput) {
        val = valInput;
    }
}
