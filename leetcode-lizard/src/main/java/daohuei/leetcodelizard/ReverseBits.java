package daohuei.leetcodelizard;

/*
 * 190. Reverse Bits
 * Link: https://leetcode.com/problems/reverse-bits/
 */
public class ReverseBits {
	/**
	 * @author: daohuei
	 * @description: simply reverse bit with shift/or/and
	 * @time: O(n): n = 32
	 * @space: O(1): not using any
	 */
	// you need treat n as an unsigned value
	public int reverseBits(int n) {
		// start answer with 0
		int res = 0;
		int count = 0;
		// do it 32 times
		while (count < 32) {
			// move 1 digit left
			res <<= 1;
			// 00000001 & n to get the unit digit and or with res
			res |= (n & 1);
			// remove unit digit (right shift)
			n >>= 1;
			count++;
		}
		return res;
	}
}