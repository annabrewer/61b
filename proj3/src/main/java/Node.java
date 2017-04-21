/**
 * Created by Anna on 4/20/17.
 */
import java.util.ArrayList;
import java.util.HashMap;

public class Node {
    public HashMap<Long, Node> adjacent; //do i want this to be a hashmap with IDs?
    public long id;
    public double lat;
    public double lon;

    public Node(long idInput, double latInput, double lonInput) {
        id = idInput;
        lat = latInput;
        lon = lonInput;
        adjacent = new HashMap<>();
    }

}
