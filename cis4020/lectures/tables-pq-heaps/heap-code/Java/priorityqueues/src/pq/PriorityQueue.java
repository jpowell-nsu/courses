package pq;

import heap.HeapArrayBased;
import heap.HeapException;
import heap.HeapInterface;
import heap.HeapReferenceBased;
import heap.HeapType;
import heap.KeyedItem;

// A priority queue built on a heap. Every operation is passed straight to
// the heap, so the priority queue is only a thin wall around it. Code that
// uses a PriorityQueue never sees the heap, and the heap could be swapped
// for another implementation without changing that code.
public class PriorityQueue<T extends KeyedItem<KT>, KT extends Comparable<? super KT>> {

	private HeapInterface<T, KT> heap;

	// a highest-key-first queue with no size limit
	public PriorityQueue() {
		this(HeapType.MAX);
	}

	// no size limit, so it uses the reference-based heap
	// HeapType.MAX serves the largest key first, HeapType.MIN the smallest
	public PriorityQueue(HeapType type) {
		heap = new HeapReferenceBased<T, KT>(type);
	}

	// a fixed size, so it uses the array-based heap
	public PriorityQueue(int maxSize, HeapType type) {
		heap = new HeapArrayBased<T, KT>(maxSize, type);
	}

	public boolean isEmpty() {
		return heap.isEmpty();
	}

	public int size() {
		return heap.size();
	}

	public void removeAll() {
		heap.removeAll();
	}

	public void insert(T item) {
		if(item == null) {
			throw new PriorityQueueException("Priority Queue Exception: Null Item");
		}
		try {
			heap.insert(item);
		} catch(HeapException e) {
			// the only other way insert can fail is a full array-based heap
			throw new PriorityQueueException("Priority Queue Exception: Queue Full");
		}
	}

	// removes and returns the item with the highest priority
	public T remove() {
		try {
			return heap.remove();
		} catch(HeapException e) {
			throw new PriorityQueueException("Priority Queue Exception: Empty Queue");
		}
	}

	// returns the item with the highest priority without removing it
	public T peek() {
		try {
			return heap.peek();
		} catch(HeapException e) {
			throw new PriorityQueueException("Priority Queue Exception: Empty Queue");
		}
	}

} // end PriorityQueue
