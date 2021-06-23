package daohuei.leetcodelizard;

import daohuei.leetcodelizard.utils.TreeNode;

/*
 * 235. Lowest Common Ancestor of a Binary Search Tree
 * Link: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
 */
public class LowestCommonAncestorOfABinarySearchTree {
    /**
     * @author: daohuei
     * @description: recursion
     * @time: O(logn) binary search
     * @space: O(h): level until the node
     */
    public TreeNode recursion(TreeNode root, TreeNode p, TreeNode q) {

        // make sure p.val <= q.val
        if (p.val > q.val) {
            return recursion(root, q, p);
        }
        // root case
        if (p.val == root.val || q.val == root.val) {
            return root;
        }
        // if q is in left tree (p in left as well)
        if (q.val < root.val) {
            // search left
            return recursion(root.left, p, q);
            // if p is in right tree (q in right as well)
        } else if (p.val > root.val) {
            // search right
            return recursion(root.right, p, q);
        } else {
            // else then it is the answer
            return root;
        }
    }

    /**
     * @author: daohuei
     * @description: iteration
     * @time: O(logn) binary search
     * @space: O(1): not using any
     */
    public TreeNode iteration(TreeNode root, TreeNode p, TreeNode q) {
        int pVal = p.val;
        int qVal = q.val;
        if (pVal == root.val || qVal == root.val) {
            return root;
        }
        // keep p.val <= q.val
        if (pVal > qVal) {
            int temp = pVal;
            pVal = qVal;
            qVal = temp;
        }
        while (true) {
            // if larger one in the left tree
            if (qVal < root.val) {
                // search left tree instead
                root = root.left;
            } else if (pVal > root.val) {
                // if smaller on in the right tree
                // search right tree instead
                root = root.right;
            } else {
                // else that the current tree root is the answer
                return root;
            }
        }
    }
}