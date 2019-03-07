package com.codewars;

/**
 * https://www.codewars.com/kata/number-of-trailing-zeros-of-n
 */
public class NFactorialTrailingZeros {
    public static int zeros(int n) {
        int count = 0;
        for (int i = 5; n / i >= 1; i *= 5) {
            count += n / i;
        }
        return count;
    }
}
