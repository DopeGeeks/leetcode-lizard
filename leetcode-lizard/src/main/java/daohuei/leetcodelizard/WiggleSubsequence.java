package daohuei.leetcodelizard;

/*
 * 376. Wiggle Subsequence
 * Link: https://leetcode.com/problems/wiggle-subsequence/description/
 */
public class WiggleSubsequence {
    /**
     * @author: daohuei
     * @description: dp method
     * @time: O(n^2): nested iteration
     * @space: O(n): for the dp array
     */
    public int wiggleMaxLength(int[] nums) {
        // if the length is below 2, the answer can be only 1 or 0
        if (nums.length < 2)
            return nums.length;
        // for storing max value is the corresponding number going up or down
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];
        // go through numbers
        for (int i = 1; i < nums.length; i++) {
            // with each previous number
            for (int j = 0; j < i; j++) {
                // if current larger: means going up
                if (nums[i] > nums[j]) {
                    // get the max between max value so far and previous (j) going down+1 length as
                    // now
                    up[i] = Math.max(up[i], down[j] + 1);
                }
                // going down
                else if (nums[i] < nums[j]) {
                    // in the other way
                    down[i] = Math.max(down[i], up[j] + 1);
                }
            }
        }
        // for the last one assume it is up or down and + 1 as the first index
        return 1 + Math.max(down[nums.length - 1], up[nums.length - 1]);
    }
}