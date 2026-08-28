public class Main {
    public static void main(String[] args) {
        char[][] grid = {
            {'L', 'L', 'W', 'W', 'L'},
            {'L', 'W', 'W', 'L', 'L'},
            {'L', 'W', 'L', 'L', 'W'},
            {'W', 'W', 'L', 'L', 'W'},
            {'L', 'L', 'L', 'W', 'W'}
        };
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        System.out.println(ConnectedRegions.regionSize(grid, visited, 0, 0));
    }
}
