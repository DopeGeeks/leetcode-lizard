package daohuei.leetcodelizard;

/*
 * 256. Paint House
 * Link: https://leetcode.com/problems/paint-house/description/
 */

/*
There are a row of n houses, each house can be painted with one of the three colors: 
red, blue or green. The cost of painting each house with a certain color is different. 
You have to paint all the houses such that no two adjacent houses have the same color.
The cost of painting each house with a certain color is represented by a n x 3 cost matrix. 
For example, costs[0][0] is the cost of painting house 0 with color red; 
costs[1][2] is the cost of painting house 1 with color green, and so on... 
Find the minimum cost to paint all houses.
Note:
All costs are positive integers.
*/

public class PaintHouse {

    int R = 0, B = 1, G = 2;

    /**
     * @author: daohuei
     * @description: simple dp
     * @time: O(n): go through costs
     * @space: O(n): the number of houses in dp array
     */
    public int simpleDPMethod(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0] == null || costs[0].length == 0)
            return 0;
        int n = costs.length; // n houses
        int[][] dp = new int[n + 1][3];
        dp[0][R] = dp[0][B] = dp[0][G] = 0; // house no.0
        // a simple way with dp
        for (int i = 1; i <= n; i++) {
            // since adjacent color cannot be the same
            // compare previous 2 different colors in dp and add with current color cost
            dp[i][R] = Math.min(dp[i - 1][B], dp[i - 1][G]) + costs[i - 1][R];
            dp[i][B] = Math.min(dp[i - 1][R], dp[i - 1][G]) + costs[i - 1][B];
            dp[i][G] = Math.min(dp[i - 1][R], dp[i - 1][B]) + costs[i - 1][G];
        }

        int min = Integer.MAX_VALUE;
        // compare 3 color cost in the last dp
        for (int j = 0; j < 3; j++)
            min = Math.min(min, dp[n][j]);
        return min;
    }
}
