package daohuei.leetcodelizard;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
 * 18. 4Sum
 * Link: https://leetcode.com/problems/4sum/description/
 */
public class FourSum {
    /**
     * @author: daohuei
     * @description: iteration method
     * @time: O(n^3): 3-layer nested loop
     * @space: O(1): not using any
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // sort number first
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        // first loop
        for (int j = 0; j < nums.length - 3; j++) {
            // for preventing duplicated
            if (j == 0 || (j > 0 && nums[j] != nums[j - 1]))
                // 2nd loop
                for (int i = j + 1; i < nums.length - 2; i++) {
                    // for preventing duplicated
                    if (i == j + 1 || nums[i] != nums[i - 1]) {
                        // i and j is first 2 numbers
                        // lo and hi is the rest 2 numbers
                        int lo = i + 1, hi = nums.length - 1, sum = target - nums[j] - nums[i];
                        // do the 2 pointer search
                        while (lo < hi) {
                            // if match
                            if (nums[lo] + nums[hi] == sum) {
                                // add ans
                                res.add(Arrays.asList(nums[j], nums[i], nums[lo], nums[hi]));
                                // if still in the bound
                                // and next is same value
                                // just move forward
                                while (lo < hi && nums[lo] == nums[lo + 1])
                                    lo++;
                                // if prev is same value
                                // just move backward
                                while (lo < hi && nums[hi] == nums[hi - 1])
                                    hi--;
                                // move to next/prev
                                lo++;
                                hi--;
                            } else if (nums[lo] + nums[hi] < sum)
                                // if smaller than the sum
                                lo++;
                            else
                                // if larger than the sum
                                hi--;
                        }
                    }
                }
        }
        return res;
    }
}