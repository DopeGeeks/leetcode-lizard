package daohuei.leetcodelizard;

/*
 * 371. Sum of Two Integers
 * Link: https://leetcode.com/problems/sum-of-two-integers
 */
public class SumOfTwoIntegers {
    /**
     * @author: daohuei
     * @description: bit operations
     * @time: O(1): no standard calculation
     * @space: O(1): not using any
     */
    public int getSum(int a, int b) {
        if (a == 0)
            return b;
        if (b == 0)
            return a;
        // iters until clear all the carrys
        while (b != 0) {
            int carry = a & b;
            a = a ^ b;
            // carry move 1 digit and become b to add with a
            b = carry << 1;
        }
        return a;
    }
}