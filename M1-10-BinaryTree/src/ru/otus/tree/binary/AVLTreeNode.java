package ru.otus.tree.binary;

public class AVLTreeNode extends BinaryTreeNode {

    private int height;

    public AVLTreeNode(int key) {
        super(key);
        height = 0;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
