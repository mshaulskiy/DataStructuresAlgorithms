package net.uk.interconnect.exercises;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CodingPractice4Test {

    private CodingPractice4 codingPractice4;

    @Before
    public void setUp() throws Exception {
        codingPractice4 = new CodingPractice4();
    }

    @After
    public void tearDown() throws Exception {
        codingPractice4 = null;
    }

    @Test
    public void calculateFactorial() throws Exception {
    }

    @Test
    public void printTriangle() throws Exception {
    }

    @Test
    public void printUpsideDownSymmetricalTriangle() throws Exception {
    }

    @Test
    public void countDecimalDigits() throws Exception {
    }

    @Test
    public void fibonacciSequence() throws Exception {
    }

    @Test
    public void printDaysOfTheWeek() throws Exception {
    }

    @Test
    public void printDaysOfTheWeek1() throws Exception {
    }

    @Test
    public void prefixSum() throws Exception {
        int[] A = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] B = {0, 0, 1, 3, 6, 10, 15, 21, 28, 36, 45};

        assertArrayEquals(B, codingPractice4.prefixSum(A));
    }

    @Test
    public void suffixSum() throws Exception {
        int[] A = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] B = {45, 45, 44, 42, 39, 35, 30, 24, 17, 9, 0,};

        assertArrayEquals(B, codingPractice4.suffixSum(A));
    }

    @Test
    public void countTotal() throws Exception {
        int[] A = {0, 0, 1, 3, 6, 10, 15, 21, 28, 36, 45};

        assertEquals(12, codingPractice4.countTotal(A, 3, 5));
    }

    @Test
    public void mushrooms() throws Exception {
        int[] A = {2, 3, 7, 5, 1, 3, 9};

        assertEquals(25, codingPractice4.mushrooms(A, 4, 6));
    }

    @Test
    public void passingCars() throws Exception {
        int[] A = {0, 1, 0, 1, 1};

        assertEquals(5, codingPractice4.passingCars(A));
        assertEquals(5, codingPractice4.passingCars2(A));
        assertEquals(5, codingPractice4.passingCars3(A));
    }

    @Test
    public void countFactors() throws Exception {
        int N = 24;

        assertEquals(8, codingPractice4.countFactors(N));
        assertEquals(8, codingPractice4.countFactors2(N));
    }

    @Test
    public void flags() throws Exception {
        int[] A = {1, 5, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2};

        assertEquals(3, codingPractice4.flags(A));
        assertEquals(3, codingPractice4.flags2(A));
    }

    @Test
    public void minPerimeterRectangle() throws Exception {
        int N = 30;

        assertEquals(22, codingPractice4.minPerimeterRectangle(N));
        assertEquals(22, codingPractice4.minPerimeterRectangle2(N));
        assertEquals(22, codingPractice4.minPerimeterRectangle3(N));
    }

    @Test
    public void peaks() throws Exception {
        int[] A = {1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2};

        assertEquals(3, codingPractice4.peaks(A));
        assertEquals(3, codingPractice4.peaks2(A));
        assertEquals(3, codingPractice4.peaks3(A));
    }





}
