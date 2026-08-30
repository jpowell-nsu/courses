#ifndef MAZE_H
#define MAZE_H

const int COLS = 5;   // fixed for this exercise

// Returns true if a path exists from (row, col) to (endRow, endCol) in
// maze, moving only up, down, left, or right, without crossing a wall
// ('#') or revisiting a cell already marked true in visited.
//
// TODO: implement this function, including the base cases we worked out
// as a class.
bool hasPath(char maze[][COLS], bool visited[][COLS], int rows,
             int row, int col, int endRow, int endCol);

#endif
