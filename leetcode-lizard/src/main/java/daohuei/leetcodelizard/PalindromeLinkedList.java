package daohuei.leetcodelizard;

import daohuei.leetcodelizard.utils.ListNode;

/*
 * 234. Palindrome Linked List
 * Link: https://leetcode.com/problems/palindrome-linked-list/
 */
public class PalindromeLinkedList {
	/**
	 * @author: daohuei
	 * @description: split and reverse
	 * @time: O(n): n = list node head's length
	 * @space: O(1): not using any
	 */
	public boolean splitAndReverse(ListNode head) {
		if (head == null || head.next == null) {
			return true;
		}
		// find the middle point
		ListNode slow = head;
		ListNode fast = head;
		// if the fast reach the end, the slow will reach the mid point
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		// reverse the half list
		ListNode newHead = reverseList(slow);

		// compare if the same
		while (newHead != null) {
			if (head.val != newHead.val) {
				return false;
			}
			head = head.next;
			newHead = newHead.next;
		}
		return true;

	}

	private ListNode reverseList(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode tail = null;
		while (head != null) {
			ListNode temp = head.next;
			head.next = tail;
			tail = head;
			head = temp;
		}

		return tail;
	}
}