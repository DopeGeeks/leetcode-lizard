package daohuei.leetcodelizard;

/*
 * 204. Count Primes
 * Link: https://leetcode.com/problems/count-primes/
 */
public class CountPrimes {
	/**
	 * @author: daohuei
	 * @description: iteration
	 * @time: O(n^2): but actually will not reach n^2
	 * @space: O(n): for hash set
	 */
	public int countPrimes(int n) {
		// for storing non-prime number
		boolean[] notPrime = new boolean[n];
		int count = 0;
		for (int i = 2; i < n; i++) {
			// if it is not in non-prime list
			if (!notPrime[i]) {
				// it must be prime, count + 1
				count++;
				// add all of its multiple into the non-prime list
				for (int j = 2; j * i < n; j++) {
					notPrime[j * i] = true;
				}
			}
		}
		return count;
	}
}
