package ru.otus.tree.binary;

import ru.otus.tree.IntTree;

public class SplayTree implements IntTree, HasSize, HasHeight {

    private Node root;

    public SplayTree() {
    }

    SplayTree(Node root) {
        this.root = root;
    }

    static int size(Node x) {
        return (x == null) ? 0 : 1 + size(x.left) + size(x.right);
    }

    static int height(Node x) {
        return (x == null) ? 0 : 1 + Math.max(height(x.left), height(x.right));
    }

    static Node rotateLeft(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        return right;
    }

    static Node rotateRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        return left;
    }

    @Override
    public boolean search(int key) {
        if (root == null) {
            return false;
        }
        root = splay(root, key);
        return (root.key == key);
    }

    @Override
    public void insert(int key) {
        if (root == null) {
            root = new Node(key);
            return;
        }
        root = splay(root, key);
        Node node = new Node(key);
        if (key < root.key) {
            node.left = root.left;
            node.right = root;
            root.left = null;
            root = node;
        } else if (key > root.key) {
            node.right = root.right;
            node.left = root;
            root.right = null;
            root = node;
        }
    }

    @Override
    public void remove(int key) {
        if (root == null) {
            return;
        }
        root = splay(root, key);
        if (key == root.key) {
            if (root.left == null) {
                root = root.right;
            } else {
                Node x = root.right;
                root = root.left;
                root = splay(root, key);
                root.right = x;
            }
        }
    }
    static Node splay(Node node, int key)
    {
        // Base cases: node is null or 
        // key is present at node  
        if (node == null || node.key == key)
            return node;

        // Key lies in left subtree  
        if (node.key > key)
        {
            // Key is not in tree, we are done  
            if (node.left == null) return node;

            // Zig-Zig (Left Left)  
            if (node.left.key > key)
            {
                // First recursively bring the 
                // key as node of left-left  
                node.left.left = splay(node.left.left, key);

                // Do first rotation for node,  
                // second rotation is done after else  
                node = rotateRight(node);
            }
            else if (node.left.key < key) // Zig-Zag (Left Right)  
            {
                // First recursively bring 
                // the key as node of left-right  
                node.left.right = splay(node.left.right, key);

                // Do first rotation for node.left  
                if (node.left.right != null)
                    node.left = rotateLeft(node.left);
            }

            // Do second rotation for node  
            return (node.left == null) ?
                    node : rotateRight(node);
        }
        else // Key lies in right subtree  
        {
            // Key is not in tree, we are done  
            if (node.right == null) return node;

            // Zag-Zig (Right Left)  
            if (node.right.key > key)
            {
                // Bring the key as node of right-left  
                node.right.left = splay(node.right.left, key);

                // Do first rotation for node.right  
                if (node.right.left != null)
                    node.right = rotateRight(node.right);
            }
            else if (node.right.key < key)// Zag-Zag (Right Right)  
            {
                // Bring the key as node of  
                // right-right and do first rotation  
                node.right.right = splay(node.right.right, key);
                node = rotateLeft(node);
            }

            // Do second rotation for node  
            return (node.right == null) ?
                    node : rotateLeft(node);
        }
    }

//    private Node splay(Node node, int key) {
//        if (node == null) {
//            return null;
//        }
//        if (key < node.key) {
//            if (node.left == null) {
//                return node;
//            }
//            if (key < node.left.key) {
//                node.left.left = splay(node.left.left, key);
//                node = rotateRight(node);
//            } else if (key > node.left.key) {
//                node.left.right = splay(node.left.right, key);
//                if (node.left.right != null) {
//                    node.left = rotateLeft(node.left);
//                }
//            }
//            return (node.left == null) ? node : rotateRight(node);
//        } else if (key > node.key) {
//            if (node.right == null) {
//                return node;
//            }
//            if (key < node.right.key) {
//                node.right.left = splay(node.right.left, key);
//                if (node.right.left != null)
//                    node.right = rotateRight(node.right);
//            } else if (key > node.right.key) {
//                node.right.right = splay(node.right.right, key);
//                node = rotateLeft(node);
//            }
//            return (node.right == null) ? node : rotateLeft(node);
//        } else {
//            return node;
//        }
//    }

    public int height() {
        return height(root);
    }

    public int size() {
        return size(root);
    }

    /**
     * Splay binary tree node
     */
    static class Node {
        int key;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
        }
    }
}
