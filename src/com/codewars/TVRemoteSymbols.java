package com.codewars;

import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.Map;

/**
 * https://www.codewars.com/kata/tv-remote-symbols/train/java
 */
public class TVRemoteSymbols {
    private static int ROW = 6;
    private static int COLUMN = 8;

    public static int tvRemote(final String words) {
        String keys = "abcde123fghij456klmno789pqrst.@0uvwxyz_/";
        Map<Character, Coordinate> grid = IntStream.range(0, keys.length())
                .boxed()
                .collect(Collectors.toMap(keys::charAt, i -> new Coordinate(i / 8, i % 8)));
        grid.put('^', new Coordinate(5, 0)); //Having '^' as shift
        grid.put(' ', new Coordinate(5, 1));

        String chars = "^~?!'\"()-:;+&%*=<>€£$¥¤\\[]{},.@§#¿¡";
        Map<Character, Coordinate> charsGrid = IntStream.range(0, chars.length())
                .boxed()
                .collect(Collectors.toMap(chars::charAt, i -> new Coordinate(i / 8, i % 8)));
        charsGrid.put('_', new Coordinate(4, 6));
        charsGrid.put('/', new Coordinate(4, 7));
        charsGrid.put(' ', new Coordinate(5, 1));

        Coordinate current = new Coordinate(0, 0);
        Coordinate shiftCharLocation = new Coordinate(5, 0);
        int result = 0;
        int mode = 0;

        //L U S
        //0 1 2
        for (char c : words.toCharArray()) {
            Coordinate newPoint;
            if (c != '_' && c != '/' && c != ' ' && c != '@' && c != '.') { //works in all modes
                if (Character.isUpperCase(c) && mode != 1) {
                    result += computeCost(current, shiftCharLocation);
                    current = shiftCharLocation;
                    if (mode == 2) { //Extra press
                        result++;
                    }
                    mode = 1;
                } else if (Character.isLowerCase(c) && mode != 0) {
                    result += computeCost(current, shiftCharLocation);
                    current = shiftCharLocation;
                    if (mode == 1) {
                        result++;
                    }
                    mode = 0;
                } else if (Character.isDigit(c) && mode == 2) { 
                    result += computeCost(current, shiftCharLocation);
                    current = shiftCharLocation;
                    mode = 0; //Going to lower case
                } else if (!(Character.isLetter(c) || Character.isDigit(c)) && mode != 2) { //symbol
                    result += computeCost(current, shiftCharLocation);
                    current = shiftCharLocation;
                    if (mode == 0) {
                        result++;
                    }
                    mode = 2;
                }
            }
            System.out.println(c + " " + result);
            newPoint = Character.isLetter(c) || Character.isDigit(c) ? grid.get(Character.toLowerCase(c)) : charsGrid.get(c);
            result += computeCost(current, newPoint);
            current = newPoint;
        }
        return result;
    }

    private static int computeCost(Coordinate p1, Coordinate p2) {
        int dx = Math.abs(p1.x - p2.x), dxW = ROW - dx,
                dy = Math.abs(p1.y - p2.y), dyW = COLUMN - dy;
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