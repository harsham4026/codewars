package com.codewars;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * https://www.codewars.com/kata/predictive-contact-list
 */
public class PredictiveContactList {
    public static List<String> predict(List<String> contacts, String keystrokes) {
        List<String> words = Arrays.asList("abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz");
        List<String> resultContacts = new ArrayList<>();

        int index;
        boolean valid;
        for (int i = 0; i < contacts.size(); i++) {
            String currContact = contacts.get(i);
            index = 0;
            valid = true;
            for (char c : keystrokes.toCharArray()) {
                int key = c - '0';
                if (words.get(key - 2).contains(currContact.charAt(index) + "")) {
                    index++;
                } else {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                resultContacts.add(currContact);
            }
        }
        return resultContacts;
    }
}