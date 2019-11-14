package gcd;

public class GreatestCommonDivisor {

    public static void main(String[] args) {
        bySubtraction(1234567890L, 12, 100);
        byReminder(1234567890L, 12, 1000000);
        byShift(1234567890L, 12, 1000000);
        byShiftRec(1234567890L, 12, 1000000);
    }

    private static void bySubtraction(long a, long b, int times) {
        long result = -1;
        long start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            result = GCD.bySubtraction(a, b);
        }
        long finish = System.currentTimeMillis();
        System.out.printf("GCD by subtraction of %d and %d is %d, executed %d times for: %d ms%n",
                a, b, result, times, finish - start);
    }

    private static void byReminder(long a, long b, int times) {
        long result = -1;
        long start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            result = GCD.byReminder(a, b);
        }
        long finish = System.currentTimeMillis();
        System.out.printf("GCD by reminder of %d and %d is %d, executed %d times for: %d ms%n",
                a, b, result, times, finish - start);
    }

    private static void byShift(long a, long b, int times) {
        long result = -1;
        long start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            result = GCD.byShift(a, b);
        }
        long finish = System.currentTimeMillis();
        System.out.printf("GCD by binary shift of %d and %d is %d, executed %d times for: %d ms%n",
                a, b, result, times, finish - start);
    }

    private static void byShiftRec(long a, long b, int times) {
        long result = -1;
        long start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            result = GCD.byShiftRec(a, b);
        }
        long finish = System.currentTimeMillis();
        System.out.printf("GCD by recursive binary shift of %d and %d is %d, executed %d times for: %d ms%n",
                a, b, result, times, finish - start);
    }


}
