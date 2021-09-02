package daohuei.leetcodelizard;

/*
 * 309. Best Time to Buy and Sell Stock with Cooldown
 * Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/
 */
public class BestTimeToBuyAndSellStockWithCooldown {
    /**
     * @author: daohuei
     * @description: dp
     * @time: O(n): single loop
     * @space: O(n): for dp array
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int n = prices.length;
        int[][] dp = new int[n][2];
        // second column 0 -> buy, 1 -> sell
        dp[0][0] = -prices[0]; // buy
        dp[0][1] = 0; // sell

        for (int i = 1; i < n; i++) {

            // no stock (sell)
            // choose the max between(previous sell or previous havestock and sell current
            // stock)
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);

            // haveStock (buy)
            // if at the time above 2
            if (i >= 2) {
                // max between we previous has stock and hold or we buy current stock after
                // cooldown of previous sell
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 2][1] - prices[i]);
            } else {
                dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            }
        }
        // return the last sell
        return dp[n - 1][1];
    }
}