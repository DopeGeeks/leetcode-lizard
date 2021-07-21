package daohuei.leetcodelizard;

/*
 * 31. Next Permutation
 * Link: https://leetcode.com/problems/next-permutation/description/
 */
public class NextPermutation {
    /**
     * @author: daohuei
     * @description: swap and reverse
     * @time: O(n): worst case will be n
     * @space: O(1): not using any
     */
    // https://windliang.oss-cn-beijing.aliyuncs.com/31_Next_Permutation.gif
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        // iter from the end (skip the end)
        // find the first place that is not increasing
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        // if exceed the left bound
        // means we got the maximum
        // then reverse will be the minimum
        if (i < 0) {
            reverse(nums, 0);
            return;
        }
        // iter from the end
        // find the first value that larger than nums[i]
        int j = nums.length - 1;
        while (j >= 0 && nums[j] <= nums[i]) {
            j--;
        }
        // swap them
        swap(nums, i, j);
        // then reverse the rest of the items after i
        reverse(nums, i + 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}