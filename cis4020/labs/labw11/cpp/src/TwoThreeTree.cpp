/**
 * A 2-3 tree of integer values. Search, the inorder traversal, and
 * printTree are provided. The three insertion methods, the public insert,
 * the recursive insert, and split, are completed for this lab.
 *
 * Assignment: Lab W11: 2-3 Tree Insertion
 * @version 1.0
 * @author
 *
 */
#include "TwoThreeTree.h"

#include <iostream>
#include <vector>

// ==================================================================
// Insertion: YOUR CODE GOES IN THE THREE METHODS BELOW
// ==================================================================

void TwoThreeTree::insert(int value) {
    // TODO (Checkpoints 1 and 2): write the public insert method.
}

SplitResult TwoThreeTree::insert(TwoThreeNode* node, int value) {
    // TODO (Checkpoints 1, 3, and 4): write the recursive insert method.
    return {0, nullptr};
}

SplitResult TwoThreeTree::split(TwoThreeNode* node, int value, TwoThreeNode* rightOfValue) {
    // TODO (Checkpoints 2, 3, and 4): write the split method.
    return {0, nullptr};
}

// ==================================================================
// Everything below this line is complete. You do not need to change it.
// ==================================================================

bool TwoThreeTree::contains(int value) const {
    const TwoThreeNode* node = root;
    while (node != nullptr) {
        if (value == node->getValue(0)
                || (node->getNumValues() == 2 && value == node->getValue(1))) {
            return true;
        }
        if (node->isLeaf()) {
            return false;
        }
        if (value < node->getValue(0)) {
            node = node->getChild(0);
        } else if (node->getNumValues() == 1 || value < node->getValue(1)) {
            node = node->getChild(1);
        } else {
            node = node->getChild(2);
        }
    }
    return false;
}

std::string TwoThreeTree::inorder() const {
    std::string out;
    inorder(root, out);
    if (!out.empty()) {
        out.pop_back();  // remove the trailing space
    }
    return out;
}

void TwoThreeTree::inorder(const TwoThreeNode* node, std::string& out) const {
    if (node == nullptr) {
        return;
    }
    // left, small, middle, large, right
    inorder(node->getChild(0), out);
    out += std::to_string(node->getValue(0)) + " ";
    inorder(node->getChild(1), out);
    if (node->getNumValues() == 2) {
        out += std::to_string(node->getValue(1)) + " ";
        inorder(node->getChild(2), out);
    }
}

void TwoThreeTree::printTree() const {
    if (root == nullptr) {
        std::cout << "(empty tree)" << std::endl;
        return;
    }
    std::vector<const TwoThreeNode*> level{root};
    int depth = 1;
    while (!level.empty()) {
        std::cout << "Level " << depth << ":";
        std::vector<const TwoThreeNode*> next;
        for (const TwoThreeNode* node : level) {
            std::cout << " [" << node->getValue(0);
            if (node->getNumValues() == 2) {
                std::cout << " " << node->getValue(1);
            }
            std::cout << "]";
            for (int i = 0; i <= node->getNumValues(); i++) {
                if (node->getChild(i) != nullptr) {
                    next.push_back(node->getChild(i));
                }
            }
        }
        std::cout << std::endl;
        level = next;
        depth++;
    }
}

void TwoThreeTree::destroy(TwoThreeNode* node) {
    if (node == nullptr) {
        return;
    }
    for (int i = 0; i < 3; i++) {
        destroy(node->getChild(i));
    }
    delete node;
}
