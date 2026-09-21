#ifndef SORTS_H
#define SORTS_H

// ---------------------------------------------------------------------------
// Sorts.h
//
// The five sorting algorithms, written as static member function templates so
// the same code works for int, Droid, or any type T that provides:
//   - a default constructor
//   - copy construction and copy assignment
//   - the < and > comparison operators
//
// This takes the place of the Java bound T extends Comparable<? super T>.
// Every sort takes the array and the number of items n, and sorts the array
// into ascending order. Template definitions must be visible to the compiler
// wherever they are used, so all of the code lives in this header and there
// is no Sorts.cpp.
//
// Call them like Java's static methods:  Sorts::bubbleSort(items, n);
// The compiler works out T from the array, so no <int> is needed.
// ---------------------------------------------------------------------------
class Sorts {
public:
	// ------------------------------------------------------------------------
	// Sorts the items in an array into ascending order.
	// Precondition: theArray is an array of n items.
	// Postcondition: theArray is sorted into ascending order.
	// Calls: indexOfLargest.
	// ------------------------------------------------------------------------
	// last = index of the last item in the subarray of items yet to be sorted
	// largest = index of the largest item found
	template <typename T>
	static void selectionSort(T theArray[], int n) {
		for (int last = n - 1; last >= 1; last--) {
			// Invariant: theArray[last+1..n-1] is sorted and > theArray[0..last]

			// select largest item in theArray[0..last]
			int largest = indexOfLargest(theArray, last + 1);
			// swap largest item theArray[largest] with theArray[last]
			T temp = theArray[largest];
			theArray[largest] = theArray[last];
			theArray[last] = temp;
		}
	}

	// ------------------------------------------------------------------------
	// Sorts the items in an array into ascending order.
	// Precondition: theArray is an array of n items.
	// Postcondition: theArray is sorted into ascending order.
	// ------------------------------------------------------------------------
	template <typename T>
	static void bubbleSort(T theArray[], int n) {
		bool sorted = false; // false when swaps occur

		for (int pass = 1; (pass < n) && !sorted; ++pass) {
			// Invariant: theArray[n+1-pass..n-1] is sorted and > theArray[0..n-pass]
			sorted = true; // assume sorted
			for (int index = 0; index < n - pass; ++index) {
				// Invariant: theArray[0..index-1] <= theArray[index]
				int nextIndex = index + 1;
				if (theArray[index] > theArray[nextIndex]) {
					// exchange items
					T temp = theArray[index];
					theArray[index] = theArray[nextIndex];
					theArray[nextIndex] = temp;
					sorted = false; // signal exchange
				}
			}
		}
	}

	// ------------------------------------------------------------------------
	// Sorts the items in an array into ascending order.
	// Precondition: theArray is an array of n items.
	// Postcondition: theArray is sorted into ascending order.
	// ------------------------------------------------------------------------
	template <typename T>
	static void insertionSort(T theArray[], int n) {
		// unsorted = first index of the unsorted region,
		// loc = index of insertion in the sorted region,
		// nextItem = next item in the unsorted region
		// initially, sorted region is theArray[0], unsorted region is theArray[1..n-1];
		// in general, sorted region is theArray[0..unsorted-1], unsorted region is theArray[unsorted..n-1]
		for (int unsorted = 1; unsorted < n; ++unsorted) {
			// Invariant: theArray[0..unsorted-1] is sorted
			// find the right position (loc) in theArray[0..unsorted] for theArray[unsorted],
			// which is the first item in the unsorted region; shift, if necessary, to make room
			T nextItem = theArray[unsorted];
			int loc = unsorted;

			while ((loc > 0) && (theArray[loc - 1] > nextItem)) {
				// shift theArray[loc-1] to the right
				theArray[loc] = theArray[loc - 1];
				loc--;
			}
			// insert nextItem into sorted region
			theArray[loc] = nextItem;
		}
	}

	// ------------------------------------------------------------------------
	// Sorts the items in an array into ascending order.
	// Precondition: theArray is an array of n items.
	// Postcondition: theArray is sorted into ascending order.
	// Calls: the four-parameter mergesort, which calls merge.
	// ------------------------------------------------------------------------
	template <typename T>
	static void mergesort(T theArray[], int n) {
		// Declare the temporary array used for merge. Java can hand this job
		// to the garbage collector; in C++ new[] must be matched by delete[].
		T* tempArray = new T[n];
		mergesort(theArray, tempArray, 0, n - 1);
		delete[] tempArray;
	}

	// ------------------------------------------------------------------------
	// Sorts the items in an array into ascending order.
	// Precondition: theArray[first..last] is an array.
	// Postcondition: theArray[first..last] is sorted in ascending order.
	// Calls: merge.
	// ------------------------------------------------------------------------
	template <typename T>
	static void mergesort(T theArray[], T tempArray[], int first, int last) {
		if (first < last) {
			// sort each half
			int mid = (first + last) / 2; // index of midpoint
			// sort left half theArray[first..mid]
			mergesort(theArray, tempArray, first, mid);
			// sort right half theArray[mid+1..last]
			mergesort(theArray, tempArray, mid + 1, last);
			// merge the two halves
			merge(theArray, tempArray, first, mid, last);
		}
	}

	// ------------------------------------------------------------------------
	// Public method to sort the items in an array into ascending order.
	// Precondition: theArray is an array of n items.
	// Postcondition: theArray is sorted in ascending order.
	// Calls: private quickSort
	// ------------------------------------------------------------------------
	template <typename T>
	static void quickSort(T theArray[], int n) {
		quickSort(theArray, 0, n - 1);
	}

private:
	// ------------------------------------------------------------------------
	// Finds the largest item in an array.
	// Precondition: theArray is an array of items and size >= 1.
	// Postcondition: Returns the index of the largest item in the array.
	// ------------------------------------------------------------------------
	template <typename T>
	static int indexOfLargest(const T theArray[], int size) {
		int indexSoFar = 0; // index of largest item found so far

		// Invariant: theArray[indexSoFar]>=theArray[0..currIndex-1]
		for (int currIndex = 1; currIndex < size; ++currIndex) {
			if (theArray[currIndex] > theArray[indexSoFar]) {
				indexSoFar = currIndex;
			}
		}

		return indexSoFar; // index of largest item
	}

	// ------------------------------------------------------------------------
	// Merges two sorted array segments theArray[first..mid] and
	//	theArray[mid+1..last] into one sorted array.
	// Precondition: first <= mid <= last. The subarrays theArray[first..mid]
	//	and theArray[mid+1..last] are each sorted in increasing order.
	// Postcondition: theArray[first..last] is sorted.
	// Implementation note: This method merges the two subarrays into a
	//	temporary array and copies the result into the original array theArray.
	// ------------------------------------------------------------------------
	template <typename T>
	static void merge(T theArray[], T tempArray[], int first, int mid, int last) {
		// initialize the local indexes to indicate the subarrays
		int first1 = first;    // beginning of first subarray
		int last1 = mid;       // end of first subarray
		int first2 = mid + 1;  // beginning of second subarray
		int last2 = last;      // end of second subarray

		// while both subarrays are not empty, copy the smaller item into the temporary array
		int index = first1;    // next available location in tempArray

		while ((first1 <= last1) && (first2 <= last2)) {
			// Invariant: tempArray[first1..index-1] is in order
			if (theArray[first1] < theArray[first2]) {
				tempArray[index] = theArray[first1];
				first1++;
			} else {
				tempArray[index] = theArray[first2];
				first2++;
			}
			index++;
		}

		// finish off the nonempty subarray (could be either side of it)

		// finish off the first subarray, if necessary
		while (first1 <= last1) {
			// Invariant: tempArray[first1..index-1] is in order
			tempArray[index] = theArray[first1];
			first1++;
			index++;
		}

		// finish off the second subarray, if necessary
		while (first2 <= last2) {
			// Invariant: tempArray[first1..index-1] is in order
			tempArray[index] = theArray[first2];
			first2++;
			index++;
		}

		// copy the result back into the original array
		for (index = first; index <= last; ++index) {
			theArray[index] = tempArray[index];
		}
	}

	// ------------------------------------------------------------------------
	// Chooses a pivot for quicksort's partition algorithm and swaps it with
	//	the first item in an array.
	// Precondition: theArray[first..last] where first <= last.
	// Postcondition: theArray[first] is the pivot.
	// ------------------------------------------------------------------------
	template <typename T>
	static void choosePivot([[maybe_unused]] T theArray[],
	                        [[maybe_unused]] int first,
	                        [[maybe_unused]] int last) {
		// code how to select the pivot and swap the element with the first element
		// note that the rest of the algorithm assumes the pivot in the first element

		return;
	}

	// ------------------------------------------------------------------------
	// Partitions an array for quicksort.
	// Precondition: theArray[first..last] where first <= last.
	// Postcondition: Returns the index of the pivot element of
	//	theArray[first..last]. Upon completion of the method, this will be the
	//	index value lastS1 such that
	//		S1 = theArray[first..lastS1-1] < pivot
	//		theArray[lastS1] == pivot
	//		S2 = theArray[lastS1+1..last] >= pivot
	// Calls: choosePivot.
	// ------------------------------------------------------------------------
	template <typename T>
	static int partition(T theArray[], int first, int last) {
		T tempItem; // tempItem is used to swap elements in the array

		choosePivot(theArray, first, last); // place pivot in theArray[first]
		T pivot = theArray[first];          // reference pivot, initially, everything but pivot is in unknown
		int lastS1 = first;                 // index of last item in S1

		// move one item at a time until unknown region is empty
		// firstUnknown is the index of first item in unknown region
		for (int firstUnknown = first + 1; firstUnknown <= last; ++firstUnknown) {
			// Invariant: 	theArray[first+1..lastS1] < pivot
			//	 			theArray[lastS1+1..firstUnknown-1] >= pivot
			// move item from unknown to proper region
			if (theArray[firstUnknown] < pivot) {
				// item from unknown belongs in S1
				++lastS1;
				tempItem = theArray[firstUnknown];
				theArray[firstUnknown] = theArray[lastS1];
				theArray[lastS1] = tempItem;
			}
			// else item from unknown belongs in S2
		}
		// place pivot in proper position and mark its location
		tempItem = theArray[first];
		theArray[first] = theArray[lastS1];
		theArray[lastS1] = tempItem;

		return lastS1;
	}

	// ------------------------------------------------------------------------
	// Sorts the items in an array into ascending order.
	// Precondition: theArray[first..last] is an array.
	// Postcondition: theArray[first..last] is sorted.
	// Calls: partition.
	// ------------------------------------------------------------------------
	template <typename T>
	static void quickSort(T theArray[], int first, int last) {
		int pivotIndex;

		if (first < last) {
			// create the partition: S1, Pivot, S2
			pivotIndex = partition(theArray, first, last);
			// sort regions S1 and S2
			quickSort(theArray, first, pivotIndex - 1);
			quickSort(theArray, pivotIndex + 1, last);
		}
	}
};

#endif
