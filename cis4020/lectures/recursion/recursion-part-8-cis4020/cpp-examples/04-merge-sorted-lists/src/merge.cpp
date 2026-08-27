#include "merge.h"

void merge(int a[], int na, int b[], int nb, int result[], int i, int j, int k) {
    if (i == na && j == nb) {                // base case: both used up
        return;
    } else if (i == na) {                     // base case: only b left
        result[k] = b[j];
        merge(a, na, b, nb, result, i, j + 1, k + 1);
    } else if (j == nb) {                     // base case: only a left
        result[k] = a[i];
        merge(a, na, b, nb, result, i + 1, j, k + 1);
    } else if (a[i] <= b[j]) {
        result[k] = a[i];
        merge(a, na, b, nb, result, i + 1, j, k + 1);
    } else {
        result[k] = b[j];
        merge(a, na, b, nb, result, i, j + 1, k + 1);
    }
}
