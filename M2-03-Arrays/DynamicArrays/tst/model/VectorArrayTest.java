package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorArrayTest {

    private IArray<Integer> array;

    @BeforeEach
    void setUp() {
        array = new VectorArray<>();
    }

    @Test
    void sizeOfEmptyArray() {
        assertEquals(0, array.size());
    }

    @Test
    void addAndRemove() {
        final int VALUE = 123;
        assertEquals(0, array.size());
        array.add(VALUE);
        assertEquals(1, array.size());
        assertEquals(VALUE, array.get(0));
        int removed = array.remove(0);
        assertEquals(VALUE, removed);
        assertEquals(0, array.size());
    }

    @Test
    void addAndRemoveNumberOfValues() {
        final int NUMBER_OF_VALUES = 100;
        assertEquals(0, array.size());
        for (int i = 0; i < NUMBER_OF_VALUES; i++) {
            array.add(i);
        }
        assertEquals(NUMBER_OF_VALUES, array.size());
        for (int i = 0; i < NUMBER_OF_VALUES; i++) {
            assertEquals(i, array.get(i));
        }
        for (int i = 0; i < NUMBER_OF_VALUES; i++) {
            int removed = array.remove(0);
            assertEquals(i, removed);
            assertEquals(NUMBER_OF_VALUES - i - 1, array.size());
        }
        assertEquals(0, array.size());
    }

    @Test
    void addToEmpty() {
        final int VALUE = 999991;
        array.add(VALUE, 0);
        assertEquals(1, array.size());
        assertEquals(VALUE, array.get(0));
    }

    @Test
    void addToHead() {
        final int FIRST_VALUE = 999991;
        array.add(FIRST_VALUE);
        final int ADDED_VALUE_1 = 1;
        array.add(ADDED_VALUE_1, 0);
        final int ADDED_VALUE_2 = 2;
        array.add(ADDED_VALUE_2, 0);
        assertEquals(3, array.size());
        assertEquals(ADDED_VALUE_2, array.get(0));
        assertEquals(ADDED_VALUE_1, array.get(1));
        assertEquals(FIRST_VALUE, array.get(2));
    }

    @Test
    void addToMiddle() {
        final int FIRST_VALUE = 999991;
        array.add(FIRST_VALUE);
        final int LAST_VALUE = 999992;
        array.add(LAST_VALUE);
        final int ADDED_VALUE_1 = 1;
        array.add(ADDED_VALUE_1, 1);
        final int ADDED_VALUE_2 = 2;
        array.add(ADDED_VALUE_2, 1);
        assertEquals(4, array.size());
        assertEquals(FIRST_VALUE, array.get(0));
        assertEquals(ADDED_VALUE_2, array.get(1));
        assertEquals(ADDED_VALUE_1, array.get(2));
        assertEquals(LAST_VALUE, array.get(3));
    }

    @Test
    void getFromEmptyArray() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () ->
                array.get(0));
        assertTrue(exception.getMessage().endsWith(": 0"));
    }

    @Test
    void addByNegativeIndex() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () ->
                array.add(123, -1));
        assertTrue(exception.getMessage().endsWith(": -1"));
    }

    @Test
    void getByNegativeIndex() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () ->
                array.get(-1));
        assertTrue(exception.getMessage().endsWith(": -1"));
    }

    @Test
    void removeFromEmptyArray() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () ->
                array.remove(0));
        assertTrue(exception.getMessage().endsWith(": 0"));
    }

    @Test
    void removeByNegativeIndex() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () ->
                array.remove(-1));
        assertTrue(exception.getMessage().endsWith(": -1"));
    }
}