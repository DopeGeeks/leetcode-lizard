package daohuei.leetcodelizard;
/*
 * 291. Word Pattern II
 * Link: https://leetcode.com/problems/word-pattern-ii/description/
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Given a pattern and a string str, find if str follows the same pattern.

// Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

// Examples:
// pattern = "abab", str = "redblueredblue" should return true.
// pattern = "aaaa", str = "asdasdasdasd" should return true.
// pattern = "aabb", str = "xyzabcxzyabc" should return false.
// Notes:
// You may assume both pattern and str contains only lowercase letters.
public class WordPatternTwo {
    /**
     * @author: daohuei
     * @description: hash map + dfs + backtracking
     * @time: O(n!): probably for all combinations in string
     * @space: O(n): for hashmap
     */

    Map<Character, String> map;
    Set<String> set;

    public boolean wordPatternMatch(String pattern, String str) {
        map = new HashMap<>();
        set = new HashSet<>();
        return isMatch(str, 0, pattern, 0);
    }

    private boolean isMatch(String str, int i, String pattern, int j) {
        // if reaching both end, found the pattern
        if (i == str.length() && j == pattern.length()) {
            return true;
        }
        // if only one of each reaching the end, then invalid
        if (i == str.length() || j == pattern.length()) {
            return false;
        }
        char c = pattern.charAt(j);
        // if the pattern key exist
        if (map.containsKey(c)) {
            // get the mapping substring
            String s = map.get(c);
            // check if it is same and start with index in the string
            if (!str.startsWith(s, i)) {
                return false;
            }
            // recur with next substring (skip the whole current substring)
            return isMatch(str, i + s.length(), pattern, j + 1);
        }
        // go through the string
        for (int k = i; k < str.length(); k++) {
            // get every substring start from i to k
            String p = str.substring(i, k + 1);
            // if the substring already existed, skip
            if (set.contains(p)) {
                continue;
            }
            // put the substring into map with the key of the pattern char c
            map.put(c, p);
            // add the substring in the set
            set.add(p);
            // recur with next index for searching all possible combination
            if (isMatch(str, k + 1, pattern, j + 1)) {
                return true;
            }
            // backtracking
            map.remove(c);
            set.remove(p);
        }
        return false;
    }
}
