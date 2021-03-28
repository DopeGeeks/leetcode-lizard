package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import daohuei.leetcodelizard.utils.TreeNode;

/*
 * 94. Binary Tree Level Order Traversal
 * Link: https://leetcode.com/problems/binary-tree-level-order-traversal/
 */
public class LevelOrder {

    /**
     * @author: daohuei
     * @description: implement DFS on every level of tree
     * @time: O(n): n = total nodes. Since we traverse all nodes.
     * @space: O(h): h = height of the tree. Since we stack the recur at most h
     *         times.
     */
    public List<List<Integer>> dfsMethod(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        dfs(root, 0, results);
        return results;
    }

    public void dfs(TreeNode tree, int level, List<List<Integer>> results) {
        if (tree == null) {
            return;
        }
        if (results.size() <= level) {
            results.add(new ArrayList<Integer>());
        }
        results.get(level).add(tree.val);
        dfs(tree.left, level + 1, results);
        dfs(tree.right, level + 1, results);
    }

    /**
     * @author: daohuei
     * @description: implement BFS with queue
     * @time: O(n): n = total nodes. Since we traverse all nodes.
     * @space: O(m): m = the max number of nodes of a level
     */
    public List<List<Integer>> bfsMethod(TreeNode root) {
        List<List<Integer>> results = new LinkedList<List<Integer>>();
        if (root == null) {
            return results;
        }

        Queue<TreeNode> traverseQueue = new LinkedList<TreeNode>();
        traverseQueue.offer(root);
        while (!traverseQueue.isEmpty()) {
            int count = traverseQueue.size();
            List<Integer> currentList = new LinkedList<Integer>();
            for (int i = 0; i < count; i++) {
                TreeNode current = traverseQueue.poll();
                if (current != null) {
                    currentList.add(current.val);
                    traverseQueue.offer(current.left);
                    traverseQueue.offer(current.right);
                }
            }
            if (currentList.size() > 0) {
                results.add(currentList);
            }
        }

        return results;
    }
}
