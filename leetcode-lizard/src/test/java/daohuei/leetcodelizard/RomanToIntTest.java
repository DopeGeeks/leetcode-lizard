package daohuei.leetcodelizard;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RomanToIntTest {
    @Test
    public void testOnRomanToInt() {
        assertEquals("Roman to Integer", 3, RomanToInt.romanToInt("III"), 0);
        assertEquals("Roman to Integer", 4, RomanToInt.romanToInt("IV"), 0);
        assertEquals("Roman to Integer", 9, RomanToInt.romanToInt("IX"), 0);
        assertEquals("Roman to Integer", 58, RomanToInt.romanToInt("LVIII"), 0);
        assertEquals("Roman to Integer", 1994, RomanToInt.romanToInt("MCMXCIV"), 0);
    }
}
