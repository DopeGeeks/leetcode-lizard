package daohuei.leetcodelizard;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ValidParenthesesTest {
    @Test
    public void validParentheses() {
        assertEquals("Stack", true, ValidParentheses.validParenthesesStack("()"));
        assertEquals("Stack", true, ValidParentheses.validParenthesesStack("()[]{}"));
        assertEquals("Stack", false, ValidParentheses.validParenthesesStack("(]"));
        assertEquals("Stack", false, ValidParentheses.validParenthesesStack("([)]"));
        assertEquals("Stack", true, ValidParentheses.validParenthesesStack("{[]}"));
    }
}