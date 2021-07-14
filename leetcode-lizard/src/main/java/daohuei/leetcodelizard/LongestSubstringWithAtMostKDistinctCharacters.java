package daohuei.leetcodelizard;

import java.util.HashMap;
import java.util.Map;

/*
 * 340. Longest Substring with At Most K Distinct Characters
 * Link: https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
 */
public class LongestSubstringWithAtMostKDistinctCharacters {
    /**
     * @author: daohuei
     * @description: hash map method
     * @time: O(n): go through the string once
     * @space: O(n): for hash map
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        // put all character into the map with count
        for (int i = 0, j = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);

            // if the map not reaching k yet
            if (map.size() <= k) {
                // calculate the max length
                res = Math.max(res, i - j + 1);
            } else {
                // if exceed the k
                while (map.size() > k) {
                    // decrease the count of front most character
                    char ch = s.charAt(j);
                    map.put(ch, map.get(ch) - 1);
                    // front point move on
                    j++;
                    // if it is zero, remove it
                    if (map.get(ch) == 0) {
                        map.remove(ch);
                        break;
                    }
                }
            }
        }

        return res;
    }
}
