package daohuei.leetcodelizard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
 * 288. Unique Word Abbreviation
 * Link: https://leetcode.com/problems/unique-word-abbreviation/description/
 */
/*
An abbreviation of a word follows the form <first letter><number><last letter>. 
Below are some examples of word abbreviations:
a) it                      --> it    (no abbreviation)
     1
     ↓
b) d|o|g                   --> d1g
              1    1  1
     1---5----0----5--8
     ↓   ↓    ↓    ↓  ↓    
c) i|nternationalizatio|n  --> i18n
              1
     1---5----0
     ↓   ↓    ↓
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.
Example:
Given dictionary = [ "deer", "door", "cake", "card" ]
isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true
*/
public class UniqueWordAbbreviation {
    class ValidWordAbbr {
        // key: abbr, value: hashset of the original words
        HashMap<String, Set<String>> map = new HashMap<>();

        public ValidWordAbbr(String[] dict) {
            // empty case
            if (dict == null || dict.length == 0) {
                return;
            }
            // for all words in the dictionary
            for (String word : dict) {
                // convert to abbreviation
                String abbr = convertToAbbr(word);
                // put into the hashmap as key vs hashset of original words
                if (!map.containsKey(abbr)) {
                    map.put(abbr, new HashSet<String>());
                }
                // add original words to the hashset
                map.get(abbr).add(word);
            }
        }

        public boolean isUnique(String word) {
            // empty case
            if (word == null) {
                return false;
            }
            // get the abbreviation
            String abbr = convertToAbbr(word);
            // if abbreviation not exist before, is unique
            if (!map.containsKey(abbr)) {
                return true;
            }
            // if contains
            else {
                // get the set
                Set<String> words = map.get(abbr);
                // if the set only got 1 word, and that word is exact the word we want
                if (words.size() == 1 && words.contains(word)) {
                    // is unique
                    return true;
                }
            }
            // if not, not unique
            return false;
        }

        // convert the word to the abbreviation
        private String convertToAbbr(String word) {
            // base case
            if (word == null || word.length() <= 2) {
                return word;
            }
            // head + int(length - 2) + tail
            return word.charAt(0) + String.valueOf(word.length() - 2) + word.charAt(word.length() - 1);
        }
    }
}
