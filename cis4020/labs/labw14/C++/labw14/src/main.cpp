// main.cpp
// Lab W14 driver. Starter code. See the lab handout for each part.
#include <cstdio>
#include <fstream>
#include <iostream>
#include <string>
#include <vector>

#include "HashTable.h"

// Reads every integer in a file into a vector.
static std::vector<int> readKeys(const std::string& filename) {
    std::vector<int> keys;
    std::ifstream in(filename);
    int x;
    while (in >> x) {
        keys.push_back(x);
    }
    return keys;
}

int main() {
    // Part 1: build the table, starting small so it has to grow.
    std::printf("=== Part 1: Building the table ===\n");
    std::vector<int> keys = readKeys("files/integers.txt");
    HashTable table(11);

    // Part 2: duplicates are rejected.
    std::printf("\n=== Part 2: Duplicates ===\n");

    // Part 3: measured probes compared with the formulas.
    std::printf("\n=== Part 3: Measured and formula ===\n");

    // Part 4: a sequence of operations on a new table.
    std::printf("\n=== Part 4: Operations ===\n");

    return 0;
}
