package daohuei.leetcodelizard;

import java.util.Stack;

/*
 * 385. Mini Parser 
 * Link: https://leetcode.com/problems/mini-parser/description/
 */

public class MiniParser {
    /**
     * @author: daohuei
     * @description: stack method
     * @time: O(n): go through the string once
     * @space: O(k): for the stack, k => the depth of the nest
     */
    public NestedInteger deserialize(String s) {
        // empty case
        if (s.isEmpty())
            return null;
        // if not start at [], then only numbers in the string
        if (s.charAt(0) != '[')
            return new NestedInteger(Integer.valueOf(s));
        // the stack for nested integer
        Stack<NestedInteger> stack = new Stack<>();
        // current nested integer
        NestedInteger curr = null;
        // as the string pointer from the left to the right
        int l = 0;
        // go through the string
        for (int r = 0; r < s.length(); r++) {
            // get the char
            char ch = s.charAt(r);
            // if start bracket
            if (ch == '[') {
                // if current is not null(already a nested integer generated)
                if (curr != null) {
                    // push it to the stack
                    stack.push(curr);
                }
                // now we need to focus on a new nested integer
                curr = new NestedInteger();
                // update the left point to the bracket's next
                l = r + 1;
            }
            // end bracket
            else if (ch == ']') {
                // get the last number before the end bracket
                String num = s.substring(l, r);
                // if the number exist
                if (!num.isEmpty()) {
                    // add it to the current list
                    curr.add(new NestedInteger(Integer.valueOf(num)));
                }
                // and if there is nested integer waiting(outside)
                if (!stack.isEmpty()) {
                    // pop it out
                    NestedInteger pop = stack.pop();
                    // and add the current nested integer to it
                    pop.add(curr);
                    // make that one as current
                    curr = pop;
                }
                // update the left pointer
                l = r + 1;
            }
            // cama
            else if (ch == ',') {
                // if the previous is not the end bracket
                if (s.charAt(r - 1) != ']') {
                    // get the number before the cama
                    String num = s.substring(l, r);
                    // add it to the current
                    curr.add(new NestedInteger(Integer.valueOf(num)));
                }
                l = r + 1;
            }
        }
        // the current should be our perfect nested integer
        return curr;
    }

    public class NestedInteger {

        public NestedInteger() {
        }

        public NestedInteger(Integer val) {
        }

        public void add(NestedInteger i) {
        }
    }
}