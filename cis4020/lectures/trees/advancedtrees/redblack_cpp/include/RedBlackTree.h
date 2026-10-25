// ============================================================================
//  RedBlackTree.h  --  a red-black tree of integer values
//
//  Uses bottom-up insertion and deletion. Every missing child points to one
//  shared black sentinel node, NIL, instead of nullptr, so the fix-up code can
//  ask for the color of a missing child without special cases.
// ============================================================================
#ifndef RED_BLACK_TREE_H
#define RED_BLACK_TREE_H

class RedBlackTree {
public:
    RedBlackTree();
    ~RedBlackTree();
    RedBlackTree(const RedBlackTree&) = delete;
    RedBlackTree& operator=(const RedBlackTree&) = delete;

    void insert(int key);       // duplicate keys are ignored
    void remove(int key);       // "delete" is a C++ keyword, so this is remove
    bool contains(int key) const;
    void printTree() const;     // one level per line, e.g. "Level 2: 20R 60B"
    int check() const;          // black height; throws if a rule is broken

private:
    enum Color { RED, BLACK };

    struct Node {
        int key;
        Color color;
        Node* left;
        Node* right;
        Node* parent;
    };

    Node* NIL;
    Node* root;

    Node* newNode(int key, Color color);
    void rotateLeft(Node* x);
    void rotateRight(Node* x);
    void insertFixup(Node* z);
    void transplant(Node* u, Node* v);
    Node* minimum(Node* node) const;
    void removeFixup(Node* x);
    int check(const Node* n, long lo, long hi) const;
    void destroy(Node* n);
};

#endif
