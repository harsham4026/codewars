package com.codewars;

import java.util.Map;
import java.util.HashMap;

public class AlphabetWarAirstrike {

    public static String alphabetWar(String fight) {

        Map<Character, Integer> leftChars = new HashMap<>();
        leftChars.put('w', 4);
        leftChars.put('p', 3);
        leftChars.put('b', 2);
        leftChars.put('s', 1);
        Map<Character, Integer> rightChars = new HashMap<>();
        rightChars.put('m', 4);
        rightChars.put('q', 3);
        rightChars.put('d', 2);
        rightChars.put('z', 1);

        char[] arr = fight.toCharArray();
        int left = 0, right = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != '*') {
                boolean strike = false;
                if (i + 1 < arr.length && arr[i + 1] == '*') {
                    strike = true;
                }
                if (i - 1 >= 0 && arr[i - 1] == '*') {
                    strike = true;
                }
                if (!strike) {
                    if (leftChars.containsKey(arr[i])) {
                        left += leftChars.get(arr[i]);
                    } else if (rightChars.containsKey(arr[i])) {
                        right += rightChars.get(arr[i]);
                    }
                }
            }
        }

        if (left == right) {
            return "Let's fight again!";
        } else if (left > right) {
            return "Left side wins!";
        }
        return "Right side wins!";
    }
}