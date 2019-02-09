package com.codewars;

import java.util.Map;
import java.util.HashMap;

/**
 * https://www.codewars.com/kata/tv-remote-shift-and-space
 */
public class TVRemoteShiftAndSpace {
    public static int tvRemote(final String words) {
        Map<Character, Coordinate> grid = constructMap();
        Coordinate current = new Coordinate(0, 0);
        Coordinate shiftCharLocation = new Coordinate(5, 0);
        int result = 0;
        boolean inShiftMode = false;

        for (char c : words.toCharArray()) {
            Coordinate newPoint;
            if (Character.isUpperCase(c) && !inShiftMode) {
                result += computeCost(current, shiftCharLocation);
                current = shiftCharLocation;
                inShiftMode = true;
            } else if (Character.isLowerCase(c) && inShiftMode) {
                result += computeCost(current, shiftCharLocation);
                current = shiftCharLocation;
                inShiftMode = false;
            }
            newPoint = grid.get(Character.toLowerCase(c));
            result += computeCost(current, newPoint);
            current = newPoint;
        }
        return result;
    }

    private static int computeCost(Coordinate src, Coordinate dst) {
        return Math.abs(src.x - dst.x) + Math.abs(src.y - dst.y) + 1;
    }

    private static class Coordinate {
        int x;
        int y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static Map<Character, Coordinate> constructMap() {
        Map<Character, Coordinate> map = new HashMap<>();
        int i = 0;
        int j = 0;
        for (char c = 'a'; c <= 'y'; c++) {
            map.put(c, new Coordinate(i, j));
            j = (j + 1) % 5;
            if (j == 0) {
                i++;
            }
        }
        map.put('z', new Coordinate(4, 5));
        i = 0;
        j = 0;
        for (int c = 1; c <= 9; c++) {
            map.put((char) (c + '0'), new Coordinate(i, j + 5));
            j = (j + 1) % 3;
            if (j == 0) {
                i++;
            }
        }
        map.put('.', new Coordinate(3, 5));
        map.put('@', new Coordinate(3, 6));
        map.put('0', new Coordinate(3, 7));
        map.put('_', new Coordinate(4, 6));
        map.put('/', new Coordinate(4, 7));

        map.put('^', new Coordinate(5, 0)); //Having '^' as shift
        map.put(' ', new Coordinate(5, 1));

        return map;
    }
}
