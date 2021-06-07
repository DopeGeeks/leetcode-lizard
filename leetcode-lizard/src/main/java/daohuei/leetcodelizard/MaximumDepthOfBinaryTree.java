package daohuei.leetcodelizard;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import daohuei.leetcodelizard.utils.TreeNode;

/*
 * 104. Maximum Depth of Binary Tree
 * Link: https://leetcode.com/problems/maximum-depth-of-binary-tree/
 */
public class MaximumDepthOfBinaryTree {
    /**
     * @author: daohuei
     * @description: dfs
     * @time: O(n): go through every nodes
     * @space: O(h): height for the tree
     */
    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // just looking for left node and right node's depth
        // search for larger one
        // and plus the current
        return Math.max(dfs(root.left), dfs(root.right)) + 1;
    }

    /**
     * @author: daohuei
     * @description: use queue to implement bfs
     * @time: O(n): go through every nodes
     * @space: O(w): most amount of nodes of a single level
     */
    public int bfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        // empty case
        if (root == null)
            return 0;
        // put root into the queue first
        queue.offer(root);
        // init level as 0
        int level = 0;
        while (!queue.isEmpty()) {
            // node amount for the current level
            int levelNum = queue.size();
            // go through the nodes
            for (int i = 0; i < levelNum; i++) {
                // get the node
                TreeNode curNode = queue.poll();
                // if not null
                if (curNode != null) {
                    // push the left node if not null
                    if (curNode.left != null) {
                        queue.offer(curNode.left);
                    }
                    // push the right node if not null
                    if (curNode.right != null) {
                        queue.offer(curNode.right);
                    }
                }
            }
            // level up
            level++;
        }
        return level;
    }
}