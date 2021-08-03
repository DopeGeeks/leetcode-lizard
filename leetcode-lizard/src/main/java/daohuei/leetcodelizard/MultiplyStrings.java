package daohuei.leetcodelizard;

/*
 * 43. Multiply Strings
 * Link: https://leetcode.com/problems/multiply-strings/description/
 */
public class MultiplyStrings {
    /**
     * @author: daohuei
     * @description: dp
     * @time: O(mn): for nested looping over num1 and num2
     * @space: O(m+n): for dp array
     */
    public String dpMethod(String num1, String num2) {

        if (num1 == null || num1.length() == 0)
            return num2;
        if (num2 == null || num2.length() == 0)
            return num1;

        int m = num1.length(), n = num2.length();
        // the dp array
        int[] rst = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // curr is the position of current combination digit in dp array
                // left is the curr's left digit, for storing carry
                int curr = i + j + 1, left = curr - 1;
                // multiply the numbers
                rst[curr] += toInt(num1, i) * toInt(num2, j);
                // add the carry to left
                rst[left] += rst[curr] / 10;
                // only get the remains of current
                rst[curr] %= 10;
            }
        }

        StringBuffer sb = new StringBuffer();
        for (int num : rst) {
            // skip all the heading zeros
            // if sb is empty and num is zero, we skip
            if (!(sb.length() == 0 && num == 0)) {
                sb.append(toChar(num));
            }
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }

    private char toChar(int num) {
        return (char) (num + '0');
    }

    private int toInt(String s, int x) {
        return s.charAt(x) - '0';
    }

    /**
     * @author: daohuei
     * @description: optimized dp
     * @time: O(mn): for nested looping over num1 and num2
     * @space: O(m+n): for dp array
     */
    public String optimizedDP(String num1, String num2) {
        if (num1 == null || num1.length() == 0)
            return num2;
        if (num2 == null || num2.length() == 0)
            return num1;

        // convert, reverse
        int m = num1.length(), n = num2.length();
        int[] rst = new int[m + n];

        // directly multiply digit together in the dp array
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                rst[i + j + 1] += toInt(num1, i) * toInt(num2, j);
            }
        }

        StringBuffer sb = new StringBuffer();
        // calculate carries after the multiplication from backward
        for (int i = rst.length - 1; i > 0; i--) {
            rst[i - 1] += rst[i] / 10;
            rst[i] %= 10;
            // and form the string from backward
            sb.insert(0, toChar(rst[i]));
        }
        // form the last string
        sb.insert(0, toChar(rst[0]));
        while (sb.length() > 1) {
            // remove heading zeroes
            if (sb.charAt(0) != '0')
                break;
            sb.deleteCharAt(0);
        }

        return sb.toString();
    }
}