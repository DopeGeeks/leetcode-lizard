package daohuei.leetcodelizard;

import daohuei.leetcodelizard.utils.TreeNode;

/*
 * 298. Binary Tree Longest Consecutive Sequence
 * Link: https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/description/
 */
/*
Given a binary tree, find the length of the longest consecutive sequence path.
The path refers to any sequence of nodes from some starting node to any node in the tree 
along the parent-child connections. The longest consecutive path need to be from parent to child 
(cannot be the reverse).
For example,
   1
    \\
     3
    / \\
   2   4
        \\
         5
Longest consecutive sequence path is 3-4-5, so return 3.
   2
    \\
     3
    / 
   2    
  / 
 1
Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
Tags:Tree
Similar Problems: (H) Longest Consecutive Sequence
*/
public class BinaryTreeLongestConsecutiveSequence {
    /**
     * @author: daohuei
     * @description: dfs
     * @time: O(n): go through all nodes
     * @space: O(h): for the height of tree as the recursion stack
     */

    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfs(root, 1);
    }

    private int dfs(TreeNode node, int depth) {
        if (node == null) {
            return depth;
        }

        // check if consecutive and not reaching the end, if so, depth count 1, else
        // restart from 1
        int leftDepth = (node.left != null && node.val + 1 == node.left.val) ? dfs(node.left, depth + 1)
                : dfs(node.left, 1);
        int rightDepth = (node.right != null && node.val + 1 == node.right.val) ? dfs(node.right, depth + 1)
                : dfs(node.right, 1);
        // choose the larger one
        return Math.max(depth, Math.max(leftDepth, rightDepth));
    }
}
