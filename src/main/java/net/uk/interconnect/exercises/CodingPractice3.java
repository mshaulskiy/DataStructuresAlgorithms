package net.uk.interconnect.exercises;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodingPractice3 {

    /**
     * A non-empty zero-indexed array A consisting of N integers is given.
     * The leader of this array is the value that occurs in more than half of the elements of A.
     * An equi leader is an index S such that 0 ≤ S < N − 1 and two sequences
     * A[0], A[1], ..., A[S] and A[S + 1], A[S + 2], ..., A[N − 1] have leaders of the same value.
     * <p>
     * A = {4, 3, 4, 4, 4, 2}
     * return 2
     * <p>
     * 0, because sequences: (4) and (3, 4, 4, 4, 2) have the same leader, whose value is 4.
     * 2, because sequences: (4, 3, 4) and (4, 4, 2) have the same leader, whose value is 4.
     * The goal is to count the number of equi leaders.
     * <p>
     * time complexity is O(N)
     * space complexity is O(N)
     */
    public int equiLeader(int[] A) {
        // we know that if there is an equi leader that exists, then the leader
        // element must be the leader element of the whole array. on the other
        // hand, if there is a leader element in the whole array, then there
        // could be an equi leader that exists.
        // so the algorithm here is first find out the leader element in the
        // whole array and then count the number of equi leader in O(N) time complexity.
        int N = A.length;
        if (N == 1)
            return 0;
        // stores the index of the leader element
        int[] leaderIndex = new int[N];
        int value = 0;
        int size = 0;
        // using the algorithm in the reading material to find the leader in
        // O(N) time complexity
        for (int ele : A) {
            if (size == 0) {
                value = ele;
                size++;
            } else if (value == ele) {
                size++;
            } else
                size--;
        }
        if (size == 0)
            return 0;
        int countLeader = 0;
        for (int i = 0; i < N; i++) {
            if (A[i] == value) {
                countLeader++;
                leaderIndex[i] = 1;
            }
        }
        if (countLeader <= N / 2)
            return 0;
        // using prefix sum to calculate the number of leaders before index i in
        // O(1) time complexity
        for (int i = 1; i < N; i++) {
            leaderIndex[i] += leaderIndex[i - 1];
        }
        int countEquiLeader = 0;
        // the number of leaders in A[0] A[1] ... A[S] is leaderIndex[i]
        // the number of leaders in A[S+1] A[S+2] ... A[N-1] is countleader - leaderIndex[i]
        for (int i = 0; i < N - 1; i++) {
            if (leaderIndex[i] > (i + 1) / 2 && (countLeader - leaderIndex[i]) > (N - i - 1) / 2)
                countEquiLeader++;
        }
        return countEquiLeader;
    }


    public int equiLeader2(int[] A) {

        if (A.length == 0) {
            return 0;
        }

        int[] B = Arrays.copyOf(A, A.length);
        Arrays.sort(A);

        int candidate = Integer.MIN_VALUE;
        int totalLength = A.length;

        if (totalLength % 2 == 0) {
            int midPos = totalLength / 2;
            if (A[midPos - 1] == A[midPos]) {
                candidate = A[midPos];
            }
        } else {
            candidate = totalLength / 2;
        }

        int leaderCount = 0;
        for (int i : A) {
            if (i == candidate) {
                leaderCount++;
            }
        }

        int candidateCount = 0;
        int totalEquiCount = 0;

        for (int i = 0; i < B.length; i++) {
            if (B[i] == candidate) {
                candidateCount++;
            }

            if (candidateCount > Float.valueOf((i + 1) / 2) && (leaderCount - candidateCount) > Float.valueOf((totalLength - i - 1) / 2)) {
                totalEquiCount++;
            }
        }

        return totalEquiCount;
    }

    /**
     * A non-empty zero-indexed array A consisting of N integers is given.
     * A triplet (X, Y, Z), such that 0 ≤ X < Y < Z < N, is called a double slice.
     * The sum of double slice (X, Y, Z) is the total of A[X + 1] + A[X + 2] + ... + A[Y − 1] + A[Y + 1] + A[Y + 2] + ... + A[Z − 1].
     * <p>
     * A = {3, 2, 6, -1, 4, 5, -1, 2}
     * return 17, because no double slice of array A has a sum of greater than 17
     * <p>
     * contains the following example double slices:
     * double slice (0, 3, 6), sum is 2 + 6 + 4 + 5 = 17,
     * double slice (0, 3, 7), sum is 2 + 6 + 4 + 5 − 1 = 16,
     * double slice (3, 4, 5), sum is 0.
     * The goal is to find the maximal sum of any double slice.
     * <p>
     * time complexity is O(N)
     * space complexity is O(N)
     */
    public int maxDoubleSliceSum(int[] A) {
        int sum = 0;
        int[] sums = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            sums[i] = sum;
            sum += A[i];
        }

        int begin = 0;
        int end = 2;
        int middle;
        int candidate_index = begin;
        for (int i = 1; i < A.length; i++) {
            if (i >= candidate_index + 2) {
                if (sums[i] - sums[candidate_index + 1] > sums[end] - sums[begin + 1]) {
                    end = i;
                    begin = candidate_index;
                }
            } else if (sums[i] > sums[end] && i > 2) {
                end = i;
                sum = sums[end] - sums[begin] - A[begin];
            }

            if (i < A.length - 2 && sums[i + 1] < sums[candidate_index + 1]) {
                candidate_index = i;
            }

        }
        middle = begin + 1;
        for (int i = begin + 1; i < end; i++) {
            if (A[i] < A[middle]) {
                middle = i;
            }
        }
        sum = 0;
        int min = begin;
        for (int i = begin - 1; i >= 0; i--) {
            if (A[i + 1] < A[min])
                min = i + 1;

            sum += A[i + 1];
            if (sum + A[middle] - A[min] > 0) {
                middle = min;
                begin = i;
                min = i;
                sum = 0;
            } else if (sum > 0) {
                begin = i;
                sum = 0;
            }
        }

        sum = 0;
        min = end;
        for (int i = end + 1; i < A.length; i++) {
            if (A[i - 1] < A[min])
                min = i - 1;

            sum += A[i - 1];
            if (sum + A[middle] - A[min] > 0) {
                middle = min;
                end = i;
                min = i;
                sum = 0;
            } else if (sum > 0) {
                end = i;
                sum = 0;
            }
        }
        return sums[end] - sums[begin] - A[begin] - A[middle];
    }

    public int maxDoubleSliceSum2(int[] A) {
        // the answer of the example (3,4,5) is 0 is very important,it means
        // both minimal values of A[X+1] + A[X+2] + ... + A[Y-1] and A[Y+1] +
        // A[Y+2] + ... + A[Z-1] is 0.
        int N = A.length;
        if (N == 3)
            return 0;
        int maxEnding = 0;
        int maxSlice = 0;
        // stores the sum value of max slice of A[1] A[2] ... A[i] in maxSliceLeft[i]
        int[] maxSliceLeft = new int[N];
        for (int i = 1; i < N - 1; i++) {
            // maxEnding = Math.max(A[i], maxEnding + A[i]);
            maxEnding = Math.max(0, maxEnding + A[i]);
            // maxSlice = Math.max(maxSlice, maxEnding);
            // maxSliceLeft[i] = maxSlice;
            maxSliceLeft[i] = maxEnding;
        }
        // stores the sum value of max slice of A[N-2] A[N-1] ... A[i] in maxSliceRight[i]
        int[] maxSliceRight = new int[N];
        maxEnding = 0;
        maxSlice = 0;
        for (int i = N - 2; i >= 1; i--) {
            // maxEnding = Math.max(A[i], maxEnding + A[i]);
            maxEnding = Math.max(0, maxEnding + A[i]);
            // maxSlice = Math.max(maxSlice, maxEnding);
            // maxSliceRight[i] = maxSlice;
            maxSliceRight[i] = maxEnding;
        }
        int maxDoubleSlice = 0;
        // calculate the max double slice from Y == 1 to Y == N-2
        for (int i = 1; i < N - 1; i++) {
            maxDoubleSlice = Math.max(maxDoubleSlice, maxSliceLeft[i - 1] + maxSliceRight[i + 1]);
        }
        return maxDoubleSlice;
    }

    /**
     * A zero-indexed array A consisting of N integers is given. It contains daily prices of a stock share for
     * a period of N consecutive days. If a single share was bought on day P and sold on day Q, where 0 ≤ P ≤ Q < N,
     * then the profit of such transaction is equal to A[Q] − A[P], provided that A[Q] ≥ A[P].
     * Otherwise, the transaction brings loss of A[P] − A[Q].
     * <p>
     * A = {23171, 21011, 21123, 21366, 21013, 21367}
     * return 356
     * <p>
     * If a share was bought on day 0 and sold on day 2, a loss of 2048 would occur because A[2] − A[0] = 21123 − 23171 = −2048.
     * If a share was bought on day 4 and sold on day 5, a profit of 354 would occur because A[5] − A[4] = 21367 − 21013 = 354.
     * Maximum possible profit was 356. It would occur if a share was bought on day 1 and sold on day 5.
     * <p>
     * Given a zero-indexed array A consisting of N integers containing daily prices of a stock share for a period of N
     * consecutive days, returns the maximum possible profit from one transaction during this period.
     * The function should return 0 if it was impossible to gain any profit.
     * <p>
     * time complexity is O(N)
     * space complexity is O(1)
     */
    public int maxProfit(int[] A) {
        int maxSlice = 0;
        int maxEnding = 0;
        for (int i = 1; i < A.length; i++) {
            maxEnding = Math.max(0, maxEnding + (A[i] - A[i - 1]));
            maxSlice = Math.max(maxEnding, maxSlice);
        }
        return maxSlice > 0 ? maxSlice : 0;
    }

    public int maxProfit2(int[] A) {
        if (A.length < 2) {
            return 0;
        }
        int msf = 0;
        int meh = 0;
        for (int i = 1; i < A.length; i++) {
            meh = Math.max(0, meh + A[i] - A[i - 1]);
            msf = Math.max(msf, meh);
        }
        return msf > 0 ? msf : 0;
    }

    /**
     * A non-empty zero-indexed array A consisting of N integers is given. A pair of integers (P, Q), such that
     * 0 ≤ P ≤ Q < N, is called a slice of array A. The sum of a slice (P, Q) is the total of A[P] + A[P+1] + ... + A[Q].
     * Given an array A consisting of N integers, returns the maximum sum of any slice of A.
     * <p>
     * A = {3, 2, -6, 4, 0}
     * return 5 because:
     * <p>
     * (3, 4) is a slice of A that has sum 4,
     * (2, 2) is a slice of A that has sum −6,
     * (0, 1) is a slice of A that has sum 5,
     * no other slice of A has sum greater than (0, 1).
     * <p>
     * time complexity is O(N);
     * space complexity is O(N)
     */
    public int maxSliceSum(int[] A) {
        double meh = Integer.MIN_VALUE; //-2147483648;
        double msf = Integer.MIN_VALUE; //-2147483648;
        for (int i : A) {
            meh = Math.max(i, meh + i);
            msf = Math.max(msf, meh);
        }
        return (int) msf;
    }

    public int maxSliceSum2(int[] A) {
        // write your code in Java SE 8
        // using the Kadane's algorithm will make the task very simple
        int N = A.length;
        int maxEnding = 0;
        int maxSlice = Integer.MIN_VALUE; //-1000000;
        for (int ele : A) {
            maxEnding = Math.max(ele, maxEnding + ele);
            maxSlice = Math.max(maxSlice, maxEnding);
        }
        return maxSlice;
    }

    /**
     * Given three integers A, B and K, returns the number of integers within the range [A..B] that are divisible by K,
     * i.e.: { i : A ≤ i ≤ B, i mod K = 0 }
     * <p>
     * For example, for A = 6, B = 11 and K = 2, your function should return 3, because there are three numbers
     * divisible by 2 within the range [6..11], namely 6, 8 and 10.
     * <p>
     * time complexity is O(1)
     * space complexity is O(1)
     */
    public int countDiv(int A, int B, int K) {
        // be careful with the situation that A is divisible by K
        int lower = A % K == 0 ? A / K - 1 : A / K;
        int upper = B / K;
        return upper - lower;
    }

    public static int countDiv2(int A, int B, int K) {
        /*
        * If the first range element is a multiple of K: add 1 to the result
        * If the difference between start and end of the range is bigger or equal to K:
        * add the multiple difference to the result
        */
        int result = 0;

        if (A % K == 0)
            result += 1;
        if (B - A >= K)
            result += B / K - A / K;

        return result;
    }

    public int countDiv3(int A, int B, int K) {
        /*
        * (A-1)/K is the sum of all the occurrences of the numbers <=(A-1) that are divisible by K.
        * B/K is the sum of all the occurrences of the numbers <= B that are divisible by K.
        * To know the sum of all the occurrences of the numbers between A and B that are divisible by K, just subtract (A-1)/K to B/K.
        * And remember that 0 is divisible by any non-zero number.
        */
        return Math.abs((A - 1) / K - B / K);
    }


    /**
     * A DNA sequence can be represented as a string consisting of the letters A, C, G and T, which correspond to
     * the types of successive nucleotides in the sequence. Each nucleotide has an impact factor, which is an integer.
     * Nucleotides of types A, C, G and T have impact factors of 1, 2, 3 and 4, respectively.
     * You are going to answer several queries of the form: What is the minimal impact factor of nucleotides contained
     * in a particular part of the given DNA sequence?
     * <p>
     * The DNA sequence is given as a non-empty string S = S[0]S[1]...S[N-1] consisting of N characters.
     * There are M queries, which are given in non-empty arrays P and Q, each consisting of M integers.
     * The K-th query (0 ≤ K < M) requires you to find the minimal impact factor of nucleotides contained in the DNA
     * sequence between positions P[K] and Q[K] (inclusive).
     * <p>
     * For example, consider string S = CAGCCTA and arrays P, Q such that:
     * P = {2, 5, 0}
     * Q = {4, 5, 6}
     * return the values [2, 4, 1]
     * <p>
     * The answers to these M = 3 queries are as follows:
     * The part of the DNA between positions 2 and 4 contains nucleotides G and C (twice),
     * whose impact factors are 3 and 2 respectively, so the answer is 2.
     * The part between positions 5 and 5 contains a single nucleotide T, whose impact factor is 4, so the answer is 4.
     * The part between positions 0 and 6 (the whole string) contains all nucleotides, in particular nucleotide A whose
     * impact factor is 1, so the answer is 1.
     * <p>
     * Given a non-empty zero-indexed string S consisting of N characters and two non-empty zero-indexed arrays P and Q
     * consisting of M integers, returns an array consisting of M integers specifying the consecutive answers to all queries.
     * <p>
     * each element of arrays P, Q is an integer within the range [0..N − 1];
     * P[K] ≤ Q[K], where 0 ≤ K < M;
     * string S consists only of upper-case English letters A, C, G, T.
     * <p>
     * time complexity is O(N+M)
     * space complexity is O(N)
     */
    public int[] genomicRangeQuery(String S, int[] P, int[] Q) {
        int aLen = S.length();

        char[] SAsCharArray = S
                .replace('A', '1')
                .replace('C', '2')
                .replace('G', '3')
                .replace('T', '4')
                .toCharArray();

        int[] A = new int[aLen];
        for (int i = 0; i < aLen; i++) {
            A[i] = (int) (Character.getNumericValue(SAsCharArray[i]));
        }

        int M = P.length;
        int[] result = new int[M];
        int[][] F = new int[aLen + 1][4];
        int sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0;

        for (int i = 0; i < aLen; i++) {
            switch (A[i]) {
                case 1:
                    sum1 += 1;
                    break;
                case 2:
                    sum2 += 1;
                    break;
                case 3:
                    sum3 += 1;
                    break;
                case 4:
                    sum4 += 1;
                    break;
            }

            F[i + 1][0] = sum1;
            F[i + 1][1] = sum2;
            F[i + 1][2] = sum3;
            F[i + 1][3] = sum4;
        }

        for (int i = 0; i < M; i++) {
            int rangeLow = P[i];
            int rangeHigh = Q[i];

            for (int j = 0; j < 4; j++) {
                if (F[rangeHigh + 1][j] - F[rangeLow][j] > 0) {
                    result[i] = j + 1;
                    break;
                }
            }
        }

        return result;
    }

    public int[] genomicRangeQuery2(String S, int[] P, int[] Q) {
        // write your code in Java SE 8
        int N = S.length();
        int M = P.length;
        int[] result = new int[M];
        // store the position of A in the String S
        int[] posOfA = new int[N + 1];
        int[] posOfC = new int[N + 1];
        int[] posOfG = new int[N + 1];
        int[] posOfT = new int[N + 1];
        for (int i = 0; i < N; i++) {
            // when A appears, assign 1 to the value of posOfA with the same index.
            if (S.charAt(i) == 'A')
                posOfA[i + 1] = 1;
            if (S.charAt(i) == 'C')
                posOfC[i + 1] = 1;
            if (S.charAt(i) == 'G')
                posOfG[i + 1] = 1;
            if (S.charAt(i) == 'T')
                posOfT[i + 1] = 1;
        }
        for (int i = 1; i <= N; i++) {
            // prefix sum of the array
            posOfA[i] += posOfA[i - 1];
            posOfC[i] += posOfC[i - 1];
            posOfG[i] += posOfG[i - 1];
            posOfT[i] += posOfT[i - 1];
        }
        for (int i = 0; i < M; i++) {
            // calculate the sum between P[i] and Q[i] in O(1) time complexity
            // if the value is not zero, then A has appeared in the slice.
            if ((posOfA[Q[i] + 1] - posOfA[P[i]]) != 0)
                result[i] = 1;
            else if ((posOfC[Q[i] + 1] - posOfC[P[i]]) != 0)
                result[i] = 2;
            else if ((posOfG[Q[i] + 1] - posOfG[P[i]]) != 0)
                result[i] = 3;
            else if ((posOfT[Q[i] + 1] - posOfT[P[i]]) != 0)
                result[i] = 4;
        }
        return result;
    }

    /**
     * A non-empty zero-indexed array A consisting of N integers is given. A pair of integers (P, Q), such that
     * 0 ≤ P < Q < N, is called a slice of array A (notice that the slice contains at least two elements).
     * The average of a slice (P, Q) is the sum of A[P] + A[P + 1] + ... + A[Q] divided by the length of the slice.
     * To be precise, the average equals (A[P] + A[P + 1] + ... + A[Q]) / (Q − P + 1).
     * <p>
     * A = {4, 2, 2, 5, 1, 5, 8}
     * return 1
     * <p>
     * contains the following example slices:
     * slice (1, 2), whose average is (2 + 2) / 2 = 2;
     * slice (3, 4), whose average is (5 + 1) / 2 = 3;
     * slice (1, 4), whose average is (2 + 2 + 5 + 1) / 4 = 2.5.
     * The goal is to find the starting position of a slice whose average is minimal.
     * <p>
     * Given a non-empty zero-indexed array A consisting of N integers, returns the starting position of the slice
     * with the minimal average. If there is more than one slice with a minimal average, you should return the smallest
     * starting position of such a slice.
     * <p>
     * time complexity is O(N)
     * space complexity is O(N)
     */
    public int minAvgTwoSlice(int[] A) {

        int pos = 0;
        float min = 10000 * 3 + 1;
        for (int i = 0; i < A.length - 1; i++) {
            if ((A[i] + A[i + 1]) / 2f < min) {
                min = (A[i] + A[i + 1]) / 2f;
                pos = i;
            }
        }
        for (int i = 0; i < A.length - 2; i++) {
            if ((A[i] + A[i + 1] + A[i + 2]) / 3f < min) {
                min = (A[i] + A[i + 1] + A[i + 2]) / 3f;
                pos = i;
            }
        }
        return pos;

    }

    // this solution is based on the fact that there must be minimal average slice
    // of length 2 or 3 in all the minimal average slices.
    // we can use the slice of length 4 and 5 to prove it.
    // if there is a slice of length 4 is the minimal average slice, then
    // (a1+a2+a3+a4)/4 <= (a1+a2)/2 -> a3+a4 <= a1+a2
    // (a1+a2+a3+a4)/4 <= (a3+a4)/2 -> a1+a2 <= a3+a4
    // so a1+a2 = a3+a4 -> (a1+a2+a3+a4)/4 = (a1+a2)/2
    // if there is a slice of length 5 is the minimal average slice, then
    // we can use the same method to prove it.
    // it's easy to conclude that (a1+a2+a3+a4+a5)/5 = (a1+a2)/2 = (a3+a4+a5)/3.
    public int minAvgTwoSlice2(int[] A) {
        // write your code in Java SE 8
        int N = A.length;
        int[] sum = new int[N + 1];
        // calculate the prefix sum of array A
        for (int i = 0; i < N; i++)
            sum[i + 1] = sum[i] + A[i];
        int minTwoSum = 20000;
        int minTwoStartIndex = 0;
        // calculate the minimal average slice of length 2
        for (int i = 0; i < N - 1; i++) {
            // the average is not been used because the division of integer in
            // Java will cut the decimal part and it will make the comparison
            // inaccurately.
            int twoSum = (sum[i + 2] - sum[i]);
            if (twoSum < minTwoSum) {
                minTwoSum = twoSum;
                minTwoStartIndex = i;
            }
        }
        int minThreeSum = 30000;
        int minThreeStartIndex = 0;
        // calculate the minimal average slice of length 3
        for (int i = 0; i < N - 2; i++) {
            int threeSum = (sum[i + 3] - sum[i]);
            if (threeSum < minThreeSum) {
                minThreeSum = threeSum;
                minThreeStartIndex = i;
            }
        }
        // return the minimal result from slice of length 2 or 3
        if (minTwoSum * 3 <= minThreeSum * 2)
            return minTwoStartIndex;
        else
            return minThreeStartIndex;
    }


}
