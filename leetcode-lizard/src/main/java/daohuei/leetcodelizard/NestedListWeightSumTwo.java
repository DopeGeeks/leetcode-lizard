package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import daohuei.leetcodelizard.FlattenNestedListIterator.NestedIterator.NestedInteger;
/*
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
Each element is either an integer, or a list -- whose elements may also be integers or other lists.
Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.
Example 1:
Input: [[1,1],2,[1,1]]
Output: 8 
Explanation: Four 1's at depth 1, one 2 at depth 2.
Example 2:
Input: [1,[4,[6]]]
Output: 17 
Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17.
*/

/*
 * 364. Nested List Weight Sum II
 * Link: https://leetcode.com/problems/nested-list-weight-sum-ii/description/
 */
public class NestedListWeightSumTwo {
    /**
     * @author: daohuei
     * @description: dfs
     * @time: O(n): go thru every integer
     * @space: O(h): for the depth of the nested integer as recursion stack
     */
    class DFSSolution {
        int overallSum = 0;

        public int depthSumInverse(List<NestedInteger> nestedList) {
            dfs(nestedList);
            return overallSum;
        }

        public int dfs(List<NestedInteger> nestedList) {
            List<NestedInteger> list = new ArrayList<>();
            int sum = 0;
            // go thru every nested integer
            for (NestedInteger nestedInt : nestedList) {
                // if is number: add to the current sum
                if (nestedInt.isInteger())
                    sum += nestedInt.getInteger();
                // if not: add to the temp list
                else
                    list.addAll(nestedInt.getList());
            }
            // if the list is empty, this is the base case
            if (list.isEmpty()) {
                // add the current sum times 1(base level) to the overall sum
                overallSum += sum;
                // return 1 since this is the base level weight
                return 1;
            }

            // get the depth with the dfs
            int depth = dfs(list) + 1;
            // calculate the overall sum with the weight
            overallSum += sum * depth;
            // still, return the depth
            return depth;
        }
    }

    /**
     * @author: daohuei
     * @description: bfs
     * @time: O(n): go thru every integer
     * @space: O(h): for the depth of the nested integer as stack
     */
    // we use stack(LIFO) instead of queue(FIFO) for BFS to deal with bottom-up
    // level weight
    class BFSSolution {
        public int depthSumInverse(List<NestedInteger> nestedList) {
            Stack<List<NestedInteger>> stack = new Stack<>();
            stack.push(nestedList);
            while (true) {
                List<NestedInteger> list = stack.peek();
                List<NestedInteger> newList = new ArrayList<>();
                // get the list
                for (NestedInteger item : list) {
                    // put all possible list into the new list
                    if (!item.isInteger())
                        newList.addAll(item.getList());
                }
                // if the new list is empty, break this loop
                if (newList.isEmpty())
                    break;
                // put the new list into the stack
                stack.push(newList);
            }

            // process
            int sum = 0, level = 1;
            while (!stack.isEmpty()) {
                // go thru every list in the stack
                List<NestedInteger> list = stack.pop();
                for (NestedInteger item : list) {
                    // sum up with the weight
                    if (item.isInteger())
                        sum += level * item.getInteger();
                }
                // increase the weight
                level++;
            }
            return sum;
        }
    }
}
