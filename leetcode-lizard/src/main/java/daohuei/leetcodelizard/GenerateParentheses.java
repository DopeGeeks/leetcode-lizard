package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.List;

/*
 * 22. Generate Parentheses
 * Link: https://leetcode.com/problems/generate-parentheses/
 */
public class GenerateParentheses {
    /**
     * @author: daohuei
     * @description: backtracking
     * @time: O(n): backtrack with every possible brackets, n = max length of the
     *        answer
     * @space: O(logn): for recursion stack
     */
    public List<String> backtracking(int n) {
        List<String> ans = new ArrayList<String>();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, String cur, int left, int right, int n) {
        if (cur.length() == n * 2) {
            ans.add(cur);
            return;
        }
        // do not let the left bracket over the n
        if (left < n)
            backtrack(ans, cur + "(", left + 1, right, n);
        // do not let the right over the left
        // which means there is a left without right
        if (right < left)
            backtrack(ans, cur + ")", left, right + 1, n);
    }

    /**
     * @author: daohuei
     * @description: recursion
     * @time: O(n!): for recursive loop
     * @space: O(n): for recursion stack
     */
    public List<String> recursion(int n) {
        List<String> ans = new ArrayList<String>();
        if (n == 0) {
            ans.add("");
        } else {
            for (int a = 0; a < n; a++)
                for (String left : recursion(a))
                    for (String right : recursion(n - 1 - a))
                        // put left with every possible with a
                        // put right with every possible with n-1-a
                        ans.add("(" + left + ")" + right);
        }
        return ans;
    }
}