package gcd;

public class GCD {

    private GCD() {
    }

    public static long bySubtraction(long a, long b) {
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }

    public static long byReminder(long a, long b) {
        while (a != 0 && b != 0) {
            if (a > b) {
                a = a % b;
            } else {
                b = b % a;
            }
        }
        return (a == 0) ? b : a;
    }

    public static long byShift(long a, long b) {
        if (a == 0L) {
            return b;
        }
        if (b == 0L) {
            return a;
        }
        if (a == b) {
            return a;
        }
        if (a == 1 || b == 1) {
            return 1;
        }
        long gcd = 1;
        while (a != 0 && b != 0) {
            if (((a & 1) | (b & 1)) == 0) {
                gcd <<= 1;
                a >>= 1;
                b >>= 1;
            } else if ((a & 1) == 0 && (b & 1) != 0) {
                a >>= 1;
            } else if ((a & 1) != 0 && (b & 1) == 0) {
                b >>= 1;
            } else {
                if (a < b) {
                    long newA = (b - a) >> 1;
                    b = a;
                    a = newA;
                } else {
                    a = (a - b) >> 1;
                }
            }
        }
        return (a == 0) ? gcd * b : gcd * a;
    }

    public static long byShiftRec(long a, long b) {
        if (a == b) {
            return a;
        }
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        if ((~a & 1) == 1) {
            if ((b & 1) == 1) {
                return byShiftRec(a >> 1, b);
            } else {
                return byShiftRec(a >> 1, b >> 1) << 1;
            }
        }
        if ((~b & 1) == 1) {
            return byShiftRec(a, b >> 1);
        }
        return (a > b)
                ? byShiftRec((a - b) >> 1, b)
                : byShiftRec((b - a) >> 1, a);
    }
}
