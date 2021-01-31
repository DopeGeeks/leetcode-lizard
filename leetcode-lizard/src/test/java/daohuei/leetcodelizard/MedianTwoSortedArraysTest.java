package daohuei.leetcodelizard;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MedianTwoSortedArraysTest {
    @Test
    public void testOnMedianTwoSortedArrays() {
        assertEquals("BrutalForce", 2.0,
                MedianTwoSortedArrays.findMedianSortedArraysBrutalForce(new int[] { 1, 3 }, new int[] { 2 }), 0.0);

        assertEquals("BrutalForce", 2.5,
                MedianTwoSortedArrays.findMedianSortedArraysBrutalForce(new int[] { 1, 2 }, new int[] { 3, 4 }), 0.0);

        assertEquals("BrutalForce", 0.0,
                MedianTwoSortedArrays.findMedianSortedArraysBrutalForce(new int[] { 0, 0 }, new int[] { 0, 0 }), 0.0);

        assertEquals("BrutalForce", 1.0,
                MedianTwoSortedArrays.findMedianSortedArraysBrutalForce(new int[] {}, new int[] { 1 }), 0.0);

        assertEquals("BrutalForce", 2.0,
                MedianTwoSortedArrays.findMedianSortedArraysBrutalForce(new int[] { 2 }, new int[] {}), 0.0);

        assertEquals("Binary", 2.0,
                MedianTwoSortedArrays.findMedianSortedArraysBinary(new int[] { 1, 3 }, new int[] { 2 }), 0.0);

        assertEquals("Binary", 2.5,
                MedianTwoSortedArrays.findMedianSortedArraysBinary(new int[] { 1, 2 }, new int[] { 3, 4 }), 0.0);

        assertEquals("Binary", 0.0,
                MedianTwoSortedArrays.findMedianSortedArraysBinary(new int[] { 0, 0 }, new int[] { 0, 0 }), 0.0);

        assertEquals("Binary", 1.0, MedianTwoSortedArrays.findMedianSortedArraysBinary(new int[] {}, new int[] { 1 }),
                0.0);

        assertEquals("Binary", 2.0, MedianTwoSortedArrays.findMedianSortedArraysBinary(new int[] { 2 }, new int[] {}),
                0.0);
    }
}
