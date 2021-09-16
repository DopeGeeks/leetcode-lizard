package daohuei.leetcodelizard;

/*
 * 63. Unique Paths II
 * Link: https://leetcode.com/problems/unique-paths-ii/description/
 */
public class UniquePathTwo {
    /**
     * @author: daohuei
     * @description: dp method
     * @time: O(mn): go thru every cell
     * @space: O(mn): dp array
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // empty case
        if (obstacleGrid == null || obstacleGrid.length == 0)
            return 0;

        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        // init the dp with every cell in first col and row with 1 (except obstacle)
        for (int i = 0, j = 0; j < n; j++) {
            if (obstacleGrid[i][j] == 1)
                break;
            dp[i][j] = 1;
        }
        for (int i = 0, j = 0; i < m; i++) {
            if (obstacleGrid[i][j] == 1)
                break;
            dp[i][j] = 1;
        }

        // go thru every cells
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // if obstacle, skip
                if (obstacleGrid[i][j] == 1)
                    continue;
                // sum up left and up cell's count
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        // the final cell will be the total count of the way to reach the goal
        return dp[m - 1][n - 1];
    }
}