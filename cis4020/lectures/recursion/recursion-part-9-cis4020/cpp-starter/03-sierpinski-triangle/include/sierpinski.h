#ifndef SIERPINSKI_H
#define SIERPINSKI_H

// Returns true if (row, col) is part of a Sierpinski triangle pattern
// occupying a size by size grid. size must be a power of two.
//
// TODO: implement this function, including the base case, so that it
// correctly identifies which cells belong to the triangle.
bool isFilled(int row, int col, int size);

#endif
