package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.List;

/*
 * 46. Permutations
 * Link: https://leetcode.com/problems/permutations/
 */
public class Permutations {
    /**
     * @author: daohuei
     * @description: back tracking
     * @time: O(n!): n=nums.length, for backtracking
     * @space: O(n): for recursion stack
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<Integer>(), nums);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            // if the temp list match the nums length
            // means answer found
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (tempList.contains(nums[i]))
                    continue; // if num already exist, skip
                tempList.add(nums[i]); // else add nums to the temp list
                backtrack(list, tempList, nums); // keep back tracking
                tempList.remove(tempList.size() - 1); // remove the current num
            }
        }
    }
}