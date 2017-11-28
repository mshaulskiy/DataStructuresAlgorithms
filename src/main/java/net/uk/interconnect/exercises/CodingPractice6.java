package net.uk.interconnect.exercises;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;
import java.util.stream.IntStream;

public class CodingPractice6 {

    /**
     * A string S consisting of N characters is considered to be properly nested if any of the following conditions is true:
     * S is empty;
     * S has the form "(U)" or "[U]" or "{U}" where U is a properly nested string;
     * S has the form "VW" where V and W are properly nested strings.
     * For example, the string "{[()()]}" is properly nested but "([)()]" is not.
     * <p>
     * Given a string S consisting of N characters, returns 1 if S is properly nested and 0 otherwise.
     * For example, given S = "{[()()]}", the function should return 1 and given S = "([)()]", the function should
     * return 0, as explained above.
     * <p>
     * String S consists only of the following characters: "(", "{", "[", "]", "}" and/or ")".
     * <p>
     * time complexity is O(N)
     * space complexity is O(N)
     */
    public int brackets(String S) {

        char[] charArr = S.toCharArray();

        Stack<Character> st = new Stack<Character>();

        for (int i = 0; i < charArr.length; i++) {

            char curr = charArr[i];

            if (curr == '(' ||
                    curr == '[' ||
                    curr == '{'
                    ) {
                st.push(charArr[i]);
            } else {

                try {
                    char last = st.pop();

                    if (!(
                            (last == '(' && curr == ')') ||
                                    (last == '{' && curr == '}') ||
                                    (last == '[' && curr == ']'))
                            ) {
                        return 0;
                    }
                } catch (Exception e) {
                    return 0;
                }
            }
        }

        return st.size() == 0 ? 1 : 0;
    }

    public int brackets2(String S) {
        // write your code in Java SE 8
        int N = S.length();
        // if the length of string s is odd, then it can't be nested.
        if (N % 2 == 1)
            return 0;
        char[] stack = new char[N];
        int num = 0;
        for (char ele : S.toCharArray()) {
            // push the left half in the stack, and because of the same length
            // of string s and stack array so there is no need to worry about if
            // the stack is full.
            if (ele == '(' || ele == '{' || ele == '[') {
                stack[num++] = ele;
            }
            // pop the left half out of the stack if the element can be matched
            // from the top of the stack
            else if (num != 0 &&
                    (ele == ')' && stack[num - 1] == '(' ||
                            ele == '}' && stack[num - 1] == '{' ||
                            ele == ']' && stack[num - 1] == '['))
                num--;
                // if there is no match character we can sure that this is not a
                // properly nested string
            else
                return 0;
        }
        // if the stack is empty, then the string is properly nested.
        if (num == 0)
            return 1;
        else
            return 0;
    }

    /**
     * You are given two non-empty zero-indexed arrays A and B consisting of N integers. Arrays A and B represent N
     * voracious fish in a river, ordered downstream along the flow of the river.
     * The fish are numbered from 0 to N − 1. If P and Q are two fish and P < Q, then fish P is initially upstream
     * of fish Q. Initially, each fish has a unique position.
     * Fish number P is represented by A[P] and B[P]. Array A contains the sizes of the fish. All its elements are unique.
     * Array B contains the directions of the fish. It contains only 0s and/or 1s, where:
     * <p>
     * 0 represents a fish flowing upstream,
     * 1 represents a fish flowing downstream.
     * <p>
     * If two fish move in opposite directions and there are no other (living) fish between them, they will eventually
     * meet each other. Then only one fish can stay alive − the larger fish eats the smaller one. More precisely,
     * we say that two fish P and Q meet each other when P < Q, B[P] = 1 and B[Q] = 0, and there are no living fish
     * between them. After they meet:
     * <p>
     * If A[P] > A[Q] then P eats Q, and P will still be flowing downstream,
     * If A[Q] > A[P] then Q eats P, and Q will still be flowing upstream.
     * <p>
     * We assume that all the fish are flowing at the same speed. That is, fish moving in the same direction never meet.
     * The goal is to calculate the number of fish that will stay alive.
     * <p>
     * A = {4, 3, 2, 1, 5}
     * B = {0, 1, 0, 0, 0}
     * the function should return 2
     * <p>
     * Initially all the fish are alive and all except fish number 1 are moving upstream. Fish number 1 meets fish
     * number 2 and eats it, then it meets fish number 3 and eats it too. Finally, it meets fish number 4 and is
     * eaten by it. The remaining two fish, number 0 and 4, never meet and therefore stay alive.
     * <p>
     * Given two non-empty zero-indexed arrays A and B consisting of N integers, returns the number of fish that will stay alive.
     * <p>
     * each element of array A is an integer within the range [0..1,000,000,000];
     * each element of array B is an integer that can have one of the following values: 0, 1;
     * the elements of A are all distinct.
     * <p>
     * time complexity is O(N)
     * space complexity is O(N)
     */
    public int fish(int[] A, int[] B) {
        Stack<Integer> upStream = new Stack<>();
        int downstreamCount = 0;

        for (int i = 0; i < B.length; i++) {
            if (B[i] == 1) {
                upStream.push(A[i]);
            } else {
                downstreamCount++;
                while (upStream.size() > 0) {
                    int upstreamItem = upStream.pop();
                    if (A[i] < upstreamItem) {
                        upStream.push(upstreamItem);
                        downstreamCount--;
                        break;
                    }
                }
            }
        }
        return downstreamCount + upStream.size();
    }


    public int fish2(int[] A, int[] B) {
        Stack sa = new Stack();
        int live = 0;
        for (int i = 0; i < A.length; i++) {
            if (B[i] == 1) {
                sa.push(A[i]);
            } else {
                while (!sa.isEmpty()) {
                    int pa = (int) sa.peek();
                    if (pa < A[i]) {
                        sa.pop();
                    } else {
                        break;
                    }
                }
                if (sa.isEmpty()) {
                    live++;
                }
            }
        }
        return live + sa.size();
    }

    public int fish3(int[] A, int[] B) {
        int N = A.length;
        // the stack of alive fish flowing downstream
        int[] downstreamStack = new int[N];
        int num = 0;
        // store the number of alive fish flowing upstream
        int aliveUpstreamNum = 0;
        for (int i = 0; i < N; i++) {
            // current fish is flowing upstream and there isn't fish flowing
            // downstream before
            if (B[i] == 0 && num == 0)
                aliveUpstreamNum++;
            else if (B[i] == 0 && num != 0) {
                // current fish flowing upstream eats the fish flowing
                // downstream before
                while (num != 0 && A[i] > downstreamStack[num - 1])
                    num--;
                // all the fish flowing downstream is eaten by the current fish
                // flowing upstream
                if (num == 0)
                    aliveUpstreamNum++;
            } else {
                // there is a fish flowing downstream and push it into the stack
                downstreamStack[num++] = A[i];
            }
        }
        // the alive fish is consist of the fish flowing upstream and the fish
        // flowing downstream
        return aliveUpstreamNum + num;
    }

    /**
     * A string S consisting of N characters is called properly nested if:
     * S is empty;
     * S has the form "(U)" where U is a properly nested string;
     * S has the form "VW" where V and W are properly nested strings.
     * For example, string "(()(())())" is properly nested but string "())" isn't.
     * Given a string S consisting of N characters, returns 1 if string S is properly nested and 0 otherwise.
     * For example, given S = "(()(())())", the function should return 1 and given S = "())", the function should
     * return 0, as explained above.
     * <p>
     * string S consists only of the characters "(" and/or ")".
     * <p>
     * time complexity is O(N);
     * space complexity is O(1)
     */
    public int nestingBrackets(String S) {
        /*
        * Since Nesting deals with only one kind of bracket (parenthesis), it is a special case of the Brackets problem.
        * In the solution to Brackets, you keep opening brackets in the stack and keep popping the stack whenever you
        * encounter a closing bracket. Since all characters in your stack in Nesting are going to be "(", you can get
        * away with not creating the stack and just keeping a stack size.
         */
        int numOpen = 0;

        for (char bracket : S.toCharArray()) {
            if (bracket == '(')
                numOpen += 1;
            else
                numOpen -= 1;

            if (numOpen < 0)
                return 0;
        }

        return numOpen == 0 ? 1 : 0;
    }

    public int nestingBrackets2(String S) {
        int res = 0;
        char[] ch = S.toCharArray();
        for (char c : ch) {
            if (c == '(') {
                res++;
            } else {
                if (--res < 0) {
                    break;
                }
            }
        }
        return (res == 0) ? 1 : 0;
    }

    public int nestingBrackets3(String S) {
        int N = S.length();
        // if the length of string s is odd, then it can't be nested.
        if (N % 2 == 1)
            return 0;
        char[] stack = new char[N];
        int num = 0;
        for (int i = 0; i < N; i++) {
            // push the '(' into the stack
            if (S.charAt(i) == '(')
                stack[num++] = S.charAt(i);
                // if the stack is not empty, pop the top element out.
            else if (num != 0)
                num--;
                // other situation means it's not a nested string
            else
                return 0;
        }
        if (num == 0)
            return 1;
        else
            return 0;
    }

    /**
     * You are going to build a stone wall. The wall should be straight and N meters long, and its thickness
     * should be constant; however, it should have different heights in different places. The height of the wall
     * is specified by a zero-indexed array H of N positive integers. H[I] is the height of the wall from I to I+1
     * meters to the right of its left end. In particular, H[0] is the height of the wall's left end and H[N−1] is
     * the height of the wall's right end.
     * <p>
     * The wall should be built of cuboid stone blocks (that is, all sides of such blocks are rectangular).
     * Your task is to compute the minimum number of blocks needed to build the wall.
     * <p>
     * Given a zero-indexed array H of N positive integers specifying the height of the wall,
     * returns the minimum number of blocks needed to build it.
     * <p>
     * H = {8, 8, 5, 7, 9, 8, 7, 4, 8}
     * the function should return 7.
     * <p>
     * time complexity is O(N);
     * space complexity is O(N)
     */
    public int stoneWall(int[] H) {
        /*
        * This is a "very hard to understand question"! The goal is to keep the stack sorted overtime a new
        * number is adding to it. You need to count number of pushes!
         */
        int cuts = 0;
        Stack st = new Stack();

        for (int h : H) {
            while (!st.isEmpty() && ((int) st.peek()) > h) {
                st.pop();
            }
            if (st.isEmpty() || ((int) st.peek()) < h) {
                st.push(h);
                cuts++;
            }
        }

        return cuts;
    }

    public int stoneWall2(int[] H) {
        int N = H.length;
        int[] stack = new int[N];
        int num = 0;
        stack[num++] = H[0];
        int result = 1;
        for (int i = 1; i < N; i++) {
            // store the stonewall in ascending order and pop out the larger
            // stonewall than the current stonewall
            while (num > 0 && stack[num - 1] > H[i]) {
                num--;
            }
            // if the stack is empty or the top of stack isn't equal to the
            // current stonewall, then we should push the current stonewall in
            // the stack and add 1 to the result.
            if (num == 0 || stack[num - 1] != H[i]) {
                stack[num++] = H[i];
                result++;
            }
        }
        return result;
    }

    public int stoneWall3(int[] H) {
        int max = 0, cnt = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < H.length; i++) {
            if (stack.empty()) {
                stack.push(H[i]);
                max = H[i];
                continue;
            }

            if (H[i] == max)
                continue;

            if (H[i] > max) {
                stack.push(H[i]);
                max = H[i];
            }

            if (H[i] < max) {
                while (!stack.empty() && stack.peek() > H[i]) {
                    stack.pop();
                    cnt++;
                }
                if (stack.empty() || stack.peek() < H[i])
                    stack.push(H[i]);

                max = stack.peek();
            }
        }
        return cnt + stack.size();
    }

    /**
     * The operation 'result += 1;' is dominant and will be executed n times.
     * The complexity is described in Big-O notation: in this case O(n) — linear complexity.
     *
     * @param n
     * @return
     */
    public int dominant(int[] n) {
        int result = 0;
        for (int i : n)
            result += 1; // dominant operation

        return result;
    }

    /**
     * Constant time — O(1). There is always a fixed number of operations.
     *
     * @param n
     * @return
     */
    public int constant(int n) {
        int result = n * n;
        return result;
    }

    /**
     * Logarithmic time — O(log n).
     * The value of n is halved on each iteration of the loop. If n = 2x then log n = x.
     *
     * @param n
     * @return
     */
    public int logarithmic(int n) {
        int result = 0;
        while (n > 1) {
            n /= 2;
            result += 1;
        }
        return result;
    }

    /**
     * Linear time — O(n).
     * How long would the program below take to execute, depending on the input data?
     * <p>
     * Let’s note that if the first value of array A is 0 then the program will end immediately.
     * But remember, when analyzing time complexity we should check for worst cases.
     * The program will take the longest time to execute if array A does not contain any 0.
     *
     * @param n
     * @return
     */
    public int linear(int n, int[] A) {
        for (int i = 0; i < n; ++i)
            if (A[i] == 0) return 0;
        return 1;
    }

    /**
     * Quadratic time — O(n^2).
     * The result of the function equals 1/2 * ( n * ( n + 1 ) ) = 1/2 * n^2 + 1/2 * n (the explanation is in the
     * exercises).
     * <p>
     * When calculating the complexity we are interested in a term that grows fastest, so we not only omit
     * constants, but also other terms ( 1/2 * n in this case). Thus we get quadratic time complexity.
     * Sometimes the complexity depends on more variables (see example below).
     *
     * @param n
     * @return
     */
    public int quadratic(int n) {
        int result = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i; j < n; ++j) {
                result += 1;
                System.out.print("1");
            }
            System.out.println("2");
        }
        return result;
    }

    /**
     * Linear time — O(n + m).
     * Sometimes the complexity depends on more variables (see example below).
     *
     * @param n
     * @return
     */
    public int linear2(int n, int m) {
        int result = 0;
        for (int i = 0; i < n; ++i)
            result += i;
        for (int j = 0; j < m; ++j)
            result += j;

        return result;
    }


    /*
     * Exercise
     * Problem: You are given an integer n. Count the total of 1+2+...+n.
     * Solution: The task can be solved in several ways. Some person, who knows nothing about
     * time complexity, may implement an algorithm in which the result is incremented by 1:
     */

    /**
     * Slow solution — time complexity O(n^2).
     *
     * @param n
     * @return
     */
    public int slowSolution(int n) {
        int result = 0;
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < i + 1; ++j)
                result += 1;

        return result;
    }

    /**
     * Fast solution — time complexity O(n).
     *
     * @param n
     * @return
     */
    public int fastSolution(int n) {
        int result = 0;
        for (int i = 0; i < n; ++i)
            result += (i + 1);

        return result;
    }

    /**
     * Model solution — time complexity O(1).
     *
     * @param n
     * @return
     */
    public int modelSolution(int n) {
        int result = n * (n + 1) / 2;
        return result;
    }

    /**
     * A zero-indexed array A consisting of N different integers is given. The array contains integers in
     * the range [1..(N + 1)], which means that exactly one element is missing.
     * Your goal is to find that missing element.
     * Given a zero-indexed array A, returns the value of the missing element.
     * A = {2, 3, 1, 5}
     * the function should return 4, as it is the missing element.
     * <p>
     * the elements of A are all distinct;
     * <p>
     * time complexity is O(N);
     * space complexity is O(1)
     */
    public int missingElem(int[] A) {
        int n = A.length;
        int sum1 = 0, sum2 = 0;

        for (int i = 0; i <= n; ++i) {
            sum1 ^= (i + 1);
            if (i < n)
                sum2 ^= A[i];
        }

        return sum1 ^ sum2;
    }

    public int missingElem2(int[] A) {
        int n = A.length;
        int[] b = new int[n + 1];

        long sum = Integer.toUnsignedLong(((n + 1) * (2 + n)) / 2);

        for (int i = 0; i < n + 1; ++i) {
            b[i] = i + 1;
        }
//        long sum = IntStream.of(b).sum();
        long sum2 = IntStream.of(A).sum();

        return (int) (sum - sum2);
    }

    public int missingElem3(int[] A) {
        // write your code in Java SE 8
        // this is the key declaration.if you use int instead of long, you'll
        // not pass the performance test.
        long N = A.length + 1;
        // calculate the sum of 1 + 2 + ... + (N + 1)
        long result = (N * (N + 1)) / 2;
        // get the result by subtracting all the array element from 1 + 2 +
        // ... (N + 1)
        for (int element : A)
            result -= element;
        return (int) result;
    }

    /**
     * A non-empty zero-indexed array A consisting of N integers is given. Array A represents numbers on a tape.
     * Any integer P, such that 0 < P < N, splits this tape into two non-empty parts: A[0], A[1], ..., A[P − 1]
     * and A[P], A[P + 1], ..., A[N − 1].
     * The difference between the two parts is the value of: |(A[0] + A[1] + ... + A[P − 1]) − (A[P] + A[P + 1] + ... + A[N − 1])|
     * In other words, it is the absolute difference between the sum of the first part and the sum of the second part.
     * <p>
     * A = {3, 1, 2, 4, 3}
     * the function should return 1
     * <p>
     * We can split this tape in four places:
     * P = 1, difference = |3 − 10| = 7
     * P = 2, difference = |4 − 9| = 5
     * P = 3, difference = |6 − 7| = 1
     * P = 4, difference = |10 − 3| = 7
     * Given a non-empty zero-indexed array A of N integers, returns the minimal difference that can be achieved.
     * <p>
     * time complexity is O(N);
     * space complexity is O(N)
     */
    public int tapeEquilibrium(int[] A) {
        int diff = IntStream.of(A).sum();
        int minDiff = Integer.MAX_VALUE;

        for (int p = 0; p < A.length - 1; p++) {
            diff -= A[p] * 2;
            minDiff = Math.min(minDiff, Math.abs(diff));
        }
        return minDiff;
    }


    public int tapeEquilibrium2(int[] A) {
        /*
        * Skipping some logical steps here to shorten the code. More verbose version of this code involves
        * keeping two sums (one to the left of p, the other one to the right of p) and goes something like this.
        * Here you may see where diff -= A[p] * 2 came from. Every iteration diff "gets smaller" by exactly
        * 2 * A[p], because one time it should be added to sum1 and the second time it should be removed from sum2.
         */
        int sum1 = 0;
        int sum2 = IntStream.of(A).sum();
        int minDiff = Integer.MAX_VALUE;

        for (int p = 0; p < A.length - 1; p++) {
            sum1 += A[p];
            sum2 -= A[p];
            int diff = sum2 - sum1;
            minDiff = Math.min(minDiff, Math.abs(diff));
        }
        return minDiff;
    }

    public int tapeEquilibrium3(int[] A) {
        // write your code in Java SE 8
        int len = A.length;
        int[] sum = new int[len];
        // get the sum from 0 to n and store it in the sum[n]
        sum[0] = A[0];
        for (int i = 1; i < len; i++) {
            sum[i] = sum[i - 1] + A[i];
        }
        int min = Math.abs(sum[len - 1] - 2 * sum[0]);
        for (int i = 2; i < len; i++) {
            int temp = Math.abs(sum[len - 1] - 2 * sum[i - 1]);
            if (temp < min)
                min = temp;
        }
        return min;
    }

    public static char firstNonRepeatedChar(String str) {
        Map<Character, Integer> counts = new LinkedHashMap<>(str.length());
        for (char c : str.toCharArray()) {
            counts.put(c, counts.containsKey(c) ? counts.get(c) + 1 : 1);
        }

        for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        throw new RuntimeException("didn't find any non repeated Character");
    }


}
