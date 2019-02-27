package com.codewars;

import java.util.Arrays;

/**
 * https://www.codewars.com/kata/split-and-then-add-both-sides-of-an-array-together/
 */
public class SplitAndAddArray {
    public static int[] splitAndAdd(int[] numbers, int n) {
        int mainArr[] = Arrays.copyOf(numbers, numbers.length);

        while (n-- > 0 && mainArr.length > 1) {
            int length = mainArr.length;

            int[] arr1 = new int[length / 2];
            int[] arr2 = new int[(length + 1) / 2];
            System.arraycopy(mainArr, 0, arr1, 0, length / 2);
            System.arraycopy(mainArr, length / 2, arr2, 0, (length + 1) / 2);

            mainArr = new int[Math.max(arr1.length, arr2.length)];
            int idx = mainArr.length - 1;
            int i = arr1.length - 1;
            int j = arr2.length - 1;
            while (i >= 0 && j >= 0) {
                mainArr[idx--] = arr1[i--] + arr2[j--];
            }
            if (j == 0) {
                mainArr[idx--] = arr2[j];
            }
        }

        return mainArr;
    }
}