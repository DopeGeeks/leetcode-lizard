package daohuei.leetcodelizard;

import java.util.LinkedList;
import java.util.Queue;

import daohuei.leetcodelizard.utils.TreeNode;

/*
 * 111. Minimum Depth of Binary Tree
 * Link: https://leetcode.com/problems/minimum-depth-of-binary-tree/description/
 */
public class MinDepthBinaryTree {
    /**
     * @author: daohuei
     * @description: dfs
     * @time: O(n) all nodes
     * @space: O(h): go through heights of recursion stack
     */
    public int dfs(TreeNode root) {
        // empty case
        if (root == null) {
            return 0;
        }

        if (root.left != null && root.right != null) {
            // find min one of left and right
            return Math.min(dfs(root.left), dfs(root.right)) + 1;
        } else {
            // if one of them are null, then we do not consider(will be 0)
            // so max will help us get the right one
            return Math.max(dfs(root.left), dfs(root.right)) + 1;
        }
    }

    /**
     * @author: daohuei
     * @description: bfs
     * @time: O(n) all nodes
     * @space: O(w): max width
     */
    public int bfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        // empty case
        if (root == null)
            return 0;
        // init
        queue.offer(root);
        int level = 1;
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            for (int i = 0; i < levelNum; i++) {
                TreeNode curNode = queue.poll();
                if (curNode != null) {
                    // if both null, return current level
                    if (curNode.left == null && curNode.right == null) {
                        return level;
                    }
                    // if left exist, simply put it into the queue
                    if (curNode.left != null) {
                        queue.offer(curNode.left);
                    }
                    // same as above but right
                    if (curNode.right != null) {
                        queue.offer(curNode.right);
                    }
                }
            }
            level++;
        }
        return level;
    }
}