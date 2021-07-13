package daohuei.leetcodelizard;

/*
 * 334. Increasing Triplet Subsequence
 * Link: https://leetcode.com/problems/increasing-triplet-subsequence/
 */
public class IncreasingTripletSubsequence {
    /**
     * @author: daohuei
     * @description: 2 min method
     * @time: O(n): for nums loop
     * @space: O(1): not using any
     */
    public boolean increasingTriplet(int[] nums) {
        // min
        int min = Integer.MAX_VALUE;
        // second min
        int min2 = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n <= min)
                min = n;
            // n <= min
            else if (n <= min2)
                min2 = n;
            else
                // if there exist n s.t. n>min2>min
                return true;
        }
        return false;
    }
}