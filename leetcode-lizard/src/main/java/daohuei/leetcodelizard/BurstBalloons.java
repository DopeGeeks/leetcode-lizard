package daohuei.leetcodelizard;

/*
 * 312. Burst Balloons
 * Link: https://leetcode.com/problems/burst-balloons/description/
 */
public class BurstBalloons {
    /**
     * @author: daohuei
     * @description: dp
     * @time: O(n^3): go through the every kind of length and every combination of
     *        ranges
     * @space: O(n^2): for dp array
     */
    public int dp(int[] nums) {
        // empty case
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[] values = new int[n + 2];
        values[0] = 1;
        values[n + 1] = 1;
        // reassign new array in order to have the edge 1
        for (int i = 1; i <= n; i++) {
            values[i] = nums[i - 1];
        }

        n = values.length;
        // dp[i][j] denotes the max value in range [i, j)
        int[][] dp = new int[n][n];
        // Critical: iterate over RANGE: then come up with i and j; i <= n - len
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = len + i - 1;
                for (int k = i + 1; k < j; k++) {
                    // in this point, i k j are currently only exist in i, j range
                    // i to k and k to j are previous exist but already burst, and their scores have
                    // already been calculated in the dp
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + values[i] * values[k] * values[j]);
                }
            }
        }

        return dp[0][n - 1];
    }

    /**
     * @author: daohuei
     * @description: dfs with dp
     * @time: O(n^3): go through the every kind of length and every combination of
     *        ranges
     * @space: O(n^2): for dp array
     */
    int[][] dp;
    int[] values;

    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        dp = new int[n + 2][n + 2];

        // Initialize new array
        values = new int[n + 2];
        values[0] = values[n + 1] = 1;
        for (int i = 1; i <= n; i++) {
            values[i] = nums[i - 1];
        }

        return dfs(1, n);
    }

    // dp but using dfs
    private int dfs(int i, int j) {
        // memorization
        if (dp[i][j] > 0) {
            return dp[i][j];
        }
        for (int k = i; k <= j; k++) {
            dp[i][j] = Math.max(dp[i][j], dfs(i, k - 1) + dfs(k + 1, j) + values[i - 1] * values[k] * values[j + 1]);
        }
        return dp[i][j];
    }
}