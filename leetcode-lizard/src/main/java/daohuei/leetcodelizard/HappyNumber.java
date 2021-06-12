package daohuei.leetcodelizard;

/*
 * 202. Happy Number
 * Link: https://leetcode.com/problems/happy-number/
 */
public class HappyNumber {
	/**
	 * @author: daohuei
	 * @description: slow fast pointer
	 * @time: O(n): n = just the full loop until getting the answer
	 * @space: O(1): not using any
	 */
	public boolean slowFastPointer(int n) {
		int slow = n;
		int fast = n;
		// according to Floyd Cycle detection algorithm
		// fast and slow will encounter to each other eventually
		do {
			slow = getNext(slow);
			fast = getNext(getNext(fast));
		} while (slow != fast);
		return slow == 1;
	}

	private int getNext(int n) {
		int next = 0;
		while (n > 0) {
			// get the unit digit
			int t = n % 10;
			// sqaure and add it
			next += t * t;
			// next digit
			n /= 10;
		}
		return next;
	}
}