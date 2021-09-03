package daohuei.leetcodelizard;

import java.util.Stack;

/*
 * 316. Remove Duplicate Letters
 * Link: https://leetcode.com/problems/remove-duplicate-letters/description/
 */
public class RemoveDuplicateLetters {
    /**
     * @author: daohuei
     * @description: hash map method
     * @time: O(n): go through the string once
     * @space: O(n): for visit and count array
     */
    public String hashMapMethod(String s) {
        // check edge case
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] arr = s.toCharArray();
        // build count array: can use hashmap for clean code
        int[] count = new int[256];
        boolean[] visited = new boolean[256];
        // count for all char
        for (char c : arr) {
            count[c]++;
        }

        // loop over s and aggregate;
        StringBuffer sb = new StringBuffer("0");
        for (char c : arr) {
            // decount
            count[c]--;
            // if visit before, ignore
            if (visited[c]) {
                continue;
            }
            // get the last char
            char lastChar = sb.charAt(sb.length() - 1);
            // Compare with tail; reduce tail letter if necessary && applicable
            // removing when not in the order and the lastchar still exist at further place
            while (c < lastChar && count[lastChar] > 0) {
                // delete lastchar
                sb.deleteCharAt(sb.length() - 1);
                // make it unvisited for re-visiting it again in the future
                visited[lastChar] = false;
                // check next last char
                lastChar = sb.charAt(sb.length() - 1);
            }
            // if there is a valid order, append it and mark it as visited
            sb.append((char) (c));
            visited[c] = true;
        }
        // return the answer
        return sb.substring(1).toString();
    }

    /**
     * @author: daohuei
     * @description: hash map with stack method
     * @time: O(n): go through the string once
     * @space: O(n): for visit and count array and the stack
     */
    public String removeDuplicateLetters(String s) {
        // check edge case
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] arr = s.toCharArray();
        // build count array... same as above
        int[] count = new int[256];
        boolean[] visited = new boolean[256];
        for (char c : arr) {
            count[c]++;
        }

        // use stack for comparing(peeking last char)
        Stack<Character> stack = new Stack<>();
        stack.push('0');
        for (char c : arr) {
            count[c]--;
            if (visited[c]) {
                continue;
            }
            // Compare with tail; reduce tail letter if necessary && applicable
            while (c < stack.peek() && count[stack.peek()] > 0) {
                visited[stack.pop()] = false;
            }
            stack.push(c);
            visited[c] = true;
        }
        // but this method need an additional loop for building string
        StringBuffer sb = new StringBuffer();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        return sb.substring(1).toString();
    }
}