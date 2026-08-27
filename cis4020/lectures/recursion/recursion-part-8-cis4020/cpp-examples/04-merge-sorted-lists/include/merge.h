#ifndef MERGE_H
#define MERGE_H

// Merges sorted array a (length na) and sorted array b (length nb) into
// result, starting at indices i, j, and k respectively. Call with
// i = 0, j = 0, k = 0 to merge the two arrays from the start.
// result must have space for at least na + nb elements.
void merge(int a[], int na, int b[], int nb, int result[], int i, int j, int k);

#endif
