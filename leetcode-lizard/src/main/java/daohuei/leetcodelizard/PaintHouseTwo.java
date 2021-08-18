package daohuei.leetcodelizard;

/*
 * 265. Paint House II
 * Link: https://leetcode.com/problems/paint-house-ii/description/
 */
/*
There are a row of n houses, each house can be painted with one of the k colors. 
The cost of painting each house with a certain color is different. 
You have to paint all the houses such that no two adjacent houses have the same color.
The cost of painting each house with a certain color is represented by a n x k cost matrix. 
For example, costs[0][0] is the cost of painting house 0 with color 0; 
costs[1][2] is the cost of painting house 1 with color 2, and so on... 
Find the minimum cost to paint all houses.
Note:
All costs are positive integers.
Follow up:
Could you solve it in O(nk) runtime?
*/
public class PaintHouseTwo {
    /**
     * @author: daohuei
     * @description: dp
     * @time: O(nk^2): go through all combinations of different colors
     * @space: O(nk): for dp array
     */

    public int simpleDPMethod(int[][] costs) {
        if (costs == null || costs.length == 0)
            return 0;
        if (costs.length == 1 && costs[0].length == 1)
            return costs[0][0];

        int n = costs.length, k = costs[0].length;
        // k should be the color of the painting
        int[][] dp = new int[n + 1][k]; // dp[0][0] = dp[0][1] = dp[0][2] ... = 0;
        // go through houses
        for (int i = 1; i <= n; i++) {
            // go through colors
            for (int j = 0; j < k; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                // go through another color
                for (int m = 0; m < k; m++) {// Select color k at index i-1
                    // if same, skip
                    if (j == m)
                        continue;
                    // compare previous different color plus current color and current dp
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][m] + costs[i - 1][j]);
                }
            }
        }
        // compare the last of all colors
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < k; j++)
            min = Math.min(min, dp[n][j]);
        return min;
    }

    /**
     * @author: daohuei
     * @description: dp with nk time
     * @time: O(nk): go through k with 2 loops for storing min and second min index
     *        then build the dp array
     * @space: O(nk): for dp array
     */

    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        } else if (costs.length == 1 && costs[0].length == 1) {
            return costs[0][0];
        }
        int minCost = Integer.MAX_VALUE;
        int n = costs.length;
        // number of colors
        int k = costs[0].length;
        int[][] dp = new int[n + 1][k];
        // Paint 0 houses should be initialized with 0 cost
        for (int j = 0; j < k; j++) {
            dp[0][j] = 0;
        }

        // go through houses
        for (int i = 1; i <= n; i++) {
            // Find minSecond and min indexes
            // min value: dp[i - 1][minIndex]
            // 2nd min value: dp[i - 1][minSecIndex]
            int minIndex = -1;
            int minSecIndex = -1;
            // go through every color
            for (int j = 0; j < k; j++) {
                // if initial state or current min cost is less than true min cost of this house
                if (minIndex == -1 || dp[i - 1][j] < dp[i - 1][minIndex]) {
                    // move previous index with min cost to second min index
                    minSecIndex = minIndex;
                    // update new index with min cost
                    minIndex = j;
                }
                // if initial state with second min index or
                // current min cost is less than second min cost
                else if (minSecIndex == -1 || dp[i - 1][j] < dp[i - 1][minSecIndex]) {
                    // update second min index
                    minSecIndex = j;
                }
            }

            // DP Processing:
            // go through colors
            for (int j = 0; j < k; j++) {
                // if min cost color
                if (j == minIndex) {
                    // update current dp with previous house second min and previous house current
                    // color cost
                    // since we got previous min cost is same color, if we need to paint same color
                    // in current house
                    // we need to have different color of previous house, so we pick second min one
                    // on previous house
                    dp[i][j] = dp[i - 1][minSecIndex] + costs[i - 1][j];
                } else {
                    // if color is not chosen at minIndex, minIndex will represent the overall min
                    dp[i][j] = dp[i - 1][minIndex] + costs[i - 1][j];
                }
                // if reaching the end house
                if (i == n) {
                    // compare all the last min cost
                    minCost = Math.min(minCost, dp[i][j]);
                }
            }

        }
        return minCost;
    }
}
