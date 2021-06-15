package daohuei.leetcodelizard;

import daohuei.leetcodelizard.utils.ListNode;

/*
 * 19. Remove Nth Node From End of List
 * Link: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 */
public class RemoveNthNodeFromEndOfList {
    /**
     * @author: daohuei
     * @description: 2 pointers
     * @time: O(n): only loop for once, n = listNode's length
     * @space: O(1): not using any
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // first pointer move n step
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // second start moving
        // so that first and second will have distance of n
        // when first reach the end
        // second's next will be the one should be removed
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        // remove it
        second.next = second.next.next;
        return dummy.next;
    }
}