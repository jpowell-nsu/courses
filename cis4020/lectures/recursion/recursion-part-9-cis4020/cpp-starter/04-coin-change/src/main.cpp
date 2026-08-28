#include <iostream>
#include "coin_change.h"

int main() {
    int coins[] = {1, 2, 5};
    int amount = 5;

    std::cout << countChange(amount, coins, 3, 0) << std::endl;

    return 0;
}
