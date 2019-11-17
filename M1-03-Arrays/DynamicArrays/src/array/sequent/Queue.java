package array.sequent;

public class Queue<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Node<T> getHead() {
        return head;
    }

    public void enqueue(T item) {
        Node<T> node = new Node<>(item);
        if(isEmpty()) {
            head = node;
        } else {
            tail.setNext(node);
        }
        tail = node;
        size++;
    }

    public T dequeue() {
        if(isEmpty()) {
            return null;
        }
        T item = head.getItem();
        head = head.getNext();
        size--;
        return item;
    }

}
