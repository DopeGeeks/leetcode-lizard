package daohuei.leetcodelizard;

/*
 * 172. Factorial Trailing Zeroes
 * Link: https://leetcode.com/problems/factorial-trailing-zeroes/
 */
public class FactorialTrailingZeroes {
	/**
	 * @author: daohuei
	 * @description: count the 5, since 2 are more than 5, so we can only just count
	 *               5 for counting the tailing zeroes
	 * @time: O(n): n = just the full loop until getting the answer
	 * @space: O(1): not using any
	 */
	public int countFiveMethod(int n) {
		int count = 0;
		// we only calculate the number of 5 for the n's factors
		while (n > 0) {
			count += n / 5;
			n = n / 5;
		}
		return count;
	}
}
