package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.List;

/*
 * 39. Combination Sum
 * Link: https://leetcode.com/problems/combination-sum/
 */
public class CombinationSum {
    /**
     * @author: daohuei
     * @description: back tracking
     * @time: O(n!): for backtracking
     * @space: O(n): for recursion stack
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<Integer>(), candidates, target, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
        // if no remain means wrong answer
        if (remain < 0)
            return;
        // if remain is 0, answer found
        else if (remain == 0)
            list.add(new ArrayList<>(tempList));
        else {
            for (int i = start; i < nums.length; i++) {
                // put number into the templist first
                tempList.add(nums[i]);
                // get the remain and backtrack with the next number
                backtrack(list, tempList, nums, remain - nums[i], i);
                // if answer found or no answer, remove the number and then keep moving
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}