package daohuei.leetcodelizard;

import java.util.Stack;

/*
 * 20. Valid Parentheses
 * Link: https://leetcode.com/problems/valid-parentheses/
 */
public class ValidParentheses {
    /**
     * @author: daohuei
     * @description: stack method
     * @time: O(n): go through the string with n char
     * @space: O(n): stack at most store n/2 brackets
     */
    public boolean isValid(String s) {
        // stack for storing brackets
        Stack<Character> brackets = new Stack<Character>();
        // go through the string
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '(':
                case '[':
                case '{':
                    // if the left bracket, just push any way
                    brackets.push(c);
                    break;
                case ')':
                    if (!brackets.empty()) {
                        // if the last bracket is the matched type
                        // we remove it for offset
                        if (brackets.peek() == '(') {
                            brackets.pop();
                        } else {
                            // not matched!
                            return false;
                        }
                    } else {
                        // if stack already empty, then it must be false.
                        // a redundant right bracket
                        return false;
                    }
                    break;
                case ']':
                    if (!brackets.empty()) {
                        if (brackets.peek() == '[') {
                            brackets.pop();
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                    break;
                case '}':
                    if (!brackets.empty()) {
                        if (brackets.peek() == '{') {
                            brackets.pop();
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }

            }
        }
        // if empty, then it is perfectly match
        return brackets.empty();
    }
}