package heap;

import java.util.ArrayList;
import java.util.List;

// array-based heap
// trickleUp, trickleDown, and change adapted from
// Data Structures and Algorithms in Java, 2nd ed, Robert Lafore
//
// The root is at index 0. For the item at index i:
//	parent      is at (i - 1) / 2
//	left child  is at 2 * i + 1
//	right child is at 2 * i + 2
public class HeapArrayBased<T extends KeyedItem<KT>, KT extends Comparable<? super KT>>
		implements HeapInterface<T, KT> {

	private T[] heap;
	private int maxSize;	// max heap size
	private int size;		// number of items in heap
	private HeapType type;	// MAX or MIN

	public HeapArrayBased(int maxSize) {
		this(maxSize, HeapType.MAX); // a maxheap unless told otherwise
	}

	@SuppressWarnings("unchecked")
	public HeapArrayBased(int maxSize, HeapType type) {
		this.maxSize = maxSize;
		this.size = 0;
		this.type = type;
		this.heap = (T[]) new KeyedItem<?>[maxSize]; // create array
	}

	public boolean isEmpty() {
		return (size == 0);
	}

	public int size() {
		return size;
	}

	public void removeAll() {
		for(int i = 0; i < size; i++) {
			heap[i] = null;
		}
		size = 0;
	}

	public void insert(T item) {
		if(item == null) {
			throw new HeapException("Heap Exception: Null Item");
		}
		if(size == maxSize) {
			throw new HeapException("Heap Exception: Heap Full");
		}
		heap[size] = item; // step 1: place the new item as the last node
		trickleUp(size);   // step 2: trickle up
		size++;
	}

	private void trickleUp(int index) {
		int parent = (index - 1) / 2;
		T bottom = heap[index]; // the item that is moving up

		// while not at the root and the new item outranks its parent
		while(index > 0 && higherPriority(bottom.getKey(), heap[parent].getKey())) {
			heap[index] = heap[parent]; // move the parent down
			index = parent;
			parent = (parent - 1) / 2;
		}
		heap[index] = bottom;
	}

	public T remove() {
		if(size == 0) {
			throw new HeapException("Heap Exception: Empty Heap");
		}
		T root = heap[0];     // step 1: remove the root

		size--;
		heap[0] = heap[size]; // step 2: place the last node as the root
		heap[size] = null;
		if(size > 0) {
			trickleDown(0);   // step 3: trickle down
		}
		return root;
	}

	private void trickleDown(int index) {
		int higherChild;
		T top = heap[index]; // the item that is moving down

		while(index < size / 2) { // while the node has at least one child
			int leftChild = 2 * index + 1;
			int rightChild = leftChild + 1;
			// find the child with the higher priority
			// (the larger child in a maxheap, the smaller child in a minheap)
			if(rightChild < size
					&& higherPriority(heap[rightChild].getKey(), heap[leftChild].getKey())) {
				higherChild = rightChild; // rightChild exists and wins
			} else {
				higherChild = leftChild;
			}
			// stop when the child does not outrank the item moving down
			if(!higherPriority(heap[higherChild].getKey(), top.getKey())) {
				break;
			}
			heap[index] = heap[higherChild]; // move the child up
			index = higherChild;             // go down
		}
		heap[index] = top;
	}

	public T peek() {
		if(size == 0) {
			throw new HeapException("Heap Exception: Empty Heap");
		}
		return heap[0];
	}

	public void change(int index, KT newKey) {
		if(index < 0 || index >= size) {
			throw new HeapException("Heap Exception: Invalid Index");
		}
		if(newKey == null) {
			throw new HeapException("Heap Exception: Null Key");
		}
		KT oldKey = heap[index].getKey(); // remember old
		heap[index].setKey(newKey);       // change to new
		if(higherPriority(newKey, oldKey)) {
			trickleUp(index);   // priority went up, so trickle it up
		} else {
			trickleDown(index); // priority went down (or stayed), so trickle it down
		}
	}

	// returns true when key1 belongs above key2:
	// larger in a maxheap, smaller in a minheap
	private boolean higherPriority(KT key1, KT key2) {
		int cmp = key1.compareTo(key2);
		if(type == HeapType.MAX) {
			return cmp > 0;
		} else {
			return cmp < 0;
		}
	}

	public void displayHeap() {
		List<KT> keys = new ArrayList<KT>();
		for(int i = 0; i < size; i++) {
			keys.add(heap[i].getKey());
		}
		HeapPrinter.print(keys);
	}

} // end HeapArrayBased
