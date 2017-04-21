/**
 * Created by Anna on 4/20/17.
 */
import java.util.ArrayList;
import java.util.HashMap;

public class Node {
    //public HashMap<Long, Node> adjacent;
    private ArrayList<Long> adjacent;

    private long id;
    private double lat;
    private double lon;

    public Node(long idInput, double latInput, double lonInput) {
        id = idInput;
        lat = latInput;
        lon = lonInput;
        adjacent = new ArrayList<>();
    }

    public ArrayList<Long> adjacent(){
        return adjacent;
    }

    public long id() {
        return id;
    }

    public double lat() {
        return lat;
    }

    public double lon() {
        return lon;
    }
}
