package daohuei.leetcodelizard;
/*
 * Author: @ballm06m06
 * Qusetion: Longest Common Prefix
 * Description: Write a function to find the longest common prefix string amongst an array of strings.
 * Example1:   Input: strs = ["flower","flow","flight"], Output: "fl"
 * Example2:   Input: strs = ["dog","racecar","car"], Output: ""
*/

public class LongestCommonPrefix{
    public static String longestCommonPrefix(String[] strs){
        if(strs.length == 0) return "";
        
        // Assume first element as final prefix, then compare to other elements  
        String prefix = strs[0];

        //Compare to next element, so start at index 1
        for(int i = 1; i < strs.length; i++){
            // indexOf() : This method returns the index within this string of the first occurrence of 
            // the specified character or -1, if the character does not occur.
            // e.g. "flow".indexOf("flower") will return -1, Cuz flow doesn't contain flower.
            
            while(strs[i].indexOf(prefix) != 0){
                // if strs[i] doesn't contain prefix, then eliminate the last character of the prefix
                prefix = prefix.substring(0, prefix.length()-1);
                if(prefix.isEmpty()) return "";
            }
        }
        System.out.println("Longest Common Prefix: " + prefix);
        return prefix;
    }
    public static void main(String[] args){
        LongestCommonPrefix.longestCommonPrefix(new String[]{"flower","flow","flight"});
    }
}