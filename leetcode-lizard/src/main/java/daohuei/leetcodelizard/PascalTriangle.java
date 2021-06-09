package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.List;

/*
 * 118. Pascal's Triangle
 * Link: https://leetcode.com/problems/pascals-triangle/submissions/
 */
public class PascalTriangle {

    /**
     * @author: daohuei
     * @description: nested loop
     * @time: O(n^2) n is numRows
     * @space: O(n) for getting the last row
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        // loop over rows num
        for (int i = 0; i < numRows; i++) {
            // sub answer
            List<Integer> sub = new ArrayList<>();
            // loop for each col
            for (int j = 0; j <= i; j++) {
                // if first one or last one
                if (j == 0 || j == i) {
                    sub.add(1);
                } else {
                    // get the last row
                    List<Integer> last = ans.get(i - 1);
                    // sum the current and previous index of last row together
                    sub.add(last.get(j - 1) + last.get(j));
                }

            }
            ans.add(sub);
        }
        return ans;
    }
}