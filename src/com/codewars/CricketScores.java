package com.codewars;


import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * https://www.codewars.com/kata/cricket-scores/train/java
 */
public class CricketScores {
    public static String scoreCard(final String balls, final List<String> batsmen) {
        Integer[] scores = new Integer[11];
        int strikerIndex = 0;
        int nonStrikerIndex = 1;

        int ballCount = 0;
        scores[strikerIndex] = 0;
        scores[nonStrikerIndex] = 0;

        for (char c : balls.toCharArray()) {
            Integer runs = null;
            if (c == 'w') {
                strikerIndex = Math.max(strikerIndex, nonStrikerIndex) + 1;
                if (strikerIndex < batsmen.size()) {
                    scores[strikerIndex] = 0;
                }
            } else if (c != '.') {
                runs = c - '0';
                scores[strikerIndex] += runs;
            }

            if (runs != null && runs % 2 == 1) { //switch ends
                Integer t = strikerIndex;
                strikerIndex = nonStrikerIndex;
                nonStrikerIndex = t;
            }
            ballCount++;
            if (ballCount == 6) { //switch ends
                Integer t = strikerIndex;
                strikerIndex = nonStrikerIndex;
                nonStrikerIndex = t;
                ballCount = 0;
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < batsmen.size(); i++) {
            builder.append(batsmen.get(i))
                    .append(" ")
                    .append(scores[i] == null ? "  -" : String.format("%3s", scores[i]));

            if (scores[i] != null && (i == strikerIndex || i == nonStrikerIndex)) {
                builder.append(" not out");
            }
            builder.append("\n");
        }
        int sum = Arrays.stream(scores)
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .sum();

        builder.append("TOTAL ")
                .append(sum);
        String s = builder.toString();
        //System.out.println(s);
        return s;
    }
}