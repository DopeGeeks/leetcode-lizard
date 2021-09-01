package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 301. Remove Invalid Parentheses
 * Link: https://leetcode.com/problems/remove-invalid-parentheses/description/
 */
public class RemoveInvalidParentheses {
    /**
     * @author: daohuei
     * @description: dfs
     * @time: O(n^2): invalid position amount k <= n and all string length n
     * @space: O(n): for recursion stack
     */
    public List<String> dfsMethod(String s) {
        List<String> rst = new ArrayList<>();
        if (s == null)
            return rst;
        dfs(rst, s, 0, 0, '(', ')');
        return rst;
    }

    private void dfs(List<String> rst, String s, int i, int j, char open, char close) {
        // go through every string
        for (int count = 0; i < s.length(); i++) {
            // count the open char and decrease is is close char
            count += s.charAt(i) == open ? 1 : 0;
            count -= s.charAt(i) == close ? 1 : 0;
            // if count is negative, means there is a redundant close
            // When invalid occurs, correct it right away: minimum correction
            if (count < 0) {
                // get the last position of done with searching
                int init = j;
                for (; j <= i; j++) {
                    // if the place is close and it is at the starting point or not equal to the
                    // previous char
                    if (s.charAt(j) == close && (j == init || s.charAt(j) != s.charAt(j - 1))) {
                        // remove the j and recur with the new starting point
                        dfs(rst, s.substring(0, j) + s.substring(j + 1), i, j, open, close);
                    }
                }
                // break this round
                return;
            }
        }
        // if the string valid, it will pass the loop
        // reverse the string
        String reversed = new StringBuffer(s).reverse().toString();
        // we need to test it again with reversed version
        if (open == '(') {
            dfs(rst, reversed, 0, 0, close, open);
        }
        // if reversed version is passed, then we reverse back and add to the answer
        else {
            rst.add(reversed);
        }
    }

    /**
     * @author: daohuei
     * @description: bfs
     * @time: O(n^2): invalid position amount k <= n and all string length n
     * @space: O(n): for queue
     */
    public List<String> bfsMethod(String s) {

        List<String> rst = new ArrayList<>();
        // validate original string first
        if (validate(s)) {
            // if valid, no need to test furthermore
            rst.add(s);
            return rst;
        }
        ;

        Queue<Node> q = new LinkedList<>();
        // assume a new start, the last must be ')'
        q.offer(new Node(s, 0, ')'));

        // bfs
        while (!q.isEmpty()) {
            // get the node
            Node node = q.poll();
            // get the string from the node
            String ss = node.s;
            // Attempt to remove 1 char after failing 3 tests
            for (int i = node.index; i < ss.length(); i++) {
                char c = ss.charAt(i);
                // Test1: skip regular char
                if (c != '(' && c != ')')
                    continue;
                // Test2: if redundant paren, do 1 is enough. skip adjacent ones.
                if (i != node.index && ss.charAt(i - 1) == c)
                    continue;
                // Test3: if last removed extra paren is '(', the next ')' must be a valid pair
                if (node.lastRemoved == '(' && c == ')')
                    continue;
                // Generate sub and test it
                String sub = ss.substring(0, i) + ss.substring(i + 1);
                // if valid, add to the result
                if (validate(sub))
                    rst.add(sub);
                // if invalid and the result is empty, add next to search to the queue
                // since if is not empty, means we already find the least steps, no need to
                // search deeper
                // current nodes in the queue are candidates for the least steps
                else if (rst.isEmpty())
                    q.offer(new Node(sub, i, c));
            }
        }

        return rst;
    }

    // validate by testing open and close parens
    private boolean validate(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            count += c == '(' ? 1 : 0;
            count -= c == ')' ? 1 : 0;
            if (count < 0)
                return false;
        }
        return count == 0;
    }

    // the node for contains information about last recorded node
    private class Node {
        // its containing string
        String s;
        // start index
        int index;
        // Smart way to maintain relationship with last recorded character
        char lastRemoved;

        public Node(String s, int index, char lastRemoved) {
            this.s = s;
            this.index = index;
            this.lastRemoved = lastRemoved;
        }
    }
}