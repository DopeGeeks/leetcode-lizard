package daohuei.leetcodelizard;

import java.util.HashMap;

/*
 * 166. Fraction to Recurring Decimal
 * Link: https://leetcode.com/problems/fraction-to-recurring-decimal/
 */
public class FractionToDecimal {
    /**
     * @author: daohuei
     * @description: hashMap
     * @time: O(n): n = steps to finish all division
     * @space: O(k): k = number of repeated number for hashmap
     */
    public String hashMap(int numerator, int denominator) {
        long num = numerator;
        long den = denominator;
        String sign = "";
        // claim the sign first
        if (num > 0 && den < 0 || num < 0 && den > 0) {
            sign = "-";
        }
        // make them all positive
        num = Math.abs(num);
        den = Math.abs(den);
        // get the quotient
        long integer = num / den;
        // get the remainders
        num = num - integer * den;

        // for checking repeated number and storing its index
        HashMap<Long, Integer> map = new HashMap<>();
        int index = 0;
        // for storing decimal part
        String decimal = "";
        // for storing index of repeated decimal
        int repeatIndex = -1;
        while (num != 0) {
            // nums become larger than den
            num *= 10;
            // if this remainder appear before
            if (map.containsKey(num)) {
                // means this is the first place to have the repeated decimal
                repeatIndex = map.get(num);
                // break the loop
                break;
            }
            // store this remainder
            map.put(num, index);
            // get the quotient
            long decimalPlace = num / den;
            // add to the decimal string
            decimal = decimal + decimalPlace;
            // calculate the new remainder
            num = num - decimalPlace * den;
            index++;
        }
        // if there is the repeat decimal
        if (repeatIndex != -1) {
            String dec = decimal;
            return sign + integer + "." + dec.substring(0, repeatIndex) + "(" + dec.substring(repeatIndex) + ")";
        } else {
            // if no decimal
            if (decimal == "") {
                return sign + integer;
            } else {
                // if there is decimal
                return sign + integer + "." + decimal;
            }
        }
    }
}