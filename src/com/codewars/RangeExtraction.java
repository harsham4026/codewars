package com.codewars;

/**
 * https://www.codewars.com/kata/range-extraction/
 */
public class RangeExtraction {

    public static String rangeExtraction(int[] arr) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            int startIndex = i;
            int count = 1;
            while (i < arr.length - 1 && arr[i] + 1 == arr[i + 1]) {
                count++;
                i++;
            }
            if (count > 2) {
                builder.append(String.valueOf(arr[startIndex]))
                        .append("-")
                        .append(String.valueOf(arr[i]))
                        .append(",");
            } else {
                builder.append(String.valueOf(arr[startIndex]));
                if (startIndex == i - 1) {
                    builder.append(",");
                    builder.append(String.valueOf(arr[i]));
                }
                builder.append(",");
            }
        }

        return builder.substring(0, builder.length() - 1);
    }
}