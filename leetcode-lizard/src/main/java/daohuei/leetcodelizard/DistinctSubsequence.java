package daohuei.leetcodelizard;

/*
 * 115. Distinct Subsequences
 * Link: https://leetcode.com/problems/distinct-subsequences/description/
 */
public class DistinctSubsequence {
    /**
     * @author: daohuei
     * @description: dp method, pretty hard
     * @time: O(mn): for iterations
     * @space: O(mn): for dp array
     */
    public int dp(String s, String t) {

        if (s == null || t == null || t.length() > s.length()) {
            return 0;
        } else if (s.equals(t)) {
            return 1;
        }

        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        // dp[i][j]: # of distinct subsequence of B[0 ~ j - 1] that can occur in A[0 ~ i
        // - 1]
        // dp[i][j] = dp[i - 2][j - 1] + dp[i - 2][j - 2]|A[i-1]==B[j-1];
        // go through 2 strings
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // first char in t
                if (j == 0) {
                    // init with 1
                    dp[i][j] = 1;
                    continue;
                }
                // first string in s
                if (i == 0) {
                    // init with 0
                    dp[i][j] = 0;
                    continue;
                }
                // get the previous value
                dp[i][j] = dp[i - 1][j];
                // if previous in s and t is same -> matched
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    // add up previous accumulated match
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    /**
     * @author: daohuei
     * @description: dp method as previous, just narrow it down with a rolling
     *               window(length 2 array)
     * @time: O(mn): for iterations
     * @space: O(n): for dp array
     */
    public int numDistinct(String s, String t) {
        if (s == null || t == null || t.length() > s.length()) {
            return 0;
        } else if (s.equals(t)) {
            return 1;
        }

        int m = s.length();
        int n = t.length();
        int[][] dp = new int[2][n + 1];
        int curr = 0;
        int prev = 0;
        for (int i = 0; i <= m; i++) {
            // alway 0 or 1
            prev = curr;
            // then 1 or 0
            curr = 1 - prev;
            for (int j = 0; j <= n; j++) {
                // same as above
                if (j == 0) {
                    dp[curr][j] = 1;
                    continue;
                }
                if (i == 0) {
                    dp[curr][j] = 0;
                    continue;
                }
                dp[curr][j] = dp[prev][j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[curr][j] += dp[prev][j - 1];
                }
            }
        }
        return dp[curr][n];

    }
}