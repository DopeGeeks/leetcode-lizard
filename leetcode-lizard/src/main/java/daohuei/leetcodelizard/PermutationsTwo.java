package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 47. Permutations II
 * Link: https://leetcode.com/problems/permutations-ii/description/
 */
public class PermutationsTwo {
    /**
     * @author: daohuei
     * @description: dfs with memorization method
     * @time: O(n^2): for dfs loop, if no duplicate
     * @space: O(n): for recursion stack need at most n layer
     */
    boolean[] visited;

    public List<List<Integer>> dfsMemorization(int[] nums) {
        List<List<Integer>> rst = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return rst;
        }
        // sort it first
        Arrays.sort(nums);
        int n = nums.length;
        // use a visited array to skip same position
        visited = new boolean[n];
        dfs(rst, new ArrayList<Integer>(), nums);
        return rst;

    }

    private void dfs(List<List<Integer>> rst, List<Integer> list, int[] nums) {
        // if reaching the end
        if (list.size() == nums.length) {
            // return the current answer
            rst.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // if visit before, or previous is the same and previous is visit before
            if (visited[i] || (i - 1 >= 0 && visited[i - 1] && nums[i] == nums[i - 1])) {
                // skip
                continue;
            }
            // if not, means there is a new combination
            visited[i] = true;
            list.add(nums[i]);
            // recur again
            dfs(rst, list, nums);
            // if finish current round, restore the answer
            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }

    /**
     * @author: daohuei
     * @description: dfs with queue method
     * @time: O(n^2): for dfs loop, if no duplicate
     * @space: O(n): for recursion stack need at most n layer
     */
    public List<List<Integer>> dfsQueue(int[] nums) {
        if (nums == null || nums.length == 0)
            return new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        Arrays.sort(nums);
        for (int num : nums)
            queue.offer(num);
        List<List<Integer>> rst = new ArrayList<>();
        dfs(rst, new ArrayList<Integer>(), queue);
        return rst;
    }

    private void dfs(List<List<Integer>> rst, List list, Queue<Integer> queue) {
        if (queue.isEmpty()) {
            rst.add(new ArrayList<Integer>(list));
            return;
        }
        int size = queue.size();
        Integer last = null;
        while (size-- > 0) {
            int val = queue.poll();
            // if it is not the first number or duplicate
            if (last != null && last == val) {
                // skip and put the value back
                queue.offer(val);
                continue;
            }
            // add to the sub list
            list.add(val);
            // recur with dfs
            dfs(rst, list, queue);
            list.remove(list.size() - 1);
            // put the value back and update last value
            queue.offer(val);
            last = val;
        }
    }

    /**
     * @author: daohuei
     * @description: iterations method
     * @time: O(n^2): the loops actually only go through every combination
     * @space: O(1): not really using any
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> rst = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return rst;
        }
        int n = nums.length;
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        // add the original list first
        rst.add(new ArrayList<>(list));
        // the first pointer
        for (int pos = 0; pos < n; pos++) {
            // iter backward in all current answers
            for (int i = rst.size() - 1; i >= 0; i--) {
                list = rst.get(i);
                // sort the sublist start from pos
                Collections.sort(list.subList(pos, list.size()));

                // for the second pointer
                for (int j = pos + 1; j < n; j++) {
                    // if duplicate then ignore
                    if (list.get(j) == list.get(j - 1)) {
                        continue;
                    }
                    // swap pos with j
                    swap(list, pos, j);
                    // add the answer
                    rst.add(new ArrayList<>(list));
                    // swap it back
                    swap(list, pos, j);
                }
            }
        }

        return rst;
    }

    // for swap value in the position x and y
    private void swap(List<Integer> list, int x, int y) {
        int temp = list.get(x);
        list.set(x, list.get(y));
        list.set(y, temp);
    }
}