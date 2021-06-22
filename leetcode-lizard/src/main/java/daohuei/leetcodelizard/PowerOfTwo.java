package daohuei.leetcodelizard;

/*
 * 231. Power of Two
 * Link: https://leetcode.com/problems/power-of-two/description/
 */
public class PowerOfTwo {
    /**
     * @author: daohuei
     * @description: brutal force
     * @time: O(logn) it is finding the power, so clear logn for any true case, but
     *        worst case will be O(logMIN_VALUE)
     * @space: O(1): not using any
     */
    public boolean brutal(int n) {
        int power = 1;
        while (power <= n) {
            // if matched
            if (power == n) {
                return true;
            }
            // left shift:
            // power -> 1 2 4 8 16...
            power = power << 1;
            // in the end it will become a signed bits with negative sign
            // and also the smallest integer
            if (power == Integer.MIN_VALUE) {
                break;
            }
        }
        return false;
    }

    /**
     * @author: daohuei
     * @description: simple brutal force
     * @time: O(logn): same as above
     * @space: O(1): not using any
     */
    public boolean simpleBrutalForce(int n) {
        if (n == 0)
            return false;
        // divide 2 until it can not be fully divided by 2
        while (n % 2 == 0) {
            n /= 2;
        }
        // if power of 2, its last result should 1
        return n == 1;
    }

    /**
     * @author: daohuei
     * @description: n & n-1
     * @time: O(1): bit operation once only
     * @space: O(1): not using any
     */
    public boolean andMethod(int n) {
        if (n <= 0) {
            return false;
        }
        // since power of 2 only got 1 bit left most
        // so n & (n-1) must be 0 unless it is negative
        return (n & (n - 1)) == 0;
    }
}