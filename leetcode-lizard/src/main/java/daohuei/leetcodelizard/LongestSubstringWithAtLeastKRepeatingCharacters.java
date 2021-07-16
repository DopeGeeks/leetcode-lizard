package daohuei.leetcodelizard;

import java.util.HashMap;
import java.util.Map;

/*
 * 395. Longest Substring with At Least K Repeating Characters
 * Link: https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {

    /**
     * @author: daohuei
     * @description: hash map and recursion method
     * @time: O(n*m): m = number that repeated more than k times
     * @space: O(n): for priority queue, it only store at most k numbers
     */
    public int longestSubstring(String s, int k) {
        int n = s.length();
        // empty case
        if (n == 0 || n < k)
            return 0;
        // k=1 case
        if (k <= 1)
            return n;

        Map<Character, Integer> counts = new HashMap();
        // count all the chars into the map
        for (char c : s.toCharArray())
            counts.put(c, counts.getOrDefault(c, 0) + 1);

        // go through the string to find left point
        int l = 0;
        while (l < n && counts.get(s.charAt(l)) >= k)
            l++;
        // if l exceed the total length
        if (l >= n - 1)
            return l;

        //
        int ls1 = longestSubstring(s.substring(0, l), k);
        // ignore the unneeded characters
        while (l < n && counts.get(s.charAt(l)) < k)
            l++;
        // if exceed the total length: 0
        // if still in the length, means there are more substring in the end part
        // recur with end part
        int ls2 = (l < n) ? longestSubstring(s.substring(l), k) : 0;
        // get the larger one of begin part and end part
        return Math.max(ls1, ls2);
    }
}