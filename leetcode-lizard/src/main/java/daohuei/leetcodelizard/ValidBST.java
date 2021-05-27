package daohuei.leetcodelizard;

import java.util.LinkedList;
import java.util.Stack;

import daohuei.leetcodelizard.utils.TreeNode;

/*
 * 98. Validate Binary Search Tree
 * Link: https://leetcode.com/problems/validate-binary-search-tree/
 */
public class ValidBST {
    /**
     * @author: daohuei
     * @description: recursion
     * @time: O(n) since we visit each node exactly once
     * @space: O(n) since we keep up to the entire tree.
     */
    public boolean recursionMethod(TreeNode root) {
        return getAns(root, null, null);
    }

    private boolean getAns(TreeNode node, Integer minValue, Integer maxValue) {
        // if reach the end
        if (node == null) {
            return true;
        }
        // the value min bound
        if (minValue != null && node.val <= minValue) {
            return false;
        }
        // the value max bound
        if (maxValue != null && node.val >= maxValue) {
            return false;
        }
        // check for left node(min bound remain, but max bound set to current value)
        // and right node(min->current value, max remain)
        return getAns(node.left, minValue, node.val) && getAns(node.right, node.val, maxValue);
    }

    /**
     * @author: daohuei
     * @description: dfs
     * @time: O(n) since we visit each node exactly once
     * @space: O(n) since we keep up to the entire tree in stack.
     */
    public boolean dfs(TreeNode root) {
        // empty or only contain single node
        if (root == null || root.left == null && root.right == null) {
            return true;
        }
        // use 3 stack for storing values
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> minValues = new LinkedList<>();
        LinkedList<Integer> maxValues = new LinkedList<>();
        // put first node into the stack
        TreeNode pNode = root;
        stack.push(pNode);
        minValues.push(null);
        maxValues.push(null);
        while (pNode != null || !stack.isEmpty()) {
            if (pNode != null) {
                Integer minValue = minValues.peek();
                Integer maxValue = maxValues.peek();
                TreeNode node = stack.peek();
                // check the conditions
                if (minValue != null && node.val <= minValue) {
                    return false;
                }
                if (maxValue != null && node.val >= maxValue) {
                    return false;
                }

                if (pNode.left != null) {
                    // put left node in stack
                    stack.push(pNode.left);
                    // min remain
                    minValues.push(minValue);
                    // new max value
                    maxValues.push(pNode.val);
                }
                // move to left
                pNode = pNode.left;
            } else {
                // if it is null, which mean reach the end, then get the parent node
                TreeNode node = stack.pop();
                minValues.pop();
                Integer maxValue = maxValues.pop();
                if (node.right != null) {
                    // put the right node in the stack
                    stack.push(node.right);
                    // new min will be current value
                    minValues.push(node.val);
                    // max remain
                    maxValues.push(maxValue);
                }
                // move to right
                pNode = node.right;
            }
        }
        return true;
    }

    /**
     * @author: daohuei
     * @description: inorder
     * @time: O(n) since we visit each node exactly once
     * @space: O(n) since we keep up to the entire tree in stack.
     */
    public boolean inorder(TreeNode root) {
        if (root == null)
            return true;
        Stack<TreeNode> stack = new Stack<>();
        // use to check parent value
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            // go to the deepest left
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            // take node out from stack
            root = stack.pop();
            // check condition
            if (pre != null && root.val <= pre.val)
                return false;
            // move to right
            pre = root;
            root = root.right;
        }
        return true;
    }
}
