package daohuei.leetcodelizard;

import daohuei.leetcodelizard.utils.ListNode;

/*
 * 160. Intersection of Two Linked Lists
 * Link: https://leetcode.com/problems/intersection-of-two-linked-lists/
 */
public class IntersectionOfTwoLinkedLists {
    /**
     * @author: daohuei
     * @description: difference looping
     * @time: O(max(m,n)): the long list's length
     * @space: O(1): not using any
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // empty case
        if (headA == null || headB == null)
            return null;

        ListNode a = headA;
        ListNode b = headB;
        // if two list are different length, short list will be ahead of long list to
        // reach the end by m-n steps
        // afterward, it will restart at the long list, and the long list will reach the
        // end and restart when short list reach the m-n
        // then they will finally reach the end(if no intersection) or the intersection
        // together!
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }

        return a;
    }
}