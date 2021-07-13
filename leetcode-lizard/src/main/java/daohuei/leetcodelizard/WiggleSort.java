package daohuei.leetcodelizard;

/*
 * 280. Wiggle Sort
 * Link: https://leetcode.com/problems/wiggle-sort/
 */
public class WiggleSort {
    /**
     * @author: daohuei
     * @description: linear method
     * @time: O(n): n for all nums in linear
     * @space: O(1): not using any
     */
    public void wiggleSort(int[] nums) {
        // since nums[0] <= nums[1] >= nums[2] <= nums[3]....
        // so we can conclude that
        // num[odd] >= num[odd-1]
        // num[even] <= num[even-1]
        for (int i = 1; i < nums.length; ++i) {
            // odd
            if (i % 2 == 1) {
                if (nums[i] < nums[i - 1]) {
                    swap(nums, i, i - 1);
                }
            }
            // even
            else {
                if (nums[i] > nums[i - 1]) {
                    swap(nums, i, i - 1);
                }
            }
        }
    }

    void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
