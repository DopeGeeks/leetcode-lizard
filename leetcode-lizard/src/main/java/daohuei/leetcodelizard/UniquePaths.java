package daohuei.leetcodelizard;

/*
 * 62. Unique Paths
 * Link: https://leetcode.com/problems/unique-paths/
 */
public class UniquePaths {
    /**
     * @author: daohuei
     * @description: dynamically programming
     * @time: O(m*n) loop through the m*n
     * @space: O(m) for array
     */
    public int uniquePaths(int m, int n) {
        // init a new array for column nums
        int[] dp = new int[m];
        // first column are 1:
        // means there are always 1 kind of path to arrive each point of first column
        for (int i = 0; i < m; i++) {
            dp[i] = 1;
        }

        // for every row except 1
        // since the first row is always 1 unique path as well as first column
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                // every position's unique path amount is the node of previous row + previous
                // column
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        // return the arrival point's unique path amount
        return dp[(m - 1)];
    }
}