package daohuei.leetcodelizard;

import daohuei.leetcodelizard.utils.TreeNode;

/*
 * 230. Kth Smallest Element in a BST
 * Link: https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 */
public class KthSmallestElementBST {

    int num = 0;
    int res;

    /**
     * @author: daohuei
     * @description: inorder traverse
     * @time: O(n): all nodes
     * @space: O(h): recursion stack with tree height
     */
    public int inorderMethod(TreeNode root, int k) {
        inorderTraversal(root, k);
        return res;
    }

    private void inorderTraversal(TreeNode node, int k) {
        // base case, reaching the end
        if (node == null) {
            return;
        }
        // inorder -> left mid right

        // left
        inorderTraversal(node.left, k);
        // mid
        // count 1
        num++;
        // if it is the kth
        if (num == k) {
            // set the result and then return
            res = node.val;
            return;
        }
        // right
        inorderTraversal(node.right, k);
    }

    /**
     * @author: daohuei
     * @description: split tree
     * @time: O(n): worst case: all nodes, but hardly worst
     * @space: O(h): recursion stack has worst case with tree height
     */
    public int splitTree(TreeNode root, int k) {
        int n = nodeCount(root.left);
        // if left tree has k-1 node
        // then root is the answer
        if (n + 1 == k) {
            return root.val;
        } else if (n + 1 < k) {
            // if n smaller than k - 1, means the answer is in the right tree
            return splitTree(root.right, k - n - 1);
        } else {
            // if n is larger than k - 1, answer in left tree
            return splitTree(root.left, k);
        }
    }

    // count the total node start from the root
    private int nodeCount(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + nodeCount(root.left) + nodeCount(root.right);
    }
}