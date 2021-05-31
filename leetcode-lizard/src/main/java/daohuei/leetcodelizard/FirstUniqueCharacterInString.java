package daohuei.leetcodelizard;

/*
 * 387. First Unique Character in a String
 * Link: https://leetcode.com/problems/first-unique-character-in-a-string/
 */
public class FirstUniqueCharacterInString {
    /**
     * @author: daohuei
     * @description: count with array
     * @time: O(n): n = string length
     * @space: O(1): at most O(26)
     */
    public int countWithArray(String s) {
        // init an array for all char with zero
        int[] count = new int[26];
        int n = s.length();
        // go through the string
        for (int i = 0; i < n; i++) {
            // store appearance in count array with letter index
            int index = s.charAt(i) - 'a';
            count[index]++;
        }

        // go through the string again
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i) - 'a';
            // first count is 1 will be the answer
            if (count[index] == 1) {
                return i;
            }

        }
        return -1;
    }
}