#ifndef HEAP_REFERENCE_BASED_H
#define HEAP_REFERENCE_BASED_H

#include <vector>
#include "HeapBasics.h"

// pointer-based heap
//
// The nodes are numbered the same way as the array-based heap: the root is 0,
// and the numbers continue level by level, left to right. For node number i:
//     parent      is node (i - 1) / 2
//     left child  is node 2 * i + 1  (an odd number)
//     right child is node 2 * i + 2  (an even number)
// There is no array to index into, so nodeAt follows pointers from the root
// to reach a node by its number. The last node is always node size - 1, and
// a new node always goes in as node size.
template <typename ItemType, typename KeyType>
class HeapReferenceBased : public HeapInterface<ItemType, KeyType> {
private:
    struct Node {
        ItemType item;
        Node* left;
        Node* right;
        Node* parent;  // lets an item trickle up without starting over at the root
        explicit Node(const ItemType& it)
            : item(it), left(nullptr), right(nullptr), parent(nullptr) {}
    };

    Node* root;
    int count;      // number of nodes in heap
    HeapType type;  // MAX or MIN

    // returns true when key1 belongs above key2:
    // larger in a maxheap, smaller in a minheap
    bool higherPriority(const KeyType& key1, const KeyType& key2) const {
        if (type == HeapType::MAX) {
            return key2 < key1;
        } else {
            return key1 < key2;
        }
    }

    // returns node number index by walking down from the root
    // note: assumes 0 <= index < count
    Node* nodeAt(int index) const {
        if (index == 0) {
            return root;
        }
        Node* parent = nodeAt((index - 1) / 2);  // first find the parent
        if (index % 2 == 1) {
            return parent->left;   // odd numbers are left children
        } else {
            return parent->right;  // even numbers are right children
        }
    }

    void trickleUp(Node* current) {
        ItemType bottom = current->item;  // the item that is moving up

        // while not at the root and the new item outranks its parent
        while (current->parent != nullptr &&
               higherPriority(bottom.getKey(), current->parent->item.getKey())) {
            current->item = current->parent->item;  // move the parent item down
            current = current->parent;
        }
        current->item = bottom;
    }

    void trickleDown(Node* current) {
        ItemType top = current->item;  // the item that is moving down

        while (current->left != nullptr) {  // while the node has at least one child
            // find the child with the higher priority
            // (the larger child in a maxheap, the smaller child in a minheap)
            Node* higherChild = current->left;
            if (current->right != nullptr &&
                higherPriority(current->right->item.getKey(), current->left->item.getKey())) {
                higherChild = current->right;  // right child exists and wins
            }
            // stop when the child does not outrank the item moving down
            if (!higherPriority(higherChild->item.getKey(), top.getKey())) {
                break;
            }
            current->item = higherChild->item;  // move the child item up
            current = higherChild;              // go down
        }
        current->item = top;
    }

    void destroy(Node* tn) {  // frees a subtree (postorder)
        if (tn != nullptr) {
            destroy(tn->left);
            destroy(tn->right);
            delete tn;
        }
    }

public:
    explicit HeapReferenceBased(HeapType type = HeapType::MAX)
        : root(nullptr), count(0), type(type) {}

    ~HeapReferenceBased() override {
        destroy(root);
    }

    // copying would make two heaps share the same nodes, so it is turned off
    HeapReferenceBased(const HeapReferenceBased&) = delete;
    HeapReferenceBased& operator=(const HeapReferenceBased&) = delete;

    bool isEmpty() const override {
        return root == nullptr;
    }

    int size() const override {
        return count;
    }

    void removeAll() override {
        destroy(root);
        root = nullptr;
        count = 0;
    }

    void insert(const ItemType& item) override {
        Node* newNode = new Node(item);

        // step 1: place the new node as the last node (node number count)
        if (root == nullptr) {
            root = newNode;
        } else {
            Node* parent = nodeAt((count - 1) / 2);
            newNode->parent = parent;
            if (count % 2 == 1) {
                parent->left = newNode;
            } else {
                parent->right = newNode;
            }
        }
        count++;
        trickleUp(newNode);  // step 2: trickle up
    }

    ItemType remove() override {
        if (root == nullptr) {
            throw HeapException("Heap Exception: Empty Heap");
        }
        ItemType rootItem = root->item;  // step 1: remove the root item

        if (count == 1) {
            delete root;
            root = nullptr;
        } else {
            // step 2: place the last node's item in the root
            Node* last = nodeAt(count - 1);
            root->item = last->item;
            // then unlink the last node from its parent and free it
            if (last->parent->right == last) {
                last->parent->right = nullptr;
            } else {
                last->parent->left = nullptr;
            }
            delete last;
            trickleDown(root);  // step 3: trickle down
        }
        count--;
        return rootItem;
    }

    ItemType peek() const override {
        if (root == nullptr) {
            throw HeapException("Heap Exception: Empty Heap");
        }
        return root->item;
    }

    void change(int index, const KeyType& newKey) override {
        if (index < 0 || index >= count) {
            throw HeapException("Heap Exception: Invalid Index");
        }
        Node* target = nodeAt(index);
        KeyType oldKey = target->item.getKey();  // remember old
        target->item.setKey(newKey);             // change to new
        if (higherPriority(newKey, oldKey)) {
            trickleUp(target);    // priority went up, so trickle it up
        } else {
            trickleDown(target);  // priority went down (or stayed), so trickle it down
        }
    }

    void displayHeap() const override {
        // visit the nodes breadth-first, the same order as the array version
        std::vector<KeyType> keys;
        std::vector<Node*> level;
        if (root != nullptr) {
            level.push_back(root);
        }
        for (std::size_t i = 0; i < level.size(); i++) {
            Node* current = level[i];
            keys.push_back(current->item.getKey());
            if (current->left != nullptr) {
                level.push_back(current->left);
            }
            if (current->right != nullptr) {
                level.push_back(current->right);
            }
        }
        printHeap(keys);
    }
};

#endif
