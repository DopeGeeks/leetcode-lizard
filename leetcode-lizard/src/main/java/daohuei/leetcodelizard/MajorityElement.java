package daohuei.leetcodelizard;

import java.util.HashMap;

/*
 * 169. Majority Element
 * Link: https://leetcode.com/problems/majority-element/
 */
public class MajorityElement {
	/**
	 * @author: daohuei
	 * @description: hashmap
	 * @time: O(n) nums length
	 * @space: O(n) hashmap for all elements
	 */
	public int hashMap(int[] nums) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int n = nums.length;
		for (int i = 0; i < nums.length; i++) {
			// get the previous count or zero
			int before = map.getOrDefault(nums[i], 0);
			// if reaching half
			if (before == n / 2) {
				// return answer
				return nums[i];
			}
			// if not then count one and put into the hashmap
			map.put(nums[i], before + 1);
		}
		// none of them over half amount
		return -1;
	}

	/**
	 * @author: daohuei
	 * @description: bit operation
	 * @time: O(n) for j go through n (considering 32 bits as constant)
	 * @space: O(1) none
	 */
	public int bitOperation(int[] nums) {
		int majority = 0;
		int n = nums.length;
		// go through every bits
		for (int i = 0, mask = 1; i < 32; i++, mask <<= 1) {
			int bits = 0;
			// the mask can show whether the current bit is 1
			// every iter it will move one digit to the left
			for (int j = 0; j < n; j++) {
				// check if j digit is 1
				if ((mask & nums[j]) == mask) {
					// if so, count it
					bits++;
				}
			}
			// check if it is over half
			if (bits > n / 2) {
				// get this i bit as one
				// or
				majority |= mask;
			}
		}
		return majority;
	}

	/**
	 * @author: daohuei
	 * @description: Boyer-Moore Majority Vote Algorithm
	 * @time: O(n) for go through nums
	 * @space: O(1) none
	 */
	public int vote(int[] nums) {
		int count = 1;
		int group = nums[0];
		for (int i = 1; i < nums.length; i++) {
			// current is empty in line
			if (count == 0) {
				// count 1
				count = 1;
				// get the current group
				group = nums[i];
				continue;
			}
			// if it is the current guy, add one
			if (nums[i] == group) {
				count++;
				// if other people, decrease count 1, but not changing group
			} else {
				count--;
			}
		}
		// the one survive will be the majority
		return group;
	}
}