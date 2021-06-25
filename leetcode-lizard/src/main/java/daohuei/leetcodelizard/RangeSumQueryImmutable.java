package daohuei.leetcodelizard;

import java.util.HashMap;
import java.util.Map;

/*
 * 303. Range Sum Query - Immutable
 * Link: https://leetcode.com/problems/range-sum-query-immutable/description/
 */
public class RangeSumQueryImmutable {
    class BrutalForceNumArray {
        /**
         * @author: daohuei
         * @description: brutal force but optimized with hash map
         * @time: O(n^2): for the worst case
         * @space: O(n^2): for storing every combination in the map
         */
        public int[] numArray;
        public Map<String, Integer> map;

        public BrutalForceNumArray(int[] nums) {
            numArray = nums;
            map = new HashMap<String, Integer>();
        }

        public int sumRange(int left, int right) {
            // get the range combination as key
            String key = left + "@" + right;
            // if key already got answer
            if (map.containsKey(key)) {
                return map.get(key);
            }

            // sum it up!
            int sum = 0;
            for (; left <= right; left++) {
                sum += numArray[left];
            }

            // store the answer
            map.put(key, sum);
            return sum;
        }
    }

    class AccumulatedDifferenceNumArray {
        /**
         * @author: daohuei
         * @description: accumulated difference
         * @time: O(n): for the initialization only
         * @space: O(n): for accu nums
         */
        public int[] numsAccumulate;

        public AccumulatedDifferenceNumArray(int[] nums) {
            numsAccumulate = new int[nums.length + 1];
            numsAccumulate[0] = 0;
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                // store the accumulate sum
                // numsAccumulate[i+1] will store the sum from 0 to i
                numsAccumulate[i + 1] = sum;
            }
        }

        public int sumRange(int left, int right) {
            // the sum range will be the diff of accumulated sum
            return numsAccumulate[right + 1] - numsAccumulate[left];
        }
    }
}
