package daohuei.leetcodelizard;

/*
 * 76. Minimum Window Substring
 * Link: https://leetcode.com/problems/minimum-window-substring/
 */
public class MinimumWindowSubstring {
    /**
     * @author: daohuei
     * @description: hashmap(integer array)
     * @time: O(n): 2n actually for the windows
     * @space: O(1): using 128 for integer array
     */
    public String minWindow(String s, String t) {
        // can use hashmap but since only store char, we can just simply use integer
        // array
        int[] map = new int[128];
        // go through t and count them with the map
        for (int i = 0; i < t.length(); i++) {
            char char_i = t.charAt(i);
            map[char_i]++;
        }
        // current left and right
        int left = 0;
        int right = 0;
        // min left and right
        int ans_left = 0;
        int ans_right = -1;
        // min length
        int ans_len = Integer.MAX_VALUE;
        // for counting how many char not matched yet
        int count = t.length();
        // go through the s
        while (right < s.length()) {
            // get the current right char
            char char_right = s.charAt(right);
            // remove count by 1 of current right char
            map[char_right]--;

            // if match one in t
            if (map[char_right] >= 0) {
                // remove current not match count by 1
                count--;
            }
            // if all char in t found
            while (count == 0) {
                // current length
                int temp_len = right - left + 1;
                // if it is shorter (a new answer)
                if (temp_len < ans_len) {
                    // update ans
                    ans_left = left;
                    ans_right = right;
                    ans_len = temp_len;
                }
                // get the left char
                char key = s.charAt(left);
                // since we are moving left forward => remove left => map count +1 back
                map[key]++;
                // if current char is in t, we need to restore no-matched count by 1
                if (map[key] > 0) {
                    count++;
                }
                // left move forward
                left++;
            }
            // right move forward
            right++;
        }
        // the answer will be the the substring from left the right
        return s.substring(ans_left, ans_right + 1);
    }
}