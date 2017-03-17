package lab9;

import java.util.*;

/**
 * Created by Anna on 3/17/17.
 */
public class MyHashMap<K,V> implements Map61B<K, V> {

    HashSet<K> keySet = new HashSet<K>();
    LinkedList[] arr;
    double LF;
    int len;


    public MyHashMap() {
        LF = 1;
        len = 4;
        arr = new LinkedList[len];
    }

    public MyHashMap(int initialSize) {
        len = initialSize;
        arr = new LinkedList[len];
        LF = 1;
    }

    public MyHashMap(int initialSize, double loadFactor) {
        len = initialSize;
        arr = new LinkedList[len];
        LF = loadFactor;
    }

    /** Removes all of the mappings from this map. */
    public void clear(){
        arr = new LinkedList[len];
    }

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        return keySet.contains(key);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        int index = (int)((key.hashCode())%len);
        LinkedList<Entry> l = arr[index];
        ListIterator<Entry> entries = l.listIterator();
        while(entries.hasNext()) {
            Entry e = entries.next();
            if(e.key == key) {
                return (V) e.val;
            }
        }
        return null; //key does not exist
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        int rv = 0;
        for (int i = 0; i < arr.length; i++) {
            LinkedList<Entry> l = arr[i];
            ListIterator<Entry> entries = l.listIterator();
            while(entries.hasNext()) {
                Entry e = entries.next();
                rv += 1;
            }
        }
        return rv;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
        keySet.add(key);
        int index = (int)((key.hashCode())%len);
        LinkedList<Entry> l;
        if (arr[index] == null) {
            l = new LinkedList<Entry>();
            arr[index] = l;
        }
        else {
            l = arr[index];
        }
        Entry e = new Entry(key, value);
        l.add(e);
        //find length
        //int entryLength = keySet.size();
        int entryLength = 0;
        ListIterator<Entry> entries = l.listIterator();
        while(entries.hasNext()) {
            Entry en = entries.next();
            entryLength += 1;
        }

        if ((entryLength/len) > LF) {
            LinkedList[] temp = new LinkedList[len*2];
            for (int i = 0; i < arr.length; i++) {
                if(arr[i] != null) {
                    LinkedList<Entry> ll = arr[i];
                    ListIterator<Entry> entries2 = ll.listIterator();
                    while(entries2.hasNext()) {
                        temp[i] = new LinkedList<Entry>();
                        temp[i].add(entries2.next());
                    }
                }
            }
        }
    }

    /* Returns a Set view of the keys contained in this map. */
    public Set<K> keySet(){
        return (Set) keySet;
        /*Set<K> rv;
        Iterator iter = keySet.iterator();
        while(iter.hasNext()) {
            K e = iter.next();
            rv.add((K)e);
        }*/

    }

    public Iterator<K> iterator() {
        return keySet.iterator();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

}
