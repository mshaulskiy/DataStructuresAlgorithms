package net.uk.interconnect.exercises;

/**
 * A non-empty zero-indexed array A consisting of N integers is given. A pair of integers (P, Q), such that 0 ≤ P < Q < N, is called a slice of array A (notice that the slice contains at least two elements). The average of a slice (P, Q) is the sum of A[P] + A[P + 1] + ... + A[Q] divided by the length of the slice. To be precise, the average equals (A[P] + A[P + 1] + ... + A[Q]) / (Q − P + 1).
 * <p>
 * For example, array A such that:
 * <p>
 * A[0] = 4
 * A[1] = 2
 * A[2] = 2
 * A[3] = 5
 * A[4] = 1
 * A[5] = 5
 * A[6] = 8
 * contains the following example slices:
 * <p>
 * slice (1, 2), whose average is (2 + 2) / 2 = 2;
 * slice (3, 4), whose average is (5 + 1) / 2 = 3;
 * slice (1, 4), whose average is (2 + 2 + 5 + 1) / 4 = 2.5.
 * The goal is to find the starting position of a slice whose average is minimal.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int[] A); }
 * that, given a non-empty zero-indexed array A consisting of N integers, returns the starting position of the slice with the minimal average. If there is more than one slice with a minimal average, you should return the smallest starting position of such a slice.
 * <p>
 * For example, given array A such that:
 * <p>
 * A[0] = 4
 * A[1] = 2
 * A[2] = 2
 * A[3] = 5
 * A[4] = 1
 * A[5] = 5
 * A[6] = 8
 * the function should return 1, as explained above.
 * <p>
 * Assume that:
 * <p>
 * N is an integer within the range [2..100,000];
 * each element of array A is an integer within the range [−10,000..10,000].
 * Complexity:
 * <p>
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 * <p>
 * Created by mshaulskiy on 09/03/2017.
 */
public class MinAvgTwoSlice {

    /**
     * This is a mathematical problem... and you have to understand the relationship
     * that exists between the averages of the slices.
     * <p>
     * We know from the problem description that the slices are a minimum length of 2.
     * The trick to this problem is that the min average slice also cannot be longer than 3.
     * So we only have to calculate the avg of the slices of length 2 and 3.
     * <p>
     * To understand why the min average slice cannot be longer than 3, consider
     * the case where it is longer than 3...
     * <p>
     * ex. [-10, 3, 4, -20]
     * <p>
     * avg(0,3) = -23 / 4 = -5.75 // entire array is -5.75 average
     * avg(0,1) = -7 / 2 = -3.5 // subslice (0,1)
     * avg(2,3) = -16 / 2 = -8 // subslice (2,3)
     * <p>
     * Notice that (avg(0,1) + avg(2,3)) / 2 = avg(0,3)
     * Therefore, if avg(0,1) != avg(2,3) then one of them must be smaller than the other.
     * <p>
     * No matter which way we split up this array, if the slices aren't exactly the same,
     * then one of them must have a lower average than the full slice. Play around with it
     * and you'll see that it's true. There are mathematical proofs out there for this.
     * <p>
     * <p>
     * <p>
     * My test case contradicts with your assumption :
     * <p>
     * array : [1,2,1,0,0,1]
     * <p>
     * avg of (0,1) = (1+2)/2 =1.5
     * avg of (0,2) = (1+2+1)/3 = 1.33
     * avg of(0,3) = (1+2+1+0)/4 = 1
     * <p>
     * now avg (0,3) < avg. of (0,2) but you said that it would not happen
     * <p>
     * <p>
     * you are right. after I added this to code :
     * if (slice > 3) {
     * break;
     * }
     * score jumped from 60 to 100.
     * <p>
     * <p>
     * The proof in common case:
     * Let the sequence (a1+a2+...+an) to be the shortest slice with minimum average value.
     * Then, let f(x,y) = (ax+a(x+1)+....+a(y-1)+ay).
     * That means:
     * if 2<k<n-2 then="" 1)="" f(1,n)="" n="" <="" f(1,k)="" k=""> f(1,n)*k < f(1,k)*n => f(k+1,n)*k < f(1,k)*(n-k)
     * => f(k+1,n)/(n-k) < f(1,k)/k
     * 2) f(1,n)/n < f(k+1,n)*(n-k) => f(1,n)*(n-k) < f(k+1,n)/n => f(1,k)*(n-k) < f(k+1,n)*k
     * => f(1,k)/k < f(k+1,n)/(n-k)
     * We've got a collision? that means, that it's not shortest slice from the task. The collision resolves only when k doesn't exist, just when n<=3.
     * And there is no need to use O(N) space. Only O(1) is nessessary. For exact, 3*4bytes size variables - that's enough for solving this problem.
     * <p>
     * <p>
     * B/K -A/K + ((A%K) == 0 ? 1:0);
     * <p>
     * (A-1)/K + B/K will not work for 0, 0, K
     * <p>
     * <p>
     * (A-1)/K is the sum of all the occurrences of the numbers <=(A-1) that are divisible by K.
     * B/K is the sum of all the occurrences of the numbers <= B that are divisible by K.
     * To know the sum of all the occurrences of the numbers between A and B that are divisible by K, just subtract (A-1)/K to B/K.
     * And remember that 0 is divisible by any non-zero number.
     * <p>
     * My explanation would have been clearer using the right notation, but it's difficult to do it using this editor; anyway, I hope I was able to give an idea of it.
     * <p>
     * <p>
     * <p>
     * <p>
     * The sums you need to keep are how many times each letter has appeared. You need to keep one sum sequence for each letter. (In actuality you don't need a prefix sum for T because if A, C, or G does not appear, you can assume T. Explained in more detail in the example below.)
     * <p>
     * So, for the example in the problem statement, CAGCCTA, you end up with three prefix sum arrays that have the following values:
     * <p>
     * A: 0 1 1 1 1 1 2
     * C: 1 1 1 2 3 3 3
     * G: 0 0 1 1 1 1 1
     * <p>
     * Look at the A prefix sum array. The value in each position corresponds to how many times that letter has appeared so far. So, for postion 0, A has not appeared yet, C has appeared once, and G has not appeared yet. At position 5, A has appeared once (and we can see from the prefix sum for A that it first appeared at position 1), C has appeared three times, and G has appeared once.
     * <p>
     * With these arrays, we can now determine whether a given letter appeared or not between any two positions. So, we check A first, then C, and then G. If none of them appeared then we know that only T appeared, which is the reason we do not need a prefix sum to count the T occurrences.
     * <p>
     * For example, consider the range 2 to 4 as in the problem statement. We can see from the sums for A that a single A has already appeared when the range starts, and only a single A has appeared at the end of the range. Thus, there could not have been an A in 2 to 4. Now we check C. At the start of the range there has been a single C appear, but at the end of the range there have been three. Thus, there must have been at least one C from 2 to 4, and we can conclude that the minimal impact factor for 2 to 4 is 2.
     * <p>
     * The following Lua implementation of the above achieves a perfect score:
     * <p>
     * function solution(S, P, Q)
     * <p>
     * p = { A = {}, C = {}, G = {} }
     * p.A[-1] = 0 p.C[-1] = 0 p.G[-1] = 0
     * for i = 1, #S do
     * s = S:sub(i,i)
     * n = i - 1
     * if s == "A" then a = 1 else a = 0 end
     * if s == "C" then c = 1 else c = 0 end
     * if s == "G" then g = 1 else g = 0 end
     * p.A[n] = p.A[n-1] + a
     * p.C[n] = p.C[n-1] + c
     * p.G[n] = p.G[n-1] + g
     * end
     * <p>
     * r = {}
     * for i = 0, #P - 1 do
     * if p.A[Q[i]] > p.A[P[i]-1] then
     * r[i] = 1
     * elseif p.C[Q[i]] > p.C[P[i]-1] then
     * r[i] = 2
     * elseif p.G[Q[i]] > p.G[P[i]-1] then
     * r[i] = 3
     * else
     * r[i] = 4
     * end
     * end
     * r[#P] = 0 -- hack to get lua array size to be correct
     * return r
     *
     * @param A
     * @return
     */
    public int solution(int[] A) {

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



}
