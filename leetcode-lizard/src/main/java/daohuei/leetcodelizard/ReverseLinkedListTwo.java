package daohuei.leetcodelizard;

import daohuei.leetcodelizard.utils.ListNode;

/*
 * 92. Reverse Linked List II
 * Link: https://leetcode.com/problems/reverse-linked-list-ii/
 */
public class ReverseLinkedListTwo {
    /**
     * @author: daohuei
     * @description: one pass
     * @time: O(n): simply go through the list once
     * @space: O(1): not using any
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // empty case
        if (left == right) {
            return head;
        }
        // for return answer purpose
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // for checking position
        int count = 0;
        // reversePreHead will be the node to link the list after reversed
        ListNode reversePreHead = null;
        // reverseTail will be the node that be linked by reversed list
        ListNode reverseTail = null;
        // for reversing target to link to their pervious node
        ListNode pre = dummy;
        while (head != null) {
            count++;
            // at location left
            if (count == left) {
                // get the reversePreHead(left-1) and reverseTail(left)
                reversePreHead = pre;
                reverseTail = head;
            }
            // during left to right
            if (count > left && count < right) {
                // reverse pre and head
                ListNode temp = head.next;
                head.next = pre;
                pre = head;
                head = temp;
                continue;
            }
            // when reaching right
            if (count == right) {
                // link reverse tail to the rest list(should be right+1 position)
                reverseTail.next = head.next;
                // reverse the link of tail's next (which is reverse head)
                head.next = pre;
                // link prehead to the head
                reversePreHead.next = head;
                break;
            }
            // moving forward
            head = head.next;
            pre = pre.next;
        }
        return dummy.next;
    }
}