/**
 * Created by Anna on 4/26/17.
 */
import edu.princeton.cs.algs4.Picture;
import java.awt.Color;

public class SeamCarver {

    private Picture pic;
    private double[][] minArray;
    //int[][] minArrayHoriz;

    public SeamCarver(Picture picInput) {
        pic = new Picture(picInput);
    }

    public double energy(int c, int r) {
        int aboveRow = r - 1;
        int belowRow = r + 1;
        int leftCol = c - 1;
        int rightCol = c + 1;

        if (c == 0) {
            leftCol = pic.width() - 1;
            /*if (c == pic.width() - 1) {
                rightCol = 0;
            }*/
        }
        if (c == pic.width() - 1) {
            rightCol = 0;
        }
        if (r == 0) {
            aboveRow = pic.height() - 1;
        }
        if (r == pic.height() - 1) {
            belowRow = 0;
        }

        Color left = pic.get(leftCol, r);
        Color right = pic.get(rightCol, r);
        Color above = pic.get(c, aboveRow);
        Color below = pic.get(c, belowRow);

        double rx = right.getRed() - left.getRed();
        double gx = right.getGreen() - left.getGreen();
        double bx = right.getBlue() - left.getBlue();

        double ry = below.getRed() - above.getRed();
        double gy = below.getGreen() - above.getGreen();
        double by = below.getBlue() - above.getBlue();

        return (rx * rx) + (gx * gx) + (bx * bx) + (ry * ry) + (gy * gy) + (by * by);
    }

    public int[] findVerticalSeam() {

        minArray = new double[pic.height()][pic.width()];
        for (int i = 0; i < pic.width(); i++) {
            minArray[0][i] = energy(i, 0);
        }
        for (int i = 1; i < pic.height(); i++) {
            for (int j = 0; j < pic.width(); j++) {
                //int aboveLeft = j - 1;
                //int aboveRight = j + 1;

                /*int minInd = j - 1;
                int maxInd = j + 2;

                if (j == 0) {
                    minInd = j;
                }
                if (j == pic.width() - 1) {
                    maxInd = j + 1;
                }*/

                //minArray[i][j] = energy(j, i) +  minIndex(minArray[i - 1], minInd, maxInd);

                if (j == 0) {
                    minArray[i][j] = energy(j, i) + Math.min(minArray[i - 1][j + 1],
                            minArray[i - 1][j]);
                } else if (j == pic.width() - 1) {
                    minArray[i][j] = energy(j, i) + Math.min(minArray[i - 1][j - 1],
                            minArray[i - 1][j]);
                } else {
                    minArray[i][j] = energy(j, i) + Math.min(Math.min(minArray[i - 1][j - 1],
                            minArray[i - 1][j]), minArray[i - 1][j + 1]);
                }
            }
        }

        int[] rv = new int[pic.height()];
        double[] firstRow = minArray[pic.height() - 1];
        int ind = minIndex(firstRow, 0, pic.width());
        rv[pic.height() - 1] = ind;

        for (int i = pic.height() - 2; i >= 0; i--) {
            if (ind == 0) {
                rv[i] = (minIndex(minArray[i], ind, ind + 2));
            } else if (ind == pic.width() - 1) {
                rv[i] = (minIndex(minArray[i], ind - 1, ind + 1));
            } else {
                rv[i] = (minIndex(minArray[i], ind - 1, ind + 2));
            }
            ind = rv[i];
        }

        return rv;

    }

    public int[] findHorizontalSeam() {

        minArray = new double[pic.width()][pic.height()];

        for (int i = 0; i < pic.height(); i++) {
            minArray[0][i] = energy(0, i);
        }

        for (int i = 1; i < pic.width(); i++) {
            for (int j = 0; j < pic.height(); j++) {
                //int aboveLeft = j - 1;
                //int aboveRight = j + 1;
                int minInd = j - 1;
                int maxInd = j + 2;
                if (j == 0) {
                    minInd = j;
                }
                if (j == pic.height() - 1) {
                    maxInd = j + 1;
                }

                minArray[i][j] = energy(i, j) +  minIndex(minArray[i - 1], minInd, maxInd);
                /*if (j == 0) {
                    minArray[i][j] = energy(i, j) + Math.min(minArray[i - 1][j + 1],
                            minArray[i - 1][j]);
                } else if (j == pic.height() - 1) {
                    minArray[i][j] = energy(i, j) + Math.min(minArray[i - 1][j - 1],
                            minArray[i - 1][j]);
                } else {
                    minArray[i][j] = energy(i, j) + Math.min(Math.min(minArray[i - 1][j - 1],
                            minArray[i - 1][j]), minArray[i - 1][j + 1]);
                }*/

            }
        }

        int[] rv = new int[pic.width()];
        double[] firstRow = minArray[pic.width() - 1];
        int ind = minIndex(firstRow, 0, pic.height());
        rv[pic.width() - 1] = ind;

        for (int i = pic.width() - 2; i >= 0; i--) {
            if (ind == 0) {
                rv[i] = (minIndex(minArray[i], ind, ind + 2));
            } else if (ind == pic.height() - 1) {
                rv[i] = (minIndex(minArray[i], ind - 1, ind + 1));
            } else {
                rv[i] = (minIndex(minArray[i], ind - 1, ind + 2));
            }
            ind = rv[i];
        }

        return rv;
    }

    private int minIndex(double[] a, int startIndex, int endIndex) {
        int index = startIndex;
        double[] arr = a;
        double min = arr[index];

        for (int i = startIndex + 1; i < endIndex; i++) {
            if (arr[i] < min) {
                min = arr[i];
                index = i;
            }
        }
        return index;
    }

    public void removeHorizontalSeam(int[] seam) {
        //SeamCarver.removeHorizontalSeam(pic, seam);
    }

    public void removeVerticalSeam(int[] seam) {
        //
    }

    public int width() {
        return pic.width();
    }

    public int height() {
        return pic.height();
    }

    public Picture picture() {
        return new Picture(pic);
    }
}
