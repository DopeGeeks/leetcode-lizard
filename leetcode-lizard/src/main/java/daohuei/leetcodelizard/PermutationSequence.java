package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * 60. Permutation Sequence
 * Link: https://leetcode.com/problems/permutation-sequence
 */
public class PermutationSequence {
    /**
     * @author: daohuei
     * @description: factorial method
     * @time: O(n): first get the total combination and locate the position of first
     *        character
     * @space: O(n): for the temp list
     */
    public String factorialMethod(int n, int k) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i <= n; i++)
            list.add(i);
        int fact = 1;
        // calculate factorial
        for (int i = 2; i <= n; i++)
            fact *= i;
        // change to the 0-indexed base
        k = k - 1;
        StringBuilder strBuilder = new StringBuilder();
        // go backward
        for (; n > 0; n--) {
            // update fact to be the partition that we are searching for k
            fact /= n;
            // append the number in the position of k / fact
            // (k / n!) = (idx / n)
            // and remove the number we found
            strBuilder.append(list.remove(k / fact));
            // update k with remainder
            k %= fact;
        }
        return strBuilder.toString();
    }

    /**
     * @author: daohuei
     * @description: factorial method with dp
     * @time: O(n): first get the total combination and locate the position of first
     *        character
     * @space: O(n): for the temp list and factorial dp array
     */
    public String getPermutation(int n, int k) {
        List<Integer> num = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++) {
            num.add(i);
        }
        // calculate the all combinations number with dp method
        int[] fact = new int[n];
        fact[0] = 1;
        for (int i = 1; i < n; i++) {
            fact[i] = i * fact[i - 1];
        }
        k = k - 1;
        StringBuilder sb = new StringBuilder();
        // same as above
        for (int i = n; i > 0; i--) {
            int index = k / fact[i - 1];
            k = k % fact[i - 1];
            sb.append(num.get(index));
            num.remove(index);
        }
        return sb.toString();
    }
}