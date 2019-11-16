package fib;

import java.math.BigInteger;

public class Fibonacci {

    public static void main(String[] args) {
        final int N = 92;
        final int TRIES = 1_000_000;
        testByRecursion(45);
        testByIteration(N, TRIES);
        testByUnlimitedIteration(N, TRIES);
        testByBinetsFormula(N, TRIES);
        testByMatrix(N, TRIES);
    }

    private static void testByRecursion(int n) {
        long start = System.currentTimeMillis();
        long result = Fib.recursion(n);
        long finish = System.currentTimeMillis();
        System.out.printf("Fibonacci by recursion of %d is %d, executed for: %d ms%n",
                n, result, finish - start);
    }

    private static void testByIteration(int n, int tries) {
        long start = System.currentTimeMillis();
        long result = 0;
        for (int i = 0; i < tries; i++) {
            result = Fib.iteration(n);
        }
        long finish = System.currentTimeMillis();
        System.out.printf("Fibonacci by iteration of %d is %d, executed for: %d ms%n",
                n, result, finish - start);
    }

    private static void testByUnlimitedIteration(int n, int tries) {
        long start = System.currentTimeMillis();
        BigInteger result = BigInteger.valueOf(0);
        for (int i = 0; i < tries; i++) {
            result = Fib.unlimitedIteration(n);
        }
        long finish = System.currentTimeMillis();
        System.out.printf("Fibonacci by unlimited iterations of %d is %s, executed for: %d ms%n",
                n, result.toString(), finish - start);
    }

    private static void testByBinetsFormula(int n, int tries) {
        long start = System.currentTimeMillis();
        long result = 0;
        for (int i = 0; i < tries; i++) {
            result = Fib.binet(n);
        }
        long finish = System.currentTimeMillis();
        System.out.printf("Fibonacci by Binet's formula of %d is %d, executed for: %d ms%n",
                n, result, finish - start);
    }

    private static void testByMatrix(int n, int tries) {
        long start = System.currentTimeMillis();
        long result = 0;
        for (int i = 0; i < tries; i++) {
            result = Fib.matrix(n);
        }
        long finish = System.currentTimeMillis();
        System.out.printf("Fibonacci by matrix of %d  is %d, executed for: %d ms%n",
                n, result, finish - start);
    }
}
