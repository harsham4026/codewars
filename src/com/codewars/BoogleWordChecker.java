package com.codewars;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * https://www.codewars.com/kata/boggle-word-checker/
 */
public class BoogleWordChecker {

    private static int[] xCoor = new int[]{-1, 1, 0, 0, -1, -1, 1, 1};
    private static int[] yCoor = new int[]{0, 0, -1, 1, -1, 1, -1, 1};
    private final char[][] board;
    private final String word;


    public BoogleWordChecker(final char[][] board, final String word) {
        this.board = Arrays.stream(board)
                .map(a -> Arrays.copyOf(a, a.length))
                .toArray(char[][]::new);
        this.word = word;
    }

    public boolean check() {
        int i = 0, j = 0;
        char[] chars = word.toCharArray();

        List<int[]> points = new ArrayList<>();
        for (i = 0; i < board.length; i++) {
            for (j = 0; j < board[0].length; j++) {
                if (board[i][j] == chars[0]) {
                    points.add(new int[]{i, j});
                }
            }
            System.out.println();
        }

        if (!points.isEmpty()) {
            return points.stream()
                    .anyMatch(p -> isValid(p[0], p[1], 1, chars, new boolean[board.length][board[0].length]));
        }
        return false;
    }

    private boolean isValid(int x, int y, int idx, char[] chars, boolean[][] visited) {
        if (idx == chars.length) {
            return true;
        }
        visited[x][y] = true;
        for (int i = 0; i < 8; i++) {
            int newX = x + xCoor[i];
            int newY = y + yCoor[i];
            if (newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length
                    && board[newX][newY] == chars[idx] && !visited[newX][newY]) {
                boolean r = isValid(newX, newY, idx + 1, chars, visited);
                if (r) {
                    return true;
                }
            }
        }
        visited[x][y] = false;
        return false;
    }

}