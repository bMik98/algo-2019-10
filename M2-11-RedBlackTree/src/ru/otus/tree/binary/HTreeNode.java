package ru.otus.tree.binary;

public interface HTreeNode extends TreeNode, HasHeight {

    void height(int height);

    @Override
    HTreeNode parent();

    @Override
    HTreeNode right();

    @Override
    HTreeNode left();

    default int balance() {
        return HasHeight.heightOf(right()) - HasHeight.heightOf(left());
    }

    default void updateHeight() {
        int heightOfLeft = HasHeight.heightOf(left());
        int heightOfRight = HasHeight.heightOf(right());
        int newHeight = Math.max(heightOfLeft, heightOfRight) + 1;
        height(newHeight);
    }
}
