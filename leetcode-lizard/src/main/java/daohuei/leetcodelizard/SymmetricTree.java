package daohuei.leetcodelizard;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import daohuei.leetcodelizard.utils.TreeNode;

/*
 * 101. Symmetric Tree
 * Link: https://leetcode.com/problems/symmetric-tree/
 */
public class SymmetricTree {
    /**
     * @author: daohuei
     * @description: recursion
     * @time: O(n): go through every node
     * @space: O(n): same
     */
    public boolean recursion(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetricHelper(root.left, root.right);
    }

    private boolean isSymmetricHelper(TreeNode left, TreeNode right) {
        // if only one of them is null, return false
        if (left == null && right != null || left != null && right == null) {
            return false;
        }
        if (left != null && right != null) {
            // both not null
            if (left.val != right.val) {
                // check if they are symmetric
                return false;
            }
            // keep looking their left and right children
            return isSymmetricHelper(left.left, right.right) && isSymmetricHelper(left.right, right.left);
        }
        // both null -> reach the end -> true
        return true;
    }

    /**
     * @author: daohuei
     * @description: iteration dfs
     * @time: O(n): go through every node
     * @space: O(n): same but using stack
     */
    public boolean dfs(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stackLeft = new Stack<>();
        Stack<TreeNode> stackRight = new Stack<>();
        TreeNode curLeft = root.left;
        TreeNode curRight = root.right;
        while (curLeft != null || !stackLeft.isEmpty() || curRight != null || !stackRight.isEmpty()) {
            // if not empty
            while (curLeft != null) {
                stackLeft.push(curLeft);
                curLeft = curLeft.left; // go to the end of the left child
            }
            while (curRight != null) {
                stackRight.push(curRight);
                curRight = curRight.right; // go to the end of the right child
            }
            // if length not match means not symmetric
            if (stackLeft.size() != stackRight.size()) {
                return false;
            }
            // now pop one left and one right
            curLeft = stackLeft.pop();
            curRight = stackRight.pop();

            // check if symmetric
            if (curLeft.val != curRight.val) {
                return false;
            }
            // see another side if not null
            curLeft = curLeft.right;
            curRight = curRight.left;
        }
        return true;
    }

    /**
     * @author: daohuei
     * @description: iteration bfs
     * @time: O(n): go through every node
     * @space: O(n): same but using queue
     */
    public boolean bfs(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> leftTree = new LinkedList<>();
        Queue<TreeNode> rightTree = new LinkedList<>();
        // start with left and right
        leftTree.offer(root.left);
        rightTree.offer(root.right);
        while (!leftTree.isEmpty() && !rightTree.isEmpty()) {
            TreeNode curLeft = leftTree.poll();
            TreeNode curRight = rightTree.poll();
            // if null then not symmetric
            if (curLeft == null && curRight != null || curLeft != null && curRight == null) {
                return false;
            }
            // if both not null, then keep checking
            if (curLeft != null && curRight != null) {
                // check if symmetric
                if (curLeft.val != curRight.val) {
                    return false;
                }
                // add left left then add left right
                leftTree.offer(curLeft.left);
                leftTree.offer(curLeft.right);

                // add right right then add right left
                rightTree.offer(curRight.right);
                rightTree.offer(curRight.left);
            }

        }
        // if either of tree not empty, it is not symmetry
        if (!leftTree.isEmpty() || !rightTree.isEmpty()) {
            return false;
        }
        return true;
    }
}