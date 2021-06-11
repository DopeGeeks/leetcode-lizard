package daohuei.leetcodelizard;

/*
 * 122. Best Time to Buy and Sell Stock II
 * Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class BestTimeToBuyAndSellStockTwo {
	/**
	 * @author: daohuei
	 * @description: iteration
	 * @time: O(n): n = prices length
	 * @space: O(1): not using any
	 */
	public int maxProfit(int[] prices) {
		int profit = 0;
		for (int i = 1; i < prices.length; i++) {
			int sub = prices[i] - prices[i - 1];
			// get positive profit only
			if (sub > 0) {
				profit += sub;
			}
		}
		return profit;
	}
}