package daohuei.leetcodelizard;

import daohuei.leetcodelizard.utils.TreeNode;

/*
 * 226. Invert Binary Tree
 * Link: https://leetcode.com/problems/invert-binary-tree/
 */
public class InvertBinaryTree {

    /**
     * @author: daohuei
     * @description: recursion
     * @time: O(n): go through every node
     * @space: O(n): same using recursion stack
     */
    public TreeNode invertTree(TreeNode root) {
        // base case
        if (root == null) {
            return root;
        }

        // invert left right node
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // recur it with left child and right child
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}