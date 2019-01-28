package com.codewars;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://www.codewars.com/kata/directions-reduction
 */
public class DirectionsReduction {
    public static String[] dirReduc(String[] arr) {
        Stack<String> stack = new Stack<>();
        for (String current : arr) {
            String prev = !stack.empty() ? stack.peek() : null;

            switch(current) {
                case "NORTH" :
                    if ("SOUTH".equals(prev)) {
                        stack.pop();
                    } else {
                        stack.push(current);
                    }
                    break;
                case "SOUTH" :
                    if ("NORTH".equals(prev)) {
                        stack.pop();
                    } else {
                        stack.push(current);
                    }
                    break;
                case "EAST" :
                    if ("WEST".equals(prev)) {
                        stack.pop();
                    } else {
                        stack.push(current);
                    }
                    break;
                case "WEST" :
                    if ("EAST".equals(prev)) {
                        stack.pop();
                    } else {
                        stack.push(current);
                    }
                    break;
            }
        }
        return stack.stream().toArray(String[]::new);
    }

    public static String[] dirReducBrute(String[] arr) {
        String [] result = Arrays.copyOf(arr, arr.length);
        boolean change;
        String [] temp = new String[result.length];
        int size;
        while (true) {
            size = 0;
            change = false;
            temp = new String[result.length];
            for (int i = 0; i < result.length; ) {
                if (i != result.length - 1
                        && (result[i].equals("NORTH") && result[i + 1].equals("SOUTH")
                        || result[i].equals("SOUTH") && result[i + 1].equals("NORTH")
                        || result[i].equals("EAST") && result[i + 1].equals("WEST")
                        || result[i].equals("WEST") && result[i + 1].equals("EAST"))) {
                    i = i + 2;
                    change = true;
                } else {
                    temp[size++] = result[i];
                    i++;
                }
            }
            if (!change) {
                break;
            }
            result = Arrays.copyOf(temp, size);
        }
        return result;
    }
}
