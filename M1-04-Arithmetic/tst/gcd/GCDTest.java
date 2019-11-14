package gcd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GCDTest {

    private static final long VALUE_A = 1234567890;
    private static final long VALUE_B = 12;
    private static final long RESULT = 6;

    @Test
    void bySubtraction() {
        assertEquals(0, GCD.bySubtraction(0, 0));
        assertEquals(VALUE_A, GCD.bySubtraction(VALUE_A, 0));
        assertEquals(VALUE_B, GCD.bySubtraction(0, VALUE_B));
        assertEquals(1, GCD.bySubtraction(VALUE_A, 1));
        assertEquals(1, GCD.bySubtraction(1, VALUE_B));
        assertEquals(VALUE_A, GCD.bySubtraction(VALUE_A, VALUE_A));
        assertEquals(RESULT, GCD.bySubtraction(VALUE_A, VALUE_B));
        assertEquals(RESULT, GCD.bySubtraction(VALUE_B, VALUE_A));
    }

    @Test
    void byReminder() {
        assertEquals(0, GCD.byReminder(0, 0));
        assertEquals(VALUE_A, GCD.byReminder(VALUE_A, 0));
        assertEquals(VALUE_B, GCD.byReminder(0, VALUE_B));
        assertEquals(1, GCD.byReminder(VALUE_A, 1));
        assertEquals(1, GCD.byReminder(1, VALUE_B));
        assertEquals(VALUE_A, GCD.byReminder(VALUE_A, VALUE_A));
        assertEquals(RESULT, GCD.byReminder(VALUE_A, VALUE_B));
        assertEquals(RESULT, GCD.byReminder(VALUE_B, VALUE_A));
    }

    @Test
    void byShift() {
        assertEquals(0, GCD.byShift(0, 0));
        assertEquals(VALUE_A, GCD.byShift(VALUE_A, 0));
        assertEquals(VALUE_B, GCD.byShift(0, VALUE_B));
        assertEquals(1, GCD.byShift(VALUE_A, 1));
        assertEquals(1, GCD.byShift(1, VALUE_B));
        assertEquals(VALUE_A, GCD.byShift(VALUE_A, VALUE_A));
        assertEquals(RESULT, GCD.byShift(VALUE_A, VALUE_B));
        assertEquals(RESULT, GCD.byShift(VALUE_B, VALUE_A));
    }

    @Test
    void byShiftRec() {
        assertEquals(0, GCD.byShiftRec(0, 0));
        assertEquals(VALUE_A, GCD.byShiftRec(VALUE_A, 0));
        assertEquals(VALUE_B, GCD.byShiftRec(0, VALUE_B));
        assertEquals(1, GCD.byShiftRec(VALUE_A, 1));
        assertEquals(1, GCD.byShiftRec(1, VALUE_B));
        assertEquals(VALUE_A, GCD.byShiftRec(VALUE_A, VALUE_A));
        assertEquals(RESULT, GCD.byShiftRec(VALUE_A, VALUE_B));
        assertEquals(RESULT, GCD.byShiftRec(VALUE_B, VALUE_A));
    }
}