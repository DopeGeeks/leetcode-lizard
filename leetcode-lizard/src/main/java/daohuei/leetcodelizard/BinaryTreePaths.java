package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.List;

import daohuei.leetcodelizard.utils.TreeNode;

/*
 * 257. Binary Tree Paths
 * Link: https://leetcode.com/problems/binary-tree-paths/
 */
public class BinaryTreePaths {

    /**
     * @author: daohuei
     * @description: dfs
     * @time: O(n) go through all nodes
     * @space: O(h) recursion stack for h as tree's height
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        // empty case
        if (root == null) {
            return result;
        }
        binaryTreePaths(root, "", result);
        return result;
    }

    private void binaryTreePaths(TreeNode root, String temp, List<String> result) {
        if (root.left == null && root.right == null) {
            // if reaching the leaf
            // at current value
            temp = temp + root.val;
            // add to the result
            result.add(temp);
            return;
        }
        if (root.left != null) {
            // if there is a left child
            binaryTreePaths(root.left, temp + root.val + "->", result);
        }
        if (root.right != null) {
            // if there is a right child
            binaryTreePaths(root.right, temp + root.val + "->", result);
        }
    }
}