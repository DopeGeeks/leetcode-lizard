package daohuei.leetcodelizard;

/*
 * 342. Power of Four
 * Link: https://leetcode.com/problems/power-of-four/description/
 */
public class PowerOfFour {
    /**
     * @author: daohuei
     * @description: brutal force method
     * @time: O(k): k = log_4^n
     * @space: O(1): not using any
     */
    public boolean loopMethod(int n) {
        if (n % 4 != 0)
            return n == 1;
        while (n > 4) {
            n /= 4;
            // if after division, become non power of 4
            if (n % 4 != 0)
                return false;
        }
        return n == 4;
    }

    /**
     * @author: daohuei
     * @description: non-loop method
     * @time: O(1): nope
     * @space: O(1): not using any
     */
    public boolean isPowerOfFour(int n) {
        if (n <= 0)
            return false;
        // for checking if it is power of 2
        int bit = n & (n - 1);
        if (bit > 0)
            return false;
        // if it is power of 2 and mod 3 equals 1
        // then it is power of 4
        return n % 3 == 1;
    }
}