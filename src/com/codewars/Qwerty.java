package com.codewars;

/**
 * https://www.codewars.com/kata/simple-encryption-number-4-qwerty
 */
public class Qwerty {
    public static String encrypt(String text, int key) {
        return process(text, key, true);
    }

    private static String process(String text, int key, boolean encrypt) {
        char[][] keyboard = new char[][]{
                {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'},
                {'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l',},
                {'z', 'x', 'c', 'v', 'b', 'n', 'm', ',', '.'}};

        StringBuilder resultBuilder = new StringBuilder();
        boolean leftToRight = encrypt; //l->r for encryption and r->l for decryption
        int[] regionsKey = new int[3];
        regionsKey[2] = key % 10;
        key /= 10;
        regionsKey[1] = key % 10;
        key /= 10;
        regionsKey[0] = key % 10;

        for (char c : text.toCharArray()) {
            Location location = getLocationOfChar(keyboard, c);
            if (location == null) {
                resultBuilder.append(c);
            } else {
                int rotationAmt = regionsKey[location.row];
                char resChar = rotate(keyboard, location, rotationAmt, leftToRight);
                if (isUpperCase(c)) {
                    resultBuilder.append(convertToUpperCase(resChar));
                } else {
                    resultBuilder.append(resChar);
                }
            }
        }
        return resultBuilder.toString();
    }

    //Helper methods
    private static Location getLocationOfChar(char[][] keyboard, char c) {
        boolean found = false;
        int i = 0, j = 0;
        for (i = 0; i < 3; i++) {
            for (j = 0; (i == 0 && j < 10) || j < 9; j++) {
                if (keyboard[i][j] == c || convertToUpperCase(keyboard[i][j]) == c) {
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }
        if (found) {
            return new Location(i, j);
        }
        return null;
    }

    private static char rotate(char[][] keyboard, Location location, int rotationAmt, boolean leftToRight) {
        int row = location.row;
        int length = row == 0 ? 10 : 9;
        int newIndex;
        if (leftToRight) {
            newIndex = (location.index + rotationAmt) % length;
        } else {
            newIndex = location.index - rotationAmt;
            if (newIndex < 0) {
                newIndex += length;
            }
        }
        return keyboard[row][newIndex];

    }

    private static boolean isUpperCase(char c) {
        return Character.isUpperCase(c) || c == '<' || c == '>';
    }

    private static char convertToUpperCase(char c) {
        if (Character.isLetter(c)) {
            return Character.toUpperCase(c);
        } else if (c == ',') {
            return '<';
        }
        return '>'; //'.'
    }

    private static class Location {
        int row;
        int index;

        Location(int row, int index) {
            this.row = row;
            this.index = index;
        }
    }

    public static String decrypt(String encryptedText, int key) {
        return process(encryptedText, key, false);
    }
}
