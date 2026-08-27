/**
 * Simulates a chain of cache levels and main memory, using a single
 * recursive function to decide how each memory access in a trace is
 * served.
 *
 * Assignment: Lab W02: A Recursive Multi-Level Cache Simulator
 * Version: 1.0
 * Author: Your Name
 *
 * The input parsing, output formatting, and summary statistics are
 * already written below. Your job is to implement accessLevel(blockID,
 * level), the single recursive function that decides how each memory
 * access is served. For a typical solution, accessLevel is the only
 * function you should need to write or change. Do not rewrite it as a
 * loop. The point of the assignment is the recursive structure itself.
 */

#include <fstream>
#include <iostream>
#include <vector>
#include <string>

using namespace std;

int numLevels;

// capacity_[i] and hitTimeArr[i] describe cache level i (0 is the
// fastest, fed by the input, one entry per cache level).
vector<int> capacity_;
vector<int> hitTimeArr;
int mainMemoryTime;

// cacheContents[i] is the list of block IDs currently stored at level
// i. All levels start empty, so this begins as N empty vectors.
vector<vector<int>> cacheContents;

// Running totals used for the summary printed after the trace.
// hitsPerLevel[i] counts how many accesses were served by level i.
vector<int> hitsPerLevel;
int mainMemoryHits = 0;

// Set by accessLevel() on every call so main() knows, after the call
// returns, which level actually served the request. This is simpler
// than returning two pieces of information at once.
string lastServedBy;

// Simple linear search helper, since these caches are small and this
// lab is not about the search itself.
bool contains(const vector<int>& v, int x) {
    for (int val : v) {
        if (val == x) return true;
    }
    return false;
}

/*
 * Returns the cost, in cycles, of servicing blockID starting the search
 * at the given level (0 is the fastest cache level).
 *
 * Rules to implement:
 *   1. Base case: if level == numLevels, we have fallen off the last
 *      cache level. The access is served by main memory, which always
 *      succeeds. Update mainMemoryHits and lastServedBy, then return
 *      mainMemoryTime.
 *   2. If cacheContents[level] already contains blockID, this level
 *      serves the request directly. Update hitsPerLevel and
 *      lastServedBy, then return hitTimeArr[level].
 *   3. Otherwise, this level misses. Recurse to level + 1 to find out
 *      how the request gets served further down, then add this level's
 *      hit time to that cost. After the recursive call returns, if this
 *      level still has room (compare its current size to
 *      capacity_[level]), insert blockID here. This is the "fill on the
 *      way back up" step, and it only makes sense after the recursive
 *      call has returned. Return the combined cost.
 *
 * Do not implement eviction. If a level is full, simply skip the
 * insertion and move on.
 */
int accessLevel(int blockID, int level) {
    // TODO: implement the three cases described above.
    return -1;
}

int main() {
    // Reads from a file named input.txt in the working directory,
    // rather than from the console, so you can run this from an IDE
    // without setting up console input.
    ifstream fin("input.txt");
    if (!fin.is_open()) {
        cout << "Could not find input.txt. Make sure it is in your project's working directory." << endl;
        return 1;
    }

    // Line 1: N, the number of cache levels.
    fin >> numLevels;
    capacity_.resize(numLevels);
    hitTimeArr.resize(numLevels);
    cacheContents.resize(numLevels);
    hitsPerLevel.assign(numLevels, 0);

    // Next N lines: one "capacity hitTime" pair per level, in order
    // from level 0 (fastest) to level N - 1. cacheContents[i] starts
    // empty for every level because of the resize() call above.
    for (int i = 0; i < numLevels; i++) {
        fin >> capacity_[i] >> hitTimeArr[i];
    }

    // Next line: main memory access time.
    fin >> mainMemoryTime;

    // Next line: M, the number of accesses, followed by the M block
    // IDs themselves.
    int m;
    fin >> m;
    vector<int> trace(m);
    for (int i = 0; i < m; i++) {
        fin >> trace[i];
    }

    // Replay the trace one block at a time. Each call to accessLevel()
    // starts the search at level 0 and walks down the chain of levels
    // itself, recursively, until something answers it. The caches
    // persist across iterations of this loop, which is what lets an
    // earlier miss turn into a later hit.
    long long totalCycles = 0;
    for (int i = 0; i < m; i++) {
        int cost = accessLevel(trace[i], 0);
        totalCycles += cost;
        cout << "Access " << (i + 1) << ": block " << trace[i]
             << " -> served by " << lastServedBy << ", cost " << cost << " cycles" << endl;
    }

    // Print the required summary: hits per level, main memory hits,
    // total cycles spent, and the average cost per access.
    cout << endl;
    cout << "Summary:" << endl;
    for (int i = 0; i < numLevels; i++) {
        cout << "  L" << (i + 1) << " hits: " << hitsPerLevel[i] << endl;
    }
    cout << "  Main Memory hits: " << mainMemoryHits << endl;
    cout << "  Total cycles: " << totalCycles << endl;
    printf("  Average cycles per access: %.2f\n", (double)totalCycles / m);

    return 0;
}
