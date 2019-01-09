package com.codewars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

/**
 * https://www.codewars.com/kata/length-of-missing-array/train/java
 */
public class LengthOfMissingArray {

    public static int getLengthOfMissingArray(Object[][] arrayOfArrays) {
        if (arrayOfArrays == null || arrayOfArrays.length == 0) {
            return 0;
        }
        List<Integer> sizes = new ArrayList<>();
        for (Object[] innerObject : arrayOfArrays) {
            if (innerObject == null || innerObject.length == 0) {
                return 0;
            }
            sizes.add(innerObject.length);
        }

        Collections.sort(sizes);
        for (int i = 1; i < sizes.size(); i++) {
            if (sizes.get(i) != sizes.get(i - 1) + 1) {
                return sizes.get(i) - 1;
            }
        }
        return 0; //Can't happen
    }

    //Solution using Streams
    public static int getLengthOfMissingArrayUsingStreams(Object[][] arrayOfArrays) {
        if (arrayOfArrays == null || arrayOfArrays.length == 0) {
            return 0;
        }

        Object[][] sortedArrayOfArrays = Arrays.stream(arrayOfArrays)
                .sorted(Comparator.nullsFirst(Comparator.comparingInt(innerObject -> innerObject.length)))
                .toArray(Object[][]::new);
        if (sortedArrayOfArrays[0] == null || sortedArrayOfArrays[0].length == 0) {
            return 0;
        }

        return IntStream.range(1, sortedArrayOfArrays.length)
                .filter(i -> sortedArrayOfArrays[i].length != sortedArrayOfArrays[i - 1].length + 1)
                .map(i -> sortedArrayOfArrays[i].length - 1)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cannot happen"));
    }
}
