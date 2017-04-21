/**
 * Created by Anna on 4/20/17.
 */
import java.util.Comparator;

/**
 * Created by Anna on 3/23/17.
 */
public class SearchNode implements Comparable<SearchNode> {

    //public WorldState worldState;
    public double totalDistance;
    public Long index;
    public SearchNode prevNode;
    public GraphDB graph;
    public Long endIndex;

    public SearchNode(Long ind, double dist, SearchNode prev, GraphDB g, Long end) {
        //worldState = w;
        index = ind;
        totalDistance = dist;
        prevNode = prev;
        graph = g;
        endIndex = end;
    }

    public int compareTo(SearchNode s) {
        double e1 = graph.distance(index, endIndex);
        double m1 = totalDistance;
        double e2 = graph.distance(s.index, endIndex);
        double m2 = s.totalDistance;

        Double sum1 = e1 + m1;
        Double sum2 = e2 + m2;

        return sum1.compareTo(sum2);
        //PQ should return least element first
        //if this is wrong, cam subtract from 0
    }
}
