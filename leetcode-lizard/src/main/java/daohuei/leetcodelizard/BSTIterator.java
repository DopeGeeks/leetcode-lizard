package daohuei.leetcodelizard;

import java.util.LinkedList;
import java.util.Queue;

import daohuei.leetcodelizard.utils.TreeNode;

public class BSTIterator {
    /**
     * @author: daohuei
     * @description: recursion
     * @time: O(n) traverse binary tree
     * @space: O(n) queue store all nodes
     */
    private class BSTIteratorInorderTraversal {

        Queue<Integer> queue = new LinkedList<>();

        public BSTIteratorInorderTraversal(TreeNode root) {
            inorderTraversal(root);
        }

        private void inorderTraversal(TreeNode root) {
            // just simply implement inorder traversal
            if (root == null) {
                return;
            }
            inorderTraversal(root.left);
            queue.offer(root.val);
            inorderTraversal(root.right);
        }

        public int next() {
            return queue.poll();
        }

        public boolean hasNext() {
            return !queue.isEmpty();
        }
    }
}
