package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 345. Reverse Vowels of a String
 * Link: https://leetcode.com/problems/reverse-vowels-of-a-string/description/
 */
public class ReverseVowelsOfAString {
    /**
     * @author: daohuei
     * @description: take out and reverse
     * @time: O(n): single loop for string s
     * @space: O(n): for matches
     */
    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        List<Character> matches = new ArrayList<>();
        // take out all the vowels in the string
        for (int i = 0; i < s.length(); i++) {
            if (vowels.contains(s.charAt(i))) {
                matches.add(s.charAt(i));
            }
        }

        // put back in backward
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char letter = s.charAt(i);
            if (vowels.contains(letter)) {
                int lastMatchIndex = matches.size() - 1;
                sb.append(matches.get(lastMatchIndex));
                matches.remove(lastMatchIndex);
            } else {
                sb.append(letter);
            }
        }
        return sb.toString();
    }

    /**
     * @author: daohuei
     * @description: 2 pointer and swap
     * @time: O(n): single loop for string s
     * @space: O(n): for string buffer
     */
    public String twoPointerAndSwap(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        StringBuffer sb = new StringBuffer(s);

        int maxIndex = sb.length() - 1;
        // pointer one
        int i = 0;
        // pointer two
        int j = maxIndex;
        while (i < j) {
            // find the pointer from the start
            while (i < maxIndex && !vowels.contains(sb.charAt(i)))
                i++;
            // find the pointer from the end
            while (j > 0 && !vowels.contains(sb.charAt(j)))
                j--;
            // if still valid (index not exceed each other) and found the vowel
            if (i < j && vowels.contains(sb.charAt(i)) && vowels.contains(sb.charAt(j))) {
                // swap them
                char letter = sb.charAt(j);
                sb.setCharAt(j, sb.charAt(i));
                sb.setCharAt(i, letter);
                // move on
                j--;
                i++;
            }
        }
        return sb.toString();
    }
}