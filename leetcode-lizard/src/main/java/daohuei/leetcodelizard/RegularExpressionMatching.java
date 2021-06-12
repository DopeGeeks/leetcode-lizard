package daohuei.leetcodelizard;

/*
 * 10. Regular Expression Matching
 * Link: https://leetcode.com/problems/regular-expression-matching/
 */
public class RegularExpressionMatching {
	/**
	 * @author: daohuei
	 * @description: recursion
	 * @time: O(2^|*| * |other p|) since star will generate two recursive
	 * @space: O(log(2^|*| * |other p|)): the recursion stack
	 */
	public boolean isMatch(String s, String p) {
		// empty case
		if (p.isEmpty())
			return s.isEmpty();

		// for check if string exist and the first char match
		boolean first_match = (!s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'));

		// if second is "*" (0-inf)
		if (p.length() >= 2 && p.charAt(1) == '*') {
			// now if the first not match, we can skip this 2 pattern
			// or if the first match, we can see if the next one still fulfill '*' term
			return (isMatch(s, p.substring(2)) || (first_match && isMatch(s.substring(1), p)));
		} else {
			// if no star, we examine next pattern
			return first_match && isMatch(s.substring(1), p.substring(1));
		}
	}

	/**
	 * @author: daohuei
	 * @description: dynamically programming
	 * @time: O(|p|*|s|) 2-loop
	 * @space: O(|p|): for dp array
	 */
	public boolean dp(String s, String p) {
		boolean[][] dp = new boolean[2][p.length() + 1];
		dp[s.length() % 2][p.length()] = true;

		// iterate backward
		for (int i = s.length(); i >= 0; i--) {
			for (int j = p.length(); j >= 0; j--) {
				if (i == s.length() && j == p.length())
					continue;
				boolean first_match = (i < s.length() && j < p.length()
						&& (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.'));
				// if there it is not the last one and previous one is '*'
				if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
					// check the 2nd previous index in same dimension => '*' related check
					// or check previous one from other dimension
					dp[i % 2][j] = dp[i % 2][j + 2] || first_match && dp[(i + 1) % 2][j];
				} else {
					// check first char match and whether previous one from other dimension is true
					dp[i % 2][j] = first_match && dp[(i + 1) % 2][j + 1];
				}
			}
		}
		return dp[0][0];
	}
}