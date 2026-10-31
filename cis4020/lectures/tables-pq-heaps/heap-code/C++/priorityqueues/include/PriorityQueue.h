#ifndef PRIORITY_QUEUE_H
#define PRIORITY_QUEUE_H

#include <memory>
#include <stdexcept>
#include <string>
#include "HeapArrayBased.h"
#include "HeapReferenceBased.h"

class PriorityQueueException : public std::runtime_error {
public:
    explicit PriorityQueueException(const std::string& msg) : std::runtime_error(msg) {}
};

// A priority queue built on a heap. Every operation is passed straight to
// the heap, so the priority queue is only a thin wall around it. Code that
// uses a PriorityQueue never sees the heap, and the heap could be swapped
// for another implementation without changing that code.
template <typename ItemType, typename KeyType>
class PriorityQueue {
private:
    std::unique_ptr<HeapInterface<ItemType, KeyType>> heap;

public:
    // no size limit, so it uses the pointer-based heap
    // HeapType::MAX serves the largest key first, HeapType::MIN the smallest
    explicit PriorityQueue(HeapType type = HeapType::MAX)
        : heap(std::make_unique<HeapReferenceBased<ItemType, KeyType>>(type)) {}

    // a fixed size, so it uses the array-based heap
    PriorityQueue(int maxSize, HeapType type)
        : heap(std::make_unique<HeapArrayBased<ItemType, KeyType>>(maxSize, type)) {}

    bool isEmpty() const {
        return heap->isEmpty();
    }

    int size() const {
        return heap->size();
    }

    void removeAll() {
        heap->removeAll();
    }

    void insert(const ItemType& item) {
        try {
            heap->insert(item);
        } catch (const HeapException&) {
            // the only way insert can fail is a full array-based heap
            throw PriorityQueueException("Priority Queue Exception: Queue Full");
        }
    }

    // removes and returns the item with the highest priority
    ItemType remove() {
        try {
            return heap->remove();
        } catch (const HeapException&) {
            throw PriorityQueueException("Priority Queue Exception: Empty Queue");
        }
    }

    // returns the item with the highest priority without removing it
    ItemType peek() const {
        try {
            return heap->peek();
        } catch (const HeapException&) {
            throw PriorityQueueException("Priority Queue Exception: Empty Queue");
        }
    }
};

#endif
