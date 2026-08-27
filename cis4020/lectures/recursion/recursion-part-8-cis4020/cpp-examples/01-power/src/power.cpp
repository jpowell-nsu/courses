#include "power.h"

int power(int base, int exponent) {
    if (exponent == 0) {                // base case
        return 1;
    }
    return base * power(base, exponent - 1);
}
