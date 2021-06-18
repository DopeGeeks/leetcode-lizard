package daohuei.leetcodelizard;

import daohuei.leetcodelizard.utils.ListNode;

/*
 * 83. Remove Duplicates from Sorted List
 * Link: https://leetcode.com/problems/remove-duplicates-from-sorted-list
 */
public class RemoveDuplicatesFromSortedList {
    /**
     * @author: daohuei
     * @description: iteration
     * @time: O(n): n = length of list
     * @space: O(1): not using any
     */
    public ListNode iteration(ListNode head) {
        ListNode pre = new ListNode(0);
        ListNode dummy = pre;
        pre.next = head;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            boolean equal = false;
            // if duplicated case found
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
                equal = true;
            }
            // pre is previous non-equal node
            if (equal) {
                // link pre and cur
                pre.next = cur;
                // make cur to be next pre
                pre = cur;
                // reset equal flag
                equal = false;
            } else {
                // if not equal just simply update pre with cur
                pre = cur;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    /**
     * @author: daohuei
     * @description: recursion
     * @time: O(n): n = length of list
     * @space: O(n): recursion stack
     */
    public ListNode recursion(ListNode head) {
        if (head == null)
            return null;
        // if duplicate with next
        if (head.next != null && head.val == head.next.val) {
            // skip same duplicates
            while (head.next != null && head.val == head.next.val) {
                head = head.next;
            }
            // then recur
            return recursion(head);
        } else {
            // if not, recur with next
            head.next = recursion(head.next);
        }
        return head;
    }

    /**
     * @author: daohuei
     * @description: iteration2, easy as hell...
     * @time: O(n): n = length of list
     * @space: O(1): not using any
     */
    public ListNode iterationTwo(ListNode head) {
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            // if duplicate
            if (cur.val == cur.next.val) {
                // just skip
                cur.next = cur.next.next;
            } else {
                // if not, move forward
                cur = cur.next;
            }
        }
        return head;
    }

    /**
     * @author: daohuei
     * @description: recursion2, easy as hell...
     * @time: O(n): n = length of list
     * @space: O(n): recursion stack
     */
    public ListNode recursionTwo(ListNode head) {
        // base case
        if (head == null || head.next == null) {
            return head;
        }
        // if the duplicate
        if (head.val == head.next.val) {
            // directly return next recur
            return recursionTwo(head.next);
        } else {
            // recur and attach to the head then return head
            head.next = recursionTwo(head.next);
            return head;
        }
    }
}