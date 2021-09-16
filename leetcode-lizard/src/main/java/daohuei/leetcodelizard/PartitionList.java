package daohuei.leetcodelizard;

import daohuei.leetcodelizard.utils.ListNode;

/*
 * 86. Partition List
 * https://leetcode.com/problems/partition-list/description/
 */
public class PartitionList {
    /**
     * @author: daohuei
     * @description: 2 linkedlist method
     * @time: O(n): go thru every nodes
     * @space: O(n): using too linkedlist and then connected them
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return head;
        }

        // dummy head for the middle tail list node
        ListNode dummy = new ListNode(0);
        ListNode midTail = dummy;
        // dummy head for the post list node
        ListNode dummyPost = new ListNode(0);
        ListNode post = dummyPost;

        // go through every node
        while (head != null) {
            // if smaller than x => should be at pre list
            if (head.val < x) {
                // put into the middle tail list
                midTail.next = head;
                // middle tail move forward
                midTail = midTail.next;
            }
            // if larger or equal than x => should be at post list
            else {
                // put into the post list
                post.next = head;
                // and move forward
                post = post.next;
            }
            // current head move forward
            head = head.next;
        }

        // the last of the post should be point to the null
        post.next = null;
        // the pre should link to the post
        midTail.next = dummyPost.next;

        return dummy.next;
    }
}