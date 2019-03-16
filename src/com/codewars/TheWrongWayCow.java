package com.codewars;

import java.util.Optional;

/**
 * https://www.codewars.com/kata/the-wrong-way-cow/
 */
public class TheWrongWayCow {

    public static int[] findWrongWayCow(final char[][] field) {
        int i, j;
        int row = field.length;
        int col = field[0].length;
        boolean moreThanOneWrongCow = false;

        Optional<int[]> result = Optional.empty();
        for (i = 0; i < row; i++) {
            for (j = 0; j < col; j++) {
                if (field[i][j] == 'w') {
                    if ((j + 2 >= col || field[i][j + 1] != 'o' || field[i][j + 2] != 'c')) {

                    } else if (result.isPresent()) {
                        moreThanOneWrongCow = true;
                        result = Optional.empty();
                        break;
                    } else {
                        result = Optional.of(new int[]{j + 2, i});
                        j += 2;
                    }
                }
            }
            if (moreThanOneWrongCow) {
                break;
            }
        }
        if (result.isPresent()) {
            return result.get();
        }

        //2
        moreThanOneWrongCow = false;
        for (j = 0; j < col; j++) {
            for (i = 0; i < row; i++) {
                if (field[i][j] == 'w') {
                    if ((i + 2 >= row || field[i + 1][j] != 'o' || field[i + 2][j] != 'c')) {

                    } else if (result.isPresent()) {
                        moreThanOneWrongCow = true;
                        result = Optional.empty();
                        break;
                    } else {
                        result = Optional.of(new int[]{j, i + 2});
                        i += 2;
                    }
                }
            }
            if (moreThanOneWrongCow) {
                break;
            }
        }
        if (result.isPresent()) {
            return result.get();
        }

        //3
        moreThanOneWrongCow = false;
        for (i = 0; i < row; i++) {
            for (j = col - 1; j >= 0; j--) {
                if (field[i][j] == 'w') {
                    if ((j - 2 < 0 || field[i][j - 1] != 'o' || field[i][j - 2] != 'c')) {

                    } else if (result.isPresent()) {
                        moreThanOneWrongCow = true;
                        result = Optional.empty();
                        break;
                    } else {
                        result = Optional.of(new int[]{j - 2, i});
                        j -= 2;
                    }
                }
            }
            if (moreThanOneWrongCow) {
                break;
            }
        }
        if (result.isPresent()) {
            return result.get();
        }

        //4
        moreThanOneWrongCow = false;
        for (j = 0; j < col; j++) {
            for (i = row - 1; i >= 0; i--) {
                if (field[i][j] == 'w') {
                    if (i - 2 < 0 || field[i - 1][j] != 'o' || field[i - 2][j] != 'c') {

                    } else if (result.isPresent()) {
                        moreThanOneWrongCow = true;
                        result = Optional.empty();
                        break;
                    } else {
                        result = Optional.of(new int[]{j, i - 2});
                        i -= 2;
                    }
                }
            }
            if (moreThanOneWrongCow) {
                break;
            }
        }

        return result.get();
    }
}