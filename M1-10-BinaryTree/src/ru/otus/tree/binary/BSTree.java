package ru.otus.tree.binary;

import ru.otus.tree.IntTree;

import java.util.Optional;

//public class BSTree implements IntTree, HasSize {
public class BSTree extends AbstractBinaryTree<TreeNode> {

//    protected TreeNode root;
//    private int size;

//    public BSTree() {
//        this.size = 0;
//    }

//    public TreeNode root() {
//        return this.root;
//    }
//
//    public void setRoot(TreeNode node) {
//        this.root = node;
//        root.unlinkParent();
//    }

//    @Override
//    public void insert(int key) {
//        TreeNode nodeToInsert = createNode(key);
//        if (root == null) {
//            root = nodeToInsert;
//            size++;
//        } else {
//            if (insertNode(root, nodeToInsert)) {
//                size++;
//            }
//        }
//    }

    @Override
    protected TreeNode createNode(int key) {
        return new BSTreeNode(key);
    }

//    protected boolean insertNode(TreeNode start, TreeNode node) {
//        TreeNode cursor = start;
//        TreeNode parent;
//        int comparison;
//        do {
//            parent = cursor;
//            comparison = node.key() - cursor.key();
//            if (comparison < 0) {
//                cursor = cursor.left();
//            } else if (comparison > 0) {
//                cursor = cursor.right();
//            } else {
//                return false;
//            }
//        } while (cursor != null);
//        if (comparison < 0) {
//            parent.left(node);
//        } else {
//            parent.right(node);
//        }
//        node.parent(parent);
//        return true;
//    }
//
//    @Override
//    public boolean search(int key) {
//        return findNode(key).isPresent();
//    }
//
//    @Override
//    public void remove(int key) {
//        findNode(key).ifPresent(node -> {
//            removeNode(node);
//            size--;
//        });
//    }
//
//    protected void removeNode(TreeNode node) {
//        TreeNode parent = node.parent();
//        TreeNode left = node.left();
//        TreeNode right = node.right();
//        unlinkNode(node);
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
//            if (root != null) {
//                root.parent(null);
//            }
//        }
//    }
//
//    private void unlinkNode(TreeNode node) {
//        node.unlinkParent();
//        node.right(null);
//        node.left(null);
//    }
//
//    private Optional<TreeNode> findNode(int key) {
//        TreeNode cursor = root;
//        while (cursor != null) {
//            int comparison = key - cursor.key();
//            if (comparison < 0) {
//                cursor = cursor.left();
//            } else if (comparison > 0) {
//                cursor = cursor.right();
//            } else {
//                return Optional.of(cursor);
//            }
//        }
//        return Optional.empty();
//    }
//
//    @Override
//    public int size() {
//        return size;
//    }
}
