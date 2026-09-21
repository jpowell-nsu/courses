#include <iostream>
#include <random>
#include <string>

#include "Droid.h"
#include "Sorts.h"

// Prints an array in the same style as Java's Arrays.toString, for example
// [3, 1, 2]. Works for any type that std::cout knows how to print.
template <typename T>
void printArray(const T theArray[], int n) {
	std::cout << "[";
	for (int i = 0; i < n; i++) {
		if (i > 0) {
			std::cout << ", ";
		}
		std::cout << theArray[i];
	}
	std::cout << "]" << std::endl;
}

int main() {
	// ---- Sorting plain ints ------------------------------------------------
	// Java needs the Integer class for generics. C++ templates work directly
	// with the primitive int.
	const int NUM_ITEMS = 50;

	// Seeded with 100 like the Java version, so every run gives the same
	// numbers. The values differ from Java's because the two languages use
	// different random number generators.
	std::mt19937 rng(100);
	std::uniform_int_distribution<int> dist(0, 999);

	int items[NUM_ITEMS];
	for (int i = 0; i < NUM_ITEMS; i++) {
		items[i] = dist(rng);
	}

	printArray(items, NUM_ITEMS);
	Sorts::bubbleSort(items, NUM_ITEMS);
	//Sorts::selectionSort(items, NUM_ITEMS);
	//Sorts::insertionSort(items, NUM_ITEMS);
	//Sorts::mergesort(items, NUM_ITEMS);
	//Sorts::quickSort(items, NUM_ITEMS);
	printArray(items, NUM_ITEMS);

	// ---- Sorting Droid objects ---------------------------------------------
	const int NUM_DROIDS = 10;

	const std::string droidNames[NUM_DROIDS] = {
		"R2-D2", "C-3PO", "BB-8", "K-2SO", "IG-11",
		"L3-37", "D-O", "Chopper", "R5-D4", "IG-88"
	};
	const int droidIDs[NUM_DROIDS] = {792, 540, 162, 966, 420, 342, 340, 565, 694, 186};

	Droid droids[NUM_DROIDS];
	for (int i = 0; i < NUM_DROIDS; i++) {
		droids[i] = Droid(droidIDs[i], droidNames[i]);
	}

	printArray(droids, NUM_DROIDS);
	Sorts::bubbleSort(droids, NUM_DROIDS);
	//Sorts::selectionSort(droids, NUM_DROIDS);
	//Sorts::insertionSort(droids, NUM_DROIDS);
	//Sorts::mergesort(droids, NUM_DROIDS);
	//Sorts::quickSort(droids, NUM_DROIDS);
	printArray(droids, NUM_DROIDS);

	return 0;
}
