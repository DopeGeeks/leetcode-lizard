package daohuei.leetcodelizard;

import daohuei.leetcodelizard.utils.ListNode;

/*
 * 142. Linked List Cycle II
 * Link: https://leetcode.com/problems/linked-list-cycle-ii/description/
 */
public class LinkedListCycleTwo {
    /**
     * @author: daohuei
     * @description: slow fast pointer
     * @time: O(n): go through all nodes
     * @space: O(1): not using any
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        // slow fast pointer
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // if there is a cycle, find the head
            if (slow == fast)
                return findBegin(head, slow);
        }
        return null;
    }

    // the slow will stop at the position of the remainder that (step -
    // distance(head, cyclehead)) mod 3
    // which indicates that slow and head will meet at the cycle head within the
    // same pace
    private ListNode findBegin(ListNode head, ListNode slow) {
        while (head != slow) {
            head = head.next;
            slow = slow.next;
        }
        return head;
    }
}