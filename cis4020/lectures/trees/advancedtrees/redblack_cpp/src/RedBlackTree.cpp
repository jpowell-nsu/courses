// ============================================================================
//  RedBlackTree.cpp  --  a red-black tree of integer values
// ============================================================================
#include "RedBlackTree.h"

#include <climits>
#include <iostream>
#include <stdexcept>
#include <string>
#include <vector>

RedBlackTree::RedBlackTree() {
    NIL = new Node{0, BLACK, nullptr, nullptr, nullptr};
    NIL->left = NIL->right = NIL->parent = NIL;
    root = NIL;
}

RedBlackTree::~RedBlackTree() {
    destroy(root);
    delete NIL;
}

RedBlackTree::Node* RedBlackTree::newNode(int key, Color color) {
    return new Node{key, color, NIL, NIL, NIL};
}

// ------------------------------------------------------------------
// Rotations
// ------------------------------------------------------------------

// x's right child y moves up into x's place, and x becomes y's left child.
void RedBlackTree::rotateLeft(Node* x) {
    Node* y = x->right;
    x->right = y->left;                 // y's left subtree moves over to x
    if (y->left != NIL) {
        y->left->parent = x;
    }
    y->parent = x->parent;              // y takes x's place under x's parent
    if (x->parent == NIL) {
        root = y;
    } else if (x == x->parent->left) {
        x->parent->left = y;
    } else {
        x->parent->right = y;
    }
    y->left = x;                        // x goes down to the left of y
    x->parent = y;
}

// The mirror image of rotateLeft.
void RedBlackTree::rotateRight(Node* x) {
    Node* y = x->left;
    x->left = y->right;
    if (y->right != NIL) {
        y->right->parent = x;
    }
    y->parent = x->parent;
    if (x->parent == NIL) {
        root = y;
    } else if (x == x->parent->right) {
        x->parent->right = y;
    } else {
        x->parent->left = y;
    }
    y->right = x;
    x->parent = y;
}

// ------------------------------------------------------------------
// Insertion
// ------------------------------------------------------------------

void RedBlackTree::insert(int key) {
    if (contains(key)) {
        return;
    }
    Node* z = newNode(key, RED);        // every new node starts red
    Node* parent = NIL;
    Node* current = root;
    while (current != NIL) {            // ordinary BST insertion
        parent = current;
        current = (key < current->key) ? current->left : current->right;
    }
    z->parent = parent;
    if (parent == NIL) {
        root = z;
    } else if (key < parent->key) {
        parent->left = z;
    } else {
        parent->right = z;
    }
    insertFixup(z);
}

// Repairs a red node z whose parent may also be red.
void RedBlackTree::insertFixup(Node* z) {
    while (z->parent->color == RED) {
        Node* grand = z->parent->parent;
        if (z->parent == grand->left) {
            Node* uncle = grand->right;
            if (uncle->color == RED) {
                // Case 1: red uncle. Recolor and move the problem up.
                z->parent->color = BLACK;
                uncle->color = BLACK;
                grand->color = RED;
                z = grand;
            } else {
                if (z == z->parent->right) {
                    // Case 2: black uncle, z is an inner child.
                    // Rotate at the parent to turn this into Case 3.
                    z = z->parent;
                    rotateLeft(z);
                }
                // Case 3: black uncle, z is an outer child.
                z->parent->color = BLACK;
                z->parent->parent->color = RED;
                rotateRight(z->parent->parent);
            }
        } else {                        // mirror image: parent is a right child
            Node* uncle = grand->left;
            if (uncle->color == RED) {
                z->parent->color = BLACK;
                uncle->color = BLACK;
                grand->color = RED;
                z = grand;
            } else {
                if (z == z->parent->left) {
                    z = z->parent;
                    rotateRight(z);
                }
                z->parent->color = BLACK;
                z->parent->parent->color = RED;
                rotateLeft(z->parent->parent);
            }
        }
    }
    root->color = BLACK;                // the root is always black
}

// ------------------------------------------------------------------
// Deletion
// ------------------------------------------------------------------

// Puts subtree v where subtree u used to be.
void RedBlackTree::transplant(Node* u, Node* v) {
    if (u->parent == NIL) {
        root = v;
    } else if (u == u->parent->left) {
        u->parent->left = v;
    } else {
        u->parent->right = v;
    }
    v->parent = u->parent;              // allowed even when v is NIL
}

RedBlackTree::Node* RedBlackTree::minimum(Node* node) const {
    while (node->left != NIL) {
        node = node->left;
    }
    return node;
}

void RedBlackTree::remove(int key) {
    Node* z = root;
    while (z != NIL && z->key != key) {
        z = (key < z->key) ? z->left : z->right;
    }
    if (z == NIL) {
        return;
    }

    Node* y = z;                        // the node actually removed from its spot
    Color removedColor = y->color;
    Node* x;                            // the node that moves into y's spot

    if (z->left == NIL) {
        x = z->right;
        transplant(z, z->right);
    } else if (z->right == NIL) {
        x = z->left;
        transplant(z, z->left);
    } else {
        y = minimum(z->right);          // inorder successor
        removedColor = y->color;
        x = y->right;
        if (y->parent == z) {
            x->parent = y;
        } else {
            transplant(y, y->right);
            y->right = z->right;
            y->right->parent = y;
        }
        transplant(z, y);
        y->left = z->left;
        y->left->parent = y;
        y->color = z->color;            // the successor takes z's place AND color
    }
    delete z;

    if (removedColor == BLACK) {        // a black node left its path short
        removeFixup(x);
    }
}

// x carries an "extra black." Push it up or absorb it.
void RedBlackTree::removeFixup(Node* x) {
    while (x != root && x->color == BLACK) {
        if (x == x->parent->left) {
            Node* sib = x->parent->right;
            if (sib->color == RED) {
                // Case 1: red sibling. Rotate so the sibling is black.
                sib->color = BLACK;
                x->parent->color = RED;
                rotateLeft(x->parent);
                sib = x->parent->right;
            }
            if (sib->left->color == BLACK && sib->right->color == BLACK) {
                // Case 2: black sibling, both of its children black.
                sib->color = RED;
                x = x->parent;
            } else {
                if (sib->right->color == BLACK) {
                    // Case 3: black sibling, near child red, far child black.
                    sib->left->color = BLACK;
                    sib->color = RED;
                    rotateRight(sib);
                    sib = x->parent->right;
                }
                // Case 4: black sibling, far child red.
                sib->color = x->parent->color;
                x->parent->color = BLACK;
                sib->right->color = BLACK;
                rotateLeft(x->parent);
                x = root;
            }
        } else {                        // mirror image: x is a right child
            Node* sib = x->parent->left;
            if (sib->color == RED) {
                sib->color = BLACK;
                x->parent->color = RED;
                rotateRight(x->parent);
                sib = x->parent->left;
            }
            if (sib->right->color == BLACK && sib->left->color == BLACK) {
                sib->color = RED;
                x = x->parent;
            } else {
                if (sib->left->color == BLACK) {
                    sib->right->color = BLACK;
                    sib->color = RED;
                    rotateLeft(sib);
                    sib = x->parent->left;
                }
                sib->color = x->parent->color;
                x->parent->color = BLACK;
                sib->left->color = BLACK;
                rotateRight(x->parent);
                x = root;
            }
        }
    }
    x->color = BLACK;
}

// ------------------------------------------------------------------
// Searching, printing, and checking
// ------------------------------------------------------------------

bool RedBlackTree::contains(int key) const {
    const Node* node = root;
    while (node != NIL) {
        if (key == node->key) {
            return true;
        }
        node = (key < node->key) ? node->left : node->right;
    }
    return false;
}

void RedBlackTree::printTree() const {
    if (root == NIL) {
        std::cout << "(empty tree)" << std::endl;
        return;
    }
    std::vector<const Node*> level{root};
    int depth = 1;
    while (!level.empty()) {
        std::cout << "Level " << depth << ":";
        std::vector<const Node*> next;
        for (const Node* n : level) {
            std::cout << " " << n->key << (n->color == RED ? "R" : "B");
            if (n->left != NIL) {
                next.push_back(n->left);
            }
            if (n->right != NIL) {
                next.push_back(n->right);
            }
        }
        std::cout << std::endl;
        level = next;
        depth++;
    }
}

int RedBlackTree::check() const {
    if (root->color != BLACK) {
        throw std::logic_error("root is red");
    }
    return check(root, LONG_MIN, LONG_MAX);
}

int RedBlackTree::check(const Node* n, long lo, long hi) const {
    if (n == NIL) {
        return 1;
    }
    if (n->key <= lo || n->key >= hi) {
        throw std::logic_error("BST order broken at " + std::to_string(n->key));
    }
    if (n->color == RED && (n->left->color == RED || n->right->color == RED)) {
        throw std::logic_error("red node " + std::to_string(n->key) + " has a red child");
    }
    int a = check(n->left, lo, n->key);
    int b = check(n->right, n->key, hi);
    if (a != b) {
        throw std::logic_error("black heights differ at " + std::to_string(n->key));
    }
    return a + (n->color == BLACK ? 1 : 0);
}

void RedBlackTree::destroy(Node* n) {
    if (n == NIL) {
        return;
    }
    destroy(n->left);
    destroy(n->right);
    delete n;
}
