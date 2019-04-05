package com.codewars;

/**
 * https://www.codewars.com/kata/fun-with-trees-is-perfect/
 */
public class FunWithTreesIsPerfect {
}

class TreeNode {
    TreeNode left;
    TreeNode right;

    public static boolean isPerfect(TreeNode root) {
        //Impl
        int maxDepth = findDepthOfLeftmostLeaf(root);
        return isPerfect(root, maxDepth, 1);
    }

    private static boolean isPerfect(TreeNode node, int maxLeftDepth, int currLevel) {
        if (node == null) {
            return true;
        }
        if (node.left == null && node.right == null) {
            return currLevel == maxLeftDepth;
        }
        if (node.left == null || node.right == null) {
            return false;
        }

        return isPerfect(node.left, maxLeftDepth, currLevel + 1)
                && isPerfect(node.right, maxLeftDepth, currLevel + 1);
    }

    /*
      Checks
      1) All leaves are at same level
      2) All internal nodes have two children
    */
    private static int findDepthOfLeftmostLeaf(TreeNode node) {
        int d = 0;
        while (node != null) {
            node = node.left;
            d++;
        }
        return d;
    }


    static TreeNode leaf() {
        return new TreeNode();
    }

    static TreeNode join(TreeNode left, TreeNode right) {
        return new TreeNode().withChildren(left, right);
    }

    TreeNode withLeft(TreeNode left) {
        this.left = left;
        return this;
    }

    TreeNode withRight(TreeNode right) {
        this.right = right;
        return this;
    }

    TreeNode withChildren(TreeNode left, TreeNode right) {
        this.left = left;
        this.right = right;
        return this;
    }

    TreeNode withLeftLeaf() {
        return withLeft(leaf());
    }

    TreeNode withRightLeaf() {
        return withRight(leaf());
    }

    TreeNode withLeaves() {
        return withChildren(leaf(), leaf());
    }
}