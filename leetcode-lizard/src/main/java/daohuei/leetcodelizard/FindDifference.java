package daohuei.leetcodelizard;

/*
 * 389. Find the Difference
 * Link: https://leetcode.com/problems/find-the-difference/
 */
public class FindDifference {

    /**
     * @author: daohuei
     * @description: simply count appearance into temp
     * @time: O(n): n = length of s or t
     * @space: O(1): 26 for temp
     */
    public char findTheDifference(String s, String t) {
        int[] temp = new int[26];
        // count appearance into temp array
        for (char c : t.toCharArray()) {
            temp[c - 'a']++;
        }
        // remove duplicate appearance
        for (char c : s.toCharArray()) {
            temp[c - 'a']--;
        }
        // search for which char appear but not repeated
        int i = 0;
        for (; i < 26; i++) {
            if (temp[i] == 1)
                break;
        }
        // return that char
        return (char) (i + 'a');
    }
}
