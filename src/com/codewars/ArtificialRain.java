package com.codewars;

/**
 * https://www.codewars.com/kata/artificial-rain/train/java
 */
public class ArtificialRain {
    public static int artificialRain(final int[] v) {
        int n = v.length;
        int [] leftToRightMax = new int[n];
        int [] rightToLeftMax = new int[n];

        //For each i, how long can it flow to the right
        int [] leftToRightFlow = new int[n];
        //For each i, how long can it flow to the left
        int [] rightToLeftFlow = new int[n];
        int result = 0;

        leftToRightMax[0] = 1;
        for (int i = 1; i < n; i++) {
            if (v[i] <= v[i - 1]) {
                leftToRightMax[i] = leftToRightMax[i - 1] + 1;
            } else {
                leftToRightMax[i] = 1;
            }
        }

        rightToLeftMax[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (v[i] <= v[i + 1]) {
                rightToLeftMax[i] = rightToLeftMax[i + 1] + 1;
            } else {
                rightToLeftMax[i] = 1;
            }
        }

        //To find origin of each max

        //Travese leftToRightMax and rightToLeftMax in other order
        leftToRightFlow[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (leftToRightMax[i] < leftToRightMax[i + 1]) {
                leftToRightFlow[i] = leftToRightFlow[i + 1] + 1;
            } else {
                leftToRightFlow[i] = 1;
            }
        }
        rightToLeftFlow[0] = 1;
        for (int i = 1; i < n; i++) {
            if (rightToLeftMax[i] < rightToLeftMax[i - 1]) {
                rightToLeftFlow[i] = rightToLeftFlow[i - 1] + 1;
            } else {
                rightToLeftFlow[i] = 1;
            }
        }

        //Compute result
        for (int i = 0; i < n; i++) {
            result = Math.max(result, leftToRightFlow[i] + rightToLeftFlow[i] - 1);
        }
        return result;
    }
}