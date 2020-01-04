package ru.otus.tree.binary;

import ru.otus.tree.IntTree;

public class BinaryTree implements IntTree {
    private IntTreeNode root;
    private int size;

    public BinaryTree() {
        this.size = 0;
    }

    @Override
    public void insert(int key) {
        IntTreeNode node = new IntTreeNode(key);
        if (root == null) {
            root = node;
            size = 1;
        } else {
            boolean added = insert(root, node);
            if (added) {
                size++;
            }
        }
    }

    private boolean insert(IntTreeNode start, IntTreeNode node) {
        IntTreeNode cursor = start;
        IntTreeNode parent;
        int comparison;
        do {
            parent = cursor;
            comparison = compare(node, cursor);
            if (comparison < 0) {
                cursor = cursor.getLeft();
            } else if (comparison > 0) {
                cursor = cursor.getRight();
            } else {
                return false;
            }
        } while (cursor != null);
        if (comparison < 0) {
            parent.setLeft(node);
        } else {
            parent.setRight(node);
        }
        node.setParent(parent);
        return true;
    }

    @Override
    public boolean search(int key) {
        return findNode(key) != null;
    }

    @Override
    public void remove(int key) {
        IntTreeNode node = findNode(key);
        if (node == null) {
            return;
        }
        IntTreeNode parent = node.getParent();
        IntTreeNode left = node.getLeft();
        IntTreeNode right = node.getRight();
        removeNode(node);
        if (parent != null) {
            if (left != null) {
                insert(parent, left);
            }
            if (right != null) {
                insert(parent, right);
            }
        } else {
            if (left != null) {
                root = left;
                if (right != null) {
                    insert(root, right);
                }
            } else {
                root = right;
            }
            if (root != null) {
                root.setParent(null);
            }
        }
        size--;
    }

    private void removeNode(IntTreeNode node) {
        IntTreeNode parent = node.getParent();
        if (parent != null) {
            if (parent.getLeft() != null && node.getKey() == parent.getLeft().getKey()) {
                parent.setLeft(null);
            } else if (parent.getRight() != null && node.getKey() == parent.getRight().getKey()) {
                parent.setRight(null);
            }
            node.setParent(null);
        }
        node.setRight(null);
        node.setLeft(null);
    }

    private IntTreeNode findNode(int key) {
        IntTreeNode cursor = root;
        IntTreeNode nodeToFind = new IntTreeNode(key);
        while (cursor != null) {
            int comparison = compare(nodeToFind, cursor);
            if (comparison < 0) {
                cursor = cursor.getLeft();
            } else if (comparison > 0) {
                cursor = cursor.getRight();
            } else {
                return cursor;
            }
        }
        return null;
    }

    private int compare(IntTreeNode n1, IntTreeNode n2) {
        if (n1.getKey() == n2.getKey()) {
            return 0;
        }
        return (n1.getKey() < n2.getKey()) ? -1 : 1;
    }

    @Override
    public int size() {
        return size;
    }
}
