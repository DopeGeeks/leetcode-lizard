package daohuei.leetcodelizard;

import java.util.Arrays;

/*
 * 377. Combination Sum IV
 * Link: https://leetcode.com/problems/combination-sum-iv/description/
 */
public class CombinationSumFour {
    /**
     * @author: daohuei
     * @description: dp method
     * @time: O(n^2): nested iteration
     * @space: O(n): for dp array
     */
    public int combinationSum4(int[] nums, int target) {
        // check empty case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // sort the number first
        Arrays.sort(nums);
        // dp array has size of target + 1
        // which contains every combination sum to that number of index
        int[] dp = new int[target + 1];
        // target == 0, 1 way to form, [] an empty combination
        dp[0] = 1;

        // go through every sum to the target
        for (int i = 1; i <= target; i++) {
            // go through every number that less than target sum
            for (int j = 0; j < nums.length && nums[j] <= i; j++) {
                // calculate the combination count with other sum and store to the dp
                // i - nums[j]: target and valid number's diff
                dp[i] += dp[i - nums[j]];
            }
        }
        return dp[target];
    }
}