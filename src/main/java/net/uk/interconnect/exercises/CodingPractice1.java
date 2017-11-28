package net.uk.interconnect.exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CodingPractice1 {

    /**
     * A zero-indexed array A consisting of N integers is given. Rotation of the array means that each element
     * is shifted right by one index, and the last element of the array is also moved to the first place.
     * For example, the rotation of array A = [3, 8, 9, 7, 6] is [6, 3, 8, 9, 7]. The goal is to rotate array A K times;
     * that is, each element of A will be shifted to the right by K indexes.
     *
     * Given a zero-indexed array A consisting of N integers and an integer K, returns the array A rotated K times.
     * <p>
     * For example, given array A = [3, 8, 9, 7, 6] and K = 3,
     * the function should return [9, 7, 6, 3, 8].
     *
     * In your solution, focus on correctness.
     */
    public int[] arrayCyclicRotation(int[] tab, int k) {
        int[] tab2 = new int[tab.length];
        for (int i = 0; i < tab.length; i++) {
            if (i + k >= tab.length)
                tab2[(i + k) % (tab.length)] = tab[i];
            else
                tab2[i + k] = tab[i];
        }
        return tab2;
    }


    /**
     * A non-empty zero-indexed array A consisting of N integers is given. The array contains an odd
     * number of elements, and each element of the array can be paired with another element that
     * has the same value, except for one element that is left unpaired.
     *
     * A = {9, 3, 9, 3, 9, 7, 9}
     * the function should return 7
     *
     * the elements at indexes 0 and 2 have value 9,
     * the elements at indexes 1 and 3 have value 3,
     * the elements at indexes 4 and 6 have value 9,
     * the element at index 5 has value 7 and is unpaired.
     *
     * Given an array A consisting of N integers fulfilling the above conditions,
     * returns the value of the unpaired element.
     *
     * all but one of the values in A occur an even number of times.
     *
     * time complexity is O(N);
     * space complexity is O(1)
     */
    public int findOddElementInArray(int[] A) {
        int odd = 0;

        for (int i = 0; i < A.length; ++i)
            odd ^= A[i];

        return odd;
    }


    /**
     * Divide array A into K blocks and minimize the largest sum of any block.
     * You are given integers K, M and a non-empty zero-indexed array A consisting of N integers.
     * Every element of the array is not greater than M.
     * You should divide this array into K blocks of consecutive elements.
     * The size of the block is any integer between 0 and N. Every element of the array should belong to some block.
     * The sum of the block from X to Y equals A[X] + A[X + 1] + ... + A[Y]. The sum of empty block equals 0.
     * The large sum is the maximal sum of any block.
     *
     * For example, you are given integers K = 3, M = 5 and array A such that:
     *
     *  A = {2,1,5,1,2,2,2}
     *  the function should return 6
     *
     * The array can be divided, for example, into the following blocks:
     * [2, 1, 5, 1, 2, 2, 2], [], [] with a large sum of 15;
     * [2], [1, 5, 1, 2], [2, 2] with a large sum of 9;
     * [2, 1, 5], [], [1, 2, 2, 2] with a large sum of 8;
     * [2, 1], [5, 1], [2, 2, 2] with a large sum of 6.
     * The goal is to minimize the large sum. In the above example, 6 is the minimal large sum.
     *
     * Given integers K, M and a non-empty zero-indexed array A consisting of N integers, returns the minimal large sum.
     *
     * time complexity is O(N*log(N+M));
     * space complexity is O(1)
     */
    public int divideArrayIntoMinMaxSumBlocks(int K, int M, int[] A) {

        int left = 0, right = 0;

        for (int val : A) {
            left = Math.max(left, val);
            right += val;
        }

        while (left <= right) {
            int mid = (left + right) >> 1; // divided by 2
            if (isValid(A, mid, K)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left; //right + 1;
    }

    public boolean isValid(int[] a, int mid, int k) {
        int sum = 0;
        --k;
        for (int i = 0; i < a.length; ) {
            if ((sum += a[i]) > mid) {
                sum = 0;
                if (--k < 0) {
                    return false;
                }
            } else {
                ++i;
            }
        }
        return true;
    }


    /**
     * Count the minimum number of nails that allow a series of planks to be nailed.
     * You are given two non-empty zero-indexed arrays A and B consisting of N integers. These arrays represent N planks.
     * More precisely, A[K] is the start and B[K] the end of the K−th plank.
     * Next, you are given a non-empty zero-indexed array C consisting of M integers. This array represents M nails.
     * More precisely, C[I] is the position where you can hammer in the I−th nail.
     * We say that a plank (A[K], B[K]) is nailed if there exists a nail C[I] such that A[K] ≤ C[I] ≤ B[K].
     *
     * The goal is to find the minimum number of nails that must be used until all the planks are nailed.
     * In other words, you should find a value J such that all planks will be nailed after using only the first J nails.
     * More precisely, for every plank (A[K], B[K]) such that 0 ≤ K < N, there should exist a nail C[I] such that I < J
     * and A[K] ≤ C[I] ≤ B[K].
     *
     * A = {1, 4, 5, 8}
     * B = {4, 5, 9, 10}
     *
     * four planks are represented: [1, 4], [4, 5], [5, 9] and [8, 10].
     *
     * Given array C such that:
     * C = {4, 6, 7, 10, 2}
     *
     * the function should return 4
     *
     * if we use the following nails:
     * 0, then planks [1, 4] and [4, 5] will both be nailed.
     * 0, 1, then planks [1, 4], [4, 5] and [5, 9] will be nailed.
     * 0, 1, 2, then planks [1, 4], [4, 5] and [5, 9] will be nailed.
     * 0, 1, 2, 3, then all the planks will be nailed.
     *
     * Thus, four is the minimum number of nails that, used sequentially, allow all the planks to be nailed.
     *
     * Given two non-empty zero-indexed arrays A and B consisting of N integers and a non-empty zero-indexed
     * array C consisting of M integers, returns the minimum number of nails that, used sequentially,
     * allow all the planks to be nailed.
     *
     * If it is not possible to nail all the planks, the function should return −1.
     *
     * N and M are integers within the range [1..30,000];
     * each element of arrays A, B, C is an integer within the range [1..2*M];
     * A[K] ≤ B[K].
     *
     * time complexity is O((N+M)*log(M));
     * space complexity is O(M)
     */
    public int nailingPlanks(int[] A, int[] B, int[] C) {
        int b = 0;
        int e = C.length;
        int total = -1;
        if (check(A, B, C, C.length)) {
            while (b <= e) {
                int mid = (b + e) / 2;
                if (check(A, B, C, mid)) {
                    total = mid;
                    e = mid - 1;
                } else {
                    b = mid + 1;
                }
            }
        }
        return total;
    }

    private boolean check(int[] A, int[] B, int[] C, int c) {
        int max = 2 * C.length + 1;
        int[] prefixSum = new int[max];

        for (int i = 0; i < c; i++) {
            //copy nails to c
            prefixSum[C[i]] += 1;
        }

        for (int i = 1; i < max; i++) {
            prefixSum[i] += prefixSum[i - 1];
        }

        for (int i = 0; i < A.length; i++) {
            if (prefixSum[A[i] - 1] - prefixSum[B[i]] == 0) {
                return false; // no nail in this range
            }
        }

        return true;
    }


    /**
     * Compute number of distinct absolute values of sorted array elements.
     * A non-empty zero-indexed array A consisting of N numbers is given. The array is sorted in non-decreasing order.
     * The absolute distinct count of this array is the number of distinct absolute values among the elements of the array.
     * For example, consider array A such that:
     *
     * A = {-5, -3, -1, 0, 3, 6}
     * the function should return 5
     *
     * The absolute distinct count of this array is 5, because there are 5 distinct absolute values among the elements
     * of this array, namely 0, 1, 3, 5 and 6.
     * Given a non-empty zero-indexed array A consisting of N numbers, returns absolute distinct count of array A.
     *
     * N is an integer within the range [1..100,000];
     * each element of array A is an integer within the range [−2,147,483,648..2,147,483,647];
     * array A is sorted in non-decreasing order.
     *
     * time complexity is O(N);
     * space complexity is O(N)
     *
     */
    public int absDistinct(int[] A) {
        return Arrays.stream(A).map(i -> Math.abs(i))
                .boxed().collect(Collectors.toCollection(HashSet::new)).size();
    }


    /**
     * Count the number of distinct slices (containing only unique numbers).
     * An integer M and a non-empty zero-indexed array A consisting of N non-negative integers are given.
     * All integers in array A are less than or equal to M.
     * A pair of integers (P, Q), such that 0 ≤ P ≤ Q < N, is called a slice of array A. The slice consists of
     * the elements A[P], A[P + 1], ..., A[Q]. A distinct slice is a slice consisting of only unique numbers.
     * That is, no individual number occurs more than once in the slice.
     * For example, consider integer M = 6 and array A such that:
     *
     * A = {3, 4, 5, 5, 2}
     * the function should return 9
     *
     * There are exactly nine distinct slices: (0, 0), (0, 1), (0, 2), (1, 1), (1, 2), (2, 2), (3, 3), (3, 4) and (4, 4).
     * The goal is to calculate the number of distinct slices.
     *
     * Given an integer M and a non-empty zero-indexed array A consisting of N integers, returns the number of distinct slices.
     * If the number of distinct slices is greater than 1,000,000,000, the function should return 1,000,000,000.
     *
     * N is an integer within the range [1..100,000];
     * M is an integer within the range [0..100,000];
     * each element of array A is an integer within the range [0..M].
     *
     * time complexity is O(N);
     * space complexity is O(M)
     */
    public int countDistinctSlices(int M, int[] A) {
        // the algorithm is that for each tail the head points at the most right
        // place where there is no same element between tail and head.we know
        // that the worst case is that all the elements of the array are the
        // same, and at this circumstance the time complexity is O(2*N).
        int N = A.length;
        // the counter array for every element in the array A
        int[] counters = new int[M + 1];
        int head = 0;
        int tail = 0;
        int result = 0;
        while (tail < N) {
            // find the most right end of the array for each tail
            while (head < N && counters[A[head]] != 2) {
                counters[A[head]]++;
                if (counters[A[head]] == 2)
                    break;
                head++;
            }
            result += head - tail;
            if (result > 1000000000)
                return 1000000000;
            // set the counter of the element before tail to be 0
            counters[A[tail]] = 0;
            tail++;
        }
        return result;
    }

    /**
     * Count the number of triangles that can be built from a given set of edges.
     * A zero-indexed array A consisting of N integers is given. A triplet (P, Q, R) is triangular if it is possible
     * to build a triangle with sides of lengths A[P], A[Q] and A[R]. In other words, triplet (P, Q, R) is triangular
     * if 0 ≤ P < Q < R < N and:
     *
     * A[P] + A[Q] > A[R],
     * A[Q] + A[R] > A[P],
     * A[R] + A[P] > A[Q].
     *
     * For example, consider array A such that:
     *
     * A = {10, 2, 5, 1, 8, 12}
     * the function should return 4
     *
     * There are four triangular triplets that can be constructed from elements of this array,
     * namely (0, 2, 4), (0, 2, 5), (0, 4, 5), and (2, 4, 5).
     *
     * Given a zero-indexed array A consisting of N integers, returns the number of triangular triplets in this array.
     *
     * N is an integer within the range [0..1,000];
     * each element of array A is an integer within the range [1..1,000,000,000].
     *
     * time complexity is O(N2);
     * space complexity is O(N)
     */
    public int countTriangles(int[] A) {
        // The time complexity of the above algorithm is O(n^2),
        // because for every i the values of j and k increase O(n) number of times.
        int N = A.length;
        if (N < 3)
            return 0;
        // use the built in sort algorithm which can perform worst case
        // O(N*log(N)) time complexity
        Arrays.sort(A);
        int result = 0;
        for (int i = 0; i < N; i++) {
            int k = i + 1;
            for (int j = i + 1; j < N; j++) {
                // for every i and j we figure out the maximal k that can be a
                // triangular, and when we increase j the former k would still
                // be a triangular because of the sorted array, so we just need
                // to count the number of triangular between j and k.
                while (k < N && A[i] + A[j] > A[k])
                    k++;
                result += k - j - 1;
            }
        }
        return result;
    }

    /**
     * Find the minimal absolute value of a sum of two elements.
     * Let A be a non-empty zero-indexed array consisting of N integers.
     * The abs sum of two for a pair of indices (P, Q) is the absolute value |A[P] + A[Q]|, for 0 ≤ P ≤ Q < N.
     * For example, the following array A:
     *
     * A = {1, 4, -3}
     * the function should return 1
     *
     * A = {-8, 4, 5, -10, 3}
     * the function should return |(−8) + 5| = 3
     *
     * has pairs of indices (0, 0), (0, 1), (0, 2), (1, 1), (1, 2), (2, 2).
     * The abs sum of two for the pair (0, 0) is A[0] + A[0] = |1 + 1| = 2.
     * The abs sum of two for the pair (0, 1) is A[0] + A[1] = |1 + 4| = 5.
     * The abs sum of two for the pair (0, 2) is A[0] + A[2] = |1 + (−3)| = 2.
     * The abs sum of two for the pair (1, 1) is A[1] + A[1] = |4 + 4| = 8.
     * The abs sum of two for the pair (1, 2) is A[1] + A[2] = |4 + (−3)| = 1.
     * The abs sum of two for the pair (2, 2) is A[2] + A[2] = |(−3) + (−3)| = 6.
     *
     * Given a non-empty zero-indexed array A consisting of N integers, returns the minimal abs sum of two for
     * any pair of indices in this array.
     *
     * N is an integer within the range [1..100,000];
     * each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].
     *
     * time complexity is O(N*log(N));
     * space complexity is O(N)
     */
    public int minAbsSumOfTwo(int[] A) {
        // the original version is at
        // http://codesays.com/2014/solution-to-min-abs-sum-of-two-by-codility/
        int N = A.length;
        Arrays.sort(A);
        int tail = 0;
        int head = N - 1;
        int minAbsSum = Math.abs(A[tail] + A[head]);
        while (tail <= head) {
            int currentSum = A[tail] + A[head];
            minAbsSum = Math.min(minAbsSum, Math.abs(currentSum));
            // it's very clever to compare to 0 because if the sum has become
            // positive, we should know that the head can be moved left
            if (currentSum <= 0)
                tail++;
            else
                head--;
        }
        return minAbsSum;
    }


    /**
     * A small frog wants to get to the other side of a river.
     * The frog is initially located on one bank of the river (position 0) and wants to get to the opposite
     * bank (position X+1). Leaves fall from a tree onto the surface of the river.
     * You are given a zero-indexed array A consisting of N integers representing the falling leaves.
     * A[K] represents the position where one leaf falls at time K, measured in seconds.
     *
     * The goal is to find the earliest time when the frog can jump to the other side of the river.
     * The frog can cross only when leaves appear at every position across the river from 1 to X (that is,
     * we want to find the earliest moment when all the positions from 1 to X are covered by leaves).
     * You may assume that the speed of the current in the river is negligibly small, i.e. the leaves do not change
     * their positions once they fall in the river.
     *
     * For example, you are given integer X = 5 and array A such that:
     *
     * A = {1, 3, 1, 4, 2, 3, 5, 4}
     * the function should return 6
     *
     * In second 6, a leaf falls into position 5. This is the earliest time when leaves appear in every position across the river.
     * Given a non-empty zero-indexed array A consisting of N integers and integer X, returns the earliest time when
     * the frog can jump to the other side of the river.
     * If the frog is never able to jump to the other side of the river, the function should return −1.
     *
     * N and X are integers within the range [1..100,000];
     * each element of array A is an integer within the range [1..X].
     *
     * time complexity is O(N);
     * space complexity is O(X)
     */
    public int frogRiverOne(int X, int[] A) {
        // Create a X length array: all elements are 0.
        // Iterate the A array: if current A value, index for X, has not been found before, then add the value 1
        // to the element at the corresponding index and add one to a sum variable.
        // When X and sum are equal, all leaves are in place.
        int time = -1;
        int[] jumps = new int[X];
        int sum = 0;

        for (int i = 0; i < A.length; ++i) {

            int current = A[i] - 1;

            if (jumps[current] == 0) {
                jumps[current] = 1;
                sum += 1;
            }

            if (sum == X) {
                time = i;
                break;
            }
        }

        return time;
    }

    /**
     * A small frog wants to get to the other side of the road. The frog is currently located at position X and wants
     * to get to a position greater than or equal to Y. The small frog always jumps a fixed distance, D.
     * Count the minimal number of jumps that the small frog must perform to reach its target.
     *
     * Given three integers X, Y and D, returns the minimal number of jumps from position X to a position equal
     * to or greater than Y.
     *
     * X = 10
     * Y = 85
     * D = 30
     * the function should return 3, because the frog will be positioned as follows:
     *
     * after the first jump, at position 10 + 30 = 40
     * after the second jump, at position 10 + 30 + 30 = 70
     * after the third jump, at position 10 + 30 + 30 + 30 = 100
     *
     * X ≤ Y.
     *
     * time complexity is O(1);
     * space complexity is O(1).
     */
    public int frogJump(int X, int Y, int D){
        return (int)Math.ceil(1.0d * (Y - X) / D);
    }

    public int frogJump2(int X, int Y, int D) {
        // the frog can jump from position X to a position equal to Y
        if ((Y-X) % D == 0)
            return (Y-X) / D;
            // the frog can only jump from position X to a position greater than Y
        else
            return (Y-X) / D + 1;
    }


    /**
     * int[] A = {0, 0, 4, 2, 4, 5};
     * int[] B = counting(A, 5);
     */
    public boolean countingElements(int[] A, int[] B, int m) {
        int n = A.length;
        int sumA = IntStream.of(A).sum();
        int sumB = IntStream.of(A).sum();

        int diff = sumB - sumA;
        if (diff % 2 == 1)
            return false;

        diff /= 2;

        int[] count = counting(A, m);

        for (int i = 0; i < n; ++i)
            if ((0 <= (B[i] - diff)) && ((B[i] - diff) <= m) && count[B[i] - diff] > 0)
                return true;

        return false;
    }

    public int[] counting(int[] A, int m) {

        int[] count = new int[m + 1];

        int n = A.length;

        for (int i = 0; i < A.length; ++i) {
            count[A[i]] += 1;
        }

        return count;
    }

    /**
     * You are given N counters, initially set to 0, and you have two possible operations on them:
     * increase(X) − counter X is increased by 1,
     * max counter − all counters are set to the maximum value of any counter.
     * A non-empty zero-indexed array A of M integers is given. This array represents consecutive operations:
     * if A[K] = X, such that 1 ≤ X ≤ N, then operation K is increase(X),
     * if A[K] = N + 1 then operation K is max counter.
     * For example, given integer N = 5 and array A such that:
     *
     * A = {3, 4, 4, 6, 1, 4, 4}
     * the function should return the sequence as:  [3, 2, 2, 4, 2]
     *
     * the values of the counters after each consecutive operation will be:
     * (0, 0, 1, 0, 0)
     * (0, 0, 1, 1, 0)
     * (0, 0, 1, 2, 0)
     * (2, 2, 2, 2, 2)
     * (3, 2, 2, 2, 2)
     * (3, 2, 2, 3, 2)
     * (3, 2, 2, 4, 2)
     * The goal is to calculate the value of every counter after all operations.
     *
     * Given an integer N and a non-empty zero-indexed array A consisting of M integers, returns a sequence
     * of integers representing the values of the counters.
     *
     * N and M are integers within the range [1..100,000];
     * each element of array A is an integer within the range [1..N + 1].
     *
     * time complexity is O(N+M);
     * space complexity is O(N)
     */
    public int[] maxCounters(int N, int[] A) {
        int M = A.length;
        int[] counters = new int[N];
        int maxValue = 0; // store the max value in the counters array
        int currMax = 0; // store the max value when there is a max counter
        for (int i = 0; i < M; i++) {
            if (A[i] >= 1 && A[i] <= N) {
                // max counter
                if (counters[A[i] - 1] < currMax)
                    counters[A[i] - 1] = currMax;
                // increase (X)
                counters[A[i] - 1]++;
                if (counters[A[i] - 1] > maxValue)
                    maxValue = counters[A[i] - 1];
            }
            // update current max value when there is a max counter
            if (A[i] == N + 1) {
                currMax = maxValue;
            }
        }
        for (int i = 0; i < N; i++) {
            // update the rest elements of counters which aren't assigned above.
            if (counters[i] < currMax)
                counters[i] = currMax;
        }
        return counters;
    }


    /**
     * Given a non-empty zero-indexed array A of N integers, returns the minimal positive integer
     * (greater than 0) that does not occur in A.
     * For example, given:
     *
     * A = {1, 3, 6, 4, 1, 2}
     * the function should return 5
     *
     * N is an integer within the range [1..100,000];
     * each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
     *
     * time complexity is O(N);
     * space complexity is O(N)
     */
    public int missingInteger(int[] A) {
        int N = A.length;
        // count array of 1 2 ... N.
        boolean[] count = new boolean[N + 1];
        for (int element : A)
            // assign true to the count array if the element of A is from 1 to N
            // if it has already appeared before then ignore it.
            if (element >= 1 && element <= N && count[element] == false)
                count[element] = true;
        // find the minimal positive integer by scanning the count array from left
        // to right, if there is an element which is false, then it's the result.
        for (int i = 1; i <= N; i++)
            if (count[i] == false)
                return i;
        // all the integer from 1 to N has appeared,then the result is N+1.
        return N + 1;
    }

    /**
     * A non-empty zero-indexed array A consisting of N integers is given.
     * A permutation is a sequence containing each element from 1 to N once, and only once.
     * For example, array A such that:
     *
     * A = {4, 1, 3, 2} - is a permutation
     * the function should return 1
     *
     * B = {4, 1, 3}    - is not a permutation, because value 2 is missing.
     * the function should return 0
     *
     * The goal is to check whether array A is a permutation.
     * Given a zero-indexed array A, returns 1 if array A is a permutation and 0 if it is not.
     *
     * N is an integer within the range [1..100,000];
     * each element of array A is an integer within the range [1..1,000,000,000].
     *
     * time complexity is O(N);
     * space complexity is O(N)
     */
    public int permutationCheck(int[] A) {
        int n = A.length;
        int[] B = new int[n + 1];

        for (int i = 0; i < A.length; ++i)
            if (A[i] > 0 && A[i] <= n)
                B[A[i]] += 1;

        for (int j = 1; j < B.length; ++j)
            if (B[j] > 1 || (B[j] == 0))
                return 0;

        return 1;
    }

    /**
     * Given array of integers, find the lowest absolute sum of elements.
     * For a given array A of N integers and a sequence S of N integers from the set {−1, 1}, we define val(A, S) as follows:
     * val(A, S) = |sum{ A[i]*S[i] for i = 0..N−1 }|
     * (Assume that the sum of zero elements equals zero.)
     *
     * For a given array A, we are looking for such a sequence S that minimizes val(A,S).
     * Given an array A of N integers, computes the minimum value of val(A,S) from all possible values of val(A,S)
     * for all possible sequences S of N integers from the set {−1, 1}.
     * For example, given array:
     *
     * A = {1, 5, 2, 2}
     *
     * your function should return 0, since for S = [−1, 1, −1, 1], val(A, S) = 0, which is the minimum possible value.
     *
     * N is an integer within the range [0..20,000];
     * each element of array A is an integer within the range [−100..100].
     *
     * time complexity is O(N*max(abs(A))2);
     * space complexity is O(N+sum(abs(A)))
     */
    public int minAbsSum(int[] A) {
        if (A.length < 1) {
            return 0;
        }
        int N = A.length;
        int max = 0;
        int sum = 0;

        for (int i = 0; i < N; i++) {
            A[i] = Math.abs(A[i]);
            max = max < A[i] ? A[i] : max;
            sum += A[i];
        }

        int[] count = new int[max + 1];
        for (int i = 0; i < N; i++) {
            count[A[i]]++;
        }

        int[] dp = new int[sum + 1];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = -1;
        }

        for (int i = 1; i <= max; i++) {
            if (count[i] <= 0) {
                continue;
            }
            for (int j = 0; j <= sum; j++) {
                if (dp[j] >= 0) {
                    dp[j] = count[i];
                } else if (j >= i && dp[j - i] > 0) {
                    dp[j] = dp[j - i] - 1;
                }
            }
        }

        int result = sum;
        for (int p = 0; p * 2 <= sum; p++) {
            if (dp[p] >= 0) {
                int q = sum - p;
                int diff = q - p;
                result = diff < result ? diff : result;
            }
        }
        return result;
    }

    /**
     * In a given array, find the subset of maximal sum in which the distance between consecutive elements is at most 6.
     * A game for one player is played on a board consisting of N consecutive squares, numbered from 0 to N − 1.
     * There is a number written on each square. A non-empty zero-indexed array A of N integers contains the numbers
     * written on the squares. Moreover, some squares can be marked during the game.
     *
     * At the beginning of the game, there is a pebble on square number 0 and this is the only square on the board
     * which is marked. The goal of the game is to move the pebble to square number N − 1.
     *
     * During each turn we throw a six-sided die, with numbers from 1 to 6 on its faces, and consider the number K,
     * which shows on the upper face after the die comes to rest. Then we move the pebble standing on square
     * number I to square number I + K, providing that square number I + K exists. If square number I + K does not exist,
     * we throw the die again until we obtain a valid move. Finally, we mark square number I + K.
     *
     * After the game finishes (when the pebble is standing on square number N − 1), we calculate the result.
     * The result of the game is the sum of the numbers written on all marked squares.
     * For example, given the following array:
     *
     * A = {1, -2, 0, 9, -1, -2}
     * the function should return 8
     *
     * one possible game could be as follows:
     * the pebble is on square number 0, which is marked;
     * we throw 3; the pebble moves from square number 0 to square number 3; we mark square number 3;
     * we throw 5; the pebble does not move, since there is no square number 8 on the board;
     * we throw 2; the pebble moves to square number 5; we mark this square and the game ends.
     *
     * The marked squares are 0, 3 and 5, so the result of the game is 1 + 9 + (−2) = 8.
     * This is the maximal possible result that can be achieved on this board.
     *
     * Given a non-empty zero-indexed array A of N integers, returns the maximal result that can be achieved
     * on the board represented by array A.
     *
     * N is an integer within the range [2..100,000];
     * each element of array A is an integer within the range [−10,000..10,000].
     *
     * time complexity is O(N);
     * space complexity is O(N)
     */
    public int numberSolitaire(int[] A) {
        int n = A.length;
        int[] dp = new int[n];
        dp[0] = A[0];
        for (int i = 1; i < n; i++) {
            int max = dp[i - 1] + A[i];
            for (int j = 1; j <= 6; j++) {
                if (i - j >= 0) {
                    max = Math.max(dp[i - j] + A[i], max);
                }
            }
            dp[i] = max;
        }
        return dp[n - 1];
    }

    /**
     * Two positive integers N and M are given. Integer N represents the number of chocolates arranged in a circle,
     * numbered from 0 to N − 1.
     * You start to eat the chocolates. After eating a chocolate you leave only a wrapper.
     * You begin with eating chocolate number 0. Then you omit the next M − 1 chocolates or wrappers on the circle,
     * and eat the following one.
     * More precisely, if you ate chocolate number X, then you will next eat the chocolate with number (X + M) modulo N
     * (remainder of division).
     * You stop eating when you encounter an empty wrapper.
     * For example, given integers N = 10 and M = 4.  You will eat the following chocolates: 0, 4, 8, 2, 6.
     * The function should return 5, as explained above.
     * The goal is to count the number of chocolates that you will eat, following the above rules.
     * Given two positive integers N and M, returns the number of chocolates that you will eat.
     *
     * N and M are integers within the range [1..1,000,000,000].
     *
     * time complexity is O(log(N+M));
     * space complexity is O(log(N+M)).
     */
    public int chocolatesByNumbers(int N, int M) {
        /*
        * Counter-example: (18, 12), they have the same primary factors (2 and 3) but 18 % 12 != 0. You can think
        * about a and b as multiplication of primary numbers with powers (my example is just 2^2 * 3 and 2 * 3^2
        */
        return N / (gcd(M, N));
    }

    public int gcd(int a, int b) {
        if (a % b == 0)
            return b;
        else
            return gcd(b, a % b);
    }

    /**
     * A prime is a positive integer X that has exactly two distinct divisors: 1 and X.
     * The first few prime integers are 2, 3, 5, 7, 11 and 13.
     * A prime D is called a prime divisor of a positive integer P if there exists a positive integer K such that D * K = P.
     * For example, 2 and 5 are prime divisors of 20.
     *
     * You are given two positive integers N and M. The goal is to check whether the sets of prime divisors of
     * integers N and M are exactly the same.
     * For example, given:
     *
     * N = 15 and M = 75, the prime divisors are the same: {3, 5};
     * N = 10 and M = 30, the prime divisors aren't the same: {2, 5} is not equal to {2, 3, 5};
     * N = 9 and M = 5, the prime divisors aren't the same: {3} is not equal to {5}.
     *
     *
     * Given two non-empty zero-indexed arrays A and B of Z integers, returns the number of positions K for which
     * the prime divisors of A[K] and B[K] are exactly the same.
     * For example, given:
     *
     * A = {15, 10, 3}
     * B = {75, 30, 5}
     * the function should return 1, because only one pair (15, 75) has the same set of prime divisors.
     *
     * Z is an integer within the range [1..6,000];
     * each element of arrays A, B is an integer within the range [1..2,147,483,647].
     *
     * time complexity is O(Z*log(max(A)+max(B))2);
     * space complexity is O(1)
     */
    public int commonPrimeDivisors(int[] A, int[] B) {
        /*
        * 1 - when a and b have same gcd, then they have one minimum prime in common
        * 2 - to test other primes, you divide them using their gcd until another prime appear and leave a remainder
        * in a or b which cause it a!=1 or b!=1 // this is what I understood in non mathematical fashion!
         */
        int res = 0;

        for (int i = 0; i < A.length; i++) {
            int a = A[i];
            int b = B[i];

            int d = gcd(a, b);
            int c;
            c = 0;
            while (c != 1) {
                c = gcd(a, d);
                a /= c;
            }

            c = 0;
            while (c != 1) {
                c = gcd(b, d);
                b /= c;
            }

            if (a == 1 && b == 1) {
                res++;
            }
        }
        return res;
    }


    /**
     * Count the minimum number of jumps required for a frog to get to the other side of a river.
     * The Fibonacci sequence is defined using the following recursive formula:
     * F(0) = 0
     * F(1) = 1
     * F(M) = F(M - 1) + F(M - 2) if M >= 2
     *
     * A small frog wants to get to the other side of a river. The frog is initially located at one bank of
     * the river (position −1) and wants to get to the other bank (position N). The frog can jump over any
     * distance F(K), where F(K) is the K-th Fibonacci number. Luckily, there are many leaves on the river,
     * and the frog can jump between the leaves, but only in the direction of the bank at position N.
     *
     * The leaves on the river are represented in a zero-indexed array A consisting of N integers. Consecutive elements
     * of array A represent consecutive positions from 0 to N − 1 on the river. Array A contains only 0s and/or 1s:
     *
     * 0 represents a position without a leaf;
     * 1 represents a position containing a leaf.
     *
     * The goal is to count the minimum number of jumps in which the frog can get to the other side of the river
     * (from position −1 to position N). The frog can jump between positions −1 and N (the banks of the river)
     * and every position containing a leaf.
     *
     * For example, consider array A such that:
     *
     * A = {0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0}
     * the function should return 3
     *
     * The frog can make three jumps of length F(5) = 5, F(3) = 2 and F(5) = 5.
     *
     * Given a zero-indexed array A consisting of N integers, returns the minimum number of jumps by which
     * the frog can get to the other side of the river. If the frog cannot reach the other side of the river,
     * the function should return −1.
     *
     * N is an integer within the range [0..100,000];
     * each element of array A is an integer that can have one of the following values: 0, 1.
     *
     * time complexity is O(N*log(N));
     * space complexity is O(N)
     */
    public int fibFrog(int[] A) {
        // the original version is at
        // http://codesays.com/2014/solution-to-fib-frog-by-codility/
        int N = A.length;
        int[] fib = new int[N + 4];
        boolean[] visit = new boolean[N];
        fib[0] = 0;
        fib[1] = 1;
        int m = 1;
        do {
            m++;
            fib[m] = fib[m - 1] + fib[m - 2];
        } while (fib[m] <= N + 1);
        ArrayList<Status> statusQueue = new ArrayList<Status>();
        // use a queue to save the position and the moves of every possible jump
        statusQueue.add(new Status(-1, 0));
        int nextTry = 0;
        // use the breadth first search to get the result
        while (true) {
            if (nextTry == statusQueue.size())
                return -1;
            Status currStatus = statusQueue.get(nextTry);
            nextTry++;
            int currPosition = currStatus.position;
            int currMoves = currStatus.moves;
            for (int i = m - 1; i > 0; i--) {
                if (currPosition + fib[i] == N)
                    return currMoves + 1;
                else if (currPosition + fib[i] > N ||
                        A[currPosition + fib[i]] == 0 ||
                        visit[currPosition + fib[i]] == true)
                    continue;
                statusQueue.add(new Status(currPosition + fib[i], currMoves + 1));
                visit[currPosition + fib[i]] = true;
            }
        }
    }

    class Status {
        public int position;
        public int moves;

        public Status(int p, int m) {
            position = p;
            moves = m;
        }
    }

    /**
     * Count the number of different ways of climbing to the top of a ladder.
     * You have to climb up a ladder. The ladder has exactly N rungs, numbered from 1 to N. With each step,
     * you can ascend by one or two rungs. More precisely:
     *
     * with your first step you can stand on rung 1 or 2,
     * if you are on rung K, you can move to rungs K + 1 or K + 2,
     * finally you have to stand on rung N.
     *
     * Your task is to count the number of different ways of climbing to the top of the ladder.
     *
     * For example, given N = 4, you have five different ways of climbing, ascending by:
     *
     * 1, 1, 1 and 1 rung,
     * 1, 1 and 2 rungs,
     * 1, 2 and 1 rung,
     * 2, 1 and 1 rungs, and
     * 2 and 2 rungs.
     *
     * Given N = 5, you have eight different ways of climbing, ascending by:
     *
     * 1, 1, 1, 1 and 1 rung,
     * 1, 1, 1 and 2 rungs,
     * 1, 1, 2 and 1 rung,
     * 1, 2, 1 and 1 rung,
     * 1, 2 and 2 rungs,
     * 2, 1, 1 and 1 rungs,
     * 2, 1 and 2 rungs, and
     * 2, 2 and 1 rung.
     *
     * The number of different ways can be very large, so it is sufficient to return the result modulo 2P,
     * for a given integer P.
     *
     * Given two non-empty zero-indexed arrays A and B of L integers, returns an array consisting of L integers
     * specifying the consecutive answers; position I should contain the number of different ways of climbing
     * the ladder with A[I] rungs modulo 2B[I].
     *
     * For example, given L = 5 and:
     *
     * A = {4, 4, 5, 5, 1}
     * B = {3, 2, 4, 3, 1}
     *
     * the function should return the sequence [5, 1, 8, 0, 1], as explained above.
     *
     * L is an integer within the range [1..30,000];
     * each element of array A is an integer within the range [1..L];
     * each element of array B is an integer within the range [1..30].
     *
     * time complexity is O(L);
     * space complexity is O(L)
     */
    public int[] ladder(int[] A, int[] B) {
        // we know that the result of a number modulo 2^P is the bit under P, so
        // if we first let the number modulo 2^Q(Q > P) and then modulo 2^P, the
        // result is the same.
        int L = A.length;
        int[] fib = new int[L+2];
        int[] result = new int[L];
        fib[1] = 1;
        fib[2] = 2;
        for (int i = 3; i <= L; i++) {
            // make sure the fibonacci number will not exceed the max integer in java
            fib[i] = (fib[i-1] + fib[i-2]) % (1 << 30);
        }
        for (int i = 0; i < L; i++) {
            result[i] = fib[A[i]] % (1 << B[i]);
        }
        return result;
    }






}
