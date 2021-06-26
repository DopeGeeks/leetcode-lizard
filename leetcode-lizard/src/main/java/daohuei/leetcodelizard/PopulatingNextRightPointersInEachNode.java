package daohuei.leetcodelizard;

import java.util.LinkedList;
import java.util.Queue;

import daohuei.leetcodelizard.utils.Node;

/*
 * 116. Populating Next Right Pointers in Each Node
 * Link: https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 */
public class PopulatingNextRightPointersInEachNode {

    /**
     * @author: daohuei
     * @description: bfs
     * @time: O(n): n = total nodes. Since we traverse all nodes.
     * @space: O(w): w = max width of the tree. the max of the queue size
     */
    public Node bfs(Node root) {
        // empty case
        if (root == null) {
            return root;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // all nodes in current level
            int size = queue.size();
            Node pre = null;
            for (int i = 0; i < size; i++) {
                // get the nide
                Node cur = queue.poll();
                // link the pre to the next if not the first node of the current level
                if (i > 0) {
                    pre.next = cur;
                }
                pre = cur;
                // put next level node into the queue
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }

            }
        }
        return root;

    }

    /**
     * @author: daohuei
     * @description: iteration
     * @time: O(n): n = total nodes. Since we traverse all nodes.
     * @space: O(1): not using any
     */
    public Node iteration(Node root) {
        if (root == null) {
            return root;
        }
        Node pre = root;
        Node cur = null;
        Node start = pre;
        while (pre.left != null) {
            // if cur is over the last node of the current level
            // pre will be the last node of the current level
            if (cur == null) {
                // link pre's left to the right
                pre.left.next = pre.right;
                // update pre and cur to the next level
                pre = start.left;
                cur = start.right;
                // update start to the start point of the next level
                start = pre;
                //
            } else {
                // link pre's left to the right
                pre.left.next = pre.right;
                // link pre's right to cur's left
                pre.right.next = cur.left;

                // both move forward
                pre = pre.next;
                cur = cur.next;
            }
        }
        return root;
    }
}