package com.codewars;

/**
 * https://www.codewars.com/kata/how-much/train/java
 */
public class HowMuch {
    public static String howmuch(int m, int n) {
        StringBuilder builder = new StringBuilder();
        int start = Math.min(m, n);
        int end = Math.max(m, n);
        builder.append("[");
        for (int i = start ; i <= end; i++) {
            if ((i - 1) % 9 == 0 && (i - 2) % 7 == 0) {
                builder.append("[")
                        .append("M: " + i + " ")
                        .append("B: " + (i - 2) / 7 + " ")
                        .append("C: " + (i - 1) / 9)
                        .append("]");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
