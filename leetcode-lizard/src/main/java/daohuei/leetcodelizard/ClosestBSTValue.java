package daohuei.leetcodelizard;

import daohuei.leetcodelizard.utils.TreeNode;

/*
 * 270. Closest Binary Search Tree Value
 * Link: https://leetcode.com/problems/closest-binary-search-tree-value/description/
 */
public class ClosestBSTValue {

    /**
     * @author: daohuei
     * @description: binary search method
     * @time: O(logn): simply binary search
     * @space: O(1): not using any
     */
    public int binarySearchMethod(TreeNode root, double target) {
        if (root == null)
            return 0;
        double closest = root.val;
        while (root != null) {
            if (root.val == target)
                return root.val;
            // find the smallest distance with current root
            if (Math.abs(target - closest) >= Math.abs(target - root.val)) {
                closest = root.val;
            }
            // if root is larger than the target
            if (root.val > target)
                // it is at the left tree
                root = root.left;
            else
                // else it is at the right tree
                root = root.right;
        }
        return (int) closest;
    }

    /**
     * @author: daohuei
     * @description: dfs method
     * @time: O(logn): simply binary search
     * @space: O(logn): for recursion stack
     */
    public int dfs(TreeNode root, double target) {
        // smaller should be left
        if (root.left != null && target < root.val) {
            // recur with left tree
            int leftResult = dfs(root.left, target);
            // choose the smaller one
            return Math.abs(leftResult - target) < Math.abs(root.val - target) ? leftResult : root.val;
        }
        // larger should be right
        if (root.right != null && target > root.val) {
            // recur with right tree
            int rightResult = dfs(root.right, target);
            // choose the smaller one
            return Math.abs(rightResult - target) < Math.abs(root.val - target) ? rightResult : root.val;
        }
        // else just return self value
        return root.val;
    }
}
