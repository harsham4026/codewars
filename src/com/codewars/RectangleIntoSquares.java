package com.codewars;

import java.util.List;
import java.util.ArrayList;

/**
 * https://www.codewars.com/kata/rectangle-into-squares/
 */
public class RectangleIntoSquares {
    public static List<Integer> sqInRect(int lng, int wdth) {
        if (lng == wdth) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        int dim1 = Math.max(lng, wdth);
        int dim2 = Math.min(lng, wdth); //smaller one

        while (dim1 != dim2) {
            result.add(dim2);
            int diff = dim1 - dim2;
            dim1 = Math.max(dim2, diff);
            dim2 = Math.min(dim2, diff);
        }
        result.add(dim2);
        return result;
    }
}
