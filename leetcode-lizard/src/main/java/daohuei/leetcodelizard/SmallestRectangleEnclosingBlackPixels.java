package daohuei.leetcodelizard;

/*
 * 302. Smallest Rectangle Enclosing Black Pixels
 * Link: https://leetcode.com/problems/smallest-rectangle-enclosing-black-pixels/description/
 */
// An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. 
// The black pixels are connected, i.e., there is only one black region. 
// Pixels are connected horizontally and vertically. 
// Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

// For example, given the following image:

// [
//   "0010",
//   "0110",
//   "0100"
// ]
// and x = 0, y = 2,
// Return 6.
public class SmallestRectangleEnclosingBlackPixels {
    /**
     * @author: daohuei
     * @description: dfs
     * @time: O(k): all black pixels amount
     * @space: O(k): worst case the recursion stack may as deep as black pixels
     *         amount
     */
    int xmin = Integer.MAX_VALUE;
    int ymin = Integer.MAX_VALUE;
    int xmax = Integer.MIN_VALUE;
    int ymax = Integer.MIN_VALUE;

    public int dfsMethod(char[][] image, int x, int y) {
        // empty case
        if (image == null || image.length == 0 || image[0].length == 0)
            return 0;
        // dfs
        helper(image, x, y);
        return (xmax - xmin + 1) * (ymax - ymin + 1);
    }

    private void helper(char[][] image, int x, int y) {
        // out of bound
        if (x < 0 || y < 0 || x >= image.length || y >= image[0].length)
            return;

        // if black pixel
        if (image[x][y] == '1') {
            // set it white as visited
            image[x][y] = '0';
            // update the min and max value for x and y
            xmin = Math.min(x, xmin);
            xmax = Math.max(x, xmax);
            ymin = Math.min(y, ymin);
            ymax = Math.max(y, ymax);

            // dfs with 4 directions
            helper(image, x - 1, y);
            helper(image, x + 1, y);
            helper(image, x, y - 1);
            helper(image, x, y + 1);
        }
    }

    /**
     * @author: daohuei
     * @description: binary search method
     * @time: O(logmn): binary search on rows and columns
     * @space: O(1): not using any
     */
    public int binarySearch(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0].length == 0)
            return 0;
        int m = image.length, n = image[0].length;
        int left = searchColumns(image, 0, y, 0, m, true);
        int right = searchColumns(image, y + 1, n, 0, m, false);
        int top = searchRows(image, 0, x, left, right, true);
        int bottom = searchRows(image, x + 1, m, left, right, false);
        return (right - left) * (bottom - top);
    }

    private int searchColumns(char[][] image, int i, int j, int top, int bottom, boolean opt) {
        // binary search
        while (i < j) {
            // get the mid point
            int k = top, mid = i + (j - i) / 2;
            // search from top to down, until there is a black pixel
            while (k < bottom && image[k][mid] == '0')
                k++;
            // if k is not reaching the end => means there may be a min point in left
            // (or is reaching => means there may be a max point in right)
            if (k < bottom == opt) {
                // search left
                j = mid;
            } else {
                // search right
                i = mid + 1;
            }
        }
        return i;
    }

    private int searchRows(char[][] image, int i, int j, int left, int right, boolean opt) {
        while (i < j) {
            // same as searchCols
            int k = left, mid = i + (j - i) / 2;
            while (k < right && image[mid][k] == '0')
                k++;
            if (k < right == opt)
                j = mid;
            else
                i = mid + 1;
        }
        return i;
    }
}
