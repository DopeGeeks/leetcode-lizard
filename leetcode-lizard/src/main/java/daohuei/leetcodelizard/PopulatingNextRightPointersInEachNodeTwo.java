package daohuei.leetcodelizard;

// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

/*
 * 117. Populating Next Right Pointers in Each Node II Link:
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
 * description/
 */
public class PopulatingNextRightPointersInEachNodeTwo {
    /**
     * @author: daohuei
     * @description: recursion method
     * @time: O(n): go through every node
     * @space: O(h): for recursion stack at most tree's height h space
     */
    public Node connect(Node root) {
        if (root == null)
            return root;
        if (root.left == null && root.right == null)
            return root;

        // link children, if applicable
        if (root.left != null) {
            root.left.next = root.right;
        }

        // resolve root's right-most children, with neighbor
        Node rightMostNode = root.right != null ? root.right : root.left;
        Node nextNode = root.next;
        while (nextNode != null) {
            // if current next node have left or right
            if (nextNode.left != null || nextNode.right != null) {
                // connect current right most node to left or right (but left first)
                rightMostNode.next = nextNode.left != null ? nextNode.left : nextNode.right;
                // since gap already filled, just break
                break;
            }
            // move to next
            nextNode = nextNode.next;
        }

        // do it on subtrees
        connect(root.right);
        connect(root.left);

        return root;
    }
}