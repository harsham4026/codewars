package com.codewars;

/**
 * https://www.codewars.com/kata/cockroach-bug-scatter/
 */
public class CockroachBugScatter {
    public static int[] cockroaches(final char[][] room) {
        int [] result = new int[10];
        int i, j, r;
        for (i = 1; i < room.length - 1; i++) {
            for (j = 1; j < room[0].length - 1; j++) {
                if (room[i][j] == 'U') {
                    r = findHoleTraversingTop(room, j);
                    r = (r == -1) ? findHoleTraversingLeft(room, 0) : r;
                    r = (r == -1) ? findHoleTraversingBottom(room, 0) : r;
                    r = (r == -1) ? findHoleTraversingRight(room, room.length - 1) : r;
                    r = (r == -1) ? findHoleTraversingTop(room, room[0].length - 1): r;
                    result[r]++;
                } else if (room[i][j] == 'D') {
                    r = findHoleTraversingBottom(room, j);
                    r = (r == -1) ? findHoleTraversingRight(room, room.length - 1) : r;
                    r = (r == -1) ? findHoleTraversingTop(room, room[0].length - 1) : r;
                    r = (r == -1) ? findHoleTraversingLeft(room, 0) : r;
                    r = (r == -1) ? findHoleTraversingBottom(room, 0): r;
                    result[r]++;
                } else if (room[i][j] == 'L') {
                    r = findHoleTraversingLeft(room, i);
                    r = (r == -1) ? findHoleTraversingBottom(room, 0) : r;
                    r = (r == -1) ? findHoleTraversingRight(room, room.length - 1) : r;
                    r = (r == -1) ? findHoleTraversingTop(room, room[0].length - 1): r;
                    r = (r == -1) ? findHoleTraversingLeft(room, 0): r;
                    result[r]++;
                } else if (room[i][j] == 'R') {
                    r = findHoleTraversingRight(room, i);
                    r = (r == -1) ? findHoleTraversingTop(room, room[0].length - 1) : r;
                    r = (r == -1) ? findHoleTraversingLeft(room, 0) : r;
                    r = (r == -1) ? findHoleTraversingBottom(room, 0) : r;
                    r = (r == -1) ? findHoleTraversingRight(room, room.length - 1): r;
                    result[r]++;
                }
            }
        }
        return result;
    }

    private static int findHoleTraversingTop(char[][] room, int y) {
        while (y >= 0) {
            if (room[0][y] != '-' && room[0][y] != '+') {
                return room[0][y] - '0';
            }
            y--;
        }
        return -1;
    }
    private static int findHoleTraversingBottom(char[][] room, int y) {
        while (y < room[0].length) {
            if (room[room.length - 1][y] != '-' && room[room.length - 1][y] != '+') {
                return room[room.length - 1][y] - '0';
            }
            y++;
        }
        return -1;
    }
    private static int findHoleTraversingLeft(char[][] room, int x) {
        while (x < room.length) {
            if (room[x][0] != '|' && room[x][0] != '+') {
                return room[x][0] - '0';
            }
            x++;
        }
        return -1;
    }
    private static int findHoleTraversingRight(char[][] room, int x) {
        while (x >= 0) {
            if (room[x][room[0].length - 1] != '|' && room[x][room[0].length - 1] != '+') {
                return room[x][room[0].length - 1] - '0';
            }
            x--;
        }
        return -1;
    }
}