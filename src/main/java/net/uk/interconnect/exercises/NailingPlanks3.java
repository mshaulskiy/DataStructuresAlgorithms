package net.uk.interconnect.exercises;

import java.util.Arrays;
import java.util.Comparator;

public class NailingPlanks3 {
    public static int solution(int[] A, int[] B, final int[] C) {

        Integer[] nailIndex = new Integer[C.length];
        for (int i = 0; i < C.length; i++) {
            nailIndex[i] = i;
        }
        Comparator<Integer> nailCompare = new Comparator<Integer>() {

            public int compare(Integer arg0, Integer arg1) {
                // TODO Auto-generated method stub
                if (C[arg0] == C[arg1]) {
                    return arg0 - arg1;
                }
                return C[arg0] - C[arg1];
            }

        };
        Arrays.sort(nailIndex, nailCompare);
        int[] nodes = buildTree(nailIndex);
        int j = 0;
        for (int i = 0; i < A.length; i++) {
            int leftMost = findNail(A[i], B[i], C, nailIndex, true);
            if (leftMost == -1) {
                return -1;
            }
            int rightMost = findNail(A[i], B[i], C, nailIndex,  false);

            if (leftMost == rightMost) {
                j = Math.max(j, nailIndex[leftMost]);
            }
            int index = query(nodes, nailIndex, leftMost, rightMost);
            j = Math.max(j, nailIndex[index]);
        }
        return j + 1;
    }

    static int findNail(int A, int B, int[] C, Integer[] nailIndex, boolean leftMost) {
        int start = 0;
        int end = C.length - 1;
        int index = -1;
        while (start <= end) {
            int mid = (end - start) / 2 + start;
            if (A <= C[nailIndex[mid]] && C[nailIndex[mid]] <= B) {
                index = mid;
                if (leftMost) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else if (C[nailIndex[mid]] > B) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return index;
    }

    public static int[] buildTree(Integer[] A) {
        int x = (int) Math.ceil(Math.log(A.length) / Math.log(2));
        int[] nodes = new int[2 * (1 << x) - 1];

        buildTree(A, nodes, 0, 0, A.length - 1);

        return nodes;
    }

    // stores the index of the minimum element in a given range
    private static int buildTree(Integer[] A, int[] nodes, int index, int start, int end) {

        if (start == end) {
            nodes[index] = start;
            return start;
        }
        int left = buildTree(A, nodes, 2 * index + 1, start, (end - start) / 2 + start);
        int right = buildTree(A, nodes, 2 * index + 2, (end - start) / 2 + start + 1, end);

        nodes[index] = (A[left] <= A[right]) ? left : right;
        return nodes[index];

    }

    public static int query(int[] nodes, Integer[] A, int a, int b) {

        if (a < 0 || b >= nodes.length) {
            return -1;
        }
        return query(nodes, A, 0, 0, A.length - 1, a, b);
    }

    private static int query(int[] nodes, Integer[] A, int index, int start, int end, int a, int b) {

        if (a > end || b < start) {
            return -1;
        }

        if (start >= a && end <= b) {
            return nodes[index];
        }
        int left = query(nodes, A, 2 * index + 1, start, (end - start) / 2 + start, a, b);
        int right = query(nodes, A, 2 * index + 2, (end - start) / 2 + start + 1, end, a, b);
        if (left == -1) {
            return right;
        }
        if (right == -1) {
            return left;
        }
        return (A[left] <= A[right]) ? left : right;
    }
}
