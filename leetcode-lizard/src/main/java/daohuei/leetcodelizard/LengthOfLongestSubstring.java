package daohuei.leetcodelizard;

import java.util.HashMap;

/*
 * Question: Longest Substring Without Repeating Characters
 * Description: Given a string s, find the length of the longest substring 
 * without repeating characters. 
 * Constraints:
 * 0 <= s.length <= 5 * 104
 * s consists of English letters, digits, symbols and spaces.
 * 
 * Example 1: 
 * Input: s = "abcabcbb" 
 * Output: 3 
 * Explanation: The answer is "abc", with the length of 3.
 * 
 * Example 2: 
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * 
 * Example 3: 
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring
 * "pwke" is a subsequence and not a substring.
 * 
 * Example 4:
 * Input: s = ""
 * Output: 0
 */
public class LengthOfLongestSubstring {
    /*
     * HashMap method
     * 
     * @Author: @daohuei
     * 
     * @Time: O(n), we loop over the string once.
     * 
     * @Space: O(m), we need a hashmap with the keys of all unique characters (m =
     * numbers of unique characters)
     */
    public static int lengthOfLongestSubstring(String s) {
        int ans = 0;
        HashMap<Character, Integer> indexHashMap = new HashMap<Character, Integer>();
        for (int i = 0, j = 0; j < s.length(); j++) {
            // `i` -> start point, `j` -> end point
            // every iter will move `j` forward
            if (indexHashMap.containsKey(s.charAt(j))) {
                // if duplicate char found: move i to next position
                // which is according to the HashMap
                i = Math.max(i, indexHashMap.get(s.charAt(j)));
            }
            // store the next position i should go if
            // the duplicate is found
            ans = Math.max(ans, j - i + 1);
            indexHashMap.put(s.charAt(j), j + 1);
        }
        return ans;
    }

}
