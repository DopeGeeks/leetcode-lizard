package daohuei.leetcodelizard;

import daohuei.leetcodelizard.utils.TreeNode;

/*
 * 99. Recover Binary Search Tree
 * https://leetcode.com/problems/recover-binary-search-tree/
 */
public class RecoverBinarySearchTree {
    /**
     * @author: daohuei
     * @description: inorder traversal method
     * @time: O(n): go thru every nodes
     * @space: O(1): just use tree node for checking
     */
    // the object for swapping the tree
    // init with the min value
    TreeNode nodeA, nodeB, preNode = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree(TreeNode root) {
        dfs(root);

        // swap
        int temp = nodeA.val;
        nodeA.val = nodeB.val;
        nodeB.val = temp;
    }

    private void dfs(TreeNode node) {
        // base case
        if (node == null)
            return;
        // inorder, search left first
        dfs(node.left);

        // then root

        // if nodeA is null and previous root is larger than right child
        // which is wrong, so we update nodeA as pre and nodeB as current for the later
        // swapping
        if (nodeA == null && preNode.val > node.val)
            nodeA = preNode;
        if (nodeA != null && preNode.val > node.val)
            nodeB = node;
        // preNode denote to the previous root, so we store it for the next round
        preNode = node;

        // then right
        dfs(node.right);
    }
}