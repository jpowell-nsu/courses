package driver;

import java.util.Arrays;

import sort.HeapSort;

public class HeapSorting {

	public static void main(String[] args) {
		Integer[] numbers = {6, 3, 5, 9, 2, 10};
		System.out.println("Start:            " + Arrays.toString(numbers));
		HeapSort.heapSort(numbers, true); // true prints each step
		System.out.println("Sorted:           " + Arrays.toString(numbers));
		System.out.println();

		// heapSort works on any Comparable type, not only numbers
		String[] names = {"Leia", "Han", "Luke", "Chewbacca", "Rey", "Finn", "Poe"};
		System.out.println("Start:  " + Arrays.toString(names));
		HeapSort.heapSort(names);
		System.out.println("Sorted: " + Arrays.toString(names));
	}

} // end HeapSorting
