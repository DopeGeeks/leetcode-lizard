package daohuei.leetcodelizard;

import java.util.HashMap;
import java.util.HashSet;

/*
 * 219. Contains Duplicate II
 * Link: https://leetcode.com/problems/contains-duplicate-ii/description/
 */
public class ContainsDuplicateTwo {
    /**
     * @author: daohuei
     * @description: hash map
     * @time: O(n): length of nums
     * @space: O(n): for hash map
     */
    public boolean hashMap(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // if contains that value
            if (map.containsKey(nums[i])) {
                int index = map.get(nums[i]);
                // check if they have distance closer or equal than k
                if (i - index <= k) {
                    return true;
                }
            }
            // update the current value's last seen index
            map.put(nums[i], i);
        }
        return false;
    }

    /**
     * @author: daohuei
     * @description: hash set
     * @time: O(n): length of nums
     * @space: O(n): for hash set
     */
    public boolean hashSet(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // remove the seen number that have been passed with k distance behind
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            // add the number into it
            if (!set.add(nums[i])) {
                // if the number exist already, return true
                return true;
            }
        }
        return false;
    }
}