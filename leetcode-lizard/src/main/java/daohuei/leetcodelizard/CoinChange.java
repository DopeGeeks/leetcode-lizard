package daohuei.leetcodelizard;

/*
 * 322. Coin Change
 * Link: https://leetcode.com/problems/coin-change
 */
public class CoinChange {
    /**
     * @author: daohuei
     * @description: dp
     * @time: O(kn): for iteration
     * @space: O(k): k = amount
     */
    public int dp(int[] coins, int amount) {
        if (amount < 1)
            return 0;
        int[] dp = new int[amount + 1];
        int sum = 1;

        while (sum <= amount) {
            int min = -1;
            for (int coin : coins) {
                // if sum larger than the current number, and diff btwn sum and coin's min is
                // not empty
                if (sum >= coin && dp[sum - coin] != -1) {
                    // the temp is the min count of diff + count of this coin
                    int temp = dp[sum - coin] + 1;
                    // if min is empty or temp is less than min -> indicate new ans
                    if (min == -1 || temp < min) {
                        min = temp;
                    }
                }
            }
            // assgin the min to the sum
            dp[sum] = min;
            sum++;
        }
        return dp[amount];
    }

    /**
     * @author: daohuei
     * @description: recursion
     * @time: O(kn): for all iteration
     * @space: O(k): k = amount, for recursion stack
     */
    public int coinChange(int[] coins, int amount) {
        if (amount < 1)
            return 0;
        return helper(coins, amount, new int[amount]);
    }

    private int helper(int[] coins, int sum, int[] count) {
        // sum < 0 : invalid
        if (sum < 0)
            return -1;
        // sum is 0, finished
        if (sum == 0)
            return 0;
        // calculated before
        if (count[sum - 1] != 0)
            return count[sum - 1];

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = helper(coins, sum - coin, count);
            // if res is the new answer
            if (res >= 0 && res < min)
                // update min
                min = 1 + res;
        }
        // update count
        count[sum - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[sum - 1];
    }
}