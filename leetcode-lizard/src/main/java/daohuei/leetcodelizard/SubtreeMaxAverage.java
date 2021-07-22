package daohuei.leetcodelizard;

import daohuei.leetcodelizard.utils.TreeNode;

/*
 * 1120. Maximum Average Subtree
 * Link: https://leetcode.com/problems/maximum-average-subtree
 */
public class SubtreeMaxAverage {

    double maxAvg;

    /**
     * @author: daohuei
     * @description: dfs
     * @time: O(n): for searching through all nodes
     * @space: O(h): the recursion stack will need the height of the tree as space
     */
    public double maximumAverageSubtree(TreeNode root) {
        maxAvg = 0.0;
        helper(root);
        return maxAvg;
    }

    // dfs search method
    private int[] helper(TreeNode root) {
        // [sum of subtree, # of nodes in the subtree]
        int[] data = new int[2];
        if (root == null) {
            return data;
        }
        int[] dataLeft = helper(root.left);
        int[] dataRight = helper(root.right);
        // get the sum of subtree
        data[0] += root.val + dataLeft[0] + dataRight[0];
        // get the # of nodes of subtree
        data[1] += 1 + dataLeft[1] + dataRight[1];
        double avg = 0.0;
        // we do not considering the node with no descendants
        if (data[1] > 1) {
            // if valid, calculate the average
            avg = ((double) data[0]) / data[1];
        }
        maxAvg = Math.max(maxAvg, avg);
        return data;
    }
}
