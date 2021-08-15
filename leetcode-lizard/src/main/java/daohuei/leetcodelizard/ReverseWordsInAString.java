package daohuei.leetcodelizard;

/*
 * 151. Reverse Words in a String
 * Link: https://leetcode.com/problems/reverse-words-in-a-string/
 */
public class ReverseWordsInAString {
    /**
     * @author: daohuei
     * @description: brutal force
     * @time: O(n): n according to the space number
     * @space: O(1): constant if not considering string buffer
     */
    public String burtalForceMethod(String s) {
        if (s == null)
            return s;
        // split the string with space
        String[] strs = s.split(" ");
        StringBuffer sb = new StringBuffer();
        // iterate through the string
        for (String str : strs) {
            // insert to the front
            if (str.length() != 0)
                sb.insert(0, str + " ");
        }
        return sb.toString().trim();
    }

    /**
     * @author: daohuei
     * @description: brutal force
     * @time: O(logn): n according to the space number(reverse if not considering
     *        string buffer)
     * @space: O(1): constant if not considering string buffer
     */
    public String reverseStringMethod(String s) {
        String[] strs = s.split(" ");
        // reverse the string array first -> logn
        reverseString(strs);
        // convert to the string buffer
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() > 0) {
                sb.append(strs[i]);
                sb.append(" ");
            }
        }
        // convert to string
        return sb.toString().trim();
    }

    private void reverseString(String[] strs) {
        int i = 0, j = strs.length - 1;
        // two pointer reverse
        while (i < j) {
            String temp = strs[i];
            strs[i++] = strs[j];
            strs[j--] = temp;
        }
    }

    /**
     * @author: daohuei
     * @description: reverse by chararray
     * @time: O(logn): n according to the char numbers(reverse if not considering
     *        string buffer)
     * @space: O(1): constant if not considering string buffer
     */
    public String reverseWords(String s) {
        if (s == null)
            return s;
        int n = s.length();
        // 1. flip all
        char[] arr = s.toCharArray();
        reverse(arr, 0, n - 1);
        // 2. reverse individual
        reverseIndividual(arr, n);
        // 3. output and skip space
        return output(arr);
    }

    // char array to string
    private String output(char[] arr) {
        StringBuffer sb = new StringBuffer();
        int count = 0;
        for (char c : arr) {
            // count the space
            count += c == ' ' ? 1 : 0;
            // if not space and there is count, reset count
            if (count != 0 & c != ' ')
                count = 0;
            // if count is less than 1, append c (means no overspace)
            if (count <= 1)
                sb.append(c);
        }
        return sb.toString().trim();
    }

    // reverse word in chararry
    private void reverseIndividual(char[] arr, int n) {
        // i is word start, j is char start
        int i = 0, j = 0;
        while (i < n) {
            // skip space, i must finally larger than j in this step and is not space
            while (i < j || i < n && arr[i] == ' ')
                i++;
            // skip non-space, j must finally larger than i and is space(find end of a word)
            // or at the end of the array
            while (j < i || j < n && arr[j] != ' ')
                j++;
            // reverse the range
            reverse(arr, i, j - 1);
        }
    }

    // reverse char between i and j
    private void reverse(char[] arr, int i, int j) {
        while (i < j) {
            char temp = arr[i];
            arr[i++] = arr[j];
            arr[j--] = temp;
        }
    }
}