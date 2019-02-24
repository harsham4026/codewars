package com.codewars;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.Comparator;

/**
 * https://www.codewars.com/kata/closest-and-smallest/
 */
public class ClosestAndSmallest {
    public static int[][] closest(String strng) {
        if (strng.isEmpty()) {
            return new int[][]{};
        }
        String[] parts = strng.split(" ");
        List<NumWeight> numAndWeights = IntStream.range(0, parts.length)
                .mapToObj(i -> new NumWeight(parts[i], i))
                .collect(Collectors.toList());

        numAndWeights.sort(Comparator.comparing((NumWeight nw) -> nw.weight)
                .thenComparing(nw -> nw.originalIndex)); //Passes without this line too

        int index1 = 0, index2 = 1;
        int diff = Integer.MAX_VALUE;

        for (int i = 1; i < numAndWeights.size(); i++) {
            int currDiff = Math.abs(numAndWeights.get(i).weight - numAndWeights.get(i - 1).weight);
            if (currDiff < diff) {
                diff = currDiff;
                index1 = i - 1;
                index2 = i;
            }
        }
        NumWeight o1 = numAndWeights.get(index1);
        NumWeight o2 = numAndWeights.get(index2);
        return new int[][]{{o1.weight, o1.originalIndex, o1.num}, {o2.weight, o2.originalIndex, o2.num}};
    }

    private static class NumWeight {
        Integer num;
        Integer weight;
        int originalIndex;

        NumWeight(String s, int originalIndex) {
            this.num = Integer.parseInt(s);
            int t = num;
            this.weight = 0;

            while (t > 0) {
                this.weight += (t % 10);
                t /= 10;
            }
            this.originalIndex = originalIndex;
        }

    }
}