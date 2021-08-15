package daohuei.leetcodelizard;

/*
 * 186. Reverse Words in a String II
 * Link: https://leetcode.com/problems/reverse-words-in-a-string-ii/
 */

/*
Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.
The input string does not contain leading or trailing spaces and the words are always separated by a single space.
For example,
Given s = "the sky is blue",
return "blue is sky the".
Could you do it in-place without allocating extra space?
Related problem: Rotate Array
Hide Company Tags Amazon Microsoft
Hide Tags String
Hide Similar Problems (M) Reverse Words in a String (E) Rotate Array
*/
public class ReverseWordsInAStringTwo {
    /**
     * @author: daohuei
     * @description: brutal force
     * @time: O(n): n according to the characters number
     * @space: O(1): not using any
     */
    public void reverseWords(char[] str) {
        if (str == null || str.length <= 1) {
            return;
        }
        reverse(str, 0, str.length - 1);
        int start = 0;
        // go through every characters
        for (int i = 0; i < str.length; i++) {
            // if found a space(indicates end of a word)
            if (str[i] == ' ') {
                // reverse the word
                reverse(str, start, i - 1);
                // update start
                start = i + 1;
            }
            // if found the end of the entire string
            else if (i == str.length - 1) {
                // reverse the last word
                reverse(str, start, i);
            }
        }
    }

    // reverse characters between 2 index with 2 pointer
    public void reverse(char[] s, int start, int end) {
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }

}
