#include <iostream>
#include "merge.h"

int main() {
    int a[] = {1, 4, 7, 9};
    int b[] = {2, 3, 8, 10, 11};
    const int na = sizeof(a) / sizeof(a[0]);
    const int nb = sizeof(b) / sizeof(b[0]);
    int result[na + nb];

    merge(a, na, b, nb, result, 0, 0, 0);

    std::cout << "Merged list: ";
    for (int i = 0; i < na + nb; i++) {
        std::cout << result[i] << " ";
    }
    std::cout << std::endl;

    return 0;
}
