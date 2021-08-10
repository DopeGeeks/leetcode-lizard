package daohuei.leetcodelizard;

/*
 * 96. Unique Binary Search Trees
 * Link: https://leetcode.com/problems/unique-binary-search-trees/description/
 */
/*
    f(0) = 1
    f(n) = f(0)*f(n-1) + f(1)*f(n-2) + ... + f(n-2)*f(1) + f(n-1)*f(0)
    If pick 1, left: 0, right: 2, 3. f(0) = 1, f(2) with value 2,3, there are two ways of orientation, which makes f(2) = 2.
    Therefore, f(0) * f(2) = 2;
    If pick 2, left: 1, right:3, there is only f(1)*f(1) = 1
    If pick 3, left: 1, 2, right: 0, f(2) * f(0) = 2
    So add all possible conditions together: 2 + 1 + 2 = 5
 */
public class UniqueBinarySearchTrees {
    /**
     * @author: daohuei
     * @description: dp: Catalan number
     * @time: O(n^2): for solving Catalan number
     * @space: O(n): for dp array
     */
    public int numTrees(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] numTree = new int[n + 1];
        numTree[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                numTree[i] += numTree[j] * numTree[i - j - 1];
            }
        }
        return numTree[n];
    }
}