package daohuei.leetcodelizard;

/*
 * 153. Find Minimum in Rotated Sorted Array
 * Link: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/
 */
public class FindMinimumInRotatedSortedArray {
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
            // mid point
            int mid = (start + end) / 2;
            // if not out of bound and the number is less than right and left number
            if (mid - 1 >= 0 && mid + 1 < n && nums[mid - 1] > nums[mid] && nums[mid] < nums[mid + 1]) {
                // answer found
                return nums[mid];
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
        // start and end, choose smaller one
        return nums[start] < nums[end] ? nums[start] : nums[end];
    }
}