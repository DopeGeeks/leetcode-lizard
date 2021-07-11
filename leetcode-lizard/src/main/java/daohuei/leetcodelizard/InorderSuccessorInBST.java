package daohuei.leetcodelizard;

import daohuei.leetcodelizard.utils.TreeNode;

/*
 * 285. Inorder Successor in BST
 * Link: https://leetcode.com/problems/inorder-successor-in-bst/
 */
public class InorderSuccessorInBST {
    /**
     * @author: daohuei
     * @description: reverse inorder traverse
     * @time: O(n): n = all nodes for worst case
     * @space: O(1): not using any
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null)
            return null;
        TreeNode res = null;
        while (root != null) {
            // if less equal than, successor must be in the right tree
            if (root.val <= p.val) {
                root = root.right;
            }
            // if root value is larger, successor may be current root or node in left tree
            else {
                res = root;
                root = root.left;
            }
        }
        return res;
    }
}
