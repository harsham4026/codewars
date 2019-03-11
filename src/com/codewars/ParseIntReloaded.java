package com.codewars;

import rx.Observable;

import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

/**
 * https://www.codewars.com/kata/parseint-reloaded/
 */
public class ParseIntReloaded {
    private static Map<String, Integer> nums = new HashMap<>();
    private static Map<String, Integer> hundreds = new HashMap<>();

    static {
        nums.put("zero", 0);
        nums.put("one", 1);
        nums.put("two", 2);
        nums.put("three", 3);
        nums.put("four", 4);
        nums.put("five", 5);
        nums.put("six", 6);
        nums.put("seven", 7);
        nums.put("eight", 8);
        nums.put("nine", 9);
        nums.put("ten", 10);

        nums.put("eleven", 11);
        nums.put("twelve", 12);
        nums.put("thirteen", 13);
        nums.put("fourteen", 14);
        nums.put("fifteen", 15);
        nums.put("sixteen", 16);
        nums.put("seventeen", 17);
        nums.put("eighteen", 18);
        nums.put("nineteen", 19);

        nums.put("twenty", 20);
        nums.put("thirty", 30);
        nums.put("forty", 40);
        nums.put("fifty", 50);
        nums.put("sixty", 60);
        nums.put("seventy", 70);
        nums.put("eighty", 80);
        nums.put("ninety", 90);

        hundreds.put("hundred", 100);
        hundreds.put("thousand", 1000);
        hundreds.put("million", 1000000);
    }
    Observable

    public static int parseInt(String numStr) {
        int result = 0;
        System.out.println(numStr);
        int lastSeen = -1;
        for (String part : numStr.split(" ")) {
            if (part.equals("and")) {
                continue;
            }
            if (part.contains("-")) {
                lastSeen = Arrays.stream(part.split("-"))
                        .mapToInt(p -> nums.get(p))
                        .sum();
            } else if (hundreds.containsKey(part)) {
                if (part.equals("hundred") || part.equals("million")) {
                    result += (lastSeen == -1 ? 1 : lastSeen) * hundreds.get(part);
                } else if (part.equals("thousand")) { //apply 1000 to whole
                    result += (lastSeen == -1 ? 0 : lastSeen);
                    result *= hundreds.get(part);
                }
                lastSeen = -1;
            } else {
                lastSeen = nums.get(part);
            }
        }
        if (lastSeen != -1) {
            result += lastSeen;
        }
        return result;
    }
}