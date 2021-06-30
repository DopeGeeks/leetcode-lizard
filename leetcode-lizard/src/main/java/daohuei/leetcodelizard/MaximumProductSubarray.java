package daohuei.leetcodelizard;

/*
 * 152. Maximum Product Subarray
 * Link: https://leetcode.com/problems/maximum-product-subarray/
 */
public class MaximumProductSubarray {
    /**
     * @author: daohuei
     * @description: dp
     * @time: O(n): length for nums
     * @space: O(1): only dpMax dpMin
     */
    public int dp(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int dpMax = nums[0];
        int dpMin = nums[0];
        // for storing final max answer
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            int preMax = dpMax;
            // since negative number may be pretty large when abs it
            // we need to check either:
            // positive with largest amount
            dpMax = Math.max(dpMin * nums[i], Math.max(dpMax * nums[i], nums[i]));
            // negative with largest amount
            dpMin = Math.min(dpMin * nums[i], Math.min(preMax * nums[i], nums[i]));
            // and check whether the product is larger than current value
            max = Math.max(max, dpMax);
        }
        return max;
    }

    /**
     * @author: daohuei
     * @description: forward and backward
     * @time: O(n): length for nums
     * @space: O(1): only dpMax dpMin
     */
    public int forwardBackward(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int max = 1;
        int res = nums[0];
        // try forward
        for (int i = 0; i < nums.length; i++) {
            // get the accumulated product
            max *= nums[i];
            res = Math.max(res, max);
            if (max == 0) {
                max = 1;
            }

        }
        max = 1;
        // try backward
        for (int i = nums.length - 1; i >= 0; i--) {
            // get the accumulated product
            max *= nums[i];
            res = Math.max(res, max);
            if (max == 0) {
                max = 1;
            }
        }

        return res;
    }
}