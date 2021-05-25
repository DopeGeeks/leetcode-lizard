package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 90. Subsets II
 * Link: https://leetcode.com/problems/subsets-ii/
 */
public class SubsetsTwo {
    private void dfs(int[] nums, int start, ArrayList<Integer> temp, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++) {
            // ignore when previous one equal current one
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            // the rest are same as previous problem
            temp.add(nums[i]);
            dfs(nums, i + 1, temp, ans);
            temp.remove(temp.size() - 1);
        }
    }

    /**
     * @author: daohuei
     * @description: https://www.youtube.com/watch?v=REOH22Xwdkk&ab_channel=NeetCodeNeetCode
     * @time: O(n* 2^n): using binary tree
     * @space: O(logn): binary tree
     */
    public List<List<Integer>> backTracking(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums); // sort it first
        dfs(nums, 0, new ArrayList<Integer>(), ans);
        return ans;
    }
}
