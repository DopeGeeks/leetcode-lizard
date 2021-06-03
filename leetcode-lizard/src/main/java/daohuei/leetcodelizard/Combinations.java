package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
 * 77. Combinations
 * Link: https://leetcode.com/problems/combinations/
 */
public class Combinations {
    /**
     * @author: daohuei
     * @description: back tracking
     * @time: O((n-k)!): since it will be dynamically decrease with n and k value
     * @space: O((n-k)!): since it will be dynamically decrease with n and k value
     */
    public List<List<Integer>> backTracking(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        // start from first node
        getAns(1, n, k, new ArrayList<Integer>(), ans);
        return ans;
    }

    private void getAns(int start, int n, int k, ArrayList<Integer> temp, List<List<Integer>> ans) {
        if (temp.size() == k) {
            // if aleady reach k, then add it to the answer
            ans.add(new ArrayList<Integer>(temp));
            return;
        }
        // for n=4, k=2 if temp = [], we only need to run til 3
        for (int i = start; i <= n - (k - temp.size()) + 1; i++) {
            // add value
            temp.add(i);
            // start with new temp
            getAns(i + 1, n, k, temp, ans);
            // remove current value
            temp.remove(temp.size() - 1);
        }
    }

    /**
     * @author: daohuei
     * @description: iterations
     * @time: O(n*k*(n-k)): 3 for loop
     * @space: O(n*k*(n-k)): 3 for loop
     */
    public List<List<Integer>> iteration(int n, int k) {
        if (n == 0 || k == 0 || k > n)
            return Collections.emptyList();
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        // add all first element as an element for each
        for (int i = 1; i <= n + 1 - k; i++)
            res.add(Arrays.asList(i));
        // start with second element
        for (int i = 2; i <= k; i++) {
            List<List<Integer>> tmp = new ArrayList<List<Integer>>();

            for (List<Integer> list : res) {
                // expand the list from first loop
                // start with previous list last element + 1
                // add them into it until reach the current target i(which will loop to k)
                for (int m = list.get(list.size() - 1) + 1; m <= n - (k - (i - 1)) + 1; m++) {
                    // add all candidates to the temp list
                    List<Integer> newList = new ArrayList<Integer>(list);
                    newList.add(m);
                    tmp.add(newList);
                }
            }
            // renew the res
            res = tmp;
        }
        return res;
    }

    /**
     * @author: daohuei
     * @description: recursion
     * @time: O(k!*n): k! for recursion, n for foreach
     * @space: O(k!): recursion stack
     */
    public List<List<Integer>> recursion(int n, int k) {
        // empty case or base case
        if (k == n || k == 0) {
            List<Integer> row = new LinkedList<>();
            for (int i = 1; i <= k; ++i) {
                row.add(i);
            }
            return new LinkedList<>(Arrays.asList(row));
        }

        // pick n-1 and k-1 first
        List<List<Integer>> result = recursion(n - 1, k - 1);
        // add n to every result: finish all answer that need n
        for (int i = 0; i < result.size(); i++)
            result.get(i).add(n);
        // do the rest recursions
        result.addAll(recursion(n - 1, k));
        return result;
    }

    /**
     * @author: daohuei
     * @description: dynamically programming
     * @time: O(k*n): k! for recursion, n for foreach
     * @space: O(k): for temp
     */
    public List<List<Integer>> dynamicallyProgramming(int n, int k) {
        List<List<Integer>>[] dp = new ArrayList[k + 1];
        dp[0] = new ArrayList<>();
        dp[0].add(new ArrayList<Integer>());
        // from 1 to n
        for (int i = 1; i <= n; i++) {
            List<List<Integer>> temp = new ArrayList<>(dp[0]);
            // j but not exceed i or k
            for (int j = 1; j <= i && j <= k; j++) {
                List<List<Integer>> last = temp;
                // if dp[j] existed, update temp
                if (dp[j] != null) {
                    temp = new ArrayList<>(dp[j]);
                }
                // if i equal to j, then we need a new array(means a new iter for j)
                if (i <= j) {
                    dp[j] = new ArrayList<>();
                }
                // put every i into templist and add them to the j element
                for (List<Integer> list : last) {
                    List<Integer> tmpList = new ArrayList<>(list);
                    tmpList.add(i);
                    dp[j].add(tmpList);
                }
            }
        }
        return dp[k];
    }
}