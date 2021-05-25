package daohuei.leetcodelizard;

import daohuei.leetcodelizard.utils.ListNode;

/*
 * 328. Odd Even Linked List
 * Link: https://leetcode.com/problems/odd-even-linked-list/
 */
public class OddEvenList {

    /**
     * @author: daohuei
     * @description: simply walk through with 2 pointer
     * @time: O(n) simply walk through the list with 2 pointer
     * @space: O(1) only use 2 pointer
     */
    public ListNode oddEvenList(ListNode head) {
        // empty case
        if (head == null)
            return null;
        // the odd pointer
        ListNode odd = head;
        // the even pointer
        ListNode even = head.next;
        // the head of even list
        ListNode evenHead = even;
        while (even != null && even.next != null) {
            // make odd point to even next (means take even out)
            odd.next = even.next;
            // odd move to next
            odd = odd.next;
            // even point to next even node in the original list (odd's next)
            even.next = odd.next;
            // even move to next
            even = even.next;
        }
        // make the odd list point to new even list
        odd.next = evenHead;
        return head;
    }
}
