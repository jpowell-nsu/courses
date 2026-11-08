import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * AdjacencyMatrixGraph.java
 * CIS 4020, Data Structures: Graphs
 *
 * A weighted graph stored as an adjacency matrix (also called a cost
 * matrix). matrix[u][v] holds the weight of the edge from u to v, or
 * NO_EDGE when there is no edge. An undirected graph has a symmetric matrix:
 * matrix[u][v] == matrix[v][u].
 *
 * This class has the same public operations as AdjacencyListGraph so the
 * two can be compared side by side. Only the storage is different.
 *
 * Compile and run:
 *     javac AdjacencyMatrixGraph.java
 *     java AdjacencyMatrixGraph
 */
public class AdjacencyMatrixGraph {

    /** Marks "no edge." Infinity in the textbook; any impossible weight works. */
    public static final int NO_EDGE = Integer.MAX_VALUE;

    private final String[] labels;
    private final int[][] matrix;
    private final boolean directed;
    private int edgeCount;

    /** Creates a graph with the given vertex labels and no edges. */
    public AdjacencyMatrixGraph(String[] labels, boolean directed) {
        this.labels = labels.clone();
        this.directed = directed;
        int n = labels.length;
        this.matrix = new int[n][n];               // n * n cells, used or not
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                matrix[u][v] = NO_EDGE;
            }
        }
        this.edgeCount = 0;
    }

    public int getNumVertices() { return labels.length; }
    public int getNumEdges()    { return edgeCount; }
    public boolean isEmpty()    { return labels.length == 0; }

    public int indexOf(String label) {
        for (int i = 0; i < labels.length; i++) {
            if (labels[i].equals(label)) {
                return i;
            }
        }
        return -1;
    }

    /** Inserts the edge u-v with the given weight. Replaces the weight if it exists. */
    public void addEdge(int u, int v, int weight) {
        if (matrix[u][v] == NO_EDGE) {
            edgeCount++;
        }
        matrix[u][v] = weight;
        if (!directed) {
            matrix[v][u] = weight;                 // keep the matrix symmetric
        }
    }

    /** Removes the edge u-v if it exists. */
    public void removeEdge(int u, int v) {
        if (matrix[u][v] != NO_EDGE) {
            edgeCount--;
        }
        matrix[u][v] = NO_EDGE;
        if (!directed) {
            matrix[v][u] = NO_EDGE;
        }
    }

    /**
     * Operation 1: is there an edge from u to v?
     * One array lookup, so this is O(1). This is where the matrix shines.
     */
    public boolean hasEdge(int u, int v) {
        return matrix[u][v] != NO_EDGE;
    }

    /** Returns the weight of edge u-v, or -1 if there is no such edge. */
    public int getWeight(int u, int v) {
        return hasEdge(u, v) ? matrix[u][v] : -1;
    }

    /**
     * Operation 2: which vertices are adjacent to v?
     * We must scan the whole row, so this is O(n) even if v has one neighbor.
     */
    public List<Integer> neighbors(int v) {
        List<Integer> result = new ArrayList<>();
        for (int u = 0; u < labels.length; u++) {
            if (matrix[v][u] != NO_EDGE) {
                result.add(u);
            }
        }
        return result;
    }

    /** Depth-first search from start. Returns the vertices in the order visited. */
    public List<Integer> dfs(int start) {
        boolean[] visited = new boolean[labels.length];
        List<Integer> order = new ArrayList<>();
        dfsHelper(start, visited, order);
        return order;
    }

    private void dfsHelper(int v, boolean[] visited, List<Integer> order) {
        visited[v] = true;
        order.add(v);
        for (int u = 0; u < labels.length; u++) {  // scan row v, in index order
            if (matrix[v][u] != NO_EDGE && !visited[u]) {
                dfsHelper(u, visited, order);
            }
        }
    }

    /** Breadth-first search from start. Returns the vertices in the order visited. */
    public List<Integer> bfs(int start) {
        boolean[] visited = new boolean[labels.length];
        List<Integer> order = new ArrayList<>();
        Queue<Integer> q = new ArrayDeque<>();

        q.add(start);
        visited[start] = true;
        order.add(start);
        while (!q.isEmpty()) {
            int w = q.remove();
            for (int u = 0; u < labels.length; u++) {
                if (matrix[w][u] != NO_EDGE && !visited[u]) {
                    visited[u] = true;
                    order.add(u);
                    q.add(u);
                }
            }
        }
        return order;
    }

    public String names(List<Integer> vertices) {
        StringBuilder sb = new StringBuilder();
        for (int v : vertices) {
            if (sb.length() > 0) sb.append(' ');
            sb.append(labels[v]);
        }
        return sb.toString();
    }

    /** Prints the matrix with row and column labels. A dash means no edge. */
    public void print() {
        StringBuilder header = new StringBuilder("     ");
        for (String s : labels) {
            header.append(String.format("%4s", s));
        }
        System.out.println(header);
        for (int u = 0; u < labels.length; u++) {
            StringBuilder row = new StringBuilder(String.format("  %-3s", labels[u]));
            for (int v = 0; v < labels.length; v++) {
                row.append(String.format("%4s", matrix[u][v] == NO_EDGE ? "-" : matrix[u][v]));
            }
            System.out.println(row);
        }
    }

    public static void main(String[] args) {
        // The same class example graph G used in AdjacencyListGraph.
        String[] names = {"a", "b", "c", "d", "e", "f", "g", "h", "i"};
        AdjacencyMatrixGraph g = new AdjacencyMatrixGraph(names, false);
        String[][] edges = {
            {"i", "a", "2"}, {"a", "b", "6"}, {"a", "f", "4"}, {"b", "e", "9"},
            {"b", "c", "7"}, {"e", "g", "8"}, {"c", "d", "4"}, {"f", "g", "2"},
            {"g", "d", "5"}, {"d", "h", "1"}
        };
        for (String[] e : edges) {
            g.addEdge(g.indexOf(e[0]), g.indexOf(e[1]), Integer.parseInt(e[2]));
        }

        int n = g.getNumVertices();
        System.out.println("Graph G as an adjacency matrix");
        System.out.println("vertices = " + n + ", edges = " + g.getNumEdges()
                + ", cells used = " + (2 * g.getNumEdges()) + " of " + (n * n));
        g.print();

        int a = g.indexOf("a"), c = g.indexOf("c"), d = g.indexOf("d");
        System.out.println();
        System.out.println("hasEdge(a, b)? " + g.hasEdge(a, g.indexOf("b"))
                + "   weight = " + g.getWeight(a, g.indexOf("b")));
        System.out.println("hasEdge(a, c)? " + g.hasEdge(a, c));
        System.out.println("neighbors(d) = " + g.names(g.neighbors(d)));

        for (String s : new String[] {"i", "h"}) {
            System.out.println();
            System.out.println("DFS(" + s + ") visit order: " + g.names(g.dfs(g.indexOf(s))));
            System.out.println("BFS(" + s + ") visit order: " + g.names(g.bfs(g.indexOf(s))));
        }
    }
}
