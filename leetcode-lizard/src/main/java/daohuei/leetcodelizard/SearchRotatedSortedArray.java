package daohuei.leetcodelizard;

/*
 * 33. Search in Rotated Sorted Array
 * Link: https://leetcode.com/problems/search-in-rotated-sorted-array/
 */
public class SearchRotatedSortedArray {
    /**
     * @author: daohuei
     * @description: binary search
     * @time: O(log(n)) since using binary search
     * @space: O(1) not using any
     */
    public int binaryMethod(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (target == nums[mid]) {
                return mid;
            }

            // left part is sorted
            if (nums[start] <= nums[mid]) {
                // target is in left part
                if (target >= nums[start] && target < nums[mid]) {
                    // move left
                    end = mid - 1;
                } else {
                    // move right
                    start = mid + 1;
                }
                // right part is sorted
            } else {
                // target is in right part
                if (target > nums[mid] && target <= nums[end]) {
                    // move right
                    start = mid + 1;
                } else {
                    // move left
                    end = mid - 1;
                }
            }
        }
        return -1;
    }
}
