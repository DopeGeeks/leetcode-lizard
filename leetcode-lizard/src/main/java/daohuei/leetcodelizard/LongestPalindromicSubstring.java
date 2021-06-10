package daohuei.leetcodelizard;

/*
 * 5. Longest Palindromic Substring
 * Link: https://leetcode.com/problems/longest-palindromic-substring/
 */
public class LongestPalindromicSubstring {

	/**
	 * @author: daohuei
	 * @description: expand from the center
	 * @time: O(n^2) 1 loop for go through element, another for checking palidromic
	 * @space: O(1) not using any
	 */
	public String expandFromCenter(String s) {
		if (s == null || s.length() < 1)
			return "";
		int start = 0, end = 0;
		for (int i = 0; i < s.length(); i++) {
			// center is the element
			int len1 = expandAroundCenter(s, i, i);
			// do not forget the center between element
			int len2 = expandAroundCenter(s, i, i + 1);
			// choose the larger one
			int len = Math.max(len1, len2);
			// if larger than previous length
			if (len > end - start) {
				// set them as new point
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}
		// return the substring
		return s.substring(start, end + 1);
	}

	private int expandAroundCenter(String s, int left, int right) {
		int L = left, R = right;
		// expanding to check palindrom
		while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
			L--;
			R++;
		}
		// return its length
		return R - L - 1;
	}

	/**
	 * @author: daohuei
	 * @description: Manacher's Algorithm
	 * @time: O(n) god speed!
	 * @space: O(1) not using any
	 */
	public String Manacher(String s) {
		String T = preProcess(s);
		int n = T.length();
		int[] P = new int[n];
		// C is the center that has the right edge of R
		// R is the edge of the C
		int C = 0, R = 0;
		for (int i = 1; i < n - 1; i++) {
			// for checking i symmetric side
			int i_mirror = 2 * C - i;
			if (R > i) {
				// make sure not giving new i that has value above R
				P[i] = Math.min(R - i, P[i_mirror]);
			} else {
				// all P after R will be assigned 0
				P[i] = 0;
			}

			// for expanding the value
			while (T.charAt(i + 1 + P[i]) == T.charAt(i - 1 - P[i])) {
				P[i]++;
			}

			// check whether to update R
			// if current palidromic length have exceeded R
			if (i + P[i] > R) {
				// update longest char to i
				C = i;
				// update new R
				R = i + P[i];
			}

		}

		// get the max value of the P
		int maxLen = 0;
		int centerIndex = 0;
		for (int i = 1; i < n - 1; i++) {
			if (P[i] > maxLen) {
				maxLen = P[i];
				centerIndex = i;
			}
		}
		// get the original index
		int start = (centerIndex - maxLen) / 2;
		return s.substring(start, start + maxLen);
	}

	public String preProcess(String s) {
		int n = s.length();
		if (n == 0) {
			return "^$";
		}
		String ret = "^";
		for (int i = 0; i < n; i++)
			ret += "#" + s.charAt(i);
		ret += "#$";
		return ret;
	}
}