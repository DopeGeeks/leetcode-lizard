package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import daohuei.leetcodelizard.utils.TreeNode;

/*
 * 314. Binary Tree Vertical Order Traversal
 * Link: https://leetcode.com/problems/binary-tree-vertical-order-traversal/description/
 */
/*
Given a binary tree, return the vertical order traversal of its nodes' values. 
(ie, from top to bottom, column by column).
If two nodes are in the same row and column, the order should be from left to right.
Examples 1:
Input: [3,9,20,null,null,15,7]
   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7 
Output:
[
  [9],
  [3,15],
  [20],
  [7]
]
Examples 2:
Input: [3,9,8,4,0,1,7]
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7 
Output:
[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]
Examples 3:
Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2
Output:
[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]
*/
public class BinaryTreeVerticalOrderTraversal {
    /**
     * @author: daohuei
     * @description: bfs
     * @time: O(n): go through every node
     * @space: O(n): list in the hashmap will store all the nodes
     */
    class BFSMethod {
        // self-defined a node that contains horizontal offset
        class Node {
            int offset;
            TreeNode treeNode;

            public Node(int offset, TreeNode treeNode) {
                this.offset = offset;
                this.treeNode = treeNode;
            }
        }

        public List<List<Integer>> verticalOrder(TreeNode root) {
            List<List<Integer>> rst = new ArrayList<>();
            if (root == null)
                return rst;

            int min = 0, max = 0;
            // the map of horizontal offset and its vertical list
            Map<Integer, List<Integer>> map = new HashMap<>();
            Queue<Node> queue = new LinkedList<>();
            // put the first root node into the queue
            queue.offer(new Node(0, root));

            while (!queue.isEmpty()) {
                int size = queue.size();
                // go through layer
                for (int i = 0; i < size; i++) {
                    Node node = queue.poll();
                    int offset = node.offset;
                    // init new list into the map
                    map.putIfAbsent(offset, new ArrayList<Integer>());
                    // put current node into the corresponding offset value list
                    map.get(offset).add(node.treeNode.val);

                    // put left
                    if (node.treeNode.left != null)
                        queue.offer(new Node(offset - 1, node.treeNode.left));
                    // put right
                    if (node.treeNode.right != null)
                        queue.offer(new Node(offset + 1, node.treeNode.right));

                    // record the min and max offset
                    min = Math.min(min, offset);
                    max = Math.max(max, offset);
                }
            }
            // go through every offset
            for (int offset = min; offset <= max; offset++) {
                // put into the final results
                if (map.containsKey(offset))
                    rst.add(map.get(offset));
            }

            return rst;
        }
    }

    /**
     * @author: daohuei
     * @description: dfs
     * @time: O(n): go through every node
     * @space: O(n): list in the hashmap will store all the nodes
     */
    class DFSMethod {

        class Node {
            int val, level;

            public Node(int val, int level) {
                this.val = val;
                this.level = level;
            }
        }

        int min = 0, max = 0;

        public List<List<Integer>> verticalOrder(TreeNode root) {
            Map<Integer, List<Node>> map = new HashMap<>();
            dfs(map, root, 0, 0);

            List<List<Integer>> rst = new ArrayList<>();
            // go through every offset
            for (int offset = min; offset <= max; offset++) {
                if (map.containsKey(offset)) {
                    // get the list and then sort it by levels
                    List<Node> list = map.get(offset);
                    list.sort(new Comparator<Node>() {
                        public int compare(Node a, Node b) {
                            return a.level - b.level;
                        }
                    });
                    // convert the list and add to the result list
                    rst.add(output(list));
                }
            }
            return rst;
        }

        public void dfs(Map<Integer, List<Node>> map, TreeNode node, int offset, int level) {
            if (node == null)
                return;
            // put list in the offset map and put the current node and its corresponding
            // level in the list
            map.putIfAbsent(offset, new ArrayList<Node>());
            map.get(offset).add(new Node(node.val, level));

            // record the max min offset
            max = Math.max(max, offset);
            min = Math.min(min, offset);

            // recur with left and right tree
            dfs(map, node.left, offset - 1, level + 1);
            dfs(map, node.right, offset + 1, level + 1);
        }

        // convert node list to an Integer list
        private List<Integer> output(List<Node> list) {
            List<Integer> rst = new ArrayList<>();
            for (Node node : list) {
                rst.add(node.val);
            }
            return rst;
        }
    }
}
