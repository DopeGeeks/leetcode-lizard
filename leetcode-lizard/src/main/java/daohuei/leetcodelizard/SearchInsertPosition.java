package daohuei.leetcodelizard;

/*
 * 35. Search Insert Position
 * Link: https://leetcode.com/problems/search-insert-position/
 */
public class SearchInsertPosition {
    /**
     * @author: daohuei
     * @description: binary search
     * @time: O(logn): n = length of nums
     * @space: O(1): not using any
     */
    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        if (nums.length == 0) {
            return 0;
        }
        // binary search
        while (start <= end) {
            int mid = (start + end) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }
}