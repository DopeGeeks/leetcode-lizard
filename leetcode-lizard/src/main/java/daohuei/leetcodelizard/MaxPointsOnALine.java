package daohuei.leetcodelizard;

import java.util.HashMap;

/*
 * 149. Max Points on a Line
 * https://leetcode.com/problems/max-points-on-a-line/
 */
public class MaxPointsOnALine {
    /**
     * @author: daohuei
     * @description: hashmap method
     * @time: O(n^2): 2 layer loop for points
     * @space: O(n): the hashmap for all combinations
     */
    public int hashMap(int[][] points) {
        // when only 2 points or less
        if (points.length < 3) {
            return points.length;
        }
        int res = 0;
        // traverse every points
        for (int i = 0; i < points.length; i++) {
            // for counting duplicate points
            int duplicate = 0;
            // for storing max count of points on the same line
            int max = 0;
            // for storing every line associate to i point
            HashMap<String, Integer> map = new HashMap<>();
            // traverse every points other than i
            for (int j = i + 1; j < points.length; j++) {

                // get the numerator and denominator for the equation
                int x = points[j][0] - points[i][0];
                int y = points[j][1] - points[i][1];

                // if the j & i are the same point
                if (x == 0 && y == 0) {
                    duplicate++;
                    continue;
                }
                // for fraction reduction
                // get the greatest common divisor
                int gcd = gcd(x, y);
                x = x / gcd;
                y = y / gcd;

                // get a key string for this combination
                // which same line will have same combination after the fraction reduction
                String key = x + "@" + y;

                // add one point for this line
                map.put(key, map.getOrDefault(key, 0) + 1);
                // compare to current max
                max = Math.max(max, map.get(key));
            }
            // add the duplicate count and current point to the max count and compare
            res = Math.max(res, max + duplicate + 1);
        }
        return res;
    }

    // get the greatese common divisor
    public int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}