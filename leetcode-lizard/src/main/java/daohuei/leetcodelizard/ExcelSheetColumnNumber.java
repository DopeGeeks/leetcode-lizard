package daohuei.leetcodelizard;

/*
 * 171. Excel Sheet Column Number
 * Link: https://leetcode.com/problems/excel-sheet-column-number/
 */
public class ExcelSheetColumnNumber {
	/**
	 * @author: daohuei
	 * @description: iteration
	 * @time: O(n): length of column title
	 * @space: O(1): not using any
	 */
	public int titleToNumber(String columnTitle) {
		char[] c = columnTitle.toCharArray();
		int res = 0;
		// 26-ary calculation
		for (int i = 0; i < c.length; i++) {
			res = res * 26 + (c[i] - 'A' + 1);
		}
		return res;
	}
}