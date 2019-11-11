package model;

class Node<T> {

    private final T item;
    private Node<T> next;

    Node(T item) {
        this.item = item;
    }

    T getItem() {
        return item;
    }

    Node<T> getNext() {
        return next;
    }

    void setNext(Node<T> next) {
        this.next = next;
    }
}
