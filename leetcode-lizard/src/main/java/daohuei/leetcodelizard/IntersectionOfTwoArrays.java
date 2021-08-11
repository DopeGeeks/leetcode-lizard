package daohuei.leetcodelizard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * 349. Intersection of Two Arrays
 * Link: https://leetcode.com/problems/intersection-of-two-arrays/description/
 */
public class IntersectionOfTwoArrays {
    /**
     * @author: daohuei
     * @description: hash method
     * @time: O(n): go through every nums
     * @space: O(n): for hash set
     */
    public int[] hashSetMethod(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        // add numbers in to the set for the first array
        for (int num : nums1)
            set.add(num);
        Set<Integer> rst = new HashSet<>();
        // if nums2 has same in 1, add to another set
        for (int num : nums2) {
            if (set.contains(num))
                rst.add(num);
        }

        // transfer from hashset to int array
        int i = 0;
        int[] result = new int[rst.size()];
        for (int num : rst)
            result[i++] = num;
        return result;
    }

    /**
     * @author: daohuei
     * @description: binary search method
     * @time: O((m+n)log(n)): go through nums by binary search
     * @space: O(n): for hash set
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);// nLog(n)
        Set<Integer> resultSet = new HashSet<>();
        // search every num by nums2 in sorted nums1 with binary search
        for (int num : nums2) { // nLog(m)
            // if found, add to the set
            if (binarySearch(nums1, num)) {
                resultSet.add(num);
            }
        }
        // hashset to string
        int i = 0;
        int[] result = new int[resultSet.size()];
        for (int num : resultSet) {
            result[i++] = num;
        }
        return result;
    }

    private boolean binarySearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] < target) {
                start = mid + 1;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}