package daohuei.leetcodelizard;

/*
 * 72. Edit Distance
 * Link: https://leetcode.com/problems/edit-distance/submissions/
 */
/*
    count with transition count matrix
    [x][r] => x -> xr => add r => 1
    [h][r] -> xh -> xr => replace h with r => previous min + 1 => 0+1 = 1
      x r o s
    x 0 1 2 3
    h 1 1
    o 2
    r 3
    s 4
    e 5
 */
public class EditDistance {
    /**
     * @author: daohuei
     * @description: dp method
     * @time: O(mn): go through every characters
     * @space: O(mn): for dp array
     */
    public int minDistance(String word1, String word2) {
        if (word1 == null && word2 == null)
            return 0;
        // since if one is null, the other length refer the operations nums
        if (word1 == null || word2 == null)
            return word1 == null ? word2.length() : word1.length();

        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // skip and init first index for both i and j
                if (i == 0) {
                    dp[i][j] = j;
                    continue;
                }
                if (j == 0) {
                    dp[i][j] = i;
                    continue;
                }
                // if num in i-1 is same as j-1: means remain, nothing to change
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // update current dp as previous
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // if different, means a single change
                    // count one with smallest previous change count
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                }
            }
        }
        return dp[m][n];
    }
}