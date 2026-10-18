// ============================================================================
//  TwoThreeNode.h  --  one node of a 2-3 tree (Lab W11)
//
//  A node holds one or two values, kept in ascending order. A leaf has no
//  children. An internal node with one value has exactly two children, and
//  an internal node with two values has exactly three children.
//
//  This file is complete. You do not need to change it.
// ============================================================================
#ifndef TWO_THREE_NODE_H
#define TWO_THREE_NODE_H

#include <stdexcept>

class TwoThreeNode {
public:
    // Creates a leaf holding a single value.
    explicit TwoThreeNode(int value) : numValues(1) {
        values[0] = value;
        values[1] = 0;
        children[0] = children[1] = children[2] = nullptr;
    }

    // Returns how many values this node holds, 1 or 2.
    int getNumValues() const { return numValues; }

    // Returns the value at position index (0 for the smaller, 1 for the larger).
    int getValue(int index) const { return values[index]; }

    // Returns the child at position index (0 is the leftmost), or nullptr.
    TwoThreeNode* getChild(int index) const { return children[index]; }

    // A node is a leaf when it has no children.
    bool isLeaf() const { return children[0] == nullptr; }

    // A node is full when it already holds two values.
    bool isFull() const { return numValues == 2; }

    // Adds a value to a node that is NOT full, keeping the values in order.
    // rightOfValue becomes the child immediately to the right of the new
    // value, and any children after it shift one place to the right. Pass
    // nullptr when this node is a leaf.
    void addValue(int value, TwoThreeNode* rightOfValue) {
        if (isFull()) {
            throw std::logic_error("addValue called on a full node");
        }
        if (value < values[0]) {
            values[1] = values[0];
            values[0] = value;
            children[2] = children[1];
            children[1] = rightOfValue;
        } else {
            values[1] = value;
            children[2] = rightOfValue;
        }
        numValues = 2;
    }

    // ------------------------------------------------------------------
    // Low-level setters. Use these when you rebuild a node during a split.
    // ------------------------------------------------------------------

    // Sets the value at position index.
    void setValue(int index, int value) { values[index] = value; }

    // Sets how many values this node holds, 1 or 2.
    void setNumValues(int count) { numValues = count; }

    // Sets the child at position index. Pass nullptr to remove a child.
    void setChild(int index, TwoThreeNode* child) { children[index] = child; }

private:
    int values[2];
    TwoThreeNode* children[3];
    int numValues;
};

#endif
