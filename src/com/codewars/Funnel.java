package com.codewars;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

/**
 * https://www.codewars.com/kata/create-a-funnel/train/java
 */
public class Funnel {
    private static final int MAX_DEPTH = 5;
    private List<Level> funnel;


    public Funnel() {
        funnel = new ArrayList<>();
        IntStream.rangeClosed(1, MAX_DEPTH)
                .forEach(l -> funnel.add(new Level(l)));
    }

    public void fill(char... args) {
        int level = IntStream.range(0, MAX_DEPTH)
                .filter(l -> hasSpace(funnel.get(l)))
                .findFirst()
                .orElse(-1); //funnel full;

        if (level != -1) {
            Level currentRow = funnel.get(level);
            int index = 0;

            for (Character c : args) {
                while (index < currentRow.data.length && currentRow.data[index] != null) {
                    index++;
                }
                if (index == currentRow.data.length) { //current level full
                    if (level + 1 != MAX_DEPTH) {
                        level++;
                        index = 0; //Is it possible that first element already exists in above row?
                        currentRow = funnel.get(level);
                        currentRow.data[index++] = c;
                    }
                } else {
                    currentRow.data[index++] = c;
                }
            }
        }
    }

    private boolean hasSpace(Level row) {
        return Arrays.stream(row.data)
                .anyMatch(Objects::isNull);
    }

    public Character drip() {
        if (funnel.get(0).data[0] == null) {
            return null;
        }
        Character result = funnel.get(0).data[0];
        int level = 0;
        int index = 0;
        while (true) {
            //m = new HashMap<>();
            int lWeight = weight(level + 1, index);
            //m = new HashMap<>();
            int rWeight = weight(level + 1, index + 1);
            if (lWeight == 0 && rWeight == 0) {
                funnel.get(level).data[index] = null;
                break;
            }
            if (lWeight >= rWeight) {
                funnel.get(level).data[index] = funnel.get(level + 1).data[index];
            } else {
                funnel.get(level).data[index] = funnel.get(level + 1).data[++index];
            }
            level++;
        }
        return result;
    }

    //region ugly way
    static Map<String, Boolean> m;

    private int weightOld(int level, int index) {
        if (level == funnel.size()) {
            return 0;
        }
        if (funnel.get(level).data[index] == null) {
            return 0;
        }
        int l = 0, r = 0;
        if (!m.containsKey("" + (level + 1) + index)) {
            l = weight(level + 1, index);
            m.put("" + (level + 1) + index, true);
        }
        if (!m.containsKey("" + (level + 1) + (index + 1))) {
            r = weight(level + 1, index + 1);
            m.put("" + (level + 1) + (index + 1), true);
        }
        return l + r + 1;
    }
    //endregion ugly way


    private int weight(int level, int index) {
        if (level == funnel.size()) {
            return 0;
        }
        if (funnel.get(level).data[index] == null) {
            return 0;
        }

        int res = 1; //current element
        int maxIndex = index + 1;
        for (int i = level + 1; i < funnel.size(); i++) {
            for (int j = index; j <= maxIndex; j++) {
                if (funnel.get(i).data[j] != null) {
                    res++;
                }
            }
            maxIndex++;
        }
        return res;

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = funnel.size() - 1; i >= 0; i--) {
            Level currentRow = funnel.get(i);
            String currentLevelData = Arrays.stream(currentRow.data)
                    .map(element -> Optional.ofNullable(element)
                            .map(String::valueOf)
                            .orElse(" "))
                    .collect(Collectors.joining(" "));

            //Append spaces
            IntStream.range(0, (funnel.size() - 1) - i)
                    .forEach(l -> builder.append(" "));

            builder.append("\\")
                    .append(currentLevelData)
                    .append("/");
            if (i != 0) {
                builder.append("\n");
            }
        }
        String b = builder.toString();
        System.out.println(b);
        return b;
    }

    private static class Level {
        private Character[] data;

        Level(int size) {
            this.data = new Character[size];
        }
    }
}