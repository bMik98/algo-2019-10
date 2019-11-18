package fib;

import java.math.BigInteger;

public class Fib {

    private Fib() {
    }

    public static long recursion(int n) {
        return (n < 2) ? n : recursion(n - 1) + recursion(n - 2);
    }

    public static long iteration(int n) {
        if (n < 2) {
            return n;
        }
        if (n == 2) {
            return 1;
        }
        long fn2 = 1;
        long fn1 = 1;
        long fn = 0;
        for (int i = 3; i <= n; i++) {
            fn = fn1 + fn2;
            fn2 = fn1;
            fn1 = fn;
        }
        return fn;
    }

    public static BigInteger unlimitedIteration(int n) {
        if (n < 2) {
            return BigInteger.valueOf(n);
        }
        if (n == 2) {
            return BigInteger.valueOf(1);
        }
        BigInteger fn2 = BigInteger.valueOf(1);
        BigInteger fn1 = BigInteger.valueOf(1);
        BigInteger fn = null;
        for (int i = 3; i <= n; i++) {
            fn = fn1.add(fn2);
            fn2 = fn1;
            fn1 = fn;
        }
        return fn;
    }

    public static long binet(int n) {
        double sqrt5 = Math.sqrt(5);
        double phi = (1 + sqrt5) / 2;
        double fn = Math.pow(phi, n) / sqrt5 + 0.5;
        return (long) fn;
    }

    public static long matrix(int n) {
        long a00 = 1;
        long a01 = 1;
        long a10 = 1;
        long a11 = 0;
        long r0 = 0;
        long r1 = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                long prevR0 = r0;
                r0 = r0 * a00 + r1 * a10;
                r1 = prevR0 * a01 + r1 * a11;
            }
            long prevA00 = a00;
            long prevA01 = a01;
            long prevA10 = a10;
            a00 = a00 * a00 + a01 * a10;
            a01 = prevA00 * a01 + a01 * a11;
            a10 = a10 * prevA00 + a11 * a10;
            a11 = prevA10 * prevA01 + a11 * a11;
            n >>= 1;
        }
        return r0;
    }

    public static long cutieMatrix(int n) {
        Matrix2x2 a = new Matrix2x2(1, 1, 1, 0);
        Matrix1x2 r = new Matrix1x2(0, 1);
        while (n > 0) {
            if ((n & 1) == 1) {
                r = r.multiply(a);
            }
            a = a.square();
            n >>= 1;
        }
        return r.get(0);
    }
}
