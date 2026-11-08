import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;

/**
 * AdjacencyListGraph.java
 * CIS 4020, Data Structures: Graphs
 *
 * A weighted graph stored as an adjacency list. Vertex i owns a linked list
 * of the edges that leave it. Each list is kept in alphabetical (index) order
 * so that DFS and BFS visit neighbors in the same order we use by hand.
 *
 * The same class handles undirected graphs (each edge is stored twice, once
 * in each endpoint's list) and directed graphs (each edge is stored once).
 *
 * Compile and run:
 *     javac AdjacencyListGraph.java
 *     java AdjacencyListGraph
 */
public class AdjacencyListGraph {

    /** One entry in an adjacency list: the vertex we can reach and the cost. */
    private static class Edge {
        final int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    private final String[] labels;               // labels[i] is the name of vertex i
    private final List<LinkedList<Edge>> adj;    // adj.get(i) is vertex i's list
    private final boolean directed;
    private int edgeCount;

    /** Creates a graph with the given vertex labels and no edges. */
    public AdjacencyListGraph(String[] labels, boolean directed) {
        this.labels = labels.clone();
        this.directed = directed;
        this.adj = new ArrayList<>();
        for (int i = 0; i < labels.length; i++) {
            adj.add(new LinkedList<>());
        }
        this.edgeCount = 0;
    }

    public int getNumVertices() { return labels.length; }
    public int getNumEdges()    { return edgeCount; }
    public boolean isEmpty()    { return labels.length == 0; }

    /** Returns the index of the vertex with the given label, or -1. */
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
        boolean isNew = insertSorted(adj.get(u), v, weight);
        if (!directed) {
            insertSorted(adj.get(v), u, weight);
        }
        if (isNew) {
            edgeCount++;
        }
    }

    /** Places (to, weight) in the list so the list stays sorted by vertex index. */
    private boolean insertSorted(LinkedList<Edge> list, int to, int weight) {
        ListIterator<Edge> it = list.listIterator();
        while (it.hasNext()) {
            Edge e = it.next();
            if (e.to == to) {          // edge already exists, so update it
                e.weight = weight;
                return false;
            }
            if (e.to > to) {           // found the spot, so step back and insert
                it.previous();
                break;
            }
        }
        it.add(new Edge(to, weight));
        return true;
    }

    /** Removes the edge u-v if it exists. */
    public void removeEdge(int u, int v) {
        boolean removed = adj.get(u).removeIf(e -> e.to == v);
        if (!directed) {
            adj.get(v).removeIf(e -> e.to == u);
        }
        if (removed) {
            edgeCount--;
        }
    }

    /**
     * Operation 1: is there an edge from u to v?
     * With a list we must walk u's list, so this is O(degree of u).
     */
    public boolean hasEdge(int u, int v) {
        for (Edge e : adj.get(u)) {
            if (e.to == v) {
                return true;
            }
        }
        return false;
    }

    /** Returns the weight of edge u-v, or -1 if there is no such edge. */
    public int getWeight(int u, int v) {
        for (Edge e : adj.get(u)) {
            if (e.to == v) {
                return e.weight;
            }
        }
        return -1;
    }

    /**
     * Operation 2: which vertices are adjacent to v?
     * The list already holds exactly those vertices, so this is O(degree of v).
     */
    public List<Integer> neighbors(int v) {
        List<Integer> result = new ArrayList<>();
        for (Edge e : adj.get(v)) {
            result.add(e.to);
        }
        return result;
    }

    /** Depth-first search from start. Returns the vertices in the order visited. */
    public List<Integer> dfs(int start) {
        boolean[] visited = new boolean[labels.length];
        List<Integer> order = new ArrayList<>();
        List<String> treeEdges = new ArrayList<>();
        dfsHelper(start, visited, order, treeEdges);
        System.out.println("  spanning tree edges: " + treeEdges);
        return order;
    }

    private void dfsHelper(int v, boolean[] visited, List<Integer> order, List<String> treeEdges) {
        visited[v] = true;                         // mark v as visited
        order.add(v);
        for (Edge e : adj.get(v)) {                // for each vertex u adjacent to v
            int u = e.to;
            if (!visited[u]) {                     // that is still unvisited
                treeEdges.add(labels[v] + "-" + labels[u]);
                dfsHelper(u, visited, order, treeEdges);
            }
        }
    }

    /** Breadth-first search from start. Returns the vertices in the order visited. */
    public List<Integer> bfs(int start) {
        boolean[] visited = new boolean[labels.length];
        List<Integer> order = new ArrayList<>();
        List<String> treeEdges = new ArrayList<>();
        Queue<Integer> q = new ArrayDeque<>();

        q.add(start);
        visited[start] = true;
        order.add(start);
        while (!q.isEmpty()) {
            int w = q.remove();
            for (Edge e : adj.get(w)) {
                int u = e.to;
                if (!visited[u]) {
                    visited[u] = true;             // mark when enqueued, not when dequeued
                    order.add(u);
                    treeEdges.add(labels[w] + "-" + labels[u]);
                    q.add(u);
                }
            }
        }
        System.out.println("  spanning tree edges: " + treeEdges);
        return order;
    }

    /**
     * Topological sort (directed acyclic graphs only), DFS version.
     * A vertex is pushed onto the stack after everything it points to is
     * finished, so popping the stack gives a valid topological order.
     */
    public List<Integer> topologicalSort() {
        boolean[] visited = new boolean[labels.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int v = 0; v < labels.length; v++) {
            if (!visited[v]) {
                topoHelper(v, visited, stack);
            }
        }
        return new ArrayList<>(stack);             // ArrayDeque iterates from the top
    }

    private void topoHelper(int v, boolean[] visited, Deque<Integer> stack) {
        visited[v] = true;
        for (Edge e : adj.get(v)) {
            if (!visited[e.to]) {
                topoHelper(e.to, visited, stack);
            }
        }
        stack.push(v);                             // v is finished
    }

    /** Converts a list of indices to their labels. */
    public String names(List<Integer> vertices) {
        StringBuilder sb = new StringBuilder();
        for (int v : vertices) {
            if (sb.length() > 0) sb.append(' ');
            sb.append(labels[v]);
        }
        return sb.toString();
    }

    /** Prints each vertex followed by its list, for example  a: b(6) -> f(4) -> i(2) */
    public void print() {
        for (int v = 0; v < labels.length; v++) {
            StringBuilder sb = new StringBuilder("  " + labels[v] + ":");
            String arrow = " ";
            for (Edge e : adj.get(v)) {
                sb.append(arrow).append(labels[e.to]).append('(').append(e.weight).append(')');
                arrow = " -> ";
            }
            System.out.println(sb);
        }
    }

    public static void main(String[] args) {
        // The class example graph G: nine vertices a through i, ten weighted edges.
        String[] names = {"a", "b", "c", "d", "e", "f", "g", "h", "i"};
        AdjacencyListGraph g = new AdjacencyListGraph(names, false);
        String[][] edges = {
            {"i", "a", "2"}, {"a", "b", "6"}, {"a", "f", "4"}, {"b", "e", "9"},
            {"b", "c", "7"}, {"e", "g", "8"}, {"c", "d", "4"}, {"f", "g", "2"},
            {"g", "d", "5"}, {"d", "h", "1"}
        };
        for (String[] e : edges) {
            g.addEdge(g.indexOf(e[0]), g.indexOf(e[1]), Integer.parseInt(e[2]));
        }

        System.out.println("Graph G as an adjacency list");
        System.out.println("vertices = " + g.getNumVertices() + ", edges = " + g.getNumEdges());
        g.print();

        int a = g.indexOf("a"), c = g.indexOf("c"), d = g.indexOf("d");
        System.out.println();
        System.out.println("hasEdge(a, b)? " + g.hasEdge(a, g.indexOf("b"))
                + "   weight = " + g.getWeight(a, g.indexOf("b")));
        System.out.println("hasEdge(a, c)? " + g.hasEdge(a, c));
        System.out.println("neighbors(d) = " + g.names(g.neighbors(d)));

        for (String s : new String[] {"i", "h"}) {
            System.out.println();
            System.out.println("DFS(" + s + ")");
            System.out.println("  visit order: " + g.names(g.dfs(g.indexOf(s))));
            System.out.println("BFS(" + s + ")");
            System.out.println("  visit order: " + g.names(g.bfs(g.indexOf(s))));
        }

        // A directed acyclic graph: getting dinner on the table.
        String[] tasks = {"boil water", "add pasta", "heat sauce", "set table",
                          "pour drinks", "serve pasta", "eat"};
        AdjacencyListGraph dinner = new AdjacencyListGraph(tasks, true);
        String[][] before = {
            {"boil water", "add pasta"}, {"add pasta", "serve pasta"},
            {"heat sauce", "serve pasta"}, {"set table", "serve pasta"},
            {"set table", "pour drinks"}, {"pour drinks", "eat"},
            {"serve pasta", "eat"}
        };
        for (String[] e : before) {
            dinner.addEdge(dinner.indexOf(e[0]), dinner.indexOf(e[1]), 1);
        }
        System.out.println();
        System.out.println("Topological order of the dinner tasks:");
        List<Integer> order = dinner.topologicalSort();
        for (int i = 0; i < order.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + tasks[order.get(i)]);
        }
    }
}
