package ru.otus.tree.binary;

import ru.otus.tree.IntTree;

import java.util.Optional;

public abstract class AbstractBinaryTree<E extends TreeNode> implements IntTree, HasSize {

    protected TreeNode root;
    private int size;

    public AbstractBinaryTree() {
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public E root() {
        return (E) this.root;
    }

    public void setRoot(E node) {
        this.root = node;
    }

    @Override
    public void insert(int key) {
        E nodeToInsert = createNode(key);
        if (root == null) {
            root = nodeToInsert;
            size++;
        } else {
            if (insertNode(root, nodeToInsert)) {
                size++;
            }
        }
    }

    protected abstract E createNode(int key);

    protected boolean insertNode(TreeNode start, TreeNode node) {
        TreeNode cursor = start;
        TreeNode parent;
        int comparison;
        do {
            parent = cursor;
            comparison = node.key() - cursor.key();
            if (comparison < 0) {
                cursor = cursor.left();
            } else if (comparison > 0) {
                cursor = cursor.right();
            } else {
                return false;
            }
        } while (cursor != null);
        if (comparison < 0) {
            parent.left(node);
        } else {
            parent.right(node);
        }
        node.parent(parent);
        return true;
    }

    @Override
    public boolean search(int key) {
        return findNode(key).isPresent();
    }

    @Override
    public void remove(int key) {
        findNode(key).ifPresent(node -> {
            removeNode(node);
            size--;
        });
    }

    protected TreeNode removeNode(TreeNode node) {
        TreeNode result = null;
        if (node.left() == null) {
            if (node.right() == null) {
                result = removeLeaf(node);
            } else {
                result = replaceByRight(node);
            }
        } else {
            if (node.right() == null) {
                result = replaceByLeft(node);
            } else {
                TreeNode max = findMax(node.left());
                node.key(max.key());
                result = removeNode(max);
            }
        }
        return result;
    }

    private TreeNode removeLeaf(TreeNode node) {
        if (node.parent() == null) {
            root = null;
            return null;
        }
        return node.unlinkParent();
    }

    private TreeNode replaceByLeft(TreeNode node) {
        TreeNode parent = node.unlinkParent();
        TreeNode left = node.unlinkLeft();
        if (parent == null) {
            root = left;
        } else {
            left.linkParent(parent);
        }
        return parent;
    }

    private TreeNode replaceByRight(TreeNode node) {
        TreeNode parent = node.unlinkParent();
        TreeNode right = node.unlinkRight();
        if (parent == null) {
            root = right;
        } else {
            right.linkParent(parent);
        }
        return parent;
    }

    private TreeNode findMax(TreeNode start) {
        TreeNode max = start;
        TreeNode cursor = start;
        while (cursor != null) {
            max = cursor;
            cursor = cursor.right();
        }
        return max;
    }

//    protected void removeNode(TreeNode node) {
//        TreeNode parent = node.unlinkParent();
//        TreeNode left = node.unlinkLeft();
//        TreeNode right = node.unlinkRight();
//        if (parent != null) {
//            if (left != null) {
//                insertNode(parent, left);
//            }
//            if (right != null) {
//                insertNode(parent, right);
//            }
//        } else {
//            if (left != null) {
//                root = left;
//                if (right != null) {
//                    insertNode(root, right);
//                }
//            } else {
//                root = right;
//            }
//        }
//    }

    @SuppressWarnings("unchecked")
    private Optional<E> findNode(int key) {
        TreeNode cursor = root;
        while (cursor != null) {
            int comparison = key - cursor.key();
            if (comparison < 0) {
                cursor = cursor.left();
            } else if (comparison > 0) {
                cursor = cursor.right();
            } else {
                return Optional.of((E) cursor);
            }
        }
        return Optional.empty();
    }

    @Override
    public int size() {
        return size;
    }
}
