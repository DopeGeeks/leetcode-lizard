package daohuei.leetcodelizard;

import java.util.Stack;

/*
 * 227. Basic Calculator II
 * Link: https://leetcode.com/problems/basic-calculator-ii/
 */
public class BasicCalculatorTwo {
    /**
     * @author: daohuei
     * @description: 2 stack method
     * @time: O(n): go through every char
     * @space: O(n): 2 stack totally need at most n for storing all char in string s
     */
    public int twoStack(String s) {
        char[] array = s.toCharArray();
        int n = array.length;
        Stack<Integer> num = new Stack<>();
        Stack<Character> op = new Stack<>();
        int temp = -1;
        for (int i = 0; i < n; i++) {
            if (array[i] == ' ') {
                continue;
            }
            // add number
            if (isNumber(array[i])) {
                if (temp == -1) {
                    temp = array[i] - '0';
                } else {
                    temp = temp * 10 + array[i] - '0';
                }
            } else {
                // if temp not empty, push it to the num stack and reset the temp
                if (temp != -1) {
                    num.push(temp);
                    temp = -1;
                }
                // if there is some empty
                while (!op.isEmpty()) {
                    // if current op prior than previous one
                    // we will move forward
                    if (compare(array[i], op.peek())) {
                        break;
                    }
                    // if previous op need to be done first
                    // get the next 2 num
                    int num1 = num.pop();
                    int num2 = num.pop();
                    // calculate
                    num.push(eval(num1, num2, op.pop()));
                }
                // push the op
                op.push(array[i]);

            }
        }
        // if there is a num left
        if (temp != -1) {
            num.push(temp);
        }
        // finish the rest of op
        while (!op.isEmpty()) {
            int num1 = num.pop();
            int num2 = num.pop();
            num.push(eval(num1, num2, op.pop()));
        }
        // the last number is the answer
        return num.pop();
    }

    // true is op1 is prior than op2
    private boolean compare(char op1, char op2) {
        if (op1 == '*' || op1 == '/') {
            return op2 == '+' || op2 == '-';
        }
        return false;
    }

    private boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    private int eval(int a, int b, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return b - a;
            case '*':
                return a * b;
            case '/':
                return b / a;
        }
        return 0;
    }

    /**
     * @author: daohuei
     * @description: sign method
     * @time: O(n): go through every char
     * @space: O(n): for the stack
     */
    public int signMethod(String s) {
        int len;
        // empty case
        if (s == null || (len = s.length()) == 0)
            return 0;
        Stack<Integer> stack = new Stack<Integer>();
        int num = 0;
        char sign = '+';
        for (int i = 0; i < len; i++) {
            // calculate num if digit
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            // if it is op and not space
            // or it is the last char
            if ((!Character.isDigit(s.charAt(i)) && ' ' != s.charAt(i)) || i == len - 1) {
                // get the previous sign
                // push the num with the following
                if (sign == '-') {
                    stack.push(-num);
                }
                if (sign == '+') {
                    stack.push(num);
                }
                // * and / directly calculate with previous num and return the answer
                if (sign == '*') {
                    stack.push(stack.pop() * num);
                }
                if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                // update the sign
                sign = s.charAt(i);
                // reset the num
                num = 0;
            }
        }

        // sum up every element in the stack
        int re = 0;
        for (int i : stack) {
            re += i;
        }
        return re;
    }
}