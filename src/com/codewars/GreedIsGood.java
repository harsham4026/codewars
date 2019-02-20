package com.codewars;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Map;
import java.util.HashMap;

public class GreedIsGood {
    private static final Map<Integer, Integer> diceScores = new HashMap<>();
    static {
        diceScores.put(1, 1000);
        diceScores.put(2, 200);
        diceScores.put(3, 300);
        diceScores.put(4, 400);
        diceScores.put(5, 500);
        diceScores.put(6, 600);
    }
    public static int greedy(int[] dice){
        Map<Integer, Integer> map = Arrays.stream(dice)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(i -> 1)));
        return map.entrySet()
                .stream()
                .mapToInt(entry -> processScore(entry.getKey(), entry.getValue()))
                .sum();
    }
    private static Integer processScore(Integer dice, Integer times) {
        int t = times;
        int result = 0;
        if (t >= 3) {
            result = diceScores.get(dice);
            t = Math.max(t - 3, 0);
        }
        int remaining = dice == 1 ? 100 * t : (dice == 5 ? 50 * t : 0);
        result += remaining;
        return result;
    }
}
