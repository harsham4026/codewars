package com.codewars;

import java.util.stream.IntStream;

/**
 * https://www.codewars.com/kata/string-%3E-n-iterations-%3E-string
 */
public class StringNIterationsString {
    public static String jumbledString(String s, long n) {
        StringBuilder builder = new StringBuilder();
        if (s.isEmpty()) {
            return s;
        }

        int originalLength = s.length();
        Character lastChar = null;
        int numOfElementsToWork = originalLength - 1;
        int length;

        //Treat it as odd length and add the last char at the end
        if (originalLength % 2 == 0) {
            lastChar = s.charAt(originalLength - 1);
            numOfElementsToWork--;
            length = originalLength - 1;
        } else {
            length = originalLength;
        }
        builder.append(s.charAt(0));
        int power = power(2, n, length);
        if (power == 0)
            System.out.println(power + " " + n + " " + length);
        int start = power == 0 ? 1 : power;

        //Second element to be taken from (2 ^ n) % length
      /*
       For rest of the indexes (i), it is
           (value_at_i-1 + start) % length
      */


        IntStream.iterate(start, i -> (start + i) % length)
                .limit(numOfElementsToWork)
                .forEach(index -> builder.append(s.charAt(index)));

        if (lastChar != null) {
            builder.append(lastChar);
        }
        return builder.toString();
    }

    //Modular exponentiation
    //(x^y)%p
    private static int power(long x, long y, long p) {
        long res = 1;
        x = x % p;
        while (y > 0) {
            if ((y & 1L) == 1) {
                res = (res * x) % p;
            }
            y = y >> 1L; // y = y/2
            x = (x * x) % p;
        }
        return (int) res;
    }
}