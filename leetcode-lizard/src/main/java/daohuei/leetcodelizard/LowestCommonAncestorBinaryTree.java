package daohuei.leetcodelizard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

import daohuei.leetcodelizard.utils.TreeNode;

/*
 * 236. Lowest Common Ancestor of a Binary Tree
 * Link: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 */
public class LowestCommonAncestorBinaryTree {
    /**
     * @author: daohuei
     * @description: inorder traversal with stack
     * @time: O(n): all nodes
     * @space: O(h): recursion stack with tree height
     */
    public TreeNode inorderMethod(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q) {
            return root;
        }
        Stack<TreeNode> stack = new Stack<>();
        // check if p and q are both left with inoder traversal
        TreeNode cur = root.left;
        boolean pLeft = false;
        boolean qLeft = false;
        while (cur != null || !stack.isEmpty()) {
            // go to the left deep
            while (cur != null) {
                // push along the way
                stack.push(cur);
                cur = cur.left;
            }
            // pop out
            cur = stack.pop();
            // if p found
            if (cur == p) {
                pLeft = true;
            }
            // if q found
            if (cur == q) {
                qLeft = true;
            }
            // if they both in left tree
            if (pLeft && qLeft) {
                break;
            }
            // go to right
            cur = cur.right;
        }

        // check left
        if (pLeft && qLeft) {
            return lowestCommonAncestor(root.left, p, q);
            // check right
        } else if (!pLeft && !qLeft) {
            return lowestCommonAncestor(root.right, p, q);
        }
        // one left one right
        return root;
    }

    /**
     * @author: daohuei
     * @description: inorder traversal with stack
     * @time: O(n): all nodes
     * @space: O(h): recursion stack with tree height
     */
    public TreeNode recursionMethod(TreeNode root, TreeNode p, TreeNode q) {
        // base case
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode leftCommonAncestor = lowestCommonAncestor(root.left, p, q);
        TreeNode rightCommonAncestor = lowestCommonAncestor(root.right, p, q);
        // if not found in the left
        if (leftCommonAncestor == null) {
            // return right
            return rightCommonAncestor;
        }
        // if not found in the right
        if (rightCommonAncestor == null) {
            // return left
            return leftCommonAncestor;
        }
        // if not left or not right, return root
        return root;
    }

    /**
     * @author: daohuei
     * @description: bfs
     * @time: O(n): all nodes
     * @space: O(n): all parent nodes -> just all nodes
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> stack = new Stack<>();
        HashMap<TreeNode, TreeNode> parent = new HashMap<>();
        stack.push(root);
        parent.put(root, null);
        // store every parent in the hash map with bfs
        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode cur = stack.pop();
            if (cur.left != null) {
                stack.push(cur.left);
                parent.put(cur.left, cur);
            }
            if (cur.right != null) {
                stack.push(cur.right);
                parent.put(cur.right, cur);
            }
        }
        HashSet<TreeNode> path = new HashSet<>();
        // put all parents of p into the path
        while (p != null) {
            path.add(p);
            p = parent.get(p);
        }

        // if any parent in q appears in the path
        // it is the answer
        while (q != null) {
            if (path.contains(q)) {
                break;
            }
            q = parent.get(q);
        }
        return q;
    }
}