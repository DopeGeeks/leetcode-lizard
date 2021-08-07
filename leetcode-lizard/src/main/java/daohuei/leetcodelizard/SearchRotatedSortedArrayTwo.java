package daohuei.leetcodelizard;

/*
 * 81. Search in Rotated Sorted Array II
 * Link: https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/
 */
public class SearchRotatedSortedArrayTwo {
    /**
     * @author: daohuei
     * @description: binary search method, need to consider where the pivot at
     * @time: O(logn): for binary search
     * @space: O(1): not using any
     */
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return false;
        // binary search method
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            // mid point
            int mid = start + (end - start) / 2;
            // if found
            if (nums[mid] == target)
                return true;
            // if start is smaller: not over pivot
            if (nums[start] < nums[mid]) {
                // if target in the interval of start and mid (which must be sorted since no
                // pivot there)
                // update end to the mid
                if (nums[start] <= target && target <= nums[mid])
                    end = mid;
                // if not then update start to the mid
                else
                    start = mid;
            }
            // if start is larger: over pivot
            else if (nums[start] > nums[mid]) {
                // if in the interval of mid and end (which must be sorted since no pivot there)
                // update start to the mid
                if (nums[mid] <= target && target <= nums[end])
                    start = mid;
                // if not then update end to the mid
                else
                    end = mid;
            }
            // if same, just keep looking to the next(but probably all duplicate until mid)
            else
                start++;
        }
        return nums[start] == target || nums[end] == target;
    }
}