package com.codewars;


import java.util.List;
import java.util.ArrayList;


/**
 * https://www.codewars.com/kata/how-many-numbers-iii/
 */
public class HowManyNumbers3 {
    public static List<Long> findAll(final int sumDigits, final int numDigits) {
        List<Long> results = new ArrayList<>();
        Result result = new Result();
        recur(0L, numDigits, 1, sumDigits, result);
        if (result.count > 0) {
            results.add(result.count);
            results.add(result.min);
            results.add(result.max);
        }
        return results;
    }

    private static class Result {
        Long count = 0L;
        Long min = Long.MAX_VALUE;
        Long max = Long.MIN_VALUE;
    }

    private static void recur(Long number, int digits, int start, int runningSum, Result result) {
        if (digits == 0) {
            if (runningSum == 0) {
                result.count++;
                result.min = Math.min(result.min, number);
                result.max = Math.max(result.max, number);
            }
        } else {
            for (int i = start; i <= 9; i++) {
                recur((number * 10) + i, digits - 1, i, runningSum - i, result);
            }
        }
    }
}