package daohuei.leetcodelizard;

/*
 * 87. Scramble String
 * https://leetcode.com/problems/scramble-string/description/
 */
public class ScrambleString {
    /**
     * @author: daohuei
     * @description: dp method
     * @time: O(n^4): go through every combination of s1 and s2 and every
     *        combination in every possibility of length
     * @space: O(n^3): for the dp array
     */
    public boolean isScramble(String s1, String s2) {
        // empty case
        if (s1 == null || s2 == null) {
            return s1 == null && s2 == null;
        }
        // should be the same length
        if (s1.length() != s2.length()) {
            return false;
        }
        // if same then is true
        if (s1.equals(s2)) {
            return true;
        }

        int n = s1.length();
        boolean[][][] dp = new boolean[n][n][n + 1];
        // len is the total length of substring
        int len = 1;
        // Initialize with len = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // we init total length 1 as if s1 and s2 are equal
                dp[i][j][len] = s1.charAt(i) == s2.charAt(j);
            }
        }

        // len = 2, as total length
        for (len = 2; len <= n; len++) {
            // go through every combination that has (len) length
            for (int i = 0; i <= n - len; i++) {
                for (int j = 0; j <= n - len; j++) {
                    // w = length of 1st substring
                    for (int w = 1; w < len; w++) {
                        // we want first string and the second string has the same char
                        // first string: start with (i, j) has total length w
                        // second string: start with (i+w, j+w) has total length len-w
                        dp[i][j][len] |= dp[i][j][w] && dp[i + w][j + w][len - w];
                        // if they swap, it will be
                        // first string: start with (i, j + (len-w)) has total length w
                        // second string: start with (i+w, j) has total length len-w
                        dp[i][j][len] |= dp[i][j + (len - w)][w] && dp[i + w][j][len - w];
                    }
                }
            }
        }
        // answer: same start from the first char and has total length of n
        return dp[0][0][n];
    }
}