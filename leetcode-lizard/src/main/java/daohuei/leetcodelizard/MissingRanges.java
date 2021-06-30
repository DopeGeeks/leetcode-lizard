package daohuei.leetcodelizard;

import java.util.LinkedList;
import java.util.List;

/*
 * 163. Missing Ranges 
 * https://leetcode.com/problems/missing-ranges/
 */
public class MissingRanges {
    /**
     * @author: daohuei
     * @description: iteration
     * @time: O(n): length of nums
     * @space: O(1): not using any
     */
    public List<String> recursion(int[] nums, int lower, int upper) {
        List<String> result = new LinkedList<String>();
        // empty case
        if (nums.length == 0) {
            // just get the range string
            findRange(result, lower, upper);
            return result;
        }
        // if the nums[0] is not the min value
        // check the lower bound range
        if (nums[0] != Integer.MIN_VALUE) {
            findRange(result, lower, nums[0] - 1);
        }
        // go through nums
        for (int i = 0; i < nums.length - 1; i++) {
            // if the nums is same as next, skip!
            if (nums[i] == nums[i + 1]) {
                continue;
            }
            // else just get the range
            findRange(result, nums[i] + 1, nums[i + 1] - 1);
        }
        // get the upper bound range
        if (nums[nums.length - 1] != Integer.MAX_VALUE) {
            findRange(result, nums[nums.length - 1] + 1, upper);
        }

        return result;
    }

    private void findRange(List<String> result, int low, int up) {
        if (low > up) {
            return;
        }
        if (low == up) {
            result.add((low) + "");
            return;
        }
        result.add(low + "->" + up);
    }
}
