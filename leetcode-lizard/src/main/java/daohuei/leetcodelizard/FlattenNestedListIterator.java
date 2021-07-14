package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class FlattenNestedListIterator {

    /*
     * 341. Flatten Nested List Iterator Link:
     * https://leetcode.com/problems/flatten-nested-list-iterator/
     */
    public class NestedIterator implements Iterator<Integer> {
        /**
         * @author: daohuei
         * @description: stack method
         * @time: O(n): for every integers once only
         * @space: O(n): for stack
         */
        public class NestedInteger {
            public NestedInteger() {
            }

            /**
             * @return true if this NestedInteger holds a single integer, rather than a
             *         nested list.
             **/
            public boolean isInteger() {
                return true;
            };

            /**
             * @return the single integer that this NestedInteger holds, if it holds a
             *         single integer
             */
            public Integer getInteger() {
                return 0;
            };

            /**
             * @return the nested list that this NestedInteger holds, if it holds a nested
             *         list
             */
            public List<NestedInteger> getList() {
                return new ArrayList<NestedInteger>();
            };
        }

        private Stack<NestedInteger> stack;

        public NestedIterator(List<NestedInteger> nestedList) {
            // flatten the nested list into the stack
            this.stack = new Stack<>();
            flatten(nestedList, stack);
        }

        @Override
        public Integer next() {
            // if it has next, just pop it out and get integer
            return hasNext() ? stack.pop().getInteger() : null;
        }

        @Override
        public boolean hasNext() {
            // check elements in the slack
            while (!stack.isEmpty()) {
                // if top element is integer
                if (stack.peek().isInteger()) {
                    // it has next
                    return true;
                }
                // if it is a list, pop it out and get the list
                // redo the flatten into the stack
                flatten(stack.pop().getList(), stack);
            }
            // if out of elements, means no next anymore
            return false;
        }

        void flatten(List<NestedInteger> nestedList, Stack<NestedInteger> stack) {
            if (null == nestedList)
                return;
            // just simply push all elements into the stack
            for (int i = nestedList.size() - 1; i >= 0; --i) {
                stack.push(nestedList.get(i));
            }
        }
    }

}