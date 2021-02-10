package daohuei.leetcodelizard;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LengthOfLongestSubstringTest {
    @Test
    public void testOnLengthOfLongestSubstring() {
        assertEquals("HashMap Method", 3, LengthOfLongestSubstring.lengthOfLongestSubstring("abcabcbb"), 0);
        assertEquals("HashMap Method", 1, LengthOfLongestSubstring.lengthOfLongestSubstring("bbbbb"), 0);
        assertEquals("HashMap Method", 3, LengthOfLongestSubstring.lengthOfLongestSubstring("pwwkew"), 0);
        assertEquals("HashMap Method", 0, LengthOfLongestSubstring.lengthOfLongestSubstring(""), 0);
    }
}
