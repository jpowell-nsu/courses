#ifndef HEAP_BASICS_H
#define HEAP_BASICS_H

#include <iostream>
#include <stdexcept>
#include <string>
#include <vector>

class HeapException : public std::runtime_error {
public:
    explicit HeapException(const std::string& msg) : std::runtime_error(msg) {}
};

// MAX: the root holds the largest key (a maxheap)
// MIN: the root holds the smallest key (a minheap)
enum class HeapType { MAX, MIN };

// The interface both heaps follow. ItemType must provide getKey() and
// setKey(), and KeyType must support < and ==.
template <typename ItemType, typename KeyType>
class HeapInterface {
public:
    virtual ~HeapInterface() {}

    // returns true if the heap has no items
    virtual bool isEmpty() const = 0;

    // returns the number of items in the heap
    virtual int size() const = 0;

    // removes every item from the heap
    virtual void removeAll() = 0;

    // adds item to the heap
    // throws HeapException if the heap is full
    virtual void insert(const ItemType& item) = 0;

    // removes and returns the item with the highest priority (the root)
    // throws HeapException if the heap is empty
    virtual ItemType remove() = 0;

    // returns the item with the highest priority without removing it
    // throws HeapException if the heap is empty
    virtual ItemType peek() const = 0;

    // changes the key of the item at index (breadth-first order, root is 0)
    // and moves the item up or down to restore the heap
    // throws HeapException if index is out of range
    virtual void change(int index, const KeyType& newKey) = 0;

    // prints the heap, first in array order and then as a tree
    virtual void displayHeap() const = 0;
};

// Prints a heap for both implementations. The caller passes the keys in
// breadth-first order (root first, then each level from left to right),
// which is the same as array order.
// Layout adapted from Data Structures and Algorithms in Java, 2nd ed, Robert Lafore
template <typename KeyType>
void printHeap(const std::vector<KeyType>& keys) {
    int size = static_cast<int>(keys.size());

    // array format
    std::cout << "Heap: ";
    for (int i = 0; i < size; i++) {
        std::cout << keys[i] << " ";
    }
    std::cout << "\n";

    // tree format (lines up best for 31 or fewer two-digit keys)
    int nBlanks = 32;
    int itemsPerRow = 1;
    int column = 0;
    int j = 0;  // current item
    std::cout << "--------------------------------------------------------------\n";
    while (j < size) {                            // for each heap item
        if (column == 0) {                        // first item in row?
            std::cout << std::string(nBlanks, ' ');   // preceding blanks
        }
        std::cout << keys[j];                     // display item
        j++;
        if (j == size) {                          // done
            break;
        }
        column++;
        if (column == itemsPerRow) {              // end of row?
            nBlanks /= 2;                         // half the blanks
            itemsPerRow *= 2;                     // twice the items
            column = 0;                           // start over on
            std::cout << "\n";                    // new row
        } else {                                  // next item on row
            std::cout << std::string(nBlanks * 2 - 2, ' ');  // interim blanks
        }
    }
    std::cout << "\n--------------------------------------------------------------\n";
}

#endif
