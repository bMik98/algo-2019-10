package array;

import array.matrix.SpaceArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpaceArrayTest {

    private static final int VECTOR = 16;

    private IArray<Integer> array;

    @BeforeEach
    void setUp() {
        array = new SpaceArray<>(VECTOR, 75);
    }

    @Test
    void defaultConstructor() {
        array = new SpaceArray<>();
        assertEquals(0, array.size());
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
        final int NUMBER_OF_VALUES = 1000;
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
    void insertIntoArrayWithIncompleteSingleRow() {
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
    void insertIntoArrayWithFullSingleRow() {
        for (int i = 0; i < VECTOR; i++) {
            array.add(i);
        }
        assertEquals(VECTOR, array.size());
        final int VALUE = 999991;
        final int INDEX = VECTOR / 2;
        array.add(VALUE, INDEX);
        assertEquals(VECTOR + 1, array.size());
        for (int i = 0; i < INDEX; i++) {
            assertEquals(i, array.get(i));
        }
        assertEquals(VALUE, array.get(INDEX));
        for (int i = INDEX + 1; i < array.size(); i++) {
            assertEquals(i - 1, array.get(i));
        }
    }

    @Test
    void insertIntoMultiRowArrayWithFullLastRow() {
        final int NUMBER_OF_ROWS = 6;
        final int SIZE = VECTOR * NUMBER_OF_ROWS;
        for (int i = 0; i < SIZE; i++) {
            array.add(i);
        }
        assertEquals(SIZE, array.size());
        final int VALUE = 999991;
        final int INDEX = (VECTOR * NUMBER_OF_ROWS / 2) + (VECTOR / 2);
        array.add(VALUE, INDEX);
        assertEquals(SIZE + 1, array.size());
        for (int i = 0; i < INDEX; i++) {
            assertEquals(i, array.get(i));
        }
        assertEquals(VALUE, array.get(INDEX));
        for (int i = INDEX + 1; i < array.size(); i++) {
            assertEquals(i - 1, array.get(i));
        }
    }

    @Test
    void insertIntoMultiRowArrayWithIncompleteLastRow() {
        final int NUMBER_OF_ROWS = 6;
        final int SIZE = VECTOR * NUMBER_OF_ROWS + (VECTOR / 2);
        for (int i = 0; i < SIZE; i++) {
            array.add(i);
        }
        assertEquals(SIZE, array.size());
        final int VALUE = 999991;
        final int INDEX = (VECTOR * NUMBER_OF_ROWS / 2) + (VECTOR / 2);
        array.add(VALUE, INDEX);
        assertEquals(SIZE + 1, array.size());
        for (int i = 0; i < INDEX; i++) {
            assertEquals(i, array.get(i));
        }
        assertEquals(VALUE, array.get(INDEX));
        for (int i = INDEX + 1; i < array.size(); i++) {
            assertEquals(i - 1, array.get(i));
        }
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