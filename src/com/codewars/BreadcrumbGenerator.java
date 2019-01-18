package com.codewars;

import java.util.Optional;
import java.util.Arrays;
import java.util.stream.Collectors;

import java.util.List;
import java.util.ArrayList;

/**
 * https://www.codewars.com/kata/breadcrumb-generator
 */
public class BreadcrumbGenerator {
    private static List<String> ignoreWords = Arrays.asList("the","of","in","from","by","with","and", "or", "for", "to", "at", "a");
    private static List<String> extensions = Arrays.asList(".html", ".htm", ".php", ".asp");

    public static String generate_bc(String url, String separator) {
        String home = "<a href=\"/\">HOME</a>";

        //Split to get the domain related part first.
        String [] rootRemainderParts = url.split("\\..*?/", 2); // '.*?' - for lazy
        String [] parts = rootRemainderParts.length > 1
                ? rootRemainderParts[1].split("/")
                : new String[0]; //Case when url is homepage

        List<String[]> results = new ArrayList<>();
        String prevPath = "/";
        for (int i = 0; i < parts.length; i++) {
            Optional<String[]> res = process(parts[i], prevPath);
            if (res.isPresent()) {
                prevPath = res.get()[0];
                results.add(res.get());
            }
        }

        String result;
        if (results.isEmpty()) {
            result = "<span class=\"active\">HOME</span>";
        } else {
            String middle = results.stream()
                    .limit(Math.max(0, results.size() - 1)) //except last
                    .map(row -> "<a href=\"" + row[0] + "\">" + row[1] + "</a>")
                    .collect(Collectors.joining(separator));

            result = home;

            if (!middle.isEmpty()) {
                result += separator + middle;
            }

            String last = "<span class=\"active\">" + results.get(results.size() - 1)[1]
                    + "</span>";
            result += separator + last;
        }
        return result;

    }
    // return String[]{hyperlink, displayText}
    private static Optional<String[]> process(String s, String prevPath) {
        String current = s;
        for (int i = 0; i < extensions.size(); i++) {
            if (s.contains(extensions.get(i))) {
                current = s.split(extensions.get(i))[0];
            }
        }
        current = current.split("#")[0]
                .split("\\?")[0];     //Ignore # and ?

        if (current.equals("index")
                || current.isEmpty()) { //anchor or query param after '/' at end
            return Optional.empty();
        }

        String displayText;

        if (!current.contains("-")) {
            displayText = current.toUpperCase();
        } else if (current.length() <= 30) {
            displayText = current.toUpperCase().replaceAll("-", " ");
        } else {
            displayText = Arrays.stream(current.split("-"))
                    .filter(part -> !ignoreWords.contains(part))
                    .map(part -> (part.charAt(0) + "").toUpperCase())
                    .collect(Collectors.joining());
        }
        return Optional.of(new String[] {prevPath +  s + "/", displayText});
    }
}
