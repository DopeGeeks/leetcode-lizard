package daohuei.leetcodelizard;

/*
 * 276. Paint Fence
 * Link: https://leetcode.com/problems/paint-fence/description/
 */
/*
There is a fence with n posts, each post can be painted with one of the k colors.
You have to paint all the posts such that no more than two adjacent fence posts have the same color.
Return the total number of ways you can paint the fence.
Note:
n and k are non-negative integers.
Tags: Dynamic Programming
Similar Problems: (E) House Robber, (M) House Robber II, (M) Paint House, (H) Paint House II
*/
public class PaintFence {
    /**
     * @author: daohuei
     * @description: dp
     * @time: O(n): for dp single loop
     * @space: O(n): for dp array
     */
    public int numWays(int n, int k) {
        // edge case
        if (n <= 1 || k <= 0) {
            return n * k;
        }
        int[] dp = new int[n + 1];
        // case when n is 0 - 2
        dp[0] = 0;
        dp[1] = k;
        // [1,2] same color + [1,2] diff color
        dp[2] = k + k * (k - 1);

        for (int i = 3; i <= n; i++) {
            // 2 circumstances
            // 1. if i is different than previous, than it will be (k-1) * dp[i-1]
            // 2. if i is same as previous, then it must be different from i-2 -> (k-1) *
            // dp[i-2]
            dp[i] = ((k - 1) * dp[i - 1]) + ((k - 1) * dp[i - 2]);
        }

        return dp[n];
    }
}
