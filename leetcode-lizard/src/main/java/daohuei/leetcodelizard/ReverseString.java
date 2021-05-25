package daohuei.leetcodelizard;

/*
 * 344. Reverse String
 * Link: https://leetcode.com/problems/reverse-string/
 */
public class ReverseString {

    /**
     * @author: daohuei
     * @description: basically just swap head and tail and then move forward
     * @time: O(n): n = total characters. (actually n/2)
     * @space: O(1): not using any
     */
    public void reverseString(char[] s) {
        int tail = s.length - 1; // start point of tail, which is the last item
        // only iters through half of the array since we move head and tail in the same
        // time
        for (int head = 0; head < s.length / 2; head++) {
            // temporary store the head value
            char tmp = s[head];
            // assign tail to the head
            s[head] = s[tail];
            // assign previously stored head to the tail
            s[tail] = tmp;
            // the tail move one step backward
            tail--;
        }
    }
}
