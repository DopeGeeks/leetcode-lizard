package daohuei.leetcodelizard;

/*
 * 273. Integer to English Words
 * Link: https://leetcode.com/problems/integer-to-english-words/description/    
 */
public class IntegerToEnglishWords {
    /**
     * @author: daohuei
     * @description: solving edge cases and mapping
     * @time: O(1): just go through 4 thousand digits
     * @space: O(1): not using any
     */
    // 3 digits LEVEL
    public String[] v1 = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven",
            "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
    public String[] v2 = { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
    public String[] v3 = { "", "Thousand", "Million", "Billion" };

    public String numberToWords(int num) {
        // negative invalid case
        if (num < 0)
            return "";
        // zero case
        if (num == 0)
            return "Zero";

        StringBuffer sb = new StringBuffer();
        // iterate 4 thousand digits
        for (int i = 0; i < 4; i++) {
            // Obtain smaller 3-digit section
            int partial = num % 1000;
            // Append suffix depending on i, where v3[0] = "";
            // if partial is larger than 0: means their is number needed to be parsed
            if (partial > 0) {
                // parse the partial number
                sb.insert(0, calcPartialNumber(partial) + " " + v3[i] + " ");
            }
            num /= 1000;
        }

        return sb.toString().trim();
    }

    private String calcPartialNumber(int num) {
        StringBuffer sb = new StringBuffer();
        // if more than a hundred
        if (num >= 100) {
            // get the hundred number
            int hund = num / 100;
            // append hundred string
            sb.append(v1[hund] + " Hundred ");
            // get the rest tenth and last digit
            num = num % 100;
        }
        // if less than 20, directly return v1
        if (num < 20)
            sb.append(v1[num] + " ");
        // else need to extract tenth digit number
        else {
            // get the tenth number
            int numTens = num / 10;
            // get last digit
            int numDigit = num % 10;
            // parse it
            sb.append(v2[numTens] + " " + v1[numDigit] + " ");
        }

        return sb.toString().trim();
    }
}