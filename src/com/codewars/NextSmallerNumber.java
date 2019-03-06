package com.codewars;

import java.util.stream.IntStream;
import java.util.Comparator;

/**
 * https://www.codewars.com/kata/next-smaller-number-with-the-same-digits/
 * @See NextBiggerNumber
 */
public class NextSmallerNumber {
    public static long nextSmaller(long n) {
        String s = Long.toString(n);

        int i = 0, j;
        int index = -1;
        for (i = s.length() - 2; i >= 0; i--) {
            char curr = s.charAt(i);
            char greatestNumSmallerThanCurr = '0';
            for (j = i + 1; j < s.length(); j++) {
                if (s.charAt(j) < curr && s.charAt(j) >= greatestNumSmallerThanCurr) {
                    greatestNumSmallerThanCurr = s.charAt(j);
                    index = j;
                }
            }
            if (index != -1 && !(i == 0 && greatestNumSmallerThanCurr == '0')) { //First num should not be 0
                break;
            } else {
                index = -1;
            }
        }
        if (index == -1) {
            return -1;
        } else {
            // [0..i - 1][j][rev_sorted of i...n  except index j]
            StringBuilder resultBuilder = new StringBuilder();
            resultBuilder.append(s, 0, i)
                    .append(s.charAt(index));

            int jIndex = index;
            IntStream.range(i, s.length())
                    .filter(idx -> idx != jIndex)
                    .mapToObj(s::charAt)
                    .sorted(Comparator.reverseOrder())
                    .forEach(resultBuilder::append);

            return Long.valueOf(resultBuilder.toString());
        }
    }
}