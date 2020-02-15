package ru.otus.tree.binary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.tree.binary.RandomBSTree.Node;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RandomBSTreeAdvancedTest {

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

    private Node root;

    @BeforeEach
    void setUp() {
        root = constructFullTree();
    }

    private Node constructFullTree() {
        Node root = new Node(HEAD_KEY);
        root.left = new Node(LEFT_KEY);
        root.left.left = new Node(LEFT_LEFT_KEY);
        root.left.right = new Node(LEFT_RIGHT_KEY);
        root.right = new Node(RIGHT_KEY);
        root.right.left = new Node(RIGHT_LEFT_KEY);
        root.right.right = new Node(RIGHT_RIGHT_KEY);
        RandomBSTree.updateSize(root.right);
        RandomBSTree.updateSize(root.left);
        RandomBSTree.updateSize(root);
        return root;
    }

    @Test
    void testNodeSize() {
        assertEquals(7, root.size);
        assertEquals(7, RandomBSTree.nodeSize(root));
        assertEquals(3, root.left.size);
        assertEquals(3, RandomBSTree.nodeSize(root.left));
        assertEquals(3, root.right.size);
        assertEquals(3, RandomBSTree.nodeSize(root.right));
        assertEquals(1, root.left.left.size);
        assertEquals(1, RandomBSTree.nodeSize(root.left.left));
        assertEquals(1, root.left.right.size);
        assertEquals(1, RandomBSTree.nodeSize(root.left.right));
        assertEquals(1, root.right.left.size);
        assertEquals(1, RandomBSTree.nodeSize(root.right.left));
        assertEquals(1, root.right.right.size);
        assertEquals(1, RandomBSTree.nodeSize(root.right.right));
    }

    @Test
    public void rotateLeftOfLeaf() {
        rotateLeftOfLeaf(root.left.left);
        rotateLeftOfLeaf(root.left.right);
        rotateLeftOfLeaf(root.right.left);
        rotateLeftOfLeaf(root.right.right);
    }

    private void rotateLeftOfLeaf(Node leaf) {
        Node node = RandomBSTree.rotateLeft(leaf);
        assertEquals(leaf.key, node.key);
        assertEquals(1, leaf.size);
        assertEquals(KEYS.length, root.size);
    }

    @Test
    public void rotateRightOfLeaf() {
        rotateRightOfLeaf(root.left.left);
        rotateRightOfLeaf(root.left.right);
        rotateRightOfLeaf(root.right.left);
        rotateRightOfLeaf(root.right.right);
    }

    private void rotateRightOfLeaf(Node leaf) {
        Node node = RandomBSTree.rotateRight(leaf);
        assertEquals(leaf.key, node.key);
        assertEquals(1, leaf.size);
        assertEquals(KEYS.length, root.size);
    }

    @Test
    public void insertRoot() {
        root = RandomBSTree.insertRoot(root, 40);
        assertEquals(KEYS.length + 1, root.size);
    }
}
