package hw4.hash;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        HashMap<Integer,LinkedList<Oomage>> h = new HashMap<>(M);
        for  (Integer i = 0; i < M; i++) {
            h.put(i, new LinkedList<Oomage>());
        }

        for (Oomage o : oomages) {
            Integer bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            h.get(bucketNum).add(o);
        }

        for  (Integer k : h.keySet()) {
            if (h.get(k).size() < oomages.size()/50 || h.get(k).size() > oomages.size()/2.5) {
                return false;
            }
        }

        return true;
    }
}
