package daohuei.leetcodelizard;

import java.util.Arrays;

/*
 * 16. 3Sum Closest
 * Link: https://leetcode.com/problems/3sum-closest/description/
 */
public class ThreeSumClosest {
    /**
     * @author: daohuei
     * @description: 2 pointer method
     * @time: O(n^2): nested loop for searching the combination
     * @space: O(1): not using any
     */
    public int threeSumClosest(int[] nums, int target) {
        // empty case: since we want 3 sum
        if (nums == null || nums.length < 3) {
            return 0;
        }
        // sort the nums first => nLog(n)
        Arrays.sort(nums);
        // start with smallest result
        long result = nums[0] + nums[1] + nums[2];
        // go through every combination
        for (int i = 0; i < nums.length - 2; i++) {
            // search with 2 pointer
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                // get the sum
                long sum = nums[start] + nums[end] + nums[i];
                // sum too large
                if (sum > target) {
                    // end move left
                    end--;
                }
                // sum too small
                else {
                    // start move right
                    start++;
                }
                // if the diff is closer, update the result
                result = Math.abs(sum - target) < Math.abs(result - target) ? sum : result;
            }
        }
        return (int) result;
    }
}