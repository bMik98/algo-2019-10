package ru.otus.tree.binary;

public class BSTreeNode implements TreeNode {

    private int key;
    private TreeNode parent;
    private TreeNode left;
    private TreeNode right;

    public BSTreeNode(int key) {
        this.key = key;
    }

    @Override
    public int key() {
        return this.key;
    }

    @Override
    public void key(int key) {
        this.key = key;
    }

    @Override
    public TreeNode parent() {
        return this.parent;
    }

    @Override
    public void parent(TreeNode node) {
        this.parent = node;
    }

    @Override
    public TreeNode left() {
        return this.left;
    }

    @Override
    public void left(TreeNode node) {
        this.left = node;
    }

    @Override
    public TreeNode right() {
        return this.right;
    }

    @Override
    public void right(TreeNode node) {
        this.right = node;
    }
}
