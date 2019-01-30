package com.codewars;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * https://www.codewars.com/kata/best-travel
 */
public class BestTravel {
    public static Integer chooseBestSum(int t, int k, List<Integer> ls) {
        List<Integer> list = ls.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        Holder holder = new Holder();
        recur(list, 0, 0, t, k, 0, holder);
        return holder.max == -1 ? null : holder.max;
    }
    static class Holder {
        int max = -1;
    }
    private static void recur(List<Integer> list, int i, int numElementsIncluded, int t, int k, int sum, Holder holder) {

        if (numElementsIncluded == k && sum <= t) {
            holder.max = Math.max(holder.max, sum);
        }
        if (i == list.size()) {
            return;
        }

        recur(list, i + 1, numElementsIncluded + 1, t, k, sum + list.get(i), holder);
        recur(list, i + 1, numElementsIncluded, t, k, sum, holder);
    }
}

