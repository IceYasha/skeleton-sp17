package hw3.puzzle;

import java.lang.IndexOutOfBoundsException;
import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {

    private int[][] tiles;
    private int N;

    /** Constructs a board from an N-by-N array of tiles where
     tiles[i][j] = tile at row i, column j */
    public Board(int[][] tiles) {
        this.N = tiles.length;
        this.tiles = new int[N][N];

        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.N; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    /** Returns value of tile at row i, column j (or 0 if blank) */
    public int tileAt(int i, int j) {
        if (0 <= i && i <= this.N-1 && 0 <= j && j <= this.N-1) {
            return this.tiles[i][j];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /** Returns the board size N */
    public int size() {
        return N;
    }

    @Override
    /** Returns the neighbors of the current board
     * Source : http://joshh.ug/neighbors.html */
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int blankX = -1;
        int blankY = -1;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (tileAt(x, y) == 0) {
                    blankX = x;
                    blankY = y;
                }
            }
        }
        int[][] copyBoard = new int[N][N];
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                copyBoard[x][y] = tileAt(x, y);
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (Math.abs(-blankX + i) + Math.abs(j - blankY) - 1 == 0) {
                    copyBoard[blankX][blankY] = copyBoard[i][j];
                    copyBoard[i][j] = 0;
                    Board neighbor = new Board(copyBoard);
                    neighbors.enqueue(neighbor);
                    copyBoard[i][j] = copyBoard[blankX][blankY];
                    copyBoard[blankX][blankY] = 0;
                }
            }
        }
        return neighbors;
    }

    /**  Hamming estimate */
    public int hamming() {
        int hamming = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) != 0 && tileAt(i, j) != i*N + j + 1) {
                    hamming++;
                }
            }
        }
        return hamming;
    }

    /** Manhattan estimate */
    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) != 0 && tileAt(i, j) != i*N + j + 1) {
                    int x = tileAt(i, j) / N;
                    int y = tileAt(i, j) % N - 1;
                    manhattan += Math.abs(i - x) + Math.abs(j - y);
                }
            }
        }
        return manhattan;
    }

    @Override
    /** Estimated distance to goal. This method should
     simply return the results of manhattan() when submitted to
     Gradescope. */
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    @Override
    /** Returns true if is this board the goal board */
    public boolean isGoal() {
        return manhattan() == 0;
    }

    /** Returns true if this board's tile values are the same
     position as y's */
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (y == null || getClass() != y.getClass()) {
            return false;
        }

        Board board1 = (Board) y;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] != board1.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Returns the string representation of the board.
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
