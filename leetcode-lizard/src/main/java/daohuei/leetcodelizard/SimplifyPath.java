package daohuei.leetcodelizard;

import java.util.Stack;

/*
 * 71. Simplify Path
 * Link: https://leetcode.com/problems/simplify-path/description/
 */
public class SimplifyPath {
    /**
     * @author: daohuei
     * @description: stack method
     * @time: O(n): amount of dir
     * @space: O(n): for the stack
     */
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        // split every part by "/"
        String[] parts = path.split("/");
        for (String s : parts) {
            // if empty and . means not changing
            if (s.isEmpty() || s.equals("."))
                continue;
            // if ..
            if (s.equals("..")) {
                // if not empty, pop last out
                // which means leave the current dir
                // move to upper one
                if (!stack.isEmpty())
                    stack.pop();
                continue;
            }
            // push dir into the stack
            stack.push(s);
        }

        // build output
        StringBuffer sb = new StringBuffer();
        // if not empty, add dir backward
        while (!stack.isEmpty())
            sb.insert(0, "/" + stack.pop());
        // if no string in sb, means empty => go with root
        if (sb.length() == 0)
            sb.append("/");

        return sb.toString();
    }
}