package com.codewars;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * https://www.codewars.com/kata/pick-peaks/train/java
 */
public class PickPeaks {
    public static Map<String, List<Integer>> getPeaks(int[] arr) {
        Map<String, List<Integer>> result = new HashMap<>();
        if (arr.length == 0) {
            result.put("pos", new ArrayList<>());
            result.put("peaks", new ArrayList<>());
            return result;
        }

        List<Integer> pos = new ArrayList<>();
        List<Integer> peaks = new ArrayList<>();

        int index = 0; //Plateau begin index - 1
        int lastSeenDiffValue = arr[0]; //Before plateau
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1] && arr[i] > lastSeenDiffValue) {
                peaks.add(arr[i]);
                if (arr[i] == arr[i - 1]) {//plateau
                    pos.add(index + 1);
                } else {
                    pos.add(i);
                }
            }
            if (arr[i] != arr[i + 1]) {
                lastSeenDiffValue = arr[i];
                index = i;
            }
        }
        result.put("pos", pos);
        result.put("peaks", peaks);
        return result;
    }
}