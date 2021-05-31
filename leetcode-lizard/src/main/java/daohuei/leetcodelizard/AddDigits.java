package daohuei.leetcodelizard;

/*
 * 258. Add Digits
 * Link: https://leetcode.com/problems/add-digits/
 */
public class AddDigits {
    /**
     * @author: daohuei
     * @description: recursion
     * @time: O(n*k): n: digits amount, k: how many layers to reach single digit
     * @space: O(1): not using any
     */
    public int recursion(int num) {
        if (num < 10) {
            return num;
        }
        int next = 0;
        // add all digits together
        while (num != 0) {
            next = next + num % 10;
            num /= 10;
        }
        // keep going
        return recursion(next);
    }
}