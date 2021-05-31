package daohuei.leetcodelizard;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 225. Implement Stack using Queues
 * Link: https://leetcode.com/problems/implement-stack-using-queues/
 */
public class ImplementStackUsingQueues {

    /**
     * @author: daohuei
     * @description: modify queue orders during push only
     */
    class MyStack {

        Queue<Integer> queue;

        /** Initialize your data structure here. */
        public MyStack() {
            queue = new LinkedList<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            // offer x into queue first
            queue.offer(x);
            int size = queue.size();
            while (size > 1) {
                // poll out all the element and re-offer them to the queue
                queue.offer(queue.poll());
                size--;
            }
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return queue.poll();
        }

        /** Get the top element. */
        public int top() {
            return queue.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue.isEmpty();
        }
    }
}
