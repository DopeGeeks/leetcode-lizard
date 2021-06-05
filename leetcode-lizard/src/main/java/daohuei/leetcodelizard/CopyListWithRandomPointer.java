package daohuei.leetcodelizard;

/*
 * 138. Copy List with Random Pointer
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 */
public class CopyListWithRandomPointer {
    // Definition for a Node.
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * @author: daohuei
     * @description: use random for temp storage
     * @time: O(n): go through all nodes
     * @space: O(1): not using any
     */
    public Node copyRandomList(Node head) {
        // empty case
        if (head == null) {
            return null;
        }

        Node l1 = head;
        Node l2 = null;

        while (l1 != null) {
            // iter through nodes
            // copy the node
            l2 = new Node(l1.val);
            // temply store the random in the l2's next
            l2.next = l1.random;
            // then temply store the l2 node into l1's random
            l1.random = l2;
            // move forward
            l1 = l1.next;
        }
        // reset l1
        l1 = head;
        while (l1 != null) {
            // retrieve l2 from temp(l1.random)
            l2 = l1.random;
            // retrieve random from temp(l2.next.random: l2.next => some random l1 node =>
            // l2.next.random => some random node's copied version: l2)
            l2.random = l2.next != null ? l2.next.random : null;
            // move forward
            l1 = l1.next;
        }

        l1 = head;
        Node l2_head = l1.random;
        // reconnect l2
        while (l1 != null) {
            // get the l2
            l2 = l1.random;
            // return l1's random
            l1.random = l2.next;
            // connect l2's next as l1's next node's random(copied)
            l2.next = l1.next != null ? l1.next.random : null;
            // move forward
            l1 = l1.next;
        }
        return l2_head;
    }
}