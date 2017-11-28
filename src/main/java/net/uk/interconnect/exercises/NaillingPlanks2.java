package net.uk.interconnect.exercises;

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
public class NaillingPlanks2 {

    /**
     * NailingPlanks: I managed to find a solution with linear complexity. In my solution I first discard
     * all planks that completely wrap other planks, because a nail used for a wrapped plank can be used
     * for all planks that wrap it.
     * <p>
     * http://draganbozanovic.blogspot.co.uk/2016/04/codility-nailingplanks-linear-complexity.html
     * <p>
     * Nail 15 is the minimum nail for plank 2. Planks 1 and 3 can use nails 5 and 10 respectively,
     * but it does not matter because nail 15 must be used for plank 3, and thus it determines the
     * minimum nail for all three planks, so planks 1 and 3 can be eliminated from the calculation.
     * <p>
     * Once all wrapper planks are eliminated, the remaining planks (maximum 2*M of them)
     * <p>
     * That means that plank which starts first will also end first, which makes queue a perfect
     * data structure to store the information when traversing the remaining planks.
     * <p>
     * <p>
     * So, the time complexity of the loops is either O(N) or O(M), and helper arrays and queues don't
     * take more than 2*M space, so the overall time complexity is O(N+M) and space O(M).
     *
     * @param A
     * @param B
     * @param C
     * @return
     */
    public int solution2(int[] A, int[] B, int[] C) {

        // Elimination of wrapper planks can be done in two steps:

        /*
        1) Sorting the planks by start position and eliminating planks that start at the same position by
        taking the shortest plank for each start position. This is basically a variation of counting sort,
        with linear time complexity:
         */
        int[] planks = new int[2 * C.length + 1];
        for (int i = 0; i < A.length; i++) {
            if (planks[A[i]] == 0 || B[i] < planks[A[i]]) {
                planks[A[i]] = B[i];
            }
        }

        /*
        After this loop planks[i] contains end position for a plank that starts at the position i.

        2) Filtering out wrapper planks:
         */
        Queue stack = new Queue(2 * C.length);
        for (int i = 1; i < planks.length; i++) {
            if (planks[i] != 0) {
                while (!stack.isEmpty() && planks[i] <= planks[stack.last()]) {
                    stack.removeLast();
                }
                stack.addLast(i);
            }
        }

        /*
        We push start positions to stack (we just reuse end side of the queue as stack here). For each plank
        we remove all the planks from the top of the stack that wrap the current plank. If the plank at the
        top of the stack does not wrap the current plank, it is guaranteed that no other planks in the stack
        wrap the current plank because if there were such a plank, it would also wrap the plank at the top
        of the stack and thus would have been removed when the plank at the top was added to the stack.

        The total time complexity of the inner loop is linear because each plank is once added and once
        visited on the stack.

        Now the stack contains only planks that are not wrapped by any other plank, and we store the remaining
        plank ends in a new array:
         */

        int[] ends = new int[planks.length];
        while (!stack.isEmpty()) {
            int start = stack.removeLast();
            ends[start] = planks[start];
        }


        /* We also create two other helper arrays for plank starts and nail positions: */

        int[] starts = new int[ends.length];
        for (int i = 1; i < ends.length; i++) {
            if (ends[i] > 0) {
                starts[ends[i]] = i;
            }
        }

        int[] nails = new int[starts.length];
        for (int i = 0; i < C.length; i++) {
            if (nails[C[i]] == 0) {
                nails[C[i]] = i + 1;
            }
        }

        /* Now we traverse the ordered planks: */

        int result = -1;
        Queue queue = new Queue(ends.length);
        for (int i = 1; i < ends.length; i++) {
            if (ends[i] > 0) {
                if (!queue.isEmpty() && ends[queue.last()] == -1) {
                    queue.removeLast();
                }
                ends[i] = -1;
                queue.addLast(i);
            } else if (queue.isEmpty()) {
                continue;
            }
            if (nails[i] != 0) {
                if (ends[queue.last()] == -1 || nails[i] < ends[queue.last()]) {
                    ends[queue.last()] = nails[i];
                    while (queue.size() > 1 && ends[queue.nextToLast()] > nails[i]) {
                        queue.removeNextToLast();
                    }
                }
            }
            if (starts[i] != 0) {
                int min = ends[queue.first()];
                if (min == -1) {
                    return -1;
                }
                if (starts[i] == queue.first()) {
                    queue.removeFirst();
                }
                if (result == -1 || min > result) {
                    result = min;
                }
            }
        }

        return result;
    }
}

/**
 * We store plank starts on the queue and reuse ends array to store minimum nails for corresponding planks in the
 * queue.For each nail we encounter during the iteration, we remove all planks from the queue for which minimum
 * nail is larger than the current minimum nail, because if a plank is in the queue, then it means that it
 * has started but not ended yet, and this nail can be used for that plank as well.The total time complexity of the
 * inner while loop is linear because each element in the queue is added once and removed once.
 * <p>
 * The implementation of the Queue is straightforward:
 */
class Queue {
    private int[] array;
    private int start = 0;
    private int end = -1;
    private int size = 0;

    Queue(int capacity) {
        this.array = new int[capacity];
    }

    void addLast(int element) {
        size++;
        end++;
        if (end == array.length) {
            end = 0;
        }
        array[end] = element;
    }

    int removeLast() {
        size--;
        int result = array[end--];
        if (end < 0) {
            end = array.length - 1;
        }
        return result;
    }

    int removeFirst() {
        size--;
        int result = array[start++];
        if (start == array.length) {
            start = 0;
        }
        return result;
    }

    int removeNextToLast() {
        size--;
        int index = nextToLastIndex();
        int result = array[index];
        array[index] = array[end];
        end = index;
        return result;
    }

    int last() {
        return array[end];
    }

    int first() {
        return array[start];
    }

    int nextToLast() {
        return array[nextToLastIndex()];
    }

    boolean isEmpty() {
        return size == 0;
    }

    int size() {
        return size;
    }

    int nextToLastIndex() {
        return end > 0 ? end - 1 : array.length - 1;
    }

}
