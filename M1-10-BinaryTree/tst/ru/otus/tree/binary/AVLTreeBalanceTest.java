package ru.otus.tree.binary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.tree.IntTree;
import ru.otus.tree.utils.Helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AVLTreeBalanceTest {

    private static final int HEAD_KEY = 10;
    private static final int LEFT_KEY = 5;
    private static final int RIGHT_KEY = 20;
    private static final int LEFT_LEFT_KEY = 1;
    private static final int LEFT_RIGHT_KEY = 7;
    private static final int RIGHT_LEFT_KEY = 15;
    private static final int RIGHT_RIGHT_KEY = 25;
    private static final int[] KEYS = new int[]{
            HEAD_KEY, LEFT_KEY, RIGHT_KEY, LEFT_LEFT_KEY, LEFT_RIGHT_KEY, RIGHT_LEFT_KEY, RIGHT_RIGHT_KEY};

    private AVLTree tree;

    @BeforeEach
    void setUp() {
        tree = new AVLTree();
    }

    @Test
    public void smallRightRotationAfterInsert() {
        tree.insert(HEAD_KEY);
        tree.insert(LEFT_KEY);
        assertTreeHeight(tree, 2);
        assertTreeSize(tree, 2);
        assertEquals(HEAD_KEY, tree.root().key());
        tree.insert(LEFT_LEFT_KEY);
        assertTreeHeight(tree, 2);
        assertTreeSize(tree, 3);
        assertEquals(LEFT_KEY, tree.root().key());
    }

    @Test
    public void smallLeftRotationAfterInsert() {
        tree.insert(HEAD_KEY);
        tree.insert(RIGHT_KEY);
        assertTreeHeight(tree, 2);
        assertTreeSize(tree, 2);
        assertEquals(HEAD_KEY, tree.root().key());
        tree.insert(RIGHT_RIGHT_KEY);
        assertTreeHeight(tree, 2);
        assertTreeSize(tree, 3);
        assertEquals(RIGHT_KEY, tree.root().key());
    }

    @Test
    public void replaceByLeft() {
        tree.insert(HEAD_KEY);
        tree.insert(LEFT_KEY);
        assertTreeHeight(tree, 2);
        assertTreeSize(tree, 2);
        assertEquals(HEAD_KEY, tree.root().key());
        tree.remove(HEAD_KEY);
        assertTreeHeight(tree, 1);
        assertTreeSize(tree, 1);
        assertEquals(LEFT_KEY, tree.root().key());
    }

    @Test
    public void checkBalanceWhileInsert() {
        int size = 65535;
        fillTree(tree, Helper.createRandomArray(size));
        checkBalance(tree.root());
    }

    @Test
    public void checkBalanceAfterRemove() {
        int size = 2047;
        fillTree(tree, Helper.createRandomArray(size));
        int count = 1;
        for(int key: Helper.createRandomArray(size)) {
            tree.remove(key);
            checkBalance(tree.root());
        }
    }

    private void checkBalance(HTreeNode node) {
        if(node != null) {
            assertTrue(Math.abs(node.balance()) < 2);
            checkBalance(node.left());
            checkBalance(node.right());
        }
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
