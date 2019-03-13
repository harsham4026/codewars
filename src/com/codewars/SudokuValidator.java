package com.codewars;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * https://www.codewars.com/kata/sudoku-solution-validator/train/java
 */
public class SudokuValidator {
    public static boolean check(int[][] sudoku) {
        return Arrays.stream(sudoku)
                .allMatch(arr -> Arrays.stream(arr)
                        .filter(e -> e != 0)
                        .distinct()
                        .count() == 9)
                &&
                IntStream.range(0, 9 /*col*/)
                        .allMatch(c -> IntStream.range(0, 9 /*row*/)
                                .map(r -> sudoku[r][c])
                                .filter(e -> e != 0)
                                .distinct()
                                .count() == 9)
                &&
                IntStream.iterate(0, i -> i <= 6, i -> i + 3)
                        .allMatch(r -> IntStream.range(0, 3)
                                .allMatch(inc -> IntStream.range(0, 3)
                                        .flatMap(c -> Arrays.stream(sudoku[r + c], inc * 3, inc * 3 + 3))
                                        .distinct()
                                        .count() == 9));

    }
}