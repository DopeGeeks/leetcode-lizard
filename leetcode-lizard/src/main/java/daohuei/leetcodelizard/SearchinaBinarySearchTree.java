/*
 * Author: @ballm06m06
 * Qusetion: Search in a Binary Search Tree
 * Description: You are given the root of a binary search tree (BST) and an integer val.
 *              Find the node in the BST that the node's value equals val and return the subtree rooted with that node. 
 *             If such a node does not exist, return null.
 */       
/*
*   Time Complexity:  worst case: O(n), n stands for nth node, 
*                     average case: O(h), h stands for the height of the tree, O(logn)???
*   Space Complexity: O(1)
*/

public class SearchinaBinarySearchTree {

    //Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Recursion
    // 100.00%, 20.22%
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null)
            return null;
        if(root.val == val)
            return root;
        else if(root.val > val)
            return searchBST(root.left, val);
        else
            return searchBST(root.right, val);
    }

    // 100.00%, 63.52%
    public TreeNode searchBST1(TreeNode root, int val) { 
        while(root != null){
            if(root.val == val)
                return root;
            else if(root.val > val)
                root = root.left;
            else
                root = root.right;
        }
        return null;
    }

    // Input: root = [4,2,7,1,3], val = 2  
    // Output: [2,1,3]
}
