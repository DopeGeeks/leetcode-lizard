package daohuei.leetcodelizard;

/*
 * 38. Count and Say
 * 
 * The count-and-say sequence is a sequence of digit strings defined by the
 * recursive formula:
 *      countAndSay(1) = "1" 
 *      countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1), 
 *          which is then converted into a different digit string. 
 * 
 * To determine how you "say" a digit string, split it into the minimal
 * number of groups so that each group is a contiguous section all of the same
 * character. Then for each group, say the number of characters, then say the
 * character. To convert the saying into a digit string, replace the counts with
 * a number and concatenate every saying.
 * 
 * Constraints: 1 <= n <= 30
 * 
 * Example 1:
 * Input: n = 1 
 * Output: "1" 
 * Explanation: This is the base case.
 * 
 * Example 2:
 * Input: n = 4
 * Output: "1211"
 * Explanation: 
 * countAndSay(1) = "1" 
 * countAndSay(2) = say "1" = one 1 = "11"
 * countAndSay(3) = say "11" = two 1's = "21" 
 * countAndSay(4) = say "21" = one 2 + one 1 = "12" + "11" = "1211"
 */
public class CountAndSay {
    /**
     * @author: daohuei
     * @description: we use a nested loop, outer is for 1 to n, inner is for chars
     *               in results of 1 to n.
     * @time: O(n*m): n = the input n, m = length of every output
     * @space: O(m): m is same as above
     */
    public static String countAndSay(int n) {
        String result = "1";
        // cause we do not need the first iter, it is the initialization step
        for (int i = 2; i <= n; i++) {
            // string builder got better performance than
            // res = res + "" + count + "" + currentChar;
            StringBuilder resultBuilder = new StringBuilder();
            // start with the first char
            char currentChar = result.charAt(0);
            // count start with 1
            int count = 1;
            for (int j = 1; j < result.length(); j++) {
                // start with the second char
                if (currentChar == result.charAt(j)) {
                    // if the current char equal to the one we are finding
                    // then count add 1 and keep looking for next
                    count++;
                } else {
                    // if we found different one
                    // then we do the saying as [count][number]
                    resultBuilder.append(count).append(currentChar);
                    // now make the different one as the one we are looking for
                    currentChar = result.charAt(j);
                    // reset the count number
                    count = 1;
                }
            }
            // since loop will end before the last char being said,
            // we have to do the saying last time
            resultBuilder.append(count).append(currentChar);
            // then output the result of current n
            result = resultBuilder.toString();
        }
        return result;
    }
}
