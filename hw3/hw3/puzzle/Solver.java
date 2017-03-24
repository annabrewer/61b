package hw3.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Created by Anna on 3/23/17.
 */
public class Solver {
    MinPQ<SearchNode> queue;
    int moves;
    ArrayList<WorldState> solution;

    public Solver (WorldState initial) {
        queue = new MinPQ<SearchNode>();
        SearchNode initialNode = new SearchNode(initial, 0, null);
        queue.insert(initialNode);
        SearchNode curr = queue.delMin();
        moves = 0;
        while (!curr.worldState.isGoal()){
            for (WorldState neighbor : curr.worldState.neighbors()) {
                SearchNode sn = new SearchNode(neighbor, moves, curr);
                if (curr.prevNode == null) {
                    queue.insert(sn);
                }
                else if (!sn.worldState.equals(curr.prevNode.worldState)) {
                    queue.insert(sn);
                }
            }
            if (queue.size() != 0) {
                curr = queue.delMin();
            }
            else {
                System.out.println("Why is this happening");
                break;
                //break;
            }
        }
        solution = new ArrayList<WorldState>();
        while (curr != null) {
            moves += 1;
            solution.add(curr.worldState);
            curr = curr.prevNode;
        }
    }

    public int moves(){
        return moves;
    }

    public Iterable<WorldState> solution() {
        return solution;
    }
}
