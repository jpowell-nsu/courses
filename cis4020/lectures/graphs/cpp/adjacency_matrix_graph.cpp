// adjacency_matrix_graph.cpp
// CIS 4020, Data Structures: Graphs
//
// A weighted graph stored as an adjacency matrix (also called a cost
// matrix). matrix_[u][v] holds the weight of the edge from u to v, or
// NO_EDGE when there is no edge. An undirected graph has a symmetric matrix:
// matrix_[u][v] == matrix_[v][u].
//
// This class has the same public operations as the adjacency list version
// so the two can be compared side by side. Only the storage is different.
//
// Compile and run:
//     g++ -std=c++17 -Wall -o adjacency_matrix_graph adjacency_matrix_graph.cpp
//     ./adjacency_matrix_graph

#include <iomanip>
#include <iostream>
#include <limits>
#include <queue>
#include <string>
#include <vector>

class AdjacencyMatrixGraph {
public:
    // Marks "no edge." Infinity in the textbook; any impossible weight works.
    static constexpr int NO_EDGE = std::numeric_limits<int>::max();

    AdjacencyMatrixGraph(const std::vector<std::string>& labels, bool directed)
        : labels_(labels),
          matrix_(labels.size(), std::vector<int>(labels.size(), NO_EDGE)),  // n * n cells
          directed_(directed),
          edgeCount_(0) {}

    int numVertices() const { return static_cast<int>(labels_.size()); }
    int numEdges() const { return edgeCount_; }
    bool isEmpty() const { return labels_.empty(); }

    int indexOf(const std::string& label) const {
        for (int i = 0; i < numVertices(); i++) {
            if (labels_[i] == label) return i;
        }
        return -1;
    }

    // Inserts the edge u-v with the given weight. Replaces the weight if it exists.
    void addEdge(int u, int v, int weight) {
        if (matrix_[u][v] == NO_EDGE) edgeCount_++;
        matrix_[u][v] = weight;
        if (!directed_) matrix_[v][u] = weight;    // keep the matrix symmetric
    }

    // Removes the edge u-v if it exists.
    void removeEdge(int u, int v) {
        if (matrix_[u][v] != NO_EDGE) edgeCount_--;
        matrix_[u][v] = NO_EDGE;
        if (!directed_) matrix_[v][u] = NO_EDGE;
    }

    // Operation 1: is there an edge from u to v?
    // One array lookup, so this is O(1). This is where the matrix shines.
    bool hasEdge(int u, int v) const { return matrix_[u][v] != NO_EDGE; }

    // Returns the weight of edge u-v, or -1 if there is no such edge.
    int getWeight(int u, int v) const { return hasEdge(u, v) ? matrix_[u][v] : -1; }

    // Operation 2: which vertices are adjacent to v?
    // We must scan the whole row, so this is O(n) even if v has one neighbor.
    std::vector<int> neighbors(int v) const {
        std::vector<int> result;
        for (int u = 0; u < numVertices(); u++) {
            if (matrix_[v][u] != NO_EDGE) result.push_back(u);
        }
        return result;
    }

    // Depth-first search from start. Returns the vertices in the order visited.
    std::vector<int> dfs(int start) const {
        std::vector<bool> visited(numVertices(), false);
        std::vector<int> order;
        dfsHelper(start, visited, order);
        return order;
    }

    // Breadth-first search from start. Returns the vertices in the order visited.
    std::vector<int> bfs(int start) const {
        std::vector<bool> visited(numVertices(), false);
        std::vector<int> order;
        std::queue<int> q;

        q.push(start);
        visited[start] = true;
        order.push_back(start);
        while (!q.empty()) {
            int w = q.front();
            q.pop();
            for (int u = 0; u < numVertices(); u++) {
                if (matrix_[w][u] != NO_EDGE && !visited[u]) {
                    visited[u] = true;
                    order.push_back(u);
                    q.push(u);
                }
            }
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

    // Prints the matrix with row and column labels. A dash means no edge.
    void print() const {
        std::cout << "     ";
        for (const std::string& s : labels_) std::cout << std::setw(4) << s;
        std::cout << '\n';
        for (int u = 0; u < numVertices(); u++) {
            std::cout << "  " << std::left << std::setw(3) << labels_[u] << std::right;
            for (int v = 0; v < numVertices(); v++) {
                if (matrix_[u][v] == NO_EDGE) std::cout << std::setw(4) << "-";
                else std::cout << std::setw(4) << matrix_[u][v];
            }
            std::cout << '\n';
        }
    }

private:
    std::vector<std::string> labels_;
    std::vector<std::vector<int>> matrix_;
    bool directed_;
    int edgeCount_;

    void dfsHelper(int v, std::vector<bool>& visited, std::vector<int>& order) const {
        visited[v] = true;
        order.push_back(v);
        for (int u = 0; u < numVertices(); u++) {  // scan row v, in index order
            if (matrix_[v][u] != NO_EDGE && !visited[u]) dfsHelper(u, visited, order);
        }
    }
};

int main() {
    // The same class example graph G used in the adjacency list version.
    AdjacencyMatrixGraph g({"a", "b", "c", "d", "e", "f", "g", "h", "i"}, false);
    struct E { std::string u, v; int w; };
    std::vector<E> edges = {
        {"i", "a", 2}, {"a", "b", 6}, {"a", "f", 4}, {"b", "e", 9},
        {"b", "c", 7}, {"e", "g", 8}, {"c", "d", 4}, {"f", "g", 2},
        {"g", "d", 5}, {"d", "h", 1}
    };
    for (const E& e : edges) g.addEdge(g.indexOf(e.u), g.indexOf(e.v), e.w);

    int n = g.numVertices();
    std::cout << "Graph G as an adjacency matrix\n";
    std::cout << "vertices = " << n << ", edges = " << g.numEdges()
              << ", cells used = " << 2 * g.numEdges() << " of " << n * n << '\n';
    g.print();

    int a = g.indexOf("a"), b = g.indexOf("b"), c = g.indexOf("c"), d = g.indexOf("d");
    std::cout << std::boolalpha << '\n';
    std::cout << "hasEdge(a, b)? " << g.hasEdge(a, b) << "   weight = " << g.getWeight(a, b) << '\n';
    std::cout << "hasEdge(a, c)? " << g.hasEdge(a, c) << '\n';
    std::cout << "neighbors(d) = " << g.names(g.neighbors(d)) << '\n';

    for (std::string s : {"i", "h"}) {
        std::cout << "\nDFS(" << s << ") visit order: " << g.names(g.dfs(g.indexOf(s))) << '\n';
        std::cout << "BFS(" << s << ") visit order: " << g.names(g.bfs(g.indexOf(s))) << '\n';
    }
    return 0;
}
