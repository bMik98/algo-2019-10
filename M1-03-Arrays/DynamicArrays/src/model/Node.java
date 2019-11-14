package model;

class Node<T> {

    private final T item;
    private Node<T> next;

    Node(T item) {
        this.item = item;
    }

    Node(T item, Node<T> next) {
        this.item = item;
        this.next = next;
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