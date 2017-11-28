package net.uk.interconnect.exercises;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CodingPractice1Test {

    private CodingPractice1 codingPractice1;

    @Before
    public void setUp() throws Exception {
        codingPractice1 = new CodingPractice1();
    }

    @After
    public void tearDown() throws Exception {
        codingPractice1 = null;
    }

    @Test
    public void arrayCyclicRotation() throws Exception {
        // For example, given array A = [3, 8, 9, 7, 6] and K = 3, the function should return [9, 7, 6, 3, 8]
        int[] A = {3, 8, 9, 7, 6};
        int K = 3;
        int[] B = {9, 7, 6, 3, 8};

        assertArrayEquals(B, codingPractice1.arrayCyclicRotation(A, K));
    }

    @Test
    public void findOddElementInArray() throws Exception {
        int[] A = {9, 3, 9, 3, 9, 7, 9};

        assertEquals(7, codingPractice1.findOddElementInArray(A));

    }

    @Test
    public void divideArrayIntoMinMaxSumBlocks() throws Exception {
        int[] A = {2, 1, 5, 1, 2, 2, 2};
        int K = 3;
        int M = 5;

        assertEquals(6, codingPractice1.divideArrayIntoMinMaxSumBlocks(K, M, A));
    }


    @Test
    public void testNailingPlanks() throws Exception {
        int[] A = {1, 4, 5, 8};
        int[] B = {4, 5, 9, 10};
        int[] C = {4, 6, 7, 10, 2};

        long start = System.currentTimeMillis();
        assertEquals(4, codingPractice1.nailingPlanks(A, B, C));
        long end = System.currentTimeMillis();

        System.out.println("Time: " + (end - start) );
    }


    @Test
    public void testAbsDistinct() throws Exception {
        int[] A = {-5, -3, -1, 0, 3, 6};

        assertEquals(5, codingPractice1.absDistinct(A));
    }


    @Test
    public void testCountDistinctSlices() throws Exception {
        int[] A = {3, 4, 5, 5, 2};
        int M = 6;

        assertEquals(9, codingPractice1.countDistinctSlices(M, A));
    }

    @Test
    public void testCountTriangles() throws Exception {
        int[] A = {10, 2, 5, 1, 8, 12};

        assertEquals(4, codingPractice1.countTriangles(A));
    }

    @Test
    public void testMinAbsSumOfTwo() throws Exception {
        int[] A = {1, 4, -3};
        int[] B = {-8, 4, 5, -10, 3};

        assertEquals(1, codingPractice1.minAbsSumOfTwo(A));
        assertEquals(3, codingPractice1.minAbsSumOfTwo(B));
    }

    @Test
    public void testFrogRiverOne() throws Exception {
        int[] A = {1, 3, 1, 4, 2, 3, 5, 4};
        int X = 5;

        assertEquals(6, codingPractice1.frogRiverOne(X, A));
    }

    @Test
    public void testFrogJump() throws Exception {

        assertEquals(3, codingPractice1.frogJump(10, 85, 30));
        assertEquals(3, codingPractice1.frogJump2(10, 85, 30));
    }

    @Test
    public void testMaxCounters() throws Exception {
        int[] A = {3, 4, 4, 6, 1, 4, 4};
        int N = 5;
        int[] B = {3, 2, 2, 4, 2};

        assertArrayEquals(B, codingPractice1.maxCounters(N, A));
    }

    @Test
    public void testMissingInteger() throws Exception {
        int[] A = {1, 3, 6, 4, 1, 2};

        assertEquals(5, codingPractice1.missingInteger(A));
    }

    @Test
    public void testPermutationCheck() throws Exception {
        int[] A = {4, 1, 3, 2};
        int[] B = {4, 1, 3};

        assertEquals(1, codingPractice1.permutationCheck(A));
        assertEquals(0, codingPractice1.permutationCheck(B));
    }

    @Test
    public void testMinAbsSum() throws Exception {
        int[] A = {1, 5, 2, -2};

        assertEquals(0, codingPractice1.minAbsSum(A));
    }

    @Test
    public void testNumberSolitaire() throws Exception {
        int[] A = {1, -2, 0, 9, -1, -2};

        assertEquals(8, codingPractice1.numberSolitaire(A));
    }

    @Test
    public void testChocolatesByNumbers() throws Exception {
        int N = 10, M = 4;

        assertEquals(5, codingPractice1.chocolatesByNumbers(N, M));
    }

    @Test
    public void testCommonPrimeDivisors() throws Exception {
        int[] A = {15, 10, 3};
        int[] B = {75, 30, 5};
        // (15, 75)
        assertEquals(1, codingPractice1.commonPrimeDivisors(A, B));
    }

    @Test
    public void testFibFrog() throws Exception {
        int[] A = {0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0};

        assertEquals(3, codingPractice1.fibFrog(A));
    }


    @Test
    public void testLadder() throws Exception {
        int[] A = {4, 4, 5, 5, 1};
        int[] B = {3, 2, 4, 3, 1};
        int[] C = {5, 1, 8, 0, 1};

        assertArrayEquals(C, codingPractice1.ladder(A, B));
    }




}