/*
 * Author: @ballm06m06
 * Qusetion: Longest Substring Without Repeating Characters
 * Description: Given a string s, find the length of the longest substring without repeating characters.
 *
 *
 * Time Complexity: O(),
 * Space Complexity: O()
 *
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * pwwkew
 * l
 * r      set[w]
*/
import java.util.*;

public class LongestSubstringWithoutRepeatingCharacters {
    //69.83%, 32.57%
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int L = 0, R = 0, max = 0;
        char[] arr = s.toCharArray();

        while(R < arr.length){
            while(set.contains(arr[R])){
                set.remove(arr[L]);
                L++;
            }
            set.add(arr[R]);
            max = Math.max(max, R - L + 1);
            R++;
        }
        return max;
    }
    //  82.01%, 83.01%  想不通幹
    public static int lengthOfLongestSubstring1(String s) {
        if (s.length() == 0)
            return 0;
        
        Map<Character, Integer> hashmap = new HashMap<>();
        int start = 0, max = 0;

        for(int i = 0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(hashmap.containsKey(ch)){
                max = Math.max(max, i-start);
                start = Math.max(start, hashmap.get(ch)+1);
            }
            hashmap.put(ch, i);
        }
        max = Math.max(max,s.length()-start);
        return max;
    }
    
    // pwwkew
    /*      i
     *    s
     * 
     * {p:0,w:5,k:3,e:4}  max:2
    */
    
    
    
    
    
    
    
    // It's looking for substring not subsequence
    // public static int lengthOfLongestSubstring(String s) {
    //     HashMap<Character, Integer> hashmap = new HashMap<Character, Integer>();
    //     int length = 0;
        
    //     for(int i = 0; i<s.length(); i++){
    //         hashmap.put(s.charAt(i), i);
    //     }
    //     for(Map.Entry m : hashmap.entrySet()){
    //         length++;
    //     }

    //     return length;
    // }

    public static void main(String[] args){
        String s = "pwwkew";
        System.out.print(lengthOfLongestSubstring(s));
    }
}
