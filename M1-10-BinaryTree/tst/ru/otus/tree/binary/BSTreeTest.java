package ru.otus.tree.binary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.tree.IntTree;

import static org.junit.jupiter.api.Assertions.*;

class BSTreeTest {

    private static final int HEAD_KEY = 10;
    private static final int LEFT_KEY = 5;
    private static final int RIGHT_KEY = 20;
    private static final int LEFT_LEFT_KEY = 1;
    private static final int LEFT_RIGHT_KEY = 7;
    private static final int RIGHT_LEFT_KEY = 15;
    private static final int RIGHT_RIGHT_KEY = 25;
    private static final int NOT_EXISTING_KEY = 1000;
    private static final int[] KEYS = new int[]{
            HEAD_KEY, LEFT_KEY, RIGHT_KEY, LEFT_LEFT_KEY, LEFT_RIGHT_KEY, RIGHT_LEFT_KEY, RIGHT_RIGHT_KEY};

    private BSTree tree;

    @BeforeEach
    void setUp() {
        tree = new BSTree();
    }

    @Test
    void insertSingleElement() {
        assertEquals(0, tree.size());
        tree.insert(HEAD_KEY);
        assertEquals(1, tree.size());
        assertTrue(tree.search(HEAD_KEY));
        assertFalse(tree.search(NOT_EXISTING_KEY));
    }

    @Test
    void doubleInsertOfTheSameElement() {
        assertEquals(0, tree.size());
        tree.insert(HEAD_KEY);
        tree.insert(HEAD_KEY);
        assertEquals(1, tree.size());
        assertTrue(tree.search(HEAD_KEY));
        assertFalse(tree.search(NOT_EXISTING_KEY));
    }

    @Test
    void removeSingleElement() {
        tree.insert(HEAD_KEY);
        tree.remove(HEAD_KEY);
        assertEquals(0, tree.size());
        assertFalse(tree.search(HEAD_KEY));
    }

    @Test
    void doubleRemoveOfTheSameElement() {
        tree.insert(HEAD_KEY);
        tree.remove(HEAD_KEY);
        tree.remove(HEAD_KEY);
        assertEquals(0, tree.size());
        assertFalse(tree.search(HEAD_KEY));
    }

    @Test
    void removeNotExistingElement() {
        assertEquals(0, tree.size());
        tree.remove(NOT_EXISTING_KEY);
        assertEquals(0, tree.size());
        assertFalse(tree.search(NOT_EXISTING_KEY));
    }

    @Test
    void insert() {
        fillTree(tree);
        assertEquals(KEYS.length, tree.size());
        for (int key : KEYS) {
            assertTrue(tree.search(key));
        }
    }

    @Test
    void remove() {
        fillTree(tree);
        for (int i = 0; i < KEYS.length; i++) {
            tree.remove(KEYS[i]);
            int expectedSize = KEYS.length - i - 1;
            assertEquals(expectedSize, tree.size());
        }
        assertEquals(0, tree.size());
        for (int key : KEYS) {
            assertFalse(tree.search(key));
        }
    }

    @Test
    void removeHead() {
        removeOneElement(HEAD_KEY);
    }

    @Test
    void removeLeft() {
        removeOneElement(LEFT_KEY);
    }

    @Test
    void removeRight() {
        removeOneElement(RIGHT_KEY);
    }

    @Test
    void removeLeftLeft() {
        removeOneElement(LEFT_LEFT_KEY);
    }

    @Test
    void removeLeftRight() {
        removeOneElement(LEFT_RIGHT_KEY);
    }

    @Test
    void removeRightLeft() {
        removeOneElement(RIGHT_LEFT_KEY);
    }

    @Test
    void removeRightRight() {
        removeOneElement(RIGHT_RIGHT_KEY);
    }

    void removeOneElement(int key) {
        fillTree(tree);
        tree.remove(key);
        assertEquals(KEYS.length - 1, tree.size());
        assertFalse(tree.search(key));
    }

    private void fillTree(IntTree tree) {
        for (int key : KEYS) {
            tree.insert(key);
        }
    }
}