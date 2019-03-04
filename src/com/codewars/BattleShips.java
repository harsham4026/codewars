package com.codewars;

import java.util.*;

/**
 * https://www.codewars.com/kata/battle-ships-sunk-damaged-or-not-touched
 */
public class BattleShips {

    public static Map<String,Double> damagedOrSunk(final int[][] board, final int[][] attacks) {

        int [] boatLengths = new int[3]; //Max 3 boats
        int [] destruction = new int[3];
        boatLengths[0] = boatLength(board, 1);
        boatLengths[1] = boatLength(board, 2);
        boatLengths[2] = boatLength(board, 3);


        for(int i=0;i<board.length;i++)
            System.out.println(Arrays.toString(board[i]));
        System.out.println(Arrays.toString(boatLengths));
        int length = board.length;
        for(int [] coordinate : attacks) {
            int i = coordinate[0];
            int j = coordinate[1];
            if (board[length - j][i - 1] != 0) {
                int boatNum = board[length - j][i - 1];
                destruction[boatNum - 1]++;
                board[length - j][i - 1] = 0;
            }
        }
        double sunk = 0.0;
        double damaged = 0.0;
        double notTouched = 0.0;
        double points = 0.0;
        for (int i = 0; i < 3; i++) {
            if (boatLengths[i] == 0) {
                continue;
            }
            if (destruction[i] == boatLengths[i]) {
                sunk++;
                points++;
            } else if (destruction[i] > 0) {
                damaged++;
                points += 0.5;
            } else {
                notTouched++;
                points--;
            }
        }
        Map<String,Double> result = new HashMap<>();
        result.put("sunk", sunk);
        result.put("damaged", damaged);
        result.put("notTouched", notTouched);
        result.put("points", points);
        return result;
    }
    private static int boatLength(int [][] board, int boatNumber) {
        int i, j;
        int length = 0;
        for(i = 0; i < board.length; i++) {
            for(j = 0; j < board[i].length; j++) {
                if(board[i][j] == boatNumber) {
                    length++;
                }
            }
        }

        return length;
    }
}
