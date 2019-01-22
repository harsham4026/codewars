package com.codewars;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * https://www.codewars.com/kata/find-the-smallest
 */
public class FindTheSmallest {
    public static long[] smallest(long n) {
        int index1 = Integer.MIN_VALUE, index2 = Integer.MIN_VALUE;
        List<Integer> digits = new ArrayList<>();
        long t = n;
        while (t > 0) {
            int rem = (int)(t % 10);
            digits.add(rem);
            t /= 10;
        }
        Collections.reverse(digits);

        long currentSmallest = n;
        for (int i = 0; i < digits.size(); i++) {
            for (int j = 0; j < digits.size(); j++) {
                if (i != j) {
                    long newNum = constructNumber(digits, i, j);

                    if (newNum < currentSmallest) {
                        currentSmallest = newNum;
                        index1 = i;
                        index2 = j;
                    } else if (newNum == currentSmallest
                            && (index1 > i || (index1 == i && index2 > j))) {
                        index1 = i;
                        index2 = j;
                    }
                }
            }
        }
        return new long[] {currentSmallest, index1, index2};
    }
    private static long constructNumber(List<Integer> digits, int index1, int index2) {
        long num = 0;
        for (int i = 0; i < digits.size(); i++) {
            if(i == index2) {  //Insert into index2
                if (index1 > index2) {
                    //Shift index2 (i) right by one
                    num = num * 10 + digits.get(index1);
                    num = num * 10 + digits.get(i);
                } else {
                    //Shift index2 (i) left by one
                    num = num * 10 + digits.get(i);
                    num = num * 10 + digits.get(index1);
                }

            } else if (i != index1) {
                num = num * 10 + digits.get(i);
            }
        }
        return num;
    }
}