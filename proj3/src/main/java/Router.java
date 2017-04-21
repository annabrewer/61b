import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

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
        //LinkedList<Long> path = new LinkedList<>();
        /*Node start = new Node(-1L, stlat, stlon);
        Node dest = new Node(1L, destlon, destlat);

        Iterator<Long> i = g.nodes.keySet().iterator();
        Long randKey = i.next();
        //Node closestStart = new Node(0L, 0, 0); // = g.nodes.get(randKey);
        //Node closestDest = new Node(0L, 0, 0); // = g.nodes.get(randKey);

        g.nodes.put(-1L, start);
        g.nodes.put(1L, dest);

        double distStart = g.distance(randKey, -1L);
        double distDest = g.distance(randKey, 1L);



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

        }*/
        //Node start = new Node(-1L, stlat, stlon);
        //Node dest = new Node(1L, destlon, destlat);

        //Iterator<Long> i = g.nodes.keySet().iterator();
        //Long randKey = i.next();

        Long startKey = g.closest(stlon, stlat);
        Long destKey = g.closest(destlon, destlat);

        PriorityQueue<SearchNode> queue = new PriorityQueue<>();
        SearchNode initialNode = new SearchNode(startKey, 0L, null, g, destKey);
        queue.add(initialNode);
        SearchNode curr = queue.poll();
        HashSet<Long> visited = new HashSet<>();

        while (!curr.index().equals(destKey)) {
            ArrayList<Long> s = g.nodes().get(curr.index()).adjacent();
            for (Long l : s) {
                if (!visited.contains(l)) {
                    SearchNode sn = new SearchNode(l, g.distance(startKey, l), curr, g, destKey);
                    if (g.distance(startKey, l) < curr.totalDistance() + g.distance(l, curr.index())) {
                        sn.setTotalDistance(curr.totalDistance() + g.distance(l, curr.index()));
                    }
                    queue.add(sn);
                }
            }
            visited.add(curr.index());
            curr = queue.poll();
        }

        LinkedList<Long> soln = new LinkedList<>();
        while (curr != null) {
            //dont need to find distance ("num moves")
            soln.add(curr.index());
            curr = curr.prevNode();
        }
        Collections.reverse(soln);

        return soln;
    }
}

//OLD RATCHET IMPLEMENTATION

    /*Long startKey = g.closest(stlon, stlat);
    Long destKey = g.closest(destlon, destlat);

    PriorityQueue<SearchNode> queue = new PriorityQueue<>();
    //Long dist = 0L;
    //(Long ind, double dist, SearchNode prev, GraphDB g, Long end)
    SearchNode initialNode = new SearchNode(startKey, 0L, null, g, destKey);
        queue.add(initialNode);
                SearchNode curr = queue.poll();
                //LinkedList<SearchNode> visited = new LinkedList<>();
                while (startKey != destKey) {
                ArrayList<Long> s = g.nodes.get(curr.index).adjacent;
        for (Long l : s) {
        SearchNode sn = new SearchNode(l, curr.totalDistance + g.distance(l,
        curr.index), curr, g, destKey);
        if (curr.prevNode == null || !curr.prevNode.index.equals(sn.index))
        { // !visited.contains(sn)) { //
        queue.add(sn);
        }
        else {
        break;
        //continue;
        //curr = curr.prevNode;
        //if (sn.totalDistance < curr.prevNode.totalDistance) {
        //sn.prevNode = curr.prevNode;
        //}
        }
                    /*(!sn.index.equals(curr.prevNode.index)) { //replace index w distance??
                    queue.add(sn);
                } else {
                    System.out.println(sn.index);
                    System.out.println(curr.index);
                    System.out.println(curr.prevNode.index);
                    break;
                }*/
        /*}
        if (queue.size() != 0) {
        curr = queue.poll();
        } else { //can delete this case??
        System.out.println("hello");
        break;
        //System.out.print("o");
        }
        queue = new PriorityQueue<>();
        }
        LinkedList<Long> soln = new LinkedList<>();
        while (curr != null) {
        //dont need to find distance ("num moves")
        soln.add(curr.index);
        curr = curr.prevNode;
        }
        Collections.reverse(soln);

        return soln;*/

        /*while(!startKey.equals(destKey)) {
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
            }*/
