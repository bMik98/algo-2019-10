package gcd;

public class GreatestCommonDivisor {

    private static final int REPEATS = 1_000_000;

    public static void main(String[] args) {
        doTest(12, 1_234_567_890, 100, REPEATS);
        doTest(1_234_567_890, 12, 100, REPEATS);
        doTest(15, 20, REPEATS);
        doTest(20, 15, REPEATS);
        doTest(112345687L * 33, 142151454L * 33, REPEATS);
        doTest(1_500_000_000, 2_000_000_000, REPEATS);
    }

    private static void doTest(long a, long b, int repeats) {
        doTest(a, b, repeats, repeats);
    }

    private static void doTest(long a, long b, int subtractionRepeats, int repeats) {
        System.out.println("-------------------");
        bySubtraction(a, b, subtractionRepeats);
        byReminder(a, b, repeats);
        byShift(a, b, repeats);
        byShiftRec(a, b, repeats);
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
