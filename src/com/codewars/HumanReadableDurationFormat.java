package com.codewars;

import java.util.Arrays;
import java.util.Map;
import java.util.LinkedHashMap;

/**
 * https://www.codewars.com/kata/human-readable-duration-format/
 */
public class HumanReadableDurationFormat {
    private static final int NUM_SECS_MIN = 60;
    private static final int NUM_SECS_HOURS = 60 * 60;
    private static final int NUM_SECS_DAYS = 24 * 60 * 60;
    private static final int NUM_SECS_YEARS = 365 * 24 * 60 * 60;

    public static String formatDuration(int seconds) {
        if (seconds == 0) {
            return "now";
        }
        int remainingSeconds = seconds;
        int years = remainingSeconds / NUM_SECS_YEARS;
        remainingSeconds = remainingSeconds % NUM_SECS_YEARS;

        int days = remainingSeconds / NUM_SECS_DAYS;
        remainingSeconds = remainingSeconds % NUM_SECS_DAYS;

        int hours = remainingSeconds / NUM_SECS_HOURS;
        remainingSeconds = remainingSeconds % NUM_SECS_HOURS;

        int minutes = remainingSeconds / NUM_SECS_MIN;
        remainingSeconds = remainingSeconds % NUM_SECS_MIN;

        long numOfElements = getNumberOfNonZeros(years, days, hours, minutes, remainingSeconds);

        StringBuilder builder = new StringBuilder();
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("year", years);
        map.put("day", days);
        map.put("hour", hours);
        map.put("minute", minutes);
        map.put("second", remainingSeconds);

        map.entrySet().stream()
                .filter(entry -> entry.getValue() != 0)
                .findFirst()
                .ifPresent(entry -> builder.append(buildDescription(entry.getValue(), entry.getKey())));

        long remainingElementsToBeProcessed = numOfElements - 1;
        map.entrySet().stream()
                .filter(entry -> entry.getValue() != 0)
                .skip(1)
                .limit(Math.max(0, remainingElementsToBeProcessed - 1))
                .forEach(entry -> builder.append(", ").append(buildDescription(entry.getValue(), entry.getKey())));

        //get last
        if (remainingElementsToBeProcessed > 0) {
            map.entrySet().stream()
                    .filter(entry -> entry.getValue() != 0)
                    .skip(remainingElementsToBeProcessed)
                    .forEach(entry -> builder.append(" and ").append(buildDescription(entry.getValue(), entry.getKey())));
        }

        return builder.toString();
    }

    private static long getNumberOfNonZeros(int... nums) {
        return Arrays.stream(nums)
                .filter(n -> n != 0)
                .count();
    }

    private static String buildDescription(int value, String unit) {
        return String.valueOf(value) + " " + unit + (value > 1 ? "s" : "");
    }

}