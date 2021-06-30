package daohuei.leetcodelizard;

import daohuei.leetcodelizard.utils.ListNode;

/*
 * 148. Sort List 
 * Link: https://leetcode.com/problems/sort-list/
 */
public class SortList {
    /**
     * @author: daohuei
     * @description: merge sort
     * @time: O(nlog(n)): merge for n, split for log n
     * @space: O(log(n)): for recursion stack
     */
    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }

    private ListNode mergeSort(ListNode head) {
        // empty case
        if (head == null || head.next == null) {
            return head;
        }
        // start with the dummy head
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;

        // use the fast slow pointer to get the mid point
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // split two list
        ListNode head2 = slow.next;
        slow.next = null;
        // recur merge sort for each
        head = mergeSort(head);
        head2 = mergeSort(head2);
        // merge them
        return merge(head, head2);

    }

    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (head1 != null && head2 != null) {
            // put the smaller one first
            if (head1.val < head2.val) {
                tail.next = head1;
                tail = tail.next;
                head1 = head1.next;
            } else {
                tail.next = head2;
                tail = tail.next;
                head2 = head2.next;
            }

        }
        // put the last one
        if (head1 != null) {
            tail.next = head1;
        }

        if (head2 != null) {
            tail.next = head2;
        }

        return dummy.next;
    }
}