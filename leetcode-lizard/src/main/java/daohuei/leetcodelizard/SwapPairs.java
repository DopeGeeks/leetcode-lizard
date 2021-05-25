package daohuei.leetcodelizard;

import daohuei.leetcodelizard.utils.ListNode;

/*
 * 24. Swap Nodes in Pairs
 * Link: https://leetcode.com/problems/swap-nodes-in-pairs/
 */
public class SwapPairs {

    /**
     * @author: daohuei
     * @description: iterate through all nodes
     * @time: O(n): n = length of the ListNode.
     * @space: O(1): not using any
     */
    public ListNode iter(ListNode head) {
        // set dummy head and let it point to the first node the list
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        // start the iterations
        while (current.next != null && current.next.next != null) {
            // get the next two nodes
            ListNode swap1 = current.next;
            ListNode swap2 = current.next.next;
            // make current point to the one will be moved backward
            current.next = swap2;
            // make the one will move forward point to the future node
            swap1.next = swap2.next;
            // let the one moved backward point to the forward one
            swap2.next = swap1;
            // then move current to next start point: the forward one
            current = swap1;
        }
        return dummy.next;
    }

    /**
     * @author: daohuei
     * @description: recurrence
     * @time: O(n): n = length of the ListNode
     * @space: O(1): it is linear
     */
    public ListNode recur(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // start swapping
        // make second node(moving backward) as current
        ListNode current = head.next;
        // make the first node(moving forward) become the recur output(head after
        // swapped, which also
        // means future node)
        head.next = recur(head.next.next);
        // make second node(moved backward) point to the forward one
        current.next = head;
        // return new head(which the the swapped second one => a new first node)
        return current;
    }
}
