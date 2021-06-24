package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import daohuei.leetcodelizard.utils.TreeNode;

/*
 * 103. Binary Tree Zigzag Level Order Traversal
 * Link: https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    /**
     * @author: daohuei
     * @description: dfs
     * @time: O(n): all nodes
     * @space: O(h): recursion stack with tree height
     */
    public List<List<Integer>> zigzagLevelOrderDFS(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        DFS(root, 0, ans);
        return ans;
    }

    private void DFS(TreeNode root, int level, List<List<Integer>> ans) {
        // empty case
        if (root == null) {
            return;
        }
        // if the current level answer not existed yet
        if (ans.size() <= level) {
            ans.add(new ArrayList<Integer>());
        }
        // if even level, add to the end
        if ((level % 2) == 0) {
            ans.get(level).add(root.val);
        } else {
            // if odd level, add to the front
            ans.get(level).add(0, root.val);
        }
        // recur for left tree
        DFS(root.left, level + 1, ans);
        // recur for right tree
        DFS(root.right, level + 1, ans);
    }

    /**
     * @author: daohuei
     * @description: bfs
     * @time: O(n): all nodes
     * @space: O(w): max width for queue
     */
    public List<List<Integer>> zigzagLevelOrderBFS(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        if (root == null)
            return ans;
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            // queue size indicate current level nodes number
            int levelNum = queue.size();
            List<Integer> subList = new LinkedList<Integer>();
            // traverse all node in the current level
            for (int i = 0; i < levelNum; i++) {
                TreeNode curNode = queue.poll();
                if (curNode != null) {
                    // even => end, odd => start
                    if ((level % 2) == 0) {
                        subList.add(curNode.val);
                    } else {
                        subList.add(0, curNode.val);
                    }
                    // put the next level node
                    queue.offer(curNode.left);
                    queue.offer(curNode.right);
                }
            }
            // if contains nodes
            if (subList.size() > 0) {
                // add answer
                ans.add(subList);
            }
            // move to the next level
            level++;
        }
        return ans;
    }
}