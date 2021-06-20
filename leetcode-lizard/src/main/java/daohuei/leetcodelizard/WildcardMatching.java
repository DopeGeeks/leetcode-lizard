package daohuei.leetcodelizard;

/*
 * 44. Wildcard Matching
 * Link: https://leetcode.com/problems/wildcard-matching/
 */
public class WildcardMatching {
    /**
     * @author: daohuei
     * @description: dp
     * @time: O(|p|*|s|) 2-loop
     * @space: O(|p|): for dp array
     */
    public boolean dp(String s, String p) {
        boolean[][] dp = new boolean[2][p.length() + 1];
        dp[s.length() % 2][p.length()] = true;

        // iterate backward
        for (int i = s.length(); i >= 0; i--) {
            for (int j = p.length(); j >= 0; j--) {
                // text and pattern over the bound (first iteration)
                if (i == s.length() && j == p.length())
                    continue;

                // check if the first char match
                boolean first_match = (i < s.length() && j < p.length()
                        && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '?' || p.charAt(j) == '*'));
                // if it is the wild card case
                if (j < p.length() && p.charAt(j) == '*') {
                    // check the 2nd previous index in same dimension => '*' related check
                    // or check previous one from other dimension
                    dp[i % 2][j] = dp[i % 2][j + 1] || first_match && dp[(i + 1) % 2][j];
                } else {
                    // check first char match and whether previous one from other dimension is true
                    dp[i % 2][j] = first_match && dp[(i + 1) % 2][j + 1];
                }
            }
        }
        return dp[0][0];
    }

    /**
     * @author: daohuei
     * @description: iteration
     * @time: O(|p|*|s|) 2-loop
     * @space: O(1): not using any
     */
    public boolean iteration(String s, String p) {
        int i = 0, j = 0, match = 0, starIdx = -1;
        // go through the string
        while (i < s.length()) {
            // if matching in ? case or same canse
            if (j < p.length() && (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j))) {
                // move forward
                i++;
                j++;
            }
            // if start case
            else if (j < p.length() && p.charAt(j) == '*') {
                // memorize the star position
                starIdx = j;
                // and last matched position
                match = i;
                // pattern move forward
                j++;
            }
            // if star index not -1: there is star matching ongoing
            else if (starIdx != -1) {
                // move p next to the star
                j = starIdx + 1;
                // last matched move forward
                match++;
                // move string to the last matched position
                i = match;
            }
            // if no star ongoing or no any matched and loop is not ending: false
            else
                return false;
        }

        // clear the residual of star
        while (j < p.length() && p.charAt(j) == '*')
            j++;

        // afterward the p should reach the end
        // or else it will have some ? left and p will not reach the end
        return j == p.length();
    }
}