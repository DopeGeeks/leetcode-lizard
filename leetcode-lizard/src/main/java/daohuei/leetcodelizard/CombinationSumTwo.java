package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 40. Combination Sum II
 * Link: https://leetcode.com/problems/combination-sum-ii/description/
 */
public class CombinationSumTwo {
    /**
     * @author: daohuei
     * @description: dfs
     * @time: O(2^n): the number of the combination
     * @space: O(n): worst case for whole array as recursion stack
     */
    public List<List<Integer>> combinationSum2(int[] cand, int target) {
        // sort nlogn
        Arrays.sort(cand);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        dfs_com(cand, 0, target, path, res);
        return res;
    }

    void dfs_com(int[] cand, int cur, int target, List<Integer> path, List<List<Integer>> res) {
        // reach the answer
        if (target == 0) {
            // add into the answer
            res.add(new ArrayList(path));
            return;
        }
        // exceed, not doing any thing
        if (target < 0)
            return;
        for (int i = cur; i < cand.length; i++) {
            // if i is not the first number of current combination, and previous one is the
            // same number
            // means current round is useless, now we can skip it.
            if (i > cur && cand[i] == cand[i - 1])
                continue;
            // add candidate into the temp list
            path.add(path.size(), cand[i]);
            // return the rest of sum by minus current candidate
            dfs_com(cand, i + 1, target - cand[i], path, res);
            // remove candidate before next round
            path.remove(path.size() - 1);
        }
    }
}