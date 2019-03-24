package com.codewars;

/**
 * https://www.codewars.com/kata/battleship-field-validator/
 */
public class BattleshipFieldValidator {

    private static int[] xCoor = new int[]{-1, 1, 0, 0, -1, -1, 1, 1};
    private static int[] yCoor = new int[]{0, 0, -1, 1, -1, 1, -1, 1};

    //right and down
    private static int[] shipXCoor = new int[]{0, 1};
    private static int[] shipYCoor = new int[]{1, 0};

    public static boolean fieldValidator(int[][] field) {
        int r = field.length;
        int c = field[0].length;
        boolean[][] visited = new boolean[r][c];

        int[] shipsByLength = new int[5];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (field[i][j] == 1 && !visited[i][j]) {
                    int length = searchForShips(field, i, j, visited);
                    if (length == -1 || length > 4) {
                        return false;
                    }
                    shipsByLength[length]++;
                }
            }
        }
        return shipsByLength[1] == 4 && shipsByLength[2] == 3 && shipsByLength[3] == 2 && shipsByLength[4] == 1;
    }

    private static int searchForShips(int[][] field, int x, int y, boolean[][] visited) {
        int r = field.length;
        int c = field[0].length;

        for (int i = 0; i < 2; i++) {
            int newX = x + shipXCoor[i];
            int newY = y + shipYCoor[i];
            if (isValid(newX, newY, r, c) && field[newX][newY] == 1) {
                return searchForShipsInDirection(field, x, y, i, 1, visited);
            }
        }
        visited[x][y] = true;
        return 1;
    }

    private static int searchForShipsInDirection(int[][] field, int x, int y, int coorIndex, int length,
                                                 boolean[][] visited) {
        int r = field.length;
        int c = field[0].length;
        int newX, newY;

        visited[x][y] = true;

        //Check for overlaps in other directions
        for (int i = 0; i < 8; i++) {
            if (shipXCoor[coorIndex] != xCoor[i] && shipYCoor[coorIndex] != yCoor[i]) { //not check in the current direction
                newX = x + xCoor[i];
                newY = y + yCoor[i];
                if (isValid(newX, newY, r, c) && field[newX][newY] == 1) {
                    return -1;
                }
            }
        }
        newX = x + shipXCoor[coorIndex];
        newY = y + shipYCoor[coorIndex];
        if (isValid(newX, newY, r, c) && field[newX][newY] == 1) {
            return searchForShipsInDirection(field, newX, newY, coorIndex, length + 1, visited);
        }
        return length;


    }

    private static boolean isValid(int x, int y, int r, int c) {
        return x >= 0 && x < r && y >= 0 && y < c;
    }
}