package daohuei.leetcodelizard;

/*
 * 162. Find Peak Element
 * Link: https://leetcode.com/problems/find-peak-element/
 */
public class FindPeakElement {
    /**
     * @author: daohuei
     * @description: iteration
     * @time: O(n): climb until go down
     * @space: O(1): not using any
     */
    public int iteration(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            // if go down
            if (nums[i] > nums[i + 1]) {
                return i;
            }
            // else it still go up
        }
        // go up until the end
        return nums.length - 1;
    }

    /**
     * @author: daohuei
     * @description: binary search
     * @time: O(log(n)): binary search until start and end meet
     * @space: O(1): not using any
     */
    public int binarySearch(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        // binary search
        while (start != end) {
            // get the mid point
            // >>> 1 right shift by 1 and do not keep the right most digit
            int mid = (start + end) >>> 1;
            // if the mid point is smaller than right one
            if (nums[mid] < nums[mid + 1]) {
                // climb right
                start = mid + 1;
            } else {
                // climb left
                end = mid;
            }
        }
        return start;
    }
}