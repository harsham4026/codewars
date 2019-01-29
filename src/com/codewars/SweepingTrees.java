package com.codewars;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Queue;
import java.util.LinkedList;

public class SweepingTrees {

    public Set<String> determineValidIds(List<String> dirtyTree) {
        Map<String, List<Node>> mappings = dirtyTree.stream()
                .map(element -> element.split(","))
                .map(arr -> new Node(arr[0], arr[1], arr[2].equals("valid")))
                .collect(Collectors.groupingBy(Node::getParent));

        Set<String> result = new HashSet<>();
        if (!mappings.isEmpty()) { //empty tree
            //findValidRecur(mappings.get("").get(0), mappings, result); //Only one root node
            findValidIterative(mappings, result);
        }
        return result;
    }

    //BFS
    private void findValidIterative(Map<String, List<Node>> mappings, Set<String> result) {
        Queue<Node> queue = new LinkedList<>();
        if (mappings.isEmpty()) {
            return;
        }
        queue.add(mappings.get("").get(0)); //Only one root

        while (!queue.isEmpty()) {
            Node head = queue.remove();
            if (head.isValid()) {
                result.add(head.getName());
                if (mappings.containsKey(head.getName())) { //has child
                    queue.addAll(mappings.get(head.getName()));
                }
            }
        }
    }

    //DFS
    private void findValidRecur(Node current, Map<String, List<Node>> mappings, Set<String> result) {
        if (current.isValid()) {
            result.add(current.getName());
            if (mappings.containsKey(current.getName())) { //has child
                for (Node node : mappings.get(current.getName())) {
                    findValidRecur(node, mappings, result);
                }
            }
        }
    }

    static class Node {
        private String name;
        private String parent;
        private boolean valid;

        Node(String name, String parent, boolean valid) {
            this.name = name;
            this.parent = parent;
            this.valid = valid;
        }

        String getName() {
            return name;
        }

        String getParent() {
            return parent;
        }

        boolean isValid() {
            return valid;
        }
    }
}