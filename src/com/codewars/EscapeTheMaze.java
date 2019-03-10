package com.codewars;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;

/**
 * https://www.codewars.com/kata/escape-the-maze/
 */
public class EscapeTheMaze {
    private static int[] xDir = new int[]{-1, 1, 0, 0};
    private static int[] yDir = new int[]{0, 0, -1, 1};
    private static char[] dir = new char[]{'U', 'D', 'L', 'R'};
    private static List<Character> resultPosition = Arrays.asList('^', 'v', '<', '>');
    private static final Map<String, Character> moves;

    static {
        moves = new HashMap<>();
        moves.put("^|D", 'B');
        moves.put("^|L", 'L');
        moves.put("^|R", 'R');

        moves.put("v|U", 'B');
        moves.put("v|L", 'R');
        moves.put("v|R", 'L');

        moves.put("<|U", 'R');
        moves.put("<|D", 'L');
        moves.put("<|R", 'B');

        moves.put(">|U", 'L');
        moves.put(">|D", 'R');
        moves.put(">|L", 'B');

    }

    public static List<Character> escape(char[][] maze) {
        boolean found = false;
        int i = 0, j = 0;
        for (i = 0; i < maze.length; i++) {
            for (j = 0; j < maze[0].length; j++) {
                if (resultPosition.contains(maze[i][j])) {
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }
        List<Character> movesList = new LinkedList<>();
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        solve(maze, i, j, maze[i][j], movesList, visited);
        return movesList;
    }

    private static boolean solve(char[][] maze, int x, int y, Character currentPosition,
                                 List<Character> movesList, boolean[][] visited) {
        if (x == 0 || x == maze.length - 1 || y == 0 || y == maze[0].length - 1) {
            return true;
        }
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int newX = x + xDir[i];
            int newY = y + yDir[i];
            if (newX >= 0 && newX < maze.length && newY >= 0 && newY < maze[0].length
                    && !visited[newX][newY]
                    && maze[newX][newY] != '#') {
                boolean res = solve(maze, newX, newY, resultPosition.get(i), movesList, visited);
                if (res) {
                    //recursive so in reverse. Cannot reverse the list after `solve` as these two moves are atomic
                    movesList.add(0, 'F');
                    getMove(currentPosition, dir[i]).ifPresent(c -> movesList.add(0, c));
                    return true;
                }
            }
        }
        return false;
    }

    private static Optional<Character> getMove(Character currentPosition, Character direction) {
        return Optional.ofNullable(moves.get(currentPosition + "|" + direction));
    }
}