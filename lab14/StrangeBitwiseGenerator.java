/**
 * Created by Anna on 4/28/17.
 */
import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator {
    private int per;
    private int state;

    public StrangeBitwiseGenerator(int per) {
        state = -1;
        this.per = per;
    }

    public double next() {
        state = state + 1;
        int weirdState = state & (state >>> 3) % per;
        return normalize(weirdState);
    }

    public double normalize(double input) {
        return -1 + ((input * 2) / per);
    }
}
