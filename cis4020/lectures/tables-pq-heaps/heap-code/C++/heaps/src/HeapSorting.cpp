#include <iostream>
#include <string>
#include <vector>
#include "HeapSort.h"

int main() {
    std::vector<int> numbers = {6, 3, 5, 9, 2, 10};
    std::cout << "Start:            ";
    printArray(numbers);
    heapSort(numbers, true);  // true prints each step
    std::cout << "Sorted:           ";
    printArray(numbers);
    std::cout << "\n";

    // heapSort works on any type that supports <, not only numbers
    std::vector<std::string> names = {"Leia", "Han", "Luke", "Chewbacca", "Rey", "Finn", "Poe"};
    std::cout << "Start:  ";
    printArray(names);
    heapSort(names);
    std::cout << "Sorted: ";
    printArray(names);
    return 0;
}
