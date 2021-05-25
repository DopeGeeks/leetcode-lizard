package daohuei.leetcodelizard;

import java.util.HashSet;

import daohuei.leetcodelizard.utils.ListNode;

/*
 * 141. Linked List Cycle
 * Link: https://leetcode.com/problems/linked-list-cycle/
 */
public class HasCycle {

    /**
     * @author: daohuei
     * @description: hash
     * @time: O(n) We visit each of the n elements in the list at most once. Adding
     *        a node to the hash table costs only O(1) time.
     * @space: O(n) The space depends on the number of elements added to the hash
     *         table, which contains at most n elements.
     */
    public boolean hash(ListNode head) {
        // using hash
        HashSet<ListNode> set = new HashSet<>();
        // go through all node
        while (head != null) {
            // store new node to the hashset
            set.add(head);
            // move to next
            head = head.next;
            // if any node in the set is the same one as the head node
            // it is a cycle
            if (set.contains(head)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @author: daohuei
     * @description: fast slow pointer
     * @time: O(n) Let us denote nn as the total number of nodes in the linked list.
     * @space: O(1) only use two nodes (slow and fast)
     */
    public boolean fastSlowPointer(ListNode head) {
        // Floyd's Cycle Finding Algorithm
        // a.k.a. fast/slow pointer method

        // move one step each time
        ListNode slow = head;
        // move two step each time
        ListNode fast = head;

        while (fast != null) {
            // if there is any next node point to null
            // this must not be a cycle list
            if (fast.next == null) {
                return false;
            }

            slow = slow.next;
            fast = fast.next.next;
            // no matter what circumstance,
            // fast and slow will definitely meet
            // if there is any cycle
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}
