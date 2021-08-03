package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 247. Strobogrammatic Number II
 * Link: https://leetcode.com/problems/strobogrammatic-number-ii/description/
 */
/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Find all strobogrammatic numbers that are of length = n.
For example,
Given n = 2, return ["11","69","88","96"].
Hint:
Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.
Tags: Math Recursion
Similar Problems: (E) Strobogrammatic Number, (H) Strobogrammatic Number III
*/

public class StrobogrammaticNumberTwo {
    /**
     * @author: daohuei
     * @description: dfs method
     * @time: O(n): for searching
     * @space: O(n): for recursion stack
     */

    List<String> singleDigitList = new ArrayList<>(Arrays.asList("0", "1", "8"));
    List<String> doubleDigitList = new ArrayList<>(Arrays.asList("00", "11", "88", "69", "96"));
    char[][] digitPair = { { '0', '0' }, { '1', '1' }, { '8', '8' }, { '6', '9' }, { '9', '6' } };

    public List<String> findStrobogrammatic(int n) {
        // using dfs for searching final answers
        List<String> result = dfs(n);
        for (int i = 0; i < result.size(); i++) {
            String num = result.get(i);
            // for make sure the answer has valid length(like zeros in the head)
            // parse it as long data and transfer back to string and check length
            if ((Long.parseLong(num) + "").length() != n) {
                // if not, remove it and redo this round
                result.remove(i);
                i--;
            }
        }
        return result;
    }

    public List<String> dfs(int n) {
        List<String> list = new ArrayList<>();
        if (n <= 0)
            return list;
        if (n == 1)
            return singleDigitList;
        if (n == 2)
            return doubleDigitList;
        // get the sublist without head and tail
        List<String> subList = dfs(n - 2);
        for (String str : subList) {
            // for every candidates
            for (int i = 0; i < digitPair.length; i++) {
                // add every possibilities to the head and tail as a answer
                list.add(digitPair[i][0] + str + digitPair[i][1]);
            }
        }
        return list;
    }
}
