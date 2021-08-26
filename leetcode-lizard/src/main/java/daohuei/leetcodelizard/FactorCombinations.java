package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
 * 254. Factor Combinations
 * Link: https://leetcode.com/problems/factor-combinations/description/
 */
/*
Numbers can be regarded as product of its factors. For example,
8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.
Note:
You may assume that n is always positive.
Factors should be greater than 1 and less than n.
Example 1:
Input: 1
Output: []
Example 2:
Input: 37
Output:[]
Example 3:
Input: 12
Output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]
Example 4:
Input: 32
Output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]
*/
public class FactorCombinations {
    /**
     * @author: daohuei
     * @description: dfs method
     * @time: O(n^1/2): find til the root of n
     * @space: O(k): k is the space of length of a single sublist for sublist and
     *         recursion stack
     */
    public List<List<Integer>> dfsMethod(int n) {
        List<List<Integer>> rst = new ArrayList<List<Integer>>();
        // empty case
        if (n <= 3)
            return rst;
        dfs(rst, new ArrayList<Integer>(), n, null);
        return rst;
    }

    public void dfs(List<List<Integer>> rst, List<Integer> list, int num, Integer lastFactor) {
        // it only reaches here when there is a `num % low == 0` from last level
        if (lastFactor != null) {
            // if found any last factor
            // add the rest of num as an answer into the result
            list.add(num);
            rst.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
        }
        // high bound is n^1/2
        int high = (int) Math.sqrt(num);
        // low bound is 2 or last factor
        int low = lastFactor == null ? 2 : Math.max(2, lastFactor);
        while (low <= high) {
            // if the low factor valid
            if (num % low == 0) {
                // add to the list
                list.add(low);
                // recur with the number divide low factor, and make low factor as last factor
                dfs(rst, list, num / low, low);
                // do backtracking
                list.remove(list.size() - 1);
            }
            // keep looking for valid low factor
            low++;
        }
    }

}
