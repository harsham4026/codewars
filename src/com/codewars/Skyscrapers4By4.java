package com.codewars;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

/**
 * https://www.codewars.com/kata/4-by-4-skyscrapers
 */
public class Skyscrapers4By4 {
    static int[][] solvePuzzle(int[] clues) {
        int[][] res = new int[4][4];
        int[] source = new int[]{1, 2, 3, 4};
        List<int[]> permutation = new ArrayList<>();
        permutations(source, 0, 3, permutation);

        Map<Integer, List<int[]>> possibleSolutionsForClues = new HashMap<>();
        for (int i = 0; i <= 4; i++) {
            possibleSolutionsForClues.put(i, computeSolutions(i, permutation));
        }
        // for(int i=0;i<possibleSolutionsForClues.get(0).size();i++) {
        // System.out.println(Arrays.toString(possibleSolutionsForClues.get(0).get(i)));
        //}

        boolean r = backtrack(clues, 0, res, possibleSolutionsForClues);
        System.out.println(r);

        for (int i = 0; i < res.length; i++) {
            System.out.println(Arrays.toString(res[i]));
        }
        return res;
    }

    private static boolean backtrack(int[] clues, int clueIndex, int[][] res,
                                     Map<Integer, List<int[]>> possibleSolutionsForClues) {
        if (clueIndex == clues.length) {
            return true;
        }
        if (clues[clueIndex] == 1110) {//todo
            return backtrack(clues, clueIndex + 1, res, possibleSolutionsForClues);
        } else {
            int[][] copyRes = Arrays.stream(res)
                    .map(r -> r.clone())
                    .toArray(int[][]::new);
            List<int[]> possibleSolutions = possibleSolutionsForClues.get(clues[clueIndex]);
            for (int[] onePermutation : possibleSolutions) {

                if (canFill(clueIndex, copyRes, onePermutation)) {
                    boolean result = backtrack(clues, clueIndex + 1, copyRes, possibleSolutionsForClues);
                    if (result) {
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                res[i][j] = copyRes[i][j];
                            }
                        }
                        return result;
                    } else {
                        //to allow next permutation to be inserted, this has to be reset
                        copyRes = Arrays.stream(res)
                                .map(r -> r.clone())
                                .toArray(int[][]::new);
                    }
                }
            }
            return false;
        }
    }

    private static boolean canFill(int clueIndex, int[][] res, int[] onePermutation) {
        boolean canWrite = true;
        int permuIndex = 0;
        int row, col;
        switch (clueIndex / 4) {
            case 0:
                for (int i = 0; i < 4; i++, permuIndex++) {
                    if (res[i][clueIndex] != 0 && res[i][clueIndex] != onePermutation[permuIndex]) {
                        canWrite = false;
                    }
                }
                if (canWrite) {
                    permuIndex = 0;
                    for (int i = 0; i < 4; i++, permuIndex++) {
                        res[i][clueIndex] = onePermutation[permuIndex];
                    }
                }
                break;
            case 1:
                row = clueIndex % 4;
                for (int j = 3; j >= 0; j--, permuIndex++) {
                    if (res[row][j] != 0 && res[row][j] != onePermutation[permuIndex]) {
                        canWrite = false;
                    }
                }
                if (canWrite) {
                    permuIndex = 0;
                    for (int j = 3; j >= 0; j--, permuIndex++) {
                        res[row][j] = onePermutation[permuIndex];
                    }
                }
                break;
            case 2:
                col = 3 - (clueIndex % 4);
                for (int i = 3; i >= 0; i--, permuIndex++) {
                    if (res[i][col] != 0 && res[i][col] != onePermutation[permuIndex]) {
                        canWrite = false;
                    }
                }
                if (canWrite) {
                    permuIndex = 0;
                    for (int i = 3; i >= 0; i--, permuIndex++) {
                        res[i][col] = onePermutation[permuIndex];
                    }
                }
                break;
            case 3:
                row = 3 - (clueIndex % 4);
                for (int j = 0; j < 4; j++, permuIndex++) {
                    if (res[row][j] != 0 && res[row][j] != onePermutation[permuIndex]) {
                        canWrite = false;
                    }
                }
                if (canWrite) {
                    permuIndex = 0;
                    for (int j = 0; j < 3; j++, permuIndex++) {
                        res[row][j] = onePermutation[permuIndex];
                    }
                }
                break;
        }
        return canWrite;
    }

    private static void permutations(int[] arr, int l, int r, List<int[]> permutation) {
        if (l == r) {
            permutation.add(Arrays.copyOf(arr, arr.length));
        } else {
            for (int i = l; i <= r; i++) {
                swap(arr, l, i);
                permutations(arr, l + 1, r, permutation);
                swap(arr, l, i);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private static List<int[]> computeSolutions(int clue, List<int[]> permutation) {
        List<int[]> solutions = new ArrayList<>();
        for (int[] onePermutation : permutation) {
            int max = onePermutation[0];
            int numVisible = 1;
            for (int i = 1; i < onePermutation.length; i++) {
                if (onePermutation[i] > max) {
                    max = onePermutation[i];
                    numVisible++;
                }
            }
            if (clue == 0 || numVisible == clue) { //clue = 0, then add all permutations
                solutions.add(onePermutation);
            }
        }
        return solutions;
    }

}
