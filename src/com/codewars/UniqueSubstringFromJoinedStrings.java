package com.codewars;


import java.util.Set;
import java.util.HashSet;

/**
 * https://www.codewars.com/kata/unique-substring-from-joined-strings/
 */
public class UniqueSubstringFromJoinedStrings {

    static int longestSubstring(String a, String b) {
        Set<Character> aUniqueChars = new HashSet<>();
        Set<Character> bUniqueChars = new HashSet<>();
        Set<Character> uniqueChars = new HashSet<>();
        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();

        for (char c : aChars) {
            aUniqueChars.add(c);
        }
        for (char c : bChars) {
            bUniqueChars.add(c);
        }
        for (char c : bUniqueChars) {
            if (!aUniqueChars.contains(c)) {
                uniqueChars.add(c);
            }
        }
        for (char c : aUniqueChars) {
            if (!bUniqueChars.contains(c)) {
                uniqueChars.add(c);
            }
        }
        return Math.max(process(aChars, bChars, uniqueChars), process(bChars, aChars, uniqueChars));
    }

    private static int process(char[] a1, char[] a2, Set<Character> uniqueChars) {
        int countSoFar = 0, max = 0;
        for (char c : a1) {
            if (uniqueChars.contains(c)) {
                countSoFar++;
            } else {
                max = Math.max(max, countSoFar);
                countSoFar = 0;
            }
        }

        for (char c : a2) {
            if (uniqueChars.contains(c)) {
                countSoFar++;
            } else {
                max = Math.max(max, countSoFar);
                countSoFar = 0;
            }
        }
        return Math.max(max, countSoFar);
    }
}