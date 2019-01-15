package com.codewars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * https://www.codewars.com/kata/prize-draw/train/java
 */
public class PrizeDraw {
    public static String nthRank(String st, Integer[] we, int n) {
        if (st.isEmpty()) {
            return "No participants";
        }
        if (n > we.length) {
            return "Not enough participants";
        }
        String [] names = st.split(",");
        List<NameAndWinningNumber> results = new ArrayList<>();

        for(int i = 0; i < names.length; i++) {
            String name = names[i];
            int winningNumber = findRank(name) * we[i];
            results.add(new NameAndWinningNumber(name, winningNumber));
        }

        results.sort(Comparator.comparingInt(NameAndWinningNumber::getWinningNumber)
                .reversed()
                .thenComparing(NameAndWinningNumber::getName));
        return results.get(n - 1)
                .getName();
    }
    private static int findRank(String name) {
        int rank = 0;
        for (Character c: name.toCharArray()) {
            if (Character.isUpperCase(c)) {
                rank = rank + (c - 64); //65 is 'A;
            } else {
                rank = rank + (c - 96); //97 is 'a'
            }
        }
        return rank + name.length();
    }

    private static class NameAndWinningNumber {
        private String name;
        private int winningNumber;

        NameAndWinningNumber(String name, int winningNumber) {
            this.name = name;
            this.winningNumber = winningNumber;
        }
        String getName() {
            return name;
        }
        int getWinningNumber() {
            return winningNumber;
        }
    }
}
