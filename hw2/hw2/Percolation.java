package hw2;                       

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.ArrayList;

public class Percolation {

    //private ArrayList<Integer> openSites;
    private WeightedQuickUnionUF union;
    private WeightedQuickUnionUF unionNB; //no backwash
    private int size;
    private int length;
    private int virtualTop;
    private int virtualBottom;
    private boolean[][] open;
    private int openNum;


    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        size = N * N;
        length = N;
        virtualTop = size;
        virtualBottom = size + 1;
        openNum = 0;
        //openSites = new ArrayList<Integer>();
        open = new boolean[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                open[i][j] = false;
            }
        }
        union = new WeightedQuickUnionUF(size + 2);
        unionNB = new WeightedQuickUnionUF(size + 1);
        for (int i = 0; i < N; i++) {
            union.union(i, virtualTop); //top row
            unionNB.union(i, virtualTop); //top row
            union.union(size - (i + 1), virtualBottom); //bottom row
        }
    }                // create N-by-N grid, with all sites initially blocked
    public void open(int row, int col) {
        int site = xyTo1D(row, col);
        if (!open[row][col]) {
            open[row][col] = true;
            //ArrayList<int[]> surrounding = new ArrayList<int[]>();
            if (row != 0) {
                int i = row - 1;
                int j = col;
                if (open[i][j]) {
                    int s = xyTo1D(i, j);
                    union.union(s, site);
                    unionNB.union(s, site);
                }
            }
            if (row != length - 1) {
                int i = row + 1;
                int j = col;
                if (open[i][j]) {
                    int s = xyTo1D(i, j);
                    union.union(s, site);
                    unionNB.union(s, site);
                }
            }
            if (col != 0) {
                int i = row;
                int j = col - 1;
                if (open[i][j]) {
                    int s = xyTo1D(i, j);
                    union.union(s, site);
                    unionNB.union(s, site);
                }
            }
            if (col != length - 1) {
                int i = row;
                int j = col + 1;
                if (open[i][j]) {
                    int s = xyTo1D(i, j);
                    union.union(s, site);
                    unionNB.union(s, site);
                }
            }
            openNum += 1;
            open[row][col] = true;
        }
    }       // open the site (row, col) if it is not open already
    public boolean isOpen(int row, int col) {
        return open[row][col];
    }  // is the site (row, col) open?

    public boolean isFull(int row, int col) {
        int site = xyTo1D(row, col);
        return open[row][col] && unionNB.connected(virtualTop, site);
    }  // is the site (row, col) full?

    public int numberOfOpenSites() {
        return openNum;
    }           // number of open sites

    public boolean percolates() {
        if (size > 1) {
            return union.connected(virtualTop, virtualBottom);
        } else { //edge case - 1x1 grid
            return open[0][0];
        }
    }              // does the system percolate?

    public static void main(String[] args) {
        //length = 5;
        //System.out.print(xyTo1D(2, 4)); //should be 14
        //test whether percolation works properly
        Percolation p = new Percolation(20);
        p.open(0, 4);
        if (p.percolates()) {
            System.out.print("oh no");
        } else {
            System.out.print("hell yeah boys");
        }
        for (int i = 1; i < p.length; i++) {
            p.open(i, 4);
        }
        if (p.percolates()) {
            System.out.print("hell yeah");
        } else {
            System.out.print("oh no");
        }
    }

    //helper
    public int xyTo1D(int r, int c) {
        int result = length * (r); //not r-1 because rows start at 0
        result += (c); //not c+1 because cols start at 0
        return result;
    }
}                       
