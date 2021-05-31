package daohuei.leetcodelizard;

/*
 * 66. Plus One
 * Link: https://leetcode.com/problems/plus-one/
 */
public class PlusOne {
    /**
     * @author: daohuei
     * @description: recursion
     * @time: O(n): n = length of digits
     * @space: O(1): not using any
     */
    public int[] recursion(int[] digits) {
        return plusOneAtIndex(digits, digits.length - 1);
    }

    private int[] plusOneAtIndex(int[] digits, int index) {
        // means 9 for every digits
        if (index < 0) {
            // digit will exceed the original array
            // need to create new one
            int[] ans = new int[digits.length + 1];
            // set largest digit as one for carry
            ans[0] = 1;
            // others are default 0
            return ans;
        }
        // if 0~8 then simply add 1 and return
        if (digits[index] < 9) {
            digits[index] += 1;
            return digits;
        }

        // need to carry
        // means this digit is 9
        // we set it to 0 and add one for next digit
        digits[index] = 0;
        return plusOneAtIndex(digits, index - 1);

    }

    /**
     * @author: iteration
     * @description: recursion
     * @time: O(n): n = length of digits
     * @space: O(1): not using any
     */
    public int[] iteration(int[] digits) {
        // go through the digits from the lowest one
        for (int i = digits.length - 1; i >= 0; i--) {
            // if less than zero
            if (digits[i] < 9) {
                // add one and break
                digits[i] += 1;
                break;
            }
            // otherwise, set to zero and move ot next iter
            digits[i] = 0;
        }
        // if the highest digit is 0
        if (digits[0] == 0) {
            // make new digit as 1 and others 0
            int[] ans = new int[digits.length + 1];
            ans[0] = 1;
            digits = ans;
        }
        return digits;
    }
}