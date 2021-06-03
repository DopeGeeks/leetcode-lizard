package daohuei.leetcodelizard;

/*
 * 268. Missing Number
 * Link: https://leetcode.com/problems/missing-number/
 */
public class MissingValue {
    /**
     * @author: daohuei
     * @description: sequence sum method
     * @time: O(n): sum the current value
     * @space: O(1): not using any
     */
    public int sequenceSum(int[] nums) {
        int sum1 = 0;
        // get the sum of current value
        for (int n : nums) {
            sum1 += n;
        }
        // calculate the sum of arithmetic sequence
        int sum2 = (1 + nums.length) * nums.length / 2;

        // the missing value will be sequence sum minus current sum
        return sum2 - sum1;
    }

    /**
     * @author: daohuei
     * @description: xor method
     * @time: O(n): loop the nums
     * @space: O(1): not using any
     */
    public int xor(int[] nums) {
        int result = nums.length;
        for (int i = 0; i < nums.length; i++) {
            // using xor: same value will be zero
            result = result ^ nums[i] ^ i;
        }
        return result;
    }
}
