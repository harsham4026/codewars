package com.codewars;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * https://www.codewars.com/kata/word-wrap-1/
 */
public class WordWrap {
    public static String wrap(String text, int limit) {
        List<String> words = new ArrayList<>();
        String[] parts = text.split(" ");
        StringBuilder currentLineBuilder = new StringBuilder();

        for (int i = 0; i < parts.length; i++) {
            if (currentLineBuilder.length() + parts[i].length() <= limit) {
                currentLineBuilder.append(parts[i]).append(" ");
            } else {
                //fit as much as possible
                int max = limit - currentLineBuilder.length();
                if (parts[i].length() > limit && max > 0) {
                    currentLineBuilder.append(parts[i].substring(0, max));
                } else {
                    max = 0;
                    currentLineBuilder.deleteCharAt(currentLineBuilder.length() - 1); //delete " "
                }
                currentLineBuilder.append("\n");

                words.add(currentLineBuilder.toString());
                currentLineBuilder = new StringBuilder();

                int index = max;
                while (true) {
                    if (index + limit >= parts[i].length()) { //last part
                        currentLineBuilder.append(parts[i].substring(index, parts[i].length()))
                                .append(" ");
                        break;
                    } else {
                        words.add(parts[i].substring(index, index + limit) + "\n");
                    }
                    index += limit;
                }
            }
        }
        if (currentLineBuilder.length() != 0) {
            words.add(currentLineBuilder.deleteCharAt(currentLineBuilder.length() - 1)
                    .toString());
        }
        return words.stream()
                .collect(Collectors.joining(""));
    }

}