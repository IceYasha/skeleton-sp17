package hw3.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import java.util.LinkedList;

public class Solver {

    private class SearchNode implements Comparable<SearchNode>{
        WorldState worldState;
        int M;
        SearchNode preNode;
        int E;

        SearchNode(WorldState worldState, int M, SearchNode preNode) {
            this.worldState = worldState;
            this.M = M;
            this.preNode = preNode;
            this.E = this.worldState.estimatedDistanceToGoal();
        }

        public int compareTo(SearchNode that) {
            int priority1 = this.M + E;
            int priority2 = that.M + that.E;

            return Integer.compare(priority1, priority2);
        }
    }

    private SearchNode currentNode;

    /** Constructor which solves the puzzle, computing
     everything necessary for moves() and solution() to
     not have to solve the problem again. Solves the
     puzzle using the A* algorithm. Assumes a solution exists. */
    public Solver(WorldState initial) {
        SearchNode preNode;
        currentNode = new SearchNode(initial, 0, null);

        MinPQ<SearchNode> nodesPQ = new MinPQ<>();
        nodesPQ.insert(currentNode);

        while (currentNode.E != 0) {
            for (WorldState x : currentNode.worldState.neighbors()) {
                if ((currentNode.preNode == null) ||
                        !currentNode.preNode.worldState.equals(x)) {
                    preNode = currentNode;
                    nodesPQ.insert(new SearchNode(x, preNode.M+1, preNode));
                }
            }
            currentNode = nodesPQ.delMin();
        }
    }

    /** Returns the minimum number of moves to solve the puzzle starting
     at the initial WorldState. */
    public int moves() {
        return currentNode.M;
    }

    /** Returns a sequence of WorldStates from the initial WorldState
     to the solution. */
    public Iterable<WorldState> solution() {
        LinkedList<WorldState> solutions = new LinkedList<>();

        while (currentNode.preNode != null) {
            solutions.addFirst(currentNode.worldState);
            currentNode = currentNode.preNode;
        }
        solutions.addFirst(currentNode.worldState);
        return solutions;
    }
}
