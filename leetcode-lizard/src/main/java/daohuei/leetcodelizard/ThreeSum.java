package daohuei.leetcodelizard;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
 * 15. 3Sum
 * Link: https://leetcode.com/problems/3sum/
 */
public class ThreeSum {
	/**
	 * @author: daohuei
	 * @description: 2 pointer
	 * @time: O(n^2): n = nums length
	 * @space: O(1): not using any
	 */
	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums); // sort it first
		List<List<Integer>> res = new LinkedList<>();
		// loop over the nums
		for (int i = 0; i < nums.length - 2; i++) {
			// make sure previous and current are different number
			if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
				// use 2 pointer, one from start and the other from end
				int lo = i + 1, hi = nums.length - 1, sum = 0 - nums[i];
				// while not meet
				while (lo < hi) {
					// if found it
					if (nums[lo] + nums[hi] == sum) {
						// add the answer
						res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
						// move lo forward until find a different value
						while (lo < hi && nums[lo] == nums[lo + 1])
							lo++;
						// move hi backward until find a different value
						while (lo < hi && nums[hi] == nums[hi - 1])
							hi--;
						// move to such value
						lo++;
						hi--;
					} else if (nums[lo] + nums[hi] < sum)
						lo++;
					else
						hi--;
					// if not enough for sum, move lo
					// if exceed the sum, move hi
				}
			}
		}
		return res;
	}
}