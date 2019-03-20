package com.codewars;

/**
 * https://www.codewars.com/kata/path-finder-number-1-can-you-reach-the-exit/
 */
public class PathFinder1 {
    private static final int xCoor[] = new int[]{-1, 1, 0, 0};
    private static final int yCoor[] = new int[]{0, 0, -1, 1};

    static boolean pathFinder(String maze) {
        String[] rows = maze.split("\n");
        String[][] grid = new String[rows.length][];

        int i = 0;
        for (String row : rows) {
            grid[i++] = row.split("");
        }

        return dfs(grid, 0, 0, new boolean[grid.length][grid[0].length]);
    }

    private static boolean dfs(String[][] grid, int x, int y, boolean[][] visited) {
        if (x == grid.length - 1 && y == grid[0].length - 1) {
            return true;
        }
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int newX = x + xCoor[i];
            int newY = y + yCoor[i];
            if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length
                    && !visited[newX][newY] && grid[newX][newY].equals(".")) {
                boolean r = dfs(grid, newX, newY, visited);
                if (r) {
                    return true;
                }
            }
        }
        return false;
    }
}