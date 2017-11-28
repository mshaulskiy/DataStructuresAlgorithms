package net.uk.interconnect.exercises;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CodingPractice6Test {

    private CodingPractice6 codingPractice6;

    @Before
    public void setUp() throws Exception {
        codingPractice6 = new CodingPractice6();
    }

    @After
    public void tearDown() throws Exception {
        codingPractice6 = null;
    }

    @Test
    public void brackets() throws Exception {
        String S = "{[()()]}";
        String S2 = "([)()]";

        assertEquals(1, codingPractice6.brackets(S));
        assertEquals(0, codingPractice6.brackets(S2));

        assertEquals(1, codingPractice6.brackets2(S));
        assertEquals(0, codingPractice6.brackets2(S2));
    }

    @Test
    public void fish() throws Exception {
        int[] A = {4, 3, 2, 1, 5};
        int[] B = {0, 1, 0, 0, 0};

        assertEquals(2, codingPractice6.fish(A, B));
        assertEquals(2, codingPractice6.fish2(A, B));
        assertEquals(2, codingPractice6.fish3(A, B));
    }

    @Test
    public void nestingBrackets() throws Exception {
        String S = "(()(())())";
        String S2 = "())";

        assertEquals(1, codingPractice6.nestingBrackets(S));
        assertEquals(0, codingPractice6.nestingBrackets(S2));

        assertEquals(1, codingPractice6.nestingBrackets2(S));
        assertEquals(0, codingPractice6.nestingBrackets2(S2));

        assertEquals(1, codingPractice6.nestingBrackets3(S));
        assertEquals(0, codingPractice6.nestingBrackets3(S2));
    }

    @Test
    public void stoneWall() throws Exception {
        int[]  H = {8, 8, 5, 7, 9, 8, 7, 4, 8};

        assertEquals(7, codingPractice6.stoneWall(H));
        assertEquals(7, codingPractice6.stoneWall2(H));
        assertEquals(7, codingPractice6.stoneWall3(H));
    }

    @Test
    public void missingElem() throws Exception {
        int[] A = {2, 3, 1, 5};

        assertEquals(4, codingPractice6.missingElem(A));
        assertEquals(4, codingPractice6.missingElem2(A));
        assertEquals(4, codingPractice6.missingElem3(A));
    }

    @Test
    public void tapeEquilibrium() throws Exception {
        int[] A = {3, 1, 2, 4, 3};

        assertEquals(1, codingPractice6.tapeEquilibrium(A));
        assertEquals(1, codingPractice6.tapeEquilibrium2(A));
        assertEquals(1, codingPractice6.tapeEquilibrium3(A));
    }

    @Test
    public void firstNonRepeatedChar() throws Exception {

        assertEquals('a', codingPractice6.firstNonRepeatedChar("apple"));
    }

}
