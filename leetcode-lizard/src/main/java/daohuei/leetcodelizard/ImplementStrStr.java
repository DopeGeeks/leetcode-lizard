package daohuei.leetcodelizard;

/*
 * 28. Implement strStr()
 * Link: https://leetcode.com/problems/implement-strstr/
 */
public class ImplementStrStr {
    /**
     * @author: daohuei
     * @description: 2 index matching
     * @time: O(kn): n = haystack length, k=needle length
     * @space: O(1): not using any
     */
    public int twoIndexMatch(String haystack, String needle) {
        // base case
        if (needle.length() == 0) {
            return 0;
        }
        int j = 0;
        // go through haystack
        for (int i = 0; i < haystack.length(); i++) {
            // if matched then move to next in the needle
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
                // if the length match then return
                if (j == needle.length()) {
                    // return start index of match string
                    return i - j + 1;
                }
            } else {
                // reset i to the match string start point's next index
                i = i - j;
                // reset j
                j = 0;
            }
        }
        return -1;
    }

    /**
     * @author: daohuei
     * @description: 2 for loop
     * @time: O(kn): n = haystack length, k=needle length
     * @space: O(1): not using any
     */
    public int twoForLoop(String haystack, String needle) {
        // go through haystack
        for (int i = 0;; i++) {
            // for go through needle
            for (int j = 0;; j++) {
                // if needle index match the needle length, return index of haystack
                if (j == needle.length())
                    return i;
                // which means there is no possible to find the answer since the rest of element
                // will not fulfill the needle requirements
                if (i + j == haystack.length())
                    return -1;
                // if the element not match
                if (needle.charAt(j) != haystack.charAt(i + j))
                    break;
            }
        }
    }
}
