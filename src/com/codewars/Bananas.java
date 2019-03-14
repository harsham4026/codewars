package com.codewars;

import java.util.Set;
import java.util.HashSet;

/**
 * https://www.codewars.com/kata/bananas/train/java
 */
public class Bananas {

    private static final String TARGET = "banana";

    static Set<String> bananas(final String s) {
        Set<String> res = new HashSet<>();
        recur(s, "", "", 0, 0, res);
        return res;
    }

    private static void recur(String s, String current, String currentWithoutHyphens, int i, int tgtIdx, Set<String> res) {
        if (i == s.length()) {
            if (TARGET.equals(currentWithoutHyphens)) {
                res.add(current);
            }
        } else {
            if (tgtIdx < TARGET.length() && TARGET.charAt(tgtIdx) == s.charAt(i)) {
                recur(s, current + s.charAt(i), currentWithoutHyphens + s.charAt(i), i + 1, tgtIdx + 1, res);
            }
            recur(s, current + "-", currentWithoutHyphens, i + 1, tgtIdx, res);
        }
    }
}