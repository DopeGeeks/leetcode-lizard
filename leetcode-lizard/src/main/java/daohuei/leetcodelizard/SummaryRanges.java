package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.List;

/*
 * 228. Summary Ranges
 * Link: https://leetcode.com/problems/summary-ranges/description/
 */
public class SummaryRanges {
    /**
     * @author: daohuei
     * @description: iteration
     * @time: O(n) length of nums
     * @space: O(1): not using any
     */
    public List<String> iteration(int[] nums) {
        int n = nums.length;
        // empty case
        if (n == 0) {
            return new ArrayList<>();
        }
        int start = nums[0];
        int end = nums[0];
        List<String> res = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            // if not continuous number
            if (nums[i] + 1 != nums[i + 1]) {
                // update end to last continuous number
                end = nums[i];
                // if start and end of the range are not the same
                if (start != end) {
                    // put arrow
                    res.add(start + "->" + end);
                } else {
                    // else just put the number
                    res.add(start + "");
                }
                // update next start of range
                start = nums[i + 1];
            }
        }
        // the loop only go through until n-2
        // need to consider the last number
        end = nums[n - 1];
        if (start != end) {
            res.add(start + "->" + end);
        } else {
            res.add(start + "");
        }
        return res;
    }

    /**
     * @author: daohuei
     * @description: recursion with class
     * @time: O(logn -> n) best case logn, worst case n
     * @space: O(1): not using any
     */
    public List<String> recursion(int[] nums) {

        List<String> resStr = new ArrayList<>();

        if (nums.length == 0) {
            return resStr;
        }

        List<Range> res = new ArrayList<>();
        helper(nums, 0, nums.length - 1, res);
        // convert range list to string list
        for (Range r : res) {
            // if start and end are the same
            if (r.start == r.end) {
                // simply add the number string
                resStr.add(Integer.toString(r.start));
            } else {
                resStr.add(r.start + "->" + r.end);
            }
        }

        return resStr;
    }

    class Range {
        int start;
        int end;

        Range(int s, int e) {
            start = s;
            end = e;
        }
    }

    // recursion function in half for the array
    private void helper(int[] nums, int i, int j, List<Range> res) {
        // base case:
        // 1. i, j are same index
        // 2. i to j is continuous number
        if (i == j || nums[j] - nums[i] == j - i) {
            add2res(nums[i], nums[j], res);
            return;
        }

        int m = (i + j) / 2;
        // do it halfly
        helper(nums, i, m, res);
        helper(nums, m + 1, j, res);
    }

    // function for adding range to the result
    private void add2res(int a, int b, List<Range> res) {
        // 1. the result list is empty
        // 2. if last range's end is continuous with start of the new range
        if (res.isEmpty() || res.get(res.size() - 1).end + 1 != a) {
            res.add(new Range(a, b));
        } else {
            // update the end of the previous range
            res.get(res.size() - 1).end = b;
        }
    }
}