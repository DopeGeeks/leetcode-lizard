package daohuei.leetcodelizard;

import daohuei.leetcodelizard.utils.ListNode;

/*
 * 206. Reverse Linked List
 * Link: https://leetcode.com/problems/reverse-linked-list/
 */
public class ReverseList {
    /**
     * @author: daohuei
     * @description: iterations
     * @time: O(n) Assume that n is the list's length, the time complexity is O(n).
     * @space: O(1)
     */
    public ListNode reverseList(ListNode head) {
        // empty case
        if (head == null)
            return null;

        // pre: last reversed node
        ListNode pre = null;
        // next: node that need to be reversed
        ListNode next;
        while (head != null) {
            // update next node, which is what we are going to reverse
            next = head.next;
            // reverse current node
            head.next = pre;
            // point to pre since it becomes last reversed
            pre = head;
            // the next node will be the one should be reversed next
            head = next;
        }
        return pre;
    }

    /**
     * @author: daohuei
     * @description: recursion
     * @time: O(n) Assume that n is the list's length, the time complexity is O(n).
     * @space: O(n) The extra space comes from implicit stack space due to
     *         recursion. The recursion could go up to nn levels deep.
     */
    public ListNode recursion(ListNode head) {
        // base case: no node exist or it is the last head
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead;
        // reverse the rest of nodes and return its new head (should be previous tail)
        newHead = reverseList(head.next);
        // the current head still point to the second node, which is the tail of new
        // reverse list
        // since the current head should attach at the end of new reverse list
        // so we make second node point to the first node
        head.next.next = head;
        // make first node point to the end, since it should be the tail
        head.next = null;
        return newHead;
    }
}
