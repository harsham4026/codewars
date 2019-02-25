package com.codewars;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * https://www.codewars.com/kata/camelcase-method/
 */
public class CamelCaseMethod {
    public static String camelCase(String str) {
        if (str.isEmpty()) {
            return str;
        }
        return Arrays.stream(str.trim().split("\\s+"))
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .collect(Collectors.joining());
    }
}
