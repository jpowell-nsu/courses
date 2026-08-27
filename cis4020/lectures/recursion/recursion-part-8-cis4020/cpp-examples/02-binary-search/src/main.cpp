#include <iostream>
#include "binary_search.h"

int main() {
    int arr[] = {2, 5, 8, 12, 16, 23, 38, 45, 56, 72, 91};
    const int size = sizeof(arr) / sizeof(arr[0]);

    int targets[] = {23, 4, 91};
    for (int target : targets) {
        int index = binarySearch(arr, target, 0, size - 1);
        if (index == -1) {
            std::cout << target << " was not found." << std::endl;
        } else {
            std::cout << target << " was found at index " << index << "." << std::endl;
        }
    }

    return 0;
}
