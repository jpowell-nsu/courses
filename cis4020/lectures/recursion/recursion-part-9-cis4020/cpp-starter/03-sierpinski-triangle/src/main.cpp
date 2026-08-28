#include <iostream>
#include "sierpinski.h"

int main() {
    int order = 2;
    int size = 1 << order;   // size = 2 ^ order

    for (int row = 0; row < size; row++) {
        for (int col = 0; col < size; col++) {
            std::cout << (isFilled(row, col, size) ? '*' : ' ');
        }
        std::cout << std::endl;
    }

    return 0;
}
