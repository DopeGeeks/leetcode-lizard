package daohuei.leetcodelizard;

/*
 * 188. Best Time to Buy and Sell Stock IV
 * Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/description/
 */
public class BestTimeToBuyAndSellStockFour {
    /**
     * @author: daohuei
     * @description: dp
     * @time: O(kn): for nested loop
     * @space: O(kn): for dp array
     */
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length < 2 || k <= 0) {
            return 0;
        }
        // if k larger than the length => means can not have more than k transaction
        if (k >= prices.length) {
            // directly return largest profit
            int profit = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1]) {
                    profit += prices[i] - prices[i - 1];
                }
            }
            return profit;
        }
        int[][] local = new int[prices.length][k + 1];
        int[][] global = new int[prices.length][k + 1];
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            for (int j = 1; j <= k; j++) {
                // compare previous transaction or current transaction with previous diff as
                // local max
                local[i][j] = Math.max(global[i - 1][j - 1] + diff, local[i - 1][j] + diff);
                // compare with not buying it and buying it in current transaction
                global[i][j] = Math.max(global[i - 1][j], local[i][j]);
            }
        }
        return global[prices.length - 1][k];
    }
}