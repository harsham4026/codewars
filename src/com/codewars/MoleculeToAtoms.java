package com.codewars;

import java.util.Map;
import java.util.HashMap;
import java.util.Stack;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.codewars.com/kata/molecule-to-atoms/
 */
public class MoleculeToAtoms {
    public static Map<String,Integer> getAtoms(String formula) {
        Stack<Character> stack = new Stack<>();
        Map<String,Integer> result = new HashMap<>();
        for (char c : formula.toCharArray()) {
            stack.push(c);
        }
        List<Character> openingBraces = Arrays.asList('(', '[', '{');
        List<Character> closingBraces = Arrays.asList(')', ']', '}');

        Map<Character, Stack<Integer>> bracketValues1 = new HashMap<>();

        Stack<Character> bracketsStack = new Stack<>();
        int currentResult = 1;
        int prevNum = 1;
        int bracketNum = 1;

        String val = "";
        int numVal = 0;
        while (!stack.isEmpty()) {
            Character topChar = stack.pop();

            if (Character.isLetter(topChar)) {
                if (Character.isLowerCase(topChar) && (!stack.isEmpty() && Character.isLowerCase(stack.peek()) || Character.isDigit(stack.peek()))) {
                    throw new IllegalArgumentException();
                }
                if (Character.isLowerCase(topChar)) {
                    val += topChar;
                    continue;
                }
                val += topChar;
                val = new StringBuilder(val).reverse().toString();
                Integer prev = result.computeIfAbsent(val, c -> 0);
                result.put(val, prev + currentResult);
                currentResult = currentResult / prevNum;
                prevNum = 1;
                val = "";

            } else if (Character.isDigit(topChar)) {
                Integer num = Integer.parseInt(String.valueOf(topChar));
                numVal = (numVal) * 10 + num;
                if (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    continue;
                }
                numVal = reverse(numVal);
                currentResult *= numVal;

                if (!stack.isEmpty() && closingBraces.contains(stack.peek())) {
                    bracketNum = numVal; //put into map here itself?
                } else if(!stack.isEmpty() && Character.isLetter(stack.peek())) {
                    prevNum = numVal;
                }
                numVal = 0;
            } else if(closingBraces.contains(topChar)) {
                bracketsStack.push(topChar);

                bracketValues1.computeIfAbsent(topChar, c -> new Stack<>());
                bracketValues1.get(topChar).push(bracketNum);

                bracketNum = 1;
            } else if(openingBraces.contains(topChar)) {

                if (bracketsStack.isEmpty() || closingBraces.indexOf(bracketsStack.pop()) != openingBraces.indexOf(topChar)) {
                    throw new IllegalArgumentException();
                }
                Integer p = bracketValues1.get(closingBraces.get(openingBraces.indexOf(topChar))).pop();
                currentResult = currentResult / p;
            }
        }
        if (!bracketsStack.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    private static int reverse(int a) {
        int r = 0;
        while (a > 0) {
            r = (r * 10) + (a % 10);
            a /= 10;
        }
        return r;
    }
}