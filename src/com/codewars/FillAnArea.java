package com.codewars;

import java.awt.Point;
import java.util.Arrays;

/**
 * https://www.codewars.com/kata/fill-in-an-area/
 */
public class FillAnArea {
    private int[] xCoor = new int[]{-1, 1, 0, 0};
    private int[] yCoor = new int[]{0, 0, -1, 1};

    public int[][] fillArea(int[][] input, Point start) {
        int row = input.length;
        int column = input[0].length;
        //Flipped in question (also starts from bottom)
        int x = input.length - start.y - 1;
        int y = start.x;

        int[][] result = new int[row][column];
        for (int i = 0; i < row; i++) {
            result[i] = Arrays.copyOf(input[i], column);
        }

        boolean res = dfs(result, x, y);
        if (!res) {
            //return all 1s
            result = new int[row][column];
            for (int i = 0; i < row; i++) {
                Arrays.fill(result[i], 1);
            }
        }
        return result;
    }

    private boolean dfs(int[][] result, int x, int y) {
        if (result[x][y] == 1) {
            return true;
        }
        result[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            int newX = x + xCoor[i];
            int newY = y + yCoor[i];
            if (newX == -1 || newX == result.length || newY == -1 || newY == result[0].length) {
                //wraps around
                return false;
            }
            boolean res = dfs(result, newX, newY);
            if (!res) {
                return res;
            }
        }
        return true;
    }
}