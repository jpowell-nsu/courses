#ifndef HEAP_ARRAY_BASED_H
#define HEAP_ARRAY_BASED_H

#include <vector>
#include "HeapBasics.h"

// array-based heap
// trickleUp, trickleDown, and change adapted from
// Data Structures and Algorithms in Java, 2nd ed, Robert Lafore
//
// The root is at index 0. For the item at index i:
//     parent      is at (i - 1) / 2
//     left child  is at 2 * i + 1
//     right child is at 2 * i + 2
//
// The items live in a std::vector so that ItemType does not need a default
// constructor. The vector never grows past maxSize, so it acts like an array.
template <typename ItemType, typename KeyType>
class HeapArrayBased : public HeapInterface<ItemType, KeyType> {
private:
    std::vector<ItemType> heap;
    int maxSize;    // max heap size
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

    void trickleUp(int index) {
        int parent = (index - 1) / 2;
        ItemType bottom = heap[index];  // the item that is moving up

        // while not at the root and the new item outranks its parent
        while (index > 0 && higherPriority(bottom.getKey(), heap[parent].getKey())) {
            heap[index] = heap[parent];  // move the parent down
            index = parent;
            parent = (parent - 1) / 2;
        }
        heap[index] = bottom;
    }

    void trickleDown(int index) {
        int heapSize = static_cast<int>(heap.size());
        int higherChild;
        ItemType top = heap[index];  // the item that is moving down

        while (index < heapSize / 2) {  // while the node has at least one child
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;
            // find the child with the higher priority
            // (the larger child in a maxheap, the smaller child in a minheap)
            if (rightChild < heapSize &&
                higherPriority(heap[rightChild].getKey(), heap[leftChild].getKey())) {
                higherChild = rightChild;  // rightChild exists and wins
            } else {
                higherChild = leftChild;
            }
            // stop when the child does not outrank the item moving down
            if (!higherPriority(heap[higherChild].getKey(), top.getKey())) {
                break;
            }
            heap[index] = heap[higherChild];  // move the child up
            index = higherChild;              // go down
        }
        heap[index] = top;
    }

public:
    explicit HeapArrayBased(int maxSize, HeapType type = HeapType::MAX)
        : maxSize(maxSize), type(type) {
        heap.reserve(maxSize);
    }

    bool isEmpty() const override {
        return heap.empty();
    }

    int size() const override {
        return static_cast<int>(heap.size());
    }

    void removeAll() override {
        heap.clear();
    }

    void insert(const ItemType& item) override {
        if (size() == maxSize) {
            throw HeapException("Heap Exception: Heap Full");
        }
        heap.push_back(item);    // step 1: place the new item as the last node
        trickleUp(size() - 1);   // step 2: trickle up
    }

    ItemType remove() override {
        if (heap.empty()) {
            throw HeapException("Heap Exception: Empty Heap");
        }
        ItemType root = heap[0];  // step 1: remove the root

        heap[0] = heap.back();    // step 2: place the last node as the root
        heap.pop_back();
        if (!heap.empty()) {
            trickleDown(0);       // step 3: trickle down
        }
        return root;
    }

    ItemType peek() const override {
        if (heap.empty()) {
            throw HeapException("Heap Exception: Empty Heap");
        }
        return heap[0];
    }

    void change(int index, const KeyType& newKey) override {
        if (index < 0 || index >= size()) {
            throw HeapException("Heap Exception: Invalid Index");
        }
        KeyType oldKey = heap[index].getKey();  // remember old
        heap[index].setKey(newKey);             // change to new
        if (higherPriority(newKey, oldKey)) {
            trickleUp(index);    // priority went up, so trickle it up
        } else {
            trickleDown(index);  // priority went down (or stayed), so trickle it down
        }
    }

    void displayHeap() const override {
        std::vector<KeyType> keys;
        for (const ItemType& item : heap) {
            keys.push_back(item.getKey());
        }
        printHeap(keys);
    }
};

#endif
