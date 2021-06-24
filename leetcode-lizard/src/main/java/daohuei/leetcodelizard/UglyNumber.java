package daohuei.leetcodelizard;

/*
 * 263. Ugly Number
 * Link: https://leetcode.com/problems/ugly-number/description/
 */
public class UglyNumber {
    /**
     * @author: daohuei
     * @description: all about math
     * @time: O(n): n will be the max decomposition time of the number
     * @space: O(1): not using any
     */
    public boolean isUgly(int n) {
        if (n <= 0)
            return false;
        while (n % 5 == 0)
            n /= 5;
        while (n % 3 == 0)
            n /= 3;
        while (n % 2 == 0)
            n /= 2;
        return n == 1;
    }
}