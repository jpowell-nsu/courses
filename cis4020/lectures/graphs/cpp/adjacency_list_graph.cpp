// adjacency_list_graph.cpp
// CIS 4020, Data Structures: Graphs
//
// A weighted graph stored as an adjacency list. Vertex i owns a linked list
// (std::list) of the edges that leave it. Each list is kept in alphabetical
// (index) order so that DFS and BFS visit neighbors in the same order we use
// by hand.
//
// The same class handles undirected graphs (each edge is stored twice, once
// in each endpoint's list) and directed graphs (each edge is stored once).
// The standard containers own all of the memory, so there is no new or
// delete anywhere in this file.
//
// Compile and run:
//     g++ -std=c++17 -Wall -o adjacency_list_graph adjacency_list_graph.cpp
//     ./adjacency_list_graph

#include <iostream>
#include <list>
#include <queue>
#include <stack>
#include <string>
#include <vector>

class AdjacencyListGraph {
public:
    AdjacencyListGraph(const std::vector<std::string>& labels, bool directed)
        : labels_(labels), adj_(labels.size()), directed_(directed), edgeCount_(0) {}

    int numVertices() const { return static_cast<int>(labels_.size()); }
    int numEdges() const { return edgeCount_; }
    bool isEmpty() const { return labels_.empty(); }

    // Returns the index of the vertex with the given label, or -1.
    int indexOf(const std::string& label) const {
        for (int i = 0; i < numVertices(); i++) {
            if (labels_[i] == label) return i;
        }
        return -1;
    }

    // Inserts the edge u-v with the given weight. Replaces the weight if it exists.
    void addEdge(int u, int v, int weight) {
        bool isNew = insertSorted(adj_[u], v, weight);
        if (!directed_) insertSorted(adj_[v], u, weight);
        if (isNew) edgeCount_++;
    }

    // Removes the edge u-v if it exists.
    void removeEdge(int u, int v) {
        auto sizeBefore = adj_[u].size();
        adj_[u].remove_if([v](const Edge& e) { return e.to == v; });
        if (!directed_) adj_[v].remove_if([u](const Edge& e) { return e.to == u; });
        if (adj_[u].size() < sizeBefore) edgeCount_--;
    }

    // Operation 1: is there an edge from u to v?
    // With a list we must walk u's list, so this is O(degree of u).
    bool hasEdge(int u, int v) const {
        for (const Edge& e : adj_[u]) {
            if (e.to == v) return true;
        }
        return false;
    }

    // Returns the weight of edge u-v, or -1 if there is no such edge.
    int getWeight(int u, int v) const {
        for (const Edge& e : adj_[u]) {
            if (e.to == v) return e.weight;
        }
        return -1;
    }

    // Operation 2: which vertices are adjacent to v?
    // The list already holds exactly those vertices, so this is O(degree of v).
    std::vector<int> neighbors(int v) const {
        std::vector<int> result;
        for (const Edge& e : adj_[v]) result.push_back(e.to);
        return result;
    }

    // Depth-first search from start. Returns the vertices in the order visited.
    std::vector<int> dfs(int start) const {
        std::vector<bool> visited(numVertices(), false);
        std::vector<int> order;
        std::vector<std::string> treeEdges;
        dfsHelper(start, visited, order, treeEdges);
        printEdges(treeEdges);
        return order;
    }

    // Breadth-first search from start. Returns the vertices in the order visited.
    std::vector<int> bfs(int start) const {
        std::vector<bool> visited(numVertices(), false);
        std::vector<int> order;
        std::vector<std::string> treeEdges;
        std::queue<int> q;

        q.push(start);
        visited[start] = true;
        order.push_back(start);
        while (!q.empty()) {
            int w = q.front();
            q.pop();
            for (const Edge& e : adj_[w]) {
                int u = e.to;
                if (!visited[u]) {
                    visited[u] = true;           // mark when enqueued, not when dequeued
                    order.push_back(u);
                    treeEdges.push_back(labels_[w] + "-" + labels_[u]);
                    q.push(u);
                }
            }
        }
        printEdges(treeEdges);
        return order;
    }

    // Topological sort (directed acyclic graphs only), DFS version.
    // A vertex is pushed onto the stack after everything it points to is
    // finished, so popping the stack gives a valid topological order.
    std::vector<int> topologicalSort() const {
        std::vector<bool> visited(numVertices(), false);
        std::stack<int> s;
        for (int v = 0; v < numVertices(); v++) {
            if (!visited[v]) topoHelper(v, visited, s);
        }
        std::vector<int> order;
        while (!s.empty()) {
            order.push_back(s.top());
            s.pop();
        }
        return order;
    }

    std::string names(const std::vector<int>& vertices) const {
        std::string result;
        for (int v : vertices) {
            if (!result.empty()) result += ' ';
            result += labels_[v];
        }
        return result;
    }

    const std::string& label(int v) const { return labels_[v]; }

    // Prints each vertex followed by its list, for example  a: b(6) -> f(4) -> i(2)
    void print() const {
        for (int v = 0; v < numVertices(); v++) {
            std::cout << "  " << labels_[v] << ":";
            std::string arrow = " ";
            for (const Edge& e : adj_[v]) {
                std::cout << arrow << labels_[e.to] << '(' << e.weight << ')';
                arrow = " -> ";
            }
            std::cout << '\n';
        }
    }

private:
    // One entry in an adjacency list: the vertex we can reach and the cost.
    struct Edge {
        int to;
        int weight;
    };

    std::vector<std::string> labels_;      // labels_[i] is the name of vertex i
    std::vector<std::list<Edge>> adj_;     // adj_[i] is vertex i's list
    bool directed_;
    int edgeCount_;

    // Places (to, weight) in the list so the list stays sorted by vertex index.
    static bool insertSorted(std::list<Edge>& lst, int to, int weight) {
        auto it = lst.begin();
        while (it != lst.end() && it->to < to) ++it;
        if (it != lst.end() && it->to == to) {    // edge already exists, so update it
            it->weight = weight;
            return false;
        }
        lst.insert(it, Edge{to, weight});
        return true;
    }

    void dfsHelper(int v, std::vector<bool>& visited, std::vector<int>& order,
                   std::vector<std::string>& treeEdges) const {
        visited[v] = true;                         // mark v as visited
        order.push_back(v);
        for (const Edge& e : adj_[v]) {            // for each vertex u adjacent to v
            int u = e.to;
            if (!visited[u]) {                     // that is still unvisited
                treeEdges.push_back(labels_[v] + "-" + labels_[u]);
                dfsHelper(u, visited, order, treeEdges);
            }
        }
    }

    void topoHelper(int v, std::vector<bool>& visited, std::stack<int>& s) const {
        visited[v] = true;
        for (const Edge& e : adj_[v]) {
            if (!visited[e.to]) topoHelper(e.to, visited, s);
        }
        s.push(v);                                 // v is finished
    }

    static void printEdges(const std::vector<std::string>& edges) {
        std::cout << "  spanning tree edges: [";
        for (std::size_t i = 0; i < edges.size(); i++) {
            std::cout << (i ? ", " : "") << edges[i];
        }
        std::cout << "]\n";
    }
};

int main() {
    // The class example graph G: nine vertices a through i, ten weighted edges.
    AdjacencyListGraph g({"a", "b", "c", "d", "e", "f", "g", "h", "i"}, false);
    struct E { std::string u, v; int w; };
    std::vector<E> edges = {
        {"i", "a", 2}, {"a", "b", 6}, {"a", "f", 4}, {"b", "e", 9},
        {"b", "c", 7}, {"e", "g", 8}, {"c", "d", 4}, {"f", "g", 2},
        {"g", "d", 5}, {"d", "h", 1}
    };
    for (const E& e : edges) g.addEdge(g.indexOf(e.u), g.indexOf(e.v), e.w);

    std::cout << "Graph G as an adjacency list\n";
    std::cout << "vertices = " << g.numVertices() << ", edges = " << g.numEdges() << '\n';
    g.print();

    int a = g.indexOf("a"), b = g.indexOf("b"), c = g.indexOf("c"), d = g.indexOf("d");
    std::cout << std::boolalpha << '\n';
    std::cout << "hasEdge(a, b)? " << g.hasEdge(a, b) << "   weight = " << g.getWeight(a, b) << '\n';
    std::cout << "hasEdge(a, c)? " << g.hasEdge(a, c) << '\n';
    std::cout << "neighbors(d) = " << g.names(g.neighbors(d)) << '\n';

    for (std::string s : {"i", "h"}) {
        std::cout << "\nDFS(" << s << ")\n";
        std::vector<int> order = g.dfs(g.indexOf(s));
        std::cout << "  visit order: " << g.names(order) << '\n';
        std::cout << "BFS(" << s << ")\n";
        order = g.bfs(g.indexOf(s));
        std::cout << "  visit order: " << g.names(order) << '\n';
    }

    // A directed acyclic graph: getting dinner on the table.
    AdjacencyListGraph dinner({"boil water", "add pasta", "heat sauce", "set table",
                               "pour drinks", "serve pasta", "eat"}, true);
    std::vector<std::pair<std::string, std::string>> before = {
        {"boil water", "add pasta"}, {"add pasta", "serve pasta"},
        {"heat sauce", "serve pasta"}, {"set table", "serve pasta"},
        {"set table", "pour drinks"}, {"pour drinks", "eat"},
        {"serve pasta", "eat"}
    };
    for (const auto& e : before) dinner.addEdge(dinner.indexOf(e.first), dinner.indexOf(e.second), 1);

    std::cout << "\nTopological order of the dinner tasks:\n";
    std::vector<int> order = dinner.topologicalSort();
    for (std::size_t i = 0; i < order.size(); i++) {
        std::cout << "  " << (i + 1) << ". " << dinner.label(order[i]) << '\n';
    }
    return 0;
}
