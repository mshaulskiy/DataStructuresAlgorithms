package net.uk.interconnect.exercises;

import java.util.TreeMap;

/**
 * Count the minimum number of nails that allow a series of planks to be nailed.
 * <p>
 * <p>
 * You are given two non-empty zero-indexed arrays A and B consisting of N integers. These arrays represent N planks. More precisely, A[K] is the start and B[K] the end of the K−th plank.
 * <p>
 * Next, you are given a non-empty zero-indexed array C consisting of M integers. This array represents M nails. More precisely, C[I] is the position where you can hammer in the I−th nail.
 * <p>
 * We say that a plank (A[K], B[K]) is nailed if there exists a nail C[I] such that A[K] ≤ C[I] ≤ B[K].
 * <p>
 * The goal is to find the minimum number of nails that must be used until all the planks are nailed. In other words, you should find a value J such that all planks will be nailed after using only the first J nails. More precisely, for every plank (A[K], B[K]) such that 0 ≤ K < N, there should exist a nail C[I] such that I < J and A[K] ≤ C[I] ≤ B[K].
 * <p>
 * For example, given arrays A, B such that:
 * <p>
 * A[0] = 1    B[0] = 4
 * A[1] = 4    B[1] = 5
 * A[2] = 5    B[2] = 9
 * A[3] = 8    B[3] = 10
 * four planks are represented: [1, 4], [4, 5], [5, 9] and [8, 10].
 * <p>
 * Given array C such that:
 * <p>
 * C[0] = 4
 * C[1] = 6
 * C[2] = 7
 * C[3] = 10
 * C[4] = 2
 * if we use the following nails:
 * <p>
 * 0, then planks [1, 4] and [4, 5] will both be nailed.
 * 0, 1, then planks [1, 4], [4, 5] and [5, 9] will be nailed.
 * 0, 1, 2, then planks [1, 4], [4, 5] and [5, 9] will be nailed.
 * 0, 1, 2, 3, then all the planks will be nailed.
 * Thus, four is the minimum number of nails that, used sequentially, allow all the planks to be nailed.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int[] A, int[] B, int[] C); }
 * that, given two non-empty zero-indexed arrays A and B consisting of N integers and a non-empty zero-indexed
 * array C consisting of M integers, returns the minimum number of nails that, used sequentially,
 * allow all the planks to be nailed.
 * <p>
 * If it is not possible to nail all the planks, the function should return −1.
 * <p>
 * For example, given arrays A, B, C such that:
 * <p>
 * A[0] = 1    B[0] = 4
 * A[1] = 4    B[1] = 5
 * A[2] = 5    B[2] = 9
 * A[3] = 8    B[3] = 10
 * <p>
 * C[0] = 4
 * C[1] = 6
 * C[2] = 7
 * C[3] = 10
 * C[4] = 2
 * the function should return 4, as explained above.
 * <p>
 * Assume that:
 * <p>
 * N and M are integers within the range [1..30,000];
 * each element of arrays A, B, C is an integer within the range [1..2*M];
 * A[K] ≤ B[K].
 * Complexity:
 * <p>
 * expected worst-case time complexity is O((N+M)*log(M));
 * expected worst-case space complexity is O(M), beyond input storage (not counting the storage required
 * for input arguments).
 * Elements of input arrays can be modified.
 * <p>
 * <p>
 * Created by mshaulskiy on 11/03/2017.
 */
public class NailingPlanks {
    /**
     * @param A
     * @param B
     * @param C
     * @return
     */
    public int solution(int[] A, int[] B, int[] C) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            Integer left = map.ceilingKey(A[i]);
            Integer right = map.floorKey(B[i]);
            if (left != null && right != null && left <= right)
                continue;
            int j = count;
            for (; j < C.length; j++) {
                map.put(C[j], j);
                count++;
                if (C[j] >= A[i] && C[j] <= B[i])
                    break;
            }
            if (j == C.length)
                return -1;
        }
        return count;
    }


    /**
     * It was tough to get 100% on performance.
     * <p>
     * thanks to
     * https://codility-lessons.blogspot.co.uk/2015/03/lesson-11-nailingplanks-nailing-planks.html?showComment=1485790327172
     * https://codility-lessons.blogspot.co.uk/2015/03/lesson-11-nailingplanks-nailing-planks.html?showComment=1485790327172
     * I fully understood the solution
     * <p>
     * I got the brute-force solution in no time :D, but this one took me two hours to figure out!
     *
     * @param A
     * @param B
     * @param C
     * @return
     */
    public int solution2(int[] A, int[] B, int[] C) {
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
     * I have to be honest and say I don't understand any of this. Why are you creating vectors with double the size +1 of C?

     why is idx_end half of the size of (C - 1)?

     I'm not as familiar with C++ as I used to be, but I can't visualize this code at all.

     EDIT:
     Okay, so I did a by hand follow through using the example. I get that we are min/maxing the nail count, and how we are getting the number of nails.

     However, what I still don't understand is, 2*M+1 for defining the size of nails and partial_sums_nails, and determining whether we nailed all the boards or not (max_nails == M) ? -1 : max_nails + 1.

     How does double the size of C then plus 1 equal the max size of nails? I mean, what if the nails we have is [1, 2, 4, 20] ? This algo would come up with 2*M+1 = 7, but the max size would in actuality be 20... right?

     EDIT:
     Okay, figured out how determining whether or not we hit all the planks or not, however the size issues is still in question. Anyways, I have to say it's really clever and hopefully I can use similar methodologies elsewhere.
     • Reply•Share ›
     Avatar
     Thiago Papageorgiou  jalday • a month ago
     I had the same question about the max size of nails, then I realized that the problem defines A, B and C as follows:

     "Each element of arrays A, B, C is an integer within the range [1..2*M];"

     Therefore your example [1, 2, 4, 20] would in fact break the algorithm used by Lara, but it is not a valid entry accordingly with what is proposed by the challenge.
     */

/*
    public int solution(int[] A, int[] B, int[] C) {
        int N = A.Length;
        int M = C.Length;
        int lwBits = 15;

        var c = C.ToList();
        for (int i = 0; i < c.Count; i++) {
            // We'll store nail index in lower 15 bits, and nail value in the next 16 bits.
            // We don't want to mess with the bit "0" because it's the negative bit
            c[i] = c[i] << lwBits | i;
        }
        c.Sort();

        int result = 0;

        for (int i = 0; i < N && result < M; i++) {
            int minNail = M;

            // Find a nail with value equal to or greater than A[i]
            int j1 = c.BinarySearch(A[i] << lwBits);
            j1 = j1 < 0 ? ~j1 : j1;

            // Find a nail with value equal to or greater than B[i] + 1
            int j2 = c.BinarySearch(B[i] + 1 << lwBits);
            j2 = j2 < 0 ? ~j2 : j2;

            // Find minimum nail index in interval [j1;j2)
            for (int k = j1; k < j2 && minNail > result; k++) {
                minNail = Math.Min(minNail, c[k] % (1 << lwBits));
            }

            result = Math.Max(result, minNail);
        }

        return result < M ? result + 1 : -1;
    }

    */

}
