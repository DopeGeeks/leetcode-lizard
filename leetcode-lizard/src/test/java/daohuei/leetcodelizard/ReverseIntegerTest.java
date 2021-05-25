package daohuei.leetcodelizard;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ReverseIntegerTest {
    @Test
    public void testOnReverseInteger() {
        assertEquals("Reverse Integer", 321, ReverseInteger.reverseByDaoHuei(123), 0);
        assertEquals("Reverse Integer", -321, ReverseInteger.reverseByDaoHuei(-123), 0);
        assertEquals("Reverse Integer", 21, ReverseInteger.reverseByDaoHuei(120), 0);
        assertEquals("Reverse Integer", 0, ReverseInteger.reverseByDaoHuei(0), 0);
    }
}
