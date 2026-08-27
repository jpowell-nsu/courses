#include <iostream>
#include "power.h"

int main() {
    std::cout << "2 ^ 10 = " << power(2, 1000000) << std::endl;
    std::cout << "5 ^ 3 = " << power(5, 3) << std::endl;
    std::cout << "7 ^ 0 = " << power(7, 0) << std::endl;

    return 0;
}
