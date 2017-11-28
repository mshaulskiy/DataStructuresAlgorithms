package net.uk.interconnect.exercises;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CodingPractice5 {

    /**
     * You are given a non-empty zero-indexed array A consisting of N integers.
     * For each number A[i] such that 0 ≤ i < N, we want to count the number of elements of the array that are not
     * the divisors of A[i]. We say that these elements are non-divisors.
     * For example, consider integer N = 5 and array A such that:
     *
     * A = {3, 1, 2, 3, 6}
     * the function should return [2, 4, 3, 2, 0]
     *
     * For the following elements:
     * A[0] = 3, the non-divisors are: 2, 6,
     * A[1] = 1, the non-divisors are: 3, 2, 3, 6,
     * A[2] = 2, the non-divisors are: 3, 3, 6,
     * A[3] = 3, the non-divisors are: 2, 6,
     * A[4] = 6, there aren't any non-divisors.
     *
     * Given a non-empty zero-indexed array A consisting of N integers, returns a sequence of integers representing
     * the amount of non-divisors.
     *
     * The sequence should be returned as an array of integers.
     *
     * time complexity is O(N*log(N))
     * space complexity is O(N)
     */
    public int[] countNonDivisible(int[] A) {
        int N = A.length;
        int max = N * 2 + 1;

        int[] c = new int[max + 1];
        for (int i = 0; i < N; i++) {
            c[A[i]]++;
        }

        int[] result = new int[N];
        for (int i = 0; i < N; i++) {
            int r = 0;
            int x = A[i];
            int target = (int) Math.sqrt(x);
            for (int d = 1; d <= target; d++) {
                if (x % d == 0) {
                    r += c[d];
                    if (x / d != d) {
                        r += c[x / d];
                    }
                }
            }
            result[i] = N - r;
        }

        return result;
    }

    public int[] countNonDivisible2(int[] A) {
        // write your code in Java SE 8
        int N = A.length;
        // stores the numbers of every element in A
        int[] counters = new int[2 * N + 1];
        // stores the numbers of divisors of A[i]
        int[] divisors = new int[2 * N + 1];
        // stores the numbers of non-divisors of A[i]
        int[] nonDivisors = new int[N];
        for (int ele : A)
            counters[ele]++;
        // using the algorithm of Sieve of Eratosthenes
        for (int i = 1; i <= 2 * N; i++) {
            // i is the element of A
            if (counters[i] > 0) {
                int k = i;
                while (k <= 2 * N) {
                    // k is also an element of A and the multiple of i
                    if (counters[k] > 0)
                        divisors[k] += counters[i];
                    k += i;
                }
            }
        }
        // the numbers of non-divisors and divisors is N
        for (int i = 0; i < N; i++) {
            nonDivisors[i] = N - divisors[A[i]];
        }
        return nonDivisors;
    }

    /**
     * A prime is a positive integer X that has exactly two distinct divisors: 1 and X. The first few prime integers
     * are 2, 3, 5, 7, 11 and 13.
     * A semiprime is a natural number that is the product of two (not necessarily distinct) prime numbers.
     * The first few semiprimes are 4, 6, 9, 10, 14, 15, 21, 22, 25, 26.
     * You are given two non-empty zero-indexed arrays P and Q, each consisting of M integers. These arrays represent
     * queries about the number of semiprimes within specified ranges.
     * Query K requires you to find the number of semiprimes within the range (P[K], Q[K]), where 1 ≤ P[K] ≤ Q[K] ≤ N.
     *
     * For example, consider an integer N = 26 and arrays P, Q such that:
     * P = {1, 4, 16}
     * Q = {26, 10, 20}
     * The number of semiprimes within each of these ranges is as follows:
     * (1, 26) is 10,
     * (4, 10) is 4,
     * (16, 20) is 0.
     * the function should return the values [10, 4, 0]
     *
     * Given an integer N and two non-empty zero-indexed arrays P and Q consisting of M integers, returns an array
     * consisting of M elements specifying the consecutive answers to all the queries.
     *
     * Assume that: each element of arrays P, Q is an integer within the range [1..N];
     * P[i] ≤ Q[i].
     *
     * time complexity is O(N*log(log(N))+M)
     * space complexity is O(N+M)
     */
    public int[] countSemiprimes(int N, int[] P, int[] Q) {
        int m = P.length;
        int[] M = new int[m];

        int[] f = new int[N + 1];
        int i = 2;
        while (i * i <= N) {
            if (f[i] == 0) {
                int k = i * i;
                while (k <= N) {
                    if (f[k] == 0) {
                        f[k] = i;
                    }
                    k += i;
                }
            }
            i++;
        }

        int[] semi = new int[N + 1];

        int sum = 0;
        for (int k = 1; k <= N; k++) {
            if (f[k] != 0) {
                int b = k / f[k];
                if (f[b] == 0) {
                    sum++;
                }
            }
            semi[k] = sum;
        }

        for (int mi = 0; mi < m; mi++) {
            int p = P[mi];
            int q = Q[mi];
            M[mi] = semi[q] - semi[p - 1];
        }

        return M;
    }

    public int[] countSemiprimes2(int N, int[] P, int[] Q) {
        // using the algorithm in the reading material
        int M = P.length;
        // if the number is prime, then the element of this array is 0,
        // otherwise the element of this array is the minimal prime factor of
        // the number.
        int[] minPrimeFactor = new int[N + 1];
        for (int i = 2; i * i <= N; i++) {
            if (minPrimeFactor[i] == 0) {
                int k = i * i;
                while (k <= N) {
                    if (minPrimeFactor[k] == 0)
                        minPrimeFactor[k] = i;
                    k += i;
                }
            }
        }
        // the prefix sum of the count of semiprime
        int[] semiprimeCount = new int[N + 1];
        for (int i = 2; i <= N; i++) {
            // whether the number i is a semiprime or not
            if (minPrimeFactor[i] != 0 && minPrimeFactor[i / minPrimeFactor[i]] == 0)
                semiprimeCount[i] = semiprimeCount[i-1] + 1;
            else
                semiprimeCount[i] = semiprimeCount[i-1];
        }
        int[] result = new int[M];
        for (int i = 0; i < M; i++) {
            result[i] = semiprimeCount[Q[i]] - semiprimeCount[P[i]-1];
        }
        return result;
    }

    /**
     * Sorting - Distinct
     * Given a zero-indexed array A consisting of N integers, returns the number of distinct values in array A.
     *
     * A = {2, 1, 1, 2, 3, 1}
     * the function should return 3, because there are 3 distinct values appearing in array A, namely 1, 2 and 3.
     *
     * time complexity is O(N*log(N))
     * space complexity is O(N)
     */
    public int distinct(int[] A) {
        return Arrays.stream(A).boxed().collect(Collectors.toSet()).size();
    }

    public int distinct2(int[] A) {
        // return new List<int>(A).Distinct().Count();
        Set<Integer> set = new HashSet();
        for (Integer i : A) {
            set.add(i);
        }
        return set.size();
    }

    /**
     * The number of distinct values — O(n*log(n)).
     *
     * First, sort array A; similar values will then be next to each other.
     * Finally, just count the number of distinct pairs in adjacent cells.
     *
     * @param A
     * @return
     */
    public static int distinct3(int[] A){
        int n = A.length;
        // the built-in sorting function performs O(n*log(n)) time complexity even in the worst case
        Arrays.sort(A);
        int result = 1;
        for(int k = 1; k < n; k++){
            // add 1 to the number when there is a bigger element.
            if(A[k] != A[k - 1])
                result += 1;
        }
        return result;
    }

    public int distinct4(int[] A) {
        // write your code in Java SE 8
        int N = A.length;
        if (N == 0)
            return 0;
        quickSort(A, 0, N-1);
        int num = 1;
        int preDist = A[0];
        for (int i = 1; i < N; i++) {
            if (A[i] != preDist) {
                preDist = A[i];
                num++;
            }
        }
        return num;
    }
    // ordinary quick_sort function, it performs O(N*N) time complexity when in
    // the worst case, but still the solution get 100/100 on codility.
    public void quickSort(int[] array, int start, int end) {
        if (start <= end) {
            int index = partition(array, start, end);
            quickSort(array, start, index - 1);
            quickSort(array, index + 1, end);
        }
    }

    public int partition(int[] array, int start, int end) {
        int pivot = array[start];
        int mark = start;
        for (int i = start + 1; i <= end; i++) {
            if (array[i] < pivot) {
                mark++;
                if (i != mark) {
                    int temp = array[mark];
                    array[mark] = array[i];
                    array[i] = temp;
                }
            }
        }
        if (start != mark) {
            array[start] = array[mark];
            array[mark] = pivot;
        }
        return mark;
    }

    /**
     * Selection sort — O(n^2).
     * The time complexity is quadratic.
     * <p>
     * Notice that after k iterations (repetition of everything inside the loop) the first k elements
     * will be sorted in the right order (this type of a property is called the loop invariant).
     *
     * @param A
     * @return
     */
    public static int[] selectionSort(int[] A) {
        int n = A.length;
        for (int k = 0; k < n; k++) {
            int minimal = k;
            for (int j = k + 1; j < n; j++) {
                if (A[j] < A[minimal])
                    minimal = j;
            }

            // swap A[k] and A[minimal]
            int temp = A[k];
            A[k] = A[minimal];
            A[minimal] = temp;
        }
        return A;
    }

    /**
     * Counting sort — O(n + k)
     *
     * The time complexity here is O(n + k). We need additional memory O(k) to count all the elements.
     *
     * If all the elements are in the set {0, 1, . . . , k}, then the array used for
     * counting should be of size k + 1. The limitation here may be available memory.
     *
     * @param A
     * @param k
     * @return
     */
    public static int[] countingSort(int[] A, int k) {
        int n = A.length;
        int[] count = new int[k + 1];
        for (int i = 0; i < n; i++) {
            count[A[i]] = +1;
        }
        int p = 0;
        for (int i = 0; i < k + 1; i++) {
            for (int j = 0; j < count[i]; j++) {
                // all the below operations are performed not more than O(n) times.
                A[p] = i;
                p += 1;
            }
        }
        return A;
    }

    /**
     * A non-empty zero-indexed array A consisting of N integers is given. The product of triplet (P, Q, R) equates
     * to A[P] * A[Q] * A[R] (0 ≤ P < Q < R < N).
     *
     * A = {-3, 1, 2, -2, 5, 6}
     * the function should return 60, as the product of triplet (2, 4, 5) is maximal.
     *
     * contains the following example triplets:
     * (0, 1, 2), product is −3 * 1 * 2 = −6
     * (1, 2, 4), product is 1 * 2 * 5 = 10
     * (2, 4, 5), product is 2 * 5 * 6 = 60
     * Your goal is to find the maximal product of any triplet.
     *
     * time complexity is O(N*log(N))
     * space complexity is O(1)
     */
    public int maxProductOfThree(int[] A) {
        int n = A.length - 1;
        Arrays.sort(A);
        int pos = A[n] * A[n - 1] * A[n - 2];
        int neg = A[0] * A[1] * A[n];
        return Math.max(neg, pos);
    }

    public int maxProductOfThree2(int[] A) {
        int N = A.length;
        // the worst-case time complexity is O(N*log(N))
        Arrays.sort(A);
        // the max product of three elements is the product of the last three
        // elements in the sorted array or the product of the first two elements
        // and the last element if the first two elements are negatives.
        return Math.max(A[N - 1] * A[N - 2] * A[N - 3], A[N - 1] * A[0] * A[1]) ;
    }

    public int maxProductOfThree3(int[] A) {
        // the original version is at
        // http://stackoverflow.com/questions/2187431/max-product-of-the-three-numbers-for-a-given-array-of-size-n
        int N = A.length;
        // the variable stores the minimal negative element
        int negativeMin = 0;
        // the variable stores the second minimal negative element
        int negativeSecond = 0;
        // the variable stores the third maximal element
        int thirdMax = -1000;
        // the variable stores the second maximal element
        int secondMax = -1000;
        // the variable stores the maximal element
        int maxValue = -1000;
        // get the five variables above in O(N) time complexity
        for (int element : A) {
            if (element < negativeMin) {
                negativeSecond = negativeMin;
                negativeMin = element;
            } else if (element < negativeSecond)
                negativeSecond = element;
            if (element > maxValue) {
                thirdMax = secondMax;
                secondMax = maxValue;
                maxValue = element;
            } else if (element > secondMax) {
                thirdMax = secondMax;
                secondMax = element;
            } else if (element > thirdMax)
                thirdMax = element;
        }
        // the product of the three maximal elements
        int maxProduct = thirdMax * secondMax * maxValue;
        // the number of negative elements is more than 2
        if (negativeSecond != 0)
            // the result is either the product of the three maximal elements or
            // the product of the two minimal negative elements and the maximal
            // positive element.
            return Math.max(negativeMin * negativeSecond * maxValue, maxProduct);
            // the number of negative elements is less than 2
        else
            return maxProduct;
    }


    /**
     * We draw N discs on a plane. The discs are numbered from 0 to N − 1. A zero-indexed array A of N non-negative
     * integers, specifying the radiuses of the discs, is given. The J-th disc is drawn with its center at (J, 0)
     * and radius A[J].
     * We say that the J-th disc and K-th disc intersect if J ≠ K and the J-th and K-th discs have at least one
     * common point (assuming that the discs contain their borders).
     * The figure below shows discs drawn for N = 6 and A as follows:
     *
     * A = {1, 5, 2, 1, 4, 0}
     * the function should return 11
     *
     * There are eleven (unordered) pairs of discs that intersect, namely:
     * discs 1 and 4 intersect, and both intersect with all the other discs;
     * disc 2 also intersects with discs 0 and 3.
     * Given an array A describing N discs as explained above, returns the number of (unordered) pairs of
     * intersecting discs. The function should return −1 if the number of intersecting pairs exceeds 10,000,000.
     *
     * time complexity is O(N*log(N))
     * space complexity is O(N)
     */
    public int numberOfDiscIntersections(int[] A){
        // write your code in Java SE 8
        int counter = 0, j= 0;
        long[] upper = new long[A.length];
        long[] lower = new long[A.length];

        for(int i=0; i < A.length; i++) {
            lower[i] =(long) i-A[i];
            upper[i] =(long) i+A[i];
        }

        Arrays.sort(lower);
        Arrays.sort(upper);

        for(int i= 0; i<A.length; i++) {
            while(j < A.length && upper[i] >= lower[j]){
                counter += j-i;
                j++;
            }
            if(counter > 10000000) return -1;
        }
        return counter;
    }

    public int numberOfDiscIntersections2(int[] A) {
        // the original version is at
        // http://stackoverflow.com/questions/4801242/algorithm-to-calculate-number-of-intersecting-discs
        int N = A.length;
        if (N < 2)
            return 0;
        // stores the number of discs which start at each point
        int[] discStart = new int[N];
        // stores the number of discs which end at each point
        int[] discEnd = new int[N];
        for (int i = 0; i < N; i++) {
            discStart[Math.max(0, i - A[i])]++;
            // the result of i + A[i] could be over the max integer in java and
            // it will become a negative integer.
            if (i + A[i] < 0)
                discEnd[N-1]++;
            else
                discEnd[Math.min(N - 1, i + A[i])]++;
        }
        // the number of discs which haven't been calculated at a very point
        int unCalcDiscNo = 0;
        int result = 0;
        for (int i = 0; i < N; i++) {
            if (discStart[i] > 0) {
                // calculate the product of the number of discs that haven't
                // been calculated and the number of started discs at this point
                result += unCalcDiscNo * discStart[i];
                // calculate the number of intersections of the started discs at
                // this point, the algorithm is 1+2+...+N = N*(N-1)/2
                result += discStart[i] *(discStart[i] - 1) / 2;
                if (result > 10000000)
                    return -1;
                // add the number of start discs to unCalcDiscNo
                unCalcDiscNo += discStart[i];
            }
            if (discEnd[i] > 0)
                // subtract the calculated discs from unCalcDiscNo
                unCalcDiscNo -= discEnd[i];
        }
        return result;
    }

    public int numberOfDiscIntersections3(int[] A) {
        // write your code in Java SE 8
        int N = A.length;
        if (N < 2)
            return 0;
        // intervals stores the two elements : i - A[i] and i + A[i]
        long[][] intervals = new long[N][2];
        for (int i = 0; i < N; i++) {
            intervals[i][0] = (long)i - A[i];
            intervals[i][1] = (long)i + A[i];
        }
        // using the lambda expression to sort a two dimensional array by the
        // intervals left end in ascending order
        Arrays.sort(intervals, (long[] a, long[] b) -> Long.signum(a[0] - b[0]));
        int result = 0;
        for (int i = 0; i < N-1; i++) {
            // using the intervals' right end as the key value of binary search
            long rightEnd = intervals[i][1];
            int binarySearchLeft = i+1;
            int binarySearchRight = N-1;
            int resultIndex = i;
            // using the binary search to find the number of intersections
            while (binarySearchLeft <= binarySearchRight) {
                int binarySearchMid = (binarySearchLeft + binarySearchRight) / 2;
                if (intervals[binarySearchMid][0] <= rightEnd) {
                    resultIndex = binarySearchMid;
                    binarySearchLeft = binarySearchMid + 1;
                }
                else
                    binarySearchRight = binarySearchMid - 1;
            }
            // count the number of intersections
            result += (resultIndex - i);
            if (result > 10000000)
                return -1;
        }
        return result;
    }

    /**
     * A zero-indexed array A consisting of N integers is given. A triplet (P, Q, R) is triangular if 0 ≤ P < Q < R < N and:
     * A[P] + A[Q] > A[R],
     * A[Q] + A[R] > A[P],
     * A[R] + A[P] > A[Q].
     *
     * A = {10, 2, 5, 1, 8, 20}
     * the function should return 1,
     * Triplet (0, 2, 4) is triangular.
     *
     * A = {10, 50, 5, 1}
     * the function should return 0.
     *
     * time complexity is O(N*log(N))
     * space complexity is O(N)
     */
    public int triangle(int[] A) {
        if(A.length< 3)
            return 0;

        Arrays.sort(A);

        for(int i = 0; i < A.length -2; i++){
            if((long)A[i]+(long)A[i+1]>A[i+2])
                return 1;
        }

        return 0;
    }

    public int triangle2(int[] A) {
        int N = A.length;
        if (N < 3)
            return 0;
        // using the built-in sort method because it can perform O(N*log(N))
        // time complexity at the worst case.The ordinary quick sort method can
        // not pass the performance test.
        Arrays.sort(A);
        for (int i = 0; i < N-2; i++) {
            // this algorithm can only test if there is a triangle, it can not
            // get the whole number of the triangles.
            if (A[i] > 0 && A[i] > A[i+2] - A[i+1])
                return 1;
        }
        return 0;
    }





}
