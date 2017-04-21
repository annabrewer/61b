import java.util.LinkedList;
import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;

/**
 * This class provides a shortestPath method for finding routes between two points
 * on the map. Start by using Dijkstra's, and if your code isn't fast enough for your
 * satisfaction (or the autograder), upgrade your implementation by switching it to A*.
 * Your code will probably not be fast enough to pass the autograder unless you use A*.
 * The difference between A* and Dijkstra's is only a couple of lines of code, and boils
 * down to the priority you use to order your vertices.
 */
public class Router {
    /**
     * Return a LinkedList of <code>Long</code>s representing the shortest path from st to dest, 
     * where the longs are node IDs.
     */

    public static LinkedList<Long> shortestPath(GraphDB g, double stlon,
                                                double stlat, double destlon, double destlat) {
        LinkedList<Long> path = new LinkedList<>();
        Node start = new Node(-1L, stlat, stlon);
        Node dest = new Node(1L, destlon, destlat);

        Iterator<Long> i = g.nodes.keySet().iterator();
        Long randKey = i.next();
        Node closestStart = new Node(0L, 0, 0); // = g.nodes.get(randKey);
        Node closestDest = new Node(0L, 0, 0); // = g.nodes.get(randKey);

        g.nodes.put(-1L, start);
        g.nodes.put(1L, dest);

        double distStart = g.distance(randKey, -1L);
        double distDest = g.distance(randKey, 1L);

        Long startKey = 0L;
        Long destKey = 0L;

        for (Long v : g.nodes.keySet()) {
            if (v != -1 && v != 1) {
                if (g.distance(v, -1L) < distStart) {
                    //closestStart = g.nodes.get(v);
                    startKey = v;
                }
                if (g.distance(v, 1L) < distDest) {
                    //closestDest = g.nodes.get(v);
                    destKey = v;
                }
            }

        }

        LinkedList<Long> rv = new LinkedList<>();

        //Long currKey = startKey;

        while(!startKey.equals(destKey)) {
            rv.add(startKey);
            //Set<Long> s = g.nodes.get(startKey).adjacent.keySet();
            ArrayList<Long> s = g.nodes.get(startKey).adjacent;
            //Iterator<Long> it = s.iterator();
            Long rand = s.get(0); //it.next();
            double smallest = g.distance(rand, startKey) + g.distance(rand, destKey);
            for (Long l : s) {
                double temp = g.distance(l, startKey) + g.distance(l, destKey);
                if (temp < smallest) {
                    smallest = temp;
                    startKey = l;
                }
            }
        }

        return rv;
        //return aStar(closestStart, closestDest, );
    }

    /*public LinkedList<Long> aStar(Node start, Node dest) {
        if (start.equals(dest)) {
            Long.add
        }
    }*/
}
