package ru.otus.tree.binary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.tree.IntTree;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {

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

    private AVLTree tree;

    @BeforeEach
    void setUp() {
        tree = new AVLTree();
    }

    @Test
    void insertSingleElement() {
        assertEquals(0, tree.size());
        tree.insert(HEAD_KEY);
        assertEquals(1, tree.size());
        assertTrue(tree.search(HEAD_KEY));
        assertFalse(tree.search(NOT_EXISTING_KEY));
        assertTreeHeight(tree, 1);
    }

    @Test
    void doubleInsertOfTheSameElement() {
        assertEquals(0, tree.size());
        tree.insert(HEAD_KEY);
        tree.insert(HEAD_KEY);
        assertEquals(1, tree.size());
        assertTrue(tree.search(HEAD_KEY));
        assertFalse(tree.search(NOT_EXISTING_KEY));
        assertTreeHeight(tree, 1);
    }

    @Test
    void removeSingleElement() {
        tree.insert(HEAD_KEY);
        tree.remove(HEAD_KEY);
        assertEquals(0, tree.size());
        assertFalse(tree.search(HEAD_KEY));
        assertTreeHeight(tree, 0);
    }

    @Test
    void doubleRemoveOfTheSameElement() {
        tree.insert(HEAD_KEY);
        tree.remove(HEAD_KEY);
        tree.remove(HEAD_KEY);
        assertEquals(0, tree.size());
        assertFalse(tree.search(HEAD_KEY));
        assertTreeHeight(tree, 0);
    }

    @Test
    void removeNotExistingElement() {
        assertEquals(0, tree.size());
        tree.remove(NOT_EXISTING_KEY);
        assertEquals(0, tree.size());
        assertFalse(tree.search(NOT_EXISTING_KEY));
        assertTreeHeight(tree, 0);
    }

    @Test
    void insertAll() {
        fillTree(tree, KEYS);
        assertEquals(KEYS.length, tree.size());
        for (int key : KEYS) {
            assertTrue(tree.search(key));
        }
        assertTreeHeight(tree, 3);
    }

    @Test
    void removeAll() {
        fillTree(tree, KEYS);
        for (int i = 0; i < KEYS.length; i++) {
            tree.remove(KEYS[i]);
            int expectedSize = KEYS.length - i - 1;
            assertTreeSize(tree, expectedSize);
        }
        assertEquals(0, tree.size());
        for (int key : KEYS) {
            assertFalse(tree.search(key));
        }
        assertTreeHeight(tree, 0);
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
        fillTree(tree, KEYS);
        tree.remove(key);
        assertEquals(KEYS.length - 1, tree.size());
        assertFalse(tree.search(key));
        assertTreeHeight(tree, 3);
    }

    private void fillTree(IntTree tree, int[] keys) {
        for (int key : keys) {
            tree.insert(key);
        }
    }

    private void assertTreeHeight(HasHeight tree, int expectedHeight) {
        assertEquals(expectedHeight, tree.height());
    }

    private void assertTreeSize(HasSize tree, int expectedHeight) {
        assertEquals(expectedHeight, tree.size());
    }
}
