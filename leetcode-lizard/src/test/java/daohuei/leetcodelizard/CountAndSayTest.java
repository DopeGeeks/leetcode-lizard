package daohuei.leetcodelizard;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CountAndSayTest {
    @Test
    public void testOnCountAndSay() {
        assertEquals("Count and Say", "1", CountAndSay.countAndSay(1));
        assertEquals("Count and Say", "1211", CountAndSay.countAndSay(4));
    }
}
