package ru.otus.tree.binary;

/**
 * AVL Tree
 */
public class AVLTree extends AbstractBinaryTree<HTreeNode> implements HasHeight {

    @Override
    protected boolean insertNode(TreeNode start, TreeNode node) {
        if (super.insertNode(start, node)) {
            upwindBalance((HTreeNode) node.parent());
            return true;
        }
        return false;
    }

    @Override
    protected TreeNode removeNode(TreeNode node) {
        HTreeNode parent = (HTreeNode) super.removeNode(node);
        if(parent != null) {
            upwindBalance(parent);
        }
        return parent;
    }

    @Override
    protected HTreeNode createNode(int key) {
        return new AVLTreeNode(key);
    }

    private void upwindBalance(HTreeNode node) {
        if (node != null) {
            HTreeNode parent = node.parent();
            node.updateHeight();
            int balance = node.balance();
            if (balance <= -2) {
                rightRotate(node);
            } else if (balance >= 2) {
                leftRotate(node);
            }
            upwindBalance(parent);
        }
    }

    private void rightRotate(HTreeNode node) {
        HTreeNode left = node.left();
        if (left.balance() > 0) {
            smallLeftRotation(left);
            smallRightRotation(node);
        } else {
            smallRightRotation(node);
        }
    }

    private void leftRotate(HTreeNode node) {
        HTreeNode right = node.right();
        if (right.balance() < 0) {
            smallRightRotation(right);
            smallLeftRotation(node);
        } else {
            smallLeftRotation(node);
        }
    }

    private void smallRightRotation(HTreeNode a) {
        HTreeNode parent = (HTreeNode) a.unlinkParent();
        HTreeNode b = (HTreeNode) a.unlinkLeft();
        HTreeNode c = (HTreeNode) b.unlinkRight();
        a.linkLeft(c);
        b.linkRight(a);
        a.updateHeight();
        b.updateHeight();
        if (parent != null) {
            b.linkParent(parent);
        } else {
            setRoot(b);
        }
    }

    private void smallLeftRotation(HTreeNode a) {
        HTreeNode parent = (HTreeNode) a.unlinkParent();
        HTreeNode b = (HTreeNode) a.unlinkRight();
        HTreeNode c = (HTreeNode) b.unlinkLeft();
        a.linkRight(c);
        b.linkLeft(a);
        a.updateHeight();
        b.updateHeight();
        if (parent != null) {
            b.linkParent(parent);
        } else {
            setRoot(b);
        }
    }

    @Override
    public int height() {
        return (root() == null) ? 0 : root().height();
    }
}
