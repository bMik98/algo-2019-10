package ru.otus.tree.binary;

import ru.otus.tree.IntTree;

public class BinaryTree implements IntTree {
    protected BinaryTreeNode root;
    private int size;

    public BinaryTree() {
        this.size = 0;
    }

    @Override
    public void insert(int key) {
        BinaryTreeNode node = createNode(key);
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

    protected BinaryTreeNode createNode(int key) {
        return new BinaryTreeNode(key);
    }

    private boolean insert(BinaryTreeNode start, BinaryTreeNode node) {
        BinaryTreeNode cursor = start;
        BinaryTreeNode parent;
        int comparison;
        do {
            parent = cursor;
            comparison = compare(node.getKey(), cursor);
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
        BinaryTreeNode node = findNode(key);
        if (node == null) {
            return;
        }
        BinaryTreeNode parent = node.getParent();
        BinaryTreeNode left = node.getLeft();
        BinaryTreeNode right = node.getRight();
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

    private void removeNode(BinaryTreeNode node) {
        BinaryTreeNode parent = node.getParent();
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

    private BinaryTreeNode findNode(int key) {
        BinaryTreeNode cursor = root;
        while (cursor != null) {
            int comparison = compare(key, cursor);
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

    private int compare(int key, BinaryTreeNode node) {
        if (key == node.getKey()) {
            return 0;
        }
        return (key < node.getKey()) ? -1 : 1;
    }

    @Override
    public int size() {
        return size;
    }
}
