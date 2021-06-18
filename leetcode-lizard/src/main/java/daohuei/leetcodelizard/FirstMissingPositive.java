package daohuei.leetcodelizard;

/*
 * 41. First Missing Positive
 * Link: https://leetcode.com/problems/first-missing-positive/
 */
public class FirstMissingPositive {
    /**
     * @author: daohuei
     * @description: swap
     * @time: O(n): n = nums length
     * @space: O(1): not using any
     */
    public int swapMethod(int[] nums) {
        int n = nums.length;
        // traverse every number
        for (int i = 0; i < n; i++) {
            // all requirements:
            // 1. positive
            // 2. less tha length
            // 3. that number is not at the same position as its value (i.e. 1 is not at
            // position 0)
            while (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                // swap it with its matching position (index = value - 1)
                swap(nums, i, nums[i] - 1);
            }
        }
        // traverse again
        for (int i = 0; i < n; i++) {
            // if not at the right position
            if (nums[i] != i + 1) {
                // then it is the missing positive
                return i + 1;
            }
        }
        // if all matched, then answer will be n+1
        return n + 1;
    }

    // swap position of i and j
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}