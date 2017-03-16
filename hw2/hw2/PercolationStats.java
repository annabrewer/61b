package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {

    private double[] ratios;

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        int[] thresholds = new int[T];
        //ArrayList<Integer> thresh = new ArrayList<Integer>();
        for (int i = 0; i < T; i++) {
            Percolation p = new Percolation(N);
            int thresh = 0;
            while (!p.percolates()) {
                int r = (int) (StdRandom.uniform() * N);
                int c = (int) (StdRandom.uniform() * N);
                if (!p.isOpen(r, c)) {
                    p.open(r, c);
                    thresh += 1;
                }
            }
            thresholds[i] = thresh;
        }
        ratios = new double[T];
        for (int i = 0; i < T; i++) {
            ratios[i] = (double) thresholds[i] / (N * N);
        }
    }   // perform T independent experiments on an N-by-N grid

    public double mean() {
        return StdStats.mean(ratios);
    }                    // sample mean of percolation threshold

    public double stddev() {
        return StdStats.stddev(ratios);
    }                  // sample standard deviation of percolation threshold

    public double confidenceLow() {
        double threshEstimate = mean();
        double sharpness = stddev();
        double T = ratios.length;
        return threshEstimate - ((1.96 * sharpness) / Math.sqrt(T));

    }           // low  endpoint of 95% confidence interval

    public double confidenceHigh() {
        double threshEstimate = mean();
        double sharpness = stddev();
        double T = ratios.length;
        return threshEstimate + ((1.96 * sharpness) / Math.sqrt(T));
    }

    public static void main(String[] args) {
        /*for (int i = 0; i<20;i++){
            System.out.println((int)(StdRandom.uniform()*5));
        }*/
        PercolationStats ps = new PercolationStats(5, 30);

        System.out.println(ps.mean());
        System.out.println(ps.stddev());
        System.out.println(ps.confidenceLow());
        System.out.println(ps.confidenceHigh());
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
