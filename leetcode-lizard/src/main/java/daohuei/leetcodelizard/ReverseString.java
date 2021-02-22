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
    public void reverseStringByDaoHuei(char[] s) {
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

    /*
     * Author: @ballm06m06 Qusetion: Reverse String Description: Write a function
     * that reverses a string. The input string is given as an array of characters
     * char[]. Do not allocate extra space for another array, you must do this by
     * modifying the input array in-place with O(1) extra memory.
     * 
     */
    public void reverseString(char[] s) {
        // Using two-pointer technique
        // Time Complexity: O(n), Space Complexity: O(1)

        int j = s.length - 1;

        for (int i = 0; i < s.length / 2; i++) {
            char tmp = s[i];
            s[i] = s[j];
            s[j--] = tmp;
        }
    }

}
