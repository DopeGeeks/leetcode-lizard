package daohuei.leetcodelizard;

import java.util.Arrays;
import java.util.HashMap;

public class App {
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> complimentMap = new HashMap<Integer, Integer>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            // check if the current number has already appeared and matched one of the
            // compliments.
            if (complimentMap.containsKey(nums[i])) {
                // if so, this is the answer
                res[0] = complimentMap.get(nums[i]);
                res[1] = i;
                return res;
            }
            // get the compliment for each number
            int compliment = target - nums[i];
            // put into the map with key: compliment value and value: its index.
            complimentMap.put(compliment, i);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("I am a lizard which can help you pass the interview.\n");

        int[] result = twoSum(new int[] { 2, 7, 11, 15 }, 9);
        // Output: [0,1]
        // Output: Because nums[0] + nums[1] == 9, we return [0, 1].
        System.out.println(Arrays.toString(result));

        result = twoSum(new int[] { 3, 2, 4 }, 6);
        // Output: [1,2]
        System.out.println(Arrays.toString(result));

        result = twoSum(new int[] { 3, 3 }, 6);
        // Output: [0,1]
        System.out.println(Arrays.toString(result));
    }
}