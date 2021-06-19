package daohuei.leetcodelizard;

import daohuei.leetcodelizard.utils.TreeNode;

/*
 * 110. Balanced Binary Tree
 * Link: https://leetcode.com/problems/balanced-binary-tree/
 */
public class BalancedBinaryTree {
    /**
     * @author: daohuei
     * @description: dfs
     * @time: O(n): n = all nodes
     * @space: O(logn): recursion stack with the height of the tree
     */
    public boolean dfs(TreeNode root) {
        // get the balanced tree depeth
        // if -1 means inbalanced
        return getBalancedTreeDepth(root) != -1;
    }

    private int getBalancedTreeDepth(TreeNode root) {
        // empty case
        if (root == null) {
            return 0;
        }
        int leftDepth = getBalancedTreeDepth(root.left);
        // if left tree depth inbalanced
        if (leftDepth == -1) {
            // whole tree inbalanced
            return -1;
        }
        // same as above
        int rightDepth = getBalancedTreeDepth(root.right);
        if (rightDepth == -1) {
            return -1;
        }
        // if the left and right tree got height diff more than 1
        if (Math.abs(leftDepth - rightDepth) > 1) {
            // inbalanced!
            return -1;
        }
        // else just return larger depth -> means current tree depth
        return Math.max(leftDepth, rightDepth) + 1;
    }
}