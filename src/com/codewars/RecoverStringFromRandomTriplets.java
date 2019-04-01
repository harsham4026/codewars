package com.codewars;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.function.Function;

import java.util.Optional;
import java.util.Collections;

/**
 * https://www.codewars.com/kata/recover-a-secret-string-from-random-triplets/
 */
public class RecoverStringFromRandomTriplets {


    private Map<Character, Set<Character>> adjList = new HashMap<>();
    private Map<Character, Long> indegrees = new HashMap<>();

    public String recoverSecret(char[][] triplets) {
        for (char[] strArr : triplets) {
            for (int i = 0; i < strArr.length - 1; i++) {
                adjList.computeIfAbsent(strArr[i], k -> new HashSet<>())
                        .add(strArr[i + 1]);
            }
        }

        indegrees = adjList.values().stream()
                .flatMap(Set::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Queue<Character> queue = new LinkedList<>();

        adjList.keySet().stream()
                .filter(v -> !indegrees.containsKey(v))
                .forEach(queue::add); //not adding to indegrees if 0.


        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()) {
            Character head = queue.remove();
            builder.append(head);
            System.out.println(head + " " + adjList.get(head));

            for (Character neighbour : Optional.ofNullable(adjList.get(head)).orElse(Collections.emptySet())) {
                if (indegrees.get(neighbour) == 1) {
                    indegrees.remove(neighbour);
                    queue.add(neighbour);
                } else {
                    indegrees.put(neighbour, indegrees.get(neighbour) - 1);
                }
            }
        }
        return builder.toString();
    }
}