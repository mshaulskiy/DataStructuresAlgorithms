package net.uk.interconnect.exercises;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class CodingPractice2 {

    /**
     * Computer number of inversion in an array.
     * A zero-indexed array A consisting of N integers is given. An inversion is a pair of indexes (P, Q) such that P < Q and A[Q] < A[P].
     * Compute the number of inversions in A, or returns −1 if it exceeds 1,000,000,000.
     *
     * N is an integer within the range [0..100,000];
     * each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
     *
     * For example, in the following array:
     *
     * A = {-1, 6, 3, 4, 7, 4}
     * return 4
     *
     * There are four inversions:
     * (1,2)  (1,3)  (1,5)  (4,5)
     *
     * time complexity is O(N*log(N))
     * space complexity is O(N)
     */
    public int arrayInversionCount(int[] array) {
        // we know that the number of inversions is the times of the swap of the
        // array to be sorted.so we can use the merge sort and count the swap times.
        if (array.length < 2)
            return 0;
        int middle = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, middle);
        int[] right = Arrays.copyOfRange(array, middle, array.length);
        return arrayInversionCount(left) + arrayInversionCount(right) + merge(array, left, right);
    }

    public int merge(int[] array, int[] left, int[] right) {
        int i = 0;
        int j = 0;
        int count = 0;
        while (i < left.length || j < right.length) {
            if (i == left.length) {
                array[i + j] = right[j];
                j++;
            } else if (j == right.length) {
                array[i + j] = left[i];
                i++;
            } else if (left[i] <= right[j]) {
                array[i + j] = left[i];
                i++;
            } else {
                array[i + j] = right[j];
                j++;
                // The number of inversions removed by this operation is the
                // number of elements left from the left array to be merged
                count += left.length - i;
            }
        }
        return count > 1000000000 ? -1 : count;
    }


    /**
     * A binary gap within a positive integer N is any maximal sequence of consecutive zeros that is surrounded by
     * ones at both ends in the binary representation of N.
     * For example, number 9 has binary representation 1001 and contains a binary gap of length 2.
     * The number 529 has binary representation 1000010001 and contains two binary gaps: one of length 4 and one of length 3.
     * The number 20 has binary representation 10100 and contains one binary gap of length 1.
     * The number 15 has binary representation 1111 and has no binary gaps.
     * <p>
     * N = 1041, binary representation 10000010001
     * return 5
     * <p>
     * time complexity is O(log(N))
     * space complexity is O(1)
     */
    public int binaryGap(int N) {
        // write your code in Java SE 8
        int positionOneLow = 0;
        int positionOneHigh = 30;
        int gap = 0;
        int position = 0;
        while (N != 0) {
            // using the & operator can also determine whether
            // N is an odd or even, but the result almost the same.
            // if ((N & 1) == 1)
            if (N % 2 == 1) {
                positionOneLow = positionOneHigh;
                positionOneHigh = position;
                gap = Math.max(gap, positionOneHigh - positionOneLow - 1);
            }
            N /= 2; // (N>>1)
            position++;
        }
        return gap;
    }

    public int binaryGap2(int N) {

        String val = Integer.toBinaryString(N);
        int length = val.length();
        int count = 0;

        for (int i = 0; i < length; ++i) {

            int start = val.substring(i, length).indexOf("10");
            if (start == -1) {
                break;
            }
            int end = val.substring(start + i + 1, length).indexOf("1");
            if (end == -1) {
                break;
            }
            int gap = end - start;
            if (count < gap) {
                count = gap;
            }
            i = end + i;
        }

        return count;
    }

    public int binaryGap3(int N) {
        /*return Stream.of(Integer.toBinaryString(N).
                replaceAll("0+$", "").split("1+"))
                .filter(a -> a != null)
                .max((a, b) -> Integer.compare(a.length(), b.length()))
                .map(String::length)
                .orElse(0);*/

        String val = Integer.toBinaryString(N);
        System.out.println("Values is: " + val);

        String replaced = val.replaceAll("0+$", "");
        System.out.println("Rreplaced is: " + replaced + "  and  " + val.replaceAll("0+$", ""));

        String[] splited = replaced.split("1+");

        for (String s : splited) {
            System.out.println("Split is: " + s);
        }

        return Stream.of(Integer.toBinaryString(N).
                replaceAll("0+$", "").split("1+"))
                .filter(a -> a != null)
                .map(String::length)
                .max(Integer::compare)
                .orElse(0);
    }


    /**
     * Find a symmetry point of a string, if any.
     * Given a string S, returns the index (counting from 0) of a character such that the part of the string
     * to the left of that character is a reversal of the part of the string to its right.
     * <p>
     * String s = "racecar"
     * return 3
     * <p>
     * time complexity is O(length(S))
     * space complexity is O(1)
     */
    public int stringtrSymmetryPoint(String S) {
        // write your code in Java SE 8
        int length = S.length();
        if (length == 0)
            return -1;
        int tail = 0;
        int head = length - 1;
        int result = 0;
        while (tail < head) {
            // symmetry character exists
            if (S.charAt(tail) == S.charAt(head)) {
                tail++;
                head--;
                // symmetry character not exists
            } else
                return -1;
        }
        // find the index
        if (tail == head)
            return tail;
            // can not find the index
        else
            return -1;
    }


    /**
     * Compute the height of a binary tree, represented by pointer data structures.
     * A binary tree is either an empty tree or a node (called the root) consisting of a single integer value and
     * two further binary trees, called the left subtree and the right subtree.
     * <p>
     * (5, (3, (20, None, None), (21, None, None)), (10, (1, None, None), None))
     * <p>
     * time complexity is O(N)
     * space complexity is O(N)
     */
    public int treeHeight(Tree T) {
        int nl = 0;
        int nr = 0;
        if (T.l != null) nl = 1 + treeHeight(T.l);
        if (T.r != null) nr = 1 + treeHeight(T.r);
        return Math.max(nl, nr);
    }

    class Tree {
        public int x;
        public Tree l;
        public Tree r;
    }

    /**
     * Find a maximal set of non-overlapping segments.
     * Located on a line are N segments, numbered from 0 to N − 1, whose positions are given in zero-indexed arrays A and B.
     * For each I (0 ≤ I < N) the position of segment I is from A[I] to B[I] (inclusive).
     * The segments are sorted by their ends, which means that B[K] ≤ B[K + 1] for K such that 0 ≤ K < N − 1.
     * Two segments I and J, such that I ≠ J, are overlapping if they share at least one common point.
     * In other words, A[I] ≤ A[J] ≤ B[I] or A[J] ≤ A[I] ≤ B[J].
     * <p>
     * A = {1, 3, 7, 9, 9}
     * B = {5, 6, 8, 9, 10}
     * <p>
     * The size of a non-overlapping set containing a maximal number of segments is 3.
     * For example, possible sets are {0, 2, 3}, {0, 2, 4}, {1, 2, 3} or {1, 2, 4}.
     * There is no non-overlapping set with four segments.
     * <p>
     * time complexity is O(N)
     * space complexity is O(N)
     */
    public int maxNonOverlappingSegments(int[] A, int[] B) {
        if (A.length == 0) {
            return A.length;
        }

        int count = 1;
        int ending = B[0];
        for (int i = 1; i < B.length; i++) {
            if (A[i] > ending) {
                ending = B[i];
                count++;
            }
        }

        return count;
    }


    /**
     * Tie adjacent ropes to achieve the maximum number of ropes of length >= K.
     * There are N ropes numbered from 0 to N − 1, whose lengths are given in a zero-indexed array A,
     * lying on the floor in a line. For each I (0 ≤ I < N), the length of rope I on the line is A[I].
     * For a given integer K, the goal is to tie the ropes in such a way that the number of ropes whose
     * length is greater than or equal to K is maximal.
     * <p>
     * consider K = 4 and array A = {1, 2, 3, 4, 1, 1, 3}
     * return 3
     * <p>
     * rope 1 with rope 2 to produce a rope of length A[1] + A[2] = 5;
     * rope 4 with rope 5 with rope 6 to produce a rope of length A[4] + A[5] + A[6] = 5.
     * After that, there will be three ropes whose lengths are greater than or equal to K = 4.
     * It is not possible to produce four such ropes.
     * <p>
     * time complexity is O(N)
     * space complexity is O(N)
     */
    public int tieRopes(int K, int[] A) {
        // use the greedy algorithm to solve this problem
        // because we can only tie the adjacent ropes, so for each rope we can
        // count the length of the consecutive ropes and figure out if there is
        // a tied rope that the length is greater than or equal to K.
        int sum = 0;
        int ropes = 0;
        for (int element : A) {
            sum += element;
            if (sum >= K) {
                sum = 0;
                ropes++;
            }
        }
        return ropes;
    }

    /**
     * Find the maximum depth of water in mountains after a huge rainfall.
     * We simplify the problem in 2-D dimensions. The whole landscape can be divided into small blocks and described
     * by an array A of length N. Each element of A is the altitude of the rock floor of a block (i.e. the height of
     * this block when there is no water at all). After the rainfall, all the low-lying areas (i.e. blocks that have
     * higher blocks on both sides) are holding as much water as possible. You would like to know the maximum depth
     * of water after this entire area is flooded. You can assume that the altitude outside this area is zero and
     * the outside area can accommodate infinite amount of water.
     * <p>
     * A = {1, 3, 2, 1, 2, 1, 5, 3, 3, 4, 2}
     * <p>
     * Thus, blocks 3 and 5 have a water depth of 2 while blocks 2, 4, 7 and 8 have a water depth of 1.
     * Therefore, the maximum water depth of this area is 2.
     * <p>
     * A = {5, 8}
     * return 0
     * <p>
     * time complexity is O(N)
     * space complexity is O(N)
     */
    public int floodDepth(int[] A) {
        int b = 0;
        int c = 0;
        int max = 0;

        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[b]) {
                max = Math.max(A[b] - A[c], max);
                b = i;
                c = b;
            } else if (A[i] > A[c]) {
                max = Math.max(A[i] - A[c], max);
            } else if (A[i] < A[c]) {
                c = i;
            }
        }

        return max;
    }

    /**
     * Given a string containing words, find the longest word that satisfies specific conditions.
     * it has to contain only alphanumerical characters (a−z, A−Z, 0−9);
     * there should be an even number of letters;
     * there should be an odd number of digits.
     * <p>
     * You are given a string S consisting of N characters. String S can be divided into words by splitting it at,
     * and removing, the spaces. The goal is to choose the longest word that is a valid password.
     * You can assume that if there are K spaces in string S then there are exactly K + 1 words.
     * <p>
     * For example, given "test 5 a0A pass007 ?xy1", there are five words and three of them are valid passwords:
     * "5", "a0A" and "pass007". Thus the longest password is "pass007" and its length is 7.
     * Note that neither "test" nor "?xy1" is a valid password, because "?" is not an alphanumerical character
     * and "test" contains an even number of digits (zero).
     * <p>
     * that, given a non-empty string S consisting of N characters, returns the length of the longest word from
     * the string that is a valid password. If there is no such word, your function should return −1.
     * <p>
     * For example, given S = "test 5 a0A pass007 ?xy1", your function should return 7, as explained above.
     * string S consists only of printable ASCII characters and spaces.
     */
    public int longestPassword(String S) {

        //Split string by space
        String[] split = S.split(" ");
        List<Integer> passwordList = new ArrayList<Integer>();

        //Check and add valid password's length to array
        for (String each : split) {
            if (each.matches("^[a-zA-Z0-9]*$")) {
                int digits = each.replaceAll("[^0-9.]", "").length();
                int alpha = each.length() - digits;

                if (digits % 2 != 0 && alpha % 2 == 0) {
                    passwordList.add(each.length());
                }
            }
        }

        //Find max length password else return -1
        if (passwordList.size() > 0) {
            Collections.sort(passwordList);
            return passwordList.get(passwordList.size() - 1);
        } else
            return -1;
    }


    /**
     * Find out how many dwarfs can fit on a raft such that it's balanced when crossing a river.
     * The raft is square and has N rows of seats, numbered from 1 to N. Each row contains N seats,
     * labeled with consecutive letters (A, B, C, etc.). Each seat is identified by a string composed
     * of its row number followed by its column number; for example, "9C" denotes the third seat in the 9th row.
     * That is, the following conditions must be satisfied:
     * the front and back halves of the raft (in terms of the rows of seats) must each contain the same number of dwarfs;
     * similarly, the left and right sides of the raft (in terms of the columns of seats) must each contain the same number of dwarfs.
     * You do not have to worry about balancing the barrels; you can assume that their weights are negligible.
     * For example, a raft of size N = 4 is shown in the following illustration:
     * Barrels are marked as brown squares, and seats that are already occupied by dwarfs are labeled d.
     * The positions of the barrels are given in string S. The occupied seat numbers are given in string T.
     * The contents of the strings are separated by single spaces and may appear in any order.
     * For example, in the diagram above, S = "1B 1C 4B 1D 2A" and T = "3B 2D".
     * In this example, the ferryman can accommodate at most six more dwarfs, as indicated by the green squares in the following diagram:
     * The raft is then balanced: both left and right halves have the same number of dwarfs (four), and both front
     * and back halves have the same number of dwarfs (also four).
     * given the size of the raft N and two strings S, T that describes the positions of barrels and occupied seats,
     * respectively, returns the maximum number of dwarfs that can fit on the raft. If it is not possible to balance
     * the raft with dwarfs, your function should return -1.
     * For instance, given N = 4, S = "1B 1C 4B 1D 2A" and T = "3B 2D", your function should return 6, as explained above.
     * Focus on correctness.
     */
    public int dwarfsRafting(int N, String S, String T) {
        int sum = 0;
        int qua = N * N / 4;
        int[] parts = new int[4];

        int maxtopleft = 0;
        int maxbuttonright = 0;
        if (S.length() == 0) {
            parts[0] = parts[1] = parts[2] = parts[3] = 0;
        } else {
            for (String loca : S.split(" ")) {
                int locanum = loca.length();
                int loc = Integer.parseInt(loca.substring(0, locanum - 1));
                char locat = loca.charAt(locanum - 1);
                int locatt = (int) locat - (int) 'A' + 1;
                if (loc <= N / 2) {
                    if (locatt <= N / 2) {
                        parts[0]++;
                    } else {
                        parts[1]++;
                    }
                } else {
                    if (locatt <= N / 2) {
                        parts[2]++;
                    } else {
                        parts[3]++;
                    }
                }
            }

            if (parts[0] > parts[3])
                maxtopleft = parts[0];
            else
                maxtopleft = parts[3];

            if (parts[1] > parts[2])
                maxbuttonright = parts[1];
            else
                maxbuttonright = parts[2];

        }

        int people1 = 0;
        int people2 = 0;
        int people3 = 0;
        int people4 = 0;
        if (T.length() != 0) {
            for (String pp : T.split(" ")) {
                int locanumt = pp.length();
                int loct = Integer.parseInt(pp.substring(0, locanumt - 1));
                char locatt = pp.charAt(locanumt - 1);
                int locattt = (int) locatt - (int) 'A' + 1;
                if (loct <= N / 2) {
                    if (locattt <= N / 2) {
                        people1++;
                    } else {
                        people2++;
                    }
                } else {
                    if (locattt <= N / 2) {
                        people3++;
                    } else {
                        people4++;
                    }
                }
            }
        }

        if ((qua - maxtopleft) < people1 || (qua - maxtopleft) < people4)
            return -1;

        if ((qua - maxbuttonright) < people2 || (qua - maxbuttonright) < people3)
            return -1;

        sum = (2 * qua - maxtopleft - maxbuttonright) * 2 - people1 - people2 - people3 - people4;
        return sum;
    }


    /**
     * Given the numbers of players and available courts, calculate the maximum number of parallel tennis games.
     * P players, who will take part in the first round of this tournament, are already registered and you have
     * reserved C tennis courts for the matches. How many games can be hosted in parallel simultaneously?
     * Given the number of players P and the number of reserved courts C, returns the maximum number
     * of games that can be played in parallel.
     * For example, given P = 5 players and C = 3 available courts, the function should return 2,
     * as two games can be played simultaneously.
     * Given P = 10 players and C = 3 courts, the function should return 3, as at most three games can be hosted in parallel.
     * focus on correctness.
     */
    public int tennisTournament(int P, int C) {
        return C * 2 < P ? C : P / 2;
    }

    /**
     * A zero-indexed array A consisting of N integers is given. The dominator of array A is the value that occurs
     * in more than half of the elements of A.
     *
     * A = {3, 4, 3, 2, 3, -1, 3, 3}
     * return 0, 2, 4, 6 or 7
     *
     * The dominator of A is 3 because it occurs in 5 out of 8 elements of A (namely in those with indices 0, 2, 4, 6 and 7)
     * and 5 is more than a half of 8.
     * Given a zero-indexed array A consisting of N integers, returns index of any element of array A in which
     * the dominator of A occurs. The function should return −1 if array A does not have a dominator.
     *
     * time complexity is O(N)
     * space complexity is O(1)
     */
    public int dominator(int[] A) {
        if (A.length == 1)
            return 0;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < A.length; i++) {
            int value = 1;
            if (map.containsKey(A[i])) {
                value = map.get(A[i]) + 1;
                if (value > (A.length / 2))
                    return i;
            }

            map.put(A[i], value);
        }

        return -1;
    }

    public int dominator2(int[] A){
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < A.length; i++) {
            if (stack.empty() || A[i] == A[stack.peek()])
                stack.push(i);
            else
                stack.pop();
        }
        if(!stack.empty()){
            int expected = stack.peek();
            int counter = 0;
            for (int i = 0; i < A.length; i++) {
                if(A[i] == A[expected])
                    counter++;
            }
            if(counter > A.length / 2)
                return expected;
        }
        return -1;
    }

}
