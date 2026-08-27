public class GridPaths {
    // Counts the number of paths from (0, 0) to (row, col) in a grid,
    // moving only right or down at each step.
    public static int countPaths(int row, int col) {
        if (row == 0 || col == 0) {         // base case: edge of the grid
            return 1;
        }
        return countPaths(row - 1, col) + countPaths(row, col - 1);
    }
}
