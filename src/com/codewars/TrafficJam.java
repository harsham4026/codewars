package com.codewars;

/**
 * https://www.codewars.com/kata/traffic-jam
 */
public class TrafficJam {
    public static String trafficJam(final String mainRoad, final String[] sideStreets) {
        //Util.display(mainRoad, sideStreets);
        StringBuilder sb;
        String currentRoad = mainRoad;
        for (int i = sideStreets.length - 1; i >= 0; i--) {
            if (!sideStreets[i].isEmpty()) {
                sb = new StringBuilder();
                int mainIndex = i;
                int j = sideStreets[i].length() - 1;

                for (; mainIndex < currentRoad.length() && j >= 0; mainIndex++, j--) {
                    sb.append(currentRoad.charAt(mainIndex));
                    sb.append(sideStreets[i].charAt(j));
                }
                while (mainIndex < currentRoad.length()) {
                    sb.append(currentRoad.charAt(mainIndex++));
                }
                while (j >= 0) {
                    sb.append(sideStreets[i].charAt(j--));
                }
                currentRoad = currentRoad.substring(0, i) + sb.toString();
            }
        }
        int indexOfX = currentRoad.indexOf("X");
        return currentRoad.substring(0, indexOfX + 1);
    }

}