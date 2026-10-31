package sort;

import java.util.Arrays;

// Heapsort works directly on the array it is sorting. It does not use the
// Heap classes, but rebuild is the same trickle down idea as in a maxheap.
//
// Phase 1 turns the whole array into a maxheap.
// Phase 2 repeatedly swaps the root (the largest item left) with the last
// item of the heap, shrinks the heap by one, and rebuilds it. The sorted
// part grows from the back of the array toward the front.
public class HeapSort {

	public static <T extends Comparable<? super T>> void heapSort(T[] items) {
		heapSort(items, false);
	}

	// showSteps prints the array after each step, which is handy for tracing
	public static <T extends Comparable<? super T>> void heapSort(T[] items, boolean showSteps) {
		int n = items.length;

		// phase 1: build a maxheap
		// start at the last node that has a child and work back to the root
		for(int index = n / 2 - 1; index >= 0; index--) {
			rebuild(items, index, n);
		}
		if(showSteps) {
			System.out.println("Heap built:       " + Arrays.toString(items));
		}

		// phase 2: move the largest item to the end, then rebuild the rest
		for(int last = n - 1; last > 0; last--) {
			// swap the root with the last item in the heap
			T temp = items[0];
			items[0] = items[last];
			items[last] = temp;
			// the heap is now items[0] to items[last - 1]
			rebuild(items, 0, last);
			if(showSteps) {
				System.out.println("Swap root with " + last + ": " + Arrays.toString(items));
			}
		}
	}

	// trickles items[index] down within the heap items[0] to items[size - 1]
	private static <T extends Comparable<? super T>> void rebuild(T[] items, int index, int size) {
		int largerChild;
		T top = items[index]; // the item that is moving down

		while(index < size / 2) { // while the node has at least one child
			int leftChild = 2 * index + 1;
			int rightChild = leftChild + 1;
			// find the larger child
			if(rightChild < size && items[rightChild].compareTo(items[leftChild]) > 0) {
				largerChild = rightChild;
			} else {
				largerChild = leftChild;
			}
			// stop when the larger child is not larger than the item moving down
			if(items[largerChild].compareTo(top) <= 0) {
				break;
			}
			items[index] = items[largerChild]; // move the child up
			index = largerChild;               // go down
		}
		items[index] = top;
	}

} // end HeapSort
