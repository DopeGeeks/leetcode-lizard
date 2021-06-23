package daohuei.leetcodelizard;

import java.util.HashMap;

/*
 * 91. Decode Ways
 * Link: https://leetcode.com/problems/decode-ways/
 */
public class DecodeWays {
    /**
     * @author: daohuei
     * @description: hashmap + recursion
     * @time: O(n): withing memorization, only need n
     * @space: O(n): for every position of the string
     */
    public int recurHashMap(String s) {
        HashMap<Integer, Integer> memoization = new HashMap<>();
        return getAns(s, 0, memoization);
    }

    private int getAns(String s, int start, HashMap<Integer, Integer> memoization) {
        // if reaching the end, answer found
        if (start == s.length()) {
            return 1;
        }
        // if the first char is 0 then return 0
        if (s.charAt(start) == '0') {
            return 0;
        }
        // if calculated before
        int m = memoization.getOrDefault(start, -1);
        if (m != -1) {
            // we will use it
            return m;
        }
        // recur with next position
        int ans1 = getAns(s, start + 1, memoization);
        // check 2 character case
        int ans2 = 0;
        // if not the last position, since we need 2 characters
        if (start < s.length() - 1) {
            // get ten digit
            int ten = (s.charAt(start) - '0') * 10;
            // get one digit
            int one = s.charAt(start + 1) - '0';
            // if their sum in the range
            if (ten + one <= 26) {
                // move to next 2
                ans2 = getAns(s, start + 2, memoization);
            }
        }
        // store the answer with the starting point
        memoization.put(start, ans1 + ans2);
        // return the answer
        return ans1 + ans2;
    }

    /**
     * @author: daohuei
     * @description: dp
     * @time: O(n): string length
     * @space: O(1): pros of dp
     */
    public int dp(String s) {
        int len = s.length();
        // end start with 1
        // end should be accumulated count
        int end = 1;
        // cur should be the count with current index
        int cur = 0;
        // if the last one is not 0
        if (s.charAt(len - 1) != '0') {
            cur = 1;
        }
        // iter backward
        for (int i = len - 2; i >= 0; i--) {
            // if zero
            if (s.charAt(i) == '0') {
                // assign end to cur
                end = cur;
                // reset cur
                cur = 0;
                // skip
                continue;
            }
            int ans1 = cur;
            int ans2 = 0;
            // check if 2 digits is valid
            int ten = (s.charAt(i) - '0') * 10;
            int one = s.charAt(i + 1) - '0';
            if (ten + one <= 26) {
                // update ans2
                ans2 = end;
            }
            // assign end to cur
            end = cur;
            // accumulate the cur value
            cur = ans1 + ans2;

        }
        return cur;
    }
}