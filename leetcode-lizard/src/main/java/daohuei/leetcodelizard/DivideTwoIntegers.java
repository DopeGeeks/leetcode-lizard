package daohuei.leetcodelizard;

/*
 * 29. Divide Two Integers
 * Link: https://leetcode.com/problems/divide-two-integers/
 */
public class DivideTwoIntegers {
    /**
     * @author: daohuei
     * @description: iterations
     * @time: O(log(n)): by double up the divisor, speed up twice every iteration
     * @space: O(1): not using any
     */
    public int divide(int dividend, int divisor) {
        int ans = -1;
        int sign = 1;
        // if both positive, then sign will be 1
        if (dividend > 0) {
            sign = opposite(sign);
            dividend = opposite(dividend);
        }
        if (divisor > 0) {
            sign = opposite(sign);
            divisor = opposite(divisor);
        }

        int origin_dividend = dividend;
        int origin_divisor = divisor;
        // no need to explain
        if (dividend > divisor) {
            return 0;
        }

        dividend -= divisor;
        while (divisor >= dividend) {
            // double the divisor for speed up
            // there may be missing some ans, but will make it up with recursion
            ans = ans + ans;
            divisor += divisor;
            dividend -= divisor;
        }
        // there still have some answer need to be count since we keep double up the
        // divisor for each loop
        int a = ans + opposite(divide(origin_dividend - divisor, origin_divisor));
        // if reach the min value
        if (a == Integer.MIN_VALUE) {
            if (sign > 0) {
                return Integer.MAX_VALUE;
            } else {
                return Integer.MIN_VALUE;
            }
        } else {
            if (sign > 0) {
                // should be positive
                return opposite(a);
            } else {
                // should be negative
                return a;
            }
        }
    }

    // return opposite sign in bit operation
    public int opposite(int x) {
        return ~x + 1;
    }
}