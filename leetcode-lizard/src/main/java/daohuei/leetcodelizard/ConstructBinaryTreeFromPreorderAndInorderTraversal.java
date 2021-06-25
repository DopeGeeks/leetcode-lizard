package daohuei.leetcodelizard;

import java.util.Stack;

import daohuei.leetcodelizard.utils.TreeNode;

/*
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * Link: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    /**
     * @author: daohuei
     * @description: dfs
     * @time: O(n): all nodes
     * @space: O(h): recursion stack for tree height
     */
    public TreeNode dfs(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, inorder, (long) Integer.MAX_VALUE + 1);
    }

    int pre = 0;
    int in = 0;

    private TreeNode buildTreeHelper(int[] preorder, int[] inorder, long stop) {
        // if reach the end of preorder
        if (pre == preorder.length) {
            // stop and return null
            return null;
        }

        // if the inorder is reaching stop point
        if (inorder[in] == stop) {
            // inorder move forward
            in++;
            return null;
        }
        // get the root value (will be the first value of preorder)
        int root_val = preorder[pre++];
        TreeNode root = new TreeNode(root_val);

        // construct the left tree with current root as the new stop point
        // since for inorder, if we found the current root, means left tree has done
        // traversing
        root.left = buildTreeHelper(preorder, inorder, root_val);
        // construct the right tree with the current stop point
        // since for right tree need to consider the parent of the root as stop point,
        // which is also the current stop point
        root.right = buildTreeHelper(preorder, inorder, stop);
        return root;
    }

    /**
     * @author: daohuei
     * @description: stack
     * @time: O(n): all nodes
     * @space: O(h): stack for tree height
     */
    public TreeNode stackMethod(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        Stack<TreeNode> roots = new Stack<TreeNode>();
        int pre = 0;
        int in = 0;
        // start with the first root
        TreeNode curRoot = new TreeNode(preorder[pre]);
        TreeNode root = curRoot;
        roots.push(curRoot);
        pre++;
        // go over the preorder
        while (pre < preorder.length) {
            // if inorder reach the current root
            if (curRoot.val == inorder[in]) {
                // moving inorder by checking constructed nodes
                while (!roots.isEmpty() && roots.peek().val == inorder[in]) {
                    curRoot = roots.peek();
                    roots.pop();
                    in++;
                }
                // construct right tree root
                curRoot.right = new TreeNode(preorder[pre]);
                // update curRoot
                curRoot = curRoot.right;
                roots.push(curRoot);
                pre++;
            } else {
                // if inorder not reaching current root yet
                // attach the next preorder node to the left tree as the current node
                curRoot.left = new TreeNode(preorder[pre]);
                // move forward
                curRoot = curRoot.left;
                // stack it
                roots.push(curRoot);
                pre++;
            }
        }
        return root;
    }
}