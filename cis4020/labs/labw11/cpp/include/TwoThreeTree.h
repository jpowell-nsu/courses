// ============================================================================
//  TwoThreeTree.h  --  a 2-3 tree of integer values (Lab W11)
//
//  Searching, the inorder traversal, and printTree are complete. Your job is
//  insertion: the three methods marked TODO in TwoThreeTree.cpp.
// ============================================================================
#ifndef TWO_THREE_TREE_H
#define TWO_THREE_TREE_H

#include <string>
#include "TwoThreeNode.h"

// What a split hands back to the level above it: the value that moves up,
// and the new node that belongs immediately to the right of that value.
// When nothing split, newRightNode is nullptr.
struct SplitResult {
    int promotedValue;
    TwoThreeNode* newRightNode;
};

class TwoThreeTree {
public:
    TwoThreeTree() : root(nullptr) {}
    ~TwoThreeTree() { destroy(root); }

    // A tree owns its nodes, so copying is turned off.
    TwoThreeTree(const TwoThreeTree&) = delete;
    TwoThreeTree& operator=(const TwoThreeTree&) = delete;

    // Inserts value into the tree. Duplicate values are ignored.   (TODO)
    void insert(int value);

    // Returns true when value is somewhere in the tree.
    bool contains(int value) const;

    // Returns the values in inorder (sorted) order, separated by spaces.
    std::string inorder() const;

    // Prints the tree one level per line, for example:
    //   Level 1: [16 31]
    //   Level 2: [14] [21] [36 42]
    void printTree() const;

private:
    TwoThreeNode* root;

    // Inserts value into the subtree rooted at node.               (TODO)
    // Returns {0, nullptr} when the subtree absorbed the value without
    // splitting node. Otherwise returns the value that moves up and the new
    // node created when node itself had to split.
    SplitResult insert(TwoThreeNode* node, int value);

    // Splits a FULL node that must also take in one more value.    (TODO)
    // rightOfValue is the node that belongs immediately to the right of the
    // new value (nullptr when node is a leaf). When this returns, node holds
    // the smallest value and the leftmost children, a new node holds the
    // largest value and the rightmost children, and the middle value is
    // handed back along with that new node.
    SplitResult split(TwoThreeNode* node, int value, TwoThreeNode* rightOfValue);

    void inorder(const TwoThreeNode* node, std::string& out) const;
    void destroy(TwoThreeNode* node);
};

#endif
