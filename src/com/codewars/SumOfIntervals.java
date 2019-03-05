package com.codewars;

import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Comparator;

/**
 * https://www.codewars.com/kata/sum-of-intervals/
 */
public class SumOfIntervals {
    public static int sumIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        int[][] sortedIntervals = Arrays.stream(intervals)
                .map(arr -> Arrays.copyOf(arr, arr.length))
                .sorted(Comparator.comparingInt((int[] arr) -> arr[0])
                        .thenComparing(arr -> arr[1]))
                .toArray(int[][]::new);
        Queue<int[]> queue = new LinkedList<>();
        int length = 0;
        queue.add(sortedIntervals[0]);
        int i = 1;

        while (!queue.isEmpty() && i < sortedIntervals.length) {
            int[] top = queue.element();
            int[] current = sortedIntervals[i++];
            if (current[0] <= top[1] && current[1] >= top[1]) {
                queue.remove();
                queue.add(new int[]{top[0], current[1]});
            } else if (current[0] > top[1]) {
                queue.remove();
                queue.add(current);
                length += (top[1] - top[0]);
            } //else - if current is nested ignore
        }
        if (!queue.isEmpty()) {
            int[] top = queue.poll();
            length += (top[1] - top[0]);
        }
        return length;
    }
}