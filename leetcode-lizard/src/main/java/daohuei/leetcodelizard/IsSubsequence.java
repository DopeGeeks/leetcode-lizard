package daohuei.leetcodelizard;

/*
 * 392. Is Subsequence
 * Link: https://leetcode.com/problems/is-subsequence/description/
 */
public class IsSubsequence {
    /**
     * @author: daohuei
     * @description: single loop method
     * @time: O(n): just single loop
     * @space: O(1): not using any
     */
    public boolean isSubsequence(String s, String t) {
        int slen = s.length(), tlen = t.length();
        int index = 0;
        // go through longer string
        for (int i = 0; i < tlen; i++) {
            // if found index is reaching the end, break
            if (index == slen)
                break;
            // if character found, count index
            if (s.charAt(index) == t.charAt(i)) {
                index++;
            }
        }
        // just return whether index reach the end
        return index == slen ? true : false;
    }
}