package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    private static final int[] VALUES = new int[]{123, 498, 112, 221, 456, 10};

    private Queue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new Queue<>();
    }

    @Test
    void testOfInitialisation() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        assertNull(queue.dequeue());
        assertNull(queue.getHead());
    }

    @Test
    void enqueue() {
        for (int i = 0; i < VALUES.length; i++) {
            queue.enqueue(VALUES[i]);
            assertEquals(i + 1, queue.size());
            assertFalse(queue.isEmpty());
            Node<Integer> head = queue.getHead();
            assertNotNull(head);
            assertEquals(VALUES[0], head.getItem());
        }
    }

    @Test
    void dequeue() {
        for (int item : VALUES) {
            queue.enqueue(item);
        }
        for (int i = 0; i < VALUES.length; i++) {
            Integer item = queue.dequeue();
            int expectedSize = VALUES.length - i - 1;
            assertEquals(expectedSize, queue.size());
            assertEquals(VALUES[i], item);
        }
        assertNull(queue.getHead());
        assertTrue(queue.isEmpty());
    }

    @Test
    void alternation() {
        for (int value : VALUES) {
            queue.enqueue(value);
            assertEquals(1, queue.size());
            Integer item = queue.dequeue();
            assertEquals(0, queue.size());
            assertEquals(value, item);
        }
    }
}