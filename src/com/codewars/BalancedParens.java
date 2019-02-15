package com.codewars;

import java.util.List;
import java.util.ArrayList;

/**
 * https://www.codewars.com/kata/all-balanced-parentheses
 */
public class BalancedParens {
    public static List<String> balancedParens(int n) {
        List<String> result = new ArrayList<>();
        parens(n, n, "", result);
        return result;
    }

    private static void parens(int open, int close, String s, List<String> result) {
        if (open == 0 && close == 0) {
            result.add(s);
        }
        if (open > 0) {
            parens(open - 1, close, s + "(", result);
        }
        if (close - open > 0) {
            parens(open, close - 1, s + ")", result);
        }
    }
}