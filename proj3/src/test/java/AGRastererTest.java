import org.junit.Test;

import java.util.Map;
import java.util.HashMap;

public class AGRastererTest extends AGMapTest {
    /**
     * Test the rastering functionality of the student code, by calling their getMapRaster method
     * and ensuring that the resulting map is correct. All of the test data is stored in a
     * TestParameters object that is loaded by the AGMapTest constructor. Note that this test file
     * extends AGMapTest, where most of the interesting stuff happens.
     * @throws Exception
     */
    @Test
    public void testGetMapRaster() throws Exception {
        for (TestParameters p : params) {
            /*HashMap<String, Double> m = new HashMap<>();
            m.put("lrlon", 7.0);
            m.put("ullon", 2.0);
            m.put("lrlat", 1.0);
            m.put("ullat", 5.0);
            m.put("w", 40.0);
            m.put("h", 30.0);
            Map<String, Object> studentRasterResult2 = rasterer.getMapRaster(m);
            System.out.println(studentRasterResult2);*/
            Map<String, Object> studentRasterResult = rasterer.getMapRaster(p.rasterParams);
            System.out.println(studentRasterResult);
            System.out.println(p.rasterResult);
            //System.out.println(p);
            checkParamsMap("Returned result differed for input: " + p.rasterParams + ".\n",
                    p.rasterResult, studentRasterResult);
        }
    }
}
