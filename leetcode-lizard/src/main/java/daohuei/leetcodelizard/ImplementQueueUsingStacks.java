package daohuei.leetcodelizard;

import java.util.Stack;

/*
 * 232. Implement Queue using Stacks
 * Link: https://leetcode.com/problems/implement-queue-using-stacks/
 */
public class ImplementQueueUsingStacks {
    /**
     * @author: daohuei
     * @description: modify stack orders during push only
     */
    class MyQueue {

        Stack<Integer> stack;

        /** Initialize your data structure here. */
        public MyQueue() {
            stack = new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            Stack<Integer> temp = new Stack<>();
            int size = stack.size();
            // reverse stack order into temp
            while (size > 0) {
                temp.push(stack.pop());
                size--;
            }
            // put x as first element in stack
            stack.push(x);
            size = temp.size();
            // put everything in temp back
            while (size > 0) {
                stack.push(temp.pop());
                size--;
            }
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            return stack.pop();
        }

        /** Get the front element. */
        public int peek() {
            return stack.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stack.isEmpty();
        }
    }
}
