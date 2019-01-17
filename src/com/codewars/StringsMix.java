package com.codewars;

import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * https://www.codewars.com/kata/strings-mix
 */
public class StringsMix {
    public static String mix(String s1, String s2) {
        String result = "";
        Map<Character, Integer> counts1 = new HashMap<>();
        Map<Character, Integer> counts2 = new HashMap<>();

        populateCount(counts1, s1);
        populateCount(counts2, s2);

        StringBuilder tempBuilder = new StringBuilder();
        ;
        for (char c = 'a'; c <= 'z'; c++) {
            int c1 = counts1.getOrDefault(c, 0);
            int c2 = counts2.getOrDefault(c, 0);
            if (Math.max(c1, c2) > 1) {
                int max = Math.max(c1, c2);
                tempBuilder.append(c1 == c2 ? "=" : (c1 == max ? "1" : "2")); //if equal '=' or from s1 or s2
                tempBuilder.append(":");
                for (int i = 0; i < max; i++) {
                    tempBuilder.append(c);
                }
                tempBuilder.append("/");
            }
        }
        String temp = tempBuilder.length() > 0
                ? tempBuilder.substring(0, tempBuilder.length() - 1) //remove last '/'
                : "";

        return Arrays.stream(temp.split("/"))
                .sorted(Comparator.comparingInt((String s) -> s.split(":")[1].length())
                        .reversed()
                        .thenComparing(Comparator.naturalOrder()))
                .collect(Collectors.joining("/"));
    }

    private static void populateCount(Map<Character, Integer> counts, String s) {
        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                counts.put(c, counts.getOrDefault(c, 0) + 1);
            }
        }
    }
}