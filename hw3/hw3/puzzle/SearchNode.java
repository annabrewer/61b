package hw3.puzzle;

import java.util.Comparator;

/**
 * Created by Anna on 3/23/17.
 */
public class SearchNode implements Comparable<SearchNode> {

    public WorldState worldState;
    public int numMoves;
    public SearchNode prevNode;

    public SearchNode(WorldState w, int n, SearchNode p) {
        worldState = w;
        numMoves = n;
        prevNode = p;
    }

    public int compareTo(SearchNode s) {
        int e1 = worldState.estimatedDistanceToGoal();
        int m1 = numMoves;
        int e2 = s.worldState.estimatedDistanceToGoal();
        int m2 = s.numMoves;

        Integer sum1 = e1 + m1;
        Integer sum2 = e2 + m2;

        return sum1.compareTo(sum2);
        //PQ should return least element first
        //if this is wrong, cam subtract from 0
    }

}
