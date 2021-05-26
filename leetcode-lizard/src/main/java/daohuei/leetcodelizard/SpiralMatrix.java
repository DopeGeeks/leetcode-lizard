package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.List;

/*
 * 54. Spiral Matrix
 * Link: https://leetcode.com/problems/rotate-image/
 */
public class SpiralMatrix {

    /**
     * @author: daohuei
     * @description: layer method
     * @time: O(n) search layer by layer, so we only need max(m,n) iters
     * @space: O(1) not using any
     */
    public List<Integer> layerMethod(int[][] matrix) {
        List<Integer> ans = new ArrayList<Integer>();
        if (matrix.length == 0)
            return ans;
        // start/end index for row search
        int rowStart = 0;
        int rowEnd = matrix.length - 1;

        // start/end index for column search
        int columnStart = 0;
        int columnEnd = matrix[0].length - 1;

        while (rowStart <= rowEnd && columnStart <= columnEnd) {
            // search columns of rowStart
            for (int c = columnStart; c <= columnEnd; c++)
                ans.add(matrix[rowStart][c]);
            // search rows of columnEnd
            for (int r = rowStart + 1; r <= rowEnd; r++)
                ans.add(matrix[r][columnEnd]);
            // if both the column/row index of start and end not meet yet
            // moving forward
            if (rowStart < rowEnd && columnStart < columnEnd) {
                // search columns of rowEnd
                for (int c = columnEnd - 1; c > columnStart; c--)
                    ans.add(matrix[rowEnd][c]);
                // search rows of columnStart
                for (int r = rowEnd; r > rowStart; r--)
                    ans.add(matrix[r][columnStart]);
            }
            // move to next inner layer
            rowStart++;
            rowEnd--;
            columnStart++;
            columnEnd--;

        }
        return ans;
    }
}
