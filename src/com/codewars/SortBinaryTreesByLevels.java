package com.codewars;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://www.codewars.com/kata/sort-binary-tree-by-levels
 */
public class SortBinaryTreesByLevels {

    public static List<Integer> treeByLevels(Node node) {
        List<Integer> result = new ArrayList<>();
        if (node == null) {
            return result;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node n = queue.remove();
            result.add(n.value);
            if (n.left != null) {
                queue.add(n.left);
            }
            if (n.right != null) {
                queue.add(n.right);
            }
        }
        return result;
    }
    public class Node {
        public Node left;
        public Node right;
        public int value;

        public Node(Node l, Node r, int v) {
            left = l;
            right = r;
            value = v;
        }
    }
}