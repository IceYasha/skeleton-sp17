package hw2;                       

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int gridSize;
    private boolean[] siteisOpen;
    private int numberOfOpenSites;
    private WeightedQuickUnionUF grid;

    /**create N-by-N grid, with all sites initially blocked */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("row index i out of bounds");
        }

        gridSize = N;
        siteisOpen = new boolean [N*N+2];
        grid = new WeightedQuickUnionUF(N*N+2);
        for (int i = 1; i <= N; i++) grid.union(0, i);
        for (int i = N * N - N + 1; i <= N * N; i++) grid.union(N*N+1, i);
    }

    /** Open the gird[row, col] if it is not open already.
     * Open the site if it not opened, check the index WASD if it's opened
     * then union index to it's neighbour */
    public void open(int row, int col) {
        int index = xyTo1D(row, col);
        if (!siteisOpen[index]) {
            siteisOpen[index] = true;
            numberOfOpenSites++;
            if (col < gridSize-1 && siteisOpen[index+1]) {
                grid.union(index, index+1);}
            if (col > 0 && siteisOpen[index-1]) {
                grid.union(index, index-1);}
            if (row < gridSize-1 && siteisOpen[index+ gridSize]) {
                grid.union(index, index+ gridSize);}
            if (row > 0 && siteisOpen[index- gridSize]) {
                grid.union(index, index- gridSize);}
        }
    }

    /** s the site (row, col) open? */
    public boolean isOpen(int row, int col) {
        int index = xyTo1D(row, col);
        return siteisOpen[index];
    }

    /** is the site (row, col) full? */
    public boolean isFull(int row, int col) {
        int index = xyTo1D(row, col);
        return (siteisOpen[index] && grid.connected(0, index));
    }

    /** number of open sites */
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    /** does the system percolate? */
    public boolean percolates() {
        return grid.connected(0, gridSize*gridSize+1);
    }

    /** converse 2D to 1D array index */
    private int xyTo1D(int row, int col) {
        checkindice(row, col);
        return (row * gridSize + col);
    }


    private void checkindice(int row, int col) {
        if (row < 0 || row >= gridSize || col < 0 || col >= gridSize)
        { throw new IndexOutOfBoundsException("row index i out of bounds"); }
    }

    /** unit testing (not required) */
    public static void main(String[] args) {

    }
}                       
