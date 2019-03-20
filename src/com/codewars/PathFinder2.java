package com.codewars;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.codewars.com/kata/path-finder-number-2-shortest-path/
 */
public class PathFinder2 {

    private static final int xCoor[] = new int[]{-1, 1, 0, 0};
    private static final int yCoor[] = new int[]{0, 0, -1, 1};

    public static int pathFinder(String maze) {
        String[] rows = maze.split("\n");
        String[][] grid = new String[rows.length][];

        int i = 0;
        for (String row : rows) {
            grid[i++] = row.split("");
        }
        int[][] result = new int[grid.length][grid[0].length];
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int[] a : result) {
            Arrays.fill(a, Integer.MAX_VALUE);
        }

        bfs(grid, result, visited);
        for (int[] aa : result) {
            System.out.println(Arrays.toString(aa));
        }
        return result[grid.length - 1][grid[0].length - 1] == Integer.MAX_VALUE ? -1 : result[grid.length - 1][grid[0].length - 1];
    }

    private static void bfs(String[][] grid, int[][] result, boolean[][] visited) {
        Queue<String> queue = new LinkedList<>();
        queue.add("0:0");
        result[0][0] = 0;
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            String top = queue.remove();
            String[] p = top.split(":");
            int x = Integer.valueOf(p[0]);
            int y = Integer.valueOf(p[1]);

            for (int i = 0; i < 4; i++) {
                int newX = x + xCoor[i];
                int newY = y + yCoor[i];
                if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length
                        && grid[newX][newY].equals(".") && !visited[newX][newY]) {
                    queue.add(newX + ":" + newY);
                    result[newX][newY] = result[x][y] + 1;
                    visited[newX][newY] = true;
                    //If this is there, the full result not built
                    //  if (newX == grid.length - 1 && newY == grid[0].length - 1) {
                    //     return;
                    //}
                }
            }
        }
    }
}