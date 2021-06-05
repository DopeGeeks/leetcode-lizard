package daohuei.leetcodelizard;

/*
 * 53. Maximum Subarray
 * Link: https://leetcode.com/problems/maximum-subarray/
 */
public class MaximumSubarray {
    /**
     * @author: daohuei
     * @description: dynamically programming
     * @time: O(n): just go through the list only
     * @space: O(1): not using any
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int dp = nums[0];
        int max = nums[0];
        // store nums[0] & go through nums
        for (int i = 1; i < n; i++) {
            // if the previous one is not positive, this should be a new start
            // since negative addition can only be smaller
            dp = Math.max(dp + nums[i], nums[i]);
            // check if current subarray sum is larger than current max value
            max = Math.max(max, dp);
        }
        return max;
    }
}