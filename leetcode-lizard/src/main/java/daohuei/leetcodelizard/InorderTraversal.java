package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import daohuei.leetcodelizard.utils.TreeNode;

/*
 * 94. Binary Tree Inorder Traversal
 * Link: https://leetcode.com/problems/binary-tree-inorder-traversal/
 */
public class InorderTraversal {
    public void traverseInorder(TreeNode node, List<Integer> results) {
        if (node == null) {
            return;
        }
        // keep going to the end of the left
        traverseInorder(node.left, results);
        // if reaching the end, put the value into answers
        results.add(node.val);
        // then we start search the right
        traverseInorder(node.right, results);
    }

    /**
     * @author: daohuei
     * @description: use recursive method to recur inorder traversal. The recur will
     *               stop when reaching the null at the left most side.
     * @time: O(n): n = total nodes. Since we traverse all nodes.
     * @space: O(h): h = height of the tree. Since we stack the recur at most h
     *         times.
     */
    public List<Integer> recursiveMethod(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        traverseInorder(root, results);
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
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (current != null && !stack.isEmpty()) {
            // same as above, go to the end of the left
            while (current != null) {
                // use stack to store nodes passed by
                stack.push(current);
                current = current.left;
            }
            // if reaching the end, store the val and move to right
            current = stack.pop();
            results.add(current.val);
            current = current.right;
        }

        return results;
    }

    /**
     * @author: daohuei
     * @description: This method always make the current node as its left tree's
     *               right most child's right child if left tree is not null. So
     *               that we do not need to store previous tree node for later
     *               traversal.
     * @time: O(n): same as above
     * @space: O(1): since we are not using any to store history node.
     */
    public List<Integer> morrisMethod(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        TreeNode current = root;
        while (current != null) {
            // if reaching the end of the left
            if (current.left == null) {
                // put the value to the answers
                results.add(current.val);
                // move to the right
                current = current.right;
            } else {
                // if we have left tree, let's find the right most node of the left tree
                TreeNode pre = current.left;
                while (pre.right != null && pre.right != current) {
                    pre = pre.right;
                }
                // pre now will be come the right most of the left tree
                if (pre.right == null) {
                    // if it is null, we make current node as its right node
                    pre.right = current;
                    // go the the next left tree
                    current = current.left;
                }
                // if right node equal to current, means this right most node has already been
                // assigned to current, which also means we are getting back, then it should be
                // push into the answers
                if (pre.right == current) {
                    pre.right = null;
                    results.add(current.val);
                    current = current.right;
                }
            }
        }
        return results;
    }
}
