package ru.otus.tree.binary;

public interface HasHeight {

    static int heightOf(HasHeight node) {
        return (node == null) ? 0 : node.height();
    }

    int height();
}
