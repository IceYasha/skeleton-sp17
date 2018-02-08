package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] fractions;
    private int T;

    /** perform T independent experiments on an N-by-N grid */
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0){
            throw new IllegalArgumentException("Given n <= 0 || t <= 0");
        }

        fractions = new double[trials];
        T = trials;
        for (int i = 0; i < trials; i++) {
            Percolation grid = new  Percolation(n);
            while (!grid.percolates()) {
                int row =  StdRandom.uniform(0, n);
                int col =  StdRandom.uniform(0, n);
                grid.open(row, col);
            }
            fractions[i] = grid.numberOfOpenSites() / (double) (n * n);
        }
    }

    /** sample mean of percolation threshold */
    public double mean() {
        return StdStats.mean(fractions);
    }

    /** sample standard deviation of percolation threshold */
    public double stddev() {
        return StdStats.stddev(fractions);
    }

    /** low  endpoint of 95% confidence interval */
    public double confidenceLow() {
        return mean() - 1.96 * stddev() /  Math.sqrt(T);
    }

    /** high endpoint of 95% confidence interval */
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() /  Math.sqrt(T);
    }

    public static void main(String[] args) {
        int n;
        int t;
        if (args.length == 0) {
            n = 200;
            t = 1000;
        } else {
            n = Integer.parseInt(args[0]);
            t = Integer.parseInt(args[1]);
        }
        // double startTime = System.nanoTime();
        PercolationStats stats = new PercolationStats(n, t);
        double confidenceLow = stats.confidenceLow();
        double confidenceHigh = stats.confidenceHigh();
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" + confidenceLow + ", "
                + confidenceHigh + "]");
    }
}                       
