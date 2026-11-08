// dijkstra.cpp
// CIS 4020, Data Structures: Graphs
//
// Dijkstra's shortest path algorithm on a weighted directed graph stored as
// an adjacency matrix. The function follows the textbook pseudocode closely:
//
//     vertexSet = { start }
//     weight[v] = matrix[start][v] for every v
//     repeat n - 1 times:
//         v = the vertex not in vertexSet with the smallest weight
//         add v to vertexSet
//         for each u not in vertexSet:
//             if weight[u] > weight[v] + matrix[v][u]
//                 weight[u] = weight[v] + matrix[v][u]
//
// Two additions to the pseudocode:
//   1. A predecessor array, so we can print the actual path, not just its cost.
//   2. A check for vertices that cannot be reached. Their weight stays at
//      infinity, and adding infinity to anything would overflow an int.
//
// The program prints the same trace table we build by hand in class.
// Ties are broken by choosing the lowest index (alphabetical order).
//
// Compile and run:
//     g++ -std=c++17 -Wall -o dijkstra dijkstra.cpp
//     ./dijkstra

#include <iomanip>
#include <iostream>
#include <limits>
#include <sstream>
#include <string>
#include <vector>

constexpr int INF = std::numeric_limits<int>::max();
using Matrix = std::vector<std::vector<int>>;

namespace {

std::string w(int x) { return x == INF ? "inf" : std::to_string(x); }

void printHeader(const std::vector<std::string>& labels) {
    std::cout << "  " << std::left << std::setw(5) << "Step" << std::setw(4) << "v"
              << std::setw(16) << "Vertex set";
    for (const std::string& s : labels) std::cout << std::setw(6) << ("w[" + s + "]");
    std::cout << std::right << '\n';
}

void printRow(int step, const std::string& v, const std::string& set, const std::vector<int>& weight) {
    std::cout << "  " << std::left << std::setw(5) << step << std::setw(4) << v << std::setw(16) << set;
    for (int x : weight) std::cout << std::setw(6) << w(x);
    std::cout << std::right << '\n';
}

}  // namespace

// Computes shortest path weights from start. Fills weight and pred
// (pred[v] is the vertex just before v on the best path, or -1).
void shortestPath(const Matrix& matrix, int start, std::vector<int>& weight,
                  std::vector<int>& pred, const std::vector<std::string>& labels) {
    int n = static_cast<int>(matrix.size());
    std::vector<bool> inSet(n, false);
    std::string setText = labels[start];
    weight.assign(n, INF);
    pred.assign(n, -1);

    // Step 1: the vertex set holds only start, and every weight is a direct edge.
    inSet[start] = true;
    for (int v = 0; v < n; v++) {
        weight[v] = matrix[start][v];
        if (matrix[start][v] != INF) pred[v] = start;
    }
    weight[start] = 0;
    pred[start] = -1;

    printHeader(labels);
    printRow(1, "-", setText, weight);

    // Steps 2 through n: grow the vertex set one vertex at a time.
    for (int step = 2; step <= n; step++) {
        // Find the smallest weight[v] such that v is not in the vertex set.
        int v = -1;
        for (int i = 0; i < n; i++) {
            if (!inSet[i] && (v == -1 || weight[i] < weight[v])) v = i;
        }
        if (v == -1 || weight[v] == INF) {
            // Everything left is unreachable from start, so we are done.
            std::cout << "  (no remaining vertex is reachable, so the algorithm stops)\n";
            break;
        }

        inSet[v] = true;
        setText += "," + labels[v];

        // Can going through v improve the path to any vertex u outside the set?
        for (int u = 0; u < n; u++) {
            if (!inSet[u] && matrix[v][u] != INF && weight[u] > weight[v] + matrix[v][u]) {
                weight[u] = weight[v] + matrix[v][u];
                pred[u] = v;
            }
        }
        printRow(step, labels[v], setText, weight);
    }
}

// Builds the path to v by following pred backward.
std::string path(const std::vector<int>& pred, int v, const std::vector<std::string>& labels) {
    std::string result = labels[v];
    for (int p = pred[v]; p != -1; p = pred[p]) result = labels[p] + " -> " + result;
    return result;
}

void run(const std::string& title, const Matrix& matrix, int start,
         const std::vector<std::string>& labels) {
    std::vector<int> weight, pred;
    std::cout << title << '\n';
    shortestPath(matrix, start, weight, pred, labels);
    std::cout << "  Shortest paths from " << labels[start] << ":\n";
    for (int v = 0; v < static_cast<int>(matrix.size()); v++) {
        if (v == start) continue;
        if (weight[v] == INF) {
            std::cout << "    to " << labels[v] << ": not reachable\n";
        } else {
            std::cout << "    to " << labels[v] << ": cost " << weight[v]
                      << "   path " << path(pred, v, labels) << '\n';
        }
    }
    std::cout << '\n';
}

struct Edge { std::string from, to; int weight; };

// Builds an n by n matrix filled with INF, then adds the listed directed edges.
Matrix build(const std::vector<std::string>& labels, const std::vector<Edge>& edges) {
    auto index = [&labels](const std::string& s) {
        for (std::size_t i = 0; i < labels.size(); i++) if (labels[i] == s) return static_cast<int>(i);
        return -1;
    };
    Matrix m(labels.size(), std::vector<int>(labels.size(), INF));
    for (const Edge& e : edges) m[index(e.from)][index(e.to)] = e.weight;
    return m;
}

int main() {
    // Example 1: five vertices, 0 through 4 (the class example).
    std::vector<std::string> five = {"0", "1", "2", "3", "4"};
    Matrix m1 = build(five, {
        {"0", "1", 8}, {"0", "3", 9}, {"0", "4", 4},
        {"1", "2", 1},
        {"2", "1", 2}, {"2", "3", 3},
        {"3", "2", 2}, {"3", "4", 7},
        {"4", "2", 1}
    });
    run("Example 1: start at vertex 0", m1, 0, five);

    // Example 2: seven vertices, A through G. Not every vertex is reachable.
    std::vector<std::string> seven = {"A", "B", "C", "D", "E", "F", "G"};
    Matrix m2 = build(seven, {
        {"A", "B", 14}, {"A", "D", 22}, {"A", "E", 4},
        {"B", "G", 3},
        {"C", "B", 16}, {"C", "D", 12},
        {"E", "D", 12}, {"E", "F", 10},
        {"G", "F", 1}
    });
    run("Example 2: start at vertex A", m2, 0, seven);
    run("Example 2: start at vertex C", m2, 2, seven);
    return 0;
}
