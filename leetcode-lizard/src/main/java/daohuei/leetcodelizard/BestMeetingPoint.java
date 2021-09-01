package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * 296. Best Meeting Point
 * Link: https://leetcode.com/problems/best-meeting-point/description//
 */
/*
A group of two or more people wants to meet and minimize the total travel distance. 
You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. 
The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

Example:

Input: 

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

Output: 6 

Explanation: Given three people living at (0,0), (0,4), and (2,2):
             The point (0,2) is an ideal meeting point, as the total travel distance 
             of 2+2+2=6 is minimal. So return 6.
*/
public class BestMeetingPoint {
    /**
     * @author: daohuei
     * @description: calculate distance with sorted row col list
     * @time: O(n): go through every cell
     * @space: O(k): for rows cols list, k = number of people
     */
    public int minTotalDistance(int[][] grid) {
        // empty case
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        List<Integer> rows = new ArrayList<Integer>();
        List<Integer> cols = new ArrayList<Integer>();

        // go through every cells
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // if it is the home
                if (grid[i][j] == 1) {
                    // add to the lists
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        // sort the columns
        Collections.sort(cols);
        // now rows and cols both sorted
        // need to find the best points for rows and cols
        return getMinDistance(rows) + getMinDistance(cols);
    }

    private int getMinDistance(List<Integer> lst) {
        int res = 0, left = 0, right = lst.size() - 1;
        // the travel distance is actually the distance of all head and tail points
        // since the ideal point will be the middle of the sorted list value
        while (left < right) {
            res += lst.get(right--) - lst.get(left++);
        }
        return res;
    }
}
