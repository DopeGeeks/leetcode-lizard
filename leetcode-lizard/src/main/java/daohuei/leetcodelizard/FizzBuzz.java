package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.List;

/*
 * 412. Fizz Buzz Link: https://leetcode.com/problems/fizz-buzz/
 */
public class FizzBuzz {
    /**
     * @author: daohuei
     * @description: just simply check if it is 2 or 3's multiple
     * @time: O(n)
     * @space: O(1)
     */
    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<String>();
        for (int i = 1; i <= n; i++) {
            StringBuilder result = new StringBuilder();
            if (i % 3 == 0)
                result.append("Fizz");
            if (i % 5 == 0)
                result.append("Buzz");
            if (result.length() > 0) {
                res.add(result.toString());
            } else {
                res.add(Integer.toString(i));
            }
        }
        return res;
    }
}