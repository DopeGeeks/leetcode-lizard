package daohuei.leetcodelizard;

import daohuei.leetcodelizard.utils.ListNode;

/*
 * 237. Delete Node in a Linked List
 * Link: https://leetcode.com/problems/delete-node-in-a-linked-list/
 */
public class DeleteNodeLinkedList {
    /**
     * @author: daohuei
     * @description: easy
     * @time: O(1): no need to explain...
     * @space: O(1): not using any
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}