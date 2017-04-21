/**
 * Created by Anna on 4/20/17.
 */

/**
 * Created by Anna on 3/23/17.
 */
public class SearchNode implements Comparable<SearchNode> {

    //public WorldState worldState;
    private double totalDistance;
    private Long index;
    private SearchNode prevNode;
    private GraphDB graph;
    private Long endIndex;

    public double totalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double d) {
        totalDistance = d;
    }

    public Long index() {
        return index;
    }

    public SearchNode prevNode() {
        return prevNode;
    }

    public GraphDB graph() {
        return graph;
    }

    public Long endIndex() {
        return endIndex;
    }

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
