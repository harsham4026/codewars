package com.codewars;

/**
 * https://www.codewars.com/kata/simple-encryption-number-1-alternating-split
 */
public class AlternatingSplit {
    public static String encrypt(final String text, final int n) {
        if (n <= 0 || text == null || text.isEmpty()) {
            return text;
        }
        String inputText = text;
        int t = n;
        while (t-- > 0) {
            StringBuilder tempBuilder = new StringBuilder();
            for (int i = 1; i < inputText.length(); i += 2) {
                tempBuilder.append(inputText.charAt(i));
            }
            for (int i = 0; i < inputText.length(); i += 2) {
                tempBuilder.append(inputText.charAt(i));
            }
            inputText = tempBuilder.toString();
        }
        return inputText;
    }

    public static String decrypt(final String encryptedText, final int n) {
        if (n <= 0 || encryptedText == null || encryptedText.isEmpty()) {
            return encryptedText;
        }
        String encryptedInputText = encryptedText;
        int t = n;
        while (t-- > 0) {
            int mid = encryptedText.length() / 2;
            int i = 0;
            int j = mid;
            StringBuilder tempBuilder = new StringBuilder();
            for (; i < mid; i++, j++) {
                tempBuilder.append(encryptedInputText.charAt(j));
                tempBuilder.append(encryptedInputText.charAt(i));
            }
            //Add last character if length is odd
            if (encryptedText.length() % 2 == 1) {
                tempBuilder.append(encryptedText.charAt(encryptedText.length() - 1));
            }
            encryptedInputText = tempBuilder.toString();
        }
        return encryptedInputText;
    }
}
