package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.List;

/*
 * 293. Flip Game
 * Link: https://leetcode.com/problems/flip-game/description/
 */
/*
You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.
Write a function to compute all possible states of the string after one valid move.
For example, given s = "++++", after one move, it may become one of the following states:
[
    "--++",
    "+--+",
    "++--"
]
*/
public class FlipGame {

    /**
     * @author: daohuei
     * @description: using string buffer to find index
     * @time: O(n): looping through the string only
     * @space: O(n): for string buffer
     */
    public List<String> replaceMethod(String s) {
        List<String> rst = new ArrayList<String>();
        if (s == null || s.length() == 0) {
            return rst;
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        StringBuffer sb = new StringBuffer(s);
        // recording all the start position of ++
        while (sb.indexOf("++") != -1) {
            int index = sb.indexOf("++");
            list.add(index);
            // replace the first "+" with "*"
            sb.replace(index, index + 1, "*");
        }
        // reconstruct the converted state of the string
        for (int index : list) {
            rst.add(s.substring(0, index) + "--" + s.substring(index + 2));
        }
        return rst;
    }

    /**
     * @author: daohuei
     * @description: 2 pointers searching
     * @time: O(n): looping through the string only
     * @space: O(n): for char array
     */
    public List<String> generatePossibleNextMoves(String s) {
        List<String> rst = new ArrayList<String>();
        if (s == null || s.length() < 1) {
            return rst;
        }
        char[] arr = s.toCharArray();
        search('+', '-', arr, rst);
        return rst;
    }

    // 2 pointer searching
    public static void search(char target, char replace, char[] arr, List<String> rst) {
        int p1 = 0;
        int p2 = 1;
        while (p2 <= arr.length - 1) {
            // if both are the target ("+")
            if (arr[p1] == target && arr[p2] == target) {
                // replace the value
                arr[p1] = replace;
                arr[p2] = replace;
                rst.add(new String(arr));
                // replace back the target
                arr[p1] = target;
                arr[p2] = target;
            }
            // move forward
            p1++;
            p2++;
        }
    }
}
