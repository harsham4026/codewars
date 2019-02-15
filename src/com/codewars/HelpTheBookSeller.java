package com.codewars;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Map;

/**
 * https://www.codewars.com/kata/help-the-bookseller/train/java
 */
public class HelpTheBookSeller {
    // 1st parameter is the stocklist (L in example),
    // 2nd parameter is list of categories (M in example)
    public static String stockSummary(String[] lstOfArt, String[] lstOf1stLetter) {
        if (lstOfArt.length == 0 || lstOf1stLetter.length == 0) {
            return "";
        }
        Map<String, Integer> map = Arrays.stream(lstOfArt)
                .map(s -> s.split(" "))
                .collect(Collectors.toMap(arr -> String.valueOf(arr[0].charAt(0)), arr -> Integer.valueOf(arr[1]),
                        (p, c) -> p + c));

        return Arrays.stream(lstOf1stLetter)
                .map(s -> "(" + s + " : " + map.getOrDefault(s, 0) + ")")
                .collect(Collectors.joining(" - "));
    }
}
