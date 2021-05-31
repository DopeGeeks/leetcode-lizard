package daohuei.leetcodelizard;

/*
 * 58. Length of Last Word
 * Link: https://leetcode.com/problems/length-of-last-word/
 */
public class LengthOfLastWord {
    /**
     * @author: daohuei
     * @description: loop from the end
     * @time: O(n): n = string length, O(n) will be worst case
     * @space: O(1): not using any
     */
    public int loopFromTheEnd(String s) {
        int count = 0;
        int index = s.length() - 1;
        // find space from the end
        while (true) {
            // if not space
            if (index < 0 || s.charAt(index) != ' ')
                break;
            index--;
        }
        // calculate the length of last word
        for (int i = index; i >= 0; i--) {
            // start from the end
            // count until the space found
            if (s.charAt(i) == ' ') {
                break;
            }
            count++;
        }
        return count;
    }
}
