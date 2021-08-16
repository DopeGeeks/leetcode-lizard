package daohuei.leetcodelizard;

/*
 * 213. House Robber II
 * Link: https://leetcode.com/problems/house-robber-ii/
 */
public class HouseRobberTwo {
    /**
     * @author: daohuei
     * @description: dp
     * @time: O(n): just a single loop
     * @space: O(n): 2 n-length dp array
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        int[][] dp = new int[n][2];
        // there will be 2 conditions
        // not robbing first house, but robbing second
        dp[0][0] = 0;
        dp[1][0] = nums[1];
        // and
        // robbing first house and not robbing second
        dp[0][1] = nums[0];
        dp[1][1] = dp[0][1]; // this since not robbing so it will be 0 + nums[0]

        for (int i = 2; i < n; i++) {
            // if reaching last house
            if (i == n - 1) {
                // compare robbing and not robbing situation
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 2][0] + nums[i]);
                // since the first rob, we are not robbing this house
                dp[i][1] = dp[i - 1][1];
            } else {
                // compare robbing and not robbing situation
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 2][0] + nums[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][1] + nums[i]);
            }
        }
        // compare 2 situation's final profit
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }
}