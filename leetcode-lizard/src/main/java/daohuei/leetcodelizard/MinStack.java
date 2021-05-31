package daohuei.leetcodelizard;

import java.util.Stack;

/*
 * 155. Min Stack
 * Link: https://leetcode.com/problems/min-stack/
 */
public class MinStack {
    /**
     * @author: daohuei
     * @description: use another stack for min value
     */
    public class MinStackTwoStack {

        /** initialize your data structure here. */
        private Stack<Integer> stack;
        private Stack<Integer> minStack;

        public MinStackTwoStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            stack.push(val);
            if (!minStack.isEmpty()) {
                int top = minStack.peek();
                // if less or equal then push
                if (val <= top) {
                    minStack.push(val);
                }
            } else {
                // if empty then push
                minStack.push(val);
            }

        }

        public void pop() {
            int pop = stack.pop();

            int top = minStack.peek();
            // if that is the one we pop
            if (pop == top) {
                minStack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    /**
     * @author: daohuei
     * @description: use single stack and an integer variable for storing min value,
     *               push previous min value before any new min value
     */
    class MinStackSingleStack {
        int min = Integer.MAX_VALUE;
        Stack<Integer> stack = new Stack<Integer>();

        public void push(int val) {
            // if new value is smaller
            if (val <= min) {
                // store previous min after the current value
                stack.push(min);
                // update min
                min = val;
            }
            // store current value
            stack.push(val);
        }

        public void pop() {
            // if the one being poped is min, then update with next value
            if (stack.pop() == min) {
                min = stack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }
}