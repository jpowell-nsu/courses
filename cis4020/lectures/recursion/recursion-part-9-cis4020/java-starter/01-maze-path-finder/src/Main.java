public class Main {
    public static void main(String[] args) {
        char[][] maze = {
            {'S', '.', '.', '#', '.'},
            {'#', '#', '.', '#', '.'},
            {'.', '.', '.', '.', '.'},
            {'.', '#', '#', '#', '.'},
            {'.', '.', '.', '.', 'E'}
        };
        boolean[][] visited = new boolean[maze.length][maze[0].length];

        System.out.println(MazePathFinder.hasPath(maze, visited, 0, 0, 4, 4));
    }
}
