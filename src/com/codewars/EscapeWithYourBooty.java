package com.codewars;

import java.util.stream.IntStream;

/**
 * https://www.codewars.com/kata/escape-with-your-booty
 */
public class EscapeWithYourBooty {
    public static boolean checkCourse(char[][] map) {
        int row = map.length;
        int col = map[0].length;

        int myLocationRow = IntStream.range(0, row)
                .filter(i -> map[i][0] == 'X')
                .findFirst()
                .getAsInt(); //Guaranteed to be present

        int rowNum;
        for (int j = 0; j < col; j++) {

            rowNum = getRowNum(j, row);
            if (map[0][j] == 'N' && Math.abs(myLocationRow - rowNum) == 1) {
                return false;
            }
            if (j + 1 < col && map[0][j + 1] == 'N' && Math.abs(myLocationRow - rowNum) == 1) {
                return false;
            }
            if (j - 1 >= 0 && map[0][j - 1] == 'N' && Math.abs(myLocationRow - rowNum) == 1) {
                return false;
            }


            //flip for the bottom one
            rowNum = (row - 1) - rowNum;
            if (map[row - 1][j] == 'N' && Math.abs(myLocationRow - rowNum) == 1) {
                return false;
            }
            if (j + 1 < col && map[row - 1][j + 1] == 'N' && Math.abs(myLocationRow - rowNum) == 1) {
                return false;
            }
            if (j - 1 >= 0 && map[row - 1][j - 1] == 'N' && Math.abs(myLocationRow - rowNum) == 1) {
                return false;
            }
        }
        return true;
    }

    private static int getRowNum(int j, int row) {
        if (j % (row - 1) == 0) {
            return (j / (row - 1)) % 2 == 0 ? 0 : row - 1;
        } else {
            return (j / (row - 1)) % 2 == 0 ? (j % (row - 1)) : (row - 1 - (j % (row - 1)));
        }
    }
}