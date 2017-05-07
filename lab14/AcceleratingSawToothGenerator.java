/**
 * Created by Anna on 4/28/17.
 */
import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    //private int currPer;
    //private int initPer;
    private int per;
    private double acc;
    private int state;

    public AcceleratingSawToothGenerator(int per, double ac) {
        state = 0;
        this.acc = ac;
        //this.currPer = per;
        //this.initPer = per;
        this.per = per;
    }

    public double next() {
        state = (state + 1) % per;
        if (state == 0) {
            per = (int) (per * acc);
        }
        return normalize(state);
    }

    public double normalize(double input) {
        return -1 + ((input * 2) / per);
    }

    /*public double next() {
        state = (state + (1 / (currPer / initPer))) % currPer;
        if (state == 0) {
            currPer = (int) (currPer * 1.1);
        }
        return state;
    }*/
}
