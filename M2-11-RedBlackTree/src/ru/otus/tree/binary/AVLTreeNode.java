package ru.otus.tree.binary;

public class AVLTreeNode extends BSTreeNode implements HTreeNode {

    private int height;

    public AVLTreeNode(int key) {
        super(key);
        this.height = 1;
    }

    @Override
    public HTreeNode parent() {
        return (HTreeNode) super.parent();
    }

    @Override
    public HTreeNode right() {
        return (HTreeNode) super.right();
    }

    @Override
    public HTreeNode left() {
        return (HTreeNode) super.left();
    }

    @Override
    public int height() {
        return this.height;
    }

    @Override
    public void height(int height) {
        this.height = height;
    }
}
