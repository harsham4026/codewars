package com.codewars;

import java.util.stream.LongStream;

/**
 * https://www.codewars.com/kata/buddy-pairs/train/java
 */
public class BuddyPairs {

    public static String buddy(long start, long limit) {
        for (long i = start; i <= limit; i++) {
            long sum = getSumOfProperDivisors(i);
            if ((sum - 1) > i + 1 && getSumOfProperDivisors(sum - 1) == i + 1) {
                return "(" + i + " " + (sum - 1) + ")";
            }
        }
        return "Nothing";
    }

    private static long getSumOfProperDivisors(long num) {
        return LongStream.rangeClosed(2, (long) Math.sqrt(num))
                .filter(i -> num % i == 0)
                .map(i -> {
                    if (num / i == i) {
                        return i;
                    }
                    return i + (num / i);
                })
                .sum() + 1; //Include 1
    }

    private static long getSumOfProperDivisorsSubOpt(long num) {
        return LongStream.rangeClosed(1, num / 2)
                .peek(e -> {
                    if (num == 12) System.out.println(e);
                })
                .filter(i -> num % i == 0)
                .sum();
    }
}