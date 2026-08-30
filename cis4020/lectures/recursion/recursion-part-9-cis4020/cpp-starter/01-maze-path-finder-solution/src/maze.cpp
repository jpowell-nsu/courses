#include "maze.h"

bool hasPath(char maze[][COLS], bool visited[][COLS], int rows,
             int row, int col, int endRow, int endCol) {
    // TODO: implement this function, including the base cases we worked
    // out as a class.

    // a possible solution
    if (row < 0 || row >= rows || col < 0 || col >= COLS) {
        return false;
    }

    if (maze[row][col] == '#' || visited[row][col]) {
        return false;
    }

    if (row == endRow && col == endCol) {
        return true;
    }

    visited[row][col] = true;

    return     hasPath(maze, visited, rows, row - 1, col, endRow, endCol)
            || hasPath(maze, visited, rows, row + 1, col, endRow, endCol)
            || hasPath(maze, visited, rows, row, col - 1, endRow, endCol)
            || hasPath(maze, visited, rows, row, col + 1, endRow, endCol);

}
