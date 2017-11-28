package net.uk.interconnect.exercises;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CodingPractice2Test {

    private CodingPractice2 codingPractice2;

    @Before
    public void setUp() throws Exception {
        codingPractice2 = new CodingPractice2();
    }

    @After
    public void tearDown() throws Exception {
        codingPractice2 = null;
    }


    @Test
    public void testArrayInversionCount() throws Exception {
        int[] A = {-1, 6, 3, 4, 7, 4};

        assertEquals(4, codingPractice2.arrayInversionCount(A));
    }

    @Test
    public void testBinaryGap() throws Exception {
        int N = 1041;

        assertEquals(5, codingPractice2.binaryGap(N));
    }

    @Test
    public void testStringtrSymmetryPoint() throws Exception {
        String str = "racecar";

        assertEquals(3, codingPractice2.stringtrSymmetryPoint(str));
    }

    @Test
    public void testTreeHeight() throws Exception {
        // (5, (3, (20, None, None), (21, None, None)), (10, (1, None, None), None))

        CodingPractice2.Tree treeD = codingPractice2.new Tree();
        treeD.x = 20;
        treeD.l = null;
        treeD.r = null;

        CodingPractice2.Tree treeE = codingPractice2.new Tree();
        treeE.x = 21;
        treeE.l = null;
        treeE.r = null;

        CodingPractice2.Tree treeF = codingPractice2.new Tree();
        treeF.x = 1;
        treeF.l = null;
        treeF.r = null;

        CodingPractice2.Tree treeB = codingPractice2.new Tree();
        treeB.x = 3;
        treeB.l = treeD;
        treeB.r = treeE;

        CodingPractice2.Tree treeC = codingPractice2.new Tree();
        treeC.x = 3;
        treeC.l = treeF;
        treeC.r = null;

        CodingPractice2.Tree root = codingPractice2.new Tree();
        root.x = 5;
        root.l = treeB;
        root.r = treeC;

        assertEquals(2, codingPractice2.treeHeight(root));
    }


    @Test
    public void testMaxNonOverlappingSegments() throws Exception {
        int[] A = {1, 3, 7, 9, 9};
        int[] B = {5, 6, 8, 9, 10};

        assertEquals(3, codingPractice2.maxNonOverlappingSegments(A, B));
    }

    @Test
    public void testTieRopes() throws Exception {
        int[] A = {1, 2, 3, 4, 1, 1, 3};
        int K = 4;

        assertEquals(3, codingPractice2.tieRopes(K, A));
    }

    @Test
    public void testFloodDepth() throws Exception {
        int[] A = {1, 2, 3, 4, 1, 1, 3};
        int[] B = {5, 8};

        assertEquals(2, codingPractice2.floodDepth(A));
        assertEquals(0, codingPractice2.floodDepth(B));
    }

    @Test
    public void testLongestPassword() throws Exception {
        String s = "test 5 a0A pass007 ?xy1";

        assertEquals(7, codingPractice2.longestPassword(s));
    }

    @Test
    public void testDwarfsRafting() throws Exception {
        String S = "1B 1C 4B 1D 2A";
        int N = 4;
        String T = "3B 2D";

        assertEquals(6, codingPractice2.dwarfsRafting(N, S, T));
    }

    @Test
    public void testTennisTournament() throws Exception {
        int P = 10;
        int C = 3;
        assertEquals(3, codingPractice2.tennisTournament(P, C));

        P = 5;
        C = 3;
        assertEquals(2, codingPractice2.tennisTournament(P, C));
    }

    @Test
    public void testDominator() throws Exception {
        int[] A = {3, 4, 3, 2, 3, -1, 3, 3};
        //return 0, 2, 4, 6 or 7
        assertEquals(7, codingPractice2.dominator(A));
        assertEquals(7, codingPractice2.dominator2(A));
    }





}
