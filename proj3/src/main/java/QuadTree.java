/**
 * Created by Anna on 4/16/17.
 */

import java.util.ArrayList;
import java.util.Arrays;

public class QuadTree {

    double ullon;
    double ullat;
    double lrlon;
    double lrlat;
    double res;
    String filename;
    int depth;
    ArrayList<QuadTree> children;

    //contains all the images
    public QuadTree(double uloi, double ulai, double lloi, double llai, String f) {
        ullon = uloi;
        ullat = ulai;
        lrlon = lloi;
        lrlat = llai;
        filename = f;
        //depth = f.length();
        res = 49 / (Math.pow(2, f.length() - 1));
        if (f.length() == 7) { //filename length is depth
            children = null;
        } else {
            String fn2;
            if (filename.equals("root")) {
                fn2 = "";
                //depth = 0;
            } else {
                fn2 = filename;
                //depth = filename.length();
            }
            double childWidth = Math.abs((uloi - lloi) / 2.0);
            double childHeight = Math.abs((ulai - llai) / 2.0);
            double midWidth = uloi + childWidth;
            double midHeight = ulai - childHeight;
            QuadTree q1 = new QuadTree(uloi, ulai, midWidth, midHeight, fn2 + "1");
            QuadTree q2 = new QuadTree(midWidth, ulai, lloi, midHeight, fn2 + "2");
            QuadTree q3 = new QuadTree(uloi, midHeight, midWidth, llai, fn2 + "3");
            QuadTree q4 = new QuadTree(midWidth, midHeight, lloi, llai, fn2 + "4");
            children = new ArrayList<>(Arrays.asList(q1, q2, q3, q4));
        }
    }

}
