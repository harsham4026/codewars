package com.codewars;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * https://www.codewars.com/kata/simple-pig-latin
 */
public class SimplePigLatin {
    public static String pigIt(String str) {
        return Arrays.stream(str.split(" "))
                .map(word -> containsAtleastOneCharacter(word) ? transform(word) : word)
                .collect(Collectors.joining(" "));
    }
    private static boolean containsAtleastOneCharacter(String string) {
        return string.chars()
                .map(c -> (char)c)
                .anyMatch(Character::isLetterOrDigit);
    }

    private static String transform(String string) {
        return string.substring(1) + string.charAt(0) + "ay";
    }
}