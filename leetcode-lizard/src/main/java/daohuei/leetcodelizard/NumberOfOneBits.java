package daohuei.leetcodelizard;

/*
 * 191. Number of 1 Bits
 * Link: https://leetcode.com/problems/number-of-1-bits/
 */
public class NumberOfOneBits {
	/**
	 * @author: daohuei
	 * @description: minus one method
	 * @time: O(n): binary digits of n
	 * @space: O(1): not using any
	 */
	// you need to treat n as an unsigned value
	public int minusOne(int n) {
		int count = 0;
		while (n != 0) {
			// 10 & 9 = (1010) & (1001) = 1000
			// minus one and & it will remove a 1 in the n
			n &= (n - 1);
			// count it
			count += 1;
		}
		return count;
	}
}