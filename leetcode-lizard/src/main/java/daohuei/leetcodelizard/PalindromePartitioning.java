package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.List;

/*
 * 131. Palindrome Partitioning
 * Link: https://leetcode.com/problems/palindrome-partitioning/
 */
public class PalindromePartitioning {
    /**
     * @author: daohuei
     * @description: dp + recursion
     * @time: O(n^2): 2 layer nested loop
     * @space: O(n^2): for dp matrix (btw recursion stack need O(n))
     */
    public List<List<String>> dpAndRecursion(String s) {
        // n*n dp matrix for boolean
        boolean[][] dp = new boolean[s.length()][s.length()];
        int length = s.length();
        // for every possible length combination
        for (int len = 1; len <= length; len++) {
            for (int i = 0; i <= s.length() - len; i++) {
                // j is the end of the current length
                int j = i + len - 1;
                // store every relationship for i and j:
                // if they equal, and (inner part is true for making sure it is palindrome or
                // current len is 1 or 2)
                dp[i][j] = s.charAt(i) == s.charAt(j) && (len < 3 || dp[i + 1][j - 1]);
            }
        }
        // partition with such relationship
        return partitionHelper(s, 0, dp);
    }

    private List<List<String>> partitionHelper(String s, int start, boolean[][] dp) {
        // base case
        // start reaching the end
        if (start == s.length()) {
            // just init with the empty array list
            List<String> list = new ArrayList<>();
            List<List<String>> ans = new ArrayList<>();
            ans.add(list);
            return ans;
        }
        List<List<String>> ans = new ArrayList<>();
        for (int i = start; i < s.length(); i++) {
            // if relationship is true
            if (dp[start][i]) {
                // get the left substring
                String left = s.substring(start, i + 1);
                // add all the answer of next position
                for (List<String> l : partitionHelper(s, i + 1, dp)) {
                    // add the left part to the partition list
                    l.add(0, left);
                    ans.add(l);
                }
            }

        }
        return ans;
    }

    /**
     * @author: daohuei
     * @description: dp + backtracking recursion
     * @time: O(n^2): 2 layer nested loop
     * @space: O(n^2): for dp matrix (btw recursion stack need O(n))
     */
    public List<List<String>> partition(String s) {
        // geting the dp matrix
        boolean[][] dp = new boolean[s.length()][s.length()];
        int length = s.length();
        for (int len = 1; len <= length; len++) {
            for (int i = 0; i <= s.length() - len; i++) {
                dp[i][i + len - 1] = s.charAt(i) == s.charAt(i + len - 1) && (len < 3 || dp[i + 1][i + len - 2]);
            }
        }
        List<List<String>> ans = new ArrayList<>();
        // backtracking
        partitionBackTrackHelper(s, 0, dp, new ArrayList<String>(), ans);
        return ans;
    }

    private void partitionBackTrackHelper(String s, int start, boolean[][] dp, List<String> temp,
            List<List<String>> res) {
        // base case when recur to the end
        if (start == s.length()) {
            // temp will be the accumulated list of the palidrome string from start
            res.add(new ArrayList<>(temp));
        }
        // iter from the start
        for (int i = start; i < s.length(); i++) {
            // if it is palindrome
            if (dp[start][i]) {
                // get the left substring
                String left = s.substring(start, i + 1);
                // add to the temp
                temp.add(left);
                // backtrack with next position
                partitionBackTrackHelper(s, i + 1, dp, temp, res);
                // clear the temp after finished backtrack
                // the temp will at the status of the first input before entering the loop in
                // the next iter
                temp.remove(temp.size() - 1);
            }

        }
    }
}