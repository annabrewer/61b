/**
 * Created by Anna on 4/28/17.
 */
import lab14lib.Generator;

public class SawToothGenerator implements Generator {
    private int per;
    private int state;

    public SawToothGenerator(int per) {
        state = -1;
        this.per = per;
    }

    public double next() {
        state = (state + 1) % per;
        return normalize(state);
    }

    public double normalize(double input) {
        return -1 + ((input * 2) / per);
    }
}
