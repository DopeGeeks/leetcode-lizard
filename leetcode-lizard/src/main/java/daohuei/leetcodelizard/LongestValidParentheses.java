package daohuei.leetcodelizard;

import java.util.Stack;

/*
 * 32. Longest Valid Parentheses
 * Link: https://leetcode.com/problems/longest-valid-parentheses
 */
public class LongestValidParentheses {
    /**
     * @author: daohuei
     * @description: burtal force method
     * @time: O(n^2): 2 2-layer nested loop for string
     * @space: O(1): not using any
     */
    public int brutalForce(String s) {
        int count = 0;
        int max = 0;
        // go through string s with 2 layer nested loop
        for (int i = 0; i < s.length(); i++) {
            count = 0;
            for (int j = i; j < s.length(); j++) {
                // left: count it
                if (s.charAt(j) == '(') {
                    count++;
                } else {
                    // right: remove count
                    count--;
                }
                // count < 0: redundant right, no matter what is in the rest, they all illegal
                if (count < 0) {
                    break;

                }
                // we got legal sequence
                if (count == 0) {
                    // put its length if it is larger
                    if (j - i + 1 > max) {
                        max = j - i + 1;
                    }
                }
            }
        }
        return max;
    }

    /**
     * @author: daohuei
     * @description: dp
     * @time: O(n): just go through the string once
     * @space: O(n): for dp array
     */
    public int dp(String s) {
        int maxans = 0;
        int dp[] = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            // when meeting the right
            if (s.charAt(i) == ')') {
                // if previous one is left
                if (s.charAt(i - 1) == '(') {
                    // update current dp value with 2 before index and sum with current 2 count
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                    // if previous not left
                    // must ensure that there is some redundant element before
                    // and such element is '('
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    // update with previous one and the redundant element's 2 before index and
                    // current 2 count(current and redundant one)
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                // get the math
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }

    /**
     * @author: daohuei
     * @description: stack
     * @time: O(n): just go through the string once
     * @space: O(n): for stack
     */
    public int stackMethod(String s) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            // if left, push index in it
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                // if right, pop out
                stack.pop();
                // if empty, means invalid, there is a redundant right
                if (stack.empty()) {
                    // push current into the stack
                    // for checking invalid purpose and as last invalid index
                    stack.push(i);
                } else {
                    // if valid
                    // calculate the length
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

    /**
     * @author: daohuei
     * @description: scanning method
     * @time: O(n): just go through the string twice
     * @space: O(1): not using any
     */
    public int longestValidParentheses(String s) {
        // for left and right count
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            // if they match, calculate the max length
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right >= left) {
                // if right over left: means invalid
                // reset them
                left = right = 0;
            }
        }
        // do it all backward again
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left >= right) {
                left = right = 0;
            }
        }
        return maxlength;
    }
}