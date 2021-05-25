package daohuei.leetcodelizard;

import daohuei.leetcodelizard.utils.ListNode;
/*
 * Author: @ballm06m06
 * Qusetion: Merge Two Sorted Lists
 * Description: Merge two sorted linked lists and return it as a sorted list. 
 *              The list should be made by splicing together the nodes of the first two lists.
*/

/*
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // Set conditions to specical cases
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        // Declare the head, and set its pointer to the smallest element in both lists
        ListNode head;
        if (l1.val <= l2.val) {
            head = l1;
            // After set the smallest element to head, l1's pointer will go to next element
            l1 = l1.next;
        } else {
            head = l2;
            l2 = l2.next;
        }

        // Make sure head will at the beginnung of the list, and use current to add new
        // elements
        ListNode current = head;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        // Lists might not have the same length, so check lists out whether there are
        // remain elements
        if (l1 != null) {
            current.next = l1;
        }

        if (l2 != null) {
            current.next = l2;
        }
        return head;
    }
}
