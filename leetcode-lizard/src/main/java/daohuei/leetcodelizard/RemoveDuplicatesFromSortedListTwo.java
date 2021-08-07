package daohuei.leetcodelizard;

import daohuei.leetcodelizard.utils.ListNode;

/*
 * 82. Remove Duplicates from Sorted List II
 * Link: https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
 */
public class RemoveDuplicatesFromSortedListTwo {
    /**
     * @author: daohuei
     * @description: single loop method with dummy head
     * @time: O(n): for the length of the list
     * @space: O(1): not using any
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode node = dummyHead;
        // go through the list
        while (node.next != null && node.next.next != null) {
            // if next is equal to next next => duplicate
            if (node.next.val == node.next.next.val) {
                // store duplicate value
                int duplicatedVal = node.next.val;
                // remove all duplicated numbers
                while (node.next != null && node.next.val == duplicatedVal) {
                    node.next = node.next.next;
                }
            } else {
                // else just moving on
                node = node.next;
            }
        }

        return dummyHead.next;
    }
}