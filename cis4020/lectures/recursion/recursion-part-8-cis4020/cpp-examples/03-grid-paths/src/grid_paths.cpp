#include "grid_paths.h"

int countPaths(int row, int col) {
    if (row == 0 || col == 0) {         // base case: edge of the grid
        return 1;
    }
    return countPaths(row - 1, col) + countPaths(row, col - 1);
}
