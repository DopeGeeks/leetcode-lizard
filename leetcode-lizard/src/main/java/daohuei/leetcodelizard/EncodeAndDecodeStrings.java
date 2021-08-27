package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.List;

/*
 * 271. Encode and Decode Strings
 * Link: https://leetcode.com/problems/encode-and-decode-strings/description/
 */
/*
Design an algorithm to encode a list of strings to a string. 
The encoded string is then sent over the network and is decoded back to the original list of strings.
Machine 1 (sender) has the function:
string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}
Machine 2 (receiver) has the function:
vector<string> decode(string s) {
  //... your code
  return strs;
}
So Machine 1 does:
string encoded_string = encode(strs);
and Machine 2 does:
vector<string> strs2 = decode(encoded_string);
strs2 in Machine 2 should be the same as strs in Machine 1.
Implement the encode and decode methods.
Note:
The string may contain any possible characters out of 256 valid ascii characters. 
Your algorithm should be generalized enough to work on any possible characters.
Do not use class member/global/static variables to store states. 
Your encode and decode algorithms should be stateless.
Do not rely on any library method such as eval or serialize methods. 
You should implement your own encode/decode algorithm.
*/
public class EncodeAndDecodeStrings {
    /**
     * @author: daohuei
     * @description: use "#" as a symbol to split the string in the list
     * @time: O(n): just go through the string list
     * @space: O(1): not using any if not considering string buffer
     */

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        // empty case
        if (strs.size() == 0) {
            return "";
        }
        // string length + # + string then concat with the full list
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strs.size(); i++) {
            sb.append(strs.get(i).length() + "#" + strs.get(i));
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> strs = new ArrayList<>();
        // empty case
        if (s == null || s.length() == 0) {
            return strs;
        }
        // go through the string
        int start = 0;
        while (start < s.length()) {
            // target out the # position
            int ind = s.indexOf("#", start);
            // extract the length
            int leng = Integer.parseInt(s.substring(start, ind));
            // the end position of the string
            int end = ind + 1 + leng;
            // add the string into the results
            strs.add(s.substring(ind + 1, end));
            // update the start cursor
            start = end;
        }
        // return strings
        return strs;
    }
}
