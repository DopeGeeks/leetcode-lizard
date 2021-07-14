package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * 347. Top K Frequent Elements
 * Link: https://leetcode.com/problems/top-k-frequent-elements/
 */
public class TopKFrequentElements {
    /**
     * @author: daohuei
     * @description: priority queue method
     * @time: O(nlogk): for sorting in the priority queue
     * @space: O(n): for hash map and priority queue
     */
    public int[] priorityQueueMethod(int[] nums, int k) {
        // count the frequency in the hash map
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums)
            counts.put(num, counts.getOrDefault(num, 0) + 1);

        Comparator<int[]> compareOnCount = new Comparator<int[]>() {
            // the sorting of priority queue is using binary search, so it will cost
            // O(log k), which k = lists.length
            @Override
            public int compare(int[] x, int[] y) {
                // calculate the difference between current node value of each list
                // for the priority weight
                return x[0] - y[0];
            }
        };
        // a priority queue for pair array
        PriorityQueue<int[]> queue = new PriorityQueue<>(compareOnCount);
        // go through the key of counts (nums)
        for (Integer num : counts.keySet()) {
            // put all hashmap key value into it
            queue.offer(new int[] { counts.get(num), num });
            // if size is larger than k, remove the num that count is smallest from queue
            if (queue.size() > k)
                queue.poll();
        }

        // reform the answer
        int[] ans = new int[k];
        for (int i = 0; i < k; ++i)
            ans[i] = queue.poll()[1];
        return ans;
    }

    /**
     * @author: daohuei
     * @description: bucket list method
     * @time: O(n): though the reform loop is nested, but it still totally only has
     *        k iterations only
     * @space: O(n): for hash map and bucket list
     */
    public int[] bucketListMethod(int[] nums, int k) {
        // use buckets with count as index
        List<Integer>[] buckets = new List[nums.length + 1];
        Map<Integer, Integer> counts = new HashMap<>();

        for (int num : nums)
            counts.put(num, counts.getOrDefault(num, 0) + 1);

        for (int num : counts.keySet()) {
            int count = counts.get(num);
            if (buckets[count] == null)
                buckets[count] = new ArrayList<Integer>();
            // add the num to the corresponding count position
            // it will form a set for each count
            buckets[count].add(num);
        }

        // reform the answer
        int[] ans = new int[k];
        int ansIndex = 0;
        // go through buckets backward
        for (int i = buckets.length - 1; i > 0 && ansIndex < k; --i) {
            if (buckets[i] != null) {
                // pull out all the integer in each bucket list
                for (int j = 0; j < buckets[i].size(); j++) {
                    ans[ansIndex] = (int) (buckets[i].get(j));
                    ansIndex++;
                }
            }
        }
        return ans;
    }
}