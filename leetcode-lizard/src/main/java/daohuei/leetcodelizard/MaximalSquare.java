package daohuei.leetcodelizard;

/*
 * 221. Maximal Square
 * Link: https://leetcode.com/problems/maximal-square/description/
 */
public class MaximalSquare {
    /**
     * @author: daohuei
     * @description: dp
     * @time: O(mn): go through all cells in the matrix
     * @space: O(mn): for dp array
     */
    public int maximalSquare(char[][] matrix) {
        // empty case
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            return 0;
        // for maxtrix height and width
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        int maxLen = 0;

        // go through every cell(but 0 row and col)
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // if 0 then skip
                if (matrix[i - 1][j - 1] == '0')
                    continue;
                // compare previous left and top and leftup => choose a smallest side
                dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                // get the max if previous max or current vakue
                maxLen = Math.max(maxLen, dp[i][j]);
            }
        }
        return maxLen * maxLen;
    }
}