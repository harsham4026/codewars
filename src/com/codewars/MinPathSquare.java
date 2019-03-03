package com.codewars;

/**
 * https://www.codewars.com/kata/minimum-path-in-squares/
 */
public class MinPathSquare {

    public static int minPath(int[][] grid, int x, int y) {
        int[][] dp = new int[grid.length][grid.length];

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid.length; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                    continue;
                }
                dp[i][j] = Math.min(i - 1 >= 0 ? dp[i - 1][j] : Integer.MAX_VALUE,
                        j - 1 >= 0 ? dp[i][j - 1] : Integer.MAX_VALUE) + grid[i][j];
            }
        }
        return dp[y][x]; //switched in question
    }
}