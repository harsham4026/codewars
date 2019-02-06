package com.codewars;

public class LargestRectangleInBackground {
    private final int[] values;

    public LargestRectangleInBackground(int... values) {
        this.values = values;
    }

    public long largestRect() {
        long result = 0;
        int i, j;
        for (i = 0; i < values.length; i++) {
            long temp = values[i];
            j = i - 1;
            while (j >= 0 && values[j] >= values[i]) {
                temp += values[i];
                j--;
            }

            j = i + 1;
            while (j < values.length && values[j] >= values[i]) {
                temp += values[i];
                j++;
            }
            result = Math.max(result, temp);
        }
        return result;
    }
}