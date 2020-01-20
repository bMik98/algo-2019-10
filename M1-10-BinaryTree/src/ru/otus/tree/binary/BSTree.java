package ru.otus.tree.binary;

public class BSTree extends AbstractBinaryTree<TreeNode> {

    @Override
    protected TreeNode createNode(int key) {
        return new BSTreeNode(key);
    }
}
