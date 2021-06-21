package daohuei.leetcodelizard;

/*
 * 75. Sort Colors
 * Link: https://leetcode.com/problems/sort-colors/
 */
public class SortColors {
    /**
     * @author: daohuei
     * @description: iteration
     * @time: O(n) length of nums but 2 loops
     * @space: O(1): not using any
     */
    public void iterations(int[] nums) {
        int zero_count = 0;
        int one_count = 0;
        // simply count zeroes and ones
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zero_count++;
            }
            if (nums[i] == 1) {
                one_count++;
            }
        }
        // add it to the answer
        for (int i = 0; i < nums.length; i++) {
            if (zero_count > 0) {
                nums[i] = 0;
                zero_count--;
            } else if (one_count > 0) {
                nums[i] = 1;
                one_count--;
            } else {
                nums[i] = 2;
            }
        }
    }

    /**
     * @author: daohuei
     * @description: one pass iteration
     * @time: O(n) length of nums
     * @space: O(1): not using any
     */
    public void onePassIteration(int[] nums) {
        int n0 = -1, n1 = -1, n2 = -1;
        int n = nums.length;
        // one pass
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                // move n2,n1,n0 position forward and assign value
                n2++;
                nums[n2] = 2;
                n1++;
                nums[n1] = 1;
                n0++;
                nums[n0] = 0;
            } else if (nums[i] == 1) {
                // move n2, n1 forward and assign value
                n2++;
                nums[n2] = 2;
                n1++;
                nums[n1] = 1;
            } else if (nums[i] == 2) {
                // move n2 forward only and assign value
                n2++;
                nums[n2] = 2;
            }
        }
    }
}