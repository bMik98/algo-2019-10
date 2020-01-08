package ru.otus.tree.binary;

public class BinaryTreeNode {

    private final int key;

    private BinaryTreeNode parent;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public BinaryTreeNode(int key) {
        this.key = key;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }

    public BinaryTreeNode getParent() {
        return parent;
    }

    public void setParent(BinaryTreeNode parent) {
        this.parent = parent;
    }

    public int getKey() {
        return key;
    }

}
