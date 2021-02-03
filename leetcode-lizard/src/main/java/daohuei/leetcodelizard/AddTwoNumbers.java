package daohuei.leetcodelizard;

import daohuei.leetcodelizard.utils.ListNode;

/*
 * Question: Add Two Numbers
 * Description: You are given two non-empty linked lists representing two non-negative integers. 
 * The digits are stored in reverse order, and each of their nodes contains a single digit. 
 * Add the two numbers and return the sum as a linked list. 
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * 
 * Example 1:
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 * 
 * Example 2:
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 * 
 * Example 3:
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 */
public class AddTwoNumbers {
    /*
     * Carry method
     * 
     * @Author: @daohuei
     * 
     * @Time: O(n), we loop over the list node once.
     * 
     * @Space: O(n), we need a answer list node, which is the size that same as l1 &
     * l2
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // dummy head is a fake head node which is used for getting the answer
        ListNode dummyHead = new ListNode();
        ListNode ptr1 = l1;
        ListNode ptr2 = l2;
        ListNode ans = dummyHead;
        // the carry is for carrying the residual value to next digit
        int carry = 0;
        while (ptr1 != null || ptr2 != null) {
            // if the pointer is not reaching the end
            int val1 = 0;
            int val2 = 0;
            if (ptr1 != null) {
                // if l1's pointer not out of the list

                // get the value
                val1 = ptr1.val;
                // move to the next pointer
                ptr1 = ptr1.next;
            }
            if (ptr2 != null) {
                // if l2's pointer not out of the list

                // get the value
                val2 = ptr2.val;
                // move to the next pointer
                ptr2 = ptr2.next;
            }
            // get the sum of current digit
            int sum = val1 + val2 + carry;
            // add a new node to the answer with the value of the sum modular 10
            // (we only want unit digit)
            ans.next = new ListNode(sum % (10));
            // the answer move to next
            ans = ans.next;
            // get the carry number
            carry = sum / (10);
        }
        if (carry != 0) {
            // if there have value in the carry after finishing the looping
            // then we add the new node with the value of carry itself
            ans.next = new ListNode(carry);
        }
        // return the first answer node, which will be the next node of dummy head node
        return dummyHead.next;
    }
}
