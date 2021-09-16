package daohuei.leetcodelizard;

import java.util.Random;

/*
 * 398. Random Pick Index
 * Link: https://leetcode.com/problems/random-pick-index/
 */
public class RandomPickIndex {
    /**
     * @author: daohuei
     * @description: dp method
     * @time: O(n): go thru every number
     * @space: O(1): not using any
     */

    Random rd;
    int[] nums;

    public RandomPickIndex(int[] nums) {
        // create the randomizer
        this.rd = new Random();
        this.nums = nums;
    }

    public int pick(int target) {
        int count = 1, result = -1;
        for (int i = 0; i < nums.length; i++) {
            // if not the target, skip it
            if (nums[i] != target)
                continue;
            // if the number is picked => update the number
            // logic: we picked 0, and count represent the amount of target index
            if (rd.nextInt(count++) == 0)
                result = i;
        }
        return result;
    }
}
