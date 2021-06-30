package daohuei.leetcodelizard;

/*
 * 326. Power of Three
 * Link: https://leetcode.com/problems/power-of-three/
 */
public class PowerOfThree {
    /**
     * @author: daohuei
     * @description: divide 3
     * @time: O(n): until it reach 1 or not
     * @space: O(1): not using any
     */
    public boolean isPowerOfThree(int n) {
        while (n > 0 && n % 3 == 0) {
            // divide 3 until 1
            n /= 3;
        }
        // if it is 1, then it is the answer
        return n == 1;
    }
}