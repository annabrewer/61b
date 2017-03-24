package hw3.puzzle;

/**
 * Created by Anna on 3/23/17.
 */
public class TestManhattan {
    public static void main(String[] args){
        int[][]bleh = new int[][]{{0, 1, 2},{3, 4, 5},{6, 7, 8}};
        int[][]blah = new int[][]{{1, 2, 3},{4, 5, 6},{7, 8, 0}};
        Board b = new Board(bleh);
        System.out.print(b.manhattan());
    }
}
