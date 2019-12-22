package ru.otus.hashtable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuadraticHTableTest {

    private static final String KEY = "key";
    private static final int VALUE = 123;
    private static final int NUMBER_OF_ELEMENTS = AbstractOpenAddressingHTable.INITIAL_SIZE * 3;

    private HTable<String, Integer> hTable;

    @BeforeEach
    void setUp() {
        hTable = new QuadraticHTable<>();
    }

    @Test
    void putAndSize() {
        for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            String key = makeKey(i);
            Integer replacedValue = hTable.put(key, i);
            assertNull(replacedValue);
            int expectedSize = i + 1;
            assertEquals(expectedSize, hTable.size());
        }
        assertEquals(NUMBER_OF_ELEMENTS, hTable.size());
    }

    @Test
    void putAndGet() {
        putAndSize();
        int numberOfElements = hTable.size();
        for (int i = 0; i < numberOfElements; i++) {
            Integer foundValue = hTable.get(makeKey(i));
            assertEquals(i, foundValue);
        }
        String absentKey = makeKey(numberOfElements);
        assertNull(hTable.get(absentKey));
        assertEquals(numberOfElements, hTable.size());
    }

    private String makeKey(Integer value) {
        return "key" + value;
    }

    @Test
    void replace() {
        hTable.put(KEY, VALUE);
        Integer newValue = VALUE + VALUE;
        assertEquals(VALUE, hTable.put(KEY, newValue));
        assertEquals(newValue, hTable.get(KEY));
        assertEquals(1, hTable.size());
    }

    @Test
    void delete() {
        hTable.put(KEY, VALUE);
        assertEquals(1, hTable.size());
        assertEquals(VALUE, hTable.get(KEY));
        Integer deletedValue = hTable.delete(KEY);
        assertEquals(VALUE, deletedValue);
        assertEquals(0, hTable.size());
        assertNull(hTable.get(KEY));
    }

    @Test
    void deleteAbsentKey() {
        assertNull(hTable.delete(KEY));
    }

    @Test
    void memoryAllocation() {
        int allocatedSizeBefore = AbstractOpenAddressingHTable.INITIAL_SIZE;
        assertEquals(allocatedSizeBefore, hTable.allocatedSize());
        int thresholdSize = (int) (allocatedSizeBefore * AbstractOpenAddressingHTable.DEFAULT_LOAD_FACTOR);
        for (int i = 0; i < thresholdSize; i++) {
            hTable.put(makeKey(i), i);
        }
        assertEquals(allocatedSizeBefore, hTable.allocatedSize());
        hTable.put(makeKey(thresholdSize), thresholdSize);
        assertTrue(allocatedSizeBefore < hTable.allocatedSize());
    }

    @Test
    void memoryAllocation2() {
        hTable = new DoubleHashingHTable<>(1.0f);
        int allocatedSizeBefore = AbstractOpenAddressingHTable.INITIAL_SIZE;
        assertEquals(allocatedSizeBefore, hTable.allocatedSize());
        int thresholdSize = hTable.allocatedSize();
        for (int i = 0; i < thresholdSize; i++) {
            hTable.put(makeKey(i), i);
        }
        assertEquals(allocatedSizeBefore, hTable.allocatedSize());
        hTable.put(makeKey(thresholdSize), thresholdSize);
        assertTrue(allocatedSizeBefore < hTable.allocatedSize());
    }

    @Test
    void memoryAllocationAfterDelete() {
        int allocatedSizeBefore = AbstractOpenAddressingHTable.INITIAL_SIZE;
        assertEquals(allocatedSizeBefore, hTable.allocatedSize());
        int thresholdSize = (int) (allocatedSizeBefore * AbstractOpenAddressingHTable.DEFAULT_LOAD_FACTOR);
        for (int i = 0; i < thresholdSize; i++) {
            hTable.put(makeKey(i), i);
        }
        assertEquals(allocatedSizeBefore, hTable.allocatedSize());
        hTable.delete(makeKey(0));
        hTable.put(makeKey(thresholdSize), thresholdSize);
        assertEquals(thresholdSize, hTable.get(makeKey(thresholdSize)));
        assertEquals(allocatedSizeBefore, hTable.allocatedSize());
    }
}