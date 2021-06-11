package daohuei.leetcodelizard;

/*
 * 8. String to Integer (atoi) Link:
 * https://leetcode.com/problems/string-to-integer-atoi/
 */
public class StringToInteger {
	/**
	 * @author: daohuei
	 * @description: iteration
	 * @time: O(n): n = string length
	 * @space: O(1): not using any
	 */
	public int myAtoi(String str) {
		// for storing sign
		int sign = 1;
		int ans = 0, pop = 0;
		// for sign flag
		boolean hasSign = false;
		for (int i = 0; i < str.length(); i++) {
			// if negative sign
			if (str.charAt(i) == '-' && !hasSign) {
				sign = -1;
				hasSign = true;
				continue;
			}
			// if positive sign
			if (str.charAt(i) == '+' && !hasSign) {
				sign = 1;
				hasSign = true;
				continue;
			}
			// if space has sign
			if (str.charAt(i) == ' ' && !hasSign) {
				continue;
			}

			// if the char is the number
			if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
				hasSign = true;
				pop = str.charAt(i) - '0';
				// if overflow
				if (ans * sign > Integer.MAX_VALUE / 10
						|| (ans * sign == Integer.MAX_VALUE / 10 && pop * sign > 7))
					return 2147483647;
				if (ans * sign < Integer.MIN_VALUE / 10
						|| (ans * sign == Integer.MIN_VALUE / 10 && pop * sign < -8))
					return -2147483648;
				// add to next digit
				ans = ans * 10 + pop;
			} else {
				// if not number or sign
				// directly return latest calculation
				return ans * sign;
			}
		}
		return ans * sign;
	}
}