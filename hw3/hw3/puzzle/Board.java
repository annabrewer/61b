package hw3.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    int BLANK = 0;
    int len;
    int[][] board;

    public Board(int[][] tiles) {
        len = tiles.length;
        board = new int[len][len];
        for (int i = 0; i < len; i++) {
            int[] row = tiles[i];
            System.arraycopy(tiles[i], 0, board[i], 0, len);
        }

    }
    public int tileAt(int i, int j) {
        int max = len + 1;
        if (!(0 <= j && j < max && 0 <= i && i < max)) {
            throw new IndexOutOfBoundsException();
            //System.out.println("hi");
        }
        return board[i][j];
    }

    public int size() {
        return len;
    }

    ///got this from staff solution :)
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    public int hamming() {
        int result = 0;
        int index = 0;
        for (int[] i : board) {
            for (int j : i) {
                if (j != index + 1 && j != 0) {
                    result += 1;
                }
                index += 1;
            }
        }
        return result;
    }

    public int manhattan() {
        int horiz = 0;
        int vert = 0;
        int result = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int num = board[i][j];
                if (num != 0) {
                    /*horiz = (len - 1) - i;
                    vert = (len - 1) - j;
                    result += horiz + vert;*/
                    vert = Math.abs(i - ((num - 1) / len)); //floor div
                    horiz = Math.abs(j - ((num - 1) % len));
                    result += horiz + vert;
                }
            }
        }
        return result;
    }

    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    public boolean isGoal() {
        return estimatedDistanceToGoal() == 0;
    }

    @Override
    public boolean equals(Object y) {

        if (!(y instanceof Board)) {
            return false;
        }
        if (y == null) {
            return false;
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if ((((Board) y).tileAt(i, j)) == board[i][j]) {
                    return true;
                }
                return false;
            }
        }
        /*if (y != null) {
            return Arrays.deepEquals(((Board) y).board, board);
        }*/
        return y.toString().equals(this.toString());
    }

    @Override
    public int hashCode() {
        return 0; //dont need this
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
