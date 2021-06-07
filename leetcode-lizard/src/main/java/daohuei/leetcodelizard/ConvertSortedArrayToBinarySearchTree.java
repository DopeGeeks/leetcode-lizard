package daohuei.leetcodelizard;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import daohuei.leetcodelizard.utils.TreeNode;

/*
 * 108. Convert Sorted Array to Binary Search Tree
 * Link: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 */
public class ConvertSortedArrayToBinarySearchTree {

    /**
     * @author: daohuei
     * @description: recursion
     * @time: O(n): since it traverse through all nodes
     * @space: O(h): the height of the tree with recursion stack
     */
    public TreeNode recursion(int[] nums) {
        return recursion(nums, 0, nums.length);
    }

    private TreeNode recursion(int[] nums, int start, int end) {
        // base case
        if (start == end) {
            return null;
        }
        // get the middle index
        int mid = (start + end) >>> 1;
        // use the middle index as root
        TreeNode root = new TreeNode(nums[mid]);
        // do the left tree with start to mid
        root.left = recursion(nums, start, mid);
        // do the right tree with mid+1 to end
        root.right = recursion(nums, mid + 1, end);

        return root;
    }

    // for storing start and end position for that specific root
    class MyTreeNode {
        TreeNode root;
        int start;
        int end;

        MyTreeNode(TreeNode r, int s, int e) {
            this.root = r;
            this.start = s;
            this.end = e;
        }
    }

    /**
     * @author: daohuei
     * @description: dfs with stack
     * @time: O(n): since it traverse through all nodes
     * @space: O(h): the height of the tree with root stack
     */
    public TreeNode dfs(int[] nums) {
        // empty case
        if (nums.length == 0) {
            return null;
        }

        Stack<MyTreeNode> rootStack = new Stack<>();
        int start = 0;
        int end = nums.length;
        int mid = (start + end) >>> 1;
        // use the middle num as the first root
        TreeNode root = new TreeNode(nums[mid]);
        TreeNode curRoot = root;
        rootStack.push(new MyTreeNode(root, start, end));
        while (end - start > 1 || !rootStack.isEmpty()) {
            // when start is not reaching end
            while (end - start > 1) {
                // current root
                mid = (start + end) >>> 1;
                // the end for the left
                end = mid;
                // the mid for the left
                mid = (start + end) >>> 1;
                // make mid as root of left
                curRoot.left = new TreeNode(nums[mid]);
                // move to left
                curRoot = curRoot.left;
                // push into root stack
                rootStack.push(new MyTreeNode(curRoot, start, end));
            }
            // after finishing left tree
            // for the right tree
            MyTreeNode myNode = rootStack.pop();
            // get the start and end from MyTreeNode
            start = myNode.start;
            end = myNode.end;
            mid = (start + end) >>> 1;
            // update start to right tree version
            start = mid + 1;
            // get the root
            curRoot = myNode.root;
            // check still in range
            if (start < end) {
                // get the mid of right
                mid = (start + end) >>> 1;
                // make it as right tree root
                curRoot.right = new TreeNode(nums[mid]);
                // move to right tree
                curRoot = curRoot.right;
                // push to the stack
                rootStack.push(new MyTreeNode(curRoot, start, end));
            }

        }

        return root;
    }

    /**
     * @author: daohuei
     * @description: bfs with queue
     * @time: O(n): since it traverse through all nodes
     * @space: O(w): the most amount of node with a single layer
     */
    public TreeNode bfs(int[] nums) {
        // empty case
        if (nums.length == 0) {
            return null;
        }
        Queue<MyTreeNode> rootQueue = new LinkedList<>();
        // for init the starting node
        TreeNode root = new TreeNode(0);
        rootQueue.offer(new MyTreeNode(root, 0, nums.length));
        while (!rootQueue.isEmpty()) {
            // get the root node (earliest pushed)
            MyTreeNode myRoot = rootQueue.poll();
            int start = myRoot.start;
            int end = myRoot.end;
            int mid = (start + end) >>> 1;
            TreeNode curRoot = myRoot.root;
            // update its value with the mid index
            curRoot.val = nums[mid];
            if (start < mid) {
                // push left position and empty node to the queue
                curRoot.left = new TreeNode(0);
                rootQueue.offer(new MyTreeNode(curRoot.left, start, mid));
            }
            if (mid + 1 < end) {
                // push right position and empty node to the queue
                curRoot.right = new TreeNode(0);
                rootQueue.offer(new MyTreeNode(curRoot.right, mid + 1, end));
            }
        }

        return root;
    }
}