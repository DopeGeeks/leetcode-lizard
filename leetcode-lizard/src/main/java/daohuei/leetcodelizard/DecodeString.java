package daohuei.leetcodelizard;

import java.util.Stack;

/*
 * 394. Decode String
 * Link: https://leetcode.com/problems/decode-string/
 */
public class DecodeString {
    /**
     * @author: daohuei
     * @description: stack method
     * @time: O(n): go thru every char in the string
     * @space: O(n): for the stack: worst case every char
     */
    public String stackMethod(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        // a stack for object
        Stack<Object> stack = new Stack<>();
        int number = 0;
        // for every character
        for (char c : s.toCharArray()) {
            // number
            if (Character.isDigit(c)) {
                // parse the number
                number = number * 10 + (c - '0');
            }
            // start bracket
            else if (c == '[') {
                // push the parsed number into the stack
                stack.push(Integer.valueOf(number));
                // reset the number
                number = 0;
            }
            // end bracket
            else if (c == ']') {
                // pop out the string in the brackets
                String str = popStack(stack);
                // get the duplicate times
                int num = (Integer) stack.pop();
                // push the str back to the stack with num times
                for (int i = 0; i < num; i++)
                    stack.push(str);
            }
            // other char
            else {
                // simply push the char into the stack as a string
                stack.push(String.valueOf(c));
            }
        }
        // pop out the last object in the stack
        // should be the answer
        return popStack(stack);
    }

    // for poping out the string with combination
    private String popStack(Stack<Object> stack) {
        StringBuffer sb = new StringBuffer();
        // if the stack is not empty and it is string type
        while (!stack.isEmpty() && (stack.peek() instanceof String)) {
            // put into the string buffer
            sb.insert(0, stack.pop());
        }
        return sb.toString();
    }

    /**
     * @author: daohuei
     * @description: dfs method
     * @time: O(nk): go through every substring, k: nest depth
     * @space: O(k): for the recursion stack
     */
    public String decodeString(String s) {
        return expressionExpand(s);
    }

    public String expressionExpand(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        String substring = "";
        int number = 0;
        // parentheses tracker: track how many pending parentheses
        int paren = 0;

        // go thru every char
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // start bracket
            if (c == '[') {
                // if paren == 0, it indicates the most outside 1st '[', no need to keep it
                if (paren > 0) {
                    // inner bracket need to be kept for parsing inner string
                    substring += c;
                }
                // track the parentheses
                paren++;
            }
            // end bracket
            else if (c == ']') {
                // release tracking by 1
                paren--;
                // if all paren solved: means there should be a new start
                if (paren == 0) {
                    // so we should close the current process
                    // get the inner result first
                    String innerString = expressionExpand(substring);
                    // build the string with the current duplicate times
                    for (int num = 0; num < number; num++) {
                        sb.append(innerString);
                    }
                    // reset the number and inner substring
                    number = 0;
                    substring = "";
                }
                // inner bracket need to be kept for parsing inner string
                else {
                    substring += c;
                }
            }
            // number
            else if (Character.isDigit(c)) {
                // only get the most outside number
                if (paren == 0) {
                    number = number * 10 + (c - '0');
                } else {
                    substring += c;
                }
            }
            // other char
            else {
                // directly put the outside char in the result
                if (paren == 0) {
                    sb.append(String.valueOf(c));
                } else {
                    substring += c;
                }
            }
        }
        return sb.toString();
    }
}