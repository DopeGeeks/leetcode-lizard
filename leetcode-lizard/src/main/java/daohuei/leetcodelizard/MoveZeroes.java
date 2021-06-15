package daohuei.leetcodelizard;

/*
 * 283. Move Zeroes
 * Link: https://leetcode.com/problems/move-zeroes/
 */
public class MoveZeroes {
    /**
     * @author: daohuei
     * @description: 2 pointers
     * @time: O(n): iters through nums
     * @space: O(1): not using any
     */
    public void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            // j is the place to insert
            // i looking for non-zero value
            if (nums[i] != 0) {
                // i & j exchange
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                // j move forward
                j++;
            }
        }
    }
}