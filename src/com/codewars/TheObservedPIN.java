package com.codewars;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

/**
 * https://www.codewars.com/kata/the-observed-pin/
 */
public class TheObservedPIN {
    private static Map<Character, List<String>> map;

    static {
        map = new HashMap<>();
        map.put('1', Arrays.asList("1", "2", "4"));
        map.put('2', Arrays.asList("2", "1", "3", "5"));
        map.put('3', Arrays.asList("3", "2", "6"));
        map.put('4', Arrays.asList("4", "1", "5", "7"));
        map.put('5', Arrays.asList("5", "2", "4", "6", "8"));
        map.put('6', Arrays.asList("6", "3", "5", "9"));
        map.put('7', Arrays.asList("7", "4", "8"));
        map.put('8', Arrays.asList("8", "5", "7", "9", "0"));
        map.put('9', Arrays.asList("9", "6", "8"));
        map.put('0', Arrays.asList("0", "8"));
    }

    public static List<String> getPINs(String observed) {
        List<String> result = new ArrayList<>();
        construct(new StringBuilder(observed), 0, result);
        return result;
    }

    private static void construct(StringBuilder sb, int index, List<String> result) {
        if (index == sb.length()) {
            result.add(sb.toString());
            return;
        }
        char c = sb.charAt(index);
        map.get(c)
                .stream()
                .map(s -> sb.replace(index, index + 1, s))
                .forEach(sBuilder -> {
                    construct(new StringBuilder(sBuilder.toString()), index + 1, result);
                });
    }
}
