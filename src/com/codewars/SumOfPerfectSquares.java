package com.codewars;

/**
 * https://www.codewars.com/kata/sums-of-perfect-squares/
 */
public class SumOfPerfectSquares {
    public static int nSquaresFor(int n) {
        int num = n;
        if (isPerfectSquare(num)) {
            return 1;
        }

        //Can it be represented as 4^k(8m + 7)
        while (num % 4 == 0) {
            num = num / 4;
        }
        if (num % 8 == 7) {
            return 4;
        }

        //Can it be represented as i * i + j * j
        for (int i = 1; i <= Math.sqrt(num); i++) {
            if (isPerfectSquare(num - i * i)) {
                return 2;
            }
        }
        return 3;

    }

    private static boolean isPerfectSquare(int num) {
        int sqrt = (int) Math.sqrt(num);
        return sqrt * sqrt == num;
    }
}
