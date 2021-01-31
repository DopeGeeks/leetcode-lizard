package daohuei.leetcodelizard;

import java.util.*;

/*
 * Question: Two Sum
 * Description: Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * 
 * Example 1:
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Output: Because nums[0] + nums[1] == 9, we return [0, 1].
 * 
 * Example 2:
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * 
 * Example 3:
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 */
public class TwoSum {
    // Method 1
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    System.out.print(i + " " + j);
                    return new int[] { i, j };
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    // Method 2
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            // key should be nums[i] to avoid duplicate elements
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            // looking for the complement number
            int complement = target - nums[i];

            // if we contains the complement number AND to avoid using duplicate element
            if (map.containsKey(complement) && map.get(complement) != i) {

                // return the index
                return new int[] { i, map.get(complement) };
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /*
     * Author: @daohuei
     * 
     * @Time_Complexity: O(n).
     * 
     * @Reason: we run through `nums` array once.
     * 
     * @Space_Complexity: O(n).
     * 
     * @Reason: we need a hashmap with the length of `n`.
     */
    public static int[] twoSumHashMap(int[] nums, int target) {
        HashMap<Integer, Integer> complementMap = new HashMap<Integer, Integer>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            // check if the current number has already appeared and matched one of the
            // compliments.
            if (complementMap.containsKey(nums[i])) {
                // if so, this is the answer
                res[0] = complementMap.get(nums[i]);
                res[1] = i;
                return res;
            }
            // get the compliment for each number
            int complement = target - nums[i];
            // put into the map with key: compliment value and value: its index.
            complementMap.put(complement, i);
        }
        return res;
    }

    public static void main(String[] args) {
        TwoSum ts = new TwoSum();
        ts.twoSum(new int[] { 2, 7, 11, 15, 17 }, 19);
    }
}
