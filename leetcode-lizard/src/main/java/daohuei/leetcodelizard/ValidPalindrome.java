package daohuei.leetcodelizard;

/*
 * 125. Valid Palindrome
 * Link: https://leetcode.com/problems/valid-palindrome/
 */
public class ValidPalindrome {
    /**
     * @author: daohuei
     * @description: start end pointer
     * @time: O(n) n: numbers of characters
     * @space: O(1) not using any
     */
    public boolean isPalindrome(String s) {
        int len = s.length();
        // considering lowercase only
        s = s.toLowerCase();
        // i: start, j: end
        int i = 0, j = len - 1;
        while (i < j) {
            // this loop is for skipping invalid characters
            while (!isAlphanumeric(s.charAt(i))) {
                i++;
                // if reaching the end
                if (i == len) {
                    return true;
                }
            }
            // this loop is for skipping invalid characters
            while (!isAlphanumeric(s.charAt(j))) {
                j--;
            }
            // if start and end is not the same
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            // start move forward
            i++;
            // end move backward
            j--;
        }
        return true;

    }

    // considering only alphanumeric characters
    private boolean isAlphanumeric(char c) {
        if ('a' <= c && c <= 'z' || 'A' <= c && c <= 'Z' || '0' <= c && c <= '9') {
            return true;
        }
        return false;
    }
}