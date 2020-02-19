package ru.otus.tree.binary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static ru.otus.tree.binary.SplayTree.Node;

class SplayTreeTest {
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

    private SplayTree tree;

    private static SplayTree createBalancedTree() {
        return new SplayTree(constructTree());
    }

    private static Node constructTree() {
        Node root = new Node(HEAD_KEY);
        root.left = new Node(LEFT_KEY);
        root.left.left = new Node(LEFT_LEFT_KEY);
        root.left.right = new Node(LEFT_RIGHT_KEY);
        root.right = new Node(RIGHT_KEY);
        root.right.left = new Node(RIGHT_LEFT_KEY);
        root.right.right = new Node(RIGHT_RIGHT_KEY);
        return root;
    }

    @BeforeEach
    void setUp() {
        tree = new SplayTree();
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
    void insertALotOfSequentialElements() {
        int count = 100_000;
        for (int i = 0; i < count; i++) {
            assertEquals(i, tree.size());
            tree.insert(i);
            System.out.println(i + " - " + tree.height());
        }
        assertEquals(count, tree.size());
        for (int i = 0; i < count; i++) {
            assertTrue(tree.search(HEAD_KEY));
        }
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
        assertFalse(tree.search(HEAD_KEY));
        assertEquals(0, tree.size());
    }

    @Test
    void doubleRemoveOfTheSameElement() {
        tree.insert(HEAD_KEY);
        tree.remove(HEAD_KEY);
        tree.remove(HEAD_KEY);
        assertFalse(tree.search(HEAD_KEY));
        assertEquals(0, tree.size());
    }

    @Test
    void removeNotExistingElement() {
        assertEquals(0, tree.size());
        tree.remove(NOT_EXISTING_KEY);
        assertEquals(0, tree.size());
        assertFalse(tree.search(NOT_EXISTING_KEY));
    }

    @Test
    void insertAll() {
        for (int i = 0; i < KEYS.length; i++) {
            assertEquals(i, tree.size());
            int key = KEYS[i];
            tree.insert(key);
            assertTrue(tree.search(key));
        }
        assertEquals(KEYS.length, tree.size());
    }

    @Test
    void removeAll() {
        tree = createBalancedTree();
        for (int i = 0; i < KEYS.length; i++) {
            tree.remove(KEYS[i]);
            int expectedSize = KEYS.length - i - 1;
            assertTreeSize(tree, expectedSize);
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
        tree = createBalancedTree();
        tree.remove(key);
        assertFalse(tree.search(key));
        assertEquals(KEYS.length - 1, tree.size());
    }

    private void assertTreeSize(HasSize tree, int expectedSize) {
        assertEquals(expectedSize, tree.size());
    }
}