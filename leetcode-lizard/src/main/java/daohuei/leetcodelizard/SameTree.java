package daohuei.leetcodelizard;

import daohuei.leetcodelizard.utils.TreeNode;

/*
 * 100. Same Tree
 * Link: https://leetcode.com/problems/same-tree/
 */
public class SameTree {

    /**
     * @author: daohuei
     * @description: inorder traversal method
     * @time: O(n): n = all nodes
     * @space: O(n): for inorder traversal recrusion stack
     */
    public boolean inorderTraversalMethod(TreeNode p, TreeNode q) {
        return inorderTraversal(p, q);
    }

    private boolean inorderTraversal(TreeNode p, TreeNode q) {
        // base case
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }
        // traverse left child
        if (!inorderTraversal(p.left, q.left)) {
            return false;
        }
        // current node
        if (p.val != q.val) {
            return false;
        }
        // traverse right child
        if (!inorderTraversal(p.right, q.right)) {
            return false;
        }
        return true;
    }
}