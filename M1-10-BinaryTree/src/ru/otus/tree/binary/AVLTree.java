package ru.otus.tree.binary;

public class AVLTree extends BinaryTree {

    @Override
    public void insert(int key) {
        super.insert(key);
        rebalance();
    }

    @Override
    public void remove(int key) {
        super.remove(key);
    }

    private void bigLeftRotation(AVLTreeNode node) {
        smallRightRotation(right(node));
        smallLeftRotation(node);
    }

    private void bigRightRotation(AVLTreeNode node) {
        smallLeftRotation(left(node));
        smallRightRotation(node);
    }

    protected void smallLeftRotation(AVLTreeNode a) {
        AVLTreeNode b = right(a);
        swapParenthood(a, b);
        a.setRight(b.getLeft());
        recalculateHeight(a);
        recalculateHeight(b);
    }

    protected void smallRightRotation(AVLTreeNode a) {
        AVLTreeNode b = left(a);
        swapParenthood(a, b);
        a.setLeft(b.getRight());
        recalculateHeight(a);
        recalculateHeight(b);
    }

    private void swapParenthood(AVLTreeNode parent, AVLTreeNode child) {
        child.setParent(parent.getParent());
        if (child.getParent() == null) {
            root = child;
        }
        parent.setParent(child);
    }

    private void recalculateHeight(AVLTreeNode node) {
        int height = Math.max(height(left(node)), height(right(node))) + 1;
        node.setHeight(height);
    }

    private int height(AVLTreeNode node) {
        return (node == null) ? 0 : node.getHeight();
    }

    private int balance(AVLTreeNode node) {
        return height(right(node)) - height(left(node));
    }

    private AVLTreeNode left(AVLTreeNode node) {
        return (AVLTreeNode) node.getLeft();
    }

    private AVLTreeNode right(AVLTreeNode node) {
        return (AVLTreeNode) node.getRight();
    }

    protected void rebalance() {

    }

    public int getHeight() {
        return height((AVLTreeNode) root);
    }

    @Override
    protected BinaryTreeNode createNode(int key) {
        return new AVLTreeNode(key);
    }
}
