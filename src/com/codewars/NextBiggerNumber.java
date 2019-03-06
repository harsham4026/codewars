package com.codewars;

import java.util.stream.IntStream;

/**
 * https://www.codewars.com/kata/next-bigger-number-with-the-same-digits/
 */
public class NextBiggerNumber {
    public static long nextBiggerNumber(long n) {
        System.out.println(n);
        String s = Long.toString(n);

        int i = 0, j;
        int index = -1;
        for (i = s.length() - 2; i >= 0; i--) {
            char curr = s.charAt(i);
            char smallestNumGreaterThanCurr = '9';
            for (j = i + 1; j < s.length(); j++) {
                if (s.charAt(j) > curr && s.charAt(j) <= smallestNumGreaterThanCurr) {
                    smallestNumGreaterThanCurr = s.charAt(j);
                    index = j;
                }
            }
            if (index != -1) {
                break;
            }
        }
        if (index == -1) {
            return -1;
        } else {
            // [0..i - 1][j][sorted of i...n  except index j]
            StringBuilder resultBuilder = new StringBuilder();
            resultBuilder.append(s, 0, i)
                    .append(s.charAt(index));

            int jIndex = index;
            IntStream.range(i, s.length())
                    .filter(idx -> idx != jIndex)
                    .mapToObj(s::charAt)
                    .sorted()
                    .forEach(resultBuilder::append);

            return Long.valueOf(resultBuilder.toString());
        }
    }

}