package daohuei.leetcodelizard;

import java.util.HashMap;

/*
 * 454. 4Sum II
 * Link: https://leetcode.com/problems/4sum-ii/
 */
public class FourSumTwo {
    /**
     * @author: daohuei
     * @description: hash map method
     * @time: O(n^2): 2 2-layer nested loop for nums
     * @space: O(k): all possibility for num1 + num2
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // count for the tuple
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        // go through nums1 and nums2
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                // calculate sum of num1 and num2
                // count it with hash map
                int sumNum1Num2 = nums1[i] + nums2[j];
                map.put(sumNum1Num2, map.getOrDefault(sumNum1Num2, 0) + 1);
            }
        }
        // go through nums3 and nums4
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                // get the distance from sum of 3 and 4 to 0
                // check if any in hashmap matched this value
                int diffFromZero = 0 - (nums3[i] + nums4[j]);
                if (map.getOrDefault(diffFromZero, 0) > 0) {
                    count += map.get(diffFromZero);
                }
            }
        }
        return count;
    }
}