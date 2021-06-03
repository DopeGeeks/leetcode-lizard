package daohuei.leetcodelizard;

import java.util.HashSet;

/*
 * 217. Contains Duplicate
 * Link: https://leetcode.com/problems/contains-duplicate/
 */
public class ContainsDuplicate {
    /**
     * @author: daohuei
     * @description: hashset
     * @time: O(n): length of nums
     * @space: O(n): for hash set
     */
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            // if contains
            if (set.contains(nums[i])) {
                return true;
            }
            // if not, just add it
            set.add(nums[i]);
        }
        return false;
    }
}
