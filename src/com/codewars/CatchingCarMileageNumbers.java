package com.codewars;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * https://www.codewars.com/kata/catching-car-mileage-numbers/
 */
public class CatchingCarMileageNumbers {
    public static int isInteresting(int number, int[] awesomePhrases) {
        if (number + 2 <= 99) {
            //"A number is only interesting if it is greater than 99!"
            //We have to take into account the ones in next 2 miles
            return 0;
        }

        if (number > 99 && isInterestingNum(number, awesomePhrases)) {
            return 2;
        } else if ((number >= 99 && isInterestingNum(number + 1, awesomePhrases)) ||
                (number >= 98 && isInterestingNum(number + 2, awesomePhrases))) {
            //occurs within the next two miles
            return 1;
        }
        return 0;
    }

    private static boolean isInterestingNum(int number, int[] awesomePhrases) {
        //Check in awesomePhrases
        for (int awesomeNum : awesomePhrases) {
            if (number == awesomeNum) {
                return true;
            }
        }
        int[] numberParts = getParts(number);

        //Any digit followed by all zeros
        if (Arrays.stream(numberParts)
                .skip(1)
                .allMatch(e -> e == 0)) {
            return true;
        }
        //Every digit is the same number
        if (Arrays.stream(numberParts)
                .distinct()
                .count() == 1) {
            return true;
        }
        //The digits are sequential, incrementing
        boolean isIncrementing = true;
        for (int i = 0; i < numberParts.length - 1; i++) {
            if ((numberParts[i] + 1) % 10 != numberParts[i + 1] || numberParts[i] == 0) {
                isIncrementing = false;
                break;
            }
        }
        if (isIncrementing) {
            return true;
        }

        //The digits are sequential, decrementing
        boolean isDecrementing = true;
        for (int i = 0; i < numberParts.length - 1; i++) {
            if (numberParts[i] - 1 != numberParts[i + 1] || numberParts[i] == 0) {
                isDecrementing = false;
                break;
            }
        }
        if (isDecrementing) {
            return true;
        }
        //palindrome
        if (isPalin(numberParts)) {
            return true;
        }
        return false;
    }

    private static int[] getParts(int n) {
        List<Integer> parts = new ArrayList<>();
        while (n > 0) {
            parts.add(n % 10);
            n /= 10;
        }
        Collections.reverse(parts);
        return parts.stream()
                .mapToInt(i -> i)
                .toArray();
    }

    private static boolean isPalin(int[] numberParts) {
        int i = 0, j = numberParts.length - 1;
        while (i < j) {
            if (numberParts[i] != numberParts[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;

    }
}