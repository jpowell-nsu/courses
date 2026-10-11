// BST.h -- binary search tree (C++ counterpart of the Java BST)
// ItemType must provide getKey(), and the key type must support < and ==.
#ifndef BST_H
#define BST_H

#include <stdexcept>
#include <string>

class BSTException : public std::runtime_error {
public:
    explicit BSTException(const std::string& msg) : std::runtime_error(msg) {}
};

template <typename ItemType, typename KeyType>
class BST {
private:
    struct Node {
        ItemType item;
        Node* left;
        Node* right;
        explicit Node(const ItemType& it) : item(it), left(nullptr), right(nullptr) {}
    };

    Node* root;

    Node* insert(Node* tn, const ItemType& item) {
        if (tn == nullptr) {                    // position found
            tn = new Node(item);                // create the new node
        } else if (item.getKey() < tn->item.getKey()) {
            tn->left = insert(tn->left, item);  // search left
        } else {
            // search right
            // Duplicates: a key EQUAL to tn's key also lands here, so
            // equal keys are stored in the right subtree (left < parent <= right).
            tn->right = insert(tn->right, item);
        }
        return tn;
    }

    Node* deleteNode(Node* tn) {
        if (tn->left == nullptr && tn->right == nullptr) {
            // case 1: node is a leaf
            delete tn;
            return nullptr;
        } else if (tn->left == nullptr) {
            // case 2a: one child, a right child only
            Node* child = tn->right;
            delete tn;
            return child;
        } else if (tn->right == nullptr) {
            // case 2b: one child, a left child only
            Node* child = tn->left;
            delete tn;
            return child;
        } else {
            // case 3: two children
            //   note: go right once before it goes left
            tn->item = inorderSuccessor(tn->right);
            tn->right = deleteInorderSuccessor(tn->right);
            return tn;
        }
    }

    Node* remove(Node* tn, const KeyType& searchKey) {
        if (tn == nullptr) {
            throw BSTException("BST Exception: Item Not Found");
        }
        if (searchKey == tn->item.getKey()) {
            tn = deleteNode(tn);
        } else if (searchKey < tn->item.getKey()) {
            tn->left = remove(tn->left, searchKey);   // search left
        } else {
            tn->right = remove(tn->right, searchKey); // search right
        }
        return tn;
    }

    const ItemType& inorderSuccessor(Node* tn) const {
        if (tn->left == nullptr) {
            return tn->item;                     // the replacement item
        }
        return inorderSuccessor(tn->left);
    }

    Node* deleteInorderSuccessor(Node* tn) {
        if (tn->left == nullptr) {
            Node* child = tn->right;
            delete tn;                           // C++ must free the node
            return child;
        }
        tn->left = deleteInorderSuccessor(tn->left);
        return tn;
    }

    Node* retrieve(Node* tn, const KeyType& searchKey) const {
        if (tn == nullptr) {
            return nullptr;
        } else if (searchKey == tn->item.getKey()) {
            return tn;
        } else if (searchKey < tn->item.getKey()) {
            return retrieve(tn->left, searchKey);
        } else {
            return retrieve(tn->right, searchKey);
        }
    }

    void destroy(Node* tn) {                     // free both subtrees, then the node
        if (tn != nullptr) {
            destroy(tn->left);
            destroy(tn->right);
            delete tn;
        }
    }

public:
    BST() : root(nullptr) {}
    ~BST() { destroy(root); }

    // Copying would make two trees share nodes; keep it simple and forbid it.
    BST(const BST&) = delete;
    BST& operator=(const BST&) = delete;

    bool isEmpty() const { return root == nullptr; }

    void removeAll() {
        destroy(root);
        root = nullptr;
    }

    const ItemType& peekRoot() const {
        if (root == nullptr) {
            throw BSTException("BST Exception: Empty Tree");
        }
        return root->item;
    }

    void add(const ItemType& item) { root = insert(root, item); }

    void remove(const KeyType& searchKey) { root = remove(root, searchKey); }

    const ItemType& search(const KeyType& searchKey) const {
        Node* found = retrieve(root, searchKey);
        if (found == nullptr) {
            throw BSTException("BST Exception: Item Not Found");
        }
        return found->item;
    }

    void inorder() const {
        inorder(root);
    }

private:
    void inorder(Node *tn) const {
        if (tn != nullptr) {
            inorder(tn->left);
            std::cout << tn->item << " ";
            inorder(tn->right);
        }
    }
};

#endif
