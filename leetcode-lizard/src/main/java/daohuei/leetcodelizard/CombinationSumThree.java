package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.List;

/*
 * 216. Combination Sum III
 * Link: https://leetcode.com/problems/combination-sum-iii/description/
 */
public class CombinationSumThree {
    /**
     * @author: daohuei
     * @description: backtracking dfs
     * @time: O(9^k): go through all combinations
     * @space: O(k): recur at most with the depth of k
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        // empty case
        if (k <= 0 || n <= 0)
            return result;
        // init dfs with index 1 and emtpy array list
        dfs(result, new ArrayList<Integer>(), 1, k, n);
        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> list, int index, int k, int n) {
        // go through 9 numbers
        for (int i = index; i <= 9; i++) {
            // add to the list
            list.add(i);
            // if a perfect solution found
            if (n == i && list.size() == k) {
                // add the answer
                result.add(new ArrayList<>(list));
            } else if (n > i) {
                // if the target still larger than i(means there is still have possibility)
                // recur with next index, current temp list, k, and target without current
                // number
                dfs(result, list, i + 1, k, n - i);
            }
            // backtracking
            list.remove(list.size() - 1);
        }
    }
}