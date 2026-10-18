// ============================================================================
//  main.cpp  --  Lab W11 driver
//
//  Runs the checkpoint tests and the example sequences. This file is complete.
//  You may add your own tests at the bottom of main.
// ============================================================================
#include <exception>
#include <iostream>
#include <string>
#include <vector>

#include "TwoThreeTree.h"

static std::string join(const std::vector<int>& values) {
    std::string out;
    for (size_t i = 0; i < values.size(); i++) {
        if (i > 0) {
            out += " ";
        }
        out += std::to_string(values[i]);
    }
    return out;
}

// Inserts every value, then prints the final tree and its inorder traversal.
static void runCheckpoint(const std::string& name, const std::vector<int>& values) {
    std::cout << std::endl << name << ": insert " << join(values) << std::endl;
    try {
        TwoThreeTree tree;
        for (int v : values) {
            tree.insert(v);
        }
        tree.printTree();
        std::cout << "Inorder: " << tree.inorder() << std::endl;
    } catch (const std::exception& e) {
        std::cout << name << " stopped with an error: " << e.what() << std::endl;
    }
}

// Inserts the values one at a time, printing the tree after every insertion.
static void runSequence(const std::string& name, const std::vector<int>& values) {
    std::cout << std::endl << name << ": " << join(values) << std::endl;
    try {
        TwoThreeTree tree;
        for (int v : values) {
            tree.insert(v);
            std::cout << std::endl << "After inserting " << v << ":" << std::endl;
            tree.printTree();
        }
        std::cout << "Inorder: " << tree.inorder() << std::endl;
    } catch (const std::exception& e) {
        std::cout << name << " stopped with an error: " << e.what() << std::endl;
    }
}

int main() {
    std::cout << "===== Checkpoint tests =====" << std::endl;
    runCheckpoint("Checkpoint 1", {50, 30});
    runCheckpoint("Checkpoint 2", {50, 30, 70});
    runCheckpoint("Checkpoint 3", {50, 30, 70, 10, 20});
    runCheckpoint("Checkpoint 4",
                  {42, 36, 16, 14, 32, 31, 40, 8, 21, 34, 20, 22, 50, 15});

    std::cout << std::endl << "===== Example sequences =====" << std::endl;
    runSequence("Sequence A", {11, 39, 61, 96, 74, 9, 24, 67, 69, 64, 52, 65, 82, 60});
    runSequence("Sequence B", {46, 80, 88, 13, 35, 91, 23, 6, 57, 56, 90, 53, 99, 81});
    runSequence("Sequence C", {98, 7, 75, 33, 45, 19, 38, 4, 62, 66, 1, 76, 86, 59});

    // ---- Add your own tests below this line, if you like ----

    return 0;
}
