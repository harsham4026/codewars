package com.codewars;

import java.util.List;
import java.util.ArrayList;

/**
 * https://www.codewars.com/kata/double-cola/java
 */
public class DoubleCola {
    public static String WhoIsNext(String[] names, int n) {
        int max = 1000000000;
        int r = 0;
        int start = 1;
        int end = 1;

        List<Bucket> buckets = new ArrayList<>();
        while (end < max) {
            int power = (int) Math.pow(2, r);
            end = start + (power * 5) - 1;
            buckets.add(new Bucket(start, end, power));
            r++;
            start = end + 1;
        }

        Bucket currentBucket = null;
        for (Bucket bucket : buckets) {
            if (bucket.getStart() <= n && bucket.getEnd() >= n) {
                currentBucket = bucket;
                break;
            }
        }

        //start.. end becomes 0..(end-start)
        return names[(n - currentBucket.getStart()) / currentBucket.getPower()];
    }

    static class Bucket {
        private int start;
        private int end;
        private int power;

        Bucket(int start, int end, int power) {
            this.start = start;
            this.end = end;
            this.power = power;
        }

        int getStart() {
            return start;
        }

        int getEnd() {
            return end;
        }

        int getPower() {
            return power;
        }
    }
}