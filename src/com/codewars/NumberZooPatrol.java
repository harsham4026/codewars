package com.codewars;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * https://www.codewars.com/kata/number-zoo-patrol/
 */
public class NumberZooPatrol {

    public static int findMissingNumber(int[] numbers) {
        long sum = 0;
        long max = 1;
        for (int number : numbers) {
            sum += number;
            max = Math.max(max, number);
        }
        long sumOfN = (max * (max + 1)) / 2L;
        return (int) (sumOfN == sum ? max + 1 : sumOfN - sum);
    }

    public static int findMissingNumber1(int[] numbers) {
        return IntStream.rangeClosed(1,numbers.length+1).sum() - Arrays.stream(numbers).sum();
    }
}