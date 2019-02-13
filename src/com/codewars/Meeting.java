package com.codewars;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

class Meeting {
    public static String meeting(String s) {
        return Arrays.stream(s.toUpperCase().split(";"))
                .sorted(Comparator.comparing((String str) -> str.split(":")[1])
                        .thenComparing(str -> str.split(":")[0]))
                .map(str -> {
                    String[] nameParts = str.split(":");
                    return "(" + nameParts[1] + ", " + nameParts[0] + ")";
                })
                .collect(Collectors.joining());
    }
}
