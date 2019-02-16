package com.codewars;


import java.util.List;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;

public class ConnectFour {

    public static String whoIsWinner(List<String> piecesPositionList) {
        String[][] grid = new String[6][7];

        for (String move : piecesPositionList) {
            String[] parts = move.split("_");
            int column = parts[0].charAt(0) - 'A';
            int row = addToGrid(column, parts[1], grid);
            if (isWin(grid, row, column)) {
                return grid[row][column];
            }
        }
        return "Draw";
    }

    private static int addToGrid(int column, String color, String[][] grid) {
        int i;
        for (i = grid.length - 1; i >= 0; i--) {
            if (grid[i][column] == null) {
                grid[i][column] = color;
                break;
            }
        }
        return i;
    }

    private static boolean isWin(String[][] grid, int row, int column) {
        String currentColor = grid[row][column];
        int left = traverse(row, column - 1, currentColor, grid, i -> true, j -> j >= 0, i -> i, j -> j - 1);
        int right = traverse(row, column + 1, currentColor, grid, i -> true, j -> j < grid[0].length, i -> i, j -> j + 1);
        if (left + right >= 3) {
            return true;
        }

        int top = traverse(row - 1, column, currentColor, grid, i -> i >= 0, j -> true, i -> i - 1, j -> j);
        int bottom = traverse(row + 1, column, currentColor, grid, i -> i < grid.length, j -> true, i -> i + 1, j -> j);
        if (top + bottom >= 3) {
            return true;
        }

        int diag1 = traverse(row - 1, column - 1, currentColor, grid, i -> i >= 0, j -> j >= 0, i -> i - 1, j -> j - 1);
        int diag2 = traverse(row + 1, column + 1, currentColor, grid, i -> i < grid.length, j -> j < grid[0].length, i -> i + 1, j -> j + 1);
        if (diag1 + diag2 >= 3) {
            return true;
        }

        diag1 = traverse(row - 1, column + 1, currentColor, grid, i -> i >= 0, j -> j < grid[0].length, i -> i - 1, j -> j + 1);
        diag2 = traverse(row + 1, column - 1, currentColor, grid, i -> i < grid.length, j -> j >= 0, i -> i + 1, j -> j - 1);
        if (diag1 + diag2 >= 3) {
            return true;
        }
        return false;
    }

    private static int traverse(int row, int column, String currentColor, String[][] grid,
                                Predicate<Integer> rowPredicate, Predicate<Integer> columnPredicate,
                                IntUnaryOperator newRowProvider, IntUnaryOperator newColumnProvider) {
        int i = row;
        int j = column;

        int cons = 0;
        while (rowPredicate.test(i) && columnPredicate.test(j) && currentColor.equals(grid[i][j])) {
            cons++;
            i = newRowProvider.applyAsInt(i);
            j = newColumnProvider.applyAsInt(j);
        }
        return cons;
    }
}