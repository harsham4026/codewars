package com.codewars;

/**
 * https://www.codewars.com/kata/longest-common-subsequence/
 */
public class LongestCommonSubsequence {
    public static String lcs(String x, String y) {
        int r = x.length();
        int c = y.length();
        int[][] lcs = new int[r + 1][c + 1];
        char[][] dir = new char[r + 1][c + 1];

        for (int i = 0; i < r; i++) {
            lcs[i][0] = 0;
        }
        for (int j = 0; j < c; j++) {
            lcs[0][j] = 0;
        }

        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    lcs[i][j] = 1 + lcs[i - 1][j - 1];
                    dir[i][j] = 'd'; //diag
                } else {
                    if (lcs[i - 1][j] > lcs[i][j - 1]) {
                        lcs[i][j] = lcs[i - 1][j];
                        dir[i][j] = 'u';
                    } else {
                        lcs[i][j] = lcs[i][j - 1];
                        dir[i][j] = 'l';
                    }
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        constructLcs(dir, r, c, x, builder);
        return builder.toString();
    }
    private static void constructLcs(char[][] dir, int i, int j, String x, StringBuilder builder) {
        if (i == 0 || j == 0) {
            return;
        }
        if (dir[i][j] == 'd') {
            constructLcs(dir, i - 1, j - 1, x, builder);
            builder.append(x.charAt(i - 1));
        } else if (dir[i][j] == 'u') {
            constructLcs(dir, i - 1, j, x, builder);
        } else {
            constructLcs(dir, i, j - 1, x, builder);
        }

    }
}