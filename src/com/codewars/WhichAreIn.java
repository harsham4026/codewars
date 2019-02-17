package com.codewars;

import java.util.Set;
import java.util.TreeSet;

/**
 * https://www.codewars.com/kata/which-are-in/train/java
 */
public class WhichAreIn {
    public static String[] inArray(String[] array1, String[] array2) {
        Set<String> result = new TreeSet<>();
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array2.length; j++) {
                if (array2[j].contains(array1[i])) {
                    result.add(array1[i]);
                    break;
                }
            }
        }

        return result.toArray(new String[0]);
    }
}