package daohuei.leetcodelizard;

/*
 * 158. Read N Characters Given Read4 II - Call multiple times
 * Link: https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/description/
 */
/* 
Example 1:
Input: s = "aDb", t = "adb" 
Output: true
Example 2:
Input: s = "ab", t = "ab" 
Output: false
Explanation:
s=t ,so they aren't one edit distance apart
Example 3:
Input: s = "1203", t = "1213"
Output: true
Explanation: We can replace '0' with '1' to get t.
*/
public class OneEditDistance {
    /**
     * @author: daohuei
     * @description: single loop but need to check for edge cases
     * @time: O(n): length of shorter string
     * @space: O(1): not using any
     */

    public boolean isOneEditDistance(String s, String t) {
        if (s == null || t == null || s.equals(t) || Math.abs(s.length() - t.length()) > 1) {
            return false;
        }
        // get the min size
        int size = Math.min(s.length(), t.length());

        for (int i = 0; i < size; i++) {
            // if not equal
            if (s.charAt(i) != t.charAt(i)) {
                // compare 3 situation
                // 1. s next and t current => 1 add
                // 2. s current and t next => 1 remove
                // 3. s next and t next => 1 add and 1 remove => 1 replace
                return compareStr(s, t, i + 1, i) || compareStr(s, t, i, i + 1) || compareStr(s, t, i + 1, i + 1);
            }
        }

        return true;
    }

    private boolean compareStr(String s, String t, int x, int y) {
        return s.substring(x).equals(t.substring(y));
    }
}
