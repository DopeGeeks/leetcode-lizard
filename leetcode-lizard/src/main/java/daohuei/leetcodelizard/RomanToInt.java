package daohuei.leetcodelizard;

/*
 * Question: Roman to Integer
 * Description: Roman numerals are represented 
 * by seven different symbols: I, V, X, L, C, D and M. 
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 * I can be placed before V (5) and X (10) to make 4 and 9. 
 * X can be placed before L (50) and C (100) to make 40 and 90. 
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer. 
 * Constraints:
 * 1 <= s.length <= 15
 * s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
 * It is guaranteed that s is a valid roman numeral in the range [1, 3999].
 *
 * Example 1:
 * Input: s = "III"
 * Output: 3
 * 
 * Example 2:
 * Input: s = "IV"
 * Output: 4
 * 
 * Example 3:
 * Input: s = "IX"
 * Output: 9
 * 
 * Example 4:
 * Input: s = "LVIII"
 * Output: 58
 * Explanation: L = 50, V= 5, III = 3.
 * 
 * Example 5:
 * Input: s = "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 */
public class RomanToInt {
    /*
     * @Author: daohuei
     * 
     * @Function: convert roman character to integer (It's like a map function)
     * 
     * @Remark: utils only
     */
    private static int convertRoman(Character c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    /*
     * @Description: We go through all the characters and use switch method to fetch
     * the value. Then we will sum it all together. But we still have to remind that
     * the character which is smaller than the latter one will be a negative value.
     * Such as `IV` will become `-1 + 5 = 4`
     * 
     * @Time: O(n),since we only go through the string once, n = s.length()
     * 
     * @Space: O(1), we only store the switch map but it is a constant.
     */
    public static int romanToInt(String s) {
        int ans = 0, n = s.length();

        for (int i = 0; i < n; i++) {
            int current = convertRoman(s.charAt(i));
            int next = 0;
            if (i != n - 1) {
                // If the iter is not at the last element
                next = convertRoman(s.charAt(i + 1));
            }
            if (current < next) {
                // if the next character's value is larger than current's
                // which means current may be a minus number
                ans -= current;
            } else {
                // normal situation is for normal addition
                ans += current;
            }
        }
        return ans;
    }

}
