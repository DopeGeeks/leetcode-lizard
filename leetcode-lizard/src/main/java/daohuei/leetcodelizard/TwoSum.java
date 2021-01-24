import java.util.*;

public class TwoSum {
    // Method 1
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    System.out.print(i + " " + j);
                    return new int[] { i, j };
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    // Method 2
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            // key should be nums[i] to avoid duplicate elements
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            // looking for the complement number
            int complement = target - nums[i];

            // if we contains the complement number AND to avoid using duplicate element
            if (map.containsKey(complement) && map.get(complement) != i) {

                // return the index
                return new int[] { i, map.get(complement) };
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        TwoSum ts = new TwoSum();
        ts.twoSum(new int[] { 2, 7, 11, 15, 17 }, 19);
    }
}
