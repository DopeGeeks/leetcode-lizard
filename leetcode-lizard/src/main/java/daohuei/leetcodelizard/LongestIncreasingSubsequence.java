package daohuei.leetcodelizard;

/*
 * 300. Longest Increasing Subsequence
 * Link: https://leetcode.com/problems/longest-increasing-subsequence/
 */
public class LongestIncreasingSubsequence {
    /**
     * @author: daohuei
     * @description: dp
     * @time: O(n^2): for nested loop
     * @space: O(n): the dp array
     */
    public int dp(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int dp[] = new int[n];
        int max = 1;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                // if it is a valid subsequent
                if (nums[j] < nums[i]) {
                    // update count for i
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // update max
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * @author: daohuei
     * @description: dp with binary search
     * @time: O(nlogn): for nested loop
     * @space: O(n): the dp array
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int dp[] = new int[n];
        int len = 0;
        // go through number
        for (int i = 0; i < n; i++) {
            int start = 0;
            int end = len;
            // binary search
            // search for the place for insert the num[i] in dp
            while (start < end) {
                int mid = (start + end) >>> 1;
                if (dp[mid] < nums[i]) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
            // len is the subsequent length
            dp[start] = nums[i];
            // if the start extend the length
            if (start == len) {
                // len + 1
                len++;
            }
        }
        return len;
    }
}