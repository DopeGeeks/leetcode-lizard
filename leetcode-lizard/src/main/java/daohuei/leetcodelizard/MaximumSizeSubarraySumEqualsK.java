package daohuei.leetcodelizard;

import java.util.HashMap;
import java.util.Map;

/*
 * 325. Maximum Size Subarray Sum Equals k
 * Link: https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
 */
/*
Given an array nums and a target value k, find the maximum length of a subarray that sums to k. 
If there isn't one, return 0 instead.
Note:
The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
Example 1:
Input: nums = [1, -1, 5, -2, 3], k = 3
Output: 4 
Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
Example 2:
Input: nums = [-2, -1, 2, 1], k = 1
Output: 2 
Explanation: The subarray [-1, 2] sums to 1 and is the longest.
Follow Up:
Can you do it in O(n) time?
*/

public class MaximumSizeSubarraySumEqualsK {

    /**
     * @author: daohuei
     * @description: hash map method
     * @time: O(n): go through every number once
     * @space: O(n): for hashmap of number and its accumulated sum
     */
    public int maxSubArrayLen(int[] nums, int k) {
        // empty case
        if (nums == null || nums.length == 0)
            return 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int preSum = 0, max = 0;
        // go through every num
        for (int i = 0; i < nums.length; i++) {
            // get the accumulated sum
            preSum += nums[i];
            // the sum is the key of the map
            // the diff between accumulated sum and target is another accumulated sum, and if it exist
            // we get the value from the map, which shows the index of the num
            if (map.containsKey(preSum - k)) {
                // i - map value is the length of the subarray
                // then get the max length
                max = Math.max(max, i - map.get(preSum - k));
            }
            // put sum and its subarray end index into it
            if (!map.containsKey(preSum)) {
                map.put(preSum, i);
            }
        }
        return max;
    }
}
