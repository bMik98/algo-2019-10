package fib;

import java.math.BigInteger;
import java.util.function.IntFunction;
import java.util.function.IntToLongFunction;

public class Fibonacci {

    private static IntToLongFunction byRecursion = n -> Fib.recursion(n);
    private static IntToLongFunction byIteration = n -> Fib.iteration(n);
    private static IntToLongFunction byBinet = n -> Fib.binet(n);
    private static IntToLongFunction byMatrix = n -> Fib.matrix(n);
    private static IntToLongFunction byCutieMatrix = n -> Fib.cutieMatrix(n);

    private static IntFunction<BigInteger> byIterationUnlimited = n -> Fib.unlimitedIteration(n);

    public static void main(String[] args) {
        test(byRecursion, 45, 1, "recursion");
        final int N = 92;
        final int TRIES = 1_000_000;
        test(byIteration, N, TRIES, "iteration");
        test(byBinet, N, TRIES, "Binet's formula");
        test(byMatrix, N, TRIES, "matrix");
        test(byCutieMatrix, N, TRIES, "matrix ver.2");

        test(byIterationUnlimited, N, TRIES, "unlimited iteration");
    }

    private static void test(IntToLongFunction function, int n, int tries, String algorithmName) {
        long result = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < tries; i++) {
            result = function.applyAsLong(n);
        }
        long finish = System.currentTimeMillis();
        System.out.printf("Fibonacci by %-20s of %d is %d, executed: %d times for: %d ms%n",
                algorithmName, n, result, tries, finish - start);
    }

    private static void test(IntFunction<BigInteger> function, int n, int tries, String algorithmName) {
        long start = System.currentTimeMillis();
        BigInteger result = BigInteger.valueOf(0);
        for (int i = 0; i < tries; i++) {
            result = function.apply(n);
        }
        long finish = System.currentTimeMillis();
        System.out.printf("Fibonacci by %-20s of %d is %s, executed: %d times for: %d ms%n",
                algorithmName, n, result.toString(), tries, finish - start);
    }
}
