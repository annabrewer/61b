import java.util.*;

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
     * @see #REQUIRED_RASTER_REQUEST_PARAMS
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {

        //System.out.println(getIndex("141"));

        arrList = new ArrayList<>();
        double[] queryBox = {params.get("ullon"), params.get("ullat"), params.get("lrlon"), params.get("lrlat")};
        q = new QuadTree(MapServer.ROOT_ULLON, MapServer.ROOT_ULLAT, MapServer.ROOT_LRLON, MapServer.ROOT_LRLAT, "root");
        //q = new QuadTree(0.0, 8.0, 8.0, 0.0, "root");
        double windowLDP = Math.abs((params.get("lrlon") - params.get("ullon")) / params.get("w"));

        getImages(q, queryBox, windowLDP, params.get("w"));

        TreeMap<Double, ArrayList<String>> s = new TreeMap<>();

        for (QuadTree t : arrList) {
            if(!s.containsKey(t.ullon)) {
                s.put(t.ullon, new ArrayList<String>());
            }
            s.get(t.ullon).add(imgr+t.filename+".png");
        }

        int lenRow = s.size();
        int numRows = arrList.size() / lenRow;

        String[][] arr = new String[numRows][lenRow];

        for (int j = 0; j < numRows; j++) {
            String[] a = new String[lenRow];
            int index = 0;
            double k = s.firstKey();
            a[index] = s.get(k).remove(0);
            while (s.higherKey(k) != null) {
                k = s.higherKey(k);
                index += 1;
                a[index] = s.get(k).remove(0); //gets first element of each arraylist
            }
            arr[j] = a;
        }

        Map<String, Object> results = new HashMap<>();
        results.put("render_grid", arr);
        results.put("raster_ul_lon", queryBox[0]);
        results.put("raster_ul_lat", queryBox[1]);
        results.put("raster_lr_lon", queryBox[2]);
        results.put("raster_lr_lat", queryBox[3]);
        //string could be "1234.png" - > depth is length - ".png" = length - 4;
        System.out.println(arr[0][0]);
        results.put("depth", arr[0][0].length() - 8);
        results.put("query_success", true);
        return results;
    }

    //helper
    public void getImages (QuadTree q, double[] queryBox, double ldp, double w) {
        if (intersectsQueryBox(q, queryBox)) {
            if (lonDPPsmallerOrIsLeaf(q, ldp, w)) {
                arrList.add(q);
                //return;
            } else {
                for (QuadTree qtree : q.children) { //should "short-circuit" kinda
                    getImages(qtree, queryBox, ldp, w);
                }
            }
        }
    }

    //helper
    public boolean intersectsQueryBox(QuadTree q, double[] queryBox) {
        if (q.ullon <= queryBox[2] && q.lrlon >= queryBox[0]) {
            if (q.ullat >= queryBox[3] && q.lrlat <= queryBox[1]) {
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

    /*String upperLeft = arrList.get(i).filename;
        //should put arrList.get(i) at upper right corner
        while (arrList.get(i).lrlon < params.get("lrlon")) {
            i += 1;
        }
        String upperRight = arrList.get(i).filename;
        int leftIndex = getIndex(upperLeft);
        int rightIndex = getIndex(upperRight);
        int lenRow = rightIndex - leftIndex;
        int numRows = arrList.size() / lenRow;*/

    /*public int getIndex(String s) {
        int n = Integer.parseInt(s);
        int index = 0;
        int sideLength = 1;
        int finalLength = (int) Math.pow(2, s.length());
        int digit = 0;
        int nextDigit = 0;
        while (sideLength < finalLength) {
            digit = n % 10;
            nextDigit = (n % 100) / 10;
            index += (Math.pow(sideLength, 2)) * (digit - 1);
            if (nextDigit % 2 != 0) {
                index += (Math.pow(sideLength, 2)) * 2;
            }
            n = n / 10;
            sideLength = sideLength * 2;
        }
        return index;
    }*/
    /*public void getAllTiles(QuadTree q, double[] qb, String[][] arr) {
        ArrayList<ArrayList<String>> temp = new ArrayList<>();
        int l = q.filename.length();
        int b = Integer.parseInt(q.filename.charAt(0)); //beginning
        int c = Integer.parseInt(q.filename.charAt(1))
        for (int i = b; i < 4; i++) {
            ArrayList<String> row = new ArrayList<>();
            for ()
        }
    }*/

    /*String[][] arr = {{"img/2143411.png", "img/2143412.png", "img/2143421.png"}, {"img/2143413.png", "img/2143414.png", "img/2143423.png"}, {"img/2143431.png", "img/2143432.png", "img/2143441.png" }};

        results.put("render_grid", arr);
        results.put("raster_ul_lon", -122.24212646484375);
        results.put("raster_ul_lat", 37.87701580361881);
        results.put("raster_lr_lon", -122.24006652832031);
        results.put("raster_lr_lat", 37.87538940251607);
        results.put("depth", 7);
        results.put("query_success", true);*/

}
