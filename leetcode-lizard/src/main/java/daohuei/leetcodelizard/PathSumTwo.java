package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.List;

import daohuei.leetcodelizard.utils.TreeNode;

/*
 * 113. Path Sum II
 * Link: https://leetcode.com/problems/path-sum-ii/description/
 */
public class PathSumTwo {
    /**
     * @author: daohuei
     * @description: dfs and backtracking
     * @time: O(n): traverse all nodes
     * @space: O(h): for recursion stack needs height of the tree
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // depth frist search
        dfs(result, new ArrayList<Integer>(), root, targetSum);
        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> list, TreeNode node, int sum) {
        if (node == null)
            return;
        // add the node to the temp list
        list.add(node.val);
        // check leaf if they reach the leaf and qualified for the sum
        if (node.left == null && node.right == null && node.val == sum) {
            // if so add to the answer and remove the last added number
            result.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
            return;
        }
        // search for left and right tree without by subtracting current val
        dfs(result, list, node.left, sum - node.val);
        dfs(result, list, node.right, sum - node.val);
        // backtracking
        list.remove(list.size() - 1);
    }
}