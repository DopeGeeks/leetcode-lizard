package daohuei.leetcodelizard;

import java.util.Stack;

/*
 * 224. Basic Calculator
 * Link: https://leetcode.com/problems/basic-calculator/description/
 */
public class BasicCalculater {
    /**
     * @author: daohuei
     * @description: stack
     * @time: O(n): walk through string length
     * @space: O(n): stack for string length
     */

    public int calculate(String s) {
        int len = s.length(), sign = 1, result = 0;
        Stack<Integer> stack = new Stack<Integer>();
        // go through the string
        for (int i = 0; i < len; i++) {
            // if it is number
            if (Character.isDigit(s.charAt(i))) {
                // get the first digit
                int sum = s.charAt(i) - '0';
                // if still in the range and next char is still number
                while (i + 1 < len && Character.isDigit(s.charAt(i + 1))) {
                    // sum it up and move forward
                    sum = sum * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                // add with the sign
                result += sum * sign;
            } else if (s.charAt(i) == '+')
                sign = 1; // positive sign
            else if (s.charAt(i) == '-')
                sign = -1; // negative sign
            else if (s.charAt(i) == '(') {
                // if left bracket
                // push the result and sign into the stack
                stack.push(result);
                stack.push(sign);
                // reset the result and sign
                result = 0;
                sign = 1;
            } else if (s.charAt(i) == ')') {
                // if right bracket
                // first pop will be the operator (1 as + or -1 as -)
                // second pop will be the next number
                result = result * stack.pop() + stack.pop();
            }

        }
        return result;
    }
}