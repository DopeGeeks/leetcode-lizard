package daohuei.leetcodelizard;

/*
 * 65. Valid Number
 * Link: https://leetcode.com/problems/valid-number/description/
 */
public class ValidNumber {
    /**
     * @author: daohuei
     * @description: iterations method
     * @time: O(n): single loop
     * @space: O(1): not using any
     */
    public boolean isNumber(String s) {
        // empty case
        if (s == null)
            return false;
        s = s.trim();
        if (s.length() == 0)
            return false;
        int i = 0;
        char[] ss = s.toCharArray();
        // if first is sign, i move to next
        if (ss[i] == '+' || ss[i] == '-')
            i++;
        // flag for number/dot/exp sign
        boolean isNum = false, isDot = false, isExp = false;
        // go through the strings
        for (; i < s.length(); i++) {
            char c = ss[i];
            // when is number
            if (Character.isDigit(c))
                isNum = true;
            // when is dot
            else if (c == '.') {
                // if dot already exist
                // or is part of exp, which should be false
                if (isDot || isExp)
                    return false;
                // set the flag
                isDot = true;
            }
            // when is exp sign
            else if (c == 'e' || c == 'E') {
                // if exp already exist or previous dont exist number
                if (isExp || !isNum)
                    return false;
                // set the flag
                isExp = true;
                // need to re-search number for e
                isNum = false;
            }
            // if is sign, need to check whether its previous char is e
            else if (c == '+' || c == '-') {
                if (i >= 1 && (s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E'))
                    return false;
            }
            // all other characters
            else
                return false;
        }
        return isNum;
    }
}