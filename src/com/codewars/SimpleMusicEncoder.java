package com.codewars;

import java.util.stream.IntStream;

/**
 * https://www.codewars.com/kata/a-simple-music-encoder
 */
public class SimpleMusicEncoder {
    public String compress(int[] raw) {
        StringBuilder builder = new StringBuilder();
        if (raw.length == 0) {
            return "";
        }
        int diff = 0;
        int start = raw[0];
        int startIndex = 0;
        for (int i = 1; i < raw.length; i++) {
            if (i == raw.length - 1) {
                process(raw, builder, raw[i - 1], raw[i], i, start, startIndex, raw[i] - raw[i - 1]);
                int consCount = i - startIndex + 1;
                if (raw[i] != raw[i - 1] && consCount < 3) {  //Inverse check of below. New seq cannot start from current index
                    builder.append(raw[i]).append(",");
                }
                break;
            }
            if (raw[i] - raw[i - 1] == raw[i + 1] - raw[i]) {
                diff = raw[i] - raw[i - 1];
            } else {
                process(raw, builder, raw[i - 1], raw[i], i, start, startIndex, diff);

                int consCount = i - startIndex + 1;
                if (raw[i] == raw[i - 1] || consCount >= 3) { //Streak ends at current index
                    start = raw[i + 1];
                    startIndex = i + 1;
                    i++;//Skip and start from i+2 for comparison
                    if (i == raw.length - 1) { //end
                        builder.append(raw[i]).append(",");
                    }
                } else { //Streak ends at previous index
                    start = raw[i];
                    startIndex = i;
                }
            }
        }
        String s = builder.deleteCharAt(builder.length() - 1).toString();

        System.out.println(s);
        return s;
    }

    private static void process(int[] raw, StringBuilder builder, int previous, int current, int currentIndex,
                                int start, int startIndex, int diff) {
        int consCount = currentIndex - startIndex + 1;
        if (current == previous) {
            builder.append(previous).append("*").append(consCount).append(",");
        } else if (consCount >= 3) {
            //Streak ends at current index
            if (Math.abs(diff) == 1) {
                builder.append(start).append("-").append(current).append(",");
            } else {
                builder.append(start).append("-").append(current).append("/")
                        .append(Math.abs(diff))
                        .append(",");
            }
        } else {
            //Streak ends at previous index
            IntStream.range(startIndex, currentIndex)
                    .forEach(i -> builder.append(raw[i]).append(","));
        }
    }
}