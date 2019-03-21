package com.codewars;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.codewars.com/kata/path-finder-number-3-the-alpinist/
 */
public class PathFinder3 {

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
        for (int[] a : result) {
            Arrays.fill(a, Integer.MAX_VALUE);
        }

        bfs(grid, result);

        return result[grid.length - 1][grid[0].length - 1];
    }

    private static void bfs(String[][] grid, int[][] result) {
        Queue<String> queue = new LinkedList<>();
        queue.add("0:0");
        result[0][0] = 0;

        while (!queue.isEmpty()) {
            String top = queue.remove();
            String[] p = top.split(":");
            int x = Integer.valueOf(p[0]);
            int y = Integer.valueOf(p[1]);

            for (int i = 0; i < 4; i++) {
                int newX = x + xCoor[i];
                int newY = y + yCoor[i];
                if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length) {
                    int costOfMovement = result[x][y]
                            + Math.abs(asInt(grid[x][y]) - asInt(grid[newX][newY]));
                    int old = result[newX][newY];
                    result[newX][newY] = Math.min(result[newX][newY], costOfMovement);
                    if (result[newX][newY] != old) {
                        queue.add(newX + ":" + newY);
                    }
                }
            }
        }
    }

    private static int asInt(String s) {
        return Integer.parseInt(s);
    }
}