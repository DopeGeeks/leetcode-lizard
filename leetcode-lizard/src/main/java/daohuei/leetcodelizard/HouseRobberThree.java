package daohuei.leetcodelizard;

import daohuei.leetcodelizard.utils.TreeNode;

/*
 * 337. House Robber III
 * Link: https://leetcode.com/problems/house-robber-iii/description/
 */
public class HouseRobberThree {
    /**
     * @author: daohuei
     * @description: dp + dfs
     * @time: O(n): go through every node in the binary tree
     * @space: O(h): actually only use contant 2 for dp array, but need height of
     *         tree space for recursion stack
     */

    public int dpDfsMethod(TreeNode root) {
        int[] dp = dfs(root);
        return Math.max(dp[0], dp[1]);
    }

    public int[] dfs(TreeNode root) {
        int[] dp = new int[2];
        if (root == null) {
            return dp;
        }
        int[] leftDP = dfs(root.left);
        int[] rightDP = dfs(root.right);
        // not picking root
        dp[0] = Math.max(leftDP[0], leftDP[1]) + Math.max(rightDP[0], rightDP[1]);
        // picking root
        dp[1] = root.val + leftDP[0] + rightDP[0];
        return dp;
    }
}