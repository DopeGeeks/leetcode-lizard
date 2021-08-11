package daohuei.leetcodelizard;

/*
 * 123. Best Time to Buy and Sell Stock III
 * Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/
 */
public class BestTimeToBuyAndSellStockThree {
    /**
     * @author: daohuei
     * @description: dp method
     * @time: O(n): for go through the days
     * @space: O(n): for dp array
     */
    public int dp(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int profit = 0;
        int n = prices.length;
        // dp for size 5
        // 5 indicates one buy one sold and at most two times
        int[][] dp = new int[n][5];

        dp[0][0] = 0; // No transaction on day 0
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < 5; j++) {
                // Accumulate partial profit regardless of stock status
                int dailyDiff = prices[i] - prices[i - 1];
                // sold
                if (j % 2 == 0) {
                    // compare profit between sold at previous day(best profit) and sold at
                    // today(potential best)
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] + dailyDiff);
                    // Find best profit when selling stock
                    profit = Math.max(profit, dp[i][j]);
                }
                // buy
                else {
                    // compare profit between bought at previous day add today's profit, and not
                    // bought at today
                    dp[i][j] = Math.max(dp[i - 1][j] + dailyDiff, dp[i - 1][j - 1]);
                }
            }
        }
        return profit;
    }

    /**
     * @author: daohuei
     * @description: left right array method
     * @time: O(n): for go through the days
     * @space: O(n): for dp array
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int[] leftProfit = new int[prices.length];
        int[] rightProfit = new int[prices.length];
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            // the lowest price of day i
            min = Math.min(min, prices[i]);
            // compare yesterday and current profit, choose the bigger one
            // indicates potential largest profit when selling
            leftProfit[i] = Math.max(leftProfit[i - 1], prices[i] - min);
        }
        int max = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            // compare next day and current profit, choose the bigger one
            // indicates potential largest profit when buying
            rightProfit[i] = Math.max(rightProfit[i + 1], max - prices[i]);
        }
        int profit = 0;
        // sum up two potential largest profit
        for (int i = 0; i < prices.length; i++) {
            profit = Math.max(profit, leftProfit[i] + rightProfit[i]);
        }
        return profit;
    }
}