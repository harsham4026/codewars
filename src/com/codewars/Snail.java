package com.codewars;

/**
 * https://www.codewars.com/kata/snail
 */
public class Snail {
    public static int[] snail(int[][] array) {
        if (array[0].length == 0) {
            return new int[0];
        }
        int [] result = new int[array.length * array.length];
        int index = 0;

        int k = 0;            //starting row
        int l = 0;            //starting column
        int m = array.length; //ending row
        int n = array.length; //ending column


        while (k < m && l < n) {
            for (int i = l; i < n; i++) {
                result[index++] = array[k][i];
            }
            k++;
            for (int i = k; i < m; i++) {
                result[index++] = array[i][n - 1];
            }
            n--;
            if (k < m) {
                for (int i = n - 1; i >= l; i--) {
                    result[index++] = array[m - 1][i];
                }
                m--;
            }

            if (l < n) {
                for (int i = m - 1; i >= k; i--) {
                    result[index++] = array[i][l];
                }
                l++;
            }
        }
        return result;
    }
}
