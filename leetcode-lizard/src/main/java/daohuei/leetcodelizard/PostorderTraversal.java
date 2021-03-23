package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import daohuei.leetcodelizard.utils.TreeNode;

public class PostorderTraversal {

    public void traversePostorder(TreeNode node, List<Integer> results) {
        if (node == null) {
            return;
        }
        // moving on left first
        traversePostorder(node.left, results);
        // moving on right after all left nodes are done
        traversePostorder(node.right, results);
        // store the value of the passed node after reaching the end of the branch
        results.add(node.val);
    }

    /**
     * @author: daohuei
     * @description: use recursive method to recur preorder traversal.
     * @time: O(n): n = total nodes. Since we traverse all nodes.
     * @space: O(h): h = height of the tree. Since we stack the recur at most h
     *         times.
     */
    public List<Integer> recursiveMethod(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        traversePostorder(root, results);
        return results;
    }

    /**
     * @author: daohuei
     * @description: this method go through to the left most and push nodes passed
     *               by into the stack until reaching the end. And then pop the last
     *               out and move to the right node.
     * @time: O(n): same as above
     * @space: O(h): same as above, except here is using stack instead of recursive
     *         function.
     */
    public List<Integer> stackMethod(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        Stack<TreeNode> stack = new Stack<>();
        // we push every node for twice because for post order, we may have to check the
        // parent node for adding value
        stack.push(root);
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            // skip when reaching the end
            if (current == null) {
                continue;
            }
            if (!stack.isEmpty() && current == stack.peek()) {
                // first time traversing this node
                // we put right and left children twice for each into the stack
                stack.push(current.right);
                stack.push(current.right);
                stack.push(current.left);
                stack.push(current.left);
            } else {
                // second time traversing the node
                // which means it is time to put it into the answers
                results.add(current.val);
            }
        }

        return results;
    }

}
