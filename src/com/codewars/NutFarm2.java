package com.codewars;

/**
 * https://www.codewars.com/kata/nut-farm-2/
 */
public class NutFarm2 {

    public static int[] shakeTree(final char[][] tree) {
        int[] result = new int[tree[0].length];
        for (int i = 0; i < tree.length; i++) {
            for (int j = 0; j < tree[0].length; j++) {
                if (tree[i][j] == 'o') {
                    int l = findFallingLocation(tree, i, j);
                    if (l != -1) {
                        result[l] += 1;
                    }
                }
            }
        }
        return result;
    }

    private static int findFallingLocation(char[][] tree, int i, int j) {
        int location = -1;
        int row = tree.length;
        int col = tree[0].length;
        int x = i;
        int y = j;
        while (true) {
            if (x + 1 == row) {
                location = y;
                break;
            }
            if (tree[x][y] == '\\') {
                if (y + 1 == col || tree[x][y + 1] == '/') {
                    break;
                }
                y = y + 1;
            } else if (tree[x][y] == '/') {
                if (y - 1 == -1 || tree[x][y - 1] == '\\') {
                    break;
                }
                y = y - 1;
            } else {
                x = x + 1;
            }
        }
        return location;
    }
}
