package daohuei.leetcodelizard;

import daohuei.leetcodelizard.utils.ListNode;

/*
 * 203. Remove Linked List Elements
 * Link: https://leetcode.com/problems/remove-linked-list-elements/description/
 */
public class RemoveLinkedListElements {
    /**
     * @author: daohuei
     * @description: iteration
     * @time: O(n): n for length of linked list
     * @space: O(1): not using any
     */
    public ListNode iteration(ListNode head, int val) {

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode newHead = dummyHead;

        while (newHead.next != null) {
            ListNode next = newHead.next;
            // check if the next if the one should be removed
            if (next.val == val) {
                // if so, attach the next of next(skip the next)
                newHead.next = next.next;
            } else {
                // if not, move forward
                newHead = newHead.next;
            }
        }
        return dummyHead.next;
    }

    /**
     * @author: daohuei
     * @description: recursion
     * @time: O(n): n for length of linked list
     * @space: O(n): recursion stack over all ndoes
     */
    public ListNode recursion(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        if (head.val == val) {
            // the one should be removed
            // return the recur with the next node
            return recursion(head.next, val);
        } else {
            head.next = recursion(head.next, val);
            return head;
        }
    }
}