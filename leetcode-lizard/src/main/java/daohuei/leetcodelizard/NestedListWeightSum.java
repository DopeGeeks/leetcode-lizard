package daohuei.leetcodelizard;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import daohuei.leetcodelizard.FlattenNestedListIterator.NestedIterator.NestedInteger;

/*
 * 339. Nested List Weight Sum
 * Link: https://leetcode.com/problems/nested-list-weight-sum/description/
 */
/*
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
Each element is either an integer, or a list -- whose elements may also be integers or other lists.
Example 1:
Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)
Example 2:
Given the list [1,[4,[6]]], return 27. 
(one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)
*/

public class NestedListWeightSum {

    /**
     * @author: daohuei
     * @description: dfs
     * @time: O(n): go through every integer
     * @space: O(depth): at most the depth for recursion stack
     */
    public int depthSumDFS(List<NestedInteger> nestedList) {
        int sum = 0;
        // for every child integer in the nested list
        for (NestedInteger childInt : nestedList) {
            // sum them with dfs
            sum += dfs(childInt, 1);
        }
        return sum;
    }

    public int dfs(NestedInteger nestInt, int depth) {
        // if it is integer, times with depth
        if (nestInt.isInteger())
            return nestInt.getInteger() * depth;
        // if nested, go through and recur with depth + 1
        int sum = 0;
        for (NestedInteger childInt : nestInt.getList()) {
            sum += dfs(childInt, depth + 1);
        }
        return sum;
    }

    /**
     * @author: daohuei
     * @description: bfs method
     * @time: O(n): go through every integer
     * @space: O(w): the largest size of a level
     */
    public int depthSumBFS(List<NestedInteger> nestedList) {
        // for bfs queue and add the root list to the queue
        Queue<NestedInteger> queue = new LinkedList<>();
        addToQueue(queue, nestedList);

        int level = 1, sum = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // go through every child integer or nested list maybe
            for (int i = 0; i < size; i++) {
                NestedInteger childInt = queue.poll();
                // if it is integer, then calculate the sum with current level
                if (childInt.isInteger())
                    sum += childInt.getInteger() * level;
                // if it is the list, add the list to the queue
                else
                    addToQueue(queue, childInt.getList());
            }
            // move to the next layer
            level++;
        }
        return sum;
    }

    private void addToQueue(Queue<NestedInteger> queue, List<NestedInteger> nestedList) {
        for (NestedInteger childInt : nestedList) {
            queue.offer(childInt);
        }
    }
}
