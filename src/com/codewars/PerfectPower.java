package com.codewars;

/**
 * https://www.codewars.com/kata/whats-a-perfect-power-anyway/
 */
public class PerfectPower {

    //Brute force TLE
    public static int[] isPerfectPowerBrute(int n) {
        boolean flag = false;
        for (int x = 2; x <= Math.sqrt(n); x++) {
            int y = 2;
            int curr = x * x;
            while (curr <= n) {
                if (curr == n) {
                    flag = true;
                    break;
                }
                y++;
                curr *= x;
            }
            if (flag) {
                return new int[]{x, y};
            }
        }
        return null;
    }

    public static int[] isPerfectPower(int n) {
        for (int x = 2; x <= Math.sqrt(n); x++) {
            double val = Math.log(n) / Math.log(x);
            if (Math.abs(val - Math.round(val)) < 0.00000001) {
                return new int[]{x, (int) Math.round(val)};
            }
        }
        return null;
    }
}
