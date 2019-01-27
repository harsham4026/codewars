package com.codewars;

import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;
/**
 * https://www.codewars.com/kata/weight-for-weight
 */
public class WeightForWeight {
    public static String orderWeight(String strng) {
        String input = strng.trim();
        List<String> inputs = Arrays.stream(input.split(" "))
                .collect(Collectors.toList());

        //Sort by integer sum. If equal sort as if they are Strings
        inputs.sort(Comparator.comparingLong(WeightForWeight::getSumOfDigits)
                .thenComparing(Comparator.naturalOrder()));


        return inputs.stream()
                .collect(Collectors.joining(" "));
    }

    //Using long as some tests had numbers above int range
    private static long getSumOfDigits(String s) {
        long inputNum = Long.parseLong(s);
        long res = 0;
        while (inputNum > 0) {
            res = res + (inputNum % 10);
            inputNum /= 10;
        }
        return res;
    }

}