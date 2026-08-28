#include <iostream>
#include "regions.h"

int main() {
    char grid[5][COLS] = {
        {'L', 'L', 'W', 'W', 'L'},
        {'L', 'W', 'W', 'L', 'L'},
        {'L', 'W', 'L', 'L', 'W'},
        {'W', 'W', 'L', 'L', 'W'},
        {'L', 'L', 'L', 'W', 'W'}
    };
    bool visited[5][COLS] = {};

    std::cout << regionSize(grid, visited, 5, 0, 0) << std::endl;

    return 0;
}
