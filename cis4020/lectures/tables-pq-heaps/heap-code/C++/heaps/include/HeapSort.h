#ifndef HEAP_SORT_H
#define HEAP_SORT_H

#include <iostream>
#include <utility>
#include <vector>

// Heapsort works directly on the array it is sorting. It does not use the
// heap classes, but rebuild is the same trickle down idea as in a maxheap.
//
// Phase 1 turns the whole array into a maxheap.
// Phase 2 repeatedly swaps the root (the largest item left) with the last
// item of the heap, shrinks the heap by one, and rebuilds it. The sorted
// part grows from the back of the array toward the front.

// prints the array (used when showSteps is true)
template <typename T>
void printArray(const std::vector<T>& items) {
    std::cout << "[";
    for (std::size_t i = 0; i < items.size(); i++) {
        std::cout << (i > 0 ? ", " : "") << items[i];
    }
    std::cout << "]\n";
}

// trickles items[index] down within the heap items[0] to items[size - 1]
template <typename T>
void rebuild(std::vector<T>& items, int index, int size) {
    int largerChild;
    T top = items[index];  // the item that is moving down

    while (index < size / 2) {  // while the node has at least one child
        int leftChild = 2 * index + 1;
        int rightChild = leftChild + 1;
        // find the larger child
        if (rightChild < size && items[leftChild] < items[rightChild]) {
            largerChild = rightChild;
        } else {
            largerChild = leftChild;
        }
        // stop when the larger child is not larger than the item moving down
        if (!(top < items[largerChild])) {
            break;
        }
        items[index] = items[largerChild];  // move the child up
        index = largerChild;                // go down
    }
    items[index] = top;
}

// showSteps prints the array after each step, which is handy for tracing
template <typename T>
void heapSort(std::vector<T>& items, bool showSteps = false) {
    int n = static_cast<int>(items.size());

    // phase 1: build a maxheap
    // start at the last node that has a child and work back to the root
    for (int index = n / 2 - 1; index >= 0; index--) {
        rebuild(items, index, n);
    }
    if (showSteps) {
        std::cout << "Heap built:       ";
        printArray(items);
    }

    // phase 2: move the largest item to the end, then rebuild the rest
    for (int last = n - 1; last > 0; last--) {
        // swap the root with the last item in the heap
        std::swap(items[0], items[last]);
        // the heap is now items[0] to items[last - 1]
        rebuild(items, 0, last);
        if (showSteps) {
            std::cout << "Swap root with " << last << ": ";
            printArray(items);
        }
    }
}

#endif
