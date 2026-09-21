package sorts;

public class Sorts {

	// ------------------------------------------------------------------------
	// Sorts the items in an array into ascending order.
	// Precondition: theArray is an array of n items.
	// Postcondition: theArray is sorted into ascending order.
	// Calls: indexOfLargest.
	// ------------------------------------------------------------------------
	// last = index of the last item in the subarray of items yet to be sorted
	// largest = index of the largest item found
	public static <T extends Comparable<? super T>>	void selectionSort(T[] theArray) {
		for (int last = theArray.length-1; last >= 1; last--) {
			// Invariant: theArray[last+1..n-1] is sorted and > theArray[0..last]
			
			// select largest item in theArray[0..last]
			int largest = indexOfLargest(theArray, last+1);
			// swap largest item theArray[largest] with theArray[last]
			T temp = theArray[largest];
			theArray[largest] = theArray[last];
			theArray[last] = temp;
		}
	}
	
	// ------------------------------------------------------------------------
	// Finds the largest item in an array.
	// Precondition: theArray is an array of size items size >= 1.
	// Postcondition: Returns the index of the largest item in the array.
	// ------------------------------------------------------------------------
	private static <T extends Comparable<? super T>> int indexOfLargest(T[] theArray, int size) {
		int indexSoFar = 0; // index of largest item found so far
	
		// Invariant: theArray[indexSoFar]>=theArray[0..currIndex-1]
		for (int currIndex = 1; currIndex < size; ++currIndex) {
			if (theArray[currIndex].compareTo(theArray[indexSoFar])>0) {
				indexSoFar = currIndex;
			}
		}
		
		return indexSoFar; // index of largest item
	}
	
	// ------------------------------------------------------------------------
	// Sorts the items in an array into ascending order.
	// Precondition: theArray is an array of n items.
	// Postcondition: theArray is sorted into ascending order.
	// ------------------------------------------------------------------------
	public static <T extends Comparable<? super T>> void bubbleSort(T[] theArray) {
		boolean sorted = false; // false when swaps occur
		
		for (int pass = 1; (pass < theArray.length) && !sorted; ++pass) {
			// Invariant: theArray[n+1-pass..n-1] is sorted and > theArray[0..n-pass]
			sorted = true; // assume sorted
			for (int index = 0; index < theArray.length-pass; ++index) {
				// Invariant: theArray[0..index-1] <= theArray[index]
				int nextIndex = index + 1;
				if (theArray[index].compareTo(theArray[nextIndex]) > 0) {
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
	public static <T extends Comparable<? super T>>	void insertionSort(T[] theArray) {
		// unsorted = first index of the unsorted region,
		// loc = index of insertion in the sorted region,
		// nextItem = next item in the unsorted region
		// initially, sorted region is theArray[0], unsorted region is theArray[1..n-1];
		// in general, sorted region is theArray[0..unsorted-1],unsorted region is theArray[unsorted..n-1]	
		for (int unsorted = 1; unsorted < theArray.length; ++unsorted) {
			// Invariant: theArray[0..unsorted-1] is sorted find the right position (loc) in theArray[0..unsorted] for theArray[unsorted],
			// which is the first item in the unsorted region; shift, if necessary, to make room
			T nextItem = theArray[unsorted];
			int loc = unsorted;
			
			while ((loc > 0) && (theArray[loc-1].compareTo(nextItem) > 0)) {
				//shift theArray[loc-1] to the right
				theArray[loc] = theArray[loc-1];
				loc--;
			}
			// insert nextItem into sorted region
			theArray[loc] = nextItem;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static<T extends Comparable<? super T>> void mergesort(T[] theArray) {
		// Declare temporary array used for merge, must do
		// unchecked cast from Comparable<?>[] to T[]
		T[] tempArray = (T[]) new Comparable<?>[theArray.length];
		mergesort(theArray, tempArray, 0, theArray.length - 1 );
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
	private static<T extends Comparable<? super T>> void merge(T[] theArray, T[] tempArray,	int first, int mid, int last) {
		// initialize the local indexes to indicate the subarrays
		int first1 = first;		// beginning of first subarray
		int last1 = mid;		// end of first subarray
		int first2 = mid + 1;	// beginning of second subarray
		int last2 = last;		// end of second subarray
		
		// while both subarrays are not empty, copy the smaller item into the temporary array
		int index = first1;		// next available location in tempArray
		
		while ((first1 <= last1) && (first2 <= last2)) {
			// Invariant: tempArray[first1..index-1] is in order
			if (theArray[first1].compareTo(theArray[first2])<0) {
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
	// Sorts the items in an array into ascending order.
	// Precondition: theArray[first..last] is an array.
	// Postcondition: theArray[first..last] is sorted in ascending order.
	// Calls: merge.
	// ------------------------------------------------------------------------
	public static <T extends Comparable<? super T>>	void mergesort(T[] theArray, T[] tempArray, int first, int last) {
		if (first < last) {
			// sort each half
			int mid = (first + last)/2;	// index of midpoint
			// sort left half theArray[first..mid]
			mergesort(theArray, tempArray, first, mid);
			// sort right half theArray[mid+1..last]
			mergesort(theArray, tempArray, mid+1, last);
			// merge the two halves
			merge(theArray, tempArray, first, mid, last);
		}
	}

	// ------------------------------------------------------------------------
	// Chooses a pivot for quicksort's partition algorithm and swaps it with
	//	the first item in an array.
	// Precondition: theArray[first..last] where first <= last.
	// Postcondition: theArray[first] is the pivot.
	// ------------------------------------------------------------------------
	private static <T extends Comparable<? super T>> void choosePivot(T[] theArray, int first, int last) {
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
	private static <T extends Comparable<? super T>> int partition(T[] theArray, int first, int last) {
		T tempItem;	// tempItem is used to swap elements in the array
		
		choosePivot(theArray, first, last);	// place pivot in theArray[first]
		T pivot = theArray[first];			// reference pivot, initially, everything but pivot is in unknown 
		int lastS1 = first;					// index of last item in S1
	
		// move one item at a time until unknown region is empty
		// firstUnknown is the index of first item in unknown region
		for (int firstUnknown = first + 1; firstUnknown <= last; ++firstUnknown) {
			// Invariant: 	theArray[first+1..lastS1] < pivot
			//	 			theArray[lastS1+1..firstUnknown-1] >= pivot
			// move item from unknown to proper region
			if (theArray[firstUnknown].compareTo(pivot) < 0) {
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
	private static <T extends Comparable<? super T>> void quickSort(T[] theArray, int first, int last) {
		int pivotIndex;
		
		if (first < last) {
			// create the partition: S1, Pivot, S2
			pivotIndex = partition(theArray, first, last);
			// sort regions S1 and S2
			quickSort(theArray, first, pivotIndex-1);
			quickSort(theArray, pivotIndex+1, last);
		}
	}
	
	// ------------------------------------------------------------------------
	// Public method to sort the items in an array into ascending order.
	// Precondition: theArray[first..last] is an array.
	// Postcondition: theArray[first..last] is sorted.
	// Calls: private quicksort
	// ------------------------------------------------------------------------
	public static <T extends Comparable<? super T>> void quickSort(T[] theArray) {
		quickSort(theArray, 0, theArray.length-1);
	}
}
