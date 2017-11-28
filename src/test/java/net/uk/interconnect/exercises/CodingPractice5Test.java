package net.uk.interconnect.exercises;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CodingPractice5Test {

    private CodingPractice5 codingPractice5;

    @Before
    public void setUp() throws Exception {
        codingPractice5 = new CodingPractice5();
    }

    @After
    public void tearDown() throws Exception {
        codingPractice5 = null;
    }

    @Test
    public void peaks() throws Exception {
        int[] A = {3, 1, 2, 3, 6};
        int[] B = {2, 4, 3, 2, 0};

        assertArrayEquals(B, codingPractice5.countNonDivisible(A));
        assertArrayEquals(B, codingPractice5.countNonDivisible2(A));
    }

    @Test
    public void distinct() throws Exception {
        int[] A = {2, 1, 1, 2, 3, 1};

        assertEquals(3, codingPractice5.distinct(A));
        assertEquals(3, codingPractice5.distinct2(A));
        assertEquals(3, codingPractice5.distinct3(A));
        assertEquals(3, codingPractice5.distinct4(A));
    }

    @Test
    public void selectionSort() throws Exception {
        int[] A = {5, 2, 8, 14, 1, 16};
        int[] B = {1, 2, 5, 8, 14, 16};

        assertArrayEquals(B, codingPractice5.selectionSort(A));
    }

    @Test
    public void countingSort() throws Exception {
        int[] A = {5, 2, 8, 14, 1, 16};
        int[] B = {1, 2, 5, 8, 14, 16};

        assertArrayEquals(B, codingPractice5.countingSort(A, 16));
    }

    @Test
    public void maxProductOfThree() throws Exception {
        int[] A = {-3, 1, 2, -2, 5, 6};

        assertEquals(60, codingPractice5.maxProductOfThree(A));
        assertEquals(60, codingPractice5.maxProductOfThree2(A));
        assertEquals(60, codingPractice5.maxProductOfThree3(A));
    }

    @Test
    public void numberOfDiscIntersections() throws Exception {
        int[] A = {1, 5, 2, 1, 4, 0};

        assertEquals(11, codingPractice5.numberOfDiscIntersections(A));
        assertEquals(11, codingPractice5.numberOfDiscIntersections2(A));
        assertEquals(11, codingPractice5.numberOfDiscIntersections3(A));
    }

    @Test
    public void triangle() throws Exception {
        int[] A = {10, 2, 5, 1, 8, 20};
        int[] B = {10, 50, 5, 1};

        assertEquals(1, codingPractice5.triangle(A));
        assertEquals(0, codingPractice5.triangle(B));

        assertEquals(1, codingPractice5.triangle2(A));
        assertEquals(0, codingPractice5.triangle2(B));
    }

}
