/**
 * Dijkstra.java
 * CIS 4020, Data Structures: Graphs
 *
 * Dijkstra's shortest path algorithm on a weighted directed graph stored as
 * an adjacency matrix. The method follows the textbook pseudocode closely:
 *
 *     vertexSet = { start }
 *     weight[v] = matrix[start][v] for every v
 *     repeat n - 1 times:
 *         v = the vertex not in vertexSet with the smallest weight
 *         add v to vertexSet
 *         for each u not in vertexSet:
 *             if weight[u] > weight[v] + matrix[v][u]
 *                 weight[u] = weight[v] + matrix[v][u]
 *
 * Two additions to the pseudocode:
 *   1. A predecessor array, so we can print the actual path, not just its cost.
 *   2. A check for vertices that cannot be reached. Their weight stays at
 *      infinity, and adding infinity to anything would overflow an int.
 *
 * The program prints the same trace table we build by hand in class.
 * Ties are broken by choosing the lowest index (alphabetical order).
 *
 * Compile and run:
 *     javac Dijkstra.java
 *     java Dijkstra
 */
public class Dijkstra {

    public static final int INF = Integer.MAX_VALUE;

    /**
     * Computes shortest path weights from start. Fills weight[] and pred[]
     * (pred[v] is the vertex just before v on the best path, or -1).
     */
    public static void shortestPath(int[][] matrix, int start, int[] weight, int[] pred,
                                    String[] labels) {
        int n = matrix.length;
        boolean[] inSet = new boolean[n];
        StringBuilder setText = new StringBuilder(labels[start]);

        // Step 1: the vertex set holds only start, and every weight is a direct edge.
        inSet[start] = true;
        for (int v = 0; v < n; v++) {
            weight[v] = matrix[start][v];
            pred[v] = (matrix[start][v] == INF) ? -1 : start;
        }
        weight[start] = 0;
        pred[start] = -1;

        printHeader(labels);
        printRow(1, "-", setText.toString(), weight);

        // Steps 2 through n: grow the vertex set one vertex at a time.
        for (int step = 2; step <= n; step++) {
            // Find the smallest weight[v] such that v is not in the vertex set.
            int v = -1;
            for (int i = 0; i < n; i++) {
                if (!inSet[i] && (v == -1 || weight[i] < weight[v])) {
                    v = i;
                }
            }
            if (v == -1 || weight[v] == INF) {
                // Everything left is unreachable from start, so we are done.
                System.out.println("  (no remaining vertex is reachable, so the algorithm stops)");
                break;
            }

            inSet[v] = true;
            setText.append(',').append(labels[v]);

            // Can going through v improve the path to any vertex u outside the set?
            for (int u = 0; u < n; u++) {
                if (!inSet[u] && matrix[v][u] != INF
                        && weight[u] > weight[v] + matrix[v][u]) {
                    weight[u] = weight[v] + matrix[v][u];
                    pred[u] = v;
                }
            }
            printRow(step, labels[v], setText.toString(), weight);
        }
    }

    // ---------- printing helpers ----------

    private static String w(int x) {
        return x == INF ? "inf" : String.valueOf(x);
    }

    private static void printHeader(String[] labels) {
        StringBuilder sb = new StringBuilder(String.format("  %-5s%-4s%-16s", "Step", "v", "Vertex set"));
        for (String s : labels) {
            sb.append(String.format("%-6s", "w[" + s + "]"));
        }
        System.out.println(sb);
    }

    private static void printRow(int step, String v, String set, int[] weight) {
        StringBuilder sb = new StringBuilder(String.format("  %-5d%-4s%-16s", step, v, set));
        for (int x : weight) {
            sb.append(String.format("%-6s", w(x)));
        }
        System.out.println(sb);
    }

    /** Builds the path to v by following pred[] backward, then reversing. */
    private static String path(int[] pred, int v, String[] labels) {
        String result = labels[v];
        for (int p = pred[v]; p != -1; p = pred[p]) {
            result = labels[p] + " -> " + result;
        }
        return result;
    }

    private static void run(String title, int[][] matrix, int start, String[] labels) {
        int n = matrix.length;
        int[] weight = new int[n];
        int[] pred = new int[n];
        System.out.println(title);
        shortestPath(matrix, start, weight, pred, labels);
        System.out.println("  Shortest paths from " + labels[start] + ":");
        for (int v = 0; v < n; v++) {
            if (v == start) continue;
            if (weight[v] == INF) {
                System.out.println("    to " + labels[v] + ": not reachable");
            } else {
                System.out.println("    to " + labels[v] + ": cost " + weight[v]
                        + "   path " + path(pred, v, labels));
            }
        }
        System.out.println();
    }

    /** Builds an n by n matrix filled with INF, then adds the listed directed edges. */
    private static int[][] build(String[] labels, String[][] edges) {
        int n = labels.length;
        int[][] m = new int[n][n];
        for (int[] row : m) {
            java.util.Arrays.fill(row, INF);
        }
        String all = String.join("", labels);
        for (String[] e : edges) {
            m[all.indexOf(e[0])][all.indexOf(e[1])] = Integer.parseInt(e[2]);
        }
        return m;
    }

    public static void main(String[] args) {
        // Example 1: five vertices, 0 through 4 (the class example).
        String[] five = {"0", "1", "2", "3", "4"};
        int[][] m1 = build(five, new String[][] {
            {"0", "1", "8"}, {"0", "3", "9"}, {"0", "4", "4"},
            {"1", "2", "1"},
            {"2", "1", "2"}, {"2", "3", "3"},
            {"3", "2", "2"}, {"3", "4", "7"},
            {"4", "2", "1"}
        });
        run("Example 1: start at vertex 0", m1, 0, five);

        // Example 2: seven vertices, A through G. Not every vertex is reachable.
        String[] seven = {"A", "B", "C", "D", "E", "F", "G"};
        int[][] m2 = build(seven, new String[][] {
            {"A", "B", "14"}, {"A", "D", "22"}, {"A", "E", "4"},
            {"B", "G", "3"},
            {"C", "B", "16"}, {"C", "D", "12"},
            {"E", "D", "12"}, {"E", "F", "10"},
            {"G", "F", "1"}
        });
        run("Example 2: start at vertex A", m2, 0, seven);
        run("Example 2: start at vertex C", m2, 2, seven);
    }
}
