package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * 17. Letter Combinations of a Phone Number
 * Link: https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 */
public class LetterCombinationsOfPhoneNumber {
	/**
	 * @author: daohuei
	 * @description: iterations
	 * @time: O(n): length of digits
	 * @space: O(1): hashmap takes contant space
	 */
	public List<String> iter(String digits) {
		LinkedList<String> ans = new LinkedList<String>();
		if (digits.isEmpty())
			return ans;
		// create a mapping from index(phone number) to represented string
		String[] mapping = new String[] { "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
		ans.add("");
		// traverse digits
		for (int i = 0; i < digits.length(); i++) {
			// get number from digit char
			int x = Character.getNumericValue(digits.charAt(i));
			// check the first answer, if less than the length with one
			while (ans.peek().length() == i) {
				// pop it out
				String t = ans.remove();
				// get the represented string
				for (char s : mapping[x].toCharArray())
					// append it and make it a new answer
					ans.add(t + s);
			}
		}
		return ans;

	}

	/**
	 * @author: daohuei
	 * @description: recursion
	 * @time: O(n): length of digits
	 * @space: O(n): for recursion stack
	 */
	private static final String[] KEYS = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

	public List<String> recur(String digits) {
		if (digits.equals("")) {
			return new ArrayList<String>();
		}
		List<String> ret = new LinkedList<String>();
		combination("", digits, 0, ret);
		return ret;

	}

	private void combination(String prefix, String digits, int offset, List<String> ret) {
		// offset is the index that should be add the char
		if (offset == digits.length()) {
			ret.add(prefix);
			return;
		}
		// get the letters from the key map
		String letters = KEYS[(digits.charAt(offset) - '0')];
		// recur with new combination
		for (int i = 0; i < letters.length(); i++) {
			combination(prefix + letters.charAt(i), digits, offset + 1, ret);
		}
	}
}