package daohuei.leetcodelizard;

/*
 * 238. Product of Array Except Self
 * Link: https://leetcode.com/problems/product-of-array-except-self/submissions/
 */
public class ProductArrayExceptSelf {
    /**
     * @author: daohuei
     * @description: iterations
     * @time: O(n): lenght of nums
     * @space: O(1): not really using any
     */
    public int[] iterations(int[] nums) {
        int mul = 1;
        int zeroNums = 0;
        int zeroFirst = -1;
        for (int i = 0; i < nums.length; i++) {
            // if current is zero
            if (nums[i] == 0) {
                // count the zero number
                zeroNums++;
                if (zeroNums == 1) {
                    // memorize the first zero position
                    zeroFirst = i;
                }
                // skip the multiplication
                continue;
            }
            // multiply it
            mul *= nums[i];
        }

        int[] res = new int[nums.length];
        // if more than 1 zero
        if (zeroNums > 1) {
            // return all zero
            return res;
        } else if (zeroNums == 1) {
            // if only 1 zero
            // set the zero first position to the product
            res[zeroFirst] = mul;
            // directly return
            return res;
        }
        // if all not zero
        for (int i = 0; i < nums.length; i++) {
            // except current number by using division
            res[i] = divide(mul, nums[i]);
        }
        return res;
    }

    // #29 problem
    public int divide(int dividend, int divisor) {
        long ans = divide((long) dividend, (long) (divisor));
        long m = 2147483648L;
        if (ans == m) {
            return Integer.MAX_VALUE;
        } else {
            return (int) ans;
        }
    }

    public long divide(long dividend, long divisor) {
        long ans = 1;
        long sign = 1;
        if (dividend < 0) {
            sign = opposite(sign);
            dividend = opposite(dividend);
        }
        if (divisor < 0) {
            sign = opposite(sign);
            divisor = opposite(divisor);
        }
        long origin_dividend = dividend;
        long origin_divisor = divisor;

        if (dividend < divisor) {
            return 0;
        }

        dividend -= divisor;
        while (divisor <= dividend) {
            ans = ans + ans;
            divisor += divisor;
            dividend -= divisor;
        }
        long a = ans + divide(origin_dividend - divisor, origin_divisor);
        return sign > 0 ? a : opposite(a);
    }

    public long opposite(long x) {
        return ~x + 1;
    }

    /**
     * @author: daohuei
     * @description: dp
     * @time: O(n): lenght of nums
     * @space: O(1): not really using any
     */
    public int[] dp(int[] nums) {
        int n = nums.length;
        int res[] = new int[n];
        // start with 1
        res[0] = 1;
        // left accumulated product at first
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        // right accumulated product for temp
        int right = 1;
        for (int i = n - 2; i >= 0; i--) {
            // accumulated product
            right = right * nums[i + 1];
            // replace result with left accu * right accu
            res[i] = res[i] * right;
        }
        return res;
    }
}