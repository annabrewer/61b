import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {
    // Recommended: QuadTree instance variable. You'll need to make
    //              your own QuadTree since there is no built-in quadtree in Java.
    QuadTree q;
    String imgr;
    ArrayList<QuadTree> arrList;
    double londpp;
    /** imgRoot is the name of the directory containing the images.
     *  You may not actually need this for your class. */
    public Rasterer(String imgRoot) {
        imgr = imgRoot; //need this?
    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     * <p>
     *     The grid of images must obey the following properties, where image in the
     *     grid is referred to as a "tile".
     *     <ul>
     *         <li>The tiles collected must cover the most longitudinal distance per pixel
     *         (LonDPP) possible, while still covering less than or equal to the amount of
     *         longitudinal distance per pixel in the query box for the user viewport size. </li>
     *         <li>Contains all tiles that intersect the query bounding box that fulfill the
     *         above condition.</li>
     *         <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     *     </ul>
     * </p>
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     *
     * @return A map of results for the front end as specified:
     * "render_grid"   -> String[][], the files to display
     * "raster_ul_lon" -> Number, the bounding upper left longitude of the rastered image <br>
     * "raster_ul_lat" -> Number, the bounding upper left latitude of the rastered image <br>
     * "raster_lr_lon" -> Number, the bounding lower right longitude of the rastered image <br>
     * "raster_lr_lat" -> Number, the bounding lower right latitude of the rastered image <br>
     * "depth"         -> Number, the 1-indexed quadtree depth of the nodes of the rastered image.
     *                    Can also be interpreted as the length of the numbers in the image
     *                    string. <br>
     * "query_success" -> Boolean, whether the query was able to successfully complete. Don't
     *                    forget to set this to true! <br>
     * //@see #REQUIRED_RASTER_REQUEST_PARAMS
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {

        //System.out.println(getIndex("141"));

        arrList = new ArrayList<>();
        double[] queryBox = {params.get("ullon"), params.get("ullat"),
                params.get("lrlon"), params.get("lrlat")};
        q = new QuadTree(MapServer.ROOT_ULLON, MapServer.ROOT_ULLAT,
                MapServer.ROOT_LRLON, MapServer.ROOT_LRLAT, "root");
        //q = new QuadTree(0.0, 8.0, 8.0, 0.0, "root");
        double windowLDP = Math.abs((params.get("lrlon") - params.get("ullon")) / params.get("w"));

        getImages(q, queryBox, windowLDP, params.get("w"));

        TreeMap<Double, ArrayList<QuadTree>> s = new TreeMap<>();

        for (QuadTree t : arrList) {
            if (!s.containsKey(t.ullon)) {
                s.put(t.ullon, new ArrayList<QuadTree>());
            }
            s.get(t.ullon).add(t);
            //(imgr+t.filename+".png");
        }

        int lenRow = s.size();
        int numRows = arrList.size() / lenRow;

        String[][] arr = new String[numRows][lenRow];

        //double ulat = 0;
        //double llat = 0;

        //ArrayList<QuadTree> hmm = ;
        double ulat = s.get(s.firstKey()).get(0).ullat;
        double llat = s.get(s.lastKey()).get(numRows - 1).lrlat;
        double llon = s.get(s.lastKey()).get(numRows - 1).lrlon;

        for (int j = 0; j < numRows; j++) {
            String[] a = new String[lenRow];
            int index = 0;
            double k = s.firstKey();

            QuadTree thing = s.get(k).remove(0); //gets first element of each arraylist
            a[index] = (imgr + thing.filename + ".png");

            while (s.higherKey(k) != null) {
                k = s.higherKey(k);
                index += 1;
                QuadTree thing2 = s.get(k).remove(0); //gets first element of each arraylist
                a[index] = (imgr + thing2.filename + ".png");
            }
            arr[j] = a;
        }

        Map<String, Object> results = new HashMap<>();
        results.put("render_grid", arr);
        results.put("raster_ul_lon", s.firstKey());
        results.put("raster_ul_lat", ulat);
        results.put("raster_lr_lon", llon);
        results.put("raster_lr_lat", llat);
        //string could be "1234.png" - > depth is length - ".png" = length - 4;
        //System.out.println(arr[0][0]);
        results.put("depth", arr[0][0].length() - 8);
        results.put("query_success", true);
        return results;
    }

    //helper
    public void getImages(QuadTree qtr, double[] queryBox, double ldp, double w) {
        if (intersectsQueryBox(qtr, queryBox)) {
            if (lonDPPsmallerOrIsLeaf(qtr, ldp, w)) {
                arrList.add(qtr);
                //return;
            } else {
                for (QuadTree qtree : qtr.children) { //should "short-circuit" kinda
                    getImages(qtree, queryBox, ldp, w);
                }
            }
        }
    }

    //helper
    public boolean intersectsQueryBox(QuadTree qutr, double[] queryBox) {
        if (qutr.ullon <= queryBox[2] && qutr.lrlon >= queryBox[0]) {
            if (qutr.ullat >= queryBox[3] && qutr.lrlat <= queryBox[1]) {
                return true;
            }
        }
        return false;
    }

    public boolean lonDPPsmallerOrIsLeaf(QuadTree qt, double l, double wid) {
        londpp = Math.abs((qt.lrlon - qt.ullon) / 256);
        if (londpp <= l  || qt.filename.length() == 7) {
            return true;
        }
        return false;
    }

}
