package com.codewars;


/**
 * https://www.codewars.com/kata/pattern-generator
 */
public class PatternGenerator {
    public static String patternGenerator(int c) {
        if (c < 1) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        StringBuilder currentSequence = new StringBuilder();

        char[] chars = new char[]{'x', 'o'};
        int index = 1;

        for (int i = 0; i < c - 1; i++) {
            int spaceCount;
            if (i % 2 == 0) {
                index = (index + 1) % 2;
                if (i != 0) {
                    currentSequence.insert(0, " ");
                }
                currentSequence.insert(0, chars[index]);
                spaceCount = c - 1;
            } else {
                spaceCount = c;
            }
            for (int j = 0; j < spaceCount; j++) {
                builder.append(" ");
            }
            builder.append(currentSequence)
                    .append("\n");
        }

        if (c % 2 == 1) {
            index = (index + 1) % 2;
            if (c != 1) {
                currentSequence.insert(0, " ");
            }
            currentSequence.insert(0, chars[index]);
        }
        StringBuilder reversed = new StringBuilder(currentSequence).reverse();
        if (c > 1) {
            if (c % 2 == 0) {
                builder.append(reversed)
                        .append(" ")
                        .append(currentSequence).append("\n");
            } else {
                builder.append(reversed.substring(0, reversed.length() - 2))
                        .append(" ")
                        .append(currentSequence).append("\n");
            }
        } else {
            builder.append(currentSequence);
        }


        String[] p = builder.toString().split("\n");
        int ii = p.length - 2;

        for (int i = 1; i < c; i++) {
            for (int j = 0; j < i; j++) {
                builder.append(" ");
            }
            builder.append(new StringBuilder(p[ii--]).reverse())
                    .append("\n");
        }
        return builder.toString();
    }
}