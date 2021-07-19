package daohuei.leetcodelizard;

/*
 * 12. Integer to Roman
 * Link: https://leetcode.com/problems/integer-to-roman
 */
public class IntegerToRoman {
    /**
     * @author: daohuei
     * @description: method
     * @time: O(n): n is num's digit numbers
     * @space: O(1): not using any
     */
    public String intToRoman(int num) {
        String r = "";
        int count = 0;
        while (num != 0) {
            // get the last digit
            int pop = num % 10;
            // get the roman of that digit with current count
            r = getRoman(pop, count) + r;
            // count -> current digit number
            count++;
            num /= 10;
        }
        return r;
    }

    public String getRoman(int num, int count) {
        // 1,10,100,1000
        char[] ten = { 'I', 'X', 'C', 'M' };
        // 5,50,500
        char[] five = { 'V', 'L', 'D' };
        String r = "";
        // below 3 cases
        if (num <= 3) {
            // if the number is not zero
            while (num != 0) {
                r += ten[count];
                num--;
            }
        }
        // 4 case
        if (num == 4) {
            // "IV", "XL", "CD"...
            r = (ten[count] + "") + (five[count] + "");
        }
        // 5 case
        if (num == 5) {
            r = five[count] + "";
        }
        if (num > 5 && num < 9) {
            // "VI"->6 ...
            r = five[count] + "";
            num -= 5;
            while (num != 0) {
                r += ten[count];
                num--;
            }
        }
        if (num == 9) {
            // IX...
            r = (ten[count] + "") + (ten[count + 1] + "");
        }
        return r;
    }
}