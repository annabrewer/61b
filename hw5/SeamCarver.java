/**
 * Created by Anna on 4/26/17.
 */
import edu.princeton.cs.algs4.Picture;
import java.awt.Color;

public class SeamCarver {

    Picture pic;
    int[][] minArray;
    int[][] minArrayHoriz;

    public SeamCarver(Picture picInput) {
        pic = picInput;
    }

    public int energy(int c, int r) {
        int aboveRow = r - 1;
        int belowRow = r + 1;
        int leftCol = c - 1;
        int rightCol = c + 1;

        if (c == 0) {
            leftCol = pic.width();
        }
        else if (c == pic.width()) {
            rightCol = 0;
        }
        if (r == 0) {
            aboveRow = pic.height();
        }
        else if (r == pic.height()) {
            belowRow = 0;
        }

        Color above = pic.get(c, aboveRow);
        Color below = pic.get(c, belowRow);
        Color left = pic.get(leftCol, r);
        Color right = pic.get(rightCol, r);

        int rx = right.getRed() - left.getRed();
        int gx = right.getGreen() - left.getGreen();
        int bx = right.getBlue() - left.getBlue();

        int ry = below.getRed() - above.getRed();
        int gy = below.getGreen() - above.getGreen();
        int by = below.getBlue() - above.getBlue();

        return rx * rx + gx * gx + bx * bx + ry * ry + gy * gy + by * by;
    }

    public int[] findVerticalSeam() {
        minArray = new int[pic.height()][pic.width()];
        for (int i = 0; i < pic.width(); i++) {
            minArray[0][i] = energy(i, 0);
        }
        for (int i = 1; i < pic.height(); i++) {
            for (int j = 0; j < pic.width(); j++) {
                minArray[i][j] = energy(i, j) + Math.min(Math.min(minArray[i - 1][j - 1], minArray[i - 1][j]), minArray[i - 1][j + 1]);
            }
        }



        int[] rv = new int[pic.height()];

        for (int i = pic.height(); i > 0; i--) {
            int ind = minIndex(minArray, i);
            rv[i] = ind;
        }

        return rv;

    }

    public int[] findHorizontalSeam() {
        minArrayHoriz = new int[pic.width()][pic.height()];
        for (int i = 0; i < pic.height(); i++) {
            minArrayHoriz[0][i] = energy(i, 0);
        }
        for (int i = 1; i < pic.width(); i++) {
            for (int j = 0; j < pic.height(); j++) {
                minArrayHoriz[i][j] = energy(i, j) + Math.min(Math.min(minArrayHoriz[i - 1][j - 1], minArrayHoriz[i - 1][j]), minArrayHoriz[i - 1][j + 1]);
            }
        }

        int[] rv = new int[pic.width()];

        for (int i = pic.height(); i > 0; i--) {
            int ind = minIndex(minArrayHoriz, i);
            rv[i] = ind;
        }

        return rv;
    }

    public int minIndex(int[][] a, int r) {
        int index = 0;
        int[] arr = a[r];
        int min = arr[index];

        for (int i=1; i<arr.length; i++){
            if (arr[i] < min ){
                min = arr[i];
                index = i;
            }
        }
        return index;
    }

    public static void removeHorizontalSeam(int[] seam) {
        //SeamCarver.removeHorizontalSeam(pic, seam);
    }

    public static void removeVerticalSeam(int[] seam) {
        //
    }

    public int width() {
        return pic.width();
    }

    public int height() {
        return pic.height();
    }

    public Picture picture() {
        return pic;
    }
}
