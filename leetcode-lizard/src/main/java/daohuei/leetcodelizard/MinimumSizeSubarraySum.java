package daohuei.leetcodelizard;

/*
 * 209. Minimum Size Subarray Sum
 * Link: https://leetcode.com/problems/minimum-size-subarray-sum/description/
 */
public class MinimumSizeSubarraySum {
    /**
     * @author: daohuei
     * @description: 2 pointer
     * @time: O(n): actually start and end pointer go through nums once
     * @space: O(1): not using any
     */
    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start, end, sum;
        start = end = sum = 0;
        int min = Integer.MAX_VALUE;
        // end pointer loop
        while (end < nums.length) {
            while (sum < target && end < nums.length) {
                // accumulate sum with end pointer
                sum += nums[end];
                end++;
            }
            // move start and log any possible min
            while (sum >= target && start >= 0) {
                // remove start value from sum and record the min value
                min = Math.min(min, end - start);
                sum -= nums[start];
                start++;
            }
        }
        // if min is not recorded, means no match subarray
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}