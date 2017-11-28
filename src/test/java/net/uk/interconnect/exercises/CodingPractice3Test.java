package net.uk.interconnect.exercises;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CodingPractice3Test {

    private CodingPractice3 codingPractice3;

    @Before
    public void setUp() throws Exception {
        codingPractice3 = new CodingPractice3();
    }

    @After
    public void tearDown() throws Exception {
        codingPractice3 = null;
    }

    @Test
    public void equiLeader() throws Exception {
        int[] A = {4, 3, 4, 4, 4, 2};

        assertEquals(2, codingPractice3.equiLeader(A));
        assertEquals(2, codingPractice3.equiLeader2(A));
    }

    @Test
    public void maxDoubleSliceSum() throws Exception {
        int[] A = {3, 2, 6, -1, 4, 5, -1, 2};

        assertEquals(17, codingPractice3.maxDoubleSliceSum(A));
        assertEquals(17, codingPractice3.maxDoubleSliceSum2(A));
    }

    @Test
    public void maxProfit() throws Exception {
        int[] A = {23171, 21011, 21123, 21366, 21013, 21367};

        assertEquals(356, codingPractice3.maxProfit(A));
        assertEquals(356, codingPractice3.maxProfit2(A));
    }

    @Test
    public void maxSliceSum() throws Exception {
        int[] A = {3, 2, -6, 4, 0};

        assertEquals(5, codingPractice3.maxSliceSum(A));
        assertEquals(5, codingPractice3.maxSliceSum2(A));
    }

    @Test
    public void countDiv() throws Exception {
        int A = 6;
        int B = 11;
        int K = 2;

        assertEquals(3, codingPractice3.countDiv(A, B, K));
        assertEquals(4, codingPractice3.countDiv(5, 15, 3));

        assertEquals(3, codingPractice3.countDiv2(A, B, K));
        assertEquals(4, codingPractice3.countDiv2(5, 15, 3));

        assertEquals(3, codingPractice3.countDiv3(A, B, K));
        assertEquals(4, codingPractice3.countDiv3(5, 15, 3));
    }



    @Test
    public void genomicRangeQuery() throws Exception {
        String S = "CAGCCTA";
        int[] P = {2, 5, 0};
        int[] Q = {4, 5, 6};
        int[] A = {2, 4, 1};

        assertArrayEquals(A, codingPractice3.genomicRangeQuery(S, P, Q));
        assertArrayEquals(A, codingPractice3.genomicRangeQuery2(S, P, Q));
    }

}
