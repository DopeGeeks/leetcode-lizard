package daohuei.leetcodelizard;

/*
 * 214. Shortest Palindrome
 * Link: https://leetcode.com/problems/shortest-palindrome/description/
 */
public class ShortestPalindrome {
    /**
     * @author: daohuei
     * @description: single loop and dealing with edge case
     * @time: O(n): actually n/2
     * @space: O(n): for end and end reverse string buffer
     */
    public String simpleLoopAndReverseMethod(String s) {
        // empty case
        if (s == null || s.length() <= 1) {
            return s;
        }

        // find the mid point
        int n = s.length();
        int mid = n / 2 + (n % 2 == 0 ? -1 : 0);

        // loop from mid -> 0, have buffer to store end
        // perform 2 checks, and substring check
        for (int i = mid; i >= 0; i--) {
            // the end substring
            String end = s.substring(i + 1);
            // the reversed end substring
            String reversedEnd = new StringBuffer(s.substring(i + 1)).reverse().toString();
            // check if (0 -> mid, mid+1 -> end) is substring
            if (validateSubstring(s, i, i + 1)) {
                // the end part reverse and add with the end part will be shortest palindrome
                return reversedEnd + end;
            }
            // check if (0 -> mid-1, mid+1 -> end) is substring
            if (i >= 1 && validateSubstring(s, i - 1, i + 1)) {
                // the middle point need to be added to the answer
                return reversedEnd + s.charAt(i) + end;
            }
        }
        // if not finding any shortcut, directly reverse 1 -> end and add to the front
        return new StringBuffer(s.substring(1)).reverse().toString() + s;
    }

    // to check if 0 -> i is substring of j -> end
    private boolean validateSubstring(String s, int i, int j) {
        String end = s.substring(j).toString();
        String front = new StringBuffer(s.substring(0, i + 1)).reverse().toString();
        // if front string got smaller length and have send frist char with end
        // means front is the end's substring
        if (front.length() <= end.length() && end.indexOf(front) == 0) {
            return true;
        }
        return false;
    }

    /**
     * @author: daohuei
     * @description: KMP method
     * @time: O(n): for forming KMP table
     * @space: O(n): KMP table
     */
    public String shortestPalindrome(String s) {
        // split temp palindrome with middle point "#"
        String temp = s + "#" + new StringBuilder(s).reverse().toString();
        int[] table = getTable(temp);

        return new StringBuilder(s.substring(table[table.length - 1])).reverse().toString() + s;
    }

    public int[] getTable(String s) {
        // get lookup table
        int[] table = new int[s.length()];

        // pointer that points to matched char in prefix part
        int index = 0;

        // skip index 0, we will not match a string with itself
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(index) == s.charAt(i)) {
                // we can extend match in prefix and postfix
                table[i] = table[i - 1] + 1;
                index++;
            } else {
                // match failed, we try to match a shorter substring

                // by assigning index to table[i-1], we will shorten the match string length,
                // and jump to the
                // prefix part that we used to match postfix ended at i - 1
                index = table[i - 1];

                while (index > 0 && s.charAt(index) != s.charAt(i)) {
                    // we will try to shorten the match string length until we revert to the
                    // beginning of match (index 1)
                    index = table[index - 1];
                }

                // when we are here may either found a match char or we reach the boundary and
                // still no luck
                // so we need check char match
                if (s.charAt(index) == s.charAt(i)) {
                    // if match, then extend one char
                    index++;
                }

                table[i] = index;
            }

        }
        return table;
    }
}