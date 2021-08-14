package daohuei.leetcodelizard;

/*
 * 201. Bitwise AND of Numbers Range
 * Link: https://leetcode.com/problems/bitwise-and-of-numbers-range/
 */
public class BitwiseANDofNumbersRange {
    /**
     * @author: daohuei
     * @description: recursion
     * @time: O(n): n = digit number: compare digits number and calculate the
     *        highest digit then recur
     * @space: O(n): same as above for recursion stack
     */
    public int recursionMethod(int left, int right) {
        if (left == 0 || left == right)
            return left;
        // get their bit digits number
        int logLeft = (int) (Math.log(left) / Math.log(2));
        int logRight = (int) (Math.log(right) / Math.log(2));
        // if not same, AND will be 0, since there will be a number btwn m and n like
        // 1000... so all AND must be 0
        if (logLeft != logRight)
            return 0;
        // if same, then we can make sure the highest bit both 1, we use 1<<logLeft to
        // calculate 2^logLeft
        // then calculate the rest
        else
            return (1 << logLeft) + rangeBitwiseAnd(left - (1 << logLeft), right - (1 << logLeft));
    }

    /**
     * @author: daohuei
     * @description: bit operations with while loop
     * @time: O(n): n = digit number: shift until the rest is same
     * @space: O(1): not using any
     */
    public int rangeBitwiseAnd(int left, int right) {
        if (left == 0) {
            return 0;
        }
        int moveFactor = 1;
        while (left != right) {
            // right shift 1 digit => divide 2
            left >>= 1;
            System.out.println(left);
            right >>= 1;
            System.out.println(right);
            // the factor multiply 2
            moveFactor <<= 1;
        }
        // after the loop, the rest must be the same
        // move factor indicates shifted bits
        // left indicates actual remains bits
        return left * moveFactor;
    }
}