package ru.otus.tree.binary;

interface TreeNode {

    int key();

    void key(int key);

    TreeNode parent();

    void parent(TreeNode node);

    TreeNode left();

    void left(TreeNode node);

    TreeNode right();

    void right(TreeNode node);

    default TreeNode unlinkParent() {
        TreeNode parent = parent();
        if (parent != null) {
            if (parent.left() != null && key() == parent.left().key()) {
                parent.left(null);
            } else if (parent.right() != null && key() == parent.right().key()) {
                parent.right(null);
            }
            parent(null);
            return parent;
        }
        return null;
    }

    default TreeNode unlinkLeft() {
        TreeNode oldLeft = left();
        if (oldLeft != null) {
            oldLeft.parent(null);
            left(null);
            return oldLeft;
        }
        return null;
    }

    default TreeNode unlinkRight() {
        TreeNode oldRight = right();
        if (oldRight != null) {
            oldRight.parent(null);
            right(null);
            return oldRight;
        }
        return null;
    }

    default void linkParent(TreeNode newParent) {
        if (newParent != null) {
            if (newParent.key() > key()) {
                newParent.left(this);
            } else {
                newParent.right(this);
            }
        }
        parent(newParent);
    }

    default void linkLeft(TreeNode node) {
        left(node);
        if (node != null) {
            node.parent(this);
        }
    }

    default void linkRight(TreeNode node) {
        right(node);
        if (node != null) {
            node.parent(this);
        }
    }
}
