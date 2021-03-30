package daohuei.leetcodelizard;

/*
 * 50. Pow(x, n)
 * Link: https://leetcode.com/problems/powx-n/
 */
public class MyPow {

    /**
     * @author: daohuei
     * @description: just multiply itself one by one
     * @time: O(n): just go through a for loop of n
     * @space: O(1): not using any
     */
    public double brutalMethod(double x, int n) {
        // pre-conditions
        if (x == -1) {
            if ((n & 1) != 0) {
                // if even
                return -1;
            } else {
                // if odd
                return 1;
            }
        } else if (x == 1) {
            return 1;
        } else if (n <= Integer.MIN_VALUE) {
            return 0;
        }

        // main algorithm
        double result = 1.0;
        int range = n;
        if (n < 0) {
            range = -n;
        }
        for (int i = 0; i < range; i++) {
            result = result * x;
        }
        if (n < 0) {
            // since the power is negative, the answer should be reciprocal
            result = 1 / result;
        }

        return result;
    }

    /**
     * @author: daohuei
     * @description: recur on half of the power number
     * @time: O(log(n)): consider the power enumeration as a binary tree, the time
     *        will be its height(levels)
     * @space: O(log(n)): same as above, the recursion will also become a binary
     *         tree with the n number of nodes
     */
    public double recursiveMethod(double x, int n) {
        // pre-conditions
        if (x == -1) {
            if ((n & 1) != 0) {
                // if even
                return -1;
            } else {
                // if odd
                return 1;
            }
        } else if (x == 1) {
            return 1;
        } else if (n <= Integer.MIN_VALUE) {
            return 0;
        }

        // main algorithm
        int range = n;
        if (n < 0) {
            range = -n;
        }
        double result = recurMyPow(x, range);
        if (n < 0) {
            // since the power is negative, the answer should be reciprocal
            result = 1 / result;
        }
        return result;
    }

    public double recurMyPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if ((n & 1) == 0) {
            // if even
            return recurMyPow(x * x, n / 2);
        } else {
            // if odd
            return recurMyPow(x * x, n / 2) * x;
        }
    }

    public double iterationMethod(double x, int n) {
        // pre-conditions
        if (x == -1) {
            if ((n & 1) != 0) {
                // if even
                return -1;
            } else {
                // if odd
                return 1;
            }
        } else if (x == 1) {
            return 1;
        } else if (n <= Integer.MIN_VALUE) {
            return 0;
        }

        // main algorithm
        int range = n;
        if (n < 0) {
            range = -n;
        }
        double result = iterMyPow(x, range);
        if (n < 0) {
            // since the power is negative, the answer should be reciprocal
            result = 1 / result;
        }
        return result;
    }

    /**
     * @author: daohuei
     * @description: recur on half of the power number
     * @time: O(log(n)): it will be the total digits of the binary of n
     * @space: O(1): not using any
     */
    public double iterMyPow(double x, int n) {
        double result = 1;
        while (n > 0) {
            // consider binary to decimal translation, 1 usually means need to add the power
            // with current digit, i.e. 10 = 1010 = 1*2^3 + 0*2^2 + 1*2^1 + 0*2^0
            if ((n & 1) == 1) {
                // add to the result (in the point of power, means multiply)
                result = result * x;
            }
            // keep going through the power
            x = x * x;
            // move one digit to the right
            n = n >> 1;
        }
        return result;
    }
}
