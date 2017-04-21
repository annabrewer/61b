import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Graph for storing all of the intersection (vertex) and road (edge) information.
 * Uses your GraphBuildingHandler to convert the XML files into a graph. Your
 * code must include the vertices, adjacent, distance, closest, lat, and lon
 * methods. You'll also need to include instance variables and methods for
 * modifying the graph (e.g. addNode and addEdge).
 *
 * @author Alan Yao, Josh Hug
 */
public class GraphDB {
    /** Your instance variables for storing the graph. You should consider
     * creating helper classes, e.g. Node, Edge, etc. */
    //public ArrayList<Edge> edges
    public HashMap<Long, Node> nodes;

    /**
     * Example constructor shows how to create and start an XML parser.
     * You do not need to modify this constructor, but you're welcome to do so.
     * @param dbPath Path to the XML file to be parsed.
     */
    public GraphDB(String dbPath) {

        nodes = new HashMap<>();
        try {
            File inputFile = new File(dbPath);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            GraphBuildingHandler gbh = new GraphBuildingHandler(this);
            saxParser.parse(inputFile, gbh);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        clean();
    }

    /**
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

    /**
     *  Remove nodes with no connections from the graph.
     *  While this does not guarantee that any two nodes in the remaining graph are connected,
     *  we can reasonably assume this since typically roads are connected.
     */
    private void clean() {
        /*Iterator<long> itr = nodes.keySet().iterator();
        while(itr.hasNext()) {
            long v = itr.next()
        }*/
        HashMap<Long, Node> thing = new HashMap<>();
        for (long v : nodes.keySet()) {
            if (!nodes.get(v).adjacent.isEmpty()) {
                thing.put(v, nodes.get(v));
            }
        }
        nodes = thing;
    }

    /** Returns an iterable of all vertex IDs in the graph. */
    Iterable<Long> vertices() {
        //YOUR CODE HERE, this currently returns only an empty list.
        return nodes.keySet();
    }

    /** Returns ids of all vertices adjacent to v. */
    Iterable<Long> adjacent(long v) {
        return nodes.get(v).adjacent; //.keySet();
    }

    /** Returns the Euclidean distance between vertices v and w, where Euclidean distance
     *  is defined as sqrt( (lonV - lonV)^2 + (latV - latV)^2 ). */
    double distance(long v, long w) {
        Node nv = nodes.get(v);
        Node nw = nodes.get(w);
        return Math.sqrt(Math.pow((nv.lon - nw.lon), 2) + Math.pow((nv.lat - nw.lat), 2));
    }

    /** Returns the vertex id closest to the given longitude and latitude. */
    long closest(double lon, double lat) {
        Node temp = new Node(0L, lat, lon);
        long vert = 0L;

        Iterator<Long> i = nodes.keySet().iterator();
        Long randKey = i.next();

        nodes.put(vert, temp);

        double dist = distance(0L, randKey);

        for (long v : nodes.keySet()) {
            if (v != 0L) {
                if (distance(v, 0L) < dist) {
                    dist = (distance(v, 0L));
                    vert = v;
                }
            }
        }
        nodes.remove(0L);
        return vert;
    }

    /** Longitude of vertex v. */
    double lon(long v) {
        return nodes.get(v).lon;
    }

    /** Latitude of vertex v. */
    double lat(long v) {
        return nodes.get(v).lat;
    }

    /*addNode (){

    }

    addEdge {

    }

    addWay {

    }*/
}
