package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import daohuei.leetcodelizard.utils.TreeNode;

public class PreorderTraversal {
    public void traversePreorder(TreeNode node, List<Integer> results) {
        if (node == null) {
            return;
        }
        // store the value of the passed node
        results.add(node.val);
        // moving on left first
        traversePreorder(node.left, results);
        // moving on right after all left nodes are done
        traversePreorder(node.right, results);
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
        traversePreorder(root, results);
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
        while (current != null || !stack.isEmpty()) {
            // same as above, go to the end of the left
            while (current != null) {
                // store the value when traversing left nodes
                results.add(current.val);
                // use stack to store nodes passed by
                stack.push(current);
                current = current.left;
            }
            // if reaching the end, move to right
            current = stack.pop();
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
                // pre now will become the right most node of the left tree
                if (pre.right == null) {
                    // add current value to the answers when we are traversing the left node
                    results.add(current.val);
                    // if it is null, we make current node as its right node
                    pre.right = current;
                    // go the the next left tree
                    current = current.left;
                }
                // if right node equal to current, means this right most node has already been
                // assigned to current, which also means we are getting back to the previous
                // node
                if (pre.right == current) {
                    pre.right = null;
                    current = current.right;
                }
            }
        }
        return results;
    }
}
