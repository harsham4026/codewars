package com.codewars;

/**
 * https://www.codewars.com/kata/ten-pin-bowling/
 */
public class TenPinBowling {
    public static int bowling_score(String frames) {
        String[] frameParts = frames.split(" ");
        int result = 0;
        for (int i = 0; i < frameParts.length; i++) {
            if (frameParts[i].equals("X")) {
                result += getScoreOfFrame(frameParts[i]);
                //add next two rolls
                if (i + 1 < frameParts.length && frameParts[i + 1].length() == 2) {
                    result += getScoreOfFrame(frameParts[i + 1]);

                } else if (i + 1 == frameParts.length - 1) { //Scored 'X' in i + 1 and i + 1 is last frame.
                    result += getScoreOfFrame(frameParts[i + 1], 2); //If last is XXX, take only 2 for bonus

                } else if (i + 2 < frameParts.length) { //Scored 'X' in i + 1
                    result += getScoreOfFrame(frameParts[i + 1]);
                    result += getScoreOfFrame(frameParts[i + 2], 1);
                }
            } else {
                result += getScoreOfFrame(frameParts[i]);
                if (frameParts[i].charAt(1) == '/') {
                    //add next roll
                    if (i + 1 < frameParts.length) {
                        result += getScoreOfFrame(frameParts[i + 1], 1);
                    }
                }
            }
            System.out.println((i + 1) + " " + result);
        }
        return result;
    }

    private static int getScoreOfFrame(String s) {
        return getScoreOfFrame(s, s.length());
    }

    private static int getScoreOfFrame(String s, int maxRolls) {
        if (s.equals("X")) {
            return 10;
        }

        int res = 0;
        int curr = 0;
        for (int i = 0; i < maxRolls; i++) {
            if (s.charAt(i) == 'X') {
                res += 10;
            } else if (s.charAt(i) == '/') {
                res -= curr;
                res += 10;
            } else {
                curr = s.charAt(i) - '0';
                res += curr;
            }
        }
        return res;
    }
}