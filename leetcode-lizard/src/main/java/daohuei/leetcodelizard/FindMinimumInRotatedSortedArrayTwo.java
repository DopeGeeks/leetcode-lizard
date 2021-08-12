package daohuei.leetcodelizard;

/*
 * 154. Find Minimum in Rotated Sorted Array II
 * Link: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/description/
 */
public class FindMinimumInRotatedSortedArrayTwo {
    /**
     * @author: daohuei
     * @description: brutal method method
     * @time: O(n): go through every nums
     * @space: O(1): not using any
     */
    public int brutalForceMethod(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int min = Integer.MAX_VALUE;
        // brutal search through nums and get the min
        for (int i = 0; i < nums.length; i++) {
            if (min > nums[i]) {
                min = nums[i];
            }
        }
        return min;
    }

    /**
     * @author: daohuei
     * @description: binary search method
     * @time: O(logn): binary search
     * @space: O(1): not using any
     */
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int start = 0;
        int end = n - 1;
        // binary search
        while (start + 1 < end) {
            // get the mid point
            int mid = (start + end) / 2;
            // if duplicate
            if (nums[mid] == nums[end]) {
                // move end backward
                end--;
            }
            // if larger than right, search right half
            else if (nums[mid] > nums[end]) {
                start = mid;
            }
            // if larger than left, search left half
            else {
                end = mid;
            }
        }
        // finally we will get two least number, pick the smaller one
        return nums[start] < nums[end] ? nums[start] : nums[end];
    }
}