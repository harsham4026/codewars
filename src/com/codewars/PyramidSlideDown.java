package com.codewars;

import java.util.Arrays;

/**
 * https://www.codewars.com/kata/pyramid-slide-down/
 */
public class PyramidSlideDown {

    public static int longestSlideDown(int[][] pyramid) {
        int[][] dp = new int[pyramid.length][pyramid[pyramid.length - 1].length];

        dp[0][0] = pyramid[0][0];
        for (int i = 0; i < pyramid.length - 1; i++) {
            for (int j = 0; j < pyramid[i].length; j++) {
                dp[i + 1][j] = Math.max(dp[i + 1][j],
                        dp[i][j] + pyramid[i + 1][j]);
                dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1],
                        dp[i][j] + pyramid[i + 1][j + 1]);
            }
        }
        return Arrays.stream(dp[pyramid.length - 1])
                .max()
                .orElse(0);
    }
}
