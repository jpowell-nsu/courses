#ifndef REGIONS_H
#define REGIONS_H

const int COLS = 5;   // fixed for this exercise

// Returns the number of land ('L') cells connected to (row, col) by
// moving up, down, left, or right through other land cells.
//
// TODO: implement this function, including the base cases we worked out
// as a class.
int regionSize(char grid[][COLS], bool visited[][COLS], int rows, int row, int col);

#endif
