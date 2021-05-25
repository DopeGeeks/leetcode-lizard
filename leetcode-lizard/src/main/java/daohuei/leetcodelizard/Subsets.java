package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 78. Subsets
 * Link: https://leetcode.com/problems/subsets/
 */
public class Subsets {

    /**
     * @author: daohuei
     * @description: brutal iter through the array with three layer loops
     * @time: O(n * 2^n): n = length of the array.
     * @space: O(1): not using any
     */
    public List<List<Integer>> brutalIter(int[] nums) {
        // for iter purpose
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        // for answer storage
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // init with empty array
        ans.add(new ArrayList<Integer>());
        res.add(new ArrayList<Integer>());
        int n = nums.length;
        // first layer: walk through every item of the input array
        for (int i = 1; i <= n; i++) {
            // tmp: use for store new answer for ans and res
            List<List<Integer>> tmp = new ArrayList<List<Integer>>();
            // second layer: walk through every exist item in res
            for (List<Integer> list : res) {
                // third layer: walk through all item of the input array again
                for (int m = 0; m < n; m++) {
                    // if the list is not empty and the selected number is smaller than the last one
                    // of the answer
                    // which means we already stored it before, we do not want it
                    if (list.size() > 0 && list.get(list.size() - 1) >= nums[m])
                        continue;
                    // if passed the condition, store it
                    List<Integer> newList = new ArrayList<Integer>(list);
                    newList.add(nums[m]);
                    tmp.add(newList);
                    ans.add(newList);
                }
            }
            res = tmp;
        }
        return ans;
    }

    /**
     * @author: daohuei
     * @description: brutal iter through the array with three layer loops
     * @time: O(n * 2^n): n = length of the array.
     * @space: O(1): not using any
     */
    public List<List<Integer>> iter(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        ans.add(new ArrayList<Integer>());
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> ans_tmp = new ArrayList<>();
            // iter through existed answer results
            for (List<Integer> list : ans) {
                // init temp with such existed result list
                List<Integer> tmp = new ArrayList<>(list);
                // add the num into temp
                tmp.add(nums[i]);
                // add the temp list into temp ans list
                ans_tmp.add(tmp);
            }
            // put all elements into ans list
            ans.addAll(ans_tmp);
        }
        return ans;
    }

    private void dfs(int[] nums, int start, ArrayList<Integer> temp, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++) {
            // since it is sorted, we can check whether the number is duplicated
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            temp.add(nums[i]);
            dfs(nums, i + 1, temp, ans);
            temp.remove(temp.size() - 1);
        }
    }

    /**
     * @author: daohuei
     * @description: same as Subset 1 but sort it first and add one additional condition about duplicated check 
     * @time: O(2^n): using binary search
     * @space: O(logn): binary tree
     */
    public List<List<Integer>> backTracking(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums); // sort it first
        dfs(nums, 0, new ArrayList<Integer>(), ans);
        return ans;
    }

    /**
     * @author: daohuei
     * @description: using bit to determine whether item should be put into the ans
     * @time: O(n * 2^n): using binary search
     * @space: O(1): not using any
     */
    public List<List<Integer>> bitSearch(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        // getting the bit number
        int bit_nums = nums.length;
        // move answer left side with bit number, 2^bitnums
        int ans_nums = 1 << bit_nums;
        // iters through ans numbers
        for (int i = 0; i < ans_nums; i++) {
            List<Integer> tmp = new ArrayList<>();
            int count = 0; // for indexing of nums
            int i_copy = i; // for getting bit numbers of ans number
            while (i_copy != 0) {
                // check whether the last digit is 1
                if ((i_copy & 1) == 1) {
                    // if so store the answer
                    tmp.add(nums[count]);
                }
                // move to the next index
                count++;
                // move to the left digit
                i_copy = i_copy >> 1;
            }
            ans.add(tmp);

        }
        return ans;
    }

}
