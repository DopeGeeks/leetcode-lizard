package daohuei.leetcodelizard;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import daohuei.leetcodelizard.utils.TreeNode;
/*
 * 297. Serialize and Deserialize Binary Tree
 * Link: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 */

public class SerializeAndDeserializeBinaryTree {
    /**
     * @author: daohuei
     * @description: bfs method
     * @time: O(n): n for all nodes
     * @space: O(n): for queue
     */
    public class BFSCodec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "";
            }
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            List<Integer> res = new LinkedList<Integer>();
            queue.offer(root);
            // use bfs for adding nodes into the res
            while (!queue.isEmpty()) {
                TreeNode curNode = queue.poll();
                if (curNode != null) {
                    res.add(curNode.val);
                    queue.offer(curNode.left);
                    queue.offer(curNode.right);
                } else {
                    res.add(null);
                }
            }
            // remove the null at the end
            // in order to reduce deserialize iteration amount
            while (true) {
                if (res.get(res.size() - 1) == null) {
                    res.remove(res.size() - 1);
                } else {
                    break;
                }
            }
            // convert to the string
            return res.toString();

        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.length() == 0) {
                return null;
            }
            // decode the string into integer list with bfs order
            String[] preStr = data.substring(1, data.length() - 1).split(",");
            Integer[] bfsOrder = new Integer[preStr.length];
            for (int i = 0; i < preStr.length; i++) {
                if (preStr[i].trim().equals("null")) {
                    bfsOrder[i] = null;
                } else {
                    bfsOrder[i] = Integer.parseInt(preStr[i].trim());
                }
            }

            // running bfs to build up the tree
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            TreeNode root = new TreeNode(bfsOrder[0]);
            int cur = 1;
            queue.offer(root);
            while (!queue.isEmpty()) {
                if (cur == bfsOrder.length) {
                    break;
                }
                TreeNode curNode = queue.poll();
                if (bfsOrder[cur] != null) {
                    curNode.left = new TreeNode(bfsOrder[cur]);
                    queue.add(curNode.left);
                }
                cur++;
                if (cur == bfsOrder.length) {
                    break;
                }
                if (bfsOrder[cur] != null) {
                    curNode.right = new TreeNode(bfsOrder[cur]);
                    queue.add(curNode.right);
                }
                cur++;
            }
            return root;
        }
    }

    /**
     * @author: daohuei
     * @description: inorder traversal method
     * @time: O(n): n for all nodes
     * @space: O(n): for queue and stringbuilder
     */
    public class InorderCodec {
        private static final String spliter = ",";
        private static final String NN = "X"; // for null

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            buildString(root, sb);
            return sb.toString();
        }

        private void buildString(TreeNode node, StringBuilder sb) {
            // build string with inorder traversal
            if (node == null) {
                sb.append(NN).append(spliter);
            } else {
                sb.append(node.val).append(spliter);
                buildString(node.left, sb);
                buildString(node.right, sb);
            }
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            Deque<String> nodes = new LinkedList<>();
            // split data with the spliter
            nodes.addAll(Arrays.asList(data.split(spliter)));
            return buildTree(nodes);
        }

        private TreeNode buildTree(Deque<String> nodes) {
            // get the first val from deque
            String val = nodes.remove();
            // build tree with inorder
            if (val.equals(NN))
                return null;
            else {
                TreeNode node = new TreeNode(Integer.valueOf(val));
                node.left = buildTree(nodes);
                node.right = buildTree(nodes);
                return node;
            }
        }
    }

}
