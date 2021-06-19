package daohuei.leetcodelizard;

/*
 * 67. Add Binary
 * Link: https://leetcode.com/problems/add-binary/
 */
public class AddBinary {
    /**
     * @author: daohuei
     * @description: iteration
     * @time: O(max(m,n)): m, n = length of string a,b
     * @space: O(1): not using any
     */
    public String iteration(String a, String b) {

        int m = a.length();
        int n = b.length();
        char[] sum = new char[Math.max(m, n) + 1];
        int carry = 0, index = sum.length - 1;
        for (int i = m - 1, j = n - 1; i >= 0 || j >= 0; i--, j--) {
            // get their number as integer
            int aNum = i < 0 ? 0 : a.charAt(i) - '0';
            int bNum = j < 0 ? 0 : b.charAt(j) - '0';

            int s = aNum + bNum + carry;
            // get sum's remainder of 2
            // and put into the sum array
            sum[index--] = (char) (s % 2 + '0');
            // get the carry
            carry = s / 2;
        }
        // put the carry to the last digit (left most)
        sum[index] = (char) ('0' + carry);
        // convert sum to the string will be the answer
        return carry == 0 ? new String(sum, 1, sum.length - 1) : new String(sum);
    }
}