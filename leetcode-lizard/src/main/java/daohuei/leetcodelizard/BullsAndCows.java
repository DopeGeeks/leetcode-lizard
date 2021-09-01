package daohuei.leetcodelizard;

/*
 * 299. Bulls and Cows
 * Link: https://leetcode.com/problems/bulls-and-cows/
 */
public class BullsAndCows {
    /**
     * @author: daohuei
     * @description: hash map(either hashmap or integer array is find since we only
     *               got keys from 0-9)
     * @time: O(n): loop once
     * @space: O(n): for hashmap integer array
     */
    public String getHint(String secret, String guess) {
        // empty case
        if (secret == null || guess == null || secret.length() == 0 || guess.length() == 0) {
            return "0A0B";
        }

        int[] nums = new int[10]; // 0 - 9
        int bulls = 0, cows = 0;

        // go through the characters
        for (int i = 0; i < secret.length(); i++) {
            // secret integer
            int s = Character.getNumericValue(secret.charAt(i));
            // guess integer
            int g = Character.getNumericValue(guess.charAt(i));
            // if they are same, bull count 1
            if (s == g) {
                bulls++;
            } else {
                // if secret number is less than 0, means there has been a guess number matched
                // before: count cow
                if (nums[s] < 0)
                    cows++;
                // if guess number is larger than 0, means there has been a same secret number
                // appeared before: count cow
                if (nums[g] > 0)
                    cows++;
                // count the secret number
                nums[s]++;
                // remove the guess number
                nums[g]--;
            }
        }

        return String.format("%sA%sB", bulls, cows);
    }
}