package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.List;

/*
 * 241. Different Ways to Add Parentheses
 * Link: https://leetcode.com/problems/different-ways-to-add-parentheses/description/
 */
public class DifferentWaysToAddParentheses {
    /**
     * @author: daohuei
     * @description: dfs
     * @time: O(k): k indicates the number of operators
     * @space: O(1): not using any
     */
    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> res = new ArrayList<>();
        if (expression == null || expression.length() == 0)
            return res;

        // go through every char
        for (int i = 0; i < expression.length(); i++) {
            char chr = expression.charAt(i);
            // if operators
            if (chr == '+' || chr == '-' || chr == '*') {
                // split the string with the operator
                String part1 = expression.substring(0, i);
                String part2 = expression.substring(i + 1);

                // calculate their results separately
                List<Integer> part1Res = diffWaysToCompute(part1);
                List<Integer> part2Res = diffWaysToCompute(part2);

                // for every results
                for (Integer p1 : part1Res) {
                    for (Integer p2 : part2Res) {
                        // calculate them
                        if (chr == '+') {
                            res.add(p1 + p2);
                        } else if (chr == '-') {
                            res.add(p1 - p2);
                        } else {
                            res.add(p1 * p2);
                        }
                    }
                }
            }
        }

        // if size is 0, which means only contains single number
        if (res.size() == 0) {
            res.add(Integer.valueOf(expression));
        }

        return res;
    }
}