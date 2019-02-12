package com.codewars;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.function.Function;

/**
 * https://www.codewars.com/kata/are-they-the-same/train/java
 */
public class AreSame {

    public static boolean comp(int[] a, int[] b) {
        if (a == null || b == null || a.length != b.length) {
            return false;
        }
        Map<Integer, Integer> squaresCount = Arrays.stream(a)
                .mapToObj(e -> e * e)
                .collect(Collectors.toMap(Function.identity(), e -> 1, (p, c) -> p + 1));

        for (int i = 0; i < b.length; i++) {
            if (!squaresCount.containsKey(b[i])) {
                return false;
            }
            int current = squaresCount.get(b[i]);
            if (current == 1) {
                squaresCount.remove(b[i]);
            } else {
                squaresCount.put(b[i], current - 1);
            }
        }
        return true;
    }
    //Stream answer
    public static boolean compStrama(final int[] a, final int[] b) {
        return a != null && b != null && a.length == b.length
                && Arrays.equals(Arrays.stream(a).map(i -> i * i).sorted().toArray(), Arrays.stream(b).sorted().toArray());
    }
}