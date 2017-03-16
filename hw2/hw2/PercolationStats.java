package hw2;

import java.util.ArrayList;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {

    public int[] thresholds;

    public PercolationStats(int N, int T){
        Percolation p = new Percolation(N);
        thresholds = new int[T];
        //ArrayList<Integer> thresh = new ArrayList<Integer>();
        for(int i = 0; i < T; i++){
            int thresh = 0;
            while(!p.percolates()){
                thresh += 1;
                int r = (int)StdRandom.uniform()*N;
                int c = (int)StdRandom.uniform()*N;
                p.open(r, c);
            }
            thresholds[i] = thresh;
        }
    }   // perform T independent experiments on an N-by-N grid

    public double mean(){
        return StdStats.mean(thresholds);
    }                    // sample mean of percolation threshold

    public double stddev(){
        return StdStats.stddev(thresholds);
    }                  // sample standard deviation of percolation threshold

    public double confidenceLow(){
        double threshEstimate = mean();
        double sharpness = stddev();
        double T = thresholds.length;
        return threshEstimate - ((1.96 * sharpness) / Math.sqrt(T));

    }           // low  endpoint of 95% confidence interval

    public double confidenceHigh(){
        double threshEstimate = mean();
        double sharpness = stddev();
        double T = thresholds.length;
        return threshEstimate + ((1.96 * sharpness) / Math.sqrt(T));
    }

    //helper
    /*public double thingamajig() { //1.96o/sqrt(T)
        double sum = 0;
        double threshEstimate = mean();
        double sharpness = stddev();
        double T = thresholds.length;
        for (int i = 0; i < T; i++){
            double j = (thresholds[i] + threshEstimate);
            sum += j * j;
        }
        double o = sum / (T - 1);
        return (1.96 * sharpness) / Math.sqrt(T);
    }*/
}                       
