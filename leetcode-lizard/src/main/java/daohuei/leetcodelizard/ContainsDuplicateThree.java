package daohuei.leetcodelizard;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/*
 * 220. Contains Duplicate III
 * Link: https://leetcode.com/problems/contains-duplicate-iii/description/
 */
public class ContainsDuplicateThree {
    /**
     * @author: daohuei
     * @description: treeset method
     * @time: O(nlogn): search through BST in every iter
     * @space: O(k): the treeset only store k number
     */
    public boolean treeSetMethod(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k <= 0 || t < 0) {
            return false;
        }
        TreeSet<Long> treeSet = new TreeSet<>();
        // go through numbers
        for (int i = 0; i < nums.length; i++) {
            // get the number that >= nums[i] - t
            // indicates that nums[i] - target <= t
            Long target = treeSet.ceiling((long) nums[i] - t);
            // if such number exist
            // and if the target is also less equal than nums[i] + t
            // which will fulfill abs(nums[i] - nums[j]) <= t requirement
            if (target != null && target <= (long) nums[i] + t) {
                // this should be the answer
                return true;
            }
            // only store number in the range k from i
            if (i >= k) {
                treeSet.remove((long) nums[i - k]);
            }
            // add number to the set
            treeSet.add((long) nums[i]);
        }
        return false;
    }

    /**
     * @author: daohuei
     * @description: bucket method
     * @time: O(n): go through nums once
     * @space: O(k): the hashmap only store k number
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 1 || t < 0)
            return false;
        // use hash map for storing buckets
        Map<Long, Long> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            long remappedNum = (long) nums[i] - Integer.MIN_VALUE;
            // split the bucket with similar numbers
            long bucket = remappedNum / ((long) t + 1);
            // if current bucket already exist
            if (map.containsKey(bucket)
                    // if previous bucket exist and different is in the range
                    || (map.containsKey(bucket - 1) && remappedNum - map.get(bucket - 1) <= t)
                    // if next bucket exist and different is in the range
                    || (map.containsKey(bucket + 1) && map.get(bucket + 1) - remappedNum <= t))
                return true;
            if (map.entrySet().size() >= k) {
                // remove the last bucket if over k range
                long lastBucket = ((long) nums[i - k] - Integer.MIN_VALUE) / ((long) t + 1);
                map.remove(lastBucket);
            }
            // store the bucket value in the map
            map.put(bucket, remappedNum);
        }
        return false;
    }
}