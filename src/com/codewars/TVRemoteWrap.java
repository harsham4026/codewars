package com.codewars;

import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.Map;

/**
 * https://www.codewars.com/kata/tv-remote-wrap
 */
public class TVRemoteWrap {
    private static int ROW = 6;
    private static int COLUMN = 8;

    public static int tvRemote(final String words) {
        String keys = "abcde123fghij456klmno789pqrst.@0uvwxyz_/";
        Map<Character, Coordinate> grid = IntStream.range(0, keys.length())
                .boxed()
                .collect(Collectors.toMap(keys::charAt, i -> new Coordinate(i / 8, i % 8)));
        grid.put('^', new Coordinate(5, 0)); //Having '^' as shift
        grid.put(' ', new Coordinate(5, 1));

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
        int wrapRow = src.x < dst.x
                ? ROW - dst.x + src.x   //Go left
                : ROW - src.x + dst.x;  //Go right
        int wrapCol = src.y < dst.y
                ? COLUMN - dst.y + src.y
                : COLUMN - src.y + dst.y;
        int wrapTempSol = Math.min(wrapRow + Math.abs(src.y - dst.y),
                Math.abs(src.x - dst.x) + wrapCol);
        int wrapSol = Math.min(wrapTempSol,
                wrapRow + wrapCol); //wrap both dimensions
        return Math.min(wrapSol,
                Math.abs(src.x - dst.x) + Math.abs(src.y - dst.y))  //direct
                + 1;
    }

    //easier way (from solution)
    private static int manhattan(Coordinate p1, Coordinate p2) {
        int dx = Math.abs(p1.x-p2.x), dxW = ROW - dx,
                dy = Math.abs(p1.y-p2.y), dyW = COLUMN  - dy;
        return 1 + Math.min(dx, dxW) + Math.min(dy, dyW);
    }

    private static class Coordinate {
        int x;
        int y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
