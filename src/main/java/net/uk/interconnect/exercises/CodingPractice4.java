package net.uk.interconnect.exercises;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodingPractice4 {

    public int calculateFactorial(int n){
        int factorial = 1;
        for (int i = 1; i < n + 1; ++i)
            factorial *= i;

        return factorial;
    }

    public void printTriangle(int n){
        for(int i = 0; i < n + 1; ++i) {
            for (int j = 0; j < i; ++j)
                System.out.print("* ");
            System.out.println();
        }
    }

    public void printUpsideDownSymmetricalTriangle(int n){
        for(int i = 4; i > 0; --i) {
            for (int j = 0; j < n - i; ++j)
                System.out.print("  ");
            for (int k = 0; k < 2 * i - 1; ++k)
                System.out.print("* ");
            System.out.println();
        }
    }

    public int countDecimalDigits(int n){
        int count = 0;
        while (n > 0){
            n /= 10;
            count++;
        }
        return count;
    }

    public List<Integer> fibonacciSequence(int n){
        int a = 0, b = 1, c;
        List<Integer> fibs = new ArrayList<>();

        while (a <= n){
            System.out.print(a + " ");
            fibs.add(a);
            c = a + b;
            a = b;
            b = c;
        }

        return fibs;
    }

    public Set<String> printDaysOfTheWeek(String[] days){
        Set<String> d = new HashSet<>();

        for (String day : days){
            System.out.print(day + " ");
            d.add(day);
        }

        return d;
    }

    public void printDaysOfTheWeek(Map<String,String> days){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String,String> day : days.entrySet()){
            sb.append(day.getKey() + " stands for " + day.getValue()).append("\n");
        }
        System.out.println(sb.toString());
    }


    public static int[] prefixSum(int[] A) {
        int n = A.length;
        int[] p = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            p[i] = p[i - 1] + A[i - 1];
        }
        return p;
    }

    public static int[] suffixSum(int[] A) {
        int n = A.length;
        int[] p = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            p[i] = p[i + 1] + A[i];
        }
        return p;
    }

    public static int countTotal(int[] P, int x, int y) {
        return P[y + 1] - P[x];
    }

    public static int mushrooms(int[] B, int k, int m) {
        int n = B.length;
        int[] pref = prefixSum(B);
        int result = 0;

        for (int p = 0; p < Math.min(m, k) + 1; p++){
            int leftPos = k - p;
            int rightPos = Math.min(n - 1, Math.max(k, k + m - 2 * p));
            result = Math.max(result, countTotal(pref, leftPos, rightPos));
        }

        for (int p = 0; p < Math.min(m + 1, n - k); p++){
            int rightPos = k + p;
            int leftPos = Math.max(0, Math.min(k, k - (m - 2 * p)));
            result = Math.max(result, countTotal(pref, leftPos, rightPos));
        }

        return result;
    }

    /**
     * regexTestHarness
     */
    public static void main(String[] args){
//        Console console = System.console();
        Scanner console = new Scanner(System.in);
        if (console == null) {
            System.err.println("No console.");
            System.exit(1);
        }
        while (true) {

//            Pattern pattern = Pattern.compile(console.readLine("%nEnter your regex: "));
            Pattern pattern = Pattern.compile(console.nextLine());

//            Matcher matcher = pattern.matcher(console.readLine("Enter input string to search: "));
            Matcher matcher = pattern.matcher(console.nextLine());

            boolean found = false;
            while (matcher.find()) {
                System.out.println("I found the text" +
                        " \"%s\" starting at " +
                        "index %d and ending at index %d.%n" +
                        matcher.group() + " " +
                        matcher.start() + " " +
                        matcher.end());
                found = true;
            }
            if(!found){
                System.out.println("No match found.%n");
            }
        }
    }

    /**
     * A non-empty zero-indexed array A consisting of N integers is given.
     * The consecutive elements of array A represent consecutive cars on a road.
     *
     * Array A contains only 0s and/or 1s:
     * 0 represents a car traveling east,
     * 1 represents a car traveling west.
     *
     * The goal is to count passing cars. We say that a pair of cars (P, Q), where 0 ≤ P < Q < N, is passing when P is
     * traveling to the east and Q is traveling to the west.
     *
     * A = {0, 1, 0, 1, 1}
     * return 5
     *
     * We have five pairs of passing cars: (0, 1), (0, 3), (0, 4), (2, 3), (2, 4).
     * Given a non-empty zero-indexed array A of N integers, returns the number of pairs of passing cars.
     * The function should return −1 if the number of pairs of passing cars exceeds 1,000,000,000.
     *
     * time complexity is O(N)
     * space complexity is O(1)
     */
    public int passingCars(int[] A) {
        long east = 0, passed = 0;
        for (int i : A) {
            if (i == 0) {
                east++;
            } else {
                passed += east;
            }
            if(passed > 1000000000)
                return -1;
        }
        return (int) passed;
    }

    public int passingCars2(int[] A) {
        long west = Arrays.stream(A).filter(i -> i == 1).count();
        long count = 0;

        for (int car : A) {
            if (car == 0) {
                count += west;
            } else {
                west -= 1;
            }
            if(count > 1000000000)
                return -1;
        }
        return (int) count;
    }

    public int passingCars3(int[] A) {
        // write your code in Java SE 8
        int N = A.length;
        // store the times of zero's occurrences
        int zeroCount = 0;
        int result = 0;
        for (int element : A) {
            if (element == 0)
                zeroCount++;
            // add the count of zero to the result when the car is traveling west
            if (element == 1 && zeroCount != 0)
                result += zeroCount;
            // the number of passing cars exceeds 1,000,000,000
            if (result > 1000000000)
                return -1;
        }
        return result;
    }

    /**
     * Count factors of given number n.
     * A positive integer D is a factor of a positive integer N if there exists an integer M such that N = D * M.
     * For example, 6 is a factor of 24, because M = 4 satisfies the above condition (24 = 6 * 4).
     * Given a positive integer N, returns the number of its factors.
     * For example, given N = 24, the function should return 8, because 24 has 8 factors, namely 1, 2, 3, 4, 6, 8, 12, 24.
     * There are no other factors of 24.
     *
     * time complexity is O(sqrt(N))
     * pace complexity is O(1)
     */
    public int countFactors(int N) {
        int numFactors = 0;
        int i;
        for (i = 1; (long) i * i < N; i++) {
            if (N % i == 0) numFactors += 2;
        }

        if ((long) i * i == N) numFactors++;

        return numFactors;
    }

    public int countFactors2(int N) {
        int count = 0;
        for (int i = 1; i <= Math.sqrt(N); ++i)
            if (N % i == 0) {
                count += 2;
                if (i == Math.sqrt(N))
                    --count;
            }
        return count;
    }

    /**
     * Find the maximum number of flags that can be set on mountain peaks.
     * A non-empty zero-indexed array A consisting of N integers is given.
     * A peak is an array element which is larger than its neighbours. More precisely, it is an index P such
     * that 0 < P < N − 1 and A[P − 1] < A[P] > A[P + 1].
     *
     * A = {1, 5, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2}
     * has exactly four peaks: elements 1, 3, 5 and 10.
     * the function should return 3
     *
     * You are going on a trip to a range of mountains whose relative heights are represented by array A, as shown
     * in a figure below. You have to choose how many flags you should take with you. The goal is to set the maximum
     * number of flags on the peaks, according to certain rules.
     *
     * Flags can only be set on peaks. What's more, if you take K flags, then the distance between any two flags
     * should be greater than or equal to K. The distance between indices P and Q is the absolute value |P − Q|.
     *
     * For example, given the mountain range represented by array A, above, with N = 12, if you take:
     * two flags, you can set them on peaks 1 and 5;
     * three flags, you can set them on peaks 1, 5 and 10;
     * four flags, you can set only three flags, on peaks 1, 5 and 10.
     * You can therefore set a maximum of three flags in this case.
     *
     * Given a non-empty zero-indexed array A of N integers, returns the maximum number of flags that can be set
     * on the peaks of the array.
     *
     * time complexity is O(N)
     * space complexity is O(N)
     */
    public int flags(int[] A) {
        int n = A.length;

        int j = 0;
        int[] p = new int[n];
        for (int i = 1; i < n - 1; i++) {
            if (A[i - 1] < A[i] && A[i] > A[i + 1]) {
                p[j++] = i;
            }
        }

        if (j < 2) {
            return j;
        }

        int maxD = p[j - 1] - p[0];
        int maxF = (int) (Math.sqrt(maxD) + 1);

        int max = 0;
        for (int k = 2; k <= maxF; k++) {
            int lastPick = p[0];
            int used = 1;
            for (int c = 1; c < j && used < k; c++) {
                if (p[c] - lastPick >= k) {
                    lastPick = p[c];
                    used++;
                }
            }
            max = Math.max(used, max);
        }

        return max;
    }

    public int flags2(int[] A) {
        // this solution is based on the algorithm of the official solution
        int N = A.length;
        boolean[] peak = new boolean[N];
        for (int i = 1; i < N-1; i++) {
            if (A[i] > A[i-1] && A[i] > A[i+1])
                peak[i] = true;
        }
        int[] next = new int[N];
        next[N-1] = -1;
        for (int i = N-2; i >= 0; i--) {
            if (peak[i])
                next[i] = i;
            else
                next[i] = next[i+1];
        }
        int flags = 1;
        int result = 0;
        while (flags * flags <= 2 * N) {
            int pos = 0;
            int count = 0;
            while (pos < N && count < flags) {
                pos = next[pos];
                if (pos == -1)
                    break;
                count++;
                pos += flags;
            }
            result = Math.max(result, count);
            flags++;
        }
        return result;
    }

    /**
     * Find the minimal perimeter of any rectangle whose area equals N.
     * An integer N is given, representing the area of some rectangle.
     * The area of a rectangle whose sides are of length A and B is A * B, and the perimeter is 2 * (A + B).
     * The goal is to find the minimal perimeter of any rectangle whose area equals N. The sides of this rectangle
     * should be only integers.
     *
     * For example, given integer N = 30, rectangles of area 30 are:
     * (1, 30), with a perimeter of 62,
     * (2, 15), with a perimeter of 34,
     * (3, 10), with a perimeter of 26,
     * (5, 6), with a perimeter of 22.
     *
     * Given an integer N, returns the minimal perimeter of any rectangle whose area is exactly equal to N.
     * For example, given an integer N = 30, the function should return 22, as explained above.
     *
     * time complexity is O(sqrt(N))
     * space complexity is O(1)
     */
    public int minPerimeterRectangle(int N) {
        int i = (int) Math.floor(Math.sqrt(N));
        while (i > 0) {
            if (N % i == 0)
                return 2 * (i + N / i);
            --i;
        }
        return -1;
    }

    public int minPerimeterRectangle2(int N) {
        for(int i= (int)Math.sqrt(N); i>0; i--)
            if(N%i==0)
                return 2*(i+(N/i));
        return -1;
    }

    public int minPerimeterRectangle3(int N) {
        // we know that the minimal perimeter of a rectangle is when the length and width are very close.
        int length = 0;
        int width = 0;
        for (int i = 1; i * i <= N; i++) {
            if (N % i == 0) {
                length = i;
                width = N / i;
            }
        }
        return 2 * (length + width);
    }

    /**
     * Divide an array into the maximum number of same-sized blocks, each of which should contain
     * an index P such that A[P - 1] < A[P] > A[P + 1].
     * A peak is an array element which is larger than its neighbors. More precisely, it is an index P such
     * that 0 < P < N − 1,  A[P − 1] < A[P] and A[P] > A[P + 1].
     *
     * A = {1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2}
     * has exactly three peaks: 3, 5 and 10.
     * the function should return 3
     *
     * We want to divide this array into blocks containing the same number of elements. More precisely, we want
     * to choose a number K that will yield the following blocks:
     * A[0], A[1], ..., A[K − 1],
     * A[K], A[K + 1], ..., A[2K − 1],
     * ...
     * A[N − K], A[N − K + 1], ..., A[N − 1].
     * What's more, every block should contain at least one peak. Notice that extreme elements of the blocks
     * (for example A[K − 1] or A[K]) can also be peaks, but only if they have both neighbors (including one in an adjacent blocks).
     *
     * The goal is to find the maximum number of blocks into which the array A can be divided.
     * Array A can be divided into blocks as follows:
     * one block (1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2). This block contains three peaks.
     * two blocks (1, 2, 3, 4, 3, 4) and (1, 2, 3, 4, 6, 2). Every block has a peak.
     * three blocks (1, 2, 3, 4), (3, 4, 1, 2), (3, 4, 6, 2). Every block has a peak.
     *
     * Notice in particular that the first block (1, 2, 3, 4) has a peak at A[3], because A[2] < A[3] > A[4],
     * even though A[4] is in the adjacent block.
     *
     * However, array A cannot be divided into four blocks, (1, 2, 3), (4, 3, 4), (1, 2, 3) and (4, 6, 2),
     * because the (1, 2, 3) blocks do not contain a peak.
     * Notice in particular that the (4, 3, 4) block contains two peaks: A[3] and A[5].
     *
     * The maximum number of blocks that array A can be divided into is three.
     *
     * Given a non-empty zero-indexed array A consisting of N integers, returns the maximum number of blocks into
     * which A can be divided. If A cannot be divided into some number of blocks, the function should return 0.
     *
     * time complexity is O(N*log(log(N)))
     * space complexity is O(N)
     */
    public int peaks(int[] A) {
        int[] A_peaks = new int[A.length];
        A_peaks[0] = 0;
        int cur_val = 0;
        for (int idx = 1; idx < A.length - 1; ++idx) {
            if (A[idx] > A[idx - 1] && A[idx] > A[idx + 1])
                cur_val++;
            A_peaks[idx] = cur_val;
        }
        A_peaks[A.length - 1] = cur_val;

        // Find solution
        int ret_val = 0;
        for (int blocks = 1; blocks <= A.length / 2; ++blocks) {
            if (A.length % blocks != 0)
                continue;
            int block_size = A.length / blocks;

            int prev_peaks = 0;
            int idx = block_size;
            for (; idx < A.length + block_size; idx += block_size) {
                if (A_peaks[idx - 1] == prev_peaks)
                    break;
                prev_peaks = A_peaks[idx - 1];
            }

            if (idx == A.length + block_size)
                ret_val = (int) blocks;
        }

        return ret_val;
    }

    public int peaks2(int[] A) {
        /*
        * My code scored 100% and had a performance of O(N*loglogN). However. I am not sure if that is in indeed the case.
        * So here is my scheme: First I find all the divisors of N, excluding 1 and N.
        * Then I divide the divisors into several arrays. The first array, all elements are multiples of 2, the second array are composed of multiples of 3, and so on...
        * For each of these arrays, I do a binary search to determine the maximum number of blocks that would work for each set.
        * Then, I return the max.
        * However, this does not sound like loglogN. For one, in the worst case scenario, the number of divisors of N is not logN. It is greater.
        * And since I have to perform several binary searches, those combined would be greater than log(# of divisors of N).
        * So all in all, I don't see how my code is scored as O(N*loglogN).
         */
        int n = A.length;

        List<Integer> peaks = new ArrayList<>();
        for (int i = 1; i < n - 1; i++) {
            if (A[i - 1] < A[i] && A[i] > A[i + 1]) {
                peaks.add(i);
            }
        }

        int max = 0;
        for (int i = 1; i < n; i++) {
            if ((n % i) == 0) {
                int bi = 0;
                int block = n / i;
                for (Integer p : peaks) {
                    if (bi * block <= p && p < (bi + 1) * block) {
                        bi++;
                    }
                }
                if (bi == i) {
                    max = i;
                }
            }
        }
        return max;
    }

    public int peaks3(int[] A) {
        // write your code in Java SE 8
        int N = A.length;
        if (N < 3)
            return 0;
        // stores the index of peak element
        boolean[] peakIndex = new boolean[N];
        int peakNumber = 0;
        for (int i = 1; i < N-1; i++) {
            if (A[i] > A[i-1] && A[i] > A[i+1]) {
                peakIndex[i] = true;
                peakNumber++;
            }
        }
        if (peakNumber == 0)
            return 0;
        int blockNumber = 1;
        for (int block = 2; block <= peakNumber; block++) {
            // the array can be divided into blocks
            if (N % block == 0) {
                int k = N / block;
                int blockEnd = k;
                boolean blockExist = true;
                // we know that at last i == N - 1 == blockEnd - 1, so i < N.
                for (int i = 1; i < N; i++) {
                    // this block has at least one peak so move to next block
                    if (peakIndex[i] == true) {
                        i = blockEnd - 1;
                        blockEnd += k;
                        // this block has no peak so this divide has failed
                    } else if (i == blockEnd-1){
                        blockExist = false;
                        break;
                    }
                }
                if (blockExist == true)
                    blockNumber = block;
            }
        }
        return blockNumber;
    }










}
