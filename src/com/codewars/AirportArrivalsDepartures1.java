package com.codewars;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * https://www.codewars.com/kata/airport-arrivals-slash-departures-number-1
 */
public class AirportArrivalsDepartures1 {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ?!@#&()|<>.:=-+*/0123456789"; //Preloaded.ALPHABET;

    public static String[] flapDisplay(final String[] lines, final int[][] rotors) {
        // Your code here
        String[] result = new String[lines.length];

        for (int i = 0; i < lines.length; i++) {
            int[] rotor = rotors[i];
            String current = lines[i];
            for (int j = 0; j < rotor.length; j++) {
                int rotationAmt = rotor[j];
                //take first j as it is
                String tempResult = Arrays.stream(current.split(""))
                        .limit(j)
                        .collect(Collectors.joining());
                current = tempResult + Arrays.stream(current.split(""))
                        .skip(j)
                        .map(c -> {
                            int index = ALPHABET.indexOf(c);
                            return ALPHABET.charAt((index + rotationAmt) % ALPHABET.length()) + "";
                        })
                        .collect(Collectors.joining());
            }
            result[i] = current;
        }
        return result;
    }
}
