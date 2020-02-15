package ru.otus.tree.binary;

import ru.otus.tree.IntTree;

import java.util.Optional;
import java.util.Random;

public class RandomBSTree implements IntTree, HasSize {

    private static final Random random = new Random(System.currentTimeMillis());

    private Node root;

    static int nodeSize(Node p) {
        return (p == null) ? 0 : p.size;
    }

    static void updateSize(Node p) {
        p.size = nodeSize(p.left) + nodeSize(p.right) + 1;
    }

    static Node rotateRight(Node p) {
        Node q = p.left;
        if (q == null) {
            return p;
        }
        p.left = q.right;
        q.right = p;
        q.size = p.size;
        updateSize(p);
        return q;
    }

    static Node rotateLeft(Node q) {
        Node p = q.right;
        if (p == null) {
            return q;
        }
        q.right = p.left;
        p.left = q;
        p.size = q.size;
        updateSize(q);
        return p;
    }

    static Node insertRoot(Node p, int key) {
        if (p == null) {
            return new Node(key);
        }
        if (key < p.key) {
            p.left = insertRoot(p.left, key);
            updateSize(p);
            return rotateRight(p);
        } else {
            p.right = insertRoot(p.right, key);
            updateSize(p);
            return rotateLeft(p);
        }
    }

    static Node insert(Node p, int key) {
        if (p == null) {
            return new Node(key);
        }
        if (p.key == key) {
            return p;
        }
        if (random.nextInt() % (p.size + 1) == 0) {
            return insertRoot(p, key);
        }
        if (p.key > key) {
            p.left = insert(p.left, key);
        } else {
            p.right = insert(p.right, key);
        }
        updateSize(p);
        return p;
    }

    static Optional<Node> find(Node p, int key) {
        if (p == null) {
            return Optional.empty();
        }
        if (key == p.key) {
            return Optional.of(p);
        }
        if (key < p.key) {
            return find(p.left, key);
        } else {
            return find(p.right, key);
        }
    }

    static Node remove(Node p, int k) {
        if (p == null) {
            return null;
        }
        if (p.key == k) {
            Node q = join(p.left, p.right);
            p.left = null;
            p.right = null;
            return q;
        } else if (k < p.key) {
            p.left = remove(p.left, k);
            updateSize(p);
        } else {
            p.right = remove(p.right, k);
            updateSize(p);
        }
        return p;
    }

    static Node join(Node p, Node q) {
        if (p == null) return q;
        if (q == null) return p;
        if (random.nextInt() % (p.size + q.size) < p.size) {
            p.right = join(p.right, q);
            updateSize(p);
            return p;
        } else {
            q.left = join(p, q.left);
            updateSize(q);
            return q;
        }
    }

    @Override
    public void insert(int key) {
        root = insert(root, key);
    }

    @Override
    public boolean search(int key) {
        return find(root, key).isPresent();
    }

    @Override
    public void remove(int key) {
        root = remove(root, key);
    }

    public int size() {
        return nodeSize(root);
    }

    /**
     * Random binary tree node
     */
    static class Node {
        int key;
        int size;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
            size = 1;
        }
    }
}
