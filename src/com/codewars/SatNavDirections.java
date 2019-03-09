package com.codewars;

import java.awt.Point;
import java.util.Arrays;
import java.util.function.IntUnaryOperator;

/**
 * https://www.codewars.com/kata/sat-nav-directions/
 */
public class SatNavDirections {
    private static final String[] dirs = new String[]{"EAST", "SOUTH", "WEST", "NORTH"};

    public static Point satNav(final String[] directions) {
        Point p = new Point(0, 0);
        String facingDirection = directions[0].split(" ")[1];
        for (int i = 1; i < directions.length - 1; i++) {
            String command = directions[i];
            if (command.contains("Take the ")) {
                String[] parts = command.split(" ");
                String direction = parts[3];
                int num = parts[2].equals("NEXT") ? 1 : parseNum(parts[2]);

                p = processTurn(p, num, facingDirection);

                int angle = direction.equals("LEFT") ? -1 : 1;
                facingDirection = dirs[(getIndex(facingDirection) + angle + dirs.length) % dirs.length];

            } else if (command.contains("Go straight on")) {
                String[] parts = command.split(" ");
                double num = parseAsDouble(parts[4]);
                if (command.contains("km")) {
                    p = processCommand(p, (int) (num * 1000), facingDirection);
                } else {
                    p = processCommand(p, (int) (num), facingDirection);
                }
            } else {
                int idx = getIndex(facingDirection);
                facingDirection = dirs[(idx + 2) % dirs.length];
            }
            System.out.println(p + " " + facingDirection);
        }
        return p;
    }

    private static int getIndex(String dir) {
        int i = 0;
        for (; i < dirs.length; i++) {
            if (dirs[i].equals(dir)) {
                return i;
            }
        }
        return -1;
    }

    private static int parseNum(String p) {
        return p.chars()
                .mapToObj(c -> (char) c)
                .takeWhile(c -> Character.isDigit(c))
                .map(c -> c - '0')
                .reduce(0, (a, b) -> a * 10 + b);
    }

    private static double parseAsDouble(String p) {
        StringBuilder builder = new StringBuilder();
        for (char c : p.toCharArray()) {
            if (Character.isLetter(c)) {
                break;
            } else {
                builder.append(c);
            }
        }
        return Double.valueOf(builder.toString());
    }

    private static Point processCommand(Point p, int distanceInMeters, String facingDirection) {
        int displacement = distanceInMeters / 100; //coordinates are described with units of 100m
        int x = p.x;
        int y = p.y;
        switch (facingDirection) {
            case "EAST":
                x += displacement;
                break;
            case "WEST":
                x -= displacement;
                break;
            case "NORTH":
                y += displacement;
                break;
            case "SOUTH":
                y -= displacement;
                break;
        }
        return new Point(x, y);
    }

    private static Point processTurn(Point p, int num, String facingDirection) {
        int x = p.x;
        int y = p.y;
        int newX = x, newY = y;
        int turn = num;
        switch (facingDirection) {
            case "EAST":
                newX = adjustCoordToNextTurn(x, i -> 10 - (i % 10), i -> i % 10);
                turn = newX != x ? num - 1 : num; //If equal, then already at an intersection
                newY = y;
                break;
            case "WEST":
                newX = adjustCoordToNextTurn(x, i -> -(i % 10), i -> -(10 - i % 10));
                turn = newX != x ? num - 1 : num;
                newY = y;
                break;
            case "NORTH":
                newY = adjustCoordToNextTurn(y, i -> 10 - (i % 10), i -> i % 10);
                turn = newY != y ? num - 1 : num;
                newX = x;
                break;
            case "SOUTH":
                newY = adjustCoordToNextTurn(y, i -> -(i % 10), i -> -(10 - i % 10));
                turn = newY != y ? num - 1 : num;
                newX = x;
                break;
        }
        return processCommand(new Point(newX, newY), turn * 1000, facingDirection);

    }

    private static int adjustCoordToNextTurn(int i, IntUnaryOperator postiveCase, IntUnaryOperator negCase) {
        int result = i;
        if (i < 0) {
            int positiveI = -i;
            if (positiveI % 10 != 0) { //Blocks of 1 km
                result += negCase.applyAsInt(positiveI);
            }
        } else {
            if (i % 10 != 0) { //Blocks of 1 km
                result += postiveCase.applyAsInt(i);
            }
        }
        return result;
    }
}