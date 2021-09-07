package daohuei.leetcodelizard;

/*
 * 351. Android Unlock Patterns
 * Link: https://leetcode.com/problems/android-unlock-patterns/description/
 */
/*
Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, 
count the total number of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.
Rules for a valid pattern:
1. Each pattern must connect at least m keys and at most n keys.
2. All the keys must be distinct.
3. If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
4. The order of keys used matters.
Explanation:
| 1 | 2 | 3 |
| 4 | 5 | 6 |
| 7 | 8 | 9 |
Invalid move: 4 - 1 - 3 - 6
Line 1 - 3 passes through key 2 which had not been selected in the pattern.
Invalid move: 4 - 1 - 9 - 2
Line 1 - 9 passes through key 5 which had not been selected in the pattern.
Valid move: 2 - 4 - 1 - 3 - 6
Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern
Valid move: 6 - 5 - 4 - 1 - 9 - 2
Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.
Example:
Given m = 1, n = 1, return 9.
*/
public class AndroidUnlockPatterns {
    /**
     * @author: daohuei
     * @description: dfs + backtracking
     * @time: O(n^2): all numbers get their one chance until reaching n length
     * @space: O(n): the recursion stack at most n stacks(the upper bound)
     */

    int ans = 0;

    public int numberOfPatterns(int m, int n) {
        // Init the tables to check whether the line from curr to next is valid
        boolean[] used = new boolean[10];
        // the table contains the value that should not be skip
        // 1 -> 3 then need 2 between them to be valid
        int[][] skip = new int[10][10];
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[3][9] = skip[9][3] = 6;
        skip[7][9] = skip[9][7] = 8;
        skip[1][9] = skip[9][1] = skip[2][8] = skip[8][2] = 5;
        skip[3][7] = skip[7][3] = skip[4][6] = skip[6][4] = 5;
        used[0] = true;
        // !!: Need to be true because we need used[skip[curr][next]] to be true for
        // lines not passing through any num

        // Calculate the solutions using symatricity
        numberOfPatternsR(m, n, used, skip, 1, 1);
        numberOfPatternsR(m, n, used, skip, 1, 2);
        /*
         * | v | v | | | | | v | | | | | | | | | | | | | | | | v | | | | | | v | | | | |
         * | | | | | | | | v | v | | v | | |
         */
        // so we only need to multiply by 4 then we get other starting point except 5
        ans *= 4;

        // then we do again at 5
        numberOfPatternsR(m, n, used, skip, 1, 5);

        return ans;
    }

    private void numberOfPatternsR(int m, int n, boolean[] used, int[][] skip, int index, int curr) {
        // Add the ans if the length is between m and n (including m and n)
        if (index >= m) {
            ans++;
        }

        // Base case
        // if it reaches the upper bound
        if (index == n) {
            return;
        }

        // Select curr
        used[curr] = true;

        // Recursion explore every other number
        for (int next = 1; next <= 9; next++) {
            // if that number used or the skip number is not used => invalid and skip
            if (used[next] || !used[skip[curr][next]]) {
                // Invalid line from curr to next
                continue;
            }

            // recur and count the size of the key
            numberOfPatternsR(m, n, used, skip, index + 1, next);
        }

        // Unselect: Backtracking
        used[curr] = false;
    }
}
