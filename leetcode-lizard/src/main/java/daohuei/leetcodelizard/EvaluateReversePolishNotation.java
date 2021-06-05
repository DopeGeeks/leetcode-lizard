package daohuei.leetcodelizard;

import java.util.Stack;

/*
 * 150. Evaluate Reverse Polish Notation
 * Link: https://leetcode.com/problems/evaluate-reverse-polish-notation/
 */
public class EvaluateReversePolishNotation {
    /**
     * @author: daohuei
     * @description: use stack method
     * @time: O(n): simply go through all the tokens
     * @space: O(n): stack store all the number string
     */
    public int stackMethod(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String t : tokens) {
            // if it is an operation
            if (isOperation(t)) {
                // pop out 2 number
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                // later one got more priority
                // eval and get the answer
                stack.push(eval(operand1, operand2, t.charAt(0)));
            } else {
                // if it is an number, just push into the stack
                stack.push(Integer.parseInt(t));
            }
        }
        // the last one should be the answer
        return stack.pop();
    }

    // for parsing operation
    private int eval(int a, int b, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
        }
        return 0;
    }

    // check if it is an operation symbol
    private boolean isOperation(String t) {
        return t.equals("+") || t.equals("-") || t.equals("*") || t.equals("/");
    }
}