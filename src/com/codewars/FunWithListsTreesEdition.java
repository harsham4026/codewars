package com.codewars;

import javax.swing.tree.TreeNode;
import java.util.Set;
import java.util.TreeSet;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;

/**
 * https://www.codewars.com/kata/fun-with-lists-trees-edition/train/java
 */
public class FunWithListsTreesEdition {
    static TreeNode flatten(ListNode head) {
        ListNode t = head;
        Set<Integer> values = new TreeSet<>();
        while (t != null) {
            if (t.data != null) {
                traverseTree(t.data, values);
            }
            t = t.next;
        }
        if (values.isEmpty()) {
            return null;
        }

        Iterator<Integer> itr = values.iterator();
        TreeNode root = new TreeNode(itr.next());
        if (values.size() == 1) {
            return root;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (itr.hasNext()) {
            TreeNode node = queue.poll();
            TreeNode left = new TreeNode(itr.next());
            node.left = left;
            queue.add(left);
            if (itr.hasNext()) {
                TreeNode right = new TreeNode(itr.next());
                node.right = right;
                queue.add(right);
            }
        }
        return root;
    }

    private static void traverseTree(TreeNode node, Set<Integer> values) {
        if (node != null) {
            values.add(node.value);
            traverseTree(node.left, values);
            traverseTree(node.right, values);
        }
    }

    static class ListNode {
        public TreeNode data;
        public ListNode next;

        ListNode(TreeNode data, ListNode next) {
            this.data = data;
            this.next = next;
        }

        ListNode(TreeNode data) {
            this(data, null);
        }
    }

    static class TreeNode {

        public TreeNode left;
        public TreeNode right;
        public int value;

        TreeNode(int value, TreeNode left, TreeNode right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        TreeNode(int value) {
            this(value, null, null);
        }
    }
}