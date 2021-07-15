package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 315. Count of Smaller Numbers After Self
 * Link: https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 */
public class CountSmallerNumberAfterSelf {
    /**
     * @author: daohuei
     * @description: Fenwick Tree method
     * @time: O(nlogn): for searching Fenwick tree and go through nums
     * @space: O(k): for hash map and Fenwick tree, k: # of unique elements
     */
    public List<Integer> countSmaller(int[] nums) {
        // sort the nums first
        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);
        // rank means the order of how large they are in all nums
        Map<Integer, Integer> ranks = new HashMap<>();
        int rank = 0;
        for (int i = 0; i < sorted.length; ++i)
            // if not the first index and current is not equal to the next
            if (i == 0 || sorted[i] != sorted[i - 1]) {
                // rank up and put into the hash map
                ranks.put(sorted[i], ++rank);
            }

        FenwickTree tree = new FenwickTree(ranks.size());
        List<Integer> ans = new ArrayList<Integer>();
        for (int i = nums.length - 1; i >= 0; --i) {
            // add the sum of count of nums that lower than current num
            // into the answer
            ans.add(tree.query(ranks.get(nums[i]) - 1));
            // add current rank into it
            tree.update(ranks.get(nums[i]), 1);
        }
        // reverse the ans since we was searching backward at the last loop
        Collections.reverse(ans);
        return ans;
    }

    // for getting Fenwick tree index
    private static int lowbit(int x) {
        return x & (-x);
    }

    // Fenwick Tree Data Structure
    class FenwickTree {
        private int[] sums;

        public FenwickTree(int n) {
            sums = new int[n + 1];
        }

        public void update(int i, int delta) {
            // update every place that may be influenced by i
            while (i < sums.length) {
                sums[i] += delta;
                i += lowbit(i);
            }
        }

        // query the sum with backward searching
        public int query(int i) {
            int sum = 0;
            while (i > 0) {
                sum += sums[i];
                i -= lowbit(i);
            }
            return sum;
        }
    }
}