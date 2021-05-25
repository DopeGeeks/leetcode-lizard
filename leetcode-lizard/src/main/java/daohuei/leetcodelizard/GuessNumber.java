package daohuei.leetcodelizard;

/*
 * 374. Guess Number Higher or Lower
 * Link: https://leetcode.com/problems/guess-number-higher-or-lower/
 */
public class GuessNumber {

    /**
     * Forward declaration of guess API.
     * 
     * @param num your guess
     * @return -1 if num is lower than the guess number 1 if num is higher than the
     *         guess number otherwise return 0 int guess(int num);
     */
    private int guess(int num) {
        return 0;
    }

    /**
     * @author: daohuei
     * @description: binary search
     * @time: O(log(n)) since using binary search
     * @space: O(1) not using any
     */
    public int guessNumber(int n) {
        int low = 1;
        int high = n;
        while (low <= high) {
            // for prevent overflow
            int mid = low + (high - low) / 2;
            // guess with middle value
            int res = guess(mid);
            // if correct
            if (res == 0)
                return mid;
            // if answer smaller
            else if (res < 0)
                // make high lower
                high = mid - 1;
            // if larger
            else
                // make low larger
                low = mid + 1;
        }
        return -1;
    }
}
