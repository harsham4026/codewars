package com.codewars;

import java.util.Map;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.stream.Stream;

/**
 * https://www.codewars.com/kata/the-hunger-games-shut-the-gate/
 */
public class TheHungerGamesShutTheGate {
    public static String shutTheGate(final String farm) {
        Map<Character, TreeSet<Integer>> elementToIndex = new HashMap<>();
        Stream.of('H', 'C', 'R', 'A', 'V', '|')
                .forEach(c -> elementToIndex.put(c, new TreeSet<>()));

        for (int i = 0; i < farm.length(); i++) {
            if (farm.charAt(i) != '/' && farm.charAt(i) != '\\' && farm.charAt(i) != '.') {
                elementToIndex.get(farm.charAt(i)).add(i);
            }
        }
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < farm.length(); i++) {
            char curr = farm.charAt(i);
            if (curr == 'H' || curr == 'C' || curr == 'R') {
                Integer gateIndexToLeft = elementToIndex.get('|').floor(i);
                Integer gateIndexToRight = elementToIndex.get('|').ceiling(i);

                if (gateIndexToLeft == null || gateIndexToRight == null) {
                    builder.append(".");
                } else {
                    builder.append(curr);
                }
            } else if (curr == 'A') {
                if (isIndexReachableForElement(i, 'H', elementToIndex)) {
                    builder.append(".");
                } else {
                    builder.append(curr);
                }

            } else if (curr == 'V') {
                if (isIndexReachableForElement(i, 'H', elementToIndex) || isIndexReachableForElement(i, 'R', elementToIndex)) {
                    builder.append(".");
                } else {
                    builder.append(curr);
                }
            } else {
                builder.append(curr);
            }
        }
        return builder.toString();
    }

    private static boolean isIndexReachableForElement(int index, Character element, Map<Character, TreeSet<Integer>> elementToIndex) {
        Integer gateIndexToLeft = elementToIndex.get('|').floor(index);
        Integer gateIndexToRight = elementToIndex.get('|').ceiling(index);
        Integer elementIndexToLeft = elementToIndex.get(element).floor(index);
        Integer elementIndexToRight = elementToIndex.get(element).ceiling(index);

        //No gate
        if ((elementIndexToLeft != null && gateIndexToLeft == null) || (elementIndexToRight != null && gateIndexToRight == null)) {
            return true;
        }
        //Gate exists but outside element
        if ((elementIndexToLeft != null && gateIndexToLeft < elementIndexToLeft)
                || (elementIndexToRight != null && gateIndexToRight > elementIndexToRight)) {
            return true;
        }

        Integer leftmostElement = !elementToIndex.get(element).isEmpty() ?
                elementToIndex.get(element).first() : null;
        Integer rightmostElement = !elementToIndex.get(element).isEmpty() ?
                elementToIndex.get(element).last() : null;
        Integer leftmostGate = !elementToIndex.get('|').isEmpty() ?
                elementToIndex.get('|').first() : null;
        Integer rightmostGate = !elementToIndex.get('|').isEmpty() ?
                elementToIndex.get('|').last() : null;
        //Can come around
        if (((leftmostElement != null) && (leftmostGate == null || leftmostGate > leftmostElement) && gateIndexToRight == null)
                || ((rightmostElement != null) && (rightmostGate == null || rightmostGate < rightmostElement) && gateIndexToLeft == null)) {
            return true;
        }
        return false;
    }
}
