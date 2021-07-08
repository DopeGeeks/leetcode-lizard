package daohuei.leetcodelizard;

import java.util.HashMap;
import java.util.HashSet;

/*
 * 128. Longest Consecutive Sequence
 * Link: https://leetcode.com/problems/longest-consecutive-sequence/
 */
public class LongestConsecutiveSequence {
    /**
     * @author: daohuei
     * @description: hashset
     * @time: O(n): length of nums
     * @space: O(n): hashset
     */
    public int hashSet(int[] nums) {
        // put all num in the hash set
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            // if contain previous num
            if (!set.contains(num - 1)) {
                // start the count
                int count = 0;
                // count all exist consecutive number
                while (set.contains(num)) {
                    count++;
                    num += 1;
                }
                // compare with previous answer
                max = Math.max(max, count);
            }
        }
        return max;
    }

    /**
     * @author: daohuei
     * @description: hashmap
     * @time: O(n): length of nums
     * @space: O(n): hashmap
     */
    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            // if already searched num before, skip
            if (map.containsKey(num)) {
                continue;
            }
            // get num - 1
            int left = map.getOrDefault(num - 1, 0);
            // get num + 1
            int right = map.getOrDefault(num + 1, 0);
            // get the max sum of left + right + 1
            int sum = left + 1 + right;
            max = Math.max(max, sum);

            // put clear current num's length count
            map.put(num, -1);
            // update left bound length
            map.put(num - left, sum);
            // update right bound length
            map.put(num + right, sum);
        }
        return max;
    }
}