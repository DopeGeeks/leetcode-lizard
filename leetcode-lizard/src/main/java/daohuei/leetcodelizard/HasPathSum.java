package daohuei.leetcodelizard;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import daohuei.leetcodelizard.utils.TreeNode;

/*
 * 112. Path Sum
 * Link: https://leetcode.com/problems/path-sum/
 */
public class HasPathSum {

    /**
     * @author: daohuei
     * @description: recursion
     * @time: O(n) go through all nodes
     * @space: O(h) height of the tree for recursion stack
     */
    public boolean recursion(TreeNode root, int targetSum) {
        // empty case
        if (root == null) {
            return false;
        }
        return hasPathSumHelper(root, targetSum);
    }

    private boolean hasPathSumHelper(TreeNode root, int sum) {
        // when arriving leaf
        if (root.left == null && root.right == null) {
            return root.val == sum;
        }
        // if no left tree
        if (root.left == null) {
            // need to except the number of current from sum
            return hasPathSumHelper(root.right, sum - root.val);
        }
        // if no right tree
        if (root.right == null) {
            // need to except the number of current from sum
            return hasPathSumHelper(root.left, sum - root.val);
        }
        // if left and right both exist
        return hasPathSumHelper(root.left, sum - root.val) || hasPathSumHelper(root.right, sum - root.val);
    }

    /**
     * @author: daohuei
     * @description: dfs
     * @time: O(n) go through all nodes
     * @space: O(h) height of the tree for stack
     */
    public boolean dfs(TreeNode root, int targetSum) {
        // a stack store node to be visited
        Stack<TreeNode> toVisit = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null;
        int curSum = 0;
        while (cur != null || !toVisit.isEmpty()) {
            while (cur != null) {
                // go to the left end
                toVisit.push(cur);
                curSum += cur.val;
                cur = cur.left;
            }
            // get the node
            cur = toVisit.peek();
            // if it is leaf, check sum
            if (curSum == targetSum && cur.left == null && cur.right == null) {
                return true;
            }
            // if there is no right node or its right have been traversed before
            if (cur.right == null || cur.right == pre) {
                // pop this value, not considering it any more
                TreeNode pop = toVisit.pop();
                curSum -= pop.val; // remove it from the current sum
                pre = cur;
                // if cur is null, next loop will peek next node(should be parent node)
                cur = null;
            } else {
                // if right have not checked yet
                // check right node
                cur = cur.right;
            }
        }
        return false;
    }

    /**
     * @author: daohuei
     * @description: bfs
     * @time: O(n) go through all nodes
     * @space: O(w) width of the tree for queue
     */
    public boolean bfs(TreeNode root, int targetSum) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        Queue<Integer> queueSum = new LinkedList<Integer>();
        if (root == null)
            return false;
        queue.offer(root);
        queueSum.offer(root.val);
        while (!queue.isEmpty()) {
            // amount of nodes of current level
            int levelNum = queue.size();
            // loop over current level
            for (int i = 0; i < levelNum; i++) {
                // get the node and current sum value
                TreeNode curNode = queue.poll();
                int curSum = queueSum.poll();
                if (curNode != null) {
                    // check sum if it is leaf
                    if (curNode.left == null && curNode.right == null && curSum == targetSum) {
                        return true;
                    }
                    // if left not null
                    if (curNode.left != null) {
                        // push left and updated sum including left
                        queue.offer(curNode.left);
                        queueSum.offer(curSum + curNode.left.val);
                    }
                    if (curNode.right != null) {
                        // push right and updated sum including right
                        queue.offer(curNode.right);
                        queueSum.offer(curSum + curNode.right.val);
                    }
                }
            }
        }
        return false;
    }
}
