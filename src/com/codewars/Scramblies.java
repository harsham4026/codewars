package com.codewars;

/**
 * https://www.codewars.com/kata/scramblies
 */
public class Scramblies {

    public static boolean scramble(String str1, String str2) {
        int [] arr = new int[26];
        for (int i = 0; i < str1.length(); i++) {
            arr[str1.charAt(i) - 'a']++;
        }
        for (int i = 0; i < str2.length(); i++) {
            if (arr[str2.charAt(i) - 'a'] == 0) {
                return false;
            }
            arr[str2.charAt(i) - 'a']--;
        }
        return true;
    }
}