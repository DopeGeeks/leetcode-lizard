package daohuei.leetcodelizard;

import daohuei.leetcodelizard.utils.ListNode;

/*
 * 25. Reverse Nodes in k-Group Link:
 * https://leetcode.com/problems/reverse-nodes-in-k-group/description/
 */
public class ReverseNodesInKGroup {
    /**
     * @author: daohuei
     * @description: linkedlist reverse node method
     * @time: O(n): go thru every nodes
     * @space: O(1): not using any
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // base and empty case
        if (head == null || k == 1) {
            return head;
        }

        // dummy head
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;

        int i = 0;
        while (head != null) {
            i++;
            // if reach the k
            if (i % k == 0) {
                // reverse and update pre
                pre = reverse(pre, head.next);
                // update head
                head = pre.next;
            } else {
                // just update head
                head = head.next;

            }
        }
        // return dummy head next
        return dummy.next;
    }

    // reverse the list between pre and next
    public ListNode reverse(ListNode pre, ListNode next) {
        ListNode last = pre.next;
        ListNode cur = last.next;

        while (cur != next) {
            // reverse
            last.next = cur.next;
            cur.next = pre.next;

            // update
            pre.next = cur;
            cur = last.next;
        }
        // return the last node
        return last;
    }
}