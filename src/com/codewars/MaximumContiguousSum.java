package com.codewars;

/**
 * https://www.codewars.com/kata/maximum-contiguous-sum/
 */
public class MaximumContiguousSum {
    public static int maxContiguousSum(final int[] arr) {
        int max = 0, maxSoFar = 0;
        for (int ele : arr) {
            maxSoFar += ele;
            /*if (maxSoFar < 0) {
                maxSoFar = 0;
            }
            if (maxSoFar > max) {
                max = maxSoFar;
            }*/
            maxSoFar = Math.max(maxSoFar, 0);
            max = Math.max(max, maxSoFar);

        }
        return max;
    }
}