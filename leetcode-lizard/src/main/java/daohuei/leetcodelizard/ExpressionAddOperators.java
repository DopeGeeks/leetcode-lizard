package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.List;

/*
 * 282. Expression Add Operators
 * Link: https://leetcode.com/problems/expression-add-operators/description/
 */
public class ExpressionAddOperators {
    /**
     * @author: daohuei
     * @description: backtracking method
     * @time: O(n!): since we go through every combination of the n numbers
     * @space: O(n): for recursion stack
     */
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(res, sb, num, 0, target, 0, 0);
        return res;
    }

    public void dfs(List<String> res, StringBuilder sb, String num, int pos, int target, long prev, long multi) {
        // if have consumed all numbers
        if (pos == num.length()) {
            // if the output is same as the target, add the string to the answer
            if (target == prev)
                res.add(sb.toString());
            return;
        }
        // go through the rest of all numbers
        for (int i = pos; i < num.length(); i++) {
            // if the first number is "0" we just ignore the rest in this recursion stack
            if (num.charAt(pos) == '0' && i != pos)
                break;
            // get the current number from pos to i
            long curr = Long.parseLong(num.substring(pos, i + 1));
            int len = sb.length();
            // if pos is the first location
            if (pos == 0) {
                // just recur with next place
                // since we dont need any operator for the first number
                dfs(res, sb.append(curr), num, i + 1, target, curr, curr);
                // and backtrack
                sb.setLength(len);
            } else {
                // 3 kind of operators
                dfs(res, sb.append("+").append(curr), num, i + 1, target, prev + curr, curr);
                sb.setLength(len);
                dfs(res, sb.append("-").append(curr), num, i + 1, target, prev - curr, -curr);
                sb.setLength(len);
                dfs(res, sb.append("*").append(curr), num, i + 1, target, prev - multi + multi * curr, multi * curr);
                sb.setLength(len);
            }
        }
    }
}