// ============================================================================
//  main.cpp  --  runs the insertion and deletion examples from the notes,
//  printing the tree after every operation and checking the red-black rules.
// ============================================================================
#include <iostream>

#include "RedBlackTree.h"

int main() {
    RedBlackTree tree;

    std::cout << "===== Insertion example =====" << std::endl;
    for (int v : {60, 55, 85, 25, 45, 15, 20, 30}) {
        tree.insert(v);
        std::cout << std::endl << "After inserting " << v << ":" << std::endl;
        tree.printTree();
        tree.check();
    }

    std::cout << std::endl << "===== Deletion example =====" << std::endl;
    for (int v : {85, 45, 15, 60, 20}) {
        tree.remove(v);
        std::cout << std::endl << "After deleting " << v << ":" << std::endl;
        tree.printTree();
        tree.check();
    }
    return 0;
}
