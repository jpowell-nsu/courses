#include <iostream>
#include "maze.h"

int main() {
    char maze[5][COLS] = {
        {'S', '.', '.', '#', '.'},
        {'#', '#', '.', '#', '.'},
        {'.', '.', '.', '.', '.'},
        {'.', '#', '#', '#', '.'},
        {'.', '.', '.', '.', 'E'}
    };
    bool visited[5][COLS] = {};

    std::cout << std::boolalpha << hasPath(maze, visited, 5, 0, 0, 4, 4) << std::endl;

    return 0;
}
