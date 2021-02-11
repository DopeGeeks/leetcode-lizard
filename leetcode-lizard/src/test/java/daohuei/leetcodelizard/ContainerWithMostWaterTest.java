package daohuei.leetcodelizard;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ContainerWithMostWaterTest {
    @Test
    public void testOnContainerWithMostWater() {
        assertEquals("Brutal Force", 49,
                ContainerWithMostWater.maxAreaBrutalForce(new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 }), 0);
        assertEquals("Brutal Force", 1, ContainerWithMostWater.maxAreaBrutalForce(new int[] { 1, 1 }), 0);
        assertEquals("Brutal Force", 16, ContainerWithMostWater.maxAreaBrutalForce(new int[] { 4, 3, 2, 1, 4 }), 0);
        assertEquals("Brutal Force", 2, ContainerWithMostWater.maxAreaBrutalForce(new int[] { 1, 2, 1 }), 0);

        assertEquals("Closure Method", 49,
                ContainerWithMostWater.maxAreaClosureMethod(new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 }), 0);
        assertEquals("Closure Method", 1, ContainerWithMostWater.maxAreaClosureMethod(new int[] { 1, 1 }), 0);
        assertEquals("Closure Method", 16, ContainerWithMostWater.maxAreaClosureMethod(new int[] { 4, 3, 2, 1, 4 }), 0);
        assertEquals("Closure Method", 2, ContainerWithMostWater.maxAreaClosureMethod(new int[] { 1, 2, 1 }), 0);
    }

}
