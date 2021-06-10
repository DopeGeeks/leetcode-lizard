package daohuei.leetcodelizard;

/*
 * 121. Best Time to Buy and Sell Stock
 * Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 */
public class BestTimeToBuyAndSellStock {
	/**
	 * @author: daohuei
	 * @description: 2 pointer
	 * @time: O(n) n for prices length
	 * @space: O(1) not using any
	 */
	public int twoPointer(int[] prices) {

		int maxProfit = 0;
		// 2 pointer for buy and sell
		int buy = 0;
		int sell = 0;
		// traverse with sell pointer
		for (; sell < prices.length; sell++) {
			// if no profitting
			if (prices[sell] < prices[buy]) {
				// update buy position with sell pointer
				// which has lower price
				buy = sell;
			} else {
				// calculate profit and compare
				maxProfit = Math.max(maxProfit, prices[sell] - prices[buy]);

			}
		}
		return maxProfit;
	}

	/**
	 * @author: daohuei
	 * @description: dp
	 * @time: O(n) n for prices length
	 * @space: O(1) not using any but dp only
	 */
	public int dp(int[] prices) {
		int n = prices.length;
		int dp = 0;
		int max = 0;
		for (int i = 1; i < n; i++) {
			// calculate profit
			int num = prices[i] - prices[i - 1];
			// compare profit and (previous + current profit)
			dp = Math.max(dp + num, num);
			// if keep making profit, the profit will be recorded
			max = Math.max(max, dp);
		}
		return max;
	}
}