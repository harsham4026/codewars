package com.codewars;

import java.util.function.BiFunction;

/**
 * https://www.codewars.com/kata/reducing-by-steps
 */
public class ReducingBySteps {
    public static long gcdi(long x, long y) {
        if (y == 0) {
            return x;
        }
        return Math.abs(gcdi(y, x % y));
    }
    public static long lcmu(long a, long b) {
        return Math.abs((a * b)) / gcdi(a, b);
    }
    public static long som(long a, long b) {
        return a + b;
    }
    public static long maxi(long a, long b) {
        return Math.max(a, b);
    }
    public static long mini(long a, long b) {
        return Math.min(a, b);
    }
    public static long[] operArray(BiFunction<Long, Long, Long> operator, long[] arr, long init) {
        long[] result = new long[arr.length];
        long prev = init;
        for (int i = 0; i < arr.length; i++) {
            result[i] = operator.apply(prev, arr[i]);
            prev = result[i];
        }
        return result;

    }
}
