package ru.otus.tree.binary;

public class IntTreeNode {

    private final int key;

    private IntTreeNode parent;
    private IntTreeNode left;
    private IntTreeNode right;

    public IntTreeNode(int key) {
        this.key = key;
    }

    public IntTreeNode getLeft() {
        return left;
    }

    public void setLeft(IntTreeNode left) {
        this.left = left;
    }

    public IntTreeNode getRight() {
        return right;
    }

    public void setRight(IntTreeNode right) {
        this.right = right;
    }

    public IntTreeNode getParent() {
        return parent;
    }

    public void setParent(IntTreeNode parent) {
        this.parent = parent;
    }

    public int getKey() {
        return key;
    }

}
