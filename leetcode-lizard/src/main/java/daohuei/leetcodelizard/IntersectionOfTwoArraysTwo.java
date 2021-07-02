package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 350. Intersection of Two Arrays II
 * Link: https://leetcode.com/problems/intersection-of-two-arrays-ii/
 */
public class IntersectionOfTwoArraysTwo {
    /**
     * @author: daohuei
     * @description: hash map
     * @time: O(m or n): 3 loop
     * @space: O(m or n): for hash map
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        // new a hashmap for storing 1 array
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<Integer>();

        for (int num1 : nums1) {
            // count the value in nums1
            int count = map.getOrDefault(num1, 0);
            map.put(num1, count + 1);
        }
        for (int num2 : nums2) {
            // decrease the count with the value in nums2
            int count = map.getOrDefault(num2, 0);
            // if the count > 0, it is an intersection
            if (count > 0) {
                map.put(num2, count - 1);
                list.add(num2);
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;
    }
}