package daohuei.leetcodelizard;

/*
 * 34. Find First and Last Position of Element in Sorted Array
 * Link: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
    /**
     * @author: daohuei
     * @description: binary search
     * @time: O(logn): binary search for each start and end position
     * @space: O(1): not using any
     */
    public int[] binarySearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int[] ans = { -1, -1 };
        if (nums.length == 0) {
            return ans;
        }
        // finding the start with binary search
        while (start <= end) {
            int mid = (start + end) / 2;
            if (target == nums[mid]) {
                // just for letting start reach the answer and break the loop
                end = mid - 1;
            } else if (target < nums[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        // if start is the answer or exceed the bound
        if (start == nums.length || nums[start] != target) {
            return ans;
        }
        ans[0] = start;
        start = 0;
        end = nums.length - 1;
        // finding the end with binary search
        while (start <= end) {
            int mid = (start + end) / 2;
            if (target == nums[mid]) {
                start = mid + 1;
            } else if (target < nums[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        ans[1] = end;
        return ans;
    }
}