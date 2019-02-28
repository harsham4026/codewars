package com.codewars;

import java.util.stream.Stream;
import java.util.stream.Collectors;

/**
 * https://www.codewars.com/kata/collatz/train/java
 */
public class Collatz {
    public static String collatz(int n) {
        String tmp = Stream.iterate(n, i -> i % 2 == 0 ? i / 2 : (3 * i) + 1)
                .takeWhile(i -> i != 1)
                .map(String::valueOf)
                .collect(Collectors.joining("->"));
        return tmp.length() > 0 ? tmp + "->1" : "1";
    }
}