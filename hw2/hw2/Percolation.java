package hw2;                       

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.ArrayList;

public class Percolation {

    public ArrayList<Integer> openSites;
    public static WeightedQuickUnionUF union;
    public static WeightedQuickUnionUF unionNB; //no backwash
    public static int size;
    public int virtualSiteTop;
    public int virtualSiteBottom;


    public Percolation(int N) {
        size = N^2;
        union = new WeightedQuickUnionUF(size + 2); //+2 because of virtual sites
        unionNB = new WeightedQuickUnionUF(size + 2);
        openSites = new ArrayList<Integer>();
        virtualSiteTop = size + 1;
        virtualSiteBottom = size + 2;
    }                // create N-by-N grid, with all sites initially blocked

    public void open(int row, int col) {
        int i = xyTo1D(row, col);
        if (!openSites.contains(i)) {
            openSites.add(i);
            ArrayList<Integer> surrounding = new ArrayList<Integer>();
            //cancer code
            if (row != 0) {
                surrounding.add(xyTo1D(row-1, col));
            }
            if (row != size) {
                surrounding.add(xyTo1D(row+1, col));
            }
            if (col != 0) {
                surrounding.add(xyTo1D(row, col-1));
            }
            if (col != size) {
                surrounding.add(xyTo1D(row, col+1));
            }
            for (Integer x : surrounding) {
                if (openSites.contains(x)) { //breaking abstraction barrier but w/e
                    union.union(x, i);
                }
            }
        }
    }      // open the site (row, col) if it is not open already

    public boolean isOpen(int row, int col) {
        int i = xyTo1D(row, col);
        if (openSites.contains(i)) {
            return true;
        }
        return false;
    }  // is the site (row, col) open?

    public boolean isFull(int row, int col) {

    } // is the site (row, col) full?

    public int numberOfOpenSites() {
        return openSites.size();
    }          // number of open sites

    public boolean percolates() {

    }             // does the system percolate?

    public static void main(String[] args) {
        System.out.println(xyTo1D(2,4)); //should be 14
    }

    //helper
    public static int xyTo1D(int r, int c) {
        if (r < size && c < size) {
            int result = (r)*size; //not r-1 cause row starts @ 0 !
            result += c; //not c+1 cause cols start @ 0 !
            return result;
        }
        else {
            return -420; //should never happen hopefully
        }
    }
}                       
