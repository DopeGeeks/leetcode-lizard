package daohuei.leetcodelizard;

import java.util.PriorityQueue;

/*
 * 1167. Minimum Cost to Connect Sticks
 * Link: https://leetcode.com/problems/minimum-cost-to-connect-sticks/
 */
public class MinCostConnectSticks {

    /**
     * @author: daohuei
     * @description: priority queue method
     * @time: O(nlogn): for sorting in the priority queue in every add
     * @space: O(n): for priority queue
     */
    public int connectSticks(int[] sticks) {
        if (sticks == null || sticks.length < 2) {
            return 0;
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // sorted with priority queue
        for (int num : sticks) {
            minHeap.offer(num);
        }

        int res = 0;
        while (minHeap.size() > 1) {
            // add smallest 2 numbers together
            int merge = minHeap.poll() + minHeap.poll();
            // you will get the min cost at this state
            res += merge;
            // put back the connected stick and sort it (log(n))
            minHeap.offer(merge);
        }

        return res;
    }
}
