package daohuei.leetcodelizard;

/*
 * 168. Excel Sheet Column Title
 * Link: https://leetcode.com/problems/excel-sheet-column-title/description/
 */
public class ExcelSheetColumnTitle {
    /**
     * @author: daohuei
     * @description: iteration
     * @time: O(n) times of division of columNumber with 26
     * @space: O(1): not using any
     */
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber > 0) {
            // get the digit number
            int c = columnNumber % 26;
            // if zero then should be 26
            if (c == 0) {
                c = 26;
                // since if fully division,
                // there will be an additional 1, which we do not want it
                // have to minus 1 for last digit removed
                columnNumber -= 1;
            }
            sb.insert(0, (char) ('A' + c - 1));
            columnNumber /= 26;
        }
        return sb.toString();
    }
}