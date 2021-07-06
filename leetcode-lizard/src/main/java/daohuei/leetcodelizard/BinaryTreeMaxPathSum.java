package daohuei.leetcodelizard;

import daohuei.leetcodelizard.utils.TreeNode;

/*
 * 124. Binary Tree Maximum Path Sum
 * Link: https://leetcode.com/problems/binary-tree-maximum-path-sum/
 */
public class BinaryTreeMaxPathSum {
    /**
     * @author: daohuei
     * @description: dfs
     * @time: O(n): traverse all nodes
     * @space: O(h): the heights of the tree
     */

    // for storing the max number
    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        helper(root);
        return max;
    }

    // dfs method
    private int helper(TreeNode root) {
        // empty case
        if (root == null)
            return 0;

        // dfs with left and right
        int left = Math.max(helper(root.left), 0);
        int right = Math.max(helper(root.right), 0);

        // consider the left -> root -> right sum
        max = Math.max(max, root.val + left + right);

        // only return the sum of larger one
        return root.val + Math.max(left, right);
    }
}