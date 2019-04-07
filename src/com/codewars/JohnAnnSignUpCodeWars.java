package com.codewars;

import java.util.List;
import java.util.ArrayList;

/**
 * https://www.codewars.com/kata/john-and-ann-sign-up-for-codewars/
 */
public class JohnAnnSignUpCodeWars {
    private static List<Long> john = new ArrayList<>();
    private static List<Long> joe = new ArrayList<>();
    private static List<Integer> johnI = new ArrayList<>();
    private static List<Integer> joeI = new ArrayList<>();

    private static List<Long> sumJohn = new ArrayList<>();
    private static List<Long> sumJoe = new ArrayList<>();

    static {
        john.add(0L);
        joe.add(1L);
        johnI.add(0);
        joeI.add(1);
        sumJohn.add(0L);
        sumJoe.add(1L);
        for (int i = 1; i < 1000000; i++) {
            johnI.add(i - joeI.get(johnI.get(i - 1)));
            joeI.add(i - johnI.get(joeI.get(i - 1)));
            john.add((long) johnI.get(i));
            joe.add((long) joeI.get(i));

            sumJohn.add(sumJohn.get(i - 1) + john.get(i));
            sumJoe.add(sumJoe.get(i - 1) + joe.get(i));
        }
    }

    public static List<Long> john(long n) {
        return john.subList(0, (int) n);
    }

    public static List<Long> ann(long n) {
        return joe.subList(0, (int) n);
    }

    public static long sumJohn(long n) {
        return sumJohn.get((int) n - 1);
    }

    public static long sumAnn(long n) {
        return sumJoe.get((int) n - 1);
    }
}