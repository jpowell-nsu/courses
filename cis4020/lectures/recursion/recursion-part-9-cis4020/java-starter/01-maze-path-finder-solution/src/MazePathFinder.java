public class MazePathFinder {
    // Returns true if a path exists from (row, col) to (endRow, endCol) in
    // maze, moving only up, down, left, or right, without crossing a wall
    // ('#') or revisiting a cell already marked true in visited.
    //
    // TODO: implement this method, including the base cases we worked out
    // as a class.
    public static boolean hasPath(char[][] maze, boolean[][] visited,
                                   int row, int col, int endRow, int endCol) {

        // a possible solution
        if (row < 0 || row >= maze.length || col < 0 || col >= maze[0].length) {
            return false;
        }

        if (maze[row][col] == '#' || visited[row][col]) {
            return false;
        }

        if (row == endRow && col == endCol) {
            return true;
        }
        
        visited[row][col] = true;

        return     hasPath(maze, visited, row - 1, col, endRow, endCol)
                || hasPath(maze, visited, row + 1, col, endRow, endCol)
                || hasPath(maze, visited, row, col - 1, endRow, endCol)
                || hasPath(maze, visited, row, col + 1, endRow, endCol);

        return false;
    }
}
